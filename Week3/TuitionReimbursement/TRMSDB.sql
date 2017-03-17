
DROP TABLE EmployeeMessage;
/
DROP TABLE ReimbAttach;
/
DROP TABLE EmployeeReimbursement;
/
DROP TABLE Reimbursement;
/
DROP TABLE Employee;
/
DROP TABLE Message;
/
DROP TABLE Attachment;
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
  message NUMBER DEFAULT(0),
  
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
  
  CONSTRAINT msg_id_pk PRIMARY KEY(message_id)
); 
/
CREATE TABLE Reimbursement 
(
  reimb_id NUMBER,
  status_id NUMBER DEFAULT(1) NOT NULL,
  event varchar2(30) NOT NULL,
  event_date number NOT NULL,
  event_time varchar2(30) NOT NULL,
  location_id number(10) NOT NULL,
  form_date varchar2(30) NOT NULL,
  event_description varchar2(30) NOT NULL, 
  event_cost varchar2(30) NOT NULL,
  grading_id number NOT NULL,
  type_of_event_id number NOT NULL,
  urgent_id number NOT NULL,
  final_grade varchar2(15) NOT NULL,
  approval_step_id number NOT NULL,
  approval_time_left number NOT NULL,
  confirmation number NOT NULL,
  approver_id number NOT NULL,
  
  CONSTRAINT reimb_id_pk PRIMARY KEY(reimb_id),
  CONSTRAINT stat_id_fk FOREIGN KEY(status_id) REFERENCES Status(status_id),
  CONSTRAINT grid_id_fk FOREIGN KEY(grading_id) REFERENCES Grading(grading_id),
  CONSTRAINT TypeEvent_id_fk FOREIGN KEY(type_of_event_id) REFERENCES TYPE_OF_EVENT(type_of_event_id),
  CONSTRAINT UrgId_id_fk FOREIGN KEY(urgent_id) REFERENCES Urgent(urgent_id),
  CONSTRAINT eloc_id_fk FOREIGN KEY(location_id) REFERENCES Location(location_id),
  CONSTRAINT approver_id_fk FOREIGN KEY(approver_id) REFERENCES Employee(employee_id)
); 
/
CREATE TABLE Attachment
(
  attachment_id number NOT NULL,
  attachment varchar2(40) NOT NULL,
  
  CONSTRAINT attach_id_pk PRIMARY KEY(attachment_id)
);
/
CREATE TABLE EmployeeReimbursement
(
  employee_id number,
  reimb_id number,
  
  CONSTRAINT empreimb_id_pk PRIMARY KEY(employee_id, reimb_id),
  CONSTRAINT empl_id_fk FOREIGN KEY(employee_id) REFERENCES Employee(employee_id),
  CONSTRAINT reimbur_id_fk FOREIGN KEY(reimb_id) REFERENCES Reimbursement(reimb_id)
);
/
CREATE TABLE EmployeeMessage
(
  employee_id number,
  message_id number,
  
  CONSTRAINT empmsg_id_pk PRIMARY KEY(employee_id, message_id),
  CONSTRAINT employ_id_fk FOREIGN KEY(employee_id) REFERENCES Employee(employee_id),
  CONSTRAINT msg_id_fk FOREIGN KEY(message_id) REFERENCES Message(message_id)
);
/
CREATE TABLE ReimbAttach
(
  reimb_id number,
  attachment_id number,
  
  CONSTRAINT reimbAttach_id_pk PRIMARY KEY(reimb_id, attachment_id),
  CONSTRAINT Reimbu_id_fk FOREIGN KEY(reimb_id) REFERENCES Reimbursement(reimb_id),
  CONSTRAINT attachm_id_fk FOREIGN KEY(attachment_id) REFERENCES Attachment(attachment_id)
);
/
CREATE SEQUENCE  employee_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
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
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 3, 1, 'Mary', 'Conley', 'mConley', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 2, 1, 1, 'William', 'Smith', 'wSmith', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 1, 1, 2, 'Ben', 'Webster', 'benwebsta', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 3, 2, 'Donna', 'Downey', 'dDowney', 'password', 0); 
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 2, 2, 4, 'Frank', 'Jenkins', 'fJenkins', 'password', 0); 
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 1, 2, 5, 'Mory', 'Keita', 'mKeita', 'password', 0); 
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 3, 3, 'Robert', 'Hallock', 'rHallock', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 2, 3, 7, 'John', 'Parker', 'jParker', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 1, 3, 8, 'Jon', 'Lee', 'jLee', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 3, 4, 'Chadwick', 'Pass', 'cPass', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 2, 4, 10, 'Michael', 'Lin', 'mLin', 'password', 0);
/
INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, MESSAGE) 
  VALUES(1, 1, 4, 11, 'Aaron', 'Camm', 'aCamm', 'password', 0);
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





