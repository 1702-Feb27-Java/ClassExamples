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
  BEFORE INSERT ON Account
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
/*=================================================================*/

INSERT INTO USERS(FIRST_NAME, LAST_NAME, username, pw, role_id) VALUES
('Marco', 'Tobon', 'mt', '123', 1);

/*==================================================================
                    POPULATE THE LOOKUP TABLES
==================================================================*/
--POPULATE ROLE TYPE LOOKUP TABLE
INSERT INTO ROLE (ROLE_ID, role) VALUES (1, 'Admin');
INSERT INTO ROLE (ROLE_ID, role) VALUES (2, 'Employee');
INSERT INTO ROLE (ROLE_ID, role) VALUES (3, 'Customer');

--POPULATE ACCOUNT TYPE LOOKUP TABLE
INSERT INTO ACCOUNT_TYPE (type_id, ac_type) VALUES (1, 'Savings');
INSERT INTO ACCOUNT_TYPE (type_id, ac_type) VALUES (2, 'Checking');
INSERT INTO ACCOUNT_TYPE (type_id, ac_type) VALUES (3, 'Joint');

--POPULATE STATUS TYPE LOOKUP TABLE
INSERT INTO STATUS (status_id, status) VALUES (1, 'Pending');
INSERT INTO STATUS (status_id, status) VALUES (1, 'Declined');
INSERT INTO STATUS (status_id, status) VALUES (1, 'Approved');
