CREATE TABLE flash_cards (
  f_id number(10) primary key,
  f_question varchar2(4000),
  f_answer varchar2(4000)
);
/
CREATE OR REPLACE SEQUENCE fc_seq
  MINVALUE 1
  START WITH 4
  INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER fc_seq_trigger
  BEFORE INSERT ON flash_cards
    FOR EACH ROW
  BEGIN
    SELECT fc_seq.nextval INTO :new.f_id FROM DUAL;
  END;
/
BEGIN
  INSERT INTO FLASH_CARDS ('', 'What is DB normalization?', 'lols');
END;
/
CREATE OR REPLACE PROCEDURE insertFlash(someQ in varchar2, someA in varchar2)
IS
BEGIN
  INSERT INTO flash_cards VALUES ('', someQ, someA);
END;
/
BEGIN
  insertFlash('whats my age?', 'twenty three');
END;
/

call insertflash('test', 'test');

UPDATE flash_cards SET f_question = 'Why NOT WORKING' WHERE f_id = 4;