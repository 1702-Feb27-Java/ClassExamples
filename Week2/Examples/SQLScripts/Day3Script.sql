--Clean up the space
drop table flash_cards;
/--Used to separate statements

--Create my table for use
CREATE TABLE flash_cards (
  f_id number(10) primary key,
  f_question varchar2(4000),
  f_answer varchar2(4000)
);
/

--Full insert statement
INSERT INTO FLASH_CARDS (f_id, f_question, f_answer)
VALUES (1, 'Who is bobbert?', 'The best');
/

--Short cut insert statement. Omitting the specific columns will default to
--inserting into every column from left to right.
INSERT INTO FLASH_CARDS
VALUES (2, 'What are SQL the different SQL joins', 'Inner join, left join, right join, full outer join, cross join');
/

--Observe our entire table
select * from FLASH_CARDS;
/

--Simple procure
CREATE OR REPLACE PROCEDURE procTest   --parameterless example
IS
--declaration block
BEGIN
--execution block
  DBMS_OUTPUT.PUT_LINE('Console output example');
--optional exception handling block can go here
END procTest;
/

--calling a procedure outside a transaction block
call procTest();
/

--Create an insert procedure
CREATE OR REPLACE PROCEDURE insertIntoFlashCards(someID in number, someQ in varchar2, someA in varchar2)
IS
BEGIN
  insert into flash_cards VALUES(someID, someQ, someA); 
End;
/

--Calling a procedure inside a transaction block
Begin
  INSERTINTOFLASHCARDS(3, 'Will this work?', 'yes?');
end;
/

--Observe table
select * from FLASH_CARDS;
/

--Procedure parameter example
CREATE OR REPLACE PROCEDURE getAnswer(question IN varchar2, answer OUT varchar2)
IS
BEGIN
  select F_ANSWER into answer from FLASH_CARDS where UPPER(F_QUESTION) = UPPER(question);
END;
/

--Full transaction block procedure call
Declare
  output varchar2(4000);
  inputVar varchar2(4000);
Begin
  select F_QUESTION into inputVar from flash_cards where F_ID = 2;

  GETANSWER(inputVar ,output);
  dbms_output.put_line('ANSWER IS: ' || output);
end;
--NOTE: || appends in sql
/

--IN : data pased into the procedure through IN variables. They are not received back from the call
--OUT : Returned out of the procedure, does NOT take in data
--IN OUT: you WILL pass in data, and the data can be altered and returned
--Retreiving a multi row query from a procedure
CREATE OR REPLACE PROCEDURE getFlashCardCursor(outCursor OUT SYS_REFCURSOR)
IS
BEGIN
  open outCursor for 
  select * from flash_cards;
END;
/

--NOTE: You can NOT return a CURSOR. Only a SYS_REFCURSOR or REF_CURSOR
declare
  cursorVar SYS_REFCURSOR;
  fcID FLASH_CARDS.F_ID%TYPE;
  fcQuestion FLASH_CARDS.F_QUESTION%TYPE;
  fcAnswer FLASH_CARDS.F_ANSWER%TYPE;
begin
  GETFLASHCARDCURSOR(cursorVar);
  LOOP
    FETCH cursorVAR INTO fcID, fcQuestion, fcAnswer;
    EXIT WHEN cursorVar%NOTFOUND;
    dbms_output.put_line('|' || fcID || '|' || fcQuestion || '|' || fcAnswer || '|');
  END LOOP;
end;
/

--Function example
CREATE OR REPLACE FUNCTION getMaxID
return number --Note this return statement declaring return type
is
  theMax number(10); --NOTE: Do not define type size in parameters, but DO
begin                --      define size when declaring them
  select max(f_id) into theMax from flash_cards;
  return theMax;
end getMaxID;
/

declare
  maximum number(10);
begin
  maximum := getMAXID();
  dbms_output.put_line(maximum);
end;
/

CREATE OR REPLACE FUNCTION highNum(num1 IN number, num2 in number, num3 in number)
return number
IS
  curMax number(10);  
BEGIN
  curMax := num1;
  if num2 > num1 THEN
    curMax := num2;
  end if;
  if num3 > curMax then
    curmax := num3;
  end if;
  return curMax;
END highNum;
/

begin
  dbms_output.put_line(highNum(1, 10, 5));
end;

--Extra examples

--Exception Handling example
CREATE OR REPLACE PROCEDURE exceptionExample
IS
  CURSOR badCurse IS --Create an explicity cursor that grabs entire table
    SELECT * FROM flash_cards;
  fid flash_cards.f_id%TYPE;
  fquestion flash_cards.f_question%TYPE;
  fanswer flash_cards.f_answer%TYPE;
BEGIN
  --OPEN badCurse;      --If a cursor isn't opened, it throws an exception when used
  LOOP
    FETCH badCurse into fid, fquestion, fanswer;  --Place column values into variables and move cursor to next row
    EXIT WHEN badCurse%NOTFOUND;           --Use NOTFOUND attribute as exit boolean
    dbms_output.put_line('Successful fetch!');
  END LOOP;
  CLOSE badCurse;
EXCEPTION
  WHEN INVALID_CURSOR THEN
    dbms_output.put_line('Bad cursor exception caught!');
  WHEN ZERO_DIVIDE THEN
    dbms_output.put_line('Divide by zero exception caught!');
END;
/

BEGIN
  exceptionExample; --Check View>dbms output for caught exception!
END;
/

CREATE OR REPLACE FUNCTION returnCursor
return SYS_REFCURSOR
is
  CURSOR cursorVar is
    select * from flash_cards;
begin
  return cursorVar;
end;


