/*
SQL : ASSIGNMENT 
DATE: 03/09/2017
SAMSEL DSOUZA
*/

--2.0 SQL QUERIES
--2.1 SELECT
SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE where LASTNAME='King';
SELECT * FROM EMPLOYEE where FIRSTNAME='Andrew' AND REPORTSTO IS NULL;
/
--2.2 ORDER BY
SELECT * FROM EMPLOYEE ORDER BY TITLE DESC;
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;
/
--2.3 INSERT INTO
INSERT INTO GENRE(GENREID,NAME) VALUES(26,'FOLK');
INSERT INTO GENRE VALUES (27,'TECHNO');
/
INSERT INTO EMPLOYEE VALUES(9,'Dsouza','Samsel','Trainee',6,'12-FEB-89','27-FEB-2017','Westwind Farms','Ashburn','Virginia','USA','20144','+1 (571) 7685-8765','+1 (571) 7685-8888','blue@gmail.com');
INSERT INTO EMPLOYEE VALUES(10,'Dsouza','Rachael','SAP Developer',6,'22-FEB-88','08-JUN-2016','Westwind','Ashburn','Virginia','USA','20147','+1 (571) 7666-8765','+1 (571) 7345-8888','rach@gmail.com');
/
INSERT INTO Customer VALUES (60, 'Sunil', 'Cerejo', 'Revature','D/ San Bernard 80', 'Madrid','' ,'Spain', '28015', '+34 914 454 444','+34 954 454 444', 'sunil@yahoo.es', 3);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (61, 'Gene', 'Ivan', 'Celsiusg. 10', 'Stockholm', 'Sweden', '11240', '+46 08-651 52 92', 'Gene@yahoo.se', 5);
/
--2.4 UPDATE 
UPDATE CUSTOMER SET FIRSTNAME='Robert',LASTNAME='Walter' WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';
UPDATE ARTIST SET NAME='CCR' WHERE NAME='Creedence Clearwater Revival';
/
--2.5 LIKE
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
/
--2.7 BETWEEN
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-2003' AND '01-MAR-2004';
/
--2.8 DELETE
/*--DISABLE CONSTRAINT
begin
  for cur in (select fk.owner, fk.constraint_name , fk.table_name 
    from all_constraints fk, all_constraints pk 
     where fk.CONSTRAINT_TYPE = 'R' and 
           pk.owner = 'CHINOOK' and
           fk.r_owner = pk.owner and
           fk.R_CONSTRAINT_NAME = pk.CONSTRAINT_NAME and 
           pk.TABLE_NAME = 'CUSTOMER') loop
    execute immediate 'ALTER TABLE "'||cur.owner||'"."'||cur.table_name||'" MODIFY CONSTRAINT "'||cur.constraint_name||'" DISABLE';
  end loop;
end;
*/
DELETE FROM CUSTOMER WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';

---7.0
--7.1
Select customer.FIRSTNAME as "Customer name", invoice.invoiceid
from customer
inner join invoice on invoice.INVOICEID=customer.CUSTOMERID ORDER BY customer.FIRSTNAME ASC;

--7.2
Select Customer.CustomerID as "ID", Customer.Firstname as "First Name",Customer.lastname as "Last Name",invoice.invoiceid as "Invoice ID" ,invoice.Total as "Total"
from CUSTOMER
full OUTER join invoice on invoice.INVOICEID=customer.CUSTOMERID ORDER BY customer.FIRSTNAME ASC;

--7.3
Select Artist.name as "Artist", album.title as "Album Title"
from Artist
right join Album on Artist.ARTISTID = Album.ARTISTID order by Artist.name ASC;

--7.4
select Artist.name as "Artist", album.ALBUMID as "Album ID"
from Artist
cross join album where artist.ARTISTID=album.ARTISTID order by artist.name asc;

--7.5
select CONCAT((CONCAT(e1.firstname,' ')),e1.Lastname) as "Employee Name", e2.reportsto as "Manager ID",e2.FIRSTNAME as"Manager Name"
from employee e1
inner join employee e2 on e1.REPORTSTO=e2.reportsto order by e1.firstname asc;
/

--6.0 TRIGGERS
--6.1 AFTER/FOR
CREATE OR REPLACE TRIGGER emp_after_insert AFTER INSERT ON EMPLOYEE
  FOR EACH ROW
DECLARE
  v_username varchar2(20);
BEGIN
  DBMS_OUTPUT.PUT_LINE('AFTER INSERT TRIGGERED');
  select user into v_username from dual;
  DBMS_OUTPUT.PUT_LINE('USER : '||v_username);
  
  /* --CREATE BACKUP OF CURRENT TABLE 
  INSERT INTO EMPLOYEE_BACKUP VALUES (:new.EMPLOYEEID,
      :new.LASTNAME,
      :new.FIRSTNAME,
      :new.TITLE,
      :new.REPORTSTO,
      :new.BIRTHDATE,
      :new.HIREDATE,
      :new.ADDRESS,
      :new.CITY,
      :new.STATE,
      :new.COUNTRY,
      :new.POSTALCODE,
      :new.PHONE,
      :new.FAX,
      :new.EMAIL);*/
END;
/

INSERT INTO EMPLOYEE VALUES(11,'Dsouza','Ray','Manager',6,'29-NOV-87','27-FEB-2011','Westwind Farms','Ashburn','Virginia','USA','20154','+1 (571) 7985-8265','+1 (571) 7185-8488','ray@gmail.com');

--AFTER UPDATE TRIGGER
CREATE OR REPLACE TRIGGER album_update_trigger AFTER UPDATE ON ALBUM
FOR EACH ROW
DECLARE
  v_username varchar2(10);
BEGIN
  SELECT USER into v_username FROM DUAL;
  DBMS_OUTPUT.PUT_LINE('USER : '||v_username||' UPDATED TABLE [ALBUM]');
END;

UPDATE ALBUM SET ALBUM.TITLE='BTTW' WHERE ALBUM.TITLE='Balls to the Wall';


--AFTER DELETE TRIGGER
CREATE OR REPLACE TRIGGER cust_after_delete AFTER DELETE ON CUSTOMER
FOR EACH ROW
DECLARE
  V_USERNAME varchar2(20);
BEGIN
SELECT USER INTO V_USERNAME FROM DUAL;
DBMS_OUTPUT.PUT_LINE('USER : '|| V_USERNAME||' DELETED A RECORD FROM TABLE [CUSTOMER]');
END;

DELETE FROM CUSTOMER WHERE FIRSTNAME='Gene';

--4.0  STORED PROCEDURES
--4.1 PROCEDURE TO SELECT FIRST AND LAST NAME OF ALL THE EMPLOYEES
CREATE OR REPLACE PROCEDURE getEmpFName(EmpCur OUT SYS_REFCURSOR)
IS
BEGIN
open EmpCur for 
Select FIRSTNAME,LASTNAME from Employee;
END;
/

DECLARE
empCursor SYS_REFCURSOR;
empFName EMPLOYEE.FIRSTNAME%TYPE;
empLName EMPLOYEE.LASTNAME%TYPE;
BEGIN
getEmpFName(empCursor);
DBMS_OUTPUT.PUT_LINE('EMPLOYEE (FULL NAME)');
DBMS_OUTPUT.PUT_LINE('-------------------------');
LOOP
FETCH empCursor INTO empFName,empLName;
EXIT WHEN empCursor%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(empFName||' '||empLName);
END LOOP;
END;
/

--4.2 Stored Procedure Input Parameters
CREATE OR REPLACE PROCEDURE empUpdate(empID IN EMPLOYEE.EMPLOYEEID%TYPE,
                                      empFName IN EMPLOYEE.FIRSTNAME%TYPE,
                                      empLName IN EMPLOYEE.LASTNAME%TYPE)
IS
BEGIN
UPDATE EMPLOYEE SET FIRSTNAME=empFName, LASTNAME=empLName WHERE EMPLOYEEID=empID;
END;
/

BEGIN
empUpdate(9,'Anna','Deena');
END;
/

CREATE OR REPLACE PROCEDURE empMangers(MCursor OUT SYS_REFCURSOR)
IS
BEGIN
open MCursor for
SELECT DISTINCT e2.EmployeeID , e2.FIRSTNAME , e2.LASTNAME FROM EMPLOYEE e1, EMPLOYEE e2
where e2.EMPLOYEEID=e1.REPORTSTO;
END;
/

DECLARE
MCurs SYS_REFCURSOR;
mgID EMPLOYEE.EMPLOYEEID%TYPE;
mgFName EMPLOYEE.FIRSTNAME%TYPE;
mgLName EMPLOYEE.FIRSTNAME%TYPE;
BEGIN
EMPMANGERS(MCurs);
DBMS_OUTPUT.PUT_LINE('MANAGERS :');
DBMS_OUTPUT.PUT_LINE('--------------------');
DBMS_OUTPUT.PUT_LINE('ID  FULLNAME');
DBMS_OUTPUT.PUT_LINE('--------------------');
LOOP
FETCH MCurs into mgID, mgFName, mgLName;
EXIT WHEN MCurs%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(mgID||'   '||mgFName||' '||mgLName);
END LOOP;
END;

--4.3 Stored Procedure Output Parameters
CREATE OR REPLACE PROCEDURE GetInfo(custID IN CUSTOMER.CUSTOMERID%TYPE,
                                    custFName OUT CUSTOMER.FIRSTNAME%TYPE,
                                    custComp OUT CUSTOMER.COMPANY%TYPE)
IS
BEGIN
Select concat(concat(firstname,' '),lastname) into custFName FROM CUSTOMER where customerid=custID;
Select company into custComp FROM CUSTOMER where customerid=custID;
END;

declare
custName CUSTOMER.FIRSTNAME%TYPE;
company CUSTOMER.COMPANY%TYPE;
BEGIN
GetInfo(60,custName,company);
DBMS_OUTPUT.PUT_LINE('CUSTOMER INFORMATION');
DBMS_OUTPUT.PUT_LINE('-----------------------------');
DBMS_OUTPUT.PUT_LINE('ID  FULLNAME       COMPANY');
DBMS_OUTPUT.PUT_LINE('-----------------------------');
DBMS_OUTPUT.PUT_LINE('60'||'  '||custName||'   '|| company);
END;

--3.0 SQL FUNCTIONS
--3.1 System Defined Functions
--Function that returns time

CREATE OR REPLACE FUNCTION GetTime
return TIMESTAMP
is
currTime TIMESTAMP;
begin
SELECT CURRENT_TIMESTAMP into currTime FROM DUAL;
return currTime;
END GetTime;
/

BEGIN
DBMS_OUTPUT.PUT_LINE('TIME STAMP : '||GETTIME);
END;

--TEST
declare
CurrentDateTime TIMESTAMP;
begin
SELECT CURRENT_TIMESTAMP into CurrentDateTime FROM DUAL;
dbms_output.put_line('TIME STAMP : '||CurrentDateTime);
end;

--TEST
declare
currentDate varchar(20);
begin
SELECT CURRENT_DATE into currentDate FROM DUAL;
dbms_output.put_line('DATE : '||currentDate);
end;
/

CREATE OR REPLACE FUNCTION getLenOfMedia
return NUMBER
IS
countOfMedia NUMBER;
BEGIN
select count(*) into countOfMedia from MEDIATYPE;
return countOfMedia;
END getLenOfMedia;

begin
dbms_output.put_line('Number of MediaType : '||getLenOfMedia);
end;

--3.2 System Defined Aggregate Functions
CREATE OR REPLACE FUNCTION getAvgOfInvoices
return NUMBER
IS
avgOfTInvoices DECIMAL(15,2);
BEGIN
select AVG(TOTAL) into avgOfTInvoices from INVOICE;
return avgOfTInvoices;
END getAvgOfInvoices;

begin
dbms_output.put_line('Average Total of Invoices : '||getAvgOfInvoices);
end;

--Get most expensive track
CREATE OR REPLACE FUNCTION getExpTrack(expTrack out DECIMAL)
RETURN DECIMAL
IS
BEGIN
select MAX(UNITPRICE) INTO expTrack from TRACK;
return expTrack;
END getExpTrack;

begin
dbms_output.put_line('Most Expensive Track  : '||getAvgOfInvoices);
end;

--3.3 User Defined Scalar Functions 
--Return Average price of invoice line items in the invoiceline table
CREATE OR REPLACE FUNCTION getAvgPOfInvoices
return NUMBER
IS
avgOfPInvoices DECIMAL(15,2);
BEGIN
select AVG(UNITPRICE) into avgOfPInvoices from INVOICELINE;
return avgOfPInvoices;
END getAvgPOfInvoices;

begin
dbms_output.put_line('Average Price of Invoice-Line Items : '||getAvgPOfInvoices);
end;
/

--3.3 User Defined Table Valued Functions
--SELECT EXTRACT(YEAR FROM BIRTHDATE) from EMPLOYEE;
/*create or replace Function TFnForEmployee
(@CYear int)
  returns
  @empList table(fname varchar(25))
  as
  begin 
      declare @FLname varchar(25)
      select @FLname=employee.firstname from employee where ((extract(year from birthdate)) > @CYear);
      insert into @Names values(@FLname)
      return 
  end;
  go;
*/

--5.0 Transactions

--Begin the transaction
SET TRANSACTION READ WRITE;
DELETE FROM INVOICE WHERE invoiceid=1;
commit;

create or replace procedure newCust
is
begin
set transaction read write;
INSERT INTO Customer VALUES (61, 'S', 'Lobo', 'Wipro','D/ San Bernard 90', 'Madrid','' ,'Spain', '28065', '+34 914 454 334','+34 334 454 444', 'sLobo@yahoo.es', 3);
commit;
end;

begin
newCust;
end;