CREATE TABLE Role(
  role_id NUMBER,
  role VARCHAR2(25),
  
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Status(
  status_id NUMBER,
  status VARCHAR(16),
  CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

CREATE TABLE Account_Type(
  type_id NUMBER,
  type VARCHAR2(25),
  
  PRIMARY KEY(type_id)
);


CREATE TABLE Users(
  user_id NUMBER,
  firstname VARCHAR2(25) NOT NULL,
  lastname VARCHAR2(25) NOT NULL,
  username VARCHAR(16) NOT NULL UNIQUE,
  password VARCHAR(16) NOT NULL,
  role_id NUMBER,
  
  PRIMARY KEY(user_id),
  FOREIGN KEY(role_id) REFERENCES Role(role_id)
);

CREATE TABLE Accounts(
  account_id NUMBER,
  type_id NUMBER,
  balance DECIMAL(12,2),
  status_id NUMBER DEFAULT 1,
  resolver_id NUMBER,
  
  PRIMARY KEY(account_id),
  FOREIGN KEY(type_id) REFERENCES Account_Type(type_id),
  FOREIGN KEY(status_id) REFERENCES Status(status_id),
  FOREIGN KEY(resolver_id) REFERENCES Users(user_id)
);

CREATE TABLE Customer_Accounts(
  user_id NUMBER,
  account_id NUMBER,
  
  PRIMARY KEY (user_id, account_id),
  FOREIGN KEY(user_id) REFERENCES Users(user_id),
  FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);


CREATE SEQUENCE user_seq
  MINVALUE 1
  START WITH    1
  INCREMENT BY  1;

/
CREATE OR REPLACE TRIGGER user_trigger 
  BEFORE INSERT ON Users 
  FOR EACH ROW 
  BEGIN 
    SELECT user_seq.NEXTVAL
    INTO :new.user_id
    from dual;
  END;
/
  
CREATE SEQUENCE account_seq
  MINVALUE 1
  START WITH    1
  INCREMENT BY  1;

/
CREATE OR REPLACE TRIGGER account_trigger 
  BEFORE INSERT ON Accounts 
  FOR EACH ROW 
  BEGIN 
    SELECT account_seq.NEXTVAL
    INTO :new.account_id
    from dual;
  END;
/

insert into role(role_id, role) VALUES(1,'Customer');
insert into role(role_id, role) VALUES(2,'Employee');
insert into role(role_id, role) VALUES(3,'Admin');

insert into account_type(type_id, type) VALUES(1,'checking');
insert into account_type(type_id, type) VALUES(2,'saving');

insert into status(status_id, status) VALUES(1, 'pending');
insert into status(status_id, status) VALUES(2, 'approved');
insert into status(status_id, status) VALUES(3, 'rejected');


insert into users (FIRSTNAME,LASTNAME, USERNAME, PASSWORD, ROLE_ID) 
Values ('Genesis', 'Bonds', 'gab12', '123', 1);

insert into users (FIRSTNAME,LASTNAME, USERNAME, PASSWORD, ROLE_ID) 
Values ('Genesis', 'Bonds', 'gaby12', '123', 1);

insert into users (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, ROLE_ID)
Values ('Owner', 'Owner', 'admin', 'password', 3);

insert into users (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, ROLE_ID) 
Values ('Bobby', 'McBobFace', 'bobby', 'Rob', 2);

insert into Accounts(TYPE_ID, BALANCE, STATUS_ID, RESOLVER_ID)
Values (1, 0.0, 1, null);

insert into Accounts(TYPE_ID, BALANCE, STATUS_ID, RESOLVER_ID)
Values (1, 200.0, 2, null);

insert into CUSTOMER_ACCOUNTS(USER_ID, ACCOUNT_ID) VALUES (21,21);
insert into CUSTOMER_ACCOUNTS(USER_ID, ACCOUNT_ID) VALUES (21,22);

-- Users and their Accounts
SELECT * 
from CUSTOMER_ACCOUNTS
INNER JOIN Users ON Users.USER_ID = CUSTOMER_ACCOUNTS.USER_ID
INNER JOIN Accounts ON Accounts.ACCOUNT_ID = CUSTOMER_ACCOUNTS.ACCOUNT_ID;

-- Users that are Admins
SELECT *
from Users
INNER JOIN ROLE
ON Users.ROLE_ID = ROLE.ROLE_ID
WHERE ROLE.ROLE = 'Admin';

-- Accounts and Status
SELECT *
from ACCOUNTS
INNER JOIN STATUS
ON ACCOUNTS.STATUS_ID = STATUS.STATUS_ID;
