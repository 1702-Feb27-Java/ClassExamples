CREATE TABLE Role 
(
role_id NUMBER,
role VARCHAR2(20),
CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Users
(
user_id NUMBER,
first_name VARCHAR2(25) NOT NULL,
last_name VARCHAR2(25) NOT NULL,
username VARCHAR2(16) NOT NULL UNIQUE,
pw VARCHAR2(16) NOT NULL,
role_id NUMBER,
PRIMARY KEY(user_id),
FOREIGN KEY(role_id) REFERENCES Role(role_id)
);

CREATE TABLE Status 
(
status_id NUMBER,
status VARCHAR2(25),
CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

CREATE TABLE Type
(
type_id NUMBER,
type VARCHAR2(25),

CONSTRAINT t_id_pk PRIMARY KEY(type_id)
);

CREATE TABLE Accounts 
(
account_id NUMBER,
type_id NUMBER,
balance DECIMAL(12,2) CHECK (balance > 0),
status_id NUMBER DEFAULT 1,
resolver_id NUMBER,
CONSTRAINT a_id_pk PRIMARY KEY(account_id),
FOREIGN KEY(type_id) REFERENCES Type(type_id),
FOREIGN KEY(status_id) REFERENCES Status(status_id),
FOREIGN KEY(resolver_id)REFERENCES Users(user_id)
);

CREATE TABLE Customer_Accounts
(
user_id NUMBER,
account_id NUMBER,
PRIMARY KEY(user_id, account_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id),
FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);

CREATE SEQUENCE user_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER user_trigger -- name
    BEFORE INSERT ON Users --upon what event
    FOR EACH ROW -- how ofter
    BEGIN  --start what actually happens
        SELECT user_seq.NEXTVAL
        INTO :new.user_id
        FROM dual;
    END;
/ -- need to stop PL/SQL

CREATE SEQUENCE acct_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER acct_trigger -- name
    BEFORE INSERT ON Users --upon what event
    FOR EACH ROW -- how ofter
    BEGIN  --start what actually happens
        SELECT acct_seq.NEXTVAL
        INTO :new.account_id
        FROM dual;
    END;
/ -- need to stop PL/SQL
-- insersts into lookup table Role
INSERT INTO Role (role_id, role) VALUES (1, 'Admin');
INSERT INTO Role (role_id, role) VALUES (2, 'Employee');
INSERT INTO Role (role_id, role) VALUES (3, 'Customer');
INSERT INTO Role (role_id, role) VALUES (3, 'Customer');

-- insert two users
INSERT INTO Users (FIRST_NAME, LAST_NAME, USERNAME, PW, ROLE_ID) 
VALUES ('KEITH', 'MINNER','MINNCOMM', 'password', 1);
INSERT INTO Users VALUES ('','MICHAEL', 'SCOTT','SCOTSTOTS', 'password', 2);
-- inserts into lookup table Status
INSERT INTO Status (STATUS_ID, STATUS) VALUES ('1', 'Pending');
INSERT INTO Status (STATUS_ID, STATUS) VALUES ('2', 'Resolved');
INSERT INTO Status (STATUS_ID, STATUS) VALUES ('3', 'Denied');
-- insert into lookup table Type
INSERT INTO Type (TYPE_ID, TYPE) VALUES ('1','Checking');
INSERT INTO Type (TYPE_ID, TYPE) VALUES ('2','Savings');



