--SQL HOMEWORK PACKET--

-- 2.1 Select --
Select * from EMPLOYEE;
Select * from EMPLOYEE where LASTNAME = 'King';
Select * from EMPLOYEE where FIRSTNAME = 'Andrew' and REPORTSTO is null;

-- 2.2 Order By --
select * from ALBUM order by TITLE desc;
select * from Customer order by CITY asc;

-- 2.3 Insert Into --
insert into genre (GENREID, NAME) values (30, 'Emo');
insert into genre (GENREID, NAME) values (31, 'Orchestral');

insert into EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
values (10, 'Stark', 'Arya', 'Assassin', 6, TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Beach Ave NW', 'Miami', 'FL', 'America', 'PTK B59', '+1 (947) 444-1111', '+1 (947) 444-5555', 'arya@chinookcorp.com');
insert into EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
values (11, 'Stark', 'Eddard', 'Warden of the West', 6, TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Beach Ave NW', 'Miami', 'FL', 'America', 'PEE 541', '+1 (947) 444-1111', '+1 (947) 444-5555', 'edd@chinookcorp.com');

INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (65, 'Bella', 'Rose', '74 Salem Street', 'Boston', 'MA', 'USA', '2113', '+1 (770) 540-1645', 'brose@yahoo.com', 4);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (66, 'Zoey', 'Rose', '74 Salem Street', 'Boston', 'MA', 'USA', '2113', '+1 (770) 540-1879', 'zrose@yahoo.com', 4);

-- 2.4 Update --
Update Customer set FIRSTNAME = 'Robert', LASTNAME = 'Walter' where FIRSTNAME = 'Aaron' and LASTNAME = 'Mitchell';
Update Artist set NAME = 'CCR' where ARTISTID = 76;

-- 2.5 Like --
select * from INVOICE where BILLINGADDRESS like 'T%';

-- 2.6 Between --
select * from INVOICE where TOTAL between 15 AND 50;
select * from EMPLOYEE where HIREDATE between '01-JUN-2003' and '01-MAR-2004';

-- 2.7 Delete -- 
alter table invoice  
  drop constraint FK_INVOICECUSTOMERID;

delete from Customer where FIRSTNAME = 'Robert' and LASTNAME = 'Walter';

------------------------------------------------------------------------
--------------------------------  3.0   --------------------------------
------------------------------------------------------------------------

-- 3.1 System Defined Functions 
/
create or replace function currTime 
return TIMESTAMP is 
  begin 
    return localtimestamp(); 
  end;
  /
  
call SYS.DBMS_OUTPUT.PUT_LINE(currTime());

/
create or replace function findLength (temp in varchar2)
return NUMBER is
  begin
    return Length(temp);
  end;
/


declare 
  penguin varchar2(45);
  begin
    select mediatype.name 
    into penguin 
    from mediatype
    where mediatypeid = 1;
    SYS.DBMS_OUTPUT.PUT_LINE(findLength(penguin));
  end;
/


-- 3.2 System Defined Aggregate Functions 
create or replace function invAvg
return NUMBER is
  bob DECIMAL(10,2);
  begin
    select avg(invoice.total)
    into bob
    from INVOICE;
    return bob;
  end;
/

declare 
  totalAvg DECIMAL(10,2);
  begin
    SYS.DBMS_OUTPUT.PUT_LINE(invAvg());
  end;
/
  
create or replace function maxTr
return NUMBER is
  bobbert DECIMAL(10,2);
  begin
    select max(track.unitprice)
    into bobbert
    from track;
    return bobbert;
  end;
/

  begin
    SYS.DBMS_OUTPUT.PUT_LINE(maxTr());
  end;
/

-- 3.3 User Defined Scalar Functions
create or replace function invLinAvg
return NUMBER is
  bobby DECIMAL(10,2);
  begin
    select avg(invoiceline.unitprice)
    into bobby
    from INVOICELINE;
    return bobby;
  end;
/

declare 
  totalAvge DECIMAL(10,2);
  begin
    SYS.DBMS_OUTPUT.PUT_LINE(invLinAvg());
  end;
/


-- 3.4 User Defined Table Valued Functions
CREATE OR REPLACE FUNCTION getEmpBornAfter(outCur OUT SYS_REFCURSOR)
return SYS_REFCURSOR
IS  
BEGIN     
  open outCur for 
  select employee.firstname, employee.lastname
  FROM employee
  WHERE TO_DATE(birthdate, 'DD/MON/YY') > TO_DATE('01/JAN/68', 'DD/MON/YY');
  return outCur;  
END;

declare
  curVar SYS_REFCURSOR;
  firName EMPLOYEE.FIRSTNAME%TYPE;
  lasName EMPLOYEE.LASTNAME%TYPE;
begin
  curVar := getEmpBornAfter(curVar);
  LOOP
    FETCH curVar INTO firName, lasName;
    EXIT WHEN curVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || firName || '|' || lasName); 
  END LOOP;
end;
/


-- 4.1 Basic Stored Procedure
CREATE OR REPLACE PROCEDURE getEmpFirstAndLast(outCur OUT SYS_REFCURSOR)
is    
begin
  open outCur for
  select firstname, lastname from employee;
end;

declare
  curVar SYS_REFCURSOR;
  FirName EMPLOYEE.FIRSTNAME%TYPE;
  LasName EMPLOYEE.LASTNAME%TYPE;
begin
  getEmpFirstAndLast(curVar);
  LOOP
    FETCH curVar INTO FirName, LasName;
    EXIT WHEN curVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || FirName || '|' || LasName); 
  END LOOP;
end;

--4.2 Stored Procedure Input Paramaters
CREATE OR REPLACE PROCEDURE getUpdateEmpInfo(Emp_id in number, cityName in varchar2, stateName in varchar2, countryName in varchar2)
is    
begin
  UPDATE EMPLOYEE 
  SET CITY = cityName, STATE = stateName, COUNTRY = countryName
  WHERE EMPLOYEEID = Emp_id;
end;

declare
begin
  getUpdateEmpInfo(1, 'Windermere', 'Florida', 'America');
end;
/

select * from Employee where employeeid = 1;


CREATE OR REPLACE PROCEDURE getManagers(emp_id in NUMBER, outCur OUT SYS_REFCURSOR)
is    
begin
  open outCur for
  select  E2.FIRSTNAME, E2.LASTNAME 
  from EMPLOYEE e1, EMPLOYEE e2 
  where e1.REPORTSTO = E2.EMPLOYEEID 
  AND E1.EMPLOYEEID = emp_id;  
end;

declare
  curVar SYS_REFCURSOR;
  FirName EMPLOYEE.FIRSTNAME%TYPE;
  LasName EMPLOYEE.LASTNAME%TYPE;
begin
  getManagers(2, curVar);
  LOOP
    FETCH curVar INTO FirName, LasName;
    EXIT WHEN curVar%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('|' || FirName || '|' || LasName); 
  END LOOP;
end;

CREATE OR REPLACE PROCEDURE getNameAndCompany(cust_id in number, fir_name OUT varchar2, las_name OUT varchar2, companyName OUT varchar2)
is    
begin
  SELECT FIRSTNAME, LASTNAME, COMPANY INTO fir_name, las_name, companyName
  FROM CUSTOMER
  WHERE CUSTOMERID = cust_id;
end;

declare
  firName varchar2(4000);
  lasName varchar2(4000);
  companyName varchar2(4000);
begin
  getNameAndCompany(1, firName, lasName, companyName);
  DBMS_OUTPUT.PUT_LINE('|' || firName || '|' || lasName || '|' || '|' || companyName); 
end;
/
-- 5.0 Transactions

set TRANSACTION 'trans';
delete from invoiceline where INVOICELINE.INVOICEID = 213;
DELETE FROM INVOICE WHERE INVOICEID = 213;
commit;

CREATE OR REPLACE PROCEDURE CustomerTransaction(id_number in number, fir_Name in varchar2, las_Name in varchar2, e_mail in varchar2,cust_Name in varchar2, address_Name in varchar2, city_name in varchar2, state_name in varchar2, county_name in varchar2, post_code in varchar2, phone_num in varchar2, fax in number, support in number)
is    
begin   
  INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
  VALUES (id_number ,fir_Name,las_Name,cust_Name,address_Name,city_name,state_name,county_name,post_code,phone_num,fax,e_mail,support);
  COMMIT;
end;

-- 6.0 Triggers


-- 7.0 Joins

--7.1 Inner
SELECT cust.firstname as "First Name", cust.lastname as "Last Name", cust.customerid AS "Customer ID", inv.invoiceid AS "In voice ID"
FROM Customer cust
inner JOIN Invoice inv
ON cust.customerid = inv.invoiceid;

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