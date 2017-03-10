-- 2.1 SELECT
-- select all records from the employee table
SELECT * FROM employee;
-- select all records from the employee table where the last name is King
SELECT * FROM employee WHERE lastname = 'King';
-- select all records from the employee table where the first name is andrew
-- and reportsto is NULL
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto IS null;

-- 2.2 ORDER BY
-- select all albums in album table and sort results set in descending order by title
SELECT * FROM album ORDER BY title desc;
-- select first name from customer and sort result set in ascending order by city
select firstname FROM customer ORDER BY city;

-- 2.3 INSERT INTO
-- insert two new records into genre table
INSERT INTO genre (genreid, name) VALUES (26, 'Space Pop');
INSERT INTO genre (genreid, name) VALUES (27, 'Galaxy Baroque');
SELECT * FROM genre;
-- insert two new records into employee table
INSERT INTO employee VALUES 
(9, 'Tang', 'Danni', 'Sales Associate', 2, '23-Nov-91', '27-Feb-17', '5015 Lakeland Rd', 
'College Park', 'MD', 'USA', '20740', '301-928-0181', '123-456-7890', 'danni@email.com');
INSERT INTO employee VALUES 
(10, 'Smith', 'Jane', 'Sales Associate', 2, '20-Aug-82', '01-Jan-16', '1234 Sunset Rd',
'Best City', 'AL', 'USA', '12345', '123-456-7890', '123-456-7890', 'janesmith@email.com');
-- insert two new records into customer table
INSERT INTO customer VALUES (60, 'Johnson', 'Dwayne', 'Hollywood', '1234 Sunset Blvd', 'Lalaland', 'WA',
'USA', '12345', '123-456-7890', null, 'dwayne@hollywood.com', 5);
INSERT INTO customer VALUES (61, 'Kent', 'Clark', 'Justice League', '10 Sun St', 'Smallville',
'Kansas', 'USA', '12345', null, null, 'imnotsuperman@jla.org', 3);

-- 2.4 UPDATE
-- update Aaron MitcheLl in Customer table to Robert Walter
 UPDATE customer
 SET firstname = 'Robert',
  lastname = 'Walter'
  WHERE customerid = 32;
-- update name of artist in the Arist table "Creedence Clearwater Revival" to "CCR"
UPDATE artist
  SET name = 'CCR'
  WHERE artistid = 76;

-- 2.5 LIKE
-- select all invoices with a billing address like "T%"
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

-- 2.6 BETWEEN
-- select all invoices that have a total between 15 and 50
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;
-- select all employees hired between 1st of june 2003 and 1st of march 2004
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

-- 2.7 DELETE
-- delete a record in customer table where the name is robert walter (there may be constraints that rely on this,
-- find out how to resolve them).
DELETE FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter';

-- -------------------------------------------------------------------------------

-- 3.0 SQL FUNCTIONS

-- 3.1 SYSTEM DEFINED FUNCTIONS
-- create a function that returns the current time
CREATE OR REPLACE FUNCTION getTime
RETURN timestamp
IS systime timestamp;
BEGIN
  SELECT CURRENT_TIMESTAMP
    INTO systime
    FROM dual;
  RETURN systime;
END getTime;
/

SELECT getTime FROM dual;

-- create a function that returns the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION getLength(mediaID IN number)
RETURN number
IS mediaLength number(10);
 mediaStr varchar2(4000);
BEGIN
SELECT name INTO mediaStr FROM mediatype WHERE MEDIATYPEID = mediaID;
mediaLength := LENGTH(mediaStr);
RETURN mediaLength;
END getLength;

SELECT * FROM mediatype;
SELECT getLength(4) FROM dual;

-- 3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS
-- create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION invoiceAvg
RETURN binary_double
IS invoice_avg binary_double;
BEGIN
SELECT AVG(total) INTO invoice_avg FROM invoice;
RETURN invoice_avg;
END invoiceAvg;

SELECT invoiceAvg FROM dual;

-- create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION mostExp
RETURN number
IS trackPrice Number(10,2);
BEGIN
  SELECT MAX(unitprice) INTO trackPrice FROM track;
RETURN trackPrice;
END mostExp;

SELECT mostExp FROM dual;

-- 3.3 USER DEFINED SCALAR FUNCTIONS
-- create a function that returns the average price of invoiceline items in the invoiceline table

SELECT * FROM invoiceline;

-- 3.4 USER DEFINED TABLE VALUED FUNCTIONS
CREATE OR REPLACE TYPE bornTable AS TABLE OF EMPLOYEE;

CREATE OR REPLACE FUNCTION bornAfter1986(emp_id IN number)
RETURN bornTable;
IS 
BEGIN

SELECT birthdate FROM EMPLOYEE
RETURN
END;

SELECT * FROM employee;


------------------------------------------------------------
-- 6.0 TRIGGERS

-- 6.1 AFTER/FOR
-- create an after insert trigger on the employee table fired after a new record 
-- is insert into the table

CREATE OR REPLACE TRIGGER record_after_insert AFTER INSERT
  ON employee
  FOR EACH ROW

BEGIN
DBMS_OUTPUT.PUT_LINE('Record inserted into table.');
END;
/

-- testing, should output to console 'Record inserted into table.'
INSERT INTO employee VALUES(11, 'Smith', 'Jane', 'Sales Associate', 2, '20-Aug-82', '01-Jan-16', '1234 Sunset Rd',
'Best City', 'AL', 'USA', '12345', '123-456-7890', '123-456-7890', 'janesmith@email.com');

-- create an after update trigger on the album table that fires after a row is inserted
-- into the table

CREATE OR REPLACE TRIGGER album_after_insert AFTER UPDATE
  ON album
  FOR EACH ROW
  
BEGIN
DBMS_OUTPUT.PUT_LINE('Record updated for album table');
END;
/

-- create an after delete trigger on the customer table that fires after a row is
-- deleted from the table

CREATE OR REPLACE TRIGGER customer_after_delete AFTER DELETE
  ON customer
  FOR EACH ROW
  
BEGIN
DBMS_OUTPUT.PUT_LINE('Customer record deleted from table.');
END;
/


-----------------------------------------------------------------

-- 7.0 JOINS

-- 7.1 INNER
-- create an inner join that joins customers and orders and specifies the name of the customer and the invoiceid

SELECT customer.firstname AS "Customer Name", invoice.invoiceid AS "Invoice ID"
FROM customer
INNER JOIN invoice ON customer.customerid = invoice.customerid;

-- 7.2 OUTER
-- create an outer join that joins the customer and invoice table, specifying the customerid, firstname, lastname,
-- invoice id, and total

SELECT cus.firstname AS "First Name", cus.lastname AS "Last Name", cus.customerid AS "Customer ID",
inv.invoiceid AS "Invoice ID", inv.total AS "Invoice Total"
FROM customer cus
FULL OUTER JOIN invoice inv ON cus.customerid = inv.customerid;

-- 7.3 RIGHT
SELECT art.name AS "Artist", al.title AS "Album Title"
FROM album al
RIGHT JOIN artist art ON al.artistid = art.artistid;

-- 7.4 CROSS

SELECT * FROM album CROSS JOIN artist ORDER BY artist.name;

-- 7.5 SELF

SELECT a1.firstname AS "Emp First", a1.lastname AS "Emp Last", b1.firstname AS "Supervisor First",
b1.lastname AS "Supervisor Last"
FROM employee a1, employee b1
WHERE b1.employeeid = a1.reportsto;

select * from employee;


--------------------------------------------------------------------------

-- 9.0 ADMINISTRATION

-- create a .bak file for the chinook database

BACKUP DATABASE chinook TO DISK='C:\Users\Danni Tang\Documents\Repos\ClassExamples\SQLWeek\chinook.bak';