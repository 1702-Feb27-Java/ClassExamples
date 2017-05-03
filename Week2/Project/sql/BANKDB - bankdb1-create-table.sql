/**
 * Produces the tables of the bankdb database
 *
 */
/*
DROP TABLE ROLE;
CREATE TABLE Role ( --lookup table for user's role
  role_id   NUMBER,
  role     VARCHAR2(20),
  
  CONSTRAINT role_id_pk PRIMARY KEY(role_id)
);

DROP TABLE USERS;
CREATE TABLE Users (
  user_id       NUMBER,
  first_name    VARCHAR2(25)      NOT NULL,
  last_name     VARCHAR2(25)      NOT NULL,
  username      VARCHAR2(25)      NOT NULL UNIQUE,
  pw            VARCHAR2(25)      NOT NULL,
  role_id       NUMBER,
  
  CONSTRAINT users_id_pk PRIMARY KEY(user_id),
  CONSTRAINT users_role_fk FOREIGN KEY(role_id) REFERENCES Role(role_id)
);

DROP TABLE ACCOUNT_TYPE;
CREATE TABLE Account_Type ( --lookup table for account's type
  type_id       NUMBER,
  type          VARCHAR2(25),
  
  CONSTRAINT account_id_pk PRIMARY KEY(type_id)
);

DROP TABLE STATUS;
CREATE TABLE Status ( --lookup table for account's status
  status_id     NUMBER,
  status        VARCHAR2(25),
  
  CONSTRAINT status_id_pk PRIMARY KEY(status_id)
);

DROP TABLE ACCOUNTS;
CREATE TABLE Accounts (
  account_id    NUMBER,
  type_id       NUMBER NOT NULL,
  balance       DECIMAL(12, 2),
  status_id     NUMBER DEFAULT(1),
  resolver_id   NUMBER,
  
  CONSTRAINT accounts_id_pk PRIMARY KEY(account_id),
  CONSTRAINT accounts_type_fk FOREIGN KEY(type_id) REFERENCES Account_Type(type_id),
  CONSTRAINT accounts_status_fk FOREIGN KEY(status_id) REFERENCES Status(status_id),
  CONSTRAINT accounts_users_fk FOREIGN KEY(resolver_id) REFERENCES Users(user_id),
  CONSTRAINT balance_not_zero CHECK (balance >= 0)
);

DROP TABLE CUSTOMER_ACCOUNTS;
CREATE TABLE Customer_Accounts ( --join table for customer and account.
  user_id       NUMBER,
  account_id    NUMBER,
  
  CONSTRAINT customeraccounts_pk PRIMARY KEY(user_id, account_id),
  CONSTRAINT customeraccounts_users_fk FOREIGN KEY(user_id) REFERENCES Users(user_id),
  CONSTRAINT customeraccounts_accounts_fk FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);
*/

DROP TABLE LOG_LEVEL;
CREATE TABLE Log_level (
  level_id      NUMBER,
  log_level     VARCHAR(25),
  
  CONSTRAINT loglevel_id_pk PRIMARY KEY(level_id)
);

DROP TABLE LOGS;
CREATE TABLE Logs (
  log_id        NUMBER,
  level_id      NUMBER,
  dateCreated   DATE      DEFAULT SYSDATE,
  message       VARCHAR(2000),
  
  CONSTRAINT logs_id_pk PRIMARY KEY(log_id),
  CONSTRAINT logs_level_fk FOREIGN KEY(level_id) REFERENCES log_level(level_id)
);

