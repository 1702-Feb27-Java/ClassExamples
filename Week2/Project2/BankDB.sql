CREATE TABLE Role 
(
    role_id NUMBER,
    role VARCHAR2(20),
    
    CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE LOGS
(
    log_id number,
    time_stamp varchar2(4000),
    message varchar(4000),
    PRIMARY KEY (log_id)    
    
);

--log sequence for primary key
CREATE SEQUENCE log_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
  /
 -- before insert on table of logs select from log sequence
CREATE OR REPLACE TRIGGER log_trigger --name
BEFORE INSERT ON LOGS --on event
FOR EACH ROW --how often
BEGIN
  SELECT log_seq.NEXTVAL
  INTO :new.log_id
  FROM dual;
END;

-- adds to the loging table
CREATE OR REPLACE PROCEDURE addLog(time_stam in varchar2, mess in varchar2)
IS
BEGIN
    INSERT INTO LOGS (time_stamp, message) VALUES (time_stam, mess);
END;

-- testing calls
call addLog('3/13/2017', 'second Message');
select * from Logs;
commit;
TRUNCATE TABLE LOGS;
/








/
--


CREATE TABLE Users
(
  user_id NUMBER,
  full_name VARCHAR2(25) NOT NULL,  
  username VARCHAR2(25) NOT NULL UNIQUE,
  pass VARCHAR(25) NOT NULL,
  role_id NUMBER,
  PRIMARY KEY(user_id),
  FOREIGN KEY(role_id) REFERENCES Role(role_id)ON DELETE CASCADE
);

/

CREATE TABLE Status 
(
    status_id NUMBER,
    status VARCHAR2(20),
    
    CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

/

CREATE TABLE AccountType
(
    account_type_id NUMBER,
    account_type VARCHAR2(20),
    PRIMARY KEY(account_type_id)
    
);

/

CREATE TABLE Accounts
(
  account_id NUMBER,
  account_type_id NOT NULL,
  balance DECIMAL(12,2) CHECK(balance > -1),
  status_id NUMBER DEFAULT(1), 
  PRIMARY KEY(account_id),
  FOREIGN KEY(account_type_id) REFERENCES AccountType(account_type_id) ON DELETE CASCADE,
  FOREIGN KEY(status_id) REFERENCES Status(status_id) ON DELETE CASCADE    
);

/

CREATE TABLE CustomerAccounts
(
  cust_id NUMBER,
  acct_id NUMBER,
  PRIMARY KEY(cust_id, acct_id),
  FOREIGN KEY (cust_id) REFERENCES Users(user_id) ON DELETE CASCADE,
  FOREIGN KEY (acct_id) REFERENCES Accounts(account_id) ON DELETE CASCADE
);



---sequences---

CREATE SEQUENCE user_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
  
  CREATE SEQUENCE account_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
  /
 -- before insert on table of users select user sequence 
CREATE OR REPLACE TRIGGER user_trigger --name
BEFORE INSERT ON Users --on event
FOR EACH ROW --how often
BEGIN
  SELECT user_seq.NEXTVAL
  INTO :new.user_id
  FROM dual;
END;
/

----inserts into user and if it is a customer will add accounts checking and savings
CREATE OR REPLACE PROCEDURE insertUser(full_name in varchar2, use_name in varchar2, pass_word in varchar2, role_id in number)
IS
  id_num number;
  ac_num number;
BEGIN
     INSERT INTO USERS (FULL_NAME,pass,username,role_id) VALUES (full_name, pass_word, use_name, role_id);
     
     IF role_id  < 2 THEN -- if its a customer
      ---CREATE CHECKING ACC
        SELECT MAX(USER_ID) INTO id_num 
        FROM USERS;       
       
        INSERT INTO ACCOUNTS (ACCOUNT_TYPE_ID, BALANCE, STATUS_ID) VALUES (1,0,1);
        
        SELECT MAX(ACCOUNT_ID) INTO ac_num 
        FROM ACCOUNTS;
        INSERT INTO CUSTOMERACCOUNTS VALUES(id_num,ac_num);
      
                  
        
        --SAVINGS ACCOUNT CREATION
        INSERT INTO ACCOUNTS (ACCOUNT_TYPE_ID, BALANCE, STATUS_ID) VALUES (2,0,1);
        SELECT MAX(ACCOUNT_ID) INTO ac_num 
        FROM ACCOUNTS;          
        INSERT INTO CUSTOMERACCOUNTS VALUES(id_num,ac_num);
      END IF;
      
          
END;
-------------------------
call insertUser('Test','test', 'pass', 1);
select * from users;

--method for adding a checking account
CREATE OR REPLACE PROCEDURE addChecking(use_name in varchar)
IS
  id_num number;
BEGIN
  select user_id into id_num from USERS WHERE USERNAME = 'use_name';
  DBMS_OUTPUT.PUT_LINE(id_num);
  INSERT INTO ACCOUNTS(ACCOUNT_TYPE_ID, BALANCE, STATUS_ID) VALUES (1, 0, 1);
  UPDATE CUSTOMERACCOUNTS SET CUST_ID = id_num WHERE cust_id = 0;
end;



----------------------
CREATE OR REPLACE TRIGGER account_trigger --name
BEFORE INSERT ON ACCOUNTS --on event
FOR EACH ROW --how often
BEGIN
  SELECT account_seq.NEXTVAL
  INTO :new.account_id
  FROM dual;  
END;

/
select * from users;

-- look up tables set up
INSERT INTO ROLE(role_id, role) VALUES (1,'Customer');
INSERT INTO ROLE(role_id, role) VALUES (2,'Employee');
INSERT INTO ROLE(role_id, role) VALUES (3,'Admin');

INSERT INTO STATUS(status_id, status) VALUES (1,'None');
INSERT INTO STATUS(status_id, status) VALUES (2,'Applied');
INSERT INTO STATUS(status_id, status) VALUES (3,'Active');

SELECT * FROM ROLE;
INSERT INTO ACCOUNTTYPE(account_type_id, account_type) VALUES (1,'Checking');
INSERT INTO ACCOUNTTYPE(account_type_id, account_type) VALUES (2,'Savings');

-- inserting the admin into the table IMPORTANT TO MAKE IT ALL WORK
INSERT INTO USERS (FULL_NAME,pass,username,role_id) VALUES ('The BOSS', '123', 'Bo$$', 3);



-- testing
SELECT count(*) FROM ACCOUNTS;
SELECT count(*) FROM CUSTOMERACCOUNTS;
DELETE FROM USERS WHERE USERNAME = 'usernamee';
DELETE FROM ACCOUNTS WHERE ACCOUNT_ID = 2;
call insertUser('fullname', 'employee', 'apsd', 2);

INSERT INTO ACCOUNTS (ACCOUNT_TYPE_ID ,BALANCE ,STATUS_ID,OVERSEER) VALUES (1, 100, 1, 2);

SELECT * FROM ACCOUNTS;
INSERT INTO CUSTOMERACCOUNTS(CUST_ID, ACCT_ID) VALUES (8, 3);

SELECT * FROM CUSTOMERACCOUNTS;
DELETE FROM USERS WHERE username = 'usernamee';
--- end testing


SELECT * FROM USERS ;
commit;

-----------------GET THE CHECKING BALANCE GIVEN THE USERNAME
CREATE OR REPLACE FUNCTION getBalance(u_name in varchar2, ty in number)
is
begin
  SELECT BALANCE FROM ACCOUNTS, CUSTOMERACCOUNTS, USERS 
  WHERE USERS.USERNAME = u_name 
  AND USERS.USER_ID = CUSTOMERACCOUNTS.CUST_ID 
  AND ACCOUNTS.ACCOUNT_ID = CUSTOMERACCOUNTS.ACCT_ID 
  AND ACCOUNTS.ACCOUNT_TYPE_ID = ty; --SWITCH TO 2 TO GET SAVINGS BALANCE
end;
------------------UPDATE
UPDATE ACCOUNTS
SET BALANCE = 100
WHERE USERS.USERNAME = 'usernamee'
AND USERS.USER_ID = CUSTOMERACCOUNTS.CUST_ID
AND ACCOUNTS.ACCOUNT_ID = CUSTOMERACCOUNTS.ACCT_ID 
AND ACCOUNTS.ACCOUNT_TYPE_ID = 1;

--procedure for updating the balance of either a checking or savings account
CREATE OR REPLACE PROCEDURE updateBalance(typeAcc in number, userName in varchar2, new_num in number)
IS
BEGIN
  UPDATE ACCOUNTS
  SET BALANCE = new_num
  WHERE ACCOUNT_ID IN (
      SELECT CUSTOMERACCOUNTS.ACCT_ID FROM USERS, CUSTOMERACCOUNTS
      WHERE USERS.USERNAME = userName
      AND CUSTOMERACCOUNTS.CUST_ID = USERS.USER_ID 
     )    
  AND ACCOUNT_TYPE_ID = typeAcc;    
END;

----------------------

-- procedure for updating the status of an account 
-- used for applying and approving accounts
CREATE OR REPLACE PROCEDURE updateAccountStatus(u_name in varchar2, new_status in number, type_acc in number)
IS
BEGIN
  UPDATE ACCOUNTS
  SET ACCOUNTS.STATUS_ID = new_status
   WHERE ACCOUNT_ID IN (
      SELECT CUSTOMERACCOUNTS.ACCT_ID FROM USERS, CUSTOMERACCOUNTS
      WHERE USERS.USERNAME = userName
      AND CUSTOMERACCOUNTS.CUST_ID = USERS.USER_ID 
     )    
  AND ACCOUNT_TYPE_ID = type_acc;
END;

CALL UPDATEACCOUNTSTATUS('test acc', 3, 2
commit;
SELECT * FROM USERS;

SELECT STATUS_ID FROM ACCOUNTS, USERS, CUSTOMERACCOUNTS WHERE ACCOUNTS.ACCOUNT_ID = CUSTOMERACCOUNTS.ACCT_ID AND CUSTOMERACCOUNTS.CUST_ID = USERS.USER_ID AND USERNAME = 'test acc' AND ACCOUNTS.ACCOUNT_TYPE_ID = 2;


--updates the username
CREATE OR REPLACE PROCEDURE updateUName(old_uname in varchar2, new_uname in varchar2)
IS
BEGIN
    UPDATE USERS
    SET USERNAME = new_uname
    WHERE USERNAME = old_uname;
END;

-- updates the password
CREATE OR REPLACE PROCEDURE updatePass(uname in varchar2, new_pass in varchar2)
IS
BEGIN
    UPDATE USERS
    SET PASS = new_pass
    WHERE USERNAME = uname;
END;

--testing
SELECT * FROM USERS;
CALL updateUName('employee', 'employee UPDATED');
CALL updatePass('employee UPDATED', 'new pass');
----------------------



/
UPDATE USERS
SET USERNAME = 'YESSS'
WHERE USERNAME = 'usernamee';
Select * from users;
SELECT * FROM ACCOUNTS;
select * from customerAccounts;
commit;
call updateBalance(1, 'test acc', 100);

commit;

--testing geting the balance
--SELECT BALANCE FROM ACCOUNTS, CUSTOMERACCOUNTS, USERS 
  --WHERE USERS.USERNAME = 'test acc' 
 --AND USERS.USER_ID = CUSTOMERACCOUNTS.CUST_ID 
 -- AND ACCOUNTS.ACCOUNT_ID = CUSTOMERACCOUNTS.ACCT_ID 
 -- AND ACCOUNTS.ACCOUNT_TYPE_ID = 2;


DELETE FROM ACCOUNTS WHERE ACCOUNT_ID = 3;
TRUNCATE TABLE ACCOUNTS;

SELECT * FROM USERS;
SELECT * FROM ACCOUNTS;
SELECT * FROM CUSTOMERACCOUNTS;


--- clean up
commit;
DELETE  FROM ACCOUNTS WHERE ACCOUNT_ID > 0;
DELETE FROM USERS WHERE USER_ID > 2;