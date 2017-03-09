CREATE TABLE Role
(
  role_id NUMBER,
  role VARCHAR2(25),
  
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Users 
(
  user_id NUMBER,
  first_name VARCHAR2(25) NOT NULL,
  last_name VARCHAR2(25) NOT NULL,
  username VARCHAR2(25) NOT NULL UNIQUE,
  pass VARCHAR2(25) NOT NULL,
  role_id NUMBER,
  
  CONSTRAINT u_id_pk PRIMARY KEY(user_id),
  CONSTRAINT r_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id)
);

CREATE TABLE Status
(
  status_id NUMBER,
  status VARCHAR2(25),
  
  CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

CREATE TABLE AccountType
(
  type_id NUMBER,
  type VARCHAR2(25),
  
  CONSTRAINT t_id_pk PRIMARY KEY(type_id)
);

CREATE TABLE Account 
(
  account_id NUMBER,
  type_id NUMBER NOT NULL,
  balance DECIMAL(12,2),
  status_id NUMBER DEFAULT(1),
  resolver_id NUMBER,
  
  CHECK(balance > 0),
  CONSTRAINT acta_id_pk PRIMARY KEY(account_id),
  CONSTRAINT actt_id_fk FOREIGN KEY(type_id) REFERENCES AccountType(type_id),
  CONSTRAINT acts_id_fk FOREIGN KEY(status_id) REFERENCES Status(status_id),
  CONSTRAINT actr_id_fk FOREIGN KEY(resolver_id) REFERENCES Users(user_id)
); 

CREATE TABLE CustomerAccounts
(
  user_id NUMBER,
  account_id NUMBER,
  
  CONSTRAINT custactu_id_pk PRIMARY KEY(user_id, account_id),
  CONSTRAINT custactu_id_fk FOREIGN KEY(user_id) REFERENCES Users(user_id),
  CONSTRAINT custacta_id_fk FOREIGN KEY(account_id) REFERENCES Account(account_id)
);

-------sequences----------

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

-------triggers----------
CREATE OR REPLACE TRIGGER user_trigger --name of hte sequence
  BEFORE INSERT ON  Users -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT user_seq.NEXTVAL
      INTO :new.user_id
      FROM dual;
    END;
/   

CREATE OR REPLACE TRIGGER account_trigger --name of hte sequence
  BEFORE INSERT ON  Account -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT account_seq.NEXTVAL
      INTO :new.account_id
      FROM dual;
    END;
/   
--populate lookup tables--
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (1, 'Admin');
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (2, 'Employee');
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (3, 'Customer');

INSERT INTO Status(Status_id, status)
VALUES (1, 'Pending');
INSERT INTO Status(Status_id, status)
VALUES (2, 'Approved');
INSERT INTO Status(Status_id, status)
VALUES (3, 'Denied');

INSERT INTO AccountType(type_id, type)
VALUES (1, 'Savings');
INSERT INTO AccountType(type_id, type)
VALUES (2, 'Checking');
