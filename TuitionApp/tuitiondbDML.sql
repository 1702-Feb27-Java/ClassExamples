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
SELECT * FROM Departments;

INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (1, 'Bruno', 'Mars', 'bmars', '1234', 'dt1379@nyu.edu', 1, 3, null);
INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (2, 'Taylor', 'Swift', 'tswift', '1234', 'dt1379@nyu.edu', 1, 2, 1);

INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (3, 'Ed', 'Sheeran', 'esheeran', '1234', 'dt1379@nyu.edu', 2, 3, null);

INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (5, 'Danni', 'Tang', 'dtang', '1234', 'dt1379@nyu.edu', 3, 3, null);
INSERT INTO Users (user_id, firstname, lastname, uname, pw, email, dept_id, role_id, reportsto) 
VALUES (6, 'Miley', 'Cyrus', 'mcyrus', '1234', 'dt1379@nyu.edu', 3, 2, 1);

COMMIT;
/******************************************************************************
SEQUENCES AND TRIGGERS
******************************************************************************/