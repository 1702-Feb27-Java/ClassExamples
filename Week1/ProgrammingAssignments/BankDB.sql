CREATE TABLE Role 
(
    role_id NUMBER,
    role VARCHAR2(20),
    
    CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

/

CREATE TABLE Users
(
  user_id NUMBER,
  first_name VARCHAR2(25) NOT NULL,
  last_name VARCHAR2(25) NOT NULL,
  username VARCHAR2(25) NOT NULL UNIQUE,
  pass VARCHAR(25) NOT NULL,
  role_id NUMBER,
  PRIMARY KEY(user_id),
  FOREIGN KEY(role_id) REFERENCES Role(role_id)
);

/

CREATE TABLE Status 
(
    status_id NUMBER,
    status VARCHAR2(20),
    
    CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

/

CREATE TABLE AccountType
(
    account_type_id NUMBER,
    account_type VARCHAR2(20),
    PRIMARY KEY(account_type_id)
    
);

/

CREATE TABLE Accounts
(
  account_id NUMBER,
  account_type_id NOT NULL,
  balance DECIMAL(12,2) CHECK(balance > 0),
  status_id NUMBER DEFAULT(1),
  overseer NUMBER,
  PRIMARY KEY(account_id),
  FOREIGN KEY(account_type_id) REFERENCES AccountType(account_type_id),
  FOREIGN KEY(status_id) REFERENCES Status(status_id),
  FOREIGN KEY(overseer) REFERENCES Users(user_id)   
);

/

CREATE TABLE CustomerAccounts
(
  cust_id NUMBER,
  acct_id NUMBER,
  PRIMARY KEY(cust_id, acct_id),
  FOREIGN KEY (cust_id) REFERENCES Users(user_id),
  FOREIGN KEY (acct_id) REFERENCES Accounts(account_id)
);

---sequences---

CREATE SEQUENCE user_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
  
  CREATE SEQUENCE account_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
  /
 -- before insert on table of users select user sequence 
CREATE OR REPLACE TRIGGER user_trigger --name
BEFORE INSERT ON Users --on event
FOR EACH ROW --how often
BEGIN
  SELECT user_seq.NEXTVAL
  INTO :new.user_id
  FROM dual;
END;
/

CREATE OR REPLACE TRIGGER account_trigger --name
BEFORE INSERT ON ACCOUNTS --on event
FOR EACH ROW --how often
BEGIN
  SELECT account_seq.NEXTVAL
  INTO :new.account_id
  FROM dual;
END;

/
select * from users;
INSERT INTO USERS  (FIRST_NAME,LAST_NAME,pass,username,role_id) 
Values ('Zack', 'Stefan', '123', 'Zack105', 1);

INSERT INTO ROLE(role_id, role) VALUES (1,'Customer');
INSERT INTO ROLE(role_id, role) VALUES (2,'Employee');
INSERT INTO ROLE(role_id, role) VALUES (3,'Admin');

INSERT INTO STATUS(status_id, status) VALUES (1,'None');
INSERT INTO STATUS(status_id, status) VALUES (2,'Applied');
INSERT INTO STATUS(status_id, status) VALUES (3,'Active');


INSERT INTO ACCOUNTTYPE(account_type_id, account_type) VALUES (1,'Checking');
INSERT INTO ACCOUNTTYPE(account_type_id, account_type) VALUES (2,'Savings');


