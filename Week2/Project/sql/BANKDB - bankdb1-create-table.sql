/**
 * Produces the tables of the bankdb database
 *
 */


CREATE TABLE Role ( --lookup table for user's role
  role_id   NUMBER,
  role     VARCHAR2(20),
  
  CONSTRAINT role_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Users (
  user_id       NUMBER,
  first_name    VARCHAR2(25)      NOT NULL,
  last_name     VARCHAR2(25)      NOT NULL,
  username      VARCHAR2(25)      NOT NULL UNIQUE,
  pw            VARCHAR2(25)      NOT NULL,
  role_id       NUMBER,
  
  PRIMARY KEY(user_id),
  FOREIGN KEY(role_id) REFERENCES Role(role_id)
);

CREATE TABLE Account_Type ( --lookup table for account's type
  type_id       NUMBER,
  type          VARCHAR2(25),
  
  PRIMARY KEY(type_id)
);

CREATE TABLE Status ( --lookup table for account's status
  status_id     NUMBER,
  status        VARCHAR2(25),
  
  PRIMARY KEY(status_id)
);

CREATE TABLE Accounts (
  account_id    NUMBER,
  type_id       NUMBER NOT NULL,
  balance       DECIMAL(12, 2),
  status_id     NUMBER DEFAULT(1),
  resolver_id   NUMBER,
  
  PRIMARY KEY(account_id),
  FOREIGN KEY(type_id) REFERENCES Account_Type(type_id),
  FOREIGN KEY(status_id) REFERENCES Status(status_id),
  FOREIGN KEY(resolver_id) REFERENCES Users(user_id),
  CONSTRAINT balance_not_zero CHECK (balance >= 0)
);

CREATE TABLE Customer_Accounts ( --join table for customer and account.
  user_id       NUMBER,
  account_id    NUMBER,
  
  PRIMARY KEY(user_id, account_id),
  FOREIGN KEY(user_id) REFERENCES Users(user_id),
  FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);

