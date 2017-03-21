--Nicholas Perez
--Database for TRMS project

--Creating lookup tables first before main tables
create table Approval
(
  Approval_id NUMBER,
  current_level NUMBER,
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

CREATE TABLE Employee
(
  fname VARCHAR2(25) NOT NULL,
  lname VARCHAR2(25) NOT NULL,
  address VARCHAR2(25) NOT NULL,
  dept_id NUMBER NOT NULL, 
  reportsto NUMBER, --fk
  role_id NUMBER NOT NULL, --fk
  emp_id NUMBER NOT NULL, --pk
  username VARCHAR2(25) UNIQUE,
  password VARCHAR2(25) UNIQUE,
  email VARCHAR2(25) NOT NULL UNIQUE,
  Allowance NUMBER NOT NULL,
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
  FOREIGN KEY(Approval_id) REFERENCES Approval(Approval_id),
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
INSERT INTO APPROVAL(APPROVAL_ID, CURRENT_LEVEL) VALUES(