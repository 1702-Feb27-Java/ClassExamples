--######2.1.1########--
SELECT * FROM EMPLOYEE;
--######2.1.2########--
SELECT * FROM EMPLOYEE
WHERE lastname = 'king';
--######2.1.3########--
SELECT * FROM EMPLOYEE
WHERE firstname = 'Andrew' 
AND REPORTSTO = NULL;
--######2.2.1########--
SELECT * FROM ALBUM
ORDER BY DESC;
--######2.2.2########--
SELECT * FROM CUSTOMER
ORDER BY CITY ASC;
--######2.3.1########--
INSERT INTO GENRE
(GENREID, NAME)
VALUES(23, 'NU METAL');

INSERT INTO GENRE
(GENREID, NAME)
VALUES(185, 'MUSICSTUFF');
--######2.3.2########--
INSERT INTO EMPLOYEE
(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPOTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
VALUES(185, 'HILL', 'BOBBY', 'MASTER', 'JHFBCB', '1-MARCH 2000', 'TODAY', 'BLAH BLAH BLAH', 'LEWES', 'DE', 'US', '6161', '654987321', '6181881', 'KJDCB@HB.COM' );

INSERT INTO EMPLOYEE
(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPOTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
VALUES(900, 'SMITH', 'JON', 'PERSON', 'JHFBCB', '1-FEB-1987', 'YESTERDAY', 'BLAH BLAH BLAH', 'REHOBETH', 'PA', 'US', '6161', '654987321', '6181881', 'KJDCB@HB.COM' );
--######2.3.3########--
INSERT INTO CUSTOMER
(CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES(876, 'KEITH', 'MOON', 'THAT PLACE', '789 KJNDV', 'TOWN', 'MD', 'CANADA', '987321', '9876549871', '161', 'JFVNJEF@BLAH', '987654321');

INSERT INTO CUSTOMER
(CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES(321321, 'MARK', 'TED', 'THAT PLACE', '789 KJNDV', 'TOWN', 'MD', 'CANADA', '987321', '9876549871', '161', 'JFVNJEF@BLAH', '987654321');
--######2.4.1########--
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'  
WHERE FIRSTNAME = 'Aaron' AND  LASTNAME = 'Mitchell';
--######2.4.2########--
UPDATE ARTIST
SET NAME = 'CCR' --'Creedence Clearwater Revival' 
WHERE NAME = 'Creedence Clearwater Revival';
--######2.5.1########--
SELECT * FROM INVOICE
WHERE Billingaddress LIKE 'T%';
--######2.6.1########--
SELECT * FROM INVOICE 
WHERE TOTAL BETWEEN 15 AND  50 ;
--######2.6.2########--
SELECT * FROM EMPLOYEE 
WHERE HIREDATE BETWEEN '01-JUN-2003' AND  '01-MAR-2004';
----######2.7.1########--
ALTER TABLE invoice 
DROP CONSTRAINT FK_invoicecustomerid ;
DELETE FROM customer WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
--ON DELETE CASCADE
--SELECT * FROM INVOICE WHERE CUSTOMERID = 32;
--WHERE firstname = 'Robert' AND LASTNAME = 'Walter'


--DELETE FROM(
--SELECT * FROM CUSTOEMR JOIN INVOICE USING (FIRSTNAME, LASTNAME)
--WHERE FIRSTNAME = Robert AND LASTNAME = Walter)
--ON DELETE CASCADE;
--######3.1.1########--
CREATE OR REPLACE FUNCTION newtimestuff 
RETURN timestamp 
IS  
BEGIN 
   return localtimestamp(); 
END;
/
 BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE (newtimestuff());
END;
/
--select LOCALTIMESTAMP
--FROM dual;
--######3.1.2########--
CREATE OR REPLACE FUNCTION medialength (patata IN varchar2)
return NUMBER
IS
BEGIN
  RETURN LENGTH(PATATA);
END;
/
declare 
newvar VARCHAR2(120);
BEGIN
  SELECT MEDIATYPE.NAME INTO NEWVAR FROM MEDIATYPE 
  WHERE MEDIATYPEID = 1;
   SYS.DBMS_OUTPUT.PUT_LINE (MEDIALENGTH(NEWVAR)); 
END;
/
--######3.2.1########--
CREATE OR REPLACE FUNCTION newavg 
RETURN number 
IS  
DERP DECIMAL(10,2);
BEGIN 
 SELECT AVG(TOTAL) INTO DERP FROM INVOICE;
   return DERP; 
END;
/
 BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE (NEWAVG());
END;
/
--######3.2.2########--
CREATE OR REPLACE FUNCTION STUFFYSTUFF 
RETURN number 
IS  
YEA DECIMAL(10,2);
BEGIN 
 SELECT MAX(unitprice) INTO yea FROM track;
   return yea; 
END;
/
 BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE (stuffystuff());
END;
/
--######3.3.1########--
CREATE OR REPLACE FUNCTION thisisaname 
RETURN number 
IS  
that DECIMAL(10,2);
BEGIN 
 SELECT AVG(unitprice) INTO that FROM INVOICELINE;
   return that; 
END;
/
 BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE (thisisaname());
END;
/
--######3.4.1########--
CREATE OR REPLACE FUNCTION oldppl 
return date
is  
BEGIN 
 select firstname, lastname
  from employee
 where birthdate > date '1968-12-31'
 group by employeeid
   return birthdate; 
END;
/
 BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE (oldppl());
END;
/
--######3.4.1########--
--CREATE OR REPLACE procedure namestuff 
--IS  
--BEGIN 
-- SELECT firstname, lastname FROM employee
-- 
--  
--END;
--######4.1.1########--
drop procedure namestuff;

CREATE OR REPLACE PROCEDURE namestuff
IS 
  CURSOR CURSNAME 
  IS 
   SELECT firstname, lastname
   FROM employee;
      e_record cursname%rowtype;

BEGIN 
  FOR e_record IN cursname
  LOOP 
    dbms_output.put_line(E_RECORD.firstname || ' ' || e_record.lastname);
  END LOOP;
END;  

/
exec namestuff
/
--######4.2.1########--
drop procedure updateinfo;

CREATE OR REPLACE PROCEDURE updateinfo
IS 
  CURSOR cinfo 
  IS 
   update employee
   set firstname = 'frank', lastname = 'smith'
   where employeeid = 47;
     -- e_record cursname%rowtype;

BEGIN 
    dbms_output.put_line(cinfo);
END;  

/
exec namestuff
/ 
--######4.2.2########--
create OR REPLACE PROCEDURE managerof(
IS
  cursor c_man
  IS 
  SELECT REPORTSTO FROM EMPLOYEE;
  e_report c_man%rowtype;
  
BEGIN 
    dbms_output.put_line(managerof);
END;  
 
--######4.3.1########--
CREATE OR REPLACE PROCEDURE replacename
is
  cursor newname
  is 
  SELECT FIRSTNAME, LASTNAME, COMPANY FROM CUSTOMER;
  C_var newname%varchar;

/ 
BEGIN 
     dbms_output.put_line(REPLACENAME);
end;

--######5.1.1########--

--######5.1.2########--

--######6.1.1########--
CREATE TRIGGER after_in AFTER
  INSERT ON employees
    FOR EACH ROW
  BEGIN
    insert_row_proc;
    WHEN (INSERT ON EMPLOYEE);
  END;
--######6.1.2########--
CREATE TRIGGER after_UP AFTER
  UPDATE ON ALBUM
  BEGIN
    update_row_proc;
    WHEN (INSERT ON ALBUM);
  END;
--######6.1.3########--
CREATE TRIGGER after_de AFTER
  DELETE ON CUSTOMER
    FOR EACH ROW
  BEGIN
    delete_row_proc;
    WHEN (INSERT ON customer);
  END;
--######7.1.1########--
SELECT CUSTOMER.LASTNAME, customer.firstname, INVOICE.INVOICEID
FROM CUSTOMER 
INNER JOIN INVOICE 
ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;
--ORDER BY DESC;
--######7.2.1########--
SELECT * --CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, CUSTOMER.CUSTOMERID, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER 
FULL OUTER JOIN INVOICE
ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID
ORDER BY FIRSTNAME;
--######7.3.1########--
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ARTIST 
RIGHT JOIN ALBUM
ON ALBUM.ARTISTID = ARTIST.ARTISTID;
--ORDER BY FIRSTNAME;
--######7.4.1########--
SELECT *
FROM ARTIST 
CROSS JOIN ALBUM
--ON ALBUM.ARTISTID = ARTIST.ARTISTID
ORDER BY ARTIST.NAME;
--######7.5.1########--
SELECT *
FROM EMPLOYEE e1, EMPLOYEE e2
where e1.EMPLOYEEID = e2.reportsto;

--######9.0.1########--
