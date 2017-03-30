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
DROP SEQUENCE cdt_seq;
DROP TRIGGER cdt_seq_trigger;
DROP SEQUENCE grading_seq;
DROP TRIGGER grading_seq_trigger;
DROP SEQUENCE attach_seq;
DROP TRIGGER attach_seq_trigger;
DROP SEQUENCE grade_attach_seq;
DROP TRIGGER grade_attach_seq_trigger;
DROP SEQUENCE reimbursement_seq;
DROP TRIGGER reimbursement_seq_trigger;
DROP SEQUENCE addInfo_seq;
DROP TRIGGER addInfo_seq_trigger;
DROP SEQUENCE approval_seq;
DROP TRIGGER approval_seq_trigger;
DROP SEQUENCE infoRe_seq;
DROP TRIGGER infoRe_seq_trigger;
DROP SEQUENCE notif_seq;
DROP TRIGGER notif_seq_trigger;

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

CREATE SEQUENCE cdt_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER cdt_seq_trigger
  BEFORE INSERT ON ClassDateTime
    FOR EACH ROW
  BEGIN
    SELECT cdt_seq.NEXTVAL INTO :new.cdt_id FROM dual;
  END;
/

CREATE SEQUENCE grading_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER grading_seq_trigger
  BEFORE INSERT ON Grading
    FOR EACH ROW
  BEGIN
    SELECT grading_seq.NEXTVAL INTO :new.grading_id FROM dual;
  END;
/

CREATE SEQUENCE attach_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER attach_seq_trigger
  BEFORE INSERT ON Attachments
    FOR EACH ROW
  BEGIN
    SELECT attach_seq.NEXTVAL INTO :new.attachment_id FROM dual;
  END;
/

CREATE SEQUENCE grade_attach_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER grade_attach_seq_trigger
  BEFORE INSERT ON GradeAttachments
    FOR EACH ROW
  BEGIN
    SELECT grade_attach_seq.NEXTVAL INTO :new.grade_attach_id FROM dual;
  END;
/

CREATE SEQUENCE reimbursement_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER reimbursement_seq_trigger
  BEFORE INSERT ON Reimbursements
    FOR EACH ROW
  BEGIN
    SELECT reimbursement_seq.NEXTVAL INTO :new.reimbursement_id FROM dual;
  END;
/

CREATE SEQUENCE addInfo_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER addInfo_seq_trigger
  BEFORE INSERT ON AdditionalInfo
    FOR EACH ROW
  BEGIN
    SELECT addInfo_seq.NEXTVAL INTO :new.additional_info_id FROM dual;
  END;
/

CREATE SEQUENCE infoRe_seq  --inforeturned seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER infoRe_seq_trigger
  BEFORE INSERT ON InfoReturned
    FOR EACH ROW
  BEGIN
    SELECT infoRe_seq.NEXTVAL INTO :new.info_id FROM dual;
  END;
/

CREATE SEQUENCE approval_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER approval_seq_trigger
  BEFORE INSERT ON Approvals
    FOR EACH ROW
  BEGIN
    SELECT approval_seq.NEXTVAL INTO :new.approval_app_id FROM dual;
  END;
/

CREATE SEQUENCE notif_seq
  START WITH 1
  INCREMENT BY 1;
/ 

CREATE OR REPLACE TRIGGER notif_seq_trigger
  BEFORE INSERT ON Notifications
    FOR EACH ROW
  BEGIN
    SELECT notif_seq.NEXTVAL INTO :new.notification_id FROM dual;
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
  VALUES (fName, lName, uname, pw, email, deptID, roleID, reportsTo); 
END;
/

-- procedure to add an application
CREATE OR REPLACE PROCEDURE addApp(userID IN NUMBER, priorityLevel IN NUMBER, event IN NUMBER, 
loc IN VARCHAR2, totalCost IN DECIMAL, justification IN VARCHAR2)
IS
BEGIN
  INSERT INTO Applications (user_id, priority_id, event_id, loc, total_cost, justification) 
  VALUES (userID, priorityLevel, event, loc, totalCost, justification);
END;
/

--creating the 1:1 tables
-- procedure to add a class date and time row. this info is filled out at the time of the application
CREATE OR REPLACE PROCEDURE addClassDateTime(appID IN NUMBER, startDate IN VARCHAR2, endDate IN VARCHAR2,
hoursPerWeek IN NUMBER)
IS sDate DATE; eDate DATE;
BEGIN
  SELECT TO_DATE(startDate, 'mm-dd-yyyy') INTO sDate FROM dual;
  SELECT TO_DATE(endDate, 'mm-dd-yyyy') INTO eDate FROM dual;
  INSERT INTO ClassDateTime (start_date, end_date, hours_per_week) VALUES (sDate, eDate, hoursPerWeek);
  UPDATE Applications SET cdt_id = (SELECT MAX(classdatetime.cdt_id) FROM ClassDateTime) WHERE Applications.app_id = appID;
END;
/

CREATE OR REPLACE PROCEDURE addGrading(appID IN NUMBER, gradingFormat IN NUMBER, gradeCutoff IN VARCHAR2)
IS
BEGIN
  INSERT INTO Grading (grading_format_id, grade_cutoff) VALUES (gradingFormat, gradeCutoff);
  UPDATE Applications SET grading_id = (SELECT MAX(Grading.grading_id) FROM Grading) WHERE Applications.app_id = appID;
END;
/

CREATE OR REPLACE PROCEDURE addReimbursement(appID IN NUMBER, projected IN DECIMAL)
IS 
BEGIN
  INSERT INTO Reimbursements (projected_reimbursement) VALUES (projected);
  UPDATE Applications SET reimbursement_id = (SELECT MAX(Reimbursements.reimbursement_id) FROM Reimbursements)
    WHERE Applications.app_id = appID;
END;
/

--creating the 1:n tables
CREATE OR REPLACE PROCEDURE addAttachments(appID IN NUMBER, attachType IN NUMBER, attachURL IN VARCHAR2)
IS
BEGIN
  INSERT INTO Attachments (app_id, attachment_type_id, attachment_url) VALUES (appID, attachType, attachURL);
END;
/
CREATE OR REPLACE PROCEDURE addGradeAttachments(appID IN NUMBER, gradeAttachType IN NUMBER, GradeAttachURL IN VARCHAR2)
IS
BEGIN
  INSERT INTO GradeAttachments (app_id, grade_attach_type_id, grade_attach_url) VALUES (appID, gradeAttachType, GradeAttachURL);
END;
/

-- add a default approval the first time the application is created
CREATE OR REPLACE PROCEDURE addApprovals(appID IN NUMBER, approvalLevel IN NUMBER, approvalStatus IN NUMBER)
IS
BEGIN
  INSERT INTO Approvals (app_id, approval_level, approval_status) VALUES
  (appID, approvalLevel, approvalStatus);
END;
/

-- add approvals to table (added every time the approval is changed) 
CREATE OR REPLACE PROCEDURE updateApprovals(appID IN NUMBER, approvalLevel IN NUMBER, approvalStatus IN NUMBER, 
approver IN NUMBER, message IN VARCHAR2)
IS
BEGIN
  INSERT INTO Approvals (app_id, approval_level, approval_status, approver_id, approval_message) VALUES
  (appID, approvalLevel, approvalStatus, approver, message);
END;
/


CREATE OR REPLACE PROCEDURE addAdditionalInfo(appID IN NUMBER, resolution IN NUMBER, requester IN NUMBER, 
message IN VARCHAR2)
IS
BEGIN
  INSERT INTO AdditionalInfo (app_id, resolution_id, requester_id, request_message) VALUES 
  (appID, resolution, requester, message);
END;
/ 

CREATE OR REPLACE PROCEDURE addInfoReturned(additionalInfo IN NUMBER, message IN VARCHAR2)
IS
BEGIN
  INSERT INTO InfoReturned (additional_info_id, returned_message) VALUES
  (additionalInfo, message);
END;
/

CREATE OR REPLACE PROCEDURE addNotifications(userID IN NUMBER, resolution in NUMBER, requester IN NUMBER,
notifMessage IN VARCHAR2)
IS
BEGIN
  INSERT INTO Notifications (user_id, resolution_id, requester_id, notification_message) VALUES
  (userID, resolution, requester, notifMessage);
END;
/

-- NEEED TO REWRITE THIS
CREATE OR REPLACE FUNCTION getAwarded(appID IN NUMBER)
RETURN DECIMAL
IS awarded DECIMAL;
BEGIN
  SELECT re.AWARDED_REIMBURSEMENT INTO awarded FROM Applications apps INNER JOIN Reimbursements re ON apps.reimbursement_id = appID;
  RETURN awarded;
END;
/

-- update current approval level for an app
-- approval level has to be the same role level as the current manager
CREATE OR REPLACE PROCEDURE approveAsManager(appID IN NUMBER, apprLvl IN NUMBER, currUser IN NUMBER, approvalMes IN VARCHAR2)
IS
BEGIN
  -- update from pending to approved, add approver_id, and approval message
  UPDATE Approvals SET approval_status = 2, approver_id = currUser, approval_message = approvalMes
  WHERE app_id = appID AND approval_level = apprLvl AND approval_status = 1;
END;
/

--if the first procedure is successful, insert a new approval level for that app
CREATE OR REPLACE PROCEDURE setAppToNextAppr(appID IN NUMBER, newApprLvl IN NUMBER)
IS
BEGIN
  INSERT INTO Approvals (app_id, approval_level, approval_status) VALUES (appID, newApprLvl, 1);
END;
/
COMMIT;