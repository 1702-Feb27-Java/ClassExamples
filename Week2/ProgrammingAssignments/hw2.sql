--2.1
-- get all employees
SELECT * FROM EMPLOYEE;
/
-- get all employee with last name King
SELECT * FROM EMPLOYEE
WHERE LASTNAME = 'King';
/
-- get all employee named Andrew with no one they report to 
SELECT * FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew'
AND REPORTSTO IS NULL;
/
--2.2 
-- order album in decending order by title
SELECT * FROM ALBUM
ORDER BY TITLE DESC
/
-- get the first name of the customer and sort by city
SELECT FIRSTNAME, CITY FROM CUSTOMER
GROUP BY FIRSTNAME, CITY
ORDER BY CITY ASC
/

--2.3
--SELECT * FROM GENRE;
INSERT INTO GENRE (GENREID, NAME) VALUES(888,'insert1');
INSERT INTO GENRE (GENREID, NAME) VALUES(77, 'insert2');
/
--SELECT * FROM EMPLOYEE;
INSERT INTO EMPLOYEE (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10000, 'INSERT', 'INSERT', 'General Manager', TO_DATE('1992-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '120 River Ave', 'Edmonton', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'INSERT@chinookcorp.com');
INSERT INTO EMPLOYEE (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10898, 'INSERTSECOND', 'INSERTSECOND', 'INSERTING THINGS', TO_DATE('1990-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '1920 Mex Ave', 'Edmonton', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'INSERTAGAIN@chinookcorp.com');
/
--SELECT * FROM CUSTOMER;
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (888, 'INSERT', 'INSERT', 'Grétrystraat 63', 'Brussels', 'AMERICA', '1000', '+32 02 219 03 03', 'INSERT@apple.be', 4);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (900, 'SECOND', 'SECOND', 'Grétrystraat 63', 'Brussels', 'AMERICA', '1000', '+32 02 219 03 03', 'INSERT@apple.be', 4);
/
#2.4 

-- function to check if Robert Walter is in the db
-- used to check for 
--SELECT * FROM CUSTOMER
--WHERE FIRSTNAME = 'Robert'
--AND LASTNAME = 'Walter';

UPDATE CUSTOMER SET FIRSTNAME = 'Robert' WHERE FIRSTNAME = 'Aaron';
UPDATE CUSTOMER SET LASTNAME = 'Walter' WHERE LASTNAME = 'Mitchell';
/
--Select * From Artist
--where NAME = 'CCR';

UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';
/
--2.5
SELECT * from INVOICE
where BILLINGADDRESS LIKE 'T%';

--2.6
Select * FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
/
SELECT * FROM EMPLOYEE
WHERE TO_DATE(HIREDATE, 'DD/MON/YY') BETWEEN TO_DATE('01/JUN/03', 'DD/MON/YY')AND TO_DATE('01/MAR/04', 'DD/MON/YY');
/
--2.7

ALTER TABLE INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;

ALTER TABLE INVOICE
ADD CONSTRAINT FK_INVOICECUSTOMERID
FOREIGN KEY (CUSTOMERID)
REFERENCES CUSTOMER(CUSTOMERID)
ON DELETE CASCADE;

-- need to change the invoiceline to cD
ALTER TABLE INVOICELINE
DROP CONSTRAINT FK_INVOICELINEINVOICEID;

ALTER TABLE INVOICELINE
ADD CONSTRAINT FK_INVOICELINEINVOICEID
FOREIGN KEY (INVOICEID)
REFERENCES INVOICE(INVOICEID)
ON DELETE CASCADE;

DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert'
AND LASTNAME = 'Walter';

--3.0
-- tests get current time and prints out resoults
--declare
  --  variable1_ts TIMESTAMP;
--begin
--  SELECT LOCALTIMESTAMP INTO variable1_ts from dual;
  --  DBMS_OUTPUT.PUT_LINE(extract(HOUR from variable1_ts) ||':' || extract(MINUTE from variable1_ts) || ':' || extract(HOUR from variable1_ts));    
--end;
-------------------3.0
----------------------
-- creates a function getCurrentTime that will print to the console the 
-- current time in hour:minute:seconds format
CREATE OR REPLACE FUNCTION getCurrentTime
RETURN NUMBER
IS
  variable1_ts TIMESTAMP; --creates a time stamp variable
BEGIN  
  SELECT LOCALTIMESTAMP INTO variable1_ts from dual; --gets the time stamp to parse
  -- prints the line from the time stamp parseing it and printing to the console.
    DBMS_OUTPUT.PUT_LINE(extract(HOUR from variable1_ts) ||':' || extract(MINUTE from variable1_ts) || ':' || extract(HOUR from variable1_ts));
    return 0;
END;
  
-- runs the function to make sure it works
DECLARE
 tempNum number;
BEGIN
tempNum := getCurrentTime();
END;


-------------------------------------------------
-- fucntion that returns the length of a mediatype from mediatype table
CREATE OR REPLACE FUNCTION getLength(medID in NUMBER, medLength out NUMBER)
return NUMBER
IS   
  temp varchar2(4000);
BEGIN
  select NAME INTO temp FROM MEDIATYPE WHERE MEDIATYPEID = medID;
  RETURN LENGTH(temp);
END;

DECLARE
 tempNum number;
BEGIN
  tempNum := getLength(1, tempNum);
  DBMS_OUTPUT.PUT_LINE(tempNum);
END;

------ checking to fine the name of the id i tested it on
Select Name from Mediatype where mediatypeid = 1;

--- 3.1
-----------function that returns the average of all invoices
------------- selects the average of the total column from the invoice table
CREATE OR REPLACE FUNCTION getInvoiceAverage
return NUMBER
IS     
  temp NUMBER;
BEGIN  
  SELECT AVG(TOTAL) INTO temp FROM INVOICE;
  RETURN temp;
END;

-- checking to see if the invoice total average works
DECLARE
 tempNum number;
BEGIN
  tempNum := GETINVOICEAVERAGE();
  DBMS_OUTPUT.PUT_LINE(tempNum);
END;


-----------------------------------
-- function to find the most expensive CREATE OR REPLACE FUNCTION getInvoiceAverage
CREATE OR REPLACE FUNCTION getExpensiveTrack
return NUMBER
IS     
  temp NUMBER;
BEGIN  
  SELECT MAX(UNITPRICE) INTO temp FROM TRACK;
  RETURN temp;
END;

-- checking to see if the track max unit price works
DECLARE
 tempNum number;
BEGIN
  tempNum := GETEXPENSIVETRACK();
  DBMS_OUTPUT.PUT_LINE(tempNum);
END;

-------------------------------------------
--3.2
----- function that returns the average price of invoiceline items

CREATE OR REPLACE FUNCTION getInvoiceLineAverage
return NUMBER
IS     
  temp NUMBER;
BEGIN  -- multiply unitprice by quantity to get the total fro the line
  SELECT AVG(UNITPRICE * QUANTITY) INTO temp FROM INVOICELINE; 
  RETURN temp;
END;

-----------------
--- USED TO TEST GETINVOICELINEAVERAGE WORKED
DECLARE
 tempNum number;
BEGIN
  tempNum := getInvoiceLineAverage();
  DBMS_OUTPUT.PUT_LINE(tempNum);
END;

--------------------
-- 3.4
-- get all employees born after 1968
/
CREATE OR REPLACE FUNCTION getEmployeeBornAfter(outCursor OUT SYS_REFCURSOR)
return SYS_REFCURSOR
IS  
  
BEGIN     
  open outCursor for 
  select EMPLOYEE.FIRSTNAME, EMPLOYEE.LASTNAME
  FROM EMPLOYEE
  WHERE TO_DATE(BIRTHDATE, 'DD/MON/YY') > TO_DATE('01/JAN/68', 'DD/MON/YY');
  return outCursor;  
END;

-- testing the fuction

declare
  cursorVar SYS_REFCURSOR;
  eFName EMPLOYEE.FIRSTNAME%TYPE;
  eLName EMPLOYEE.LASTNAME%TYPE;
begin
  cursorVar := getEmployeeBornAfter(cursorVar);
  LOOP
    FETCH cursorVar INTO eFName, eLName;
    EXIT WHEN cursorVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || eFName || '|' || ELName); 
  END LOOP;
end;
/
-------------------------------------------------------------
--4.1 
-- stored procecdure that stores all the first and last names of employees
CREATE OR REPLACE PROCEDURE getEmployeeFirstAndLastName(outCursor OUT SYS_REFCURSOR)
is    
begin
  open outCursor for
  select FIRSTNAME, LASTNAME from EMPLOYEE;
end;
------------
-- TEST THAT 4.1 WORKS
------
declare
  cursorVar SYS_REFCURSOR;
  eFName EMPLOYEE.FIRSTNAME%TYPE;
  eLName EMPLOYEE.LASTNAME%TYPE;
begin
  getEmployeeFirstAndLastName(cursorVar);
  LOOP
    FETCH cursorVar INTO eFName, eLName;
    EXIT WHEN cursorVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || eFName || '|' || ELName); 
  END LOOP;
end;

-----4.2-------
-------
---- update the personal information of employee
--- will update the city, state and country of the employee
CREATE OR REPLACE PROCEDURE getUpdateEmployeeInfo(E_id in number, cityN in varchar2, stateName in varchar2, countryN in varchar2)
is    
begin
  UPDATE EMPLOYEE 
  SET CITY = cityN, STATE = stateName, COUNTRY = countryN
  WHERE EMPLOYEEID = E_id;
end;

-- run the test
declare
begin
  getUpdateEmployeeInfo(1, 'Blacksburg', 'Virginia', 'Merica');
end
--- checking it worked
select * from Employee where employeeid = 1;

--------------------
-- returns the managers of the employy with the given id
-----------------------
CREATE OR REPLACE PROCEDURE getManagers(e_id in NUMBER, outCursor OUT SYS_REFCURSOR)
is    
begin
  open outCursor for
  select  E1.FIRSTNAME, E1.LASTNAME  -- get the first/last name of the manager
  from EMPLOYEE e1, EMPLOYEE e2 
  where e1.REPORTSTO = E2.EMPLOYEEID -- check the reporto number matchs
  AND E1.EMPLOYEEID = e_id;  -- check its the employee we want to find 
end;

--- CHECKING GET MANAGERS IS CORRECT
declare
  cursorVar SYS_REFCURSOR;
  eFName EMPLOYEE.FIRSTNAME%TYPE;
  eLName EMPLOYEE.LASTNAME%TYPE;
begin
  getManagers(2, cursorVar);
  LOOP
    FETCH cursorVar INTO eFName, eLName;
    EXIT WHEN cursorVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || eFName || '|' || ELName); 
  END LOOP;
end;


CREATE OR REPLACE PROCEDURE getNameAndCompany(c_id in number, f_name OUT varchar2, l_name OUT varchar2, companyN OUT varchar2)
is    
begin
  SELECT FIRSTNAME, LASTNAME, COMPANY INTO f_name, l_name, companyN
  FROM CUSTOMER
  WHERE CUSTOMERID = c_id;
  
end;

--- testing
declare
  firstName varchar2(4000);
  lastName varchar2(4000);
  companyName varchar2(4000);
begin
  getNameAndCompany(1, firstName, lastName, companyName);
  DBMS_OUTPUT.PUT_LINE('|' || firstName || '|' || lastName || '|' || '|' || companyName); 
end;

---------------------5.0 transactions

-- deletes the specificed invoice id
start TRANSACTION 
DELETE FROM INVOICE WHERE INVOICEID = 1;
commit;

--takes the input for the customers info and inserts into the table
CREATE OR REPLACE PROCEDURE insterCustomerTransaction(id_num in number, f_Name in varchar2, l_Name in varchar2, e_mail in varchar2,c_Name in varchar2, add_Name in varchar2, ci_name in varchar2, s_name in varchar2, coun_name in varchar2, p_code in varchar2, ph_num in varchar2, fax in number, support in number)
is    
begin   
  INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
  VALUES (id_num ,f_Name,l_Name,c_Name,add_name,ci_name,s_name,coun_name,p_code,ph_num,fax,e_mail,support);
  COMMIT;
end;






-------------------------------------------

--6.1 CREATES AN AFTER INSERT TRIGGER 
  
CREATE OR REPLACE TRIGGER employee_trigger --name
AFTER INSERT ON EMPLOYEE --on event
FOR EACH ROW --how often
DECLARE
  v_username varchar2(10);
BEGIN
   -- Find username of person performing the INSERT into the table
   SELECT user INTO v_username
   FROM dual;
END;
\


CREATE OR REPLACE TRIGGER album_trigger --name
AFTER UPDATE ON ALBUM --on event
FOR EACH ROW --how often
DECLARE
  v_username varchar2(10);
BEGIN
   -- Find username of person performing the INSERT into the table
   SELECT user INTO v_username
   FROM dual;
   
   -- print the username out
   DBMS_OUTPUT.PUT_LINE(v_username);   
END;
-- TEST AFTER UPDATE TRIGGER WORKS
UPDATE ALBUM
SET TITLE = 'TEST'
WHERE ALBUMID = 1;

CREATE OR REPLACE TRIGGER customer_trigger --name
AFTER DELETE ON CUSTOMER --on event
FOR EACH ROW --how often
DECLARE
  v_username varchar2(10);
BEGIN
   -- Find username of person performing the INSERT into the table
   SELECT user INTO v_username
   FROM dual;   
   -- print the username out
   DBMS_OUTPUT.PUT_LINE(v_username);   
END;


---7.1 Inner Join
SELECT c.firstname as "First Name", c.lastname as "Last Name", c.customerid AS "Customer ID", i.invoiceid AS "In voice ID"
FROM Customer c
inner JOIN Invoice i
ON c.customerid = i.invoiceid;

--- 7.2 Outer Join
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER FULL OUTER JOIN INVOICE
ON customer.customerid = invoice.customerid;
 
 
 --7.3 right join
 SELECT ARTIST.NAME, ALBUM.TITLE
 FROM ALBUM RIGHT JOIN ARTIST
 ON ARTIST.ARTISTID = ALBUM.ARTISTID;
 
 --7.4 CROSS JOIN
 SELECT ARTIST.NAME, ALBUM.TITLE
 FROM ALBUM CROSS JOIN ARTIST 
 ORDER BY ARTIST.NAME ASC;
 
 --7.5 SELF
 SELECT *
 FROM EMPLOYEE E1, EMPLOYEE E2
 WHERE E1.REPORTSTO = E2.REPORTSTO;
 