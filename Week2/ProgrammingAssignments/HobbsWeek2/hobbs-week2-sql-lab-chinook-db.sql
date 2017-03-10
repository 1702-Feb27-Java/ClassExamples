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
 *//*
SELECT * FROM Employee;
SELECT * FROM Employee where lastname = 'King';
SELECT * FROM Employee where firstname = 'Andrew' AND reportsto IS NULL;
*/

/* 2.2 - ORDER BY 
 *
 * 1. Select all albums from Album and sort them in descending order by title.
 * 2. Select first name from Customer and sort them in ascending order by city.
 *//*
SELECT * FROM Album ORDER BY title DESC;
SELECT firstname FROM Customer ORDER BY city;
*/

/* 2.3 - INSERT INTO 
 *
 * 1. Insert two records into Genre.
 * 2. Insert two records into Employee.
 * 3. Insert two records into Customer.
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
 *//*
UPDATE Customer SET firstname = 'Robert', lastname='Walter' WHERE customerid=32;
UPDATE Artist SET name = 'CCR' WHERE name = 'Creedence Clearwater Revival'; --artistid=76
*/

/* 2.5 - LIKE
 *
 * 1. Select all invoices with a billing address like 'T%'.
 *//*
 SELECT * FROM INVOICE WHERE billingaddress LIKE 'T%';
 */

/* 2.6 - BETWEEN
 *
 * 1. Select all invoices that have a total between 15 and 50.
 * 2. Select all employees hired between 1st of June 2003 and 1st of March 2004.
 *//*
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04'; --DATE COMPARISON IN RANGE USING BETWEEN
SELECT * FROM employee WHERE hiredate >= '01-JUN-03' AND hiredate <= '01-MAR-04'; --DATE COMPARISON IN RANGE NOT USING BETWEEN
*/

/* 2.7 - DELETE
 *
 * 1. Delete Robert Walter's record from Customer.
 */
 SELECT * FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'; --verify Robert Walter is a customer
 SELECT * FROM invoiceline where invoiceline.invoiceid IN (SELECT invoice.invoiceid FROM invoice WHERE invoice.customerid IN (SELECT customerid FROM customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter')); -- verify all invoices and invoicelines that can be correlated with customer 'Robert Walter'
 SELECT * FROM invoice WHERE customerid IN (SELECT customerid FROM customer where invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter'); --verify all invoices that can be related to customer 'Robert Walter'.
 DELETE FROM invoiceline WHERE invoiceline.invoiceid IN (SELECT invoice.invoiceid FROM invoice WHERE invoice.customerid IN (SELECT customerid FROM customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter')); --remove all invoicelines that can be related to customer 'Robert Walter'.
 DELETE FROM invoice WHERE invoice.customerid IN (SELECT customerid from customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter'); --remove all invoices that can be related to customer 'Robert Walter'.
 DELETE FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'; --remove customer 'Robert Walter'
 SELECT * FROM invoiceline where invoiceline.invoiceid IN (SELECT invoice.invoiceid FROM invoice WHERE invoice.customerid IN (SELECT customerid FROM customer WHERE invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter')); -- verify all invoices and invoicelines that can be correlated with customer 'Robert Walter' no longer exist.
 SELECT * FROM invoice WHERE customerid IN (SELECT customerid FROM customer where invoice.customerid = customer.customerid AND customer.firstname = 'Robert' AND customer.lastname = 'Walter'); --verify all invoices that can be related to customer 'Robert Walter' no longer exist.
 SELECT * FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'; --verify Robert Walter is no longer a customer
 
/*******************************************************************************
 * 3.0 - SQL FUNCTIONS
 *
 */
 
/*******************************************************************************
 * 4.0 - STORED PROCEDURES
 *
 */
 
/*******************************************************************************
 * 5.0 - TRANSACTIONS
 *
 */
 
/*******************************************************************************
 * 6.0 - TRIGGERS
 *
 */
 
/*******************************************************************************
 * 7.0 - JOINS
 *
 */
 
/*******************************************************************************
 * 9.0 - ADMINISTRATION
 *
 */
 
 