
DROP TABLE Message;
/
DROP TABLE Attachment;
/
DROP TABLE Reimbursement;
/
DROP TABLE Employee;
/
DROP TABLE Role;
/
DROP TABLE Department;
/
DROP TABLE Status;
/
DROP TABLE Grading;
/
DROP TABLE Passing_Grade;
/
DROP TABLE Type_of_event;
/
DROP TABLE Urgent;
/
DROP TABLE Location;
/
DROP TABLE Approval_step;
/
DROP SEQUENCE employee_seq;
/
DROP SEQUENCE reimbursement_seq;
/

--Create Lookup Tables--
CREATE TABLE ROLE
(
  role_id NUMBER,
  role VARCHAR2(25) NOT NULL,
  
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);
/
CREATE TABLE STATUS
(
  status_id NUMBER,
  status VARCHAR2(25) NOT NULL,
  
  CONSTRAINT s_id_pk PRIMARY KEY(status_id)
);
/
CREATE TABLE GRADING
(
  grading_id NUMBER,
  grading VARCHAR2(25) NOT NULL,
  presentatin NUMBER(1) DEFAULT(0),
  
  CONSTRAINT g_id_pk PRIMARY KEY(grading_id)
);
/
CREATE TABLE DEPARTMENT
(
  dept_id number,
  dept varchar(25),
  
  CONSTRAINT dept_id_pk PRIMARY KEY(dept_id)
);
/
CREATE TABLE PASSING_GRADE
(
  grading_id NUMBER,
  passing_grade VARCHAR2(25) NOT NULL,
  
  CONSTRAINT pg_id_pk PRIMARY KEY(grading_id)
);
/
CREATE TABLE TYPE_OF_EVENT
(
  type_of_event_id NUMBER,
  type_of_event VARCHAR2(25) NOT NULL,
  
  CONSTRAINT t_id_pk PRIMARY KEY(type_of_event_id)
);
/
CREATE TABLE URGENT
(
  urgent_id NUMBER,
  urgent VARCHAR2(25) NOT NULL,
  
  CONSTRAINT u_id_pk PRIMARY KEY(urgent_id)
);
/
CREATE TABLE LOCATION
(
  location_id NUMBER,
  location VARCHAR2(25) NOT NULL,
  
  CONSTRAINT loc_id_pk PRIMARY KEY(location_id)
);
/
CREATE TABLE APPROVAL_STEP
(
  approval_step_id NUMBER,
  approval_step VARCHAR2(25) NOT NULL,
  
  CONSTRAINT a_id_pk PRIMARY KEY(approval_step_id)
);
/
--Populate lookup tables--
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (1, 'Employee');
/
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (2, 'Direct Supervisor');
/
INSERT INTO Role(ROLE_ID, ROLE)
VALUES (3, 'Department Head');
/
INSERT INTO Department(dept_id, dept)
VALUES (1, 'Benefits Coordination');
/
INSERT INTO Department(dept_id, dept)
VALUES (2, 'HR');
/
INSERT INTO Department(dept_id, dept)
VALUES (3, 'Accounting');
/
INSERT INTO Department(dept_id, dept)
VALUES (4, 'Marketing');
/
INSERT INTO Status(Status_id, status)
VALUES (1, 'Pending');
/
INSERT INTO Status(Status_id, status)
VALUES (2, 'Approved');
/
INSERT INTO Status(Status_id, status)
VALUES (3, 'Denied');
/
INSERT INTO Status(Status_id, status)
VALUES (4, 'Paid Out');
/
INSERT INTO Grading(grading_id, grading)
VALUES (1, 'A-F');
/
INSERT INTO Grading(grading_id, grading)
VALUES (2, 'Pass/Fail');
/
INSERT INTO Grading(grading_id, grading)
VALUES (3, 'Six point system');
/
INSERT INTO Passing_grade(grading_id, passing_grade)
VALUES (1, 'C');
/
INSERT INTO Passing_grade(grading_id, passing_grade)
VALUES (2, 'Pass');
/
INSERT INTO Passing_grade(grading_id, passing_grade)
VALUES (3, '3');
/
INSERT INTO type_of_event(type_of_event_id, type_of_event)
VALUES (1, 'University Courses');
/
INSERT INTO type_of_event(type_of_event_id, type_of_event)
VALUES (2, 'Seminars');
/
INSERT INTO type_of_event(type_of_event_id, type_of_event)
VALUES (3, 'Cert. Prep. Classes');
/
INSERT INTO type_of_event(type_of_event_id, type_of_event)
VALUES (4, 'Certification');
/
INSERT INTO type_of_event(type_of_event_id, type_of_event)
VALUES (5, 'Technical Training');
/
INSERT INTO type_of_event(type_of_event_id, type_of_event)
VALUES (6, 'Other');
/
INSERT INTO Urgent(urgent_id, urgent)
VALUES (1, 'Urgent');
/
INSERT INTO Urgent(urgent_id, urgent)
VALUES (2, 'Not Urgent');
/
INSERT INTO Location(location_id, location)
VALUES (1, 'Stanford');
/
INSERT INTO Location(location_id, location)
VALUES (2, 'UCLA');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (1, 'Employee');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (2, 'Direct Supervisor');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (3, 'Department Head');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (4, 'Benefits Coordinator');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (5, 'Waiting on Grade');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (6, 'Approved');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (7, 'Denied');
/
INSERT INTO Approval_step(approval_step_id, approval_step)
VALUES (8, 'Paid out');
/
--create object tables--
CREATE TABLE Employee 
(
  employee_id NUMBER,
  role_id NUMBER NOT NULL,
  dept_id NUMBER NOT NULL,
  supervisor_id number,
  first_name varchar2(30) NOT NULL,
  last_name varchar2(30) NOT NULL,
  username varchar2(30) UNIQUE NOT NULL,
  pass varchar2(30) NOT NULL,
  
  CONSTRAINT emp_id_pk PRIMARY KEY(employee_id),
  CONSTRAINT sup_id_fk FOREIGN KEY(supervisor_id) REFERENCES Employee(employee_id),
  CONSTRAINT r_id_fk FOREIGN KEY(role_id) REFERENCES Role(role_id),
  CONSTRAINT dep_id_fk FOREIGN KEY(dept_id) REFERENCES Department(dept_id)
); 
/
CREATE TABLE Message 
(
  message_id NUMBER,
  message varchar2(200) NOT NULL,
  emp_id NUMBER NOT NULL,
  
  CONSTRAINT msg_id_pk PRIMARY KEY(message_id),
  CONSTRAINT emplo_id_fk FOREIGN KEY(emp_id) REFERENCES Employee(employee_id)
); 
/
CREATE TABLE Reimbursement 
(
  reimb_id NUMBER,
  status_id NUMBER DEFAULT(1) NOT NULL,
  employee_id NUMBER NOT NULL,
  event varchar2(30) NOT NULL,
  event_date varchar2(10) NOT NULL,
  event_time varchar2(10) NOT NULL,
  location_id number(10) NOT NULL,
  form_date varchar2(10) NOT NULL,
  event_description varchar2(30) NOT NULL, 
  event_cost varchar2(30) NOT NULL,
  grading_id number NOT NULL,
  type_of_event_id number NOT NULL,
  urgent_id number NOT NULL,
  final_grade varchar2(15),
  approval_step_id number NOT NULL,
  approval_cutoff number NOT NULL,
  confirmation number,
  approver_id number,
  
  CONSTRAINT reimb_id_pk PRIMARY KEY(reimb_id),
  CONSTRAINT stat_id_fk FOREIGN KEY(status_id) REFERENCES Status(status_id),
  CONSTRAINT grid_id_fk FOREIGN KEY(grading_id) REFERENCES Grading(grading_id),
  CONSTRAINT TypeEvent_id_fk FOREIGN KEY(type_of_event_id) REFERENCES TYPE_OF_EVENT(type_of_event_id),
  CONSTRAINT UrgId_id_fk FOREIGN KEY(urgent_id) REFERENCES Urgent(urgent_id),
  CONSTRAINT eloc_id_fk FOREIGN KEY(location_id) REFERENCES Location(location_id),
  CONSTRAINT empl_id_fk FOREIGN KEY(employee_id) REFERENCES Employee(employee_id),
  CONSTRAINT approver_id_fk FOREIGN KEY(approver_id) REFERENCES Employee(employee_id)
); 
/
CREATE TABLE Attachment
(
  attachment_id number NOT NULL,
  attachment varchar2(40) NOT NULL,
  reim_id NUMBER NOT NULL,
  
  CONSTRAINT attach_id_pk PRIMARY KEY(attachment_id),
  CONSTRAINT reimur_id_fk FOREIGN KEY(reim_id) REFERENCES Reimbursement(reimb_id)
);
/
CREATE SEQUENCE  employee_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE SEQUENCE reimbursement_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER reimbursement_trigger
    BEFORE INSERT ON  Reimbursement -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT reimbursement_seq.NEXTVAL
      INTO :new.reimb_id
      FROM dual;
    END;
/
CREATE OR REPLACE TRIGGER employee_trigger
    BEFORE INSERT ON  Employee -- upon what event
    FOR EACH ROW --how often 
    BEGIN -- start what actually happens
      SELECT employee_seq.NEXTVAL
      INTO :new.employee_id
      FROM dual;
    END;
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 3, 1, 'Mary', 'Conley', 'mConley', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 2, 1, 1, 'William', 'Smith', 'wSmith', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 1, 1, 2, 'Ben', 'Webster', 'benwebsta', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 3, 2, 'Donna', 'Downey', 'dDowney', 'password'); 
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 2, 2, 4, 'Frank', 'Jenkins', 'fJenkins', 'password'); 
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 1, 2, 5, 'Mory', 'Keita', 'mKeita', 'password'); 
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 3, 3, 'Robert', 'Hallock', 'rHallock', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 2, 3, 7, 'John', 'Parker', 'jParker', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 1, 3, 8, 'Jon', 'Lee', 'jLee', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 3, 4, 'Chadwick', 'Pass', 'cPass', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 2, 4, 10, 'Michael', 'Lin', 'mLin', 'password');
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS) 
  VALUES(1, 1, 4, 11, 'Aaron', 'Camm', 'aCamm', 'password');
/
CREATE OR REPLACE PROCEDURE loginEmployee(emp_id OUT number, un IN varchar2, pw OUT varchar2)
IS
  employee_id number(10);
  pas varchar2(20);
BEGIN
  SELECT EMPLOYEE_ID, PASS into employee_id, pas
  FROM EMPLOYEE
  WHERE USERNAME = un
  AND ROLE_ID = 1;
  emp_id := employee_id;
  pw := pas;
END loginEmployee;
/
CREATE OR REPLACE PROCEDURE applyForReimbursement(emp_id IN number, event IN varchar2, event_date IN varchar2, event_time IN varchar2, location_id IN number, form_date IN varchar2, 
description IN varchar2, event_cost IN number, grading_id IN number, type_of_event_id IN number, urgent_id IN number, approval_step_id IN number, approval_cutoff IN number)
IS
BEGIN
  INSERT INTO REIMBURSEMENT(REIMB_ID, STATUS_ID, EMPLOYEE_ID, EVENT, EVENT_DATE, EVENT_TIME, LOCATION_ID, FORM_DATE, EVENT_DESCRIPTION, EVENT_COST, GRADING_ID, TYPE_OF_EVENT_ID,
                              URGENT_ID, APPROVAL_STEP_ID, APPROVAL_CUTOFF)
  VALUES(1, 1, emp_id, event, event_date, event_time, location_id, form_date, description, event_cost, grading_id, 
                  type_of_event_id, urgent_id, approval_step_id, approval_cutoff);
END applyForReimbursement;
/
DECLARE
BEGIN
  APPLYFORREIMBURSEMENT(3, 'CS COURSE', '06/06/2016', '10:30pm', 1, '05/06/2016', 'college course at uw', 500, 1, 1, 0, 1, 7);
END;





