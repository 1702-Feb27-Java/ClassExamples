CREATE TABLE Role
(
  role_id NUMBER,
  role VARCHAR(20),
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Users
(
  user_id NUMBER,
  first_name VARCHAR2(25) NOT NULL,
  last_name VARCHAR2(25) NOT NULL,
  username VARCHAR2(16) NOT NULL UNIQUE,
  pq VARCHAR2(16) NOT NULL,
  role_id NUMBER,
  PRIMARY KEY (user_id),
  FOREIGN KEY(role_id) REFERENCES Role(role_id)
);

CREATE  TABLE Status
(
status_id NUMBER,
status VARCHAR2(25),
PRIMARY KEY (status)
);

CREATE  TABLE AccType
(
type_id NUMBER,
acc_type VARCHAR2(25),
PRIMARY KEY (type_id)
);

CREATE TABLE BAccounts
(
acc_no NUMBER,
type_id NUMBER NOT NULL,
balance DECIMAL(12,2) check(balance >0),
status_id NUMBER default 1,
user_id NUMBER,
task VARCHAR2(10),
PRIMARY KEY (acc_no),
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY (status_id) REFERENCES Status(status_id),
FOREIGN KEY (type_id) REFERENCES AccType(type_id)
);

CREATE TABLE CAccounts
(
user_id NUMBER,
acc_no NUMBER,
PRIMARY KEY (user_id,acc_no),
FOREIGN KEY (user_id) REFERENCES Users(user_id),
FOREIGN KEY (acc_no) REFERENCES BAccounts(acc_no)
);

create table logs(
logID  number NOT NULL primary key,
timeS TIMESTAMP,
Action varchar2(10),
by_User varchar2(10),
TBLUpdated varchar(10),
role_id number,
acc_no number,
FOREIGN KEY(role_id) REFERENCES Role(role_id)
);
/

CREATE SEQUENCE log__seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER log_Trigger BEFORE INSERT ON logs
  FOR EACH ROW
  BEGIN
    SELECT log__seq.NEXTVAL
    INTO :new.logid
    FROM dual;
END;
/
-----sequences------
CREATE SEQUENCE user__seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;

CREATE OR REPLACE TRIGGER user_trigger BEFORE INSERT ON USERS
  FOR EACH ROW
  BEGIN
    SELECT user__seq.NEXTVAL
    INTO :new.user_id
    FROM dual;
END;
/

-----sequences------
CREATE SEQUENCE acc__seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;

CREATE OR REPLACE TRIGGER acc_trigger BEFORE INSERT ON BACCOUNTS
  FOR EACH ROW
  BEGIN
    SELECT acc__seq.NEXTVAL
    INTO :new.acc_no
    FROM dual;
END;
/

create or replace procedure createAccount(iUser in varchar2, iAccT in number,iAm in number)
is 
uUid  number;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
iRole number;
begin
  select USER_ID into uUid from users where USERNAME=iUser;
  select role_id into iRole from users where USERNAME=iUser;
  select CURRENT_TIMESTAMP into timeS from dual;
  iUs:=iUser;
  --if uUid IS NOT NULL then
  dbms_output.put_line('Account insert');
  insert into baccounts(TYPE_ID,BALANCE,STATUS_ID,USER_ID,TASK) values(iAccT,0,1,uUid,concat('C/',iAm)); 
  insert into logs values('',timeS,'REQ_N_ACC',iUs,'BACCOUNTS',iRole,'');
  --end if;
end;
/

create or replace procedure appAcc(iAcc in number, iAmt in number, stat in number, iUserL in varchar2)
is 
uUid  number;
iRole number;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
begin
  dbms_output.put_line('Account Request');
  select user_id into uUid from bAccounts where acc_no=iAcc;
  select role_id into iRole from users where USERNAME=iUserL;
  iUs:=iUserL;
  select CURRENT_TIMESTAMP into timeS from dual; 
  update baccounts set balance=iAmt,status_id=stat where acc_no=iAcc; 
  insert into logs values('',timeS,'APP_N_ACC',iUs,'BACCOUNTS',iRole,iAcc);
end;
/

create or replace procedure updateAcct(iUser in varchar2,iAm in number)
is 
uUid  number;
uBal number;
iRole number;
iAccT baccounts.type_id%TYPE;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
begin
  select USER_ID into uUid from users where USERNAME=iUser;
  if uUid IS NOT NULL then
  select acc_no into iAccT from BACCOUNTS where user_id=uUid;
   select role_id into iRole from users where USERNAME=iUser;
  select CURRENT_TIMESTAMP into timeS from dual; 
  iUs:=iUser;
  dbms_output.put_line('Account update request');
  update baccounts set status_id=1,task=concat('',iAm) where  acc_no=iAccT; 
  insert into logs values('',timeS,'REQ_U_ACC',iUs,'BACCOUNTS',iRole,iAccT);
  end if;
end;
/

create or replace procedure updateApp(iAcc in number,stat in number,iAm in number, iUserL in varchar2)
is 
uUid  number;
uBal number;
iAccT baccounts.type_id%TYPE;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
iRole number;
begin
  select user_id into uUid from bAccounts where acc_no=iAcc;
  iUs:=iUserL;
  select role_id into iRole from users where USERNAME=iUserL;
  select CURRENT_TIMESTAMP into timeS from dual; 
  dbms_output.put_line('updating approved');
  update baccounts set status_id=stat, balance=iAm where acc_no=iAcc; 
   insert into logs values('',timeS,'APP_U_ACC',iUs,'BACCOUNTS',iRole,iAcc);
end;
/

create or replace procedure deleteAcc(iUser in varchar2)
is 
iUid number;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
iRole number;
iAccNo number;
begin
select user_id into iUid from users where username=iUser;
select role_id into iRole from users where username=iUser;
select acc_no into iAccNo from baccounts where USER_ID=iUid;
select CURRENT_TIMESTAMP into timeS from dual; 
iUs:=iUser;
update baccounts set status_id=1,task='D' where user_id=iUid;
insert into logs values('',timeS,'REQ_D_ACC',iUs,'BACCOUNTS',iRole,iAccNo);
end;
/

create or replace procedure appDelete(iAcc in number,stat in number, iAmt in number, iUserL in varchar2)
is 
iUid number;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
iRole number;
begin
select user_id into iUid from bAccounts where acc_no=iAcc;
iUs:=iUserL;
select role_id into iRole from users where USERNAME=iUserL;
select CURRENT_TIMESTAMP into timeS from dual; 
update baccounts set status_id=stat,balance=iAmt where acc_no=iAcc;
if iAmt=0 then
SYS.DBMS_OUTPUT.PUT_LINE('DELETE ROW ACCOUNT');
 delete from baccounts where acc_no=iAcc;
 insert into logs values('',timeS,'APP_D_ACC',iUs,'BACCOUNTS',iRole,'');
else
 update baccounts set status_id=3 where acc_no=iAcc;
 insert into logs values('',timeS,'DEN_D_ACC',iUs,'BACCOUNTS',iRole,iAcc);
end if;
end;
/

create or replace procedure regUsers(fName in varchar2,lName in varchar2,iUser in varchar2,iPwd in varchar2,iRole in number)
is 
uUid  number;
timeS timestamp;
iUs LOGS.BY_USER%TYPE;
begin
  select CURRENT_TIMESTAMP into timeS from dual;
  iUs:=iUser;
  dbms_output.put_line('User registered');
  insert into users values('',fName,lName,iUser,iPwd,iRole); 
  insert into logs values('',timeS,'REG_USER',iUs,'USERS',iRole,'');
end;
/