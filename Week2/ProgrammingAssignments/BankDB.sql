-------------------------------------------------------------------------------------------------------------------------------------
-- TABLES CREATION
-------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE Role 
(
roleid NUMBER,
role VARCHAR2(20),
CONSTRAINT pk_roleid PRIMARY KEY(roleid)
);

CREATE TABLE Users
(
userid NUMBER,
firstname VARCHAR2(25) NOT NULL,
lastname VARCHAR2(25) NOT NULL,
username VARCHAR2(16) NOT NULL UNIQUE,
pw VARCHAR2(16) NOT NULL,
roleid NUMBER,
PRIMARY KEY(userid),
FOREIGN KEY(roleid) REFERENCES Role(roleid) 
ON DELETE CASCADE
);

CREATE TABLE Status 
(
statusid NUMBER,
status VARCHAR2(25),
CONSTRAINT pk_statusid PRIMARY KEY(statusid)
);

CREATE TABLE Type
(
typeid NUMBER,
type VARCHAR2(25),
CONSTRAINT p_typeid PRIMARY KEY(typeid)
);

CREATE TABLE Accounts 
(
accountid NUMBER,
typeid NUMBER DEFAULT 1,
balance NUMBER(12,2) DEFAULT 0 CHECK (balance >= 0),
statusid NUMBER DEFAULT 1,
resolverid NUMBER,
CONSTRAINT pk_accountid PRIMARY KEY(accountid),
FOREIGN KEY(typeid) REFERENCES Type(typeid)
ON DELETE CASCADE,
FOREIGN KEY(statusid) REFERENCES Status(statusid)
ON DELETE CASCADE,
FOREIGN KEY(resolverid)REFERENCES Users(userid)
ON DELETE CASCADE
);

CREATE TABLE CustomerAccounts
(
userid NUMBER,
accountid NUMBER,
PRIMARY KEY(userid, accountid),
FOREIGN KEY(userid) REFERENCES Users(userid)
ON DELETE CASCADE,
FOREIGN KEY(accountid) REFERENCES Accounts(accountid)
ON DELETE CASCADE
);

CREATE TABLE USERSLOG(
userlogid NUMBER,
operation VARCHAR2(10),
time_stamp DATE DEFAULT SYSTIMESTAMP,
OldFirstName VARCHAR2(25),
NewFirstName VARCHAR2(25),
OldLastName VARCHAR2(25),
NewLastName VARCHAR2(25),
OldPassword VARCHAR2(25),
NewPassword VARCHAR2(25),
CONSTRAINT pk_userlogid PRIMARY KEY(userlogid)
);
/
CREATE TABLE ACCOUNTSLOG(
accountlogid NUMBER,
operation VARCHAR2(10),
time_stamp DATE DEFAULT SYSTIMESTAMP,
OldTypeID VARCHAR2(25),
NewTypeID VARCHAR2(25),
OldBalance VARCHAR2(25),
NewBalance VARCHAR2(25),
OldStatusID VARCHAR2(25),
NewStatusID VARCHAR2(25),
OldResolverID VARCHAR2(25),
NewResolverID VARCHAR2(25),
CONSTRAINT pk_accountlogid PRIMARY KEY(accountlogid)
);
------------------------------------------------------------------------------------------------------------------------------------
-- LOOKUP TABLES - INSERTS
-------------------------------------------------------------------------------------------------------------------------------------
-- ROLE
INSERT INTO Role (roleid, role) VALUES (1, 'Admin');
INSERT INTO Role (roleid, role) VALUES (2, 'Employee');
INSERT INTO Role (roleid, role) VALUES (3, 'Customer');
-- STATUS
INSERT INTO Status (STATUSID, STATUS) VALUES (1, 'Pending');
INSERT INTO Status (STATUSID, STATUS) VALUES (2, 'Resolved');
INSERT INTO Status (STATUSID, STATUS) VALUES (3, 'Denied');
-- TYPE
INSERT INTO Type (TYPEID, TYPE) VALUES (0,'None');
INSERT INTO Type (TYPEID, TYPE) VALUES (2,'Checking');
INSERT INTO Type (TYPEID, TYPE) VALUES (3,'Savings');
-------------------------------------------------------------------------------------------------------------------------------------
-- SEQUENCES AND TRIGGERS
-------------------------------------------------------------------------------------------------------------------------------------
-- USERS TABLE PRIMARY KEY AUTO INCREMENT
CREATE SEQUENCE user_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
/ -- TRIGGER FOR USERS TABLE
CREATE OR REPLACE TRIGGER user_trigger 
    BEFORE INSERT ON USERS
    FOR EACH ROW 
    BEGIN  
        SELECT user_seq.NEXTVAL
        INTO :new.userid
        FROM dual;
    END;
/ -- ACCOUNTS TABLE PRIMARY KEY AUTO INCREMENT
CREATE SEQUENCE acct_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
/ -- TRIGGER FOR ACCOUNTS TABLE
CREATE OR REPLACE TRIGGER acct_trigger 
    BEFORE INSERT ON ACCOUNTS 
    FOR EACH ROW 
    BEGIN  
        SELECT acct_seq.NEXTVAL
        INTO :new.acctid
        FROM dual;
    END;
/ -- USERSLOG TABLE PRIMARY KEY AUTO INCREMENT
CREATE SEQUENCE userslog_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
/ -- TRIGGER FOR USERSLOG TABLE
CREATE OR REPLACE TRIGGER userslog_trigger 
    BEFORE INSERT ON USERSLOG
    FOR EACH ROW 
    BEGIN  
        SELECT userslog_seq.NEXTVAL
        INTO :new.userlogid
        FROM dual;
    END;
/ -- ACCOUNTSLOG TABLE PRIMARY KEY AUTO INCREMENT
CREATE SEQUENCE accountslog_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
/ -- TRIGGER FOR ACCOUNTSLOG TABLE 
CREATE OR REPLACE TRIGGER accountslog_trigger 
    BEFORE INSERT ON ACCOUNTSLOG
    FOR EACH ROW 
    BEGIN  
        SELECT accountslog_seq.NEXTVAL
        INTO :new.accountlogid
        FROM dual;
    END;
/ -- USERSLOG TRANSACTIONS
 CREATE OR REPLACE TRIGGER users_log_trigger   
 AFTER INSERT OR DELETE OR UPDATE ON USERS
 FOR EACH ROW
 BEGIN
    IF INSERTING THEN
        INSERT INTO USERSLOG (userlogid, operation, NewFirstName, NewLastName, NewPassword)
        VALUES(NULL,'INSERT', :NEW.FIRSTNAME, :NEW.LASTNAME, :NEW.PW);
    ELSIF DELETING THEN
        INSERT INTO USERSLOG(userlogid, operation, oldFirstName, OldLastName, OldPassword)
        VALUES (NULL, 'DELETE', :OLD.FIRSTNAME, :OLD.LASTNAME, :OLD.PW);
    ELSIF UPDATING THEN
        INSERT INTO USERSLOG (userlogid, operation, oldFirstName, OldLastName, OldPassword, 
            NewFirstName, NewLastName, NewPassword) VALUES (NULL, 'UPDATE', :OLD.FIRSTNAME, :OLD.LASTNAME,
            :OLD.PW, :NEW.FIRSTNAME, :NEW.LASTNAME, :NEW.PW);
    END IF;        
 END;
 / -- ACCOUNTS LOG TRANSACTIONS
 CREATE OR REPLACE TRIGGER accounts_log_trigger   
 AFTER INSERT OR DELETE OR UPDATE ON ACCOUNTS
 FOR EACH ROW
 BEGIN
    IF INSERTING THEN
        INSERT INTO ACCOUNTSLOG (accountlogid, operation, NewTypeID, NewBalance, NewStatusID, NewResolverID)
        VALUES(NULL,'INSERT', :NEW.TYPEID, :NEW.BALANCE, :NEW.STATUSID, :NEW.RESOLVERID);
    ELSIF DELETING THEN
        INSERT INTO ACCOUNTSLOG(accountlogid, operation, OldTypeID, OldBalance, OldStatusID, OldResolverID)
        VALUES (NULL, 'DELETE', :OLD.TYPEID, :OLD.BALANCE, :OLD.STATUSID, :OLD.RESOLVERID);
    ELSIF UPDATING THEN
        INSERT INTO ACCOUNTSLOG (accountlogid, operation, OldTypeID, OldBalance, OldStatusID, OldResolverID,
            NewTypeID, NewBalance, NewStatusID, NewResolverID) VALUES (NULL, 'UPDATE', :OLD.TYPEID, :OLD.BALANCE,
            :OLD.STATUSID, :OLD.RESOLVERID, :NEW.TYPEID, :NEW.BALANCE, :NEW.STATUSID, :NEW.RESOLVERID);
    END IF;        
 END;
 /
 -------------------------------------------------------------------------------------------------------------------------------------
-- PROCEDURES
-------------------------------------------------------------------------------------------------------------------------------------
-- INSERT NEW PERSON
CREATE OR REPLACE PROCEDURE insert_new_person(fname IN VARCHAR2, lname IN VARCHAR2, uname IN VARCHAR2, pw IN VARCHAR2, role IN NUMBER)
IS
    newid NUMBER(10);
    new_acctid NUMBER(10);
BEGIN
    INSERT INTO USERS (FIRSTNAME, LASTNAME, USERNAME, PW, ROLEID) VALUES (fname, lname, uname, pw, role);
    COMMIT;   
    SELECT USERID INTO newid FROM USERS WHERE USERNAME = uname;
    INSERT INTO ACCOUNTS(RESOLVERID) VALUES(1);
    COMMIT;
    SELECT ACCTID INTO new_acctid FROM ACCOUNTS WHERE ACCTID=(SELECT MAX(ACCTID) FROM ACCOUNTS);
    INSERT INTO CUSTOMERACCOUNTS (USERID, ACCTID) VALUES (newid, new_acctid);
END;
/
-- VALIDATION OF USERNAME AND PASSWORD
CREATE OR REPLACE PROCEDURE update_personal_info (name1 IN VARCHAR2, user IN NUMBER, num IN NUMBER)
IS
BEGIN
    IF num = 1 THEN
        UPDATE USERS SET FIRSTNAME=name1 WHERE USERID = user;
    ELSIF num = 2 THEN
        UPDATE USERS SET LASTNAME=name1 WHERE USERID = user;
    ELSE
        UPDATE USERS SET PW=name1 WHERE USERID = user;
    END IF;
END;
-- ACCOUNT TRANSACTION
CREATE OR REPLACE PROCEDURE account_transaction(uid IN NUMBER,atype IN NUMBER, amount IN NUMBER)
IS
BEGIN
    UPDATE ACCOUNTS
    SET BALANCE = amount
    WHERE ACCTID = (SELECT a.ACCTID FROM USERS u
        INNER JOIN CUSTOMERACCOUNTS ac ON u.USERID=ac.USERID
        INNER JOIN ACCOUNTS a ON ac.ACCTID=a.ACCTID 
        WHERE u.USERID=uid AND a.TYPEID=atype);
        COMMIT;
    END;
-- GET ALL RECORDS FOR EMPLOYEE USE
CREATE OR REPLACE PROCEDURE get_all_records(type IN NUMBER)
IS
    results VARCHAR2(100);
BEGIN
    SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, 
    a.STATUSID, a.TYPEID, a.BALANCE FROM USERS u
    INNER JOIN CUSTOMERACCOUNTS ac ON u.USERID=ac.USERID
    INNER JOIN ACCOUNTS a ON ac.ACCTID=a.ACCTID 
    WHERE a.TYPEID=type;
END; 
-- UPDATES STATUS ID FOR PENDING CUSTOMERS/EMPLOYEES TO APPROVED OR DENIED
CREATE OR REPLACE PROCEDURE update_status(uid IN NUMBER, status IN NUMBER, resolver IN NUMBER)
IS 
BEGIN
    UPDATE ACCOUNTS
    SET STATUSID = status, RESOLVERID = resolver
    WHERE ACCTID = (SELECT a.ACCTID FROM USERS u
        INNER JOIN CUSTOMERACCOUNTS ac ON u.USERID=ac.USERID
        INNER JOIN ACCOUNTS a ON ac.ACCTID=a.ACCTID 
        WHERE u.USERID=uid);
        COMMIT;
    END;
/
-- UPDATES SAVNIGS OR CHECKING ACCOUNT
CREATE OR REPLACE PROCEDURE make_account (type IN NUMBER, uid IN NUMBER)
IS
BEGIN
    UPDATE ACCOUNTS a
    SET TYPEID = type
    WHERE ACCTID = (SELECT a.ACCTID FROM USERS u
    INNER JOIN CUSTOMERACCOUNTS ac ON u.USERID=ac.USERID
    INNER JOIN ACCOUNTS a ON ac.ACCTID=a.ACCTID 
    WHERE u.USERID=uid);
    COMMIT;
END;
-- CREATING AN ACCOUNT CHECKING OR SAVINGS
CREATE OR REPLACE PROCEDURE make_account(type IN NUMBER, uid IN NUMBER)
IS
    newid NUMBER(10);
BEGIN
    INSERT INTO ACCOUNTS (TYPEID) VALUES (type);
    COMMIT;   
    SELECT MAX(ACCTID) INTO newid FROM ACCOUNTS;
    INSERT INTO CUSTOMERACCOUNTS (USERID, ACCTID) VALUES (uid, newid);
END;
