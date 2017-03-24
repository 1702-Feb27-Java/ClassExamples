/******************************************************************************
POPULATE SOME STUFF NOW
******************************************************************************/

/******************************************************************************
lOOKUP TABLES
******************************************************************************/

INSERT INTO Departments VALUES (1, 'Marketing');
INSERT INTO Departments VALUES (2, 'Human Resources');
INSERT INTO Departments VALUES (3, 'BenCo');

--SELECT * FROM Departments;

INSERT INTO EventType VALUES (1, 'University Courses');
INSERT INTO EventType VALUES (2, 'Seminars');
INSERT INTO EventType VALUES (3, 'Certification Prep. Classes');
INSERT INTO EventType VALUES (4, 'Certification');
INSERT INTO EventType VALUES (5, 'Technical Training');
INSERT INTO EventType VALUES (6, 'Other');

--SELECT * FROM EventType;

INSERT INTO Roles VALUES (1, 'Employee');
INSERT INTO Roles VALUES (2, 'Direct Supervisor');
INSERT INTO Roles VALUES (3, 'Department Head');
INSERT INTO Roles VALUES (4, 'BenCo worker');

--SELECT * FROM Roles;

INSERT INTO PriorityLevel VALUES (1, 'normal');
INSERT INTO PriorityLevel VALUES (2, 'urgent');

--SELECT * FROM PriorityLevel;

INSERT INTO GradingFormat VALUES (1, 'Pass/Fail');
INSERT INTO GradingFormat VALUES (2, 'A-F');
INSERT INTO GradingFormat VALUES (3, 'Percentage');
INSERT INTO GradingFormat VALUES (4, 'Presentation');

--SELECT * FROM GradingFormat;

INSERT INTO ApprovalLevel VALUES (1, 'Direct Supervisor');
INSERT INTO ApprovalLevel VALUES (2, 'Department Head');
INSERT INTO ApprovalLevel VALUES (3, 'BenCo');

--SELECT * FROM ApprovalLevel;

INSERT INTO ApprovalStatus VALUES (1, 'pending');
INSERT INTO ApprovalStatus VALUES (2, 'approved');
INSERT INTO ApprovalStatus VALUES (3, 'denied');

--SELECT * FROM ApprovalStatus;

INSERT INTO AttachmentType VALUES (1, 'event-related');
INSERT INTO AttachmentType VALUES (2, 'approval');
INSERT INTO AttachmentType VALUES (3, 'work-time missed');

--SELECT * FROM AttachmentType;

INSERT INTO GradeAttachmentType VALUES (1, 'grade');
INSERT INTO GradeAttachmentType VALUES (2, 'presentation');

--SELECT * FROM GradeAttachmentType;

INSERT INTO ResolutionStatus VALUES (1, 'active');
INSERT INTO ResolutionStatus VALUES (2, 'inactive');

--SELECT * FROM ResolutionStatus;

COMMIT;

/******************************************************************************
ADD THE DEFAULT DIRECT SUPERVISOR, DEPARTMENT HEAD, BENCO
******************************************************************************/
SELECT * FROM GradingFormat;

INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (1, 'Bruno', 'Mars', 'bmars', '1234', 'dt1379@nyu.edu', 1, 3, null);
INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (2, 'Taylor', 'Swift', 'tswift', '1234', 'dt1379@nyu.edu', 1, 2, 1);

INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (3, 'Ed', 'Sheeran', 'esheeran', '1234', 'dt1379@nyu.edu', 2, 3, null);

INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (5, 'Danni', 'Tang', 'dtang', '1234', 'dt1379@nyu.edu', 3, 3, null);
INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (6, 'Miley', 'Cyrus', 'mcyrus', '1234', 'dt1379@nyu.edu', 3, 2, 5);

COMMIT;
/******************************************************************************
SEQUENCES AND TRIGGERS
******************************************************************************/

DROP SEQUENCE user_seq;
DROP TRIGGER user_seq_trigger;
DROP SEQUENCE app_seq;
DROP TRIGGER app_seq_trigger;

CREATE SEQUENCE user_seq
  START WITH 7  -- because we already have 2 default accounts stored
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER user_seq_trigger
  BEFORE INSERT ON Users
    FOR EACH ROW
  BEGIN
    SELECT user_seq.NEXTVAL INTO :new.user_id FROM dual;
  END;
/

CREATE SEQUENCE app_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER app_seq_trigger
  BEFORE INSERT ON Applications
    FOR EACH ROW
  BEGIN
    SELECT app_seq.NEXTVAL INTO :new.app_id FROM dual;
  END;
/

/******************************************************************************
PROCEDURES AND FUNCTIONS
******************************************************************************/
-- procedure to add a user
CREATE OR REPLACE PROCEDURE addUser(fName IN VARCHAR2, lName IN VARCHAR2, uname IN VARCHAR2, 
pw IN VARCHAR2, email IN VARCHAR2, deptID IN NUMBER, roleID IN NUMBER, reportsTo IN NUMBER)
IS
BEGIN
  INSERT INTO Users (firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
  VALUES (fName, lName, uname, pw, email, deptID, roleID, reportsTO); -- only add customers
END;
/

COMMIT;
ROLLBACK;

SELECT * FROM Users;