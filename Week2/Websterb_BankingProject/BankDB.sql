CREATE TABLE Role
(
  role_id NUMBER,
  role VARCHAR2(25),
  
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Users 
(
  user_id NUMBER,
  first_name VARCHAR2(25) NOT NULL,
  last_name VARCHAR2(25) NOT NULL,
  username VARCHAR2(25) NOT NULL UNIQUE,
  pass VARCHAR2(25) NOT NULL,
  role_id NUMBER,
  
  CONSTRAINT u_id_pk PRIMARY KEY(user_id),
  CONSTRAINT r_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id)
);

CREATE TABLE LogTable
(
	US_IDd varchar2(20) NOT NULL,
	LOGGERr  varchar2(50) NOT NULL,
	levell   varchar2(10) NOT NULL,
	MESSAGEe varchar2(1000) NOT NULL
);

CREATE TABLE Status
(
  status_id NUMBER,
  status VARCHAR2(25),
  
  CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

CREATE TABLE AccountType
(
  type_id NUMBER,
  type VARCHAR2(25),
  
  CONSTRAINT t_id_pk PRIMARY KEY(type_id)
);

CREATE TABLE Account 
(
  account_id NUMBER,
  type_id NUMBER NOT NULL,
  balance DECIMAL(12,2),
  status_id NUMBER DEFAULT(1),
  resolver_id NUMBER,
  
  CHECK(balance > 0),
  CONSTRAINT acta_id_pk PRIMARY KEY(account_id),
  CONSTRAINT actt_id_fk FOREIGN KEY(type_id) REFERENCES AccountType(type_id),
  CONSTRAINT acts_id_fk FOREIGN KEY(status_id) REFERENCES Status(status_id),
  CONSTRAINT actr_id_fk FOREIGN KEY(resolver_id) REFERENCES Users(user_id)
); 

CREATE TABLE CustomerAccounts
(
  user_id NUMBER,
  account_id NUMBER,
  
  CONSTRAINT custactu_id_pk PRIMARY KEY(user_id, account_id),
  CONSTRAINT custactu_id_fk FOREIGN KEY(user_id) REFERENCES Users(user_id),
  CONSTRAINT custacta_id_fk FOREIGN KEY(account_id) REFERENCES Account(account_id)
);

-------sequences----------
--drop sequence user_seq;
--drop sequence account_seq;
CREATE SEQUENCE user_seq
  MINVALUE 1 
  START WITH 1
  INCREMENT BY 1;
/
CREATE SEQUENCE account_seq
  MINVALUE 1 
  START WITH 1
  INCREMENT BY 1;
/
-------triggers----------
CREATE OR REPLACE TRIGGER user_trigger --name of the sequence
  BEFORE INSERT ON  Users -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT user_seq.NEXTVAL
      INTO :new.user_id
      FROM dual;
    END;
/   
CREATE OR REPLACE TRIGGER account_trigger --name of the sequence
  BEFORE INSERT ON  Account-- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT account_seq.NEXTVAL
      INTO :new.account_id
      FROM dual;
    END;
/
--populate lookup tables--
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (1, 'Admin');
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (2, 'Employee');
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (3, 'Customer');

INSERT INTO Status(Status_id, status)
VALUES (1, 'Pending');
INSERT INTO Status(Status_id, status)
VALUES (2, 'Approved');
INSERT INTO Status(Status_id, status)
VALUES (3, 'Denied');

INSERT INTO AccountType(type_id, type)
VALUES (1, 'Savings');
INSERT INTO AccountType(type_id, type)
VALUES (2, 'Checking');
/
CREATE OR REPLACE PROCEDURE insertCustomer(fn IN varchar2, ln IN varchar2, un IN varchar2, pw IN varchar2)
IS
BEGIN
  INSERT INTO Users VALUES(1, fn, ln, un, pw, 3); 
  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE applyForAccount(userID IN number, actId OUT number, tID IN number, bal IN number)
IS
  val number(10);
BEGIN
  INSERT INTO ACCOUNT(ACCOUNT_ID, TYPE_ID, BALANCE) VALUES(actId, tID, bal);
  SELECT ACCOUNT_ID INTO VAL FROM ACCOUNT WHERE ROWNUM = 1 ORDER BY ACCOUNT_ID DESC;
  INSERT INTO CUSTOMERACCOUNTS(USER_ID, ACCOUNT_ID) VALUES(userId, val);
  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE loginCustomer(useId OUT number, un IN varchar2, pw OUT varchar2)
IS
  userid number(10);
  pas varchar2(20);
BEGIN
  SELECT USER_ID, PASS into userid, pas
  FROM USERS 
  WHERE USERNAME = un
  AND ROLE_ID = 3;
  useId := userid;
  pw := pas;
END loginCustomer;
/
CREATE OR REPLACE PROCEDURE getBalance(actId IN number, balance OUT number)
IS
  bal number(12,2);
BEGIN
  SELECT balance INTO bal
  FROM ACCOUNT 
  WHERE ACCOUNT_ID  = actId;
  balance := bal;
  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE setBalance(actId IN number, bal IN number)
IS
BEGIN
  UPDATE ACCOUNT 
  SET BALANCE = bal
  WHERE ACCOUNT_ID = actId;
  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE insertEmployee(fn IN varchar2, ln IN varchar2, un IN varchar2, pw IN varchar2)
IS
BEGIN
  INSERT INTO Users VALUES(1, fn, ln, un, pw, 2); 
  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE loginEmployee(useId OUT number, un IN varchar2, pw OUT varchar2)
IS
  userid number(10);
  pas varchar2(20);
BEGIN
  SELECT USER_ID, PASS into userid, pas
  FROM USERS 
  WHERE USERNAME = un
  AND ROLE_ID = 2;
  useId := userid;
  pw := pas;
END loginEmployee;
/
CREATE OR REPLACE PROCEDURE loginCustomerNoPass(useId OUT number, un IN varchar2)
IS 
userid number(10);
BEGIN
  SELECT USER_ID into userid
  FROM USERS
  WHERE USERNAME = un;
  useId := userid;
END;
/
CREATE OR REPLACE PROCEDURE loginAdmin( un IN varchar2, pw OUT varchar2)
IS 
  passwrd varchar2(20);
BEGIN
  SELECT pass into passwrd
  FROM USERS
  WHERE USERNAME = un
  and ROLE_ID = 1;
  pw := passwrd;
END;
/
CREATE OR REPLACE PROCEDURE setStatus(actId IN number, statusId IN number)
IS
BEGIN
  UPDATE ACCOUNT 
  SET STATUS_ID = statusId
  WHERE ACCOUNT_ID = actId;
END;
/
CREATE OR REPLACE PROCEDURE setResolverId(empId IN number, actId IN number)
IS
BEGIN
  UPDATE ACCOUNT
  SET RESOLVER_ID = empId
  WHERE ACCOUNT_ID = actId;
END;
/
--drop table customeraccounts;

--drop table account;

--drop table users;

--drop sequence account_seq;

--insert into Users(user_id, first_name, last_name, username, pass, role_id)
--values(1, 'admin', 'admin', 'admin', 'admin', 1);

CREATE TABLE LogsUsers (
  log_id NUMBER,
  log_time TIMESTAMP,
  log_operation VARCHAR2(60),
  
  -- for logging changes in the Users table
  oldFirst_Name VARCHAR2(25),
  newFirst_Name VARCHAR2(25),
  oldLast_Name VARCHAR2(25),
  newLast_Name VARCHAR2(25),
  oldUsername VARCHAR2(25),
  newUsername VARCHAR2(25),
  oldpass VARCHAR2(25),
  newpass VARCHAR2(25),
  
  CONSTRAINT PK_LOGSUSERS PRIMARY KEY(log_id)
);
/
CREATE TABLE LogsAccount (
  log_id NUMBER,
  log_time TIMESTAMP,
  log_operation VARCHAR2(60),
  
  -- for logging changes in the Users table
  oldaccount_id number(10),
  newaccount_id number(10),
  oldtype_id number(10),
  newtype_id number(10),
  oldbalance number(10, 2),
  newbalance number(10, 2),
  oldstatus_id number(10),
  newstatus_id number(10),
  
  CONSTRAINT PK_LOGSACCOUNT PRIMARY KEY(log_id)
);
/
CREATE SEQUENCE user_log_seq
  MINVALUE 1 
  START WITH 1
  INCREMENT BY 1;
/
CREATE SEQUENCE account_log_seq
  MINVALUE 1 
  START WITH 1
  INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER user_log_trigger --name of the sequence
  BEFORE INSERT ON  LogsUsers -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT user_log_seq.NEXTVAL
      INTO :new.log_id
      FROM dual;
    END;
/
CREATE OR REPLACE TRIGGER account_log_trigger --name of the sequence
  BEFORE INSERT ON  LogsAccount -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT account_log_seq.NEXTVAL
      INTO :new.log_id
      FROM dual;
    END;
/
 CREATE OR REPLACE TRIGGER myUserTableAuditTrigger
    AFTER INSERT OR DELETE OR UPDATE ON Users
    FOR EACH ROW
    BEGIN
      IF INSERTING THEN
      INSERT INTO LogsUsers (log_time, log_operation, newFirst_Name, newLast_Name, newUsername, newPass) 
      VALUES (CURRENT_TIMESTAMP, 'Insert', :NEW.first_name, :NEW.last_name, :NEW.Username, :NEW.Pass);
     ELSIF DELETING THEN
        INSERT INTO LogsUsers (log_time, log_operation, newFirst_Name, newLast_Name, newUsername, newPass) 
      VALUES (CURRENT_TIMESTAMP, 'Delete', :NEW.first_name, :NEW.last_name, :NEW.Username, :NEW.Pass);
     ELSIF UPDATING THEN
       INSERT INTO LogsUsers (log_time, log_operation, newFirst_Name, newLast_Name, newUsername, newPass) 
      VALUES (CURRENT_TIMESTAMP, 'Update', :NEW.first_name, :NEW.last_name, :NEW.Username, :NEW.Pass);
     END IF;
   END;
/
 CREATE OR REPLACE TRIGGER myAccountTableAuditTrigger
    AFTER INSERT OR DELETE OR UPDATE ON Account
    FOR EACH ROW
    BEGIN
      IF INSERTING THEN
      INSERT INTO LogsAccount (log_time, log_operation, newaccount_id, newtype_id, newbalance, newstatus_id) 
      VALUES (CURRENT_TIMESTAMP, 'Insert', :NEW.account_id, :NEW.type_id, :NEW.balance, :NEW.status_id);
     ELSIF DELETING THEN
      INSERT INTO LogsAccount (log_time, log_operation, newaccount_id, newtype_id, newbalance, newstatus_id) 
      VALUES (CURRENT_TIMESTAMP, 'Delete', :NEW.account_id, :NEW.type_id, :NEW.balance, :NEW.status_id);
     ELSIF UPDATING THEN
      INSERT INTO LogsAccount (log_time, log_operation, newaccount_id, newtype_id, newbalance, newstatus_id) 
      VALUES (CURRENT_TIMESTAMP, 'Update', :NEW.account_id, :NEW.type_id, :NEW.balance, :NEW.status_id);
     END IF;
   END;
/