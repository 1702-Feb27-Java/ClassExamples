-- SQL Lab (week 2)
-- Author: Michael Hobbs
-- Submission Date: 2017 March 10
-- 
-- Executes queries and other statements with the chinook database provided.

/*******************************************************************************
 * 1.0 - SETTING UP ORACLE CHINOOK
 *
 */
 -- Opened Chinook_Oracle.sql and executed the scripts there.

/*******************************************************************************
 * 2.0 - SQL QUERIES
 *
 */
/* 2.1 - SELECT 
 *
 * 1. Select all records from Employee.
 * 2. Select all records from Employee where the last name is King.
 * 3. Select all records from Employee where first name is Andrew and
 *    REPORTSTO is null.
 *
 *//*
SELECT * FROM Employee;
SELECT * FROM Employee where lastname = 'King';
SELECT * FROM Employee where firstname = 'Andrew' AND reportsto IS NULL;
*/

/* 2.2 - ORDER BY 
 *
 * 1. Select all albums from Album and sort them in descending order by title.
 * 2. Select first name from Customer and sort them in ascending order by city.
 *
 *//*
SELECT * FROM Album ORDER BY title DESC;
SELECT firstname FROM Customer ORDER BY city;
*/

/* 2.3 - INSERT INTO 
 *
 * 1. Insert two records into Genre.
 * 2. Insert two records into Employee.
 * 3. Insert two records into Customer.
 *
 *//*
INSERT INTO Genre (genreid, name) VALUES (26, 'Doowop');
INSERT INTO Genre (genreid, name) VALUES (27, 'Experimental');
INSERT INTO Genre (genreid) VALUES (28); --can omit table (columnnames), but then would have to put values for every column for values (values).

INSERT INTO Employee 
(employeeid, lastname, firstname, title, birthdate, hiredate, address, city, state, country, postalcode, phone, fax, email) VALUES
(9, 'Bobbert', 'Rob', 'Software Developer', '28-FEB-85', '27-FEB-16', '123 Somewhere Str', 'Nowhere', 'NoState', 'United States', '98765', '+1 (123) 555-6789', '+1 (999) 555 1234', 'rob@chinookcorp.com');
INSERT INTO Employee 
(employeeid, lastname, firstname, title, birthdate, hiredate, address, city, state, country, postalcode, phone, fax, email) VALUES
(10, 'Bros', 'Mario', 'Quality Assurance', '25-DEC-83', '27-FEB-16', '', 'Tokyo', 'NoState', 'Japan', '98765', '+1 (123) 555-6789', '+1 (999) 555 1234', 'mario@chinookcorp.com');

INSERT INTO Customer (customerid, firstname, lastname, company, address, city, state, country, postalcode, phone, fax, email, supportrepid) VALUES 
(60, 'Sally', 'Mally', 'Chinook Corp', '999 Double Street', 'Reston', 'VA', 'United States', '20190', '(703) 555 4444', '(703) 555 1234', 'sally@mally.net', 5);
INSERT INTO Customer (customerid, firstname, lastname, company, address, city, state, country, postalcode, phone, fax, email, supportrepid) VALUES 
(61, 'Max', 'Wax', 'Big Wig Co', '123 Single Avenue', 'Little Prairie', 'GA', 'United States', '99999', '(703) 555 9999', '(703) 555 9999', 'max@wax.com', 3);
*/

/* 2.4 - UPDATE
 *
 * 1. Update Customer to have Aaron Mitchell become Robert Walter.
 * 2. Update Artist from name of 'Creedence Clearwater Revival' to 'CCR'.
 *
 *//*
UPDATE Customer SET firstname = 'Robert', lastname='Walter' WHERE customerid=32;
UPDATE Artist SET name = 'CCR' WHERE name = 'Creedence Clearwater Revival'; --artistid=76
*/

/* 2.5 - LIKE
 *
 * 1. Select all invoices with a billing address like 'T%'.
 *
 *//*
 SELECT * FROM INVOICE WHERE billingaddress LIKE 'T%';
 */

/* 2.6 - BETWEEN
 *
 * 1. Select all invoices that have a total between 15 and 50.
 * 2. Select all employees hired between 1st of June 2003 and 1st of March 2004.
 *
 *//*
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04'; --DATE COMPARISON IN RANGE USING BETWEEN
--SELECT * FROM employee WHERE hiredate >= '01-JUN-03' AND hiredate <= '01-MAR-04'; --DATE COMPARISON IN RANGE NOT USING BETWEEN
*/

/* 2.7 - DELETE
 *
 * 1. Delete Robert Walter's record from Customer.
 *
 *//*
 SELECT * FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'; --verify Robert Walter is a customer
 SELECT * FROM invoiceline where invoiceline.invoiceid IN (SELECT invoice.invoiceid FROM invoice WHERE invoice.customerid IN (SELECT customerid FROM customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter')); -- verify all invoices and invoicelines that can be correlated with customer 'Robert Walter'. These reference Customer so must first be removed in turn before can remove the customer.
 SELECT * FROM invoice WHERE customerid IN (SELECT customerid FROM customer where invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter'); --verify all invoices that can be related to customer 'Robert Walter'.
 DELETE FROM invoiceline WHERE invoiceline.invoiceid IN (SELECT invoice.invoiceid FROM invoice WHERE invoice.customerid IN (SELECT customerid FROM customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter')); --remove all invoicelines that can be related to customer 'Robert Walter'.
 DELETE FROM invoice WHERE invoice.customerid IN (SELECT customerid from customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter'); --remove all invoices that can be related to customer 'Robert Walter'.
 DELETE FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'; --remove customer 'Robert Walter'
 SELECT * FROM invoiceline where invoiceline.invoiceid IN (SELECT invoice.invoiceid FROM invoice WHERE invoice.customerid IN (SELECT customerid FROM customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter')); -- verify all invoices and invoicelines that can be correlated with customer 'Robert Walter' no longer exist.
 SELECT * FROM invoice WHERE customerid IN (SELECT customerid FROM customer where invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter'); --verify all invoices that can be related to customer 'Robert Walter' no longer exist.
 SELECT * FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'; --verify Robert Walter is no longer a customer
 */
 
/*******************************************************************************
 * 3.0 - SQL FUNCTIONS
 *
 */
 /* 3.1 - SYSTEM DEFINED FUNCTIONS
  * 
  * 1. Define a function that returns the current time.
  * 2. Define a function that returns the length of a mediatype.
  *
  *//*
  --  Gets current time.
  CREATE OR REPLACE FUNCTION get_current_time
    RETURN DATE
    IS
    BEGIN
      RETURN SYSDATE;
    END;
    /
    
  -- Gets length of mediatype name.
  CREATE OR REPLACE FUNCTION get_mediatype_name_length(mediatype_id IN NUMBER)
    RETURN NUMBER
    IS
      mediatypeVar VARCHAR2(120);
    BEGIN
      SELECT name into mediatypeVar FROM mediatype WHERE mediatypeid = mediatype_id;
      RETURN LENGTH(mediatypeVar);
    END;
    /
    
  -- Invoke functions just defined.
  CALL DBMS_OUTPUT.PUT_LINE('CURRENT TIME: ' || TO_CHAR(get_current_time(),'HH24:MI:SS'));
  CALL DBMS_OUTPUT.PUT_LINE('LENGTH OF MEDIATYPE NAME: ' || get_mediatype_name_length(1));
  */
  
 /* 3.2 - SYSTEM DEFINED AGGREGATE FUNCTIONS
  *
  * 1. Define a function that returns the average total of all invoices.
  * 2. Define a function that returns the most expensive track.
  *
  *//*
  -- Returns average of all totals of invoices.
  CREATE OR REPLACE FUNCTION average_invoice
    RETURN NUMBER
    IS
      average NUMBER;
    BEGIN
      SELECT AVG(total) INTO average FROM invoice;
      RETURN average;
    END;
    /
  
  -- Returns id of the most expensive track.
  CREATE OR REPLACE FUNCTION get_most_expensive_track
    RETURN NUMBER
    IS
      price NUMBER;
    BEGIN
      SELECT MAX(unitprice) INTO price FROM track;
      RETURN price;
    END;
    /
    
    SELECT trackid 
      --INTO track_id
      FROM track 
      WHERE trackid in (SELECT MAX(unitprice) FROM track);
  
  -- Invoke functions just defined.
  CALL DBMS_OUTPUT.PUT_LINE('AVERAGE OF ALL INVOICE TOTALS: ' || average_invoice());
  CALL DBMS_OUTPUT.PUT_LINE('ID OF MOST EXPENSIVE TRACK: ' || get_most_expensive_track());
  */
  
 /* 3.3 - USER DEFINED SCALAR FUNCTIONS
  *
  * 1. Define a function that returns the average price of invoicelines.
  *
  *//*
  -- Gets average price of invoiceline items.
  CREATE OR REPLACE FUNCTION avg_price_invoicelines
    RETURN NUMBER
    IS
      average NUMBER;
    BEGIN
      SELECT AVG(unitprice) INTO average FROM invoiceline;
      RETURN average;
    END;
    /
  
  -- Invoke just defined function.
  BEGIN
    DBMS_OUTPUT.PUT_LINE('AVERAGE PRICE OF INVOICELINES: ' || avg_price_invoicelines());
  END;
  /
  */
  
 /* 3.4 - USER DEFINED TABLE VALUED FUNCTIONS
  *
  * 1. Define a function that returns all employees born after 1968.
  *
  *//*
  -- Define table type which will hold employee ids.
  CREATE TYPE employeeids IS TABLE OF NUMBER;
  /
  
  -- Get employees born after 1968.
  CREATE OR REPLACE FUNCTION getEmployeesBornAfter(year IN NUMBER)
  RETURN employeeids
  IS
    employees employeeids;
  BEGIN
    SELECT employeeid BULK COLLECT INTO employees FROM employee
    WHERE to_char(employee.birthdate, 'YYYY') > '1968';
    RETURN employees;
  END;
  /
 
 -- Display employees born after 1968.
 DECLARE 
  employees employeeids;
 BEGIN
  employees := getEmployeesBornAfter(1968);
  FOR employeeBornAfter1968 IN 1 .. employees.COUNT
  LOOP
    DBMS_OUTPUT.PUT_LINE('EMPLOYEE ID BORN AFTER 1968: ' || employees(employeeBornAfter1968));
  END LOOP;
 END;
 /
 */
 
/*******************************************************************************
 * 4.0 - STORED PROCEDURES
 *
 */
 /* 4.1 - BASIC STORED PROCEDURE
  *
  * 1. Define a procedure that selects Employees' first and last names.
  *
  *//*
  -- Defines a type to hold employee names from Employee.
  CREATE TYPE employeenames IS TABLE OF VARCHAR2(41);
  /
  
  -- Gets the full name of an Employee.
  CREATE OR REPLACE PROCEDURE get_employee_name(e_names OUT employeenames)
  IS
  BEGIN
    SELECT employee.firstname || ' ' || employee.lastname AS name BULK COLLECT INTO e_names FROM employee;
  END;
  /
  
  -- Displays the full name of employees.
 DECLARE 
  employees employeenames;
 BEGIN
  get_employee_name(employees);
  FOR employeeName IN 1 .. employees.COUNT
  LOOP
    DBMS_OUTPUT.PUT_LINE('EMPLOYEE NAME: ' || employees(employeeName));
  END LOOP;
 END;
 /
  */
  
 /* 4.2 - STORED PROCEDURE INPUT PARAMETERS
  *
  * 1. Define a procedure that updates Employees' personal information.
  * 2. Defines a procedure that returns Employees' managers.
  *
  *//*
  -- Allow update of an employee's personal information.
 CREATE OR REPLACE PROCEDURE update_employee(e_employeeid IN NUMBER, e_firstname IN VARCHAR2, e_lastname IN VARCHAR2, e_address IN VARCHAR2, e_city IN VARCHAR2, e_state IN VARCHAR2, e_country IN VARCHAR2, e_postalcode IN VARCHAR2, e_phone IN VARCHAR2, e_fax IN VARCHAR2, e_email IN VARCHAR2)
 IS
 BEGIN
  UPDATE employee SET firstname = e_firstname, lastname = e_lastname, address = e_address, city = e_city, state = e_state, country = e_country, postalcode = e_postalcode, phone = e_phone, fax = e_fax, email = e_email WHERE employeeid = e_employeeid;
 END;
 /
 
 -- Get an employee's manager.
 CREATE OR REPLACE PROCEDURE get_manager(e_employeeid IN NUMBER, e_manager OUT VARCHAR2)
 IS
 BEGIN
  SELECT e_m.firstname || ' ' || e_m.lastname INTO e_manager FROM employee e_e, employee e_m WHERE e_employeeid = e_e.employeeid AND e_e.reportsto = e_m.employeeid;
 END;
 /
  
  -- Update an employee's personal information.
  BEGIN
  update_employee(11, 'Bob', 'Robert', '115 Nothing St', 'Somewhere City', 'New State', 'Korea', '11145', '111-555-2222', '111-555-2222', 'bob@robert.com');
  END;
  /
  
  -- Get an employee's manager.
  DECLARE
    e_manager VARCHAR2(41);
  BEGIN
    get_manager(8, e_manager);
  DBMS_OUTPUT.PUT_LINE('MANAGER: ' || e_manager);
  END;
  /
  */
  
 /* 4.3 - STORED PROCEDURE OUTPUT PARAMETERS
  *
  * 1. Define a procedure that returns a Customer's name and company.
  *
  *//*
  -- Gets a customer's name and company.
  CREATE OR REPLACE PROCEDURE get_customer_name_company(c_id IN NUMBER, c_name OUT VARCHAR2, c_company OUT VARCHAR2)
  IS
  BEGIN
    SELECT customer.firstname || ' ' || customer.lastname as name INTO c_name FROM customer WHERE c_id = customer.customerid;
    SELECT customer.company INTO c_company FROM customer WHERE c_id = customer.customerid;
  END;
  /
 
 -- Displays a customer's name and company.
 DECLARE 
  c_name VARCHAR2(61);
  c_company VARCHAR2(80);
 BEGIN
  get_customer_name_company(1, c_name, c_company);
  DBMS_OUTPUT.PUT_LINE('CUSTOMER NAME: ' || c_name || CHR(13) || CHR(10) || 'CUSTOMER COMPANY: ' || c_company); --CHR(13) = CR, CHR(10) = LF.
 END;
 /
 */
 
/*******************************************************************************
 * 5.0 - TRANSACTIONS
 *
 * 1. Define a transaction given an invoice that when given an invoiceid will delete that invoice.
 * 2. Define a transaction nested within a procedure that inserts a new record in Customer.
 *
 *//*
 -- Deletes an invoice.
 SET TRANSACTION NAME 'delete_invoice';
 DECLARE
  invoice_id NUMBER; --invoice to be deleted.
  BEGIN
  invoice_id := 2;
 DELETE FROM invoiceline WHERE invoiceline.invoiceid = invoice_id; --remove all invoicelines associated with the given invoice.
 DELETE FROM invoice WHERE invoice.invoiceid = invoice_id; --remove all the given invoices.
 END;
 / 
 
 COMMIT;
 /
 
 -- Adds a new customer.
 CREATE OR REPLACE PROCEDURE insert_customer(c_id IN NUMBER, c_firstname IN VARCHAR2, c_lastname IN VARCHAR2, c_company IN VARCHAR2, c_address IN VARCHAR2, c_city IN VARCHAR2, c_state IN VARCHAR2, c_country IN VARCHAR2, c_postalcode IN VARCHAR2, c_phone IN VARCHAR2, c_fax IN VARCHAR2, c_email IN VARCHAR2)
 IS
 BEGIN
  INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
  VALUES (c_id, c_firstname, c_lastname, c_company, c_address, c_city, c_state, c_country, c_postalcode, c_phone, c_fax, c_email);
 END;
 / 

 SET TRANSACTION NAME 'insert_customer';

 BEGIN
 insert_customer(64, 'fn', 'ln', 'c', 'a', 'c', 's', 'c', 'p', 'p', 'f', 'e');
 END;
 /
 
 COMMIT;
 /
 */
 
/*******************************************************************************
 * 6.0 - TRIGGERS
 *
 */
 /* 6.1 - AFTER/FOR
  *
  * 1. Define an ARTER INSERT trigger on EMPLOYEE fired after a new record is inserted.
  * 2. Define an AFTER UPDATE trigger on ALBUM that fires after a row is inserted.
  * 3. Define an AFTER DELETE trigger on Customer that fires after a row is deleted.
  *
  *//*
  -- Defines a trigger that <!--prints the count of employees after insertion.--> displays a message after insertion of an employee.
  CREATE OR REPLACE TRIGGER employee_after_insert
  AFTER INSERT ON employee
  FOR EACH ROW
    DECLARE
      number_employees NUMBER;
    BEGIN
      --SELECT COUNT(*) INTO number_employees FROM employee;
      --DBMS_OUTPUT.PUT_LINE(number_employees);
      DBMS_OUTPUT.PUT_LINE('AFTER_INSERT OF EMPLOYEE');
    END;
    /
    
  -- Defines a trigger that displays a message after insertion of an employee.
  CREATE OR REPLACE TRIGGER album_after_update
  AFTER UPDATE ON album
  FOR EACH ROW
    BEGIN
      DBMS_OUTPUT.PUT_LINE('AFTER UPDATE OF ALBUM');
    END;
    /
    
  CREATE OR REPLACE TRIGGER customer_after_delete
  AFTER DELETE ON customer
  FOR EACH ROW
  BEGIN
    DBMS_OUTPUT.PUT_LINE('AFTER DELETE OF CUSTOMER');
  END;
  /
    
-- INSERT TO TEST EMPLOYEE AFTER_INSERT TRIGGER
INSERT INTO Employee 
(employeeid, lastname, firstname, title, birthdate, hiredate, address, city, state, country, postalcode, phone, fax, email) VALUES
(11, 'Bobbert', 'Rob', 'Software Developer', '28-FEB-85', '27-FEB-16', '123 Somewhere Str', 'Nowhere', 'NoState', 'United States', '98765', '+1 (123) 555-6789', '+1 (999) 555 1234', 'rob@chinookcorp.com');
-- UPDATE TO TEST ALBUM AFTER_UPDATE TRIGGER
UPDATE album SET title = 'Warner 25 Years' WHERE album.albumid = 8; --title previously 'Warner 25 Anos'
-- DELETE TO TEST CUSTOMER AFTER_DELETE TRIGGER
-- some delete similar to 2.7
 */
 
/*******************************************************************************
 * 7.0 - JOINS
 *
 */
 /* 7.1 - INNER
  *
  * Joins Customer and Invoice and displays Customer's name and Invoice's id.
  *
  * It performs an inner join to do so.
  *
  *//*
  SELECT customer.firstname, customer.lastname, invoice.invoiceid FROM customer
  INNER JOIN invoice
  ON customer.customerid = invoice.customerid;
  */
  
 /* 7.2 - OUTER
  *
  * Joins Customer and Invoice and displays id and name of Customer and id and total of Invoice.
  *
  * It performs a full outer join to do so.
  *//*
  SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total FROM customer
  FULL OUTER JOIN invoice
  ON customer.customerid = invoice.customerid;
  */
  
 /* 7.3 -  RIGHT
  *
  * Joins Album and Artist and displays Artist's name and Album's title.
  *
  * It performs a right outer join to do so.
  *
  *//*
  SELECT artist.name, album.title FROM album
  RIGHT OUTER JOIN artist
  ON album.artistid = artist.artistid;
  */
  
 /* 7.4 - CROSS
  *
  * Joins Album and Artist and sorts the result by Artist's name (in ascending order a-z).
  *
  * It performs a cross join / cartesian product to do so.
  *
  *//*
  SELECT * FROM album
  CROSS JOIN artist
  ORDER BY artist.name;
  */
  
 /* 7.5 - SELF
  *
  * Self-joins Employee to find out the managers of employees.
  *
  * It performs a self-join to do so.
  *
  *//*
  SELECT e1.employeeid, e1.firstname || ' ' || e1.lastname as "EMPLOYEE NAME", e2.reportsto, e2.firstname || ' ' || e2.lastname as "MANAGER NAME" FROM employee e1
  INNER JOIN employee e2
  ON e1.employeeid = e2.reportsto;
  */
 
/*******************************************************************************
 * 9.0 - ADMINISTRATION
 *
 */
 -- Backed up the chinook database to chinook.bak.
 
 