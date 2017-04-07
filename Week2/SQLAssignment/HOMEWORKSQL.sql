--Problem #1.0---------
--implemented
--Problem #2.0---------
SELECT * FROM ALBUM;
SELECT * FROM ARTIST
WHERE ARTIST.NAME LIKE 'D%';
SELECT TITLE FROM ALBUM;
--SELECT * FROM ALBUM;

--Problem #2.1---------
--
SELECT * FROM EMPLOYEE;
--
SELECT * FROM EMPLOYEE
WHERE LASTNAME ='King';
--
SELECT * FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
--Problem #2.2---------
--
SELECT * FROM ALBUM
ORDER BY TITLE DESC;
--
SELECT FIRSTNAME FROM CUSTOMER
ORDER BY CITY ASC;
--Problem #2.3---------



--
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (1, 'Adams', 'Andrew', 'General Manager', TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Jasper Ave NW', 'Edmonton', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'andrew@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (1, 'Adams', 'Andrew', 'General Manager', TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Jasper Ave NW', 'Edmonton', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'andrew@chinookcorp.com');

--INSERT INTO Genre (GenreId, Name) VALUES (1, 'Rock');
--Problem #2.4---------
Update  Customer
set FIRSTNAME = 'Robert', LASTNAME = 'Walter'
where FIRSTNAME = 'Aaron' and LASTNAME = 'Mitchell';

Update  Artist
set NAME = 'CCR'
where name = 'Creedence Clearwater Revival';


--Problem #2.5---------
select * from INVOICE
where  BILLINGADDRESS Like 'T%';
--Problem #2.6---------
select * from INVOICE
WHERE TOTAL BETWEEN 15 AND 30;

select * from EMPLOYEE
where HIREDATE between '01-JUN-2003' and  '01-MAR-2004';

--Problem #2.7---------
ALTER TABLE Invoice 
 drop CONSTRAINT fk_invoicecustomerid;

 delete from customer
 where firstname = 'Robert' and lastname = 'Walter';
 
--Problem #3.0---------

--Problem #3.1---------
Select localtimestamp from dual;


create or replace function getTimey
return timestamp
is
begin

return current_timestamp();
end gettimey;
/
begin
SYS.DBMS_OUTPUT.PUT_LINE(getTimey());
end;
/



------------------------------------------


create or replace function getLeng(typer IN VARCHAR2)
return NUMBER
is
begin
return LENGTH(typer);
end;
/
declare varvar
varchar2(120);

begin
select MEDIATYPE.NAME into varvar from MEDIATYPE
where MEDIATYPEID = 4;
SYS.DBMS_OUTPUT.PUT_LINE(getleng(varvar));
end;
/

--begin
---SYS.DBMS_OUTPUT.PUT_LINE();

--end;
/



--Problem #3.2---------
create or replace function  calcavg
return number
is
definedtotal DECIMAL(12,2);
begin 
SELECT AVG(TOTAL) INTO definedtotal FROM INVOICE ;
RETURN definedtotal;
end;
/

begin
SYS.DBMS_OUTPUT.PUT_LINE(calcavg());
end;
/
--Problem #3.3---------
create or replace function  calcmax
return number
is
definedtotal DECIMAL(12,2);
begin 
SELECT MAX(UNITPRICE) INTO definedtotal FROM TRACK  ;
RETURN definedtotal;
end;
/

begin
SYS.DBMS_OUTPUT.PUT_LINE(calcmax());
end;

create or replace function  
return number
is
definedtotal DECIMAL(12,2);
begin 
SELECT MAX(UNITPRICE) INTO definedtotal FROM TRACK  ;
RETURN definedtotal;
end;
/

begin
SYS.DBMS_OUTPUT.PUT_LINE(calcmax());
end;
--Problem #3.4---------

create or replace funcPull
RETURN SYS_REFCURSOR
IS
    pointerCurOut SYS_REFCURSOR;
BEGIN
    OPEN pointerCurOut FOR 
    SELECT firstname, lastname, birthdate FROM employee WHERE birthdate > '01-JAN-68';
    RETURN pointerCurOut;
END;
/
DECLARE
  pointerCur SYS_REFCURSOR;
  lastname employee.birthdate%TYPE;
  birthdate employee.birthdate%TYPE;
  firstname employee.firstdate%TYPE;
BEGIN
    pointerCur:=funcpull();     
  LOOP
    FETCH pointerCur INTO firstname, lastname, birthdate;
    dbms_output.put_line(firstname || ' | ' || lastname || ' | ' || birthdate);
  END LOOP;
END;

/

/

--Problem #4.0---------


--Problem #4.1---------

drop procedure employee_tt
/
 CREATE OR REPLACE PROCEDURE employee_tt
 IS 
  CURSOR emp_cur IS
    
  SELECT firstname, lastname FROM employee;
emp_rec emp_cur%rowtype;
 BEGIN 
  FOR emp_rec in emp_cur 
  LOOP 
  dbms_output.put_line(emp_REC.firstname || ' ' ||emp_REC.lastname);
 END LOOP;
END;
 /
exec employee_tt
/
--Problem #4.2---------
drop procedure employee_asd
/
 CREATE OR REPLACE PROCEDURE rollout_tt (IN toop)
 IS 
  CURSOR emp_cur IS
  SELECT firstname, lastname FROM employee;
  emp_rec emp_cur%rowtype;
 BEGIN 
  FOR emp_rec in emp_cur 
  LOOP 
  dbms_output.put_line(emp_REC.lastname || ' ' ||emp_REC.lastname);
 END LOOP;
END;
 /
exec rollout_tt

/

 CREATE OR REPLACE PROCEDURE rollout_manager 
 IS 
  CURSOR emp_cur IS
  SELECT firstname, lastname, title FROM employee
  where Title like '%Manager%'  ;
  emp_rec emp_cur%rowtype;
 BEGIN 
  FOR emp_rec in emp_cur 
  LOOP 
  dbms_output.put_line(emp_REC.firstname || ' ' ||emp_REC.lastname|| ' ' || emp_REC.title);
 END LOOP;
END;



 /
exec rollout_manager


--Problem #4.3---------
drop procedure rollout_companyname;
/
 CREATE OR REPLACE PROCEDURE rollout_companyname
 IS 
  CURSOR emp_cur IS
  SELECT firstname,lastname,company from customer
  where company is not null;
  emp_rec emp_cur%rowtype;
 BEGIN 
  FOR emp_rec in emp_cur 
  LOOP 
  dbms_output.put_line(emp_REC.firstname || ' - ' ||emp_REC.lastname|| ' - ' || emp_REC.company);
 END LOOP;
END;
/
exec rollout_companyname

--Problem #5.0---------


CREATE OR REPLACE PROCEDURE deleting_inv(invoiceidn IN NUMBER)
IS
BEGIN
    DELETE FROM invoice
    WHERE invoiceid = invoiceidn;
    COMMIT;
END;

BEGIN
    delete_invoice(21);
END;


--Problem #6.0---------


--Problem #6.1---------
 
 CREATE OR REPLACE TRIGGER triggerEMP
      AFTER 
        INSERT OR 
        UPDATE OR 
        DELETE 
      ON employee
      FOR EACH ROW;  
 
 CREATE OR REPLACE TRIGGER triggerCST
      AFTER 
        INSERT OR 
        UPDATE OR 
        DELETE 
      ON customer
      FOR EACH ROW;  
      
    
 CREATE OR REPLACE TRIGGER trigger atriggerALB
      AFTER 
        INSERT OR 
        UPDATE OR 
        DELETE 
      ON album
      FOR EACH ROW ; 
      
   

-- #7.0---------


--Problem #7.1---------

SELECT Customer.FIRSTNAME, Customer.LASTNAME,Invoice.InvoiceID
FROM Customer
INNER JOIN INVOICE
ON Customer.CustomerID=Invoice.Customerid
ORDER BY Customer.Firstname;

--Problem #7.2---------


SELECT Customer.FIRSTNAME, Customer.LASTNAME,Invoice.TOTAL,Invoice.InvoiceID
FROM Customer
Full Outer JOIN INVOICE
ON Customer.CustomerID=Invoice.Customerid
ORDER BY Customer.Firstname;

--Problem #7.3---------
SELECT Album.artistid,artist.artistid,album.title,artist.Name
FROM artist
RIGHT JOIN Album
ON artist.artistid=album.artistid
ORDER BY artist.name;


--Problem #7.4---------

SELECT * 
FROM artist 
CROSS JOIN album
order by  artist.name ASC;

--Problem #7.5---------


 SELECT *
 FROM employee emp
 ,employee emp1
 WHERE emp.reportsto = emp1.reportsto;
 
 --Problem #9.0---------
