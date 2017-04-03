/*******************************************************************************
   Script: TRMS.sql
   Description: Creates and populates the TRMS database.
   Author: Marco Tobon
********************************************************************************/
/*******************************************************************************
Drop Tables If They Exist
*******************************************************************************/
DROP TABLE Attachments;
/
DROP TABLE Messages;
/
DROP TABLE Reimbursement;
/
DROP TABLE Appr;
/
DROP TABLE Course;
/
DROP TABLE Employee;
/
DROP TABLE GradeType;
/
DROP TABLE Department;
/
DROP TABLE Rolet;
/

/*******************************************************************************
   Create Lookup Tables
********************************************************************************/

CREATE TABLE GradeType
(
    GradeId NUMBER,
    GradingType VARCHAR2(500),

    PRIMARY KEY (GradeId)
);
/
CREATE TABLE Department
(
    DeptId NUMBER,
    Dept VARCHAR2(500),

    PRIMARY KEY (DeptId)
);
/
CREATE TABLE RoleT
(
    RoleId NUMBER,
    RoleT VARCHAR2(500),

    PRIMARY KEY (RoleID)
);
/
CREATE TABLE Course
(
    CourseId NUMBER,
    CourseType VARCHAR2(500),
    Percentage NUMBER(10,2),
    
    PRIMARY KEY (CourseId)
);
/
CREATE TABLE Appr
(
    ApprId NUMBER,
    CurrLvl VARCHAR2(500), --MIGHT NEEDD TO CHAGNE THIS

    PRIMARY KEY (ApprId)
);
/
CREATE TABLE Status
(
  sid NUMBER,
  stat VARCHAR(100),
  PRIMARY KEY (sid)
);
/
/*******************************************************************************
  Create Tables
*******************************************************************************/
CREATE TABLE Employee
(
    EmpId NUMBER,
    fName VARCHAR2(500) NOT NULL,
    lName VARCHAR2(500),
    Addr VARCHAR2(500),
    DeptId NUMBER,
    RepTo NUMBER,
    UserName VARCHAR2(500) NOT NULL UNIQUE,
    Password VARCHAR2(500) NOT NULL,
    Email VARCHAR2(500) NOT NULL UNIQUE,
    Balance NUMBER CHECK(Balance > -1),
    RoleId NUMBER,
    
    FOREIGN KEY (DeptId)REFERENCES Department(DeptId),
    FOREIGN KEY (RoleId) REFERENCES RoleT(RoleId),
    FOREIGN KEY (RepTo) REFERENCES Employee(EmpId),
    PRIMARY KEY (EmpId)
);
/
CREATE TABLE Reimbursement
(
    ReimId NUMBER,
    EmpId NUMBER,
    Loc VARCHAR2(500) NOT NULL,
    FormTm VARCHAR2(500) NOT NULL,
    CourseDescr VARCHAR2(500) NOT NULL,
    CostCrs NUMBER(10,2),
    GradeId NUMBER,
    CourseId NUMBER,
    WorkJst VARCHAR2(500) NOT NULL,
    TmMiss VARCHAR2(500),
    CrsStart VARCHAR2(500) NOT NULL,
    CrsEnd VARCHAR2(500) NOT NULL,
    ApprId NUMBER, 
    FormDt VARCHAR2(500) NOT NULL,     
    GradeVal VARCHAR2(500),

    FOREIGN KEY (EmpId) REFERENCES Employee(EmpId),
    FOREIGN KEY (ApprId) REFERENCES Appr(ApprId),
    FOREIGN KEY (CourseId) REFERENCES Course(CourseId),
    FOREIGN KEY (GradeId) REFERENCES GradeType(GradeId),
    PRIMARY KEY (ReimId)
    --MIGHT NEED TO ADD IF IT HAS AN ATTACHMENT
);
/

CREATE TABLE Attachments
(
    AttchId NUMBER,
    FilLoc VARCHAR2(500),
    ReimId NUMBER,

    PRIMARY KEY (AttchId),
    FOREIGN KEY (ReimId) REFERENCES Reimbursement(ReimId)
);
/
CREATE TABLE Messages
(
    MsgId NUMBER,
    Sender NUMBER,
    Receiver NUMBER,
    Msg VARCHAR2(500) NOT NULL,
    ReimId NUMBER,
    rInfo NUMBER DEFAULT 0,

    FOREIGN KEY (Sender) REFERENCES Employee(EmpId),
    FOREIGN KEY (Receiver) REFERENCES Employee(EmpId),
    FOREIGN KEY (ReimId) REFERENCES Reimbursement(ReimId),
    PRIMARY KEY (MsgId)
);
/
/*=========================================================================
 Create Sequences
=========================================================================*/
CREATE SEQUENCE emp_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE SEQUENCE reim_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE SEQUENCE msg_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
CREATE SEQUENCE attch_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1;
/
/*=========================================================================
 Create Triggers
=========================================================================*/
CREATE OR REPLACE TRIGGER emp_trig
  BEFORE INSERT ON Employee
  FOR EACH ROW
  BEGIN
    SELECT emp_seq.NEXTVAL
    INTO :new.EmpId
    FROM dual;
  END;
/
CREATE OR REPLACE TRIGGER reim_trig
  BEFORE INSERT ON Reimbursement
  FOR EACH ROW
  BEGIN
    SELECT reim_seq.NEXTVAL
    INTO :new.ReimId
    FROM dual;
  END;
/
CREATE OR REPLACE TRIGGER msg_trig
  BEFORE INSERT ON Messages
  FOR EACH ROW
  BEGIN
    SELECT msg_seq.NEXTVAL
    INTO :new.MsgId
    FROM dual;
  END;
/
CREATE OR REPLACE TRIGGER attch_trig
  BEFORE INSERT ON Attachments
  FOR EACH ROW
  BEGIN
    SELECT attch_seq.NEXTVAL
    INTO :new.attchid
    FROM dual;
  END;
/
/*******************************************************************************
 Populate Lookup Tables
*******************************************************************************/
--POPULATE GRADETYPE LOOKUP TABLE
INSERT INTO GRADETYPE (gradeId, gradingType) VALUES (1, 'A,B,C,D,E,F');
INSERT INTO GRADETYPE (gradeId, gradingType) VALUES (2, 'Pass/Fail');
/
--POPULATE DEPARTMENT LOOKUP TABLE
INSERT INTO DEPARTMENT (deptId, dept) VALUES (1, 'Benefits Coordinator');
INSERT INTO DEPARTMENT (deptId, dept) VALUES (2, 'IT');
/
--POPULATE ROLET LOOKUP TABLE
INSERT INTO ROLET (RoleId, RoleT) VALUES (1, 'Employee');
INSERT INTO ROLET (RoleId, RoleT) VALUES (2, 'Direct Supervisor');
INSERT INTO ROLET (RoleId, RoleT) VALUES (3, 'Department Head');
INSERT INTO ROLET (RoleId, RoleT) VALUES (4, 'Benefits Coordinator');
/
--POPULATE COURSE LOOKUP TABLE
INSERT INTO COURSE (CourseId, CourseType, Percentage) VALUES (1, 'University Course', '.8') ;
INSERT INTO COURSE (CourseId, CourseType, Percentage) VALUES (2, 'Seminars', '.6') ;
INSERT INTO COURSE (CourseId, CourseType, Percentage) VALUES (3, 'Certification Preparaton Classes', '.75') ;
INSERT INTO COURSE (CourseId, CourseType, Percentage) VALUES (4, 'Certification', '1') ;
INSERT INTO COURSE (CourseId, CourseType, Percentage) VALUES (5, 'Technical Training', '.9') ;
INSERT INTO COURSE (CourseId, CourseType, Percentage) VALUES (6, 'Other', '.3') ;
/
--POPULATE APRRV LOOKUP TABLE
INSERT INTO APPR (apprId, CurrLvl) VALUES ('1', 'Direct Supervisor');
INSERT INTO APPR (apprId, CurrLvl) VALUES ('2', 'Department Head');
INSERT INTO APPR (apprId, CurrLvl) VALUES ('3', 'Benefits Coordinator');
INSERT INTO APPR (apprId, CurrLvl) VALUES ('4', 'Owner');
/
--POPULATE STATUS LOOKUP TABLE
INSERT INTO STATUS (sid, stat) VALUES ('1', 'Pending');
INSERT INTO STATUS (sid, stat) VALUES ('2', 'Declined');
INSERT INTO STATUS (sid, stat) VALUES ('3', 'Approved');
/
/*******************************************************************************
 Create Some Users
*******************************************************************************/
INSERT INTO EMPLOYEE 
        (FNAME, USERNAME, PASSWORD, EMAIL, DEPTID, ROLEID, BALANCE) 
    VALUES
        ('Marco', 'mt', '123', 'test@test.com', 2, 3, 1000);
/
INSERT INTO EMPLOYEE 
        (FNAME, USERNAME, PASSWORD, EMAIL, DEPTID, ROLEID, BALANCE) 
    VALUES
        ('J', 'jt', '123', 'test2@test.com', 2, 2, 1000);
/
INSERT INTO EMPLOYEE 
        (FNAME, USERNAME, PASSWORD, EMAIL, DEPTID, ROLEID, BALANCE) 
    VALUES
        ('BobCo', 'bob', '123', 'test1@test.com', 1, 1, 1000);
/
commit;
exit;
/
select * from employee;
/
--========================= GETS EMPLOYEE INFO =================================
Select E.fname, E.username, E.password, E.email, D.dept, E2.fname AS REPTO, R.rolet, E.balance
FROM Employee E
  INNER JOIN Department D
  ON
    E.deptid = D.deptid
  LEFT JOIN Employee E2
  ON
    E2.EMPID = E.REPTO
  INNER JOIN Rolet R
  ON
    E.roleid = R.roleid;
    /
--==============================================================================
ALTER TABLE REIMBURSEMENT 
  ADD curr_on NUMBER constraint reim_curr_on_fk references Employee(empid);
/
ALTER TABLE REIMBURSEMENT 
  DROP CONSTRAINT reim_curr_on_fk;
  /
ALTER TABLE REIMBURSEMENT 
  ADD sid NUMBER constraint sid_on_fk references Status(sid);
/
DROP TABLE STATUS;
/
SELECT R.*, A.FILLOC FROM REIMBURSEMENT R
  LEFT JOIN ATTACHMENTS A
  ON A.REIMID = R.REIMID;
/
Select R.*, A.FILLOC, S.STAT from REIMBURSEMENT R
              LEFT JOIN ATTACHMENTS A  ON A.REIMID = R.REIMID
              INNER JOIN STATUS S ON S.SID = R.SID
              WHERE R.CURR_ON = 1;
  