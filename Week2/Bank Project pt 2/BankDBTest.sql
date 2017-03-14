
UPDATE Accounts SET status_id = 2, resolver_id = 2 WHERE account_id = 3;
SELECT * FROM Users;

SELECT ac.ACCOUNT_ID,ac.TYPE_ID, ac.BALANCE, ac.STATUS_ID, ac.RESOLVER_ID 
FROM Accounts ac
INNER JOIN CustomerAccounts ca ON  ac.account_id = ca.acct_id
AND ca.customer_id = 9;

INSERT INTO Accounts Values (3, 1, 0, 1, 1);
DELETE FROM Accounts WHERE account_id = 1;

select * from LOGSUSERS;
select * from LOGSACCOUNTS;


DROP TABLE LOGSUSERS;
DROP TABLE LOGSACCOUNTS;