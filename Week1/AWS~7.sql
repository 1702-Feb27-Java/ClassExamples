drop table users;
drop table role;
drop table CustomerAccounts;
drop table status;
drop table accounts;
drop table accounttype;
create table status
(
status_id NUMBER,
status varchar2(25),
primary key (status_id)
);
CREATE TABLE Role
(
role_id NUMBER,
role VARCHAR2(20),
CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Users
(
user_id NUMBER,
firstname VARCHAR2(25) NOT NULL,
last_name VARCHAR2 (25) NOT NULL,
username VARCHAR2(16) NOT NULL UNIQUE,
pw VARCHAR2(16) NOT NULL,
role_id NUMBER,
PRIMARY KEY(user_id),
FOREIGN KEY (role_id) REFERENCES role(role_id)

);

Create Table AccountType
(
type_id NUMBER,
type_name varchar2(25),
primary key (type_id)



);
drop Table Accounts;
CREATE TABLE Accounts
(

account_id NUMBER,
type_id NUMBER,
balance Decimal(12,2) CHECK (balance >= 0),
status_id NUMBER,
resolver NUMBER,
PRIMARY KEY (account_id),
FOREIGN KEY (type_id)REFERENCES accounttype (type_id),
foreign key (status_id)references  status(status_id),
foreign key(resolver) references users(user_id)
);
drop table customeraccounts;
CREATE TABLE CustomerAccounts
(
user_id NUMBER,
acct_id NUMBER,
PRIMARY KEY (user_id, acct_id),
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY (acct_id) REFERENCES Accounts(account_id)
);





Create SEQUENCE user_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1;

/
Create or replace trigger user_trigger
before insert on Users
for each row

begin select user_seq.NEXTVAL
INTO :new.user_id
from dual;
end;
/
select * from users;


insert into role(role_id,role)values(1,'Admin');
insert into role(role_id,role)values(2,'Employee');
insert into role(role_id,role)values(3,'Customer');
insert into users(FIRSTNAME,LAST_NAME,username,pw,role_id)values
('David','Lund','dl003','Testing',1);

select * from role;
select * from users;

