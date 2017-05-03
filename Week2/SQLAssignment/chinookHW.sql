--2.1 *SQL Queries*
--2.1.1 - Select all records from the Employee Table
SELECT * FROM EMPLOYEE;
--2.1.2 - Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
--2.1.3 - Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND  REPORTSTO IS NULL;

--2.2 *ORDER BY*
--2.2.1
SELECT * FROM ALBUM ORDER BY TITLE DESC;
--2.2.2
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;

--2.3 *INSERT INTO*
--2.3.1
INSERT INTO GENRE (GENREID, NAME) VALUES (255, 'FunkoPop');
INSERT INTO GENRE (GENREID, NAME) VALUES (256, 'PunkoJazz');
select * from genre;
--2.3.2
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE,
HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES
(2552, 'Grogan', 'Xavier', 'Developer', 2, '18-JUN-91', '27-FEB-17', '1544 Ratliff Rd.',
'Raymond', 'MS', 'USA', '39154', '6014055584', '0000000000', 'xavier.grogan@aol.com');
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE,
HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES
(2553, 'Vaughn', 'Carrie', 'Mother', 2, '28-JUN-71', '18-JUNE-91', '1544 Ratliff Rd.',
'Raymond', 'MS', 'USA', '39154', '6012788703', '0000000000', 'carrie.vaughn@aol.com');
select * from employee;
--2.3.3
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY,
POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES (2552, 'Xavier', 'Grogan', 'Revature', '1544 Ratliff Rd.', 'Raymond', 'MS',
'USA', '39154', '6014055584', '0000000000', 'xavier.grogan@aol.com', 2);
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY,
POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES (2553, 'Carrie', 'Vaughn', 'Mother', '1544 Ratliff Rd.', 'Raymond', 'MS',
'USA', '39154', '6012788703', '0000000000', 'carrie.vaughn@aol.com', 2);
select * from customer;

--2.4 *UPDATE*
--2.4.1
UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
select * from customer where firstname = 'Robert';
--2.4.2
UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';
select * from artist where name = 'CCR';

--2.5 *LIKE*
--2.5.1
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

--2.6 *BETWEEN*
--2.6.1
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 30;
--2.6.2
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7 *DELETE*
--2.7.1
ALTER TABLE INVOICE DROP CONSTRAINT FK_INVOICECUSTOMERID;
ALTER TABLE INVOICELINE DROP CONSTRAINT FK_INVOICELINEINVOICEID;
ALTER TABLE INVOICE ADD CONSTRAINT FK_INVOICECUSTOMERID 
FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER (CUSTOMERID) ON DELETE CASCADE;
ALTER TABLE INVOICELINE ADD CONSTRAINT FK_INVOICELINEINVOICEID
FOREIGN KEY (INVOICEID) REFERENCES INVOICE (INVOICEID) ON DELETE CASCADE;
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
select * from customer where firstname = 'Robert' and lastname = 'Walter';

--3.1 *System Definded Functions*
--3.1.1
/
CREATE OR REPLACE FUNCTION getTime
return TIMESTAMP 
is
begin
    return localtimestamp();
end getTime;
/
begin
    SYS.DBMS_OUTPUT.PUT_LINE(getTime());
end;
--3.1.2
/
create or replace function getTheLength(temp in varchar2)
return number
is
begin
    return length(temp);
end getTheLength;
/
declare outerTemp
varchar2(120);

begin
    select name into outerTemp from mediatype where mediatypeid = 2;
    sys.dbms_output.put_line(getTheLength(outerTemp));
end;
/

--3.2 *System Defined Aggregate Functions*
--3.2.1
/
    create or replace function totes
    return number
    is
    temp number(10,2);
    begin
        select avg(total) into temp from invoice;
        return temp;
    end totes;
/
begin
    sys.dbms_output.put_line(totes());
end;
/
--3.2.2
/
create or replace function pricey
return number
is
temp number(10,2);
begin
    select max(unitprice) into temp from track;
    return temp;
end pricey;
/
begin
    sys.dbms_output.put_line(pricey());
end;
/

--3.3 *User Defined Scalar Functions*
--3.3.1
/
create or replace function avgprice
return number
is
temp number(10,2);
begin
    select avg(unitprice) into temp from invoiceline;
    return temp;
end avgprice;
/
begin
    sys.dbms_output.put_line(avgprice());
end;
/

--3.4 *User Defined Table Valued Functions*
--3.4.1
/
CREATE OR REPLACE PROCEDURE getCursors(outCursor OUT SYS_REFCURSOR)
IS
BEGIN
  open outCursor for 
  select lastname from employee where birthdate > '31-DEC-68';
END;
/

create or replace function theseNames
return sys_refcursor
is
cursorVar sys_refcursor;
lastNames employee.lastname%type;
begin
getCursors(cursorVar);
loop
    fetch cursorVar into lastnames;
    exit when cursorVar%notfound;
    sys.dbms_output.put_line('|' || lastnames || '|');
end loop;
end theseNames;
/
declare
begin
    select theseNames from employee;
end;
/
--4.1 *Basic Stored Procedure*
--4.1.1
/
CREATE OR REPLACE PROCEDURE someNames(outCursor OUT SYS_REFCURSOR)
IS
BEGIN
  open outCursor for 
  select firstname, lastname from employee;
END;
/
declare
    cursorVar sys_refcursor;
    firstName employee.firstname%type;
    lastName employee.lastname%type;
begin
    someNames(cursorVar);
    loop
        fetch cursorVar into firstname, lastname;
        exit when cursorVar%notfound;
        sys.dbms_output.put_line('|' || firstname || '|' || lastname || '|');
    end loop;
end;
/

--4.2 *Stored Procedure Input Parameters*
--4.2.1
/
CREATE OR REPLACE PROCEDURE updateName(fname in employee.firstname%type, lname in employee.lastname%type, idd in employee.employeeid%type)
IS
BEGIN 
    update employee set firstname = fname where employeeid = idd;
    update employee set lastname = lname where employeeid = idd;
END;
/
begin
    updatename('ryan', 'reynolds', 2552);
end;
/
select * from employee;
--4.2.2
/
CREATE OR REPLACE PROCEDURE getManagers(outCursor OUT SYS_REFCURSOR, idd in employee.employeeid%type)
IS
BEGIN 
    open outCursor for 
    select firstname, lastname from employee where employeeid = (select reportsto from employee where employeeid = idd);
END;
/
declare
    cursorVar sys_refcursor;
    firstName employee.firstname%type;
    lastName employee.lastname%type;
begin
    getManagers(cursorVar, 2);
    loop
        fetch cursorVar into firstname, lastname;
        exit when cursorVar%notfound;
        sys.dbms_output.put_line('|' || firstname || '|' || lastname || '|');
    end loop;
end;
/

--4.3 *Stored Procedure Output Parameters*
--4.3.1
/
CREATE OR REPLACE PROCEDURE thisCustomer(outCursor OUT SYS_REFCURSOR)
IS
BEGIN
  open outCursor for 
  select firstname, company from customer;
END;
/
declare
    cursorVar sys_refcursor;
    firstName customer.firstname%type;
    company customer.company%type;
begin
    thisCustomer(cursorVar);
    loop
        fetch cursorVar into firstname, company;
        exit when cursorVar%notfound;
        sys.dbms_output.put_line('|' || firstname || '|' || company || '|');
    end loop;
end;
/
--5.0 *Transactions*


--6.0 *Triggers*
--6.1
--6.1.1
create or replace trigger empInsert
after insert on employee
for each row

begin
    sys.dbms_output.put_line('Fired!!!!!!!!!!!');
end;
/
insert into employee (employeeid, lastname, firstname) values (255252, 'hahahaha', 'ksdlfngsdkjlmfg');
--6.1.2
create or replace trigger albumUpdate
after update on album
for each row
begin
    sys.dbms_output.put_line('Fired!!!!!!!!!!!');
end;
/
update album set title = 'HACKERS' where albumid = 2;
--6.1.3
create or replace trigger customerDelete
after delete on customer
for each row
begin
    sys.dbms_output.put_line('FIRED!!!!!!!!!!!!');
end;
/
delete from customer where customerid = 2553;

--7.0 *JOINS*
--7.1
select customer.firstname, customer.lastname, invoice.invoiceid from customer inner join invoice on customer.customerid = invoice.customerid;
--7.2
select customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total from customer 
left outer join invoice on customer.customerid = invoice.customerid;
--7.3
select artist.name, album.title from album right join artist on artist.artistid = album.artistid;
--7.4
select artist.name from artist cross join album order by artist.name asc;
--7.5
select a.lastname, a.firstname, b.lastname, b.firstname from employee a, employee b where a.reportsto < b.reportsto;