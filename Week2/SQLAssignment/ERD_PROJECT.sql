CREATE TABLE role
(
  role_id number,
  role VARCHAR2(25),
  PRIMARY KEY (role_id)
  
);

CREATE TABLE status
(
  status_id NUMBER,
  status VARCHAR2(25),
  PRIMARY KEY (status_id)

);

CREATE TABLE Account_Type
(
  type_id NUMBER,
  type VARCHAR2(25),
  PRIMARY KEY (type_id)

);

CREATE TABLE Accounts
(
  Account_id NUMBER,
  type_id NUMBER NOT NULL,
  BALANCE DECIMAL(12, 2) CHECK(BALANCE > 0),
  status_id NUMBER DEFAULT 1,
  resolver NUMBER,
  FOREIGN KEY(status_id) REFERENCES status (status_id),
  FOREIGN KEY(type_id) REFERENCES Account_type (type_id),
  FOREIGN KEY(resolver) REFERENCES users (user_id),
  PRIMARY KEY(Account_id)
);

CREATE TABLE users
(
  user_id NUMBER,
  Firstname VARCHAR2(25) NOT NUll,
  Lastname VARCHAR2(25) NOT NULL,
  username VARCHAR2(25) NOT NULL UNIQUE,
  password VARCHAR2(16),
  role_id NUMBER,
  FOREIGN KEY(role_id) REFERENCES role (role_id),
  PRIMARY KEY(user_id)

);


CREATE TABLE Customer_Accounts
(
  user_id NUMBER,
  Account_id number,
  FOREIGN KEY(user_id) REFERENCES users (user_id),
  FOREIGN KEY(Account_id) REFERENCES Accounts (Account_id),
  PRIMARY KEY(user_id, Account_id)
  
);

CREATE SEQUENCE user_seq
  MINVALUE 1  
  START WITH 1
  INCREMENT BY 1;
  
CREATE sequence account_seq
  minvalue 1
  start with 1
  increment by 1;
  
create or replace trigger account_trigger
  before insert ON Accounts
  for each row
  BEGIN
  SELECT account_seq.NEXTVAL
  INTO:new.Account_id
  from dual;
END;
/ 
drop trigger user_trigger;
CREATE OR REPLACE TRIGGER user_trigger
--when this trigger is activated before inserting into users
  BEFORE INSERT ON users
--how ofter
  for each row
--start what actualy happens
  BEGIN
--select USERS se
  SELECT user_seq.NEXTVAL
--NEW USER ID
  INTO:new.user_id
--DUMMY Table
  FROM dual;
END;
/

INSERT INTO users (USER_ID, firstname, lastname, username, PASSWORD, role_id) values(1, 'Nicholas', 'Perez', 'nperez', 'opeth4life', 1);
INSERT INTO users (USER_ID, firstname, lastname, username, PASSWORD, role_id) values(2, 'john', 'smith', 'jsmith', 'password', 2);

INSERT into role (role, role_id) values ( 'Admin', 1);
INSERT into role (role, role_id) values ( 'Employee', 2);
INSERT into role (role, role_id) values ( 'Customer', 3);

INSERT into ACCOUNT_TYPE (type_id, type) values (1, 'Checking');
INSERT into ACCOUNT_TYPE (type_id, type) values (2, 'Savings');
INSERT into ACCOUNT_TYPE (type_id, type) values (3, 'Joint');

INSERT INTO status (status_id, status) VALUES (1, 'Pending');
INSERT INTO status (status_id, status) VALUES (2, 'Declined');
INSERT INTO status (status_id, status) VALUES (3, 'Approved');

--Creating log table for transactions 
-- will include timestamps of logs
create table log_table
(
  user_id NUMBER
  when_date TIMESTAMP,
  FOREIGN KEY(user_id),
);
--joining a singular account from three different tables
Select users.firstname, users.lastname, users.username, accounts.balance, account_type.type from Users
join Accounts
on users.firstname = 'peter'
join account_type on accounts.type_id = account_type.type_id;