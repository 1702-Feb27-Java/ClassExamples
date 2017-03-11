--Nicholas Perez
--2.0 SQL Queries
--2.1 SELECT all records from the Employee table.

Select * From EMPLOYEE; 

--Select all records from the Employee table where the last name is king

Select * From EMPLOYEE where lastname = 'King';

--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL

Select * from EMPLOYEE where firstname = 'Andrew' and REPORTSTO is NULL;

--2.2 Order by
--Select all albums in Album table and sort result result set in descending order by title.

Select * from  Album
order by TITLE desc;

--Select first name from Customer and sort result in ascending order by city
SELECT firstname from CUSTOMER
order by city asc;

--2.3 INSERT TO
--Insert two records into Genre table

INSERT into GENRE (GENREID, Name) 
values(26, 'Stone');

INSERT into GENRE (GENREID, Name) 
values(27, 'Spanish Folk music');

--Insert two records into Employee table
INSERT into Employee (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE)
values(9, 'Perez', 'Nicholas', 'Jr Software developer');

INSERT into Employee (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE)
values(10, 'Smith', 'John', 'Python Developer');

--insert two records into Customer table
INSERT into CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
VALUES (60, 'Perez', 'Nicholas', 'nicholasp77@gmail.com');

--2.4 Update
--Upadte Araon Mitchell in Customer table to robert walter

UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
where CUSTOMERID = 32;

--update name of artist table "Creedence clearwater Revival" to "CCR"
UPDATE ARTIST
SET NAME = 'CCR'
WHERE ARTISTID = '76';

--2.5 LIKE
--Select all invoices with a billing address like "T%"
SELECT * from INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

--2.6 BETWEEN
--Select all invoices that have a total between 15 and 50
Select * from INVOICE
WHERE TOTAL BETWEEN 15 and 50;

--select all employees hired between 1st of june 2003 and 1st of March 2004
select * from EMPLOYEE
where HIREDATE BETWEEN '01-JUNE-03' and '01-MAR-04';

--2.7 Delete
--Task - Delete a record in Customer table where the name is Robert Walter

ALTER TABLE invoice
  DROP CONSTRAINT FK_INVOICECUSTOMERID;

DELETE FROM CUSTOMER
where FIRSTNAME = 'Robert' and LASTNAME = 'Walter';
 

--3.0 SQL FUNCTIONS
--3.1 System defined functions
--Task - create a function that returns the average total of all invoices

CREATE or REPLACE function get_current_time
  return TIMESTAMP is 
  BEGIN
    return LOCALTIMESTAMP();
END;
/

CALL SYS.DBMS_OUTPUT.PUT_LINE(get_current_time());
/
--create a function that returns the length of a mediatype from the mediatype table

CREATE or REPLACE FUNCTION get_length(m_length IN varchar2)
return NUMBER is
begin
  return length(m_length);
END;
/
DECLARE
varlength varchar2(120);
BEGIN
  select NAME into varlength FROM MEDIATYPE where MEDIATYPEID = 2;
  sys.dbms_output.put_line(get_length(varlength));
end;
/

--3.2 System defined aggregate functions
--Create a function that returns the average total of all invoices

CREATE OR REPLACE FUNCTION avg_total
return NUMBER is
num_avg DECIMAL(10, 2); -- 8 places tot he left with 2 decimals poitns after

BEGIN
  select avg(TOTAL) into num_avg from INVOICE;
  return num_avg;
END;
/
CALL dbms_output.put_line(avg_total);
 /
 
 --Create a function that returns the most expensive track
 Create or replace FUNCTION expen_track
 return NUMBER is
 exp_num number(10, 2);
 
 BEGIN
  select max(UNITPRICE) into exp_num from TRACK;
  return exp_num;
end;
 /
 CALL dbms_output.put_line(expen_track);
 
 
 --3.3 User Defined scalar functions
 --create a function that returns the average price of invoiceline items in the invoiceline table
 CREATE or REPLACE FUNCTION invoice_total
 return number is
 avg_invo number(10, 2);
 
 BEGIN
  Select avg(UNITPRICE) into avg_invo from INVOICELINE;
  return avg_invo;
end;
/
CALL dbms_output.put_line(invoice_total);

 
--3.4 User Defined Table Valued Functions
--Create a function that returns all employees who born after 1968

CREATE OR REPLACE PROCEDURE getDateCursor( outcursor OUT sys_refcursor )
IS
BEGIN
  open outcursor for
  select * from EMPLOYEE where BIRTHDATE > '09-JAN-68';
END;
/

CREATE OR REPLACE FUNCTION return_birth
return sys_refcursor
is
  cursorVar sys_refcursor;
  
BEGIN
  OPEN cursorVAR FOR
  SELECT BIRTHDATE, FIRSTNAME, LASTNAME from EMPLOYEE where BIRTHDATE > '01-JAN-68';
return cursorVar;
END;
/

DECLARE
  cursorVar sys_refcursor;
  emp_name EMPLOYEE.FIRSTNAME%TYPE;
  emp_lname EMPLOYEE.LASTNAME%TYPE;
  emp_Bdate EMPLOYEE.BIRTHDATE%TYPE;
BEGIN
  cursorVar := RETURN_BIRTH();
  LOOP
    FETCH cursorVar INTO emp_Bdate, emp_name, emp_lname;
    EXIT when cursorVar%NOTFOUND;
    sys.dbms_output.put_line(emp_Bdate || '|' || emp_name || '|' || emp_lname || '|');
  END LOOP;
END;
/
  

--4.0 Stored Procedure
--you will be creating and executing stored procedures 
--you will be creating various types of stored procedures that take inpput and output parameters
--4.1 Basic stored procedure
--create a stored procedure that selects the first and last names of all the employees

--USE CURSORS TO GET MORE THAN ONE ROW

CREATE OR REPLACE PROCEDURE get_first_get_last(out_var OUT sys_REFCURSOR)
--when passing in parameters you do not need to assing them in the procedure
is  
BEGIN
  --open cursor for use
  open out_var FOR
  SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
  
END get_first_get_last;
/

DECLARE
  MyCurvar sys_refcursor;
  emp_name EMPLOYEE.FIRSTNAME%TYPE;
  emp_lname EMPLOYEE.LASTNAME%TYPE;
BEGIN
  --with procedures you do not need to assign name of the procedure to cursor varaible
  get_first_get_last(MyCurvar);
  LOOP
    FETCH MyCurvar INTO emp_name, emp_lname;
    EXIT when MyCurvar%NOTFOUND;
    sys.dbms_output.put_line(emp_name || '|' || emp_lname || '|');
  END LOOP;
END;
/

--create a stored procedure that updates the personal information of an employee
CREATE OR REPLACE PROCEDURE update_emp(some_id in number, some_lname in varchar2, some_fname in varchar2, some_title in VARCHAR2)
IS
BEGIN
   UPDATE EMPLOYEE SET EMPLOYEEID = some_id, LASTNAME = some_lname, FIRSTNAME = some_fname, TITLE = some_title where EMPLOYEEID = 9;
END;
/

BEGIN
  update_emp(9, 'Perez', 'Nick', 'janitor');
END;
/

--create a stored procedure that returns the managers of an employee
CREATE OR REPLACE PROCEDURE get_manager(some_id in number, outcur OUT sys_REFCURSOR)
IS
BEGIN
  OPEN outcur for
  SELECT E1.FIRSTNAME, E1.LASTNAME FROM EMPLOYEE E1, EMPLOYEE E2 WHERE E1.REPORTSTO = E2.EMPLOYEEID and E1.EMPLOYEEID = some_id;
END;
/

DECLARE
  thisvar SYS_REFCURSOR;
  emp_name EMPLOYEE.FIRSTNAME%TYPE;
  emp_lname EMPLOYEE.LASTNAME%TYPE;
  BEGIN
  get_manager(2, thisvar);
  LOOP
    FETCH thisvar INTO emp_name, emp_lname;
    EXIT WHEN thisvar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || emp_name || '|' || emp_lname); 
  END LOOP;
END;
/
