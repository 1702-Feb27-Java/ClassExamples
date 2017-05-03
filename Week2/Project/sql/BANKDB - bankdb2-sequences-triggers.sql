/**
 * Produces the sequences and triggers of bankdb database
 *
 */
 
---- sequences ----
CREATE SEQUENCE user_sequence
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
  
CREATE SEQUENCE account_sequence
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;


CREATE SEQUENCE log_sequence
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;

---- triggers ----
-- auto increment and insert id of users as they are created

CREATE OR REPLACE TRIGGER user_trigger --name
  BEFORE INSERT ON Users --on what event
  FOR EACH ROW --how often
  BEGIN --start the trigger's code
    SELECT user_sequence.NEXTVAL
    INTO :new.user_id
    FROM dual;
  END;
  /

-- auto increment and insert id of accounts as they are created
CREATE OR REPLACE TRIGGER account_trigger
  BEFORE INSERT ON Accounts
  FOR EACH ROW
  BEGIN
    SELECT account_sequence.NEXTVAL
    INTO :new.account_id
    FROM dual;
  END;
  /

  
-- auto increment and insert id of logs as they are created
CREATE OR REPLACE TRIGGER log_trigger
  BEFORE INSERT ON logs
  FOR EACH ROW
  BEGIN
    SELECT log_sequence.NEXTVAL
    INTO :new.log_id
    FROM dual;
  END;
  /
  
  
---- PROCEDURES

CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT_FOR_CUSTOMER (userId IN NUMBER, accountType IN NUMBER)
  IS
  BEGIN
    INSERT INTO ACCOUNTS (TYPE_ID, BALANCE, STATUS_ID) VALUES (accountType, 0, 1);
    INSERT INTO CUSTOMER_ACCOUNTS (user_id, account_id) VALUES (userId, (SELECT Max(ACCOUNT_ID) FROM ACCOUNTS));
  END;
  /
    