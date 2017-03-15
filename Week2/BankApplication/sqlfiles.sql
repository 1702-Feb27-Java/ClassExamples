
---- add a customer
CREATE OR REPLACE PROCEDURE addCustomer( v_userName IN VARCHAR2, v_lastName in VARCHAR2, v_userId VARCHAR2, v_password VARCHAR2)
IS
BEGIN
 INSERT INTO users( firstName, lastName,username, password) values (v_userName,v_lastName,v_username,v_password);
 END;
--------------- add an account

/
CREATE OR REPLACE PROCEDURE ADD_ACCOUNT(U_ID IN NUMBER, T_ID IN NUMBER, S_ID IN NUMBER)
  IS
    A_ID NUMBER;
  BEGIN
    INSERT INTO ACCOUNTS(TYPE_ID, STATUS_ID) VALUES (T_ID, S_ID);
    SELECT ACCOUNT_ID INTO A_ID FROM ACCOUNTS WHERE ROWNUM = 1 ORDER BY ACCOUNT_ID DESC;
    INSERT INTO CUSTOMER_ACCOUNTS(USER_ID, ACCOUNT_ID) VALUES (U_ID, A_ID); 
  END;
/

---- get the balance

create or replace PROCEDURE getBalance(balance OUT number, v_AccountId in number)
IS
 currNum number(10,2);
BEGIN
  SElect balance into currNum from accounts 
  where account_id = v_accountId;
  balance := currNum;
END;
/
--------- Get  user's password -----
CREATE OR REPLACE  PROCEDURE getPassword( v_username in VARCHAR2, v_password In varchar2)
IS 
 v_password varchar2;
 BEGIN
 SELECT password into v_password from users
 where username=v_username;
 password:=v_password;
 END;
/
--------------- approve an account---------------------
CREATE OR REPLACE PROCEDURE approveAccount( v_accountId in Number)
IS
BEGIN
update account set status_id=2 where account_id=v_accountid;
END;
/

-----------------------------------------------------------
create or replace PROCEDURE addCustomer( v_userName IN VARCHAR2, v_lastName in VARCHAR2, v_userId VARCHAR2, v_password VARCHAR2,v_roleid NUMBER)
IS
BEGIN
 INSERT INTO users( firstName, lastName,username, password,role_id) values (v_userName,v_lastName,v_username,v_password,v_roleid);
 END;
--------------------------------------------------------
/
CREATE OR REPLACE VIEW listOfAccountsId 
 as SELECT account_id from customer_accounts where user_id=127;

SELECT
FROM ACCOUNTS A
INNER JOIN listOfAccountsId B on a.account_id = b.account_id;
/

Select accounts.account_id,account_Type.type, accounts.balance
from accounts
inner join 
account_Type on  accounts.type_id=account_Type.TYPE_ID 
inner join customer_accounts on customer_accounts.account_id= accounts.account_id
where user_id=127;






