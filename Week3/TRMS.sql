--Creating tables
CREATE TABLE APPROVAL
(
    approver_id number PRIMARY KEY,
    currentLevel varchar(25)
);

CREATE TABLE ATTACHMENTS
(
    attach_id number PRIMARY KEY,
    file_location varchar(100),
    reimburst_id number
);

CREATE TABLE CROLE
(
    role_id number PRIMARY KEY,
    role varchar(25)
);

CREATE TABLE GRADETYPE
(
    grade_id number PRIMARY KEY,
    gradetype varchar(25)
);

CREATE TABLE DEPARTMENT
(
    dept_id number PRIMARY KEY,
    dept varchar(25)
);

CREATE TABLE COURSE
(
    course_id number PRIMARY KEY,
    course_type varchar(25),
    percentage number
);

CREATE TABLE EMPLOYEE
(
    emp_id number PRIMARY KEY,
    fname varchar(25) NOT NULL,
    lname varchar(25) NOT NULL,
    address varchar(25) NOT NULL,
    dept_id number NOT NULL,
    reportsto number NOT NULL,
    role_id number NOT NULL,
    username varchar(25) UNIQUE,
    pass varchar(25) UNIQUE,
    email varchar(25) NOT NULL,
    allowance number NOT NULL
);

CREATE TABLE REIMBURSTMENT
(
    reim_id number PRIMARY KEY,
    emp_id number,
    relocation varchar(100),
    addDate varchar(10),
    startDate varchar(10),
    endDate varchar(10),
    courseTime varchar(25),
    courseCost number,
    reimburst_amt number,
    approval_id number,
    course_id number,
    gradetype_id number
);

--Foreign keys
ALTER TABLE ATTACHMENTS ADD CONSTRAINT FK_attach_reim_id
    FOREIGN KEY (reimburst_id) REFERENCES REIMBURSTMENT (reim_id);
    
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_emp_dept_id
    FOREIGN KEY (dept_id) REFERENCES DEPARTMENT (dept_id);
    
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_emp_reportsto_id
    FOREIGN KEY (reportsto) REFERENCES EMPLOYEE (emp_id);
    
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_emp_role_id
    FOREIGN KEY (role_id) REFERENCES CROLE (role_id);
    
ALTER TABLE REIMBURSTMENT ADD CONSTRAINT FK_reimburst_emp_id
    FOREIGN KEY (emp_id) REFERENCES EMPLOYEE (emp_id);
    
ALTER TABLE REIMBURSTMENT ADD CONSTRAINT FK_reimburst_approval_id
    FOREIGN KEY (approval_id) REFERENCES APPROVAL (approver_id);
    
ALTER TABLE REIMBURSTMENT ADD CONSTRAINT FK_reimburst_course_id
    FOREIGN KEY (course_id) REFERENCES COURSE (course_id);
    
ALTER TABLE REIMBURSTMENT ADD CONSTRAINT FK_reimburst_gradetype_id
    FOREIGN KEY (gradetype_id) REFERENCES GRADETYPE (grade_id);

--Drop Foreign Keys
alter table attachments drop constraint fk_attach_reim_id;
alter table employee drop constraint fk_emp_dept_id;
alter table employee drop constraint fk_emp_reportsto_id;
alter table employee drop constraint fk_emp_role_id;
alter table reimburstment drop constraint fk_reimburst_emp_id;
alter table reimburstment drop constraint fk_reimburst_approval_id;
alter table reimburstment drop constraint fk_reimburst_course_id;
alter table reimburstment drop constraint fk_reimburst_gradetype_id;

--Drop Tables
drop table approval;
drop table attachments;
drop table course;
drop table crole;
drop table department;
drop table employee;
drop table gradetype;
drop table reimburstment;

--Sequences
create sequence emp_seq
    start with 1
    increment by 1;
/
create sequence attach_seq
    start with 1
    increment by 1;
/
create sequence reim_seq
    start with 1
    increment by 1;
/

--Drop Sequences
drop sequence attach_seq;
drop sequence emp_seq;
drop sequence reim_seq;

--Triggers
/
create or replace TRIGGER attach_trigger --name
    BEFORE INSERT ON attachments --upon what event
    FOR EACH ROW --how often
    BEGIN --start what actually happens
        SELECT attach_seq.NEXTVAL
        INTO :new.attach_id
        FROM dual;
    END;
/

create or replace TRIGGER emp_trigger --name
    BEFORE INSERT ON employee --upon what event
    FOR EACH ROW --how often
    BEGIN --start what actually happens
        SELECT emp_seq.NEXTVAL
        INTO :new.emp_id
        FROM dual;
    END;
/

create or replace TRIGGER reim_trigger --name
    BEFORE INSERT ON reimburstment --upon what event
    FOR EACH ROW --how often
    BEGIN --start what actually happens
        SELECT reim_seq.NEXTVAL
        INTO :new.reim_id
        FROM dual;
    END;
/

--Procedures

--Seeded Users
INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('Xavier', 'Grogan', '1544 Ratliff Rd.', 4, 1, 3, 'xpgrogan', 'password', 'xavier.grogan@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('f', 'f', 'Frontier Rd.', 2, 2, 3, 'f', 'f', 'ff@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('h', 'h', 'Heimerdinger Rd.', 1, 3, 3, 'h', 'h', 'hh@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('i', 'i', 'Illaoi Rd.', 3, 4, 3, 'i', 'i', 'bb@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('Carrie', 'Vaughn', '1544 Ratliff Rd.', 1, 3, 2, 'carrie', 'p', 'carrie.vaughn@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('c', 'c', 'Fairfax Ln.', 4, 1, 2, 'c', 'c', 'cc@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('e', 'e', 'Echeart Rd.', 3, 2, 2, 'e', 'e', 'ee@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('g', 'g', 'Google Ln.', 4, 4, 2, 'g', 'g', 'gg@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('a', 'a', 'Allen Cr.', 2, 7, 1, 'a', 'a', 'aa@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('b', 'b', 'Bonified Rd.', 3, 8, 1, 'b', 'b', 'bb@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('d', 'd', 'Darius Ln.', 1, 5, 1, 'd', 'd', 'dd@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('j', 'j', 'Justification Ln.', 4, 6, 1, 'j', 'j', 'jj@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('k', 'k', 'Klingon Rd.', 1, 5, 1, 'k', 'k', 'kk@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('l', 'l', 'Linux Ln.', 2, 7, 1, 'l', 'l', 'll@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('m', 'm', 'Mountian Cr.', 3, 8, 1, 'm', 'm', 'mm@aol.com', 1000);

INSERT INTO EMPLOYEE (FNAME, LNAME, ADDRESS, DEPT_ID, REPORTSTO, ROLE_ID, USERNAME, PASS, EMAIL, ALLOWANCE) 
VALUES ('n', 'n', 'Netherland Ln.', 4, 6, 1, 'n', 'n', 'nn@aol.com', 1000);

--Seed Courses
INSERT INTO COURSE VALUES (1, 'University Course', 80);
INSERT INTO COURSE VALUES (2, 'Seminars', 60);
INSERT INTO COURSE VALUES (3, 'Certification Prep Class', 75);
INSERT INTO COURSE VALUES (4, 'Certification', 100);
INSERT INTO COURSE VALUES (5, 'Technical Training', 90);
INSERT INTO COURSE VALUES (6, 'Other', 30);

--Crole Table Seeds
INSERT INTO CROLE VALUES (1, 'Employee');
INSERT INTO CROLE VALUES (2, 'Supervisor');
INSERT INTO CROLE VALUES (3, 'Department Head');

--Approval Seeds
INSERT INTO APPROVAL VALUES (1, 'Pending');
INSERT INTO APPROVAL VALUES (2, 'Waiting on Supervisor');
INSERT INTO APPROVAL VALUES (3, 'Waiting on Dept. Head');
INSERT INTO APPROVAL VALUES (4, 'Waiting on BenCo');
INSERT INTO APPROVAL VALUES (5, 'Approved');
INSERT INTO APPROVAL VALUES (6, 'Declined');

--Department Seeds
INSERT INTO DEPARTMENT VALUES (1, 'Sales');
INSERT INTO DEPARTMENT VALUES (2, 'Marketing');
INSERT INTO DEPARTMENT VALUES (3, 'Accounting');
INSERT INTO DEPARTMENT VALUES (4, 'BenCo');

--Gradetype Seeds
INSERT INTO GRADETYPE VALUES (1, 'Pass-Fail');
INSERT INTO GRADETYPE VALUES (2, 'A-F');
