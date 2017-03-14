--DROP SEQUENCE ACCOUNT_SEQ;
--DROP SEQUENCE USER_SEQ;
--DROP TABLE CUSTOMERACCOUNTS;
--DROP TABLE ACCOUNTS;
--DROP TABLE USERS;
--DROP TABLE ROLE;
--DROP TABLE STATUS;
--DROP TABLE ACCOUNTTYPE;
--/
CREATE TABLE ROLE
(
  role_id NUMBER,
  role VARCHAR2(25),
  
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE USERS 
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

CREATE TABLE STATUS
(
  status_id NUMBER,
  status VARCHAR2(25),
  
  CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);

CREATE TABLE ACCOUNTTYPE
(
  type_id NUMBER,
  type VARCHAR2(25),
  
  CONSTRAINT t_id_pk PRIMARY KEY(type_id)
);

CREATE TABLE ACCOUNTS
(
  account_id NUMBER,
  type_id NUMBER NOT NULL,
  balance NUMBER(12,2) DEFAULT(0),
  status_id NUMBER DEFAULT(1),
  resolver_id NUMBER DEFAULT(0),
  
  CHECK(balance >= 0),
  CONSTRAINT acta_id_pk PRIMARY KEY(account_id),
  CONSTRAINT actt_id_fk FOREIGN KEY(type_id) REFERENCES AccountType(type_id),
  CONSTRAINT acts_id_fk FOREIGN KEY(status_id) REFERENCES Status(status_id)
);

CREATE TABLE CUSTOMERACCOUNTS
(
  user_id NUMBER,
  account_id NUMBER,
  
  CONSTRAINT custactu_id_pk PRIMARY KEY(user_id, account_id),
  CONSTRAINT custactu_id_fk FOREIGN KEY(user_id) REFERENCES Users(user_id),
  CONSTRAINT custacta_id_fk FOREIGN KEY(account_id) REFERENCES Accounts(account_id)
);

---- sequences ----
/
CREATE SEQUENCE user_seq
  MINVALUE 1
  START WITH    1
  INCREMENT BY  1;

CREATE OR REPLACE TRIGGER user_trigger
  BEFORE INSERT ON Users
  FOR EACH ROW
  BEGIN
    SELECT user_seq.NEXTVAL
    INTO :new.user_id
    FROM dual;
  END;
  /
CREATE SEQUENCE account_seq
  MINVALUE 1
  START WITH    1
  INCREMENT BY  1;
  
CREATE OR REPLACE TRIGGER account_trigger
  BEFORE INSERT ON Accounts
  FOR EACH ROW
  BEGIN
    SELECT account_seq.NEXTVAL
    INTO :new.account_id
    FROM dual;
  END;
/
CREATE OR REPLACE PROCEDURE LOGIN(UN IN VARCHAR2, PW IN VARCHAR2, U_ID OUT NUMBER)
  IS
    USERID NUMBER;
  BEGIN
    SELECT USER_ID INTO USERID FROM USERS WHERE USERS.USERNAME = UN AND USERS.PASS = PW;
    U_ID := USERID;
  END;
/
create or replace PROCEDURE ADD_ACCOUNT(U_ID IN NUMBER, T_ID IN NUMBER, S_ID IN NUMBER)
  IS
    A_ID NUMBER;
  BEGIN
    -- TODO RESOLVE ACCOUNTTYPE
    -- STATUS ID
    -- RESOLVER ID
    INSERT INTO ACCOUNTS(TYPE_ID, STATUS_ID) VALUES (T_ID, S_ID);
    SELECT ACCOUNT_ID INTO A_ID FROM ACCOUNTS WHERE ROWNUM = 1 ORDER BY ACCOUNT_ID DESC;--TOP 1 FROM TABLE ORDER BY ACCOUNT_ID DESC
    INSERT INTO CUSTOMERACCOUNTS(USER_ID, ACCOUNT_ID) VALUES (U_ID, A_ID); --CUST_ACCTS
  END;
/
create or replace PROCEDURE ADD_ADMIN(F_NAME IN VARCHAR2, L_NAME IN VARCHAR2, UNAME IN VARCHAR2, PW IN VARCHAR2)
  IS
  BEGIN
    INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PASS, ROLE_ID) VALUES (F_NAME, L_NAME, UNAME, PW, 1);--INTO USERS
  END;
/
create or replace PROCEDURE ADD_CUSTOMER(F_NAME IN VARCHAR2, L_NAME IN VARCHAR2, UNAME IN VARCHAR2, PW IN VARCHAR2)
  IS
  BEGIN
    INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PASS, ROLE_ID) VALUES (F_NAME, L_NAME, UNAME, PW, 3);--INTO USERS
  END;
/
create or replace PROCEDURE ADD_EMPLOYEE(F_NAME IN VARCHAR2, L_NAME IN VARCHAR2, UNAME IN VARCHAR2, PW IN VARCHAR2)
  IS
  BEGIN
    INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PASS, ROLE_ID) VALUES (F_NAME, L_NAME, UNAME, PW, 2);--INTO USERS
  END;
/
create or replace PROCEDURE SET_BALANCE(A_ID IN NUMBER, NEW_BAL IN NUMBER)
  IS
    BEGIN
      UPDATE ACCOUNTS A
      SET A.BALANCE = NEW_BAL
      WHERE A_ID = A.ACCOUNT_ID;
    END;
/
CREATE OR REPLACE PROCEDURE APPROVE(U_ID IN NUMBER, A_ID IN NUMBER)
  IS
    BEGIN
      UPDATE ACCOUNTS A
      SET A.STATUS_ID = 2,
          A.RESOLVER_ID = U_ID
      WHERE A_ID = A.ACCOUNT_ID;
    END;
/
CREATE OR REPLACE PROCEDURE DECLINE(U_ID IN NUMBER, A_ID IN NUMBER)
  IS
    BEGIN
      UPDATE ACCOUNTS A
      SET A.STATUS_ID = 3,
          A.RESOLVER_ID = U_ID
      WHERE A_ID = A.ACCOUNT_ID;
    END;
/
--insert into users (FIRST_NAME, LAST_NAME, username, pass, role_id) VALUES
--('Jonathan', 'Lee', 'dlwoaks', '123', 1);
--insert into users (FIRST_NAME, LAST_NAME, username, pass, role_id) VALUES
--('Ben', 'Webster', 'websterb', '234', 2);
--insert into users (FIRST_NAME, LAST_NAME, username, pass, role_id) VALUES
--('Moriarty', 'Keita', 'ask', '321', 1);

insert into role(role_id, role) VALUES (1, 'Admin');
insert into role(role_id, role) VALUES (2, 'Employee');
insert into role(role_id, role) VALUES (3, 'Customer');

insert into status(status_id, status) VALUES (1, 'Pending');
insert into status(status_id, status) VALUES (2, 'Approved');
insert into status(status_id, status) VALUES (3, 'Declined');

insert into accounttype(type_id, type) VALUES (1, 'Checkings');
insert into accounttype(type_id, type) VALUES (2, 'Savings');

insert into users(FIRST_NAME, LAST_NAME, USERNAME, PASS, ROLE_ID) VALUES ('SYSTEM', 'ADMIN', 'admin', '1234', 1);