CREATE TABLE Role(
  role_id NUMBER(10),
  role VARCHAR2(20),
  
  CONSTRAINT r_id_pk PRIMARY KEY(role_id)
);

CREATE TABLE Department(
  dept_id NUMBER,
  dept VARCHAR2(20),
  
  CONSTRAINT d_id_pk PRIMARY KEY(dept_id)
);

CREATE TABLE GradeType(
  grade_id NUMBER,
  gradingType VARCHAR2(20),
  
  CONSTRAINT g_id_pk PRIMARY KEY(grade_id)
);

CREATE TABLE CourseType(
  course_id NUMBER,
  courseType VARCHAR2(20),
  percentage number,
  
  CONSTRAINT c_id_pk PRIMARY KEY(course_id)
);

CREATE TABLE Approved(
  approval_id NUMBER,
  approvalLevel VARCHAR2(20),
  
  CONSTRAINT a_id_pk PRIMARY KEY(approval_id)
);

create table Employee
(
  First_Name varchar2(40) NOT NULL,
  Last_Name varchar2(20) NOT NULL,
  Address varchar2(70) NOT NULL,
  Dept_ID number NOT NULL,
  ReportsTo number,
  Role_ID number NOT NULL,
  Employee_ID number,
  Username varchar2(16) NOT NULL UNIQUE, 
  Password varchar2(16) NOT NULL,
  Email varchar2(40) NOT NULL UNIQUE,
  Allowance number,
  PRIMARY KEY(Employee_ID),
  FOREIGN KEY(role_id) REFERENCES Role(role_id),
  FOREIGN KEY(dept_id) REFERENCES Department(dept_id),
  FOREIGN KEY(ReportsTo) REFERENCES Employee(Employee_ID)
);

create table Reimbursement
(
  Reimbursement_ID number,
  Reimbursement_Amnt number,
  Employee_ID number NOT NULL,
  Location varchar2(40) NOT NULL,
  Application_Date varchar2(20) NOT NULL,
  Start_Date varchar2(20) NOT NULL,
  End_Date varchar2(20) NOT NULL,
  Course_Cost number NOT NULL,
  Course_Length number,
  Approval_ID number NOT NULL UNIQUE, 
  Course_ID number,
  GradeType_ID number,
  Grade number,
  
  PRIMARY KEY(Reimbursement_ID),
  FOREIGN KEY(Employee_ID) REFERENCES Employee(Employee_ID),
  FOREIGN KEY(Approval_ID) REFERENCES Approved(Approval_ID),
  FOREIGN KEY(Course_ID) REFERENCES CourseType(Course_ID),
  FOREIGN KEY(GradeType_ID) REFERENCES GradeType(grade_id)
);

CREATE TABLE Attachments(
  attachment_id NUMBER,
  file_location VARCHAR2(20),
  reimbursement_id,
  
  CONSTRAINT att_id_pk PRIMARY KEY(attachment_id),
  FOREIGN KEY(reimbursement_id) REFERENCES Reimbursement(reimbursement_id)
);

create sequence user_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1;

/
create or replace trigger user_trigger -- name
  before insert on Users --upon what event
  for each row -- how often
  begin --start what actually happens
    select user_seq.NEXTVAL
    into :new.user_id
    from dual;
  end;
/