/*******************************************************************************
   Chinook Database 
   Script: Chinook_Oracle.sql
   Description: SQL LAB HOMEWORK
   Author: Marco Tobon
********************************************************************************/

/*==============================================================================
SECTION 2.1
SELECT
===============================================================================*/


--SELECT ALL RECORDS FROM THE EMPLOYEE TABLE
--==============================================================================
SELECT * FROM EMPLOYEE;
/
--SELECT ALL RECORDS FROM EMPLOYEE WHERE LAST NAME IS King
--===============================================================================*/
SELECT * FROM EMPLOYEE WHERE LASTNAME='King';
/
--SELECT ALL RECORDS FROM EMPLOYEE WHERE FIRSSTNAME IS Andrew
--    AND REPORTSTO IS NUL
--===============================================================================*/
SELECT * FROM EMPLOYEE WHERE FIRSTNAME='Andrew' AND REPORTSTO IS NULL;


/*==============================================================================
SECTION 2.2
ORDER BY
===============================================================================*/


--SELECT ALL ALBUMS IN ALBUM TABLE AND SORT RESULT SET IN DESCENDING ORDER BY 
--      TITTLE
--===============================================================================*/
SELECT * FROM ALBUM
    ORDER BY TITLE;
/
--SELECT FIRST NAME FROM CUSTOMER AND SORT RESULT SET IN ASCENDING ORDER BY CITY 
--===============================================================================*/
SELECT FIRSTNAME FROM CUSTOMER 
    ORDER BY CITY;
/


/*==============================================================================
SECTION 2.3
INSERT INTO
===============================================================================*/


-- INSERT TWO NEW RECORDS INTO GENRE TABLE
--===============================================================================*/
INSERT INTO GENRE (NAME) VALUES ('Kids');
INSERT INTO GENRE (NAME) VALUES ('Testing');
/
-- INSERT TWO NEW RECORDS INTO EMPLOYEE TABLE
--===============================================================================*/
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
        VALUES (9, 'Adams', 'Andrew', 'General Manager', TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), 
        TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Jasper Ave NW', 'Edmonton', 'AB', 'Canada', 
        'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'andrew@chinookcorp.com');
/

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
        VALUES (10, 'Adams', 'Andrew', 'General Manager', TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), 
        TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Jasper Ave NW', 'Edmonton', 'AB', 'Canada', 
        'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'andrew@chinookcorp.com');
/

-- INSERT TWO NEW RECORDS INTO CUSTOMER TABLE
--===============================================================================*/
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) 
        VALUES (60, 'Luís', 'Gonçalves', 'Embraer - Empresa Brasileira de Aeronáutica S.A.', 'Av. Brigadeiro Faria Lima, 2170',
        'São José dos Campos', 'SP', 'Brazil', '12227-000', '+55 (12) 3923-5555', '+55 (12) 3923-5566', 'luisg@embraer.com.br', 3);
/    
    
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) 
        VALUES (61, 'Luís', 'Gonçalves', 'Embraer - Empresa Brasileira de Aeronáutica S.A.', 'Av. Brigadeiro Faria Lima, 2170',
        'São José dos Campos', 'SP', 'Brazil', '12227-000', '+55 (12) 3923-5555', '+55 (12) 3923-5566', 'luisg@embraer.com.br', 3);
/

/*==============================================================================
SECTION 2.4
INSERT INTO
===============================================================================*/


--UPDATE Aaron Mitchell IN CUSTOMER TO Robert Walter
--===============================================================================*/
UPDATE CUSTOMER C
   SET C.FirstName = 'Robert', C.LastName = 'Walter'
   WHERE C.FirstName = 'Aaron' AND C.LastName = 'Mitchell';
/

--UPDATE NAME OF ARTIST IN THE ARTIST TABLE "Creedence Clearwater Revival" to "CCR"
--===============================================================================*/
UPDATE ARTIST A
   SET A.NAME = 'CCR' 
   WHERE A.NAME = 'Creedence Clearwater Revival';
/


/*==============================================================================
SECTION 2.5
LIKE
===============================================================================*/

--SELECT ALL INVOICES WITH BILLING ADDRESS LIKE "T%"
--===============================================================================*/
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
/

/*==============================================================================
SECTION 2.6
BETWEEN
===============================================================================*/

--SELECT ALL INVOICES THAT HAVE A TOTAL BETWEEN 15 AND 50
--===============================================================================*/
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
/

--SELECT ALL EMPLOYEES HIRED BETWEEN 1st OF JUNE 2003 AND 1ST ON MARCH 2004
--===============================================================================*/
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN TO_DATE('2003/06/01', 'yyyy/mm/dd') AND TO_DATE('2004/03/01', 'yyyy/mm/dd');
/

/*==============================================================================
SECTION 2.7
DELETE
===============================================================================*/

--DELETE A RECORD IN CUSTOMER TABLE WHERE THE NAME IS ROBERT WALTER (There may be 
    --constraitns that rely on this,find out how to resolve them).
--===============================================================================*/
ALTER TABLE INVOICE
    DROP CONSTRAINT FK_invoicecustomerid;
/

DELETE FROM CUSTOMER WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';
/


/*==============================================================================
SECTION 3.0 SQL Functions
===============================================================================*/
/*==============================================================================
SECTION 3.1 
SYSTEM DEFINED FUNCTIONS
===============================================================================*/

--CREATE A FUNCTION THAT RETURNS THE CURRENT TIME
--==============================================================================
CREATE OR REPLACE FUNCTION t_func
RETURN TIMESTAMP
IS    
    BEGIN
        RETURN LOCALTIMESTAMP();
    END t_func;
/

BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE(t_func());
END;
/


--CREATE A FUNCTION TAHT RETURNS THE LENGTH OF A MEDIATYPE  FROM THE MEDIATYPE TABLE
--==============================================================================
CREATE OR REPLACE FUNCTION mediafunc (types IN VARCHAR2)
RETURN NUMBER
IS
    BEGIN
        RETURN LENGTH(types); 
    END mediafunc;
/
DECLARE 
    TEST VARCHAR2(120);
BEGIN
    SELECT ME.name INTO TEST FROM MEDIATYPE ME WHERE MEDIATYPEID = 1;
    SYS.DBMS_OUTPUT.PUT_LINE(mediafunc(TEST));
END;
 /   


/*==============================================================================
SECTION 3.2 
SYSTEM DEFINED AGGREGATE FUNCTIONS
===============================================================================*/

--CREATE A FUNCTION THAT RETURNS THE AVERAGE TOTAL OF ALL INVOICES
--==============================================================================
CREATE OR REPLACE FUNCTION avg_inv
RETURN NUMBER
IS
    avg_total DECIMAL(10,2);
    BEGIN
        SELECT AVG(TOTAL) INTO avg_total FROM INVOICE;
        RETURN avg_total; 
    END avg_inv;
/

BEGIN
    SYS.DBMS_OUTPUT.PUT_LINE(avg_inv());
END;
/


--CREATE A FUNCTION THAT RETURNS THE MOST EXPENSIVE TRACK
--==============================================================================
CREATE OR REPLACE FUNCTION ex_track
RETURN NUMBER
IS
    max_price DECIMAL(10,2);
    BEGIN
        SELECT MAX(UNITPRICE) INTO max_price FROM TRACK;
        RETURN max_price; 
    END ex_track;
/

BEGIN
    SYS.DBMS_OUTPUT.PUT_LINE(ex_track());
END;
/

/*==============================================================================
SECTION 3.3
USER DEFINED SCALAR FUNCTIONS
===============================================================================*/

--CREATE A FUNCTION THAT RETURNS THE AVERAGE PRICE OF INVOICELINE ITEMS IN THE INVOICE INVOUCELINE TABLE
--===============================================================================*/
CREATE OR REPLACE FUNCTION avg_inv_item
RETURN NUMBER
IS
    avg_price DECIMAL(8,2);
    the_price NUMBER;
    BEGIN
        SELECT SUM(UNITPRICE) INTO avg_price FROM INVOICELINE;
        SELECT COUNT(*) INTO the_price FROM INVOICELINE;
        RETURN avg_price/the_price;
    END avg_inv_item;
/

BEGIN
    SYS.DBMS_OUTPUT.PUT_LINE(avg_inv_item());
END;
/

/*==============================================================================
SECTION 3.4
USER DEFINED TABLE VALUED FUNCTIONS
===============================================================================*/

--CREATE A FUNCTION THAT RETURNS ALL EMPLOYEES WHO ARE BORN AFTER 1986
--==============================================================================

CREATE OR REPLACE FUNCTION getAfterYearCursor 
RETURN SYS_REFCURSOR
IS
    emplo_curs SYS_REFCURSOR;
BEGIN
    OPEN emplo_curs FOR
        SELECT E.FIRSTNAME, E.LASTNAME FROM Employee E WHERE BIRTHDATE >  TO_DATE('1968/12/31', 'yyyy/mm/dd');
    RETURN emplo_curs;
END getAfterYearCursor;
/

DECLARE
    empl_cur SYS_REFCURSOR;
    lastna EMPLOYEE.LASTNAME%TYPE;
    firstna EMPLOYEE.FIRSTNAME%TYPE;

BEGIN
  empl_cur := getAfterYearCursor();
  LOOP
    FETCH empl_cur INTO lastna, firstna;
    EXIT WHEN empl_cur%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || lastna  || '|' || firstna );
  END LOOP;
END;
/



/*==============================================================================
SECTION 4.0 Stored Procedures
===============================================================================*/
/*==============================================================================
SECTION 4.1
BASIC STORED PROCEDURES
===============================================================================*/

--CREATE A STORED PROCEDURE THAT SELECTS THE FIRST AND ALST NAMES OF ALL THE EMPLOYEES
--==============================================================================
CREATE OR REPLACE PROCEDURE choose_emp (chooser OUT SYS_REFCURSOR)
IS
    BEGIN
        OPEN chooser FOR
            SELECT E.FIRSTNAME , E.LASTNAME FROM EMPLOYEE E;

    END choose_emp;
/

DECLARE
    current_e SYS_REFCURSOR;
    firstna EMPLOYEE.FIRSTNAME%TYPE;
    lastna EMPLOYEE.LASTNAME%TYPE;
BEGIN
    choose_emp(current_e);
    LOOP
        FETCH current_e INTO firstna, lastna;
        EXIT WHEN current_e%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('|' || firstna || '|' || lastna );
    END LOOP;
END;
/
   

/*==============================================================================
SECTION 4.2
STORED PRCEDURE INPUT PARAMETERS
===============================================================================*/

--CREATE A STORED PROCEDURE THAT UPDATES THE PERSONAL INFORMATION OF AN EMPLOYEE
--==============================================================================
CREATE OR REPLACE PROCEDURE update_em (usid IN NUMBER, fname IN VARCHAR2, lname IN VARCHAR2)
IS
    BEGIN
        UPDATE EMPLOYEE SET FIRSTNAME=fname,
                            LASTNAME=lname
                        WHERE EMPLOYEEID=usid;
    END;
/

BEGIN
    update_em(9, 'Lee', 'Sin');
END;
/

--CREATE A STORED PROCEDURE THAT RETURNS THE MANAGERS OF AN EMPLOYEE
--==============================================================================
CREATE OR REPLACE PROCEDURE man_of_em (emp_id IN NUMBER, emp_man OUT NUMBER)
IS 
    BEGIN
        SELECT REPORTSTO INTO emp_man FROM EMPLOYEE WHERE EMPLOYEEID = emp_id;
    END;
/

DECLARE 
    emid NUMBER(10);
    ename VARCHAR2(120);
    manname VARCHAR2(120);
BEGIN
    man_of_em (2, emid);
    SELECT FIRSTNAME INTO ename FROM EMPLOYEE WHERE EMPLOYEEID=2;
    SELECT FIRSTNAME INTO manname FROM EMPLOYEE WHERE EMPLOYEEID=emid;
    DBMS_OUTPUT.PUT_LINE('THE MANAGER FOR ' || ename || ' IS ' || manname);
END;
/
        
/*==============================================================================
SECTION 4.3
STORED PRCEDURE OUTPUT PARAMETERS
===============================================================================*/

--CREATE A STORED PROCEDURE THAT RETURNS THE NAME AND COMPANY OF A CUSTOMER
--==============================================================================
CREATE OR REPLACE PROCEDURE name_comp ( em1 IN NUMBER, emname OUT VARCHAR, comname OUT VARCHAR)
IS 
    BEGIN
        SELECT E.FIRSTNAME, E.company INTO emname, comname FROM Company E WHERE EMPLOYEEID = em1;
    END;
/

DECLARE
    emname VARCHAR(120);
    comname VARCHAR(120);
BEGIN
    name_comp(2, emname, comname);
    DBMS_OUTPUT.PUT_LINE(emname || ' WORKS IN ' || comname);
END;
/


/*==============================================================================
SECTION 5.0 Transactions 
===============================================================================*/

--CREATE A TRANSACTION THAT GIVEN AN IVOICEID WILL DELETE THT INVOICE
-- There may be constraints that rely on this, find out how to resolve them
--===============================================================================*/
SET TRANSACTION NAME 'del_tran_name';
    DELETE FROM INVOICELINE WHERE INVOICEID = '412';
    DELETE FROM INVOICE WHERE INVOICEID = '412';
COMMIT;
/

--CREATE A TRANSACTION NESTED WITHIN A STORED PROCEDURE THAT INSERTS A NEW RECORD 
    --IN THE CUSTOMER TABLE
--===============================================================================
CREATE OR REPLACE PROCEDURE nest_trans
IS
    BEGIN
        SET TRANSACTION NAME 'nested_trans';
            INSERT INTO CUSTOMER (61, 'TEST', 'TESTNAME');
        COMMIT;
    END;

/*==============================================================================
SECTION 6.0 Triggers 
===============================================================================*/
/*==============================================================================
SECTION 6.1 
AFTER/FOR 
===============================================================================*/

--CREATE AN AFTERS INSERT TRIGGER ON THE EMPLOYEE TABLE FIRED AFTER A NEW RECORD 
    --IS INSERTED INTO THE TABLE
--==============================================================================
CREATE OR REPLACE TRIGGER emp_trigger
  AFTER INSERT ON EMPLOYEE
  FOR EACH ROW
  BEGIN
    DBMS_OUTPUT.PUT_LINE('NEW RECORD');
    --INSERT INTO EMPLOYEE 
  END;
/


--CREATE AN AFTER UPDATE TRIGGER ON THE ALBUM TABLE THAT FIRES AFTER A ROW IS 
    --INSERTED IN THE TABLE
--============================================================================
CREATE OR REPLACE TRIGGER alb_trigger
  AFTER INSERT ON ALBUM
  FOR EACH ROW
  BEGIN
    DBMS_OUTPUT.PUT_LINE('ROW INSERTED');
  END;
/

--CREATE AN AFTER DELETE TRIGGER ON THE CUSTOMER TABLE THAT FIRES AFTER A ROW IS
    --DELETED FROM THE TABLE
--===============================================================================
CREATE OR REPLACE TRIGGER cust_trigger
    AFTER DELETE ON CUSTOMER
    FOR EACH ROW
    BEGIN
        DBMS_OUTPUT.PUT_LINE ('ROW DELETTED');
    END;


/*==============================================================================
SECTION 7.0 Joins 
===============================================================================*/
/*==============================================================================
SECTION 7.1 
INNER 
===============================================================================*/

--CREATE AN INNER JOIN THAT JOINS CUSTOMERS AND ORDERS AND SPECIFIES THE NAME OF
    --THE CUSTOMER AND THE INVOICEID
--===============================================================================
SELECT C.FIRSTNAME, C.LASTNAME, I.INVOICEID FROM CUSTOMER C
    INNER JOIN  INVOICE I
        ON C.CUSTOMERID = I.CUSTOMERID;
/

/*==============================================================================
SECTION 7.2 
OUTER 
===============================================================================*/

--CREATE AN OUTER JOIN THAT JOINS THE CUSTOMER AND INVOICE TABLE, SPECIFYING
    --THE CustomerID, Firstname, Lastname, invoiceId, and total
--==============================================================================
SELECT C.customerid, C.firstname, C.lastname, I.invoiceid, I.total FROM
    CUSTOMER C OUTER JOIN INVOICE I
        ON C.CUSTOMERID = I.CUSTOMERID;
/

/*==============================================================================
SECTION 7.3 
RIGHT 
===============================================================================*/

--CREATE A RIGHT JOIN THAT JOINS ALBUM AND ARTIST SPECIFYING NAME AND TITLE
--==============================================================================
SELECT Ar.name, Al,title FROM Artist Ar RIGHT JOIN Album Al 
    ON Ar.artistid = Al.artistid;
/

/*==============================================================================
SECTION 7.4 
CROSS 
===============================================================================*/

--CREATE A CROSS JOIN THAT JOINS ALBUM AND ARTIST AND SORTS BY ARTIST NAME IN 
    --ASCENDING ORDER
--==============================================================================
SELECT AR.NAME FROM ARTIST AR CROSS JOIN ALBUM
    ORDER BY AR.NAME ASC;


/*==============================================================================
SECTION 7.5 
SELF 
===============================================================================*/

--PERFORM A SELF-JOIN ON THE EMPLOYEE TABLE, JOINING ON THE REPORTSTO COLUMN
--==============================================================================
SELECT E1.FIRSTNAME, E2.FIRSTNAME FROM EMPLOYEE E1, EMPLOYEE E2 
    WHERE E1.REPORTSTO = E2.EMPLOYEEID;


/*==============================================================================
SECTION 9.0 Administration 
===============================================================================*/

--CREATE A .BAK FILE FOR THE CHINOOK DATABASE
--==============================================================================
--
--C:\oraclexe\app\oracle\fast_recovery_area\XE\BACKUPSET\2017_03_10