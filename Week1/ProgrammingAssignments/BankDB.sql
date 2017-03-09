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

insert into role(role_id, role) VALUES(1,'Admin');
insert into role(role_id, role) VALUES(2,'Employee');
insert into role(role_id, role) VALUES(3,'Customer');
SELECT * from role;

insert into ACCOUNT_TYPE(TYPE,TYPE_ID) VALUES('CHECKING',1);
insert into ACCOUNT_TYPE(TYPE,TYPE_ID) VALUES('SAVING',2);
SELECT * from role;
update account_type set TYPE='SAVING' where type_id=2;
insert into status(status_id, status) VALUES(1,'ACTIVE');
insert into status(status_id, status) VALUES(2,'INACTIVE');
insert into status(status_id, status) VALUES(3,'PENDING');

SELECT * from role;
/* User sequence*/
CREATE SEQUENCE user_seq
  MINVALUE 1
  START WITH    1
  INCREMENT BY  1;
/* user trigger*/
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
  
  /*Triger and for account*/
  
  CREATE OR REPLACE TRIGGER account_trigger 
  BEFORE INSERT ON accounts 
  FOR EACH ROW 
  BEGIN 
    SELECT account_seq.NEXTVAL
    INTO :new.account_id
    from dual;
  END;
/
 /*Sequence and for account*/
 CREATE SEQUENCE account_seq
  MINVALUE 1
  START WITH    1
  INCREMENT BY  1;
/
/*Populate*/
delete from users where USERNAME='gaby12';
SELECT * FROM users;
insert into users (FIRSTNAME,LASTNAME, USERNAME, PASSWORD, ROLE_ID) 
Values ('Genesis', 'Bonds', 'gab12', '123', 1);
insert into users (FIRSTNAME,LASTNAME, USERNAME, PASSWORD, ROLE_ID) 
Values ('Genesis', 'Bonds', 'gaby12', '123', 1);
insert into users (FIRSTNAME,LASTNAME, USERNAME, PASSWORD, ROLE_ID) 
values('Mory','Keita','morykeita','abcdef',1);
SELECT * FROM users;

Select*from accounts;
insert into accounts(type_id,balance,status_id)
values(1,2323,1);
insert into accounts(type_id,balance,status_id,RESOLVER_ID)
values(1,2323,1,3);

/* joint table*/
Select users.user_id,ROLE.role
from role inner join users
on role.role_id=users.role_id;

