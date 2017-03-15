DROP user bankdb cascade;

/******************************************************************************
CREATE A DATABASE
******************************************************************************/

CREATE USER bankdb
IDENTIFIED BY p4ssw0rd;

GRANT connect to bankdb;
GRANT resource to bankdb;

/******************************************************************************
CREATE TABLES
******************************************************************************/

CREATE TABLE Role(
 role_id NUMBER,
 role VARCHAR2(20),

 CONSTRAINT PK_ROLE PRIMARY KEY (role_id)
);

CREATE TABLE Users(
 user_id NUMBER,
 firstname VARCHAR2(25) NOT NULL,
 lastname VARCHAR(25) NOT NULL,
 uname VARCHAR(25) NOT NULL UNIQUE,
 pw VARCHAR(16) NOT NULL,
 role_id NUMBER,
 
 CONSTRAINT PK_USERS PRIMARY KEY(user_id)
);

CREATE TABLE Status(
 status_id NUMBER,
 status varchar2(15),
 
 CONSTRAINT PK_STATUS PRIMARY KEY(status_id)
);

CREATE TABLE AccountType(
 type_id NUMBER,
 type VARCHAR2(15),
 
 CONSTRAINT PK_ACCOUNTTYPE PRIMARY KEY(type_id)
);


CREATE TABLE Accounts(
 account_id NUMBER,
 type_id NUMBER NOT NULL,
 balance DECIMAL(12,2) CHECK(balance >= 0),
 status_id NUMBER DEFAULT 1,
 resolver_id NUMBER,
 
 CONSTRAINT PK_ACCOUNTS PRIMARY KEY(account_id)
);

CREATE TABLE CustomerAccounts(
 customer_id NUMBER,
 acct_id NUMBER,
 
 CONSTRAINT PK_CUSTOMERACCOUNTS PRIMARY KEY(customer_id, acct_id)
);

CREATE TABLE LogsUsers (
  log_id NUMBER,
  log_time TIMESTAMP,
  log_operation VARCHAR2(60),
  
  -- for logging changes in the Users table
  oldFirstName VARCHAR2(25),
  newFirstName VARCHAR2(25),
  oldLastName VARCHAR2(25),
  newLastName VARCHAR2(25),
  oldUname VARCHAR2(25),
  newUname VARCHAR2(25),
  oldPW VARCHAR2(25),
  newPW VARCHAR2(25),
  
  CONSTRAINT PK_LOGSUSERS PRIMARY KEY(log_id)
);

CREATE TABLE LogsAccounts (
  log_id NUMBER,
  log_time TIMESTAMP,
  log_operation VARCHAR2(60),
  
  -- for logging changes in the Accounts table
  oldBalance NUMBER(12,2),
  newBalance NUMBER(12,2),
  oldStatus NUMBER,
  newStatus NUMBER,
  oldResolver NUMBER,
  newResolver NUMBER,
  
  CONSTRAINT PK_LOGSACCOUNTS PRIMARY KEY(log_id)
);

/******************************************************************************
CREATE FOREIGN KEY CONSTRAINTS
******************************************************************************/

ALTER TABLE Users ADD CONSTRAINT FK_USERSROLEID
    FOREIGN KEY (role_id) REFERENCES Role (role_id);
    
ALTER TABLE Accounts ADD CONSTRAINT FK_ACCOUNTSTYPEID
    FOREIGN KEY (type_id) REFERENCES AccountType (type_id);
    
ALTER TABLE Accounts ADD CONSTRAINT FK_STATUSID
    FOREIGN KEY (status_id) REFERENCES Status (status_id);
    
ALTER TABLE Accounts ADD CONSTRAINT FK_RESOLVERID
    FOREIGN KEY (resolver_id) REFERENCES Users (user_id);
    
ALTER TABLE CustomerAccounts ADD CONSTRAINT FK_CUSTOMERID
    FOREIGN KEY (customer_id) REFERENCES Users (user_id);
    
ALTER TABLE CustomerAccounts ADD CONSTRAINT FK_ACCOUNTID
    FOREIGN KEY (acct_id) REFERENCES Accounts (account_id);
    
