--2.0 SQL QUERIES
/*
2.1 SELECT
*/
--2.1.1--
SELECT *
FROM EMPLOYEE;

--2.1.2
SELECT *
FROM EMPLOYEE
WHERE LASTNAME = 'King';
--2.1.3
SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' and REPORTSTO is NULL;

/*
2.2 ORDER BY
*/
--2.2.1
SELECT *
FROM ALBUM
ORDER BY TITLE DESC;
--2.2.2
SELECT FIRSTNAME
FROM CUSTOMER 
ORDER BY CITY ASC;

/*
2.3 INSERT INTO
*/
--2.3.1
INSERT INTO GENRE(GENREID, NAME)
VALUES (26, 'Donkey Noises');
--2.3.2
INSERT INTO GENRE(GENREID, NAME)
VALUES (27, 'Jons Guitar Solos');
--2.3.3
INSERT INTO EMPLOYEE(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
VALUES (9, 'Webster', 'Ben', 'CEO', NULL, '07-OCT-93', '2-FEB-17', '8022 Mermaid Cir', 'Huntington Beach', 'CA', 'United States', 'T1H 43J', '+1 (714) 642 1889', '+1 (714) 642 1889', 'bmwebster@wisc.edu');
--2.3.4
INSERT INTO EMPLOYEE(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
VALUES (10, 'Lee', 'Jon', 'Janitor', 7, '10-JUN-93', '2-FEB-17', '2121 RavenTower Ct', 'Herndon', 'VA', 'United States', 'T1H 43H', '+1 (653) 321 3234', '+1 (653) 321 3224', 'jonsemail@gmail.com');
--2.3.5
INSERT INTO CUSTOMER(CUSTOMERID, LASTNAME, FIRSTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES (60, 'Webster', 'Jeff', NULL, '2122 raventower ct', 'Herndon', 'VA', 'United States', '34532', '+1 (714) 642 1889', NULL, 'bmwebster@wisc.com', 5);
--2.3.6
INSERT INTO CUSTOMER(CUSTOMERID, LASTNAME, FIRSTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES (61, 'Lee', 'Johnny', NULL, '2122 raventower ct', 'Herndon', 'VA', 'United States', '53715', '+1 (653) 321 3234', NULL, 'jonsemail@gmail.com', 5);

/*
2.4 UPDATE
*/
--2.4.1
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
Where FIRSTNAME = 'Aaron'
AND LASTNAME = 'Mitchell';
--2.4.2
UPDATE ARTIST
SET NAME = 'CCR'
Where NAME = 'Creedence Clearwater Revival';

/*
2.5 LIKE
*/
--2.5.1
SELECT *
FROM INVOICE
WHERE BILLINGADDRESS like 'T%';

/*
2.6 BETWEEN
*/
--2.6.1
SELECT *
FROM INVOICE
WHERE TOTAL > 15 
AND TOTAL < 50;
--2.6.2
SELECT *
FROM EMPLOYEE
WHERE HIREDATE < '01-MAR-04'
AND HIREDATE >'06-JUN-03';

/*
2.7 DELETE
*/
ALTER TABLE INVOICELINE
DROP CONSTRAINT FK_INVOICELINEINVOICEID;
ALTER TABLE INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;
DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' 
AND LASTNAME = 'Walter';

--3.0 SQL FUNCTIONS
/*
3.1 SYSTEM DEFINED FUNCTIONS
*/
--3.1.1
/
CREATE OR REPLACE FUNCTION getCurrentTime
RETURN TIMESTAMP
IS
  theTime TIMESTAMP;
BEGIN 
  SELECT SYSTIMESTAMP INTO theTime FROM DUAL;
  return theTime;
END getCurrentTime;
/
DECLARE
  timenow TIMESTAMP(9);
BEGIN
  timenow := getCurrentTime();
  dbms_output.put_line(timenow);
END;
/*
--3.1.2
*/
CREATE OR REPLACE FUNCTION GETMEDIATYPELENGTH(media IN NUMBER)
RETURN number
IS
  MEDIA_LENGTH number(30);
BEGIN
  SELECT LENGTH(NAME) INTO MEDIA_LENGTH FROM MEDIATYPE 
  WHERE MEDIATYPEID = media;
  return MEDIA_LENGTH;
END GETMEDIATYPELENGTH;
/
DECLARE
  medialength number(30);
BEGIN
  medialength := GETMEDIATYPELENGTH(1);
  dbms_output.put_line(medialength);
END;
/
/*
3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS
*/
--3.2.1
/
CREATE OR REPLACE FUNCTION averageInvoice
RETURN number
IS
  average number(10);
BEGIN
  SELECT AVG(TOTAL) INTO AVERAGE FROM INVOICE;
  RETURN average;
END averageInvoice;
/
DECLARE
  average number(10);
BEGIN
  average := averageInvoice();
  dbms_output.put_line(average);
END;
/
--3.2.2
/
CREATE OR REPLACE FUNCTION mostExpensiveTrack
return number
IS
  maxPrice number(10);
BEGIN
  SELECT MAX(unitprice) INTO maxPrice FROM Track;
  return maxPrice;
END mostExpensiveTrack;
/
DECLARE
  maxPrice number(10);
BEGIN
  maxprice := mostExpensiveTrack;
  dbms_output.put_line(maxPrice);
END;
/
/*
3.3 USER DEFINED SCALAR FUNCTIONS
*/
/
CREATE OR REPLACE FUNCTION averageInvoiceLinePrice
return number
IS
  average number(10);
BEGIN
  SELECT AVG(unitprice) INTO average FROM invoiceline;
  return average;
END averageInvoiceLinePrice;
/
DECLARE 
  average number(10);
BEGIN
  average := AVERAGEINVOICELINEPRICE();
  dbms_output.put_line(average);
END;
/
/*
3.4 USER DEFINED TABLE VALUED FUNCTIONS
*/
CREATE OR REPLACE FUNCTION employeesBornAfter1968(outCursor OUT SYS_REFCURSOR)
return SYS_REFCURSOR
IS
BEGIN 
  OPEN outCursor for
  SELECt firstname, lastname 
  FROM EMPLOYEE 
  WHERE TO_DATE(EMPLOYEE.BIRTHDATE, 'DD/MON/YY') > TO_DATE('31-DEC-67', 'DD/MON/YY');
  return outCursor;
END employeesBornAfter1968;
/
DECLARE
  cursorVar SYS_REFCURSOR;
  fn employee.firstname%type;
  ln employee.lastname%type;
BEGIN
  cursorVar := employeesBornAfter1968(cursorVar);
  LOOP
    FETCH cursorVar INTO fn, ln;
    EXIT WHEN cursorVar%NOTFOUND;
    dbms_output.put_line('| ' || fn || ' | ' || ln || ' | ');
  END LOOP;
END;
/
--4.0 STORED PROCEDURES
/*
4.1 BASIC STORED PROCEDURES
*/
/
CREATE OR REPLACE PROCEDURE firstAndLastNames(outCursor OUT SYS_REFCURSOR)
IS
BEGIN 
  OPEN outCursor FOR
  SELECT firstname, lastname FROM employee;
END;
/
DECLARE
  cursorVar SYS_REFCURSOR;
  fn employee.firstname%type;
  ln employee.lastname%type;
BEGIN
  firstAndLastNames(cursorVar);
  LOOP
    FETCH cursorVar INTO fn, ln;
    EXIT WHEN cursorVar%NOTFOUND;
    dbms_output.put_line('| ' || fn || ' | ' || ln || ' | ');
  END LOOP;
END;
/
/*
4.2 STORED PROCEDURE INPUT PARAMETERS
*/
--4.2.1
CREATE OR REPLACE PROCEDURE updatePersonalInformation(empId IN number, )
IS 
BEGIN
  UPDATE employee 
  SET FIRSTNAME = 'Bobbert'
  WHERE employee.employeeid = empid;
END;
/
DECLARE
BEGIN
  updatePersonalInformation(2);
END;
/
--4.2.2
CREATE OR REPLACE PROCEDURE returnManagers(empId IN number, outCursor OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN outCursor FOR
  SELECT firstName, lastname  FROM employee
  WHERE employeeId = 
  (select reportsto from employee where employeeid = empId);
END;
/
declare
    cursorVar sys_refcursor;
    firstName employee.firstname%type;
    lastName employee.lastname%type;
begin
    returnManagers(2, cursorVar);
    loop
        fetch cursorVar into firstname, lastname;
        exit when cursorVar%notfound;
        dbms_output.put_line('|' || firstname || '|' || lastname || '|');
    end loop;
end;
/
/*
4.3 STORED PROCEDURE OUTPUT PARAMETERS
*/
CREATE OR REPLACE PROCEDURE nameAndCompany(custId IN number, outCursor OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN outCursor FOR
  SELECT firstname, lastname, company FROM customer
  WHERE customerid = custId;
END;
/
DECLARE
  cursorVar SYS_REFCURSOR;
  fn customer.firstname%type;
  ln customer.lastname%type;
  c customer.company%type;
BEGIN
  nameAndCompany(2, cursorVar);
  LOOP
    FETCH cursorVar INTO fn, ln, c;
    EXIT WHEN cursorVar%NOTFOUND;
    dbms_output.put_line('| ' || fn || ' | ' || ln || ' | ' || c || ' | ');
  END LOOP;
END;
/
/*
5.0 TRANSACTIONS
*/
--5.0.1
CREATE OR REPLACE PROCEDURE deleteInvoiceID(invID IN number)
IS
BEGIN
-- resolve restraints from child table first
  DELETE FROM invoiceline WHERE invoiceid IN
    (SELECT invoiceid FROM invoiceline WHERE invoiceid = invID);
-- then delete what you want from parent table
  DELETE FROM invoice WHERE invoiceid = invID;
COMMIT;
END deleteInvoiceID;
/
DECLARE
BEGIN
  deleteInvoiceID(1);
END;
/
--5.0.2
CREATE OR REPLACE PROCEDURE insertCustomer
IS 
BEGIN
  INSERT INTO CUSTOMER
  VALUES (62, 'web', 'ben', NULL, '2322 raventower ct', 'Herndon', 'VA', 'United States', '53715', '+1 (653) 321 3234', NULL, 'sdafasd@gmail.com', 5);
COMMIT;
END insertCustomer;
/
BEGIN
  insertCustomer();
END;
/*
6.0 TRIGGERS
*/
/*
6.1 AFTER/FOR
*/
--6.1.1
CREATE OR REPLACE TRIGGER employee_trigger
  AFTER INSERT ON employee
  BEGIN
    dbms_output.put_line('Employee Added');
  END;
/
--6.1.2
CREATE OR REPLACE TRIGGER album_trigger
  AFTER UPDATE ON album
  BEGIN
    dbms_output.put_line('Album updated');
  END;
/
--6.1.3
CREATE OR REPLACE TRIGGER customer_trigger
  AFTER DELETE ON customer
  BEGIN
    dbms_output.put_line('Customer deleted');
  END;
/
/*
7.0 JOINS
*/
/*
7.1 INNER
*/
SELECT CUSTOMER.FIRSTNAME, INVOICE.INVOICEID 
FROM CUSTOMER 
INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
/
/*
7.2 OUTER
*/
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER 
LEFT OUTER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
/*
7.3 RIGHT 
*/
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ARTIST 
RIGHT JOIN ALBUM
ON ARTIST.ARTISTID = ALBUM.ARTISTID;
/*
7.4 CROSS
*/
SELECT *
FROM ARTIST
CROSS JOIN ALBUM
ORDER BY ARTIST.NAME ASC;
/*
7.5 SELF
*/
SELECT emp1.firstname, emp1.lastname
FROM EMPLOYEE emp1, EMPLOYEE emp2
WHERE emp1.EMPLOYEEID = emp2.REPORTSTO; 
/*
9.0 ADMINISTRATION
*/
--Saved