CREATE TABLE flash_cards (
  f_id number(10) primary key,
  f_question varchar2(4000),
  f_answer varchar2(4000)
);
/
CREATE OR REPLACE SEQUENCE fc_seq
  MINVALUE 0
  START WITH 1
  INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER fc_seq_trigger
  BEFORE INSERT ON flash_cards
    FOR EACH ROW
  BEGIN
    SELECT fc_seq.nextval INTO :new.f_id FROM DUAL;
  END;
/
CREATE OR REPLACE PROCEDURE procTest
IS
BEGIN
  DBMS_OUTPUT.PUT_LINE('Console output example');
END procTest;
/
BEGIN 
  PROCTEST();
END proctest;
/
CREATE OR REPLACE PROCEDURE insertIntoFlashCards(someID in number, someQ in varchar2, someA in varchar2)
IS
BEGIN 
  insert into flash_cards VALUES(someID, someQ, someA);
END;
/
BEGIN
  INSERTINTOFLASHCARDS(3, 'Will this work?', 'Yes');
END;
/
SELECT * from FLASH_CARDS;
/
CREATE OR REPLACE PROCEDURE getAnswer(question IN VARCHAR2, answer OUT VARCHAR2)
IS
BEGIN
  select F_ANSWER into answer from FLASH_CARDS where UPPER(f_question) = UPPER(question);
END;
/

Declare
  output varchar2(4000);
  inputVar varchar2(4000);
Begin
  select F_QUESTION into inputVar from flash_cards where f_id = 2;
  GETANSWER(inputVar, output);
  dbms_output.put_line('ANSWER IS ' || output);
end;
/
select * from flash_cards;
/
--IN : data passed into procedure through in vars. they are not received back from the call
--OUT: returned out of the procedure does NOT take in data
--IN OUT: WILL pass into procedure and data can be alterd and returned
CREATE OR REPLACE PROCEDURE getFlashCardCursor(outCursor OUT SYS_REFCURSOR)
IS
  newVar number(10) := 45;
BEGIN
  open outCursor for
  select * from flash_cards;
END;
/
declare
  cursorVar SYS_REFCURSOR;
  fcID flash_cards.f_id%type;
  fcQuestion flash_cards.f_QUESTION%type;
  fcAnswer FLASH_CARDS.F_ANSWER%type;
begin
  GETFLASHCARDCURSOR(cursorVar);
  LOOP
    FETCH cursorVar into fcID, fcQuestion, fcAnswer;
    EXIT WHEN cursorVar%NOTFOUND;
    dbms_output.put_line('i' || fcID || 'i' || fcQuestion || 'i' || fcAnswer || 'i');
  END LOOP;
end;
/
CREATE OR REPLACE FUNCTION getMaxID
return number
is
  theMax number(10);
begin
  select max(f_id) into theMax from flash_cards;
  return theMax;
end getMaxID; --can also just say end
/
declare
  maximum number(10);
begin
  maximum := getMaxID();
  dbms_output.put_line(maximum);
end;
/
CREATE OR REPLACE FUNCTION highNum(num1 IN number, num2 IN number, num3 in number)
return number
IS
  curMax number(10);  
BEGIN
  curMax := num1;
  IF num1 < num2 THEN
    curMax := num2;
  END IF;
  IF curMax < num3 THEN
    curMax := num3;
  END IF;
  return curMax;
END highNum;
/
BEGIN 
  dbms_output.put_line(highNum(10,20,5));
END;
/