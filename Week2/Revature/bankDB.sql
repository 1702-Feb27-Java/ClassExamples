/*==================================================================
                   MAKING LOOKUP TABLES 
==================================================================*/
CREATE TABLE Role
(
    role_id NUMBER,
    role VARCHAR2(20),
  
    CONSTRAINT r_id_k PRIMARY KEY(role_id)
);

CREATE TABLE Account_Type
(
    type_id NUMBER,
    ac_type VARCHAR(25),

    PRIMARY KEY(type_id)
);

CREATE TABLE Status
(
    status_id number,
    status varchar(20),

    PRIMARY KEY(status_id)
);
/
CREATE TABLE logtype
(
    logtype_id number,
    type varchar(20),

    PRIMARY KEY(logtype_id)
);
/
/*==================================================================
    
/*==================================================================
                   MAKING REGULAR TABLES 
==================================================================*/
CREATE TABLE Users
(
    user_id NUMBER,
    first_name VARCHAR2(25) NOT NULL,
    last_name VARCHAR2(25),
    username VARCHAR2(16) NOT NULL UNIQUE,
    pw VARCHAR(16) NOT NULL,
    role_id NUMBER,
  
    PRIMARY KEY(user_id),
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
);
/
CREATE TABLE Accounts
(
    account_id NUMBER,
    type_id NUMBER NOT NULL,
    balance DECIMAL(12,2) CHECK (balance > 0),
    status_id NUMBER DEFAULT 1,
    resolver NUMBER,

    PRIMARY KEY(account_id),
    FOREIGN KEY(type_id) REFERENCES Account_Type(type_id),
    FOREIGN KEY(status_id) REFERENCES Status(status_id),
    FOREIGN KEY(resolver) REFERENCES Users(user_id)
);
/
CREATE TABLE LOGS
(
  log_id NUMBER,
  logtype_id NUMBER,
  msg VARCHAR2(1000),
  
  FOREIGN KEY(logtype_id) REFERENCES logtype
);
/
/*==================================================================

/*==================================================================
                   MAKING JOINT TABLES 
==================================================================*/
-- MAKING JOIN ACCOUNT TABLE BETWEEN ACCOUNTS AND USERS
-- N:N REALTION SHIP REQUIRES JOINT ACCONT
CREATE TABLE Customer_Accounts
(
    user_id NUMBER,
    account_id NUMBER,
    
    PRIMARY KEY(user_id, account_id),
    FOREIGN KEY(user_id) REFERENCES Users(user_id),
    FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);
/*==================================================================

/*==================================================================
                   SEQUENCES FOR ACCOUNT TABLE AND USERS 
==================================================================*/
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
 CREATE SEQUENCE log_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
 / 
/*=================================================================*/

/*==================================================================
                   TRIGGERS FOR ACCOUNT TABLE AND USERS 
==================================================================*/
CREATE OR REPLACE TRIGGER user_trigger
--WHAT EVEN SHOULD THIS TRIGGER HAPPEN
  BEFORE INSERT ON Users
  --HOW OFTER
  FOR EACH ROW
  --START WHAT ACTUALLY HAPPENS
  BEGIN
  --SELECT USER SE
    SELECT user_seq.NEXTVAL
  --NEW USER ID
    INTO :new.user_id
  --DUMMY TABLE
    FROM dual;
  END;
  /

CREATE OR REPLACE TRIGGER account_trigger
--WHAT EVEN SHOULD THIS TRIGGER HAPPEN
  BEFORE INSERT ON AccountS
  --HOW OFTER
  FOR EACH ROW
  --START WHAT ACTUALLY HAPPENS
  BEGIN
  --SELECT USER SE
    SELECT account_seq.NEXTVAL
  --NEW USER ID
    INTO :new.account_id
  --DUMMY TABLE
    FROM dual;
  END;
  /
  
CREATE OR REPLACE TRIGGER log_trigger
--WHAT EVEN SHOULD THIS TRIGGER HAPPEN
  BEFORE INSERT ON logs
  --HOW OFTER
  FOR EACH ROW
  --START WHAT ACTUALLY HAPPENS
  BEGIN
  --SELECT USER SE
    SELECT log_seq.NEXTVAL
  --NEW USER ID
    INTO :new.log_id
  --DUMMY TABLE
    FROM dual;
  END;
  /
/*=================================================================*/

INSERT INTO USERS(FIRST_NAME, LAST_NAME, username, pw, role_id) VALUES
('Marco', 'Tobon', 'mt', '123', 1);
INSERT INTO USERS(FIRST_NAME, LAST_NAME, username, pw, role_id) VALUES
('Marco', 'Tobon', 'jaun', 'oneonone', 2);
commit;

/*==================================================================
                    POPULATE THE LOOKUP TABLES
==================================================================*/
--POPULATE ROLE TYPE LOOKUP TABLE
INSERT INTO ROLE (ROLE_ID, role) VALUES (1, 'Admin');
INSERT INTO ROLE (ROLE_ID, role) VALUES (2, 'Employee');
INSERT INTO ROLE (ROLE_ID, role) VALUES (3, 'Customer');
/
--POPULATE ACCOUNT TYPE LOOKUP TABLE
INSERT INTO ACCOUNT_TYPE (type_id, ac_type) VALUES (1, 'Savings');
INSERT INTO ACCOUNT_TYPE (type_id, ac_type) VALUES (2, 'Checking');
INSERT INTO ACCOUNT_TYPE (type_id, ac_type) VALUES (3, 'Joint');
/
--POPULATE STATUS TYPE LOOKUP TABLE
INSERT INTO STATUS (status_id, status) VALUES (1, 'Pending');
INSERT INTO STATUS (status_id, status) VALUES (2, 'Declined');
INSERT INTO STATUS (status_id, status) VALUES (3, 'Approved');
/
--POPULATE STATUS TYPE LOOKUP TABLE
INSERT INTO LOGTYPE (LOGTYPE_ID, TYPE) VALUES (1, 'Error');
INSERT INTO LOGTYPE (LOGTYPE_ID, TYPE) VALUES (2, 'Warning');
INSERT INTO LOGTYPE (LOGTYPE_ID, TYPE) VALUES (3, 'Info');
/
SELECT account_seq.CURRVAL FROM DUAL;
SELECT USER_seq.CURRVAL FROM DUAL;
/

--=============================================================================
--===PROCEDURE MY JAVA PROGRAM WILL CALL
--===========================================================================
CREATE OR REPLACE PROCEDURE add_account
IS
  nextVal NUMBER(10);
  currVal NUMBER(10);
  BEGIN
    INSERT INTO ACCOUNTS (TYPE_id, BALANCE, STATUS_ID) 
    VALUES (1, 0.01, 1);
    SELECT account_seq.currval into nextVal FROM DUAL;
    SELECT user_seq.CURRVAL into currVal FROM DUAL;

    INSERT INTO CUSTOMER_ACCOUNTS VALUES (currval, nextVal);
  END;
/
--======================================================================
--PROCEDURE TO ADD ACCOUNTS, AND CREATE CUSTOMER ACCOUNTS AS WELL
CREATE OR REPLACE PROCEDURE cus_acc_proc (UID IN NUMBER, TYPE IN NUMBER)
IS
  nextVal NUMBER(10);
  BEGIN
    INSERT INTO ACCOUNTS (TYPE_id, BALANCE, STATUS_ID) 
    VALUES (type, 0.01, 1);
    SELECT account_seq.currval into nextVal FROM DUAL;
    INSERT INTO CUSTOMER_ACCOUNTS VALUES (uid, nextVal);
    END;
/
INSERT INTO USERS(FIRST_NAME, USERNAME, PW) VALUES ('ABC', 'ABC', 'ABC');
CALL ADD_ACCOUNT();
/
--====================== PENDING ACCOUNTS
  SELECT E2.*, a.account_id, a.balance, at.AC_TYPE, s.STATUS  FROM USERS E2 
    INNER JOIN customer_accounts c ON
      c.user_id = E2.USER_ID
    INNER JOIN accounts a ON
      a.ACCOUNT_ID = c.ACCOUNT_ID
    INNER JOIN account_type at ON
      at.TYPE_ID = a.TYPE_ID
    INNER JOIN status s ON
      a.status_id = s.status_id
    WHERE a.status_id = 1;
/
--====================== ACCOUNTS OF USERS THAT IS APPROVED
SELECT a.account_id, a.balance, at.AC_TYPE, s.status, u2.username
          FROM ACCOUNTS a
          INNER JOIN  account_type at ON
            at.type_id = a.type_id
          INNER JOIN status s ON
            s.status_id = a.status_id
          INNER JOIN customer_accounts cs ON
            cs.account_id = a.account_id 
          INNER JOIN users u2 ON
            u2.user_id = cs.user_id
          WHERE u2.username = 'john'
          AND s.status = 'Approved';
/
--==================== ACCOTNS AND PENDING 
SELECT a.account_id, a.balance, at.AC_TYPE, s.status, a.resolver
							 FROM ACCOUNTS a
							 INNER JOIN  account_type at ON
							 at.type_id = a.type_id
							 INNER JOIN status s ON
							 s.status_id = a.status_id
               INNER JOIN customer_accounts cs ON
							 cs.account_id = a.account_id
							 INNER JOIN users u2 ON
							 u2.user_id = cs.user_id
							 WHERE u2.username = 'janee';
/
select u.username from users u 