drop table flash_cards;
/

CREATE TABLE flash_cards
(
  f_id NUMBER(10) PRIMARY KEY,
  f_question VARCHAR2(4000),
  f_answer VARCHAR2(4000)
);
/

INSERT INTO flash_cards (f_id, f_question, f_answer)
  VALUES (1, 'Who is Bobbert?', 'The Best' );
 / 
 
INSERT INTO flash_cards (f_id, f_question, f_answer)
  VALUES (2, 
          'What are the SQL differrent joins',
          'Inner joins, left join, right join, full outer join, cross join ' 
          );
/

CREATE SEQUENCE fc_seq  
  MINVALUE 1 
  INCREMENT BY 1;
/

create or replace TRIGGER fcid_trigger
--WHAT EVEN SHOULD THIS TRIGGER HAPPEN
  BEFORE INSERT ON FLASH_CARDS
  --HOW OFTER
  FOR EACH ROW
  --START WHAT ACTUALLY HAPPENS
  BEGIN
  --SELECT USER SE
    SELECT fc_seq.NEXTVAL
  --NEW USER ID
    INTO :new.f_id
  --DUMMY TABLE
    FROM dual;
  END;
/
commit;
select * from flash_cards;
/
INSERT INTO flash_cards (f_id, f_question, f_answer)
  VALUES ('', 'Who is Bobbert?', 'The Best' );
 / 
 
 CREATE OR REPLACE PROCEDURE insertFlash( someQ IN VARCHAR2, someA IN VARCHAR2)
IS
BEGIN
  INSERT INTO flash_cards VALUES ('', someQ, someA);
END insertFlash;
/

CALL insertFlash ('test', 'worked');
/

UPDATE FLASH_CARDS
  SET f_question = 'hello'
  WHERE f_id = 3;
  
SELECT * FROM FLASH_cARDS WHERE F_ID = 5;
