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
--------------------------------------------------------------------------------
-- LOOKUP TABLES - INSERTS
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
-------------------------------------------------------------------------------
CREATE SEQUENCE user_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER user_trigger 
    BEFORE INSERT ON USERS
    FOR EACH ROW 
    BEGIN  
        SELECT user_seq.NEXTVAL
        INTO :new.userid
        FROM dual;
    END;
/
CREATE SEQUENCE acctseq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER accttrigger 
    BEFORE INSERT ON ACCOUNTS 
    FOR EACH ROW 
    BEGIN  
        SELECT acctseq.NEXTVAL
        INTO :new.acctid
        FROM dual;
    END;
/
-- PROCEDURES
CREATE OR REPLACE PROCEDURE insert_new_person(fname IN VARCHAR2, lname IN VARCHAR2, uname IN VARCHAR2, pw IN VARCHAR2)
IS
    newid NUMBER(10);
    new_acctid NUMBER(10);
BEGIN
    INSERT INTO USERS (FIRSTNAME, LASTNAME, USERNAME, PW) VALUES (fname, lname, uname, pw);
    COMMIT;   
    SELECT USERID INTO newid FROM USERS WHERE USERNAME = uname;
    INSERT INTO ACCOUNTS(RESOLVERID) VALUES(1);
    COMMIT;
    SELECT ACCTID INTO new_acctid FROM ACCOUNTS WHERE ACCTID=(SELECT MAX(ACCTID) FROM ACCOUNTS);
    INSERT INTO CUSTOMERACCOUNTS (USERID, ACCTID) VALUES (newid, new_acctid);
END;
/
truncate table users
truncate table accounts
truncate table customeraccounts
call insert_new_person('Keith','Minner', 'minncomm','m1ssw0rd');














