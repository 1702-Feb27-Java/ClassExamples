
/* 2.1 Select */
-- Select all records from employee table
SELECT *
FROM EMPLOYEE;

-- select all records from employee table where last name is king
SELECT *
FROM employee
WHERE lastname = 'King';

-- select all records from Employees table where first name is Andrew and REPORTS TO IS NULL
SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND
REPORTSTO = Null;

/*2.2 ORDER BY*/

-- Select all albums in Album tablee and sort the result set in descending order by title
SELECT *
FROM album
ORDER BY album.title DESC;

-- Select first name from Customer and sort result set in ascending order by city
SELECT firstname
FROM customer
ORDER BY city; /* ASC is default */

/*2.3 Insert Into*/

-- Insert two records into Genre
INSERT INTO Genre (GENREID, NAME)
VALUES (26, 'Light Rock');
INSERT INTO Genre (GENREID, NAME)
VALUES (27, 'Religious');

-- Insert two records into Employee
INSERT INTO Employee(EmployeeId, LastName, FirstName)
Values(10, 'Robbert', 'Rob');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
VALUES (11, 'Bobby', 'Bob', 'Sales Support Agent', 10, TO_DATE('1947-9-19 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2003-5-3 00:00:00','yyyy-mm-dd hh24:mi:ss'), '683 10 Street SW', 'Calgary', 'AB', 'Canada', 'T2P 5G3', '+1 (403) 263-4423', '+1 (403) 263-4289', 'margaret@chinookcorp.com');

-- Insert two records into Customer
INSERT INTO customer(CustomerId, FirstName, LastName, City, State, Phone, Email)
VALUES(95, 'Aaron', 'Camm', 'Columbus', 'OH', '+1 (614) 316-3078', 'Cammaaron@gmail.com');

INSERT INTO customer(CustomerId, FirstName, LastName, City, State, Email)
VALUES(96, 'Happy', 'Camm', 'Columbus', 'OH', 'Bark@woof.com');

/*2.4 Update */
-- Update Aaron Mitchel in Customer table to Robert Walter
UPDATE customer
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

--Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR"
UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

/*2.5 Like*/

SELECT *
FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

/* 2.6 Between */
-- Select all invoices that have a total between 15 and 50
SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 30;

-- Select all employees hired between 1st of June 2003 and 1st Match 2004
SELECT *
FROM employee
WHERE HIREDATE BETWEEN TO_DATE('2003-06-01 00:00:00','yyyy-mm-dd hh24:mi:ss') 
AND TO_DATE('2004-03-01 23:59:59','yyyy-mm-dd hh24:mi:ss');

/* 2.7 Delete */
--DELETES the invoiceline that are related to Customer through invoice table*/
DELETE
FROM INVOICELINE
WHERE INVOICEID IN 
(SELECT INVOICEID
FROM invoice
WHERE CUSTOMERID = (
SELECT CUSTOMERID
FROM customer
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
));

-- Deletes the customer's invoices*/
DELETE 
FROM invoice
WHERE CUSTOMERID = (
SELECT CUSTOMERID
FROM customer
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
);


-- Deletes the customer */
DELETE 
FROM customer
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

/* 3.0 SQL Functions */
/* 3.1 System Defined Functions */

-- Create a function that returns the current time
SELECT systimestamp 
FROM dual;


-- Create a function that returns the length of a mediatype from the mediatype table
SELECT name, LENGTH(name)
FROM mediatype;

/* 3.2 System Defined Aggregate Function*/
-- Create a function that returns the average total of all invoices
SELECT AVG(total)
FROM invoice;

-- Create a function that returns the most expensive track

SELECT *			   	-- gets multiple rows because several tracks are at the max price
FROM track
WHERE unitprice = 
(SELECT MAX(unitprice) 	-- gets the max price of any track
FROM track);

/* 3.3 Unser Defined Scalar Functions */
CREATE OR REPLACE FUNCTION ailp
RETURN NUMBER
IS
  theAvg Number(10,2);
BEGIN
  SELECT AVG(unitprice) INTO theAvg From invoiceline;
  RETURN theAvg;
END ailp;

/* 3.4 User Defined Table Valued Functions */
CREATE OR REPLACE FUNCTION employeesBornAfter1968
return SYS_REFCURSOR
IS
	oCursor SYS_REFCURSOR;
BEGIN 
  OPEN oCursor for
  SELECt firstname, lastname 
  FROM EMPLOYEE 
  WHERE TO_DATE(EMPLOYEE.BIRTHDATE, 'DD/MON/YY') > TO_DATE('31-DEC-67', 'DD/MON/YY');
  return oCursor;
END employeesBornAfter1968;


/* Stored Procedure */
--4.1

CREATE OR REPLACE PROCEDURE firstAndLastNames(outCursor OUT SYS_REFCURSOR)
IS
BEGIN 
  OPEN outCursor FOR
  SELECT firstname, lastname FROM employee;
END;

/* 4.2 Stored Procedure Input Parameters*/
-- create a stored procedure that updates the personal information of an employee
CREATE OR REPLACE PROCEDURE updateInfo (
  employeesID IN NUMBER
)
IS
BEGIN
  UPDATE employee
  SET FIRSTNAME = 'Bob'
  WHERE employee.employeeid = employeesID;
END updateInfo;

-- create a stored procedure that returns the managers of an employee
CREATE OR REPLACE PROCEDURE getManager (
employeesID IN NUMBER
)
IS
	managerName varchar2(40);
BEGIN
  select s.firstname
  into managerName
  from employee e, employee s
  where e.reportsto = s.employeeid 
  AND e.employeeID = employeesID;
  DBMS_OUTPUT.PUT_LINE(managerName);
END;

/* 4.3 Stored Procedure output parameters */
CREATE OR REPLACE PROCEDURE nameCompany(
customersID IN number, firstname OUT varchar2, 
lastname OUT varchar2, company OUT varchar2)
IS
BEGIN
  SELECT customer.firstname, customer.lastname, customer.company
  INTO firstname, lastname, company 
  FROM customer
  WHERE customer.customerid = customersId;
  --DBMS_OUTPUT.PUT_LINE(fName + ' ' + lName + ' works at ' + company);
END;

/* 5.0 Transaction */
  -- Create a transaction that deletes the invoice
SET TRANSACTION NAME deleteInvoice;

  DELETE
  FROM invoiceline
  WHERE invoiceline.invoiceID = 2;
  DELETE
  FROM invoice
  WHERE invoice.invoiceID = 2;
COMMIT;

/* 6.1 Triggers*/
-- Create an After Trigger
CREATE OR REPLACE TRIGGER employees_tr
  AFTER INSERT ON employee
  FOR EACH ROW
BEGIN
  UPDATE employee
    SET firstname = 'bob';
END;

-- Create an Update TRIGGER

CREATE OR REPLACE TRIGGER album_tr 
AFTER UPDATE ON ALBUM
FOR EACH ROW
BEGIN
  UPDATE album
  SET title = 'bob was here';
END;

-- Create a delete trigger
create or replace TRIGGER Customer_tr
AFTER DELETE ON Customer
FOR EACH ROW
DECLARE
  v_line varchar2(25);
BEGIN
  v_line := 'Customer Deleted';
  DBMS_OUTPUT.PUT_LINE(v_line);
END;

/* 7.0 Joins */
-- 7.1 Inner
SELECT customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer
INNER JOIN invoice
ON customer.customerid = invoice.customerid;

-- 7.2 Outer
SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer
LEFT OUTER JOIN invoice
ON customer.customerid = invoice.customerid;

--7.3 Right
SELECT artist.Name, album.title
FROM album
RIGHT OUTER JOIN artist
ON album.artistid = artist.artistid;

--7.4 Cross
SELECT *
from album
CROSS JOIN artist
ORDER BY artist.name ASC;

-- 7.5 SELF
SELECT e.firstname, e.lastname, supervisor.firstname, supervisor.lastname
FROM employee e, employee supervisor
WHERE e.reportsto = supervisor.employeeid;

/* Structure of Function */
CREATE OR REPLACE FUNCTION fName


/* Structure of PROCEDURE */
CREATE OR REPLACE FUNCTION fName(name IN type, name OUT type)
IS - declarations
BEGIN
	<body>
[EXCEPTION section_name]
END fName	