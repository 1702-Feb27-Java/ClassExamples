--Nicholas Perez
--Database for TRMS project

--Creating lookup tables first before main tables

create table Approval_Status
(
  Approval_id NUMBER,
  current_level VARCHAR2(25),
  PRIMARY KEY (Approval_id)
);

CREATE TABLE Attachments
(
  attach_id NUMBER,
  file_location VARCHAR2(25),
  reim_id NUMBER,
  PRIMARY KEY(attach_id),
  FOREIGN KEY (reim_id) REFERENCES Reimburstment(reim_id)
);

CREATE TABLE ROLE
(
  role_id NUMBER,
  role_name VARCHAR2(25),
  PRIMARY KEY(role_id)
);

CREATE TABLE Grade_Type
(
  grade_id NUMBER,
  grade_type VARCHAR2(25),
  PRIMARY KEY (grade_id)
);

CREATE TABLE Department
(
  dept_id NUMBER,
  Dept VARCHAR2(25),
  PRIMARY KEY (dept_id)
);

CREATE TABLE Course
(
  course_id NUMBER,
  course_type varchar2(25),
  percentage NUMBER,
  PRIMARY KEY(course_id)
);

CREATE TABLE Message
(
  message_id NUMBER,
  message varchar2(400) NOT NULL,
  emp_id NUMBER NOT NULL,
  
  PRIMARY KEY(message_id),
  FOREIGN KEY(emp_id) REFERENCES Employee(emp_id)
); 


CREATE TABLE Employee
(
  fname VARCHAR2(25) NOT NULL,
  lname VARCHAR2(25) NOT NULL,
  dept_id NUMBER NOT NULL, 
  reportsto NUMBER, --fk
  role_id NUMBER NOT NULL, --fk
  emp_id NUMBER NOT NULL, --pk
  username VARCHAR2(25) UNIQUE,
  password VARCHAR2(25) UNIQUE,
  Allowance NUMBER,
  FOREIGN KEY(dept_id) REFERENCES Department(dept_id),
  FOREIGN KEY(reportsto) REFERENCES Employee(emp_id),
  FOREIGN KEY(role_id) REFERENCES Role(role_id),
  PRIMARY KEY (emp_id)

);

CREATE TABLE Reimburstment
(
  emp_id NUMBER NOT NULL,
  location_ VARCHAR2(25),
  Add_Date NUMBER,
  start_date_course NUMBER,
  end_date_course NUMBER,
  time_course NUMBER,
  course_cost NUMBER,
  reimburst_amt NUMBER,
  reim_id NUMBER, 
  Approval_id NUMBER, 
  course_id NUMBER, 
  grade_type_id NUMBER, 
  grade VARCHAR2(25),
  FOREIGN KEY(Approval_id) REFERENCES Approval_status(Approval_id),
  FOREIGN KEY(course_id) REFERENCES Course(course_id),
  FOREIGN KEY(grade_type_id) REFERENCES Grade_Type(grade_id),
  FOREIGN KEY(emp_id) REFERENCES Employee(emp_id),
  PRIMARY KEY (reim_id)
  
);

--Going to insert Employees one of regular employee, two department heads, two direct supervisors
--and two BenCo employees, But will need to populate lookup tables first

INSERT INTO ROLE(ROLE_ID, ROLE_NAME) VALUES(1, 'Employee');
INSERT INTO ROLE(ROLE_ID, ROLE_NAME) VALUES(2, 'Direct Supervisor');
INSERT INTO ROLE(ROLE_ID, ROLE_NAME) VALUES(3, 'Department Head');
INSERT INTO ROLE(ROLE_ID, ROLE_NAME) VALUES(4, 'Benefits Coordinator');

--inserting into the grade_type table
INSERT INTO GRADE_TYPE(GRADE_ID, GRADE_TYPE) VALUES(1, 'A,B,C,D,F');
INSERT INTO GRADE_TYPE(GRADE_ID, GRADE_TYPE) VALUES(2, 'Pass/Fail');

--insert courses type info for reimbursment
INSERT INTO COURSE(COURSE_ID, COURSE_TYPE, PERCENTAGE) VALUES(1, 'University Course', 80);
INSERT INTO COURSE(COURSE_ID, COURSE_TYPE, PERCENTAGE) VALUES(2, 'Seminars', 60);
INSERT INTO COURSE(COURSE_ID, COURSE_TYPE, PERCENTAGE) VALUES(3, 'Certification Prep Class', 75);
INSERT INTO COURSE(COURSE_ID, COURSE_TYPE, PERCENTAGE) VALUES(4, 'Certification', 100);
INSERT INTO COURSE(COURSE_ID, COURSE_TYPE, PERCENTAGE) VALUES(5, 'Technical Training', 90);
INSERT INTO COURSE(COURSE_ID, COURSE_TYPE, PERCENTAGE) VALUES(6, 'Other', 30);

--putting info into the Approval table
INSERT INTO APPROVAL_STATUS(APPROVAL_ID, CURRENT_LEVEL) VALUES(1, 'Pending');
INSERT INTO APPROVAL_STATUS(APPROVAL_ID, CURRENT_LEVEL) VALUES(2, 'Approved');
INSERT INTO APPROVAL_STATUS(APPROVAL_ID, CURRENT_LEVEL) VALUES(3, 'Denied');
INSERT INTO APPROVAL_STATUS(APPROVAL_ID, CURRENT_LEVEL) VALUES(4, 'Urgent');
INSERT INTO APPROVAL_STATUS(APPROVAL_ID, CURRENT_LEVEL) VALUES(5, 'More Information required');

--putting info into the Department table
INSERT INTO DEPARTMENT(DEPT_ID, DEPT) VALUES(1, 'Development');
INSERT INTO DEPARTMENT(DEPT_ID, DEPT) VALUES(2, 'Human Resources');
INSERT INTO DEPARTMENT(DEPT_ID, DEPT) VALUES(3, 'BenCo');
INSERT INTO DEPARTMENT(DEPT_ID, DEPT) VALUES(4, 'Finance');

--Creating sequence for a trigger for the sole pupose of incrementing
--the emp_id in the employee table. This will affect the pojo in java
--so certain fields will be included in the constructor.
CREATE SEQUENCE EMP_ID_SEQ
MINVALUE 1
START WITH 1
INCREMENT by 1;
/

--DROP SEQUENCE EMP_ID_SEQ;
--/

CREATE OR REPLACE TRIGGER EMP_ID_INCRE
BEFORE INSERT 
  ON EMPLOYEE
  FOR EACH ROW
  
BEGIN
  SELECT EMP_ID_SEQ.NEXTVAL
  INTO:new.emp_id 
  FROM DUAL;
END;
/

--DROP TRIGGER EMP_ID_INCRE;

--Creating sequnce for reiembursment id increment
CREATE SEQUENCE REIM_ID_SEQ
MINVALUE 1
START WITH 1
INCREMENT BY 1;
/

--DROP SEQUENCE REIM_ID_SEQ;
--/

--The trigger will then start using the seqeunce for the REIMBURSEMENT ID
CREATE OR REPLACE TRIGGER REIM_ID_INCRE
BEFORE INSERT
  ON REIMBURSTMENT
  FOR EACH ROW

BEGIN
  SELECT REIM_ID_SEQ.NEXTVAL
  INTO:new.REIM_ID 
  FROM DUAL;
END;
/

INSERT INTO EMPLOYEE(FNAME, LNAME, DEPT_ID, ROLE_ID, USERNAME, PASSWORD, ALLOWANCE) 
VALUES('Nicholas', 'Perez', 1, 1, 'nperez', 'admin', 0.0);
INSERT INTO EMPLOYEE(FNAME, LNAME, DEPT_ID, ROLE_ID, USERNAME, PASSWORD, ALLOWANCE) 
VALUES('Chuck', 'Berry', 1, 1, 'cberry', 'admin2',  0.0);
/

--Will need to create procedure for employee sign in
--it will take in a username and "give out" two params or more
CREATE OR REPLACE PROCEDURE EMP_SIGNIN(user_name in VARCHAR2, fname out VARCHAR2, lname out VARCHAR2, dep_id out number, role_id out number,
pass out VARCHAR2, money out number)
IS
  --username that is passed in will get the remaining fields from the employee table
  firstn VARCHAR2(25);
  lastn VARCHAR2(25);
  ID_dep NUMBER;
  ID_role number;
  pw VARCHAR2(25);
  Allow number;
BEGIN
  SELECT FNAME, LNAME, DEPT_ID, ROLE_ID, PASSWORD, ALLOWANCE into
  firstn, lastn, ID_dep, ID_role, pw, Allow FROM EMPLOYEE
  WHERE USERNAME = user_name;
  fname := firstn;
  lname := lastn;
  dep_id := ID_dep;
  role_id := ID_role;
  pass := pw;
  money := Allow;
END;
/

--creating procedure for inserting into the reimbursmnet table 
--NOTE approval id will start at 1 which is pending
--reimbursment amount will zero to begin with
CREATE OR REPLACE PROCEDURE REIMBURSTMENT_APPLY(USERN in VARCHAR, LOCA in VARCHAR, DATE_ADD in NUMBER, course_start in NUMBER, course_end in NUMBER,
course_len in NUMBER, course_cost in NUMBER, app_id in number, course_id in number, grade_id in number, grade in varchar)
IS
  emp_id number;
  --ID_COURSE number;
BEGIN

  --SELECTINT EMP ID FORM EMPLOYEE TABLE
  SELECT EMP_ID INTO emp_id FROM EMPLOYEE where USERNAME = USERN;
  
  INSERT INTO REIMBURSTMENT(EMP_ID, LOCATION_, ADD_DATE, START_DATE_COURSE, END_DATE_COURSE, TIME_COURSE, COURSE_COST,
  APPROVAL_ID, COURSE_ID, GRADE_TYPE_ID, GRADE)
  VALUES(emp_id, LOCA, DATE_ADD, course_start, course_end, course_len, course_cost, 1, course_id, grade_id, grade);
END;
/
