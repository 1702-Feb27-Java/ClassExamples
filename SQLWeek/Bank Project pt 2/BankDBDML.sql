/******************************************************************************
POPULATE SOME STUFF NOW
******************************************************************************/

/******************************************************************************
lOOKUP TABLES
******************************************************************************/

INSERT INTO ROLE VALUES (1, 'Admin');
INSERT INTO ROLE VALUES (2, 'Employee');
INSERT INTO ROLE VALUES (3, 'Customer');

SELECT * FROM ROLE;

INSERT INTO AccountType VALUES (1, 'Checking');
INSERT INTO AccountType VALUES (2, 'Saving');
INSERT INTO AccountType VALUES (3, 'Joint');

SELECT * FROM ACCOUNTTYPE;

INSERT INTO Status VALUES (1, 'Pending');
INSERT INTO Status VALUES (2, 'Active');

SELECT * FROM Status;

/******************************************************************************
ADD THE ADMIN AND EMPLOYEES
******************************************************************************/

-- creating our admin
INSERT INTO Users VALUES (1, 'Danni', 'Tang', 'dtang', '1234', 1);
-- creating a default employee
INSERT INTO Users VALUES (2, 'John', 'Smith', 'default', '1234', 2);

SELECT * FROM Users;
COMMIT;


/******************************************************************************
NOW WE NEED SEQUENCES AND TRIGGERS
******************************************************************************/

-- sequence and trigger for creating a new user
CREATE SEQUENCE user_seq
  START WITH 3  -- because we already have 2 default accounts stored
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER user_seq_trigger
  BEFORE INSERT ON Users
    FOR EACH ROW
  BEGIN
    SELECT user_seq.NEXTVAL INTO :new.user_id FROM dual;
  END;
/

-- sequence and trigger for creating a new account
CREATE SEQUENCE account_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER account_seq_trigger
  BEFORE INSERT ON Accounts
    FOR EACH ROW
  BEGIN
    SELECT account_seq.NEXTVAL INTO :new.account_id FROM dual;
  END;
/

/******************************************************************************
PROCEDURES AND FUNCTIONS
******************************************************************************/

CREATE OR REPLACE PROCEDURE addUser(fName IN VARCHAR2, lName IN VARCHAR2, 
uname IN VARCHAR2, pw IN VARCHAR2)
IS
BEGIN
  INSERT INTO Users VALUES ('', fName, lName, uname, pw, 3); -- only add customers
END;
/

CREATE OR REPLACE PROCEDURE addAccounts
(typeid IN NUMBER, customer_id IN NUMBER)
IS
BEGIN
  INSERT INTO Accounts (type_id) VALUES (typeid);
  INSERT INTO CustomerAccounts VALUES 
  (customer_id, account_seq.CURRVAL);
END;
/

/******************************************************************************
SEQUENCES/TRIGGERS/PROCEDURES FOR LOGGING
******************************************************************************/

CREATE SEQUENCE log_user_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER log_user_seq_trigger
  BEFORE INSERT ON LogsUsers
    FOR EACH ROW
  BEGIN
    SELECT log_user_seq.NEXTVAL INTO :new.log_id FROM dual;
  END;
/

CREATE SEQUENCE log_account_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER log_account_seq_trigger
  BEFORE INSERT ON LogsAccounts
    FOR EACH ROW
  BEGIN
    SELECT log_account_seq.NEXTVAL INTO :new.log_id FROM dual;
  END;
/
-----------------------------------------------------------
CREATE OR REPLACE TRIGGER log_user_created
  BEFORE INSERT ON Users
    FOR EACH ROW
  DECLARE new_user NUMBER;
  BEGIN
    SELECT MAX(user_id).NEXTVAL INTO new_user FROM USERS;
    INSERT INTO Logs VALUES ('', current_TIMESTAMP, 'User account ' || new_user || + ' created.');
  END;
/
----------------------------------------------------------
-- logging changes to the Users table
CREATE OR REPLACE TRIGGER users_change
  AFTER INSERT OR UPDATE OR DELETE ON Users
    FOR EACH ROW
  DECLARE currID NUMBER;
  BEGIN
    SELECT user_seq.CURRVAL INTO currID FROM dual;
    IF INSERTING THEN
      INSERT INTO LogsUsers (log_time, log_operation, log_user_id, newFirstName, newLastName, newUname, newPW) 
      VALUES (CURRENT_TIMESTAMP, 'Insert', currID, :NEW.firstname, :NEW.lastname, :NEW.Uname, :NEW.PW);
    ELSIF DELETING THEN
    INSERT INTO LogsUsers (log_time, log_operation, log_user_id, oldFirstName, oldLastName, oldUname, oldPW) 
      VALUES (CURRENT_TIMESTAMP, 'Delete', currID, :OLD.firstname, :OLD.lastname, :OLD.Uname, :OLD.PW);
    ELSIF UPDATING THEN
      INSERT INTO LogsUsers (log_time, log_operation, log_user_id, oldFirstName, oldLastName, oldUname, oldPW, newFirstName, newLastName, newUname, newPW) 
      VALUES (CURRENT_TIMESTAMP, 'Update', currID, :OLD.firstname, :OLD.lastname, :OLD.Uname, :OLD.PW, :NEW.firstname, :NEW.lastname, :NEW.Uname, :NEW.PW);
    END IF;
  END;
/

-- logging changes to the Accounts table
CREATE OR REPLACE TRIGGER accounts_change
  AFTER INSERT OR UPDATE OR DELETE ON Accounts
    FOR EACH ROW
  DECLARE currID NUMBER;
  BEGIN
    SELECT account_seq.CURRVAL INTO currID FROM dual;
    IF INSERTING THEN
      INSERT INTO LogsAccounts (log_time, log_operation, log_account_id, newBalance, newStatus, newResolver) 
      VALUES (CURRENT_TIMESTAMP, 'Insert', currID, :NEW.balance, :NEW.status_id, :NEW.resolver_id);
    ELSIF DELETING THEN
    INSERT INTO LogsAccounts (log_time, log_operation, log_account_id, oldBalance, oldStatus, oldResolver) 
      VALUES (CURRENT_TIMESTAMP, 'Delete', currID, :OLD.balance, :OLD.status_id, :OLD.resolver_id);
    ELSIF UPDATING THEN
      INSERT INTO LogsAccounts (log_time, log_operation, log_account_id, oldBalance, oldStatus, oldResolver, newBalance, newStatus, newResolver) 
      VALUES (CURRENT_TIMESTAMP, 'Update', currID, :OLD.balance, :OLD.status_id, :OLD.resolver_id, :NEW.balance, :NEW.status_id, :NEW.resolver_id);
    END IF;
  END;
/