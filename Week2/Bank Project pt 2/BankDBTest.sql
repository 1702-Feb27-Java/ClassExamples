
UPDATE Accounts SET status_id = 2, resolver_id = 2 WHERE account_id = 3;
SELECT * FROM Users;
SELECT * FROM Accounts;


SELECT ac.ACCOUNT_ID,ac.TYPE_ID, ac.BALANCE, ac.STATUS_ID, ac.RESOLVER_ID 
FROM Accounts ac
INNER JOIN CustomerAccounts ca ON  ac.account_id = ca.acct_id
AND ca.customer_id = 9;

INSERT INTO Accounts (type_id) Values (1);

UPDATE Accounts SET status_id = 2, resolver_id = 2 WHERE account_id = 2;
UPDATE Users SET role_id = 3 WHERE user_id = 5;

select * from LOGSUSERS;
select * from LOGSACCOUNTS;
rollback;

CALL updatefirstname('Daniel', 3);

SELECT user_seq.CURRVAL from DUAL;

INSERT INTO Users (firstname, lastname, uname, pw) Values ('Mary', 'Jane', 'mj', '1234');


commit;
