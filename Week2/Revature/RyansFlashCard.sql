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

SELECT * FROM flash_cards;
/

CREATE OR REPLACE PROCEDURE procTest
IS 
BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE('Console output example');
END procTest;
/

CALL procTest();
/

CREATE OR REPLACE PROCEDURE insertIntoFlashCards(someId IN NUMBER, someQ IN VARCHAR2, someA IN VARCHAR2)
IS
BEGIN
  INSERT INTO flash_cards VALUES (someId, someQ, someA);
END insertIntoFlashCards;
/

CALL INSERTINTOFLASHCARDS (3, 'Will this work', 'yes?');
/

SELECT * FROM FLASH_CARDS;
/

CREATE OR REPLACE PROCEDURE getAnswer (question IN VARCHAR2, answer OUT VARCHAR2)
IS
BEGIN
  --WHATEVERR QUERY WE GET WILL GO INTO ASNWER
  SELECT f_answer INTO answer FROM flash_cards WHERE UPPER (f_question) = UPPER (question);
END getAnswer;
/

DECLARE 
  output varchar2(4000);
  inputVar varchar(4000);
BEGIN
  SELECT f_question INTO inputVar FROM flash_cards WHERE F_ID =2;
  
  GETANSWER(inputVar, output);
  DBMS_OUTPUT.PUT_LINE('Answer is: ' || output); --|| concatenatts strings
END;
/

--IN: data passed into the procedure thorugh IN variables. They are not received back from the call
--OUT: Returned out of the procdeure, does NOT take in data
--IN OUT : you WILL pass in data, and the data can be altered and returned
CREATE OR REPLACE PROCEDURE getFlashCardCursor(outcursor OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN outCursor FOR
    SELECT * FROM flash_cards;
END;
/

DECLARE
  cursorVar SYS_REFCURSOR;
  fcID FLASH_CARDS.F_ID%TYPE;
  fcQuestion FLASH_CARDS.F_QUESTION%TYPE;
  fcAnswer FLASH_CARDS.F_ANSWER%TYPE;
  
BEGIN
  getFlashCardCursor(cursorVar);
  LOOP
    FETCH cursorVar INTO fcID, fcQuestion, fcAnswer;
    EXIT WHEN cursorVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || fcID || '|' || fcQuestion || '|' || fcAnswer || '|');
  END LOOP;
END;
/*==============================================================================
                FUNCTIONS
==============================================================================*/
CREATE OR REPLACE FUNCTION getMaxID
RETURN NUMBER
IS
  theMax number(10);
  BEGIN
    SELECT MAX(f_id) INTO theMax FROM flash_cards;
    return theMax;
END getMaxID;

DECLARE 
  maximum NUMBER(10);
BEGIN
  maximum := getMaxID();
  SYS.DBMS_OUTPUT.PUT_LINE(maximum);
END;

CREATE OR REPLACE FUNCTION highNum( num1 IN NUMBER, num2 IN NUMBER, num3 IN NUMBER)
RETURN NUMBER
IS
  curMax NUMBER(10);
  BEGIN 
    curMax := num1;
    IF num2 > curMax THEN
      curMax := num2;
    END IF;
    IF num3 > curMax THEN
        curMax := num3;
    END IF;
    return curMax;
END highNum;

BEGIN
  DBMS_OUTPUT.PUT_LINE (highNum(10, 20, 5));
END;

