----------------------------------SQL LAB----------------------------------
------2.1 SELECT
--Task: Select all records from the Employee Table
SELECT* FROM employee;
--Task: Select all records from the employee table where last name is King
SELECT * FROM employee WHERE lastname='King';
--Select all records from employee table where first name is Andrew and REPORTSTO IS NULL
SELECT* FROM employee WHERE firstname='Andrew' AND REPORTSTO IS NULL;
----- 2.2 ORDERBY
--Task: Select all albums in album table and sort result set in descending order by title
SELECT* FROM album ORDER BY title DESC;
--Task: Select first name from Customer and sort result set in ascending order by city
SELECT* FROM customer ORDER BY city ASC;
----- 2.3: INSERT INTO
--TASK: Inser two new records into genre table
/
SELECt* FROM genre;
INSERT INTO genre(genreid,name) 
VALUES(26,'Manga');
/
INSERT INTO genre(genreid,name) 
VALUES(27,'Opera');
/
--Task: Inser two new employee new records into employee table
INSERT INTO employee (employeeid,lastname,firstname,title)
VALUES(9,'John','Doe','Mathematician');
/
INSERT INTO employee (employeeid,lastname,firstname)
VALUES(10,'SODA','CANE');
/
--Task:Insert two new records into the customer table

INSERT INTO customer(customerid,firstname,lastname,email)
VALUES(69,'Joe','Joyce','joey@gmail.com');
/
INSERT INTO customer(customerid,firstname,lastname,email)
VALUES(60,'Mory','keita','mk29@gmail.com');
/
----2.4 UPDATE
--Task: Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer SET firstname='Robert',lastname='Walter' WHERE customerid=32;
/
--Task: Update name of artist in the artist table "Creedence ClearWater Revival' to "CCR"

UPDATE artist SET name='CCR' WHERE artistid=76;
/
-----LIKE---
--Task: select all invoices with billing address like "T%"
SELECT* FROM invoice WHERE billingAddress LIKE 'T%';
/
----2.6 Between
--Task: Select all invoives that have a total between 15 and 50
SELECT* FROM invoice WHERE TOTAL BETWEEN 15 AND 50;
--Task: Select all employees hired between 1st of june 2003 and 1st of March 2004
SELECT* FROM employee;
SELECT* FROM employee WHERE hireDate BETWEEN '01-FEB-2003' AND '01-MAR-04';
-----2.7 DELETE
--Task: delete a record in customer table where the name is Robert Walter
SELECT* FROM customer;
/* We must first ALTER THE TABLE AND DROP THE CONSTRAINTS*/
/
ALTER TABLE Invoice DISABLE CONSTRAINT FK_InvoiceCustomerId;
ALTER TABLE customer DISABLE  CONSTRAINT FK_CustomerSupportRepId;
DELETE FROM customer WHERE customerid=32;
/
----------3.0 SQL functions
---3.1 System defined functions
--Task: Create a function that returns the current time
CREATE OR REPLACE FUNCTION currentTime
RETURN VARCHAR2
IS
 mTime VARCHAR2(10);
BEGIN
  SELECT TO_CHAR(CURRENT_DATE,'HH:MI:SS') INTO mmTime FROM DUAL;
  RETURN mTime;
END;
/
BEGIN
  DBMS_OUTPUT.PUT_LINE(currentTime());
  END;
/
--Task: Create a funtion that returns the length of a mediatype from the mediatype
SELECT* FROM MEDIATYPE;

CREATE OR REPLACE FUNCTION getLength( numVar IN NUMBER) RETURN NUMBER
IS numcontainer NUMBER(10);
BEGIN
  SELECT LENGTH(name) INTO numcontainer FROM mediatype
  WHERE numVar=MEDIATYPEID;
RETURN numcontainer;
END;

BEGIN
  DBMS_OUTPUT.PUT_LINE(getLength(2));
  END;
/
 ---------------System defined Aggregate functions
 --Task: Create a function that returns the average total invoices
 
 CREATE OR REPLACE FUNCTION getAverage(numVar IN NUMBER) RETURN NUMBER
 IS container Number(20);
 BEGIN
  SELECT SUM(TOTAL) INTO container
  FROM INVOICE
  WHERE numVar=total;
  END;
  /
--Task create a function that returns the most expensive tranck

CREATE OR REPLACE FUNCTION getMaxPrice RETURN NUMBER
IS
 myVar NUMBER(10,2);
BEGIN
    SELECT MAX(unitprice) INTO myVar from track ;
RETURN myVar;
END getMaxPrice;
/
BEGIN
    SYS.DMS_OUTPUT.PUT_LINE(getMaxPrice());
END;
/

------6.0 Triggers
--Task: Create an after insert trigger on the employee table after  a new record is inserted into the table
CREATE OR REPLACE TRIGGER empFIred AFTER INSERT ON employee
FOR EACH ROW
BEGIN
    SYS.DBMS_OUTPUT.PUT('You have been fired!');
end;
/
--Task:Create an after update trigger on the album table that fires after a row is inserted in the table

CREATE OR REPLACE TRIGGER onUpDate AFTER UPDATE ON album
FOR EACH ROW 
BEGIN
     SYS.DBMS_OUTPUT.PUT('Album updated');
END;

--Task: Create an after delete trigger on the customer table that fires after a row is deleted from the table

CREATE OR REPLACE TRIGGER onDelete AFTER DELETE ON album
FOR EACH ROW 
BEGIN
     SYS.DBMS_OUTPUT.PUT('Album deleted');
END;



  
----JOINS
--7.1: INNER JOIN
---Task: create an inner join that joins customers and orders and specifies the name of the customer and the invoice id
/
SELECT customer.firstname,customer.lastname, invoice.invoiceid
FROM customer
inner join invoice
ON customer.customerid=invoice.customerid;
/

--7.2 OUTER
/* create an outer join that joins the customer and invoice table, specifying
the customerid, firstname,lastname, invoiceid, and total*/

SELECT customer.customerid,customer.firstname,customer.lastname,invoice.invoiceid,invoice.total
FROM customer
LEFT OUTER JOIN invoice
ON customer.customerid=invoice.customerid;
/
--7.3 RIGHT
--Task:create a right join that joins album and artist specifying artist name and title
SELECT artist.name,album.title
FROM album
RIGHT OUTER JOIN artist
ON album.artistid=artist.artistid;

---7.4 CROSS
--Task: Create a cross join that joins album and artistid and sort by artist name in descending order
SELECT* FROM artist, album ORDER BY artist.name;

------7.5 SELF
--Task: perform a self-join on the employee table, joinning on the reportsto colum
SELECT* 
FROM employee emp, employee app where emp.employeeid=app.reportsto;
/

