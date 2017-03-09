CREATE TABLE Role(
 role_id NUMBER,
 role VARCHAR2(20),

 CONSTRAINT r_id_pk PRIMARY KEY (role_id)
);

CREATE TABLE Users(
 user_id NUMBER,
 firstname VARCHAR2(25) NOT NULL,
 lastname VARCHAR(25) NOT NULL,
 username VARCHAR(25) NOT NULL UNIQUE,
 pw VARCHAR(16) NOT NULL,
 role_id NUMBER,
 
 PRIMARY KEY(user_id),
 FOREIGN KEY (role_id) REFERENCES Role(role_id)

);

CREATE TABLE Status(
 status_id NUMBER,
 status varchar2(15),
 
 PRIMARY KEY(status_id)
);

CREATE TABLE AccountType(
 type_id NUMBER,
 type VARCHAR2(15),
 
 PRIMARY KEY(type_id)
);

CREATE TABLE Accounts(
 account_id NUMBER,
 type_id NUMBER NOT NULL,
 balance DECIMAL(12,2) CHECK(balance > 0),
 status_id NUMBER DEFAULT 1,
 resolver_id NUMBER,
 
 PRIMARY KEY(account_id),
 FOREIGN KEY(type_id) REFERENCES AccountType(type_id),
 FOREIGN KEY(status_id) REFERENCES Status(status_id),
 FOREIGN KEY(resolver_id) REFERENCES Users(user_id)
);

CREATE TABLE CustomerAccounts(
 customer_id NUMBER,
 acct_id NUMBER,
 
 PRIMARY KEY(customer_id, acct_id),
 FOREIGN KEY(customer_id) REFERENCES Users(user_id),
 FOREIGN KEY(acct_id) REFERENCES Accounts(account_id)
);


-----Sequences-----

-- for Users
CREATE SEQUENCE user_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER user_trigger --name
  BEFORE INSERT ON Users -- upon what event
  FOR EACH ROW -- how often
  BEGIN -- start what actually happens
    SELECT user_seq.NEXTVAL
    INTO :new.user_id
    FROM dual;
  END;
/

-- for Accounts

CREATE SEQUENCE acc_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER acc_trigger --name
  BEFORE INSERT ON Accounts -- upon what event
  FOR EACH ROW -- how often
  BEGIN -- start what actually happens
    SELECT acc_seq.NEXTVAL
    INTO :new.account_id
    FROM dual;
  END;
/

SELECT * FROM Users;

INSERT INTO Users (FIRSTNAME, LASTNAME, USERNAME, PW, role_id) VALUES
('Danni', 'Tang', 'dtang', 'danni111', 1);

-- Populating lookup tables
INSERT INTO Role(role_id, role) VALUES (1, 'Admin');
INSERT INTO Role(role_id, role) VALUES (2, 'Employee');
INSERT INTO Role(role_id, role) VALUES (3, 'Customer');

INSERT INTO Status(status_id, status) VALUES(1, 'Pending');
INSERT INTO Status(status_id, status) VALUES(2, 'Approved');

INSERT INTO AccountType(type_id, type) VALUES(1, 'Checking');
INSERT INTO AccountType(type_id, type) VALUES(2, 'Saving');

select * from accounttype;