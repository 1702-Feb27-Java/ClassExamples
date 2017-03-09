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
  
