DROP user tuitiondb cascade;

/******************************************************************************
CREATE A DATABASE
******************************************************************************/

CREATE USER tuitiondb
IDENTIFIED BY p4ssw0rd;

GRANT connect to tuitiondb;
GRANT resource to tuitiondb;

/******************************************************************************
CREATE TABLES
******************************************************************************/

CREATE TABLE Users(
 user_id NUMBER,
 firstName VARCHAR2(25) NOT NULL,
 lastName VARCHAR2(25) NOT NULL,
 uname VARCHAR2(16) NOT NULL UNIQUE,
 pw VARCHAR2(16) NOT NULL,
 email VARCHAR2(50) NOT NULL,
 dept_id NUMBER, --FK to departments table
 role_id NUMBER, --FK to Roles table
 reportsto NUMBER, --FK to user_id

 CONSTRAINT PK_USERS PRIMARY KEY (user_id)
);

CREATE TABLE Applications(
  app_id NUMBER,
  user_id NUMBER, --FK to user_id in Users table
  date_created DATE NOT NULL,
  status_id NUMBER, -- FK to ResolutionStatus
  priority_id NUMBER NOT NULL, --FK to Priority Level
  event_id NUMBER NOT NULL, --FK to Event Type
  cdt_id NUMBER, --FK to ClassDateTime
  loc VARCHAR2(60), -- could be null, address of class?
  total_cost DECIMAL(12,2) NOT NULL,
  grading_id NUMBER, -- FK to Grading
  justification VARCHAR2(60), --explains how this class relates to work
  reimbursement_id NUMBER, --FK to reimbusements table

  CONSTRAINT PK_APPS PRIMARY KEY (app_id)
);

--1:1
CREATE TABLE ClassDateTime(
  cdt_id NUMBER,

  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  --start_time TIME NOT NULL,
  --end_time TIME NOT NULL,
  hours_per_week NUMBER NOT NULL,

  CONSTRAINT PK_CDT PRIMARY KEY (cdt_id)
);

--1:1
CREATE TABLE Grading(
  grading_id NUMBER,
  grading_format_id NUMBER NOT NULL, --FK to grading format 
  grade_cutoff VARCHAR2(25), -- required for A-F and percentage based grading formats
  grade_awarded VARCHAR2(25), -- for grading formats 1-3
  presentation_review VARCHAR2(25), -- for grading format 4

  CONSTRAINT PK_GRADING PRIMARY KEY (grading_id)
);

--1:n
CREATE TABLE Attachments( --optional table
  attachment_id NUMBER,
  app_id NUMBER, --FK to app_id in Applications table
  attachment_url VARCHAR2(250),  --where the file is stored
  
  CONSTRAINT PK_ATTACHMENTS PRIMARY KEY (attachment_id)
);

--1:n
CREATE TABLE GradeAttachments( --optional table
  grade_attach_id NUMBER,
  app_id NUMBER,
  grade_attach_url VARCHAR2(250),  --where the file is stored
  
  CONSTRAINT PK_GRADEATTACHMENTS PRIMARY KEY (grade_attach_id)
);

--1:1
CREATE TABLE Reimbursements(
  reimbursement_id NUMBER,
  projected_reimbursement DECIMAL(12,2) NOT NULL, --calculated from equation, write as procedure
  awarded_reimbursement DECIMAL(12,2),
  change_reason VARCHAR2(250), --message from benco saying why amount was change, can be null
  
  CONSTRAINT PK_REIMBURSEMENT PRIMARY KEY (reimbursement_id)
);

--1:n
CREATE TABLE Approvals(
  approval_app_id NUMBER,
  app_id NUMBER, --FK to app_id in Applications table
  approval_level NUMBER, --FK to approval level table
  approval_status NUMBER, --FK to approval status table
  approver_id NUMBER, --FK to users table
  approval_message VARCHAR2(250), --message from D.S. saying why it was denied, can be null if approved
  
  CONSTRAINT PK_APPROVALAPP PRIMARY KEY (approval_app_id)
);

--1:n
CREATE TABLE Notifications(
  notification_id NUMBER,
  user_id NUMBER, --FK to user_id in Users table
  resolution_id NUMBER, --FK to lookup resolution status table (has this request been resolved)
  requester_id NUMBER, --FK to user_id in Users table
  notification_message VARCHAR2(250),
  
  CONSTRAINT PK_NOTIFICATIONS PRIMARY KEY (notification_id)
);

--1:n
CREATE TABLE InfoRequest(
  info_request_id NUMBER,
  app_id NUMBER, --FK to app_id in Applications table
  resolution_id NUMBER, --FK to lookup resolution status table (has this request been resolved)
  requester_id NUMBER, --FK to user_id in Users table
  request_message VARCHAR2(250), --message with the request
  reply_message VARCHAR2(250),
  
  CONSTRAINT PK_INFOREQUEST PRIMARY KEY (info_request_id)
);

/******************************************************************************
REFERENCE TABLES
******************************************************************************/

CREATE TABLE Departments(
  dept_id NUMBER,
  dept_name VARCHAR2(25),
  
  CONSTRAINT PK_DEPTS PRIMARY KEY (dept_id)
);

CREATE TABLE Roles(
  role_id NUMBER,
  role_desc VARCHAR2(25), -- employee, DM, dept head, benco
  
  CONSTRAINT PK_ROLES PRIMARY KEY (role_id)
);

CREATE TABLE PriorityLevel(
  priority_id NUMBER,
  priority_level VARCHAR2(25),
  
  CONSTRAINT PK_PRIORITY PRIMARY KEY (priority_id)
);

CREATE TABLE EventType(
  event_id NUMBER,
  event_type VARCHAR2(40),
  
  CONSTRAINT PK_EVENT PRIMARY KEY (event_id)
);

CREATE TABLE GradingFormat(
  grading_format_id NUMBER,
  grading_format VARCHAR2(25) NOT NULL, -- should be 4 diff types
  
  CONSTRAINT PK_GRADINGFORMAT PRIMARY KEY (grading_format_id)
);

CREATE TABLE ApprovalStatus( --pending, approved, denied
  approval_id NUMBER,
  approval_status VARCHAR2(25),
  
  CONSTRAINT PK_APPROVAL PRIMARY KEY (approval_id)
);

CREATE TABLE ApprovalLevel( 
  approval_level_id NUMBER,
  approval_level VARCHAR2(25),
  
  CONSTRAINT PK_APPROVALLEVEL PRIMARY KEY (approval_level_id)
);

CREATE TABLE ResolutionStatus(
  resolution_id NUMBER,
  resolution_status VARCHAR2(15), -- ACTIVE, INACTIVE
  
  CONSTRAINT PK_RESOLUTION PRIMARY KEY (resolution_id)
);

/******************************************************************************
CREATE FOREIGN KEYS
******************************************************************************/

-- FKs for Users table
ALTER TABLE Users ADD  
  CONSTRAINT FK_DEPTID FOREIGN KEY (dept_id) REFERENCES Departments (dept_id);
ALTER TABLE Users ADD
 CONSTRAINT FK_ROLEID FOREIGN KEY (role_id) REFERENCES Roles (role_id);
ALTER TABLE Users ADD
 CONSTRAINT FK_REPORTSTO FOREIGN KEY (reportsto) REFERENCES Users (user_id);
 
-- FKs for Applications table
ALTER TABLE Applications ADD 
  CONSTRAINT FK_APPUSERID FOREIGN KEY (user_id) REFERENCES Users (user_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_PRIORITYID FOREIGN KEY (priority_id) REFERENCES PriorityLevel (priority_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_EVENTID FOREIGN KEY (event_id) REFERENCES EventType (event_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_CDTID FOREIGN KEY (cdt_id) REFERENCES ClassDateTime (cdt_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_GRADINGID FOREIGN KEY (grading_id) REFERENCES Grading (grading_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_REIMBURSEMENTID FOREIGN KEY (reimbursement_id) REFERENCES Reimbursements (reimbursement_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_STATUSID FOREIGN KEY (status_id) REFERENCES ResolutionStatus (resolution_id);

--FK for the Grading table
ALTER TABLE Grading ADD
  CONSTRAINT FK_GRADINGFORMATID FOREIGN KEY (grading_format_id) REFERENCES GradingFormat (grading_format_id);
  
--FK for the Attachments table
ALTER TABLE Attachments ADD
  CONSTRAINT FK_ATTACHAPPID FOREIGN KEY (app_id) REFERENCES Applications (app_id);
  
--FK for the GradeAttachments table
ALTER TABLE GradeAttachments ADD
  CONSTRAINT FK_GRADEATTACHAPPID FOREIGN KEY (app_id) REFERENCES Applications (app_id);
  
--FK for the Approvals table
ALTER TABLE Approvals ADD
  CONSTRAINT FK_APPROVALAPPID FOREIGN KEY (app_id) REFERENCES Applications (app_id);
ALTER TABLE Approvals ADD
  CONSTRAINT FK_APPROVALLEVEL FOREIGN KEY (approval_level) REFERENCES ApprovalLevel (approval_level_id);
ALTER TABLE Approvals ADD
  CONSTRAINT FK_APPROVALSTATUS FOREIGN KEY (approval_status) REFERENCES ApprovalStatus (approval_id);
ALTER TABLE Approvals ADD
  CONSTRAINT FK_APPROVERID FOREIGN KEY (approver_id) REFERENCES Users (user_id);
  
--FK for Notifications table
ALTER TABLE Notifications ADD
  CONSTRAINT FK_NOTIFUSERID FOREIGN KEY (user_id) REFERENCES Users (user_id);
ALTER TABLE Notifications ADD
  CONSTRAINT FK_RESOLUTIONID FOREIGN KEY (resolution_id) REFERENCES ResolutionStatus (resolution_id);
ALTER TABLE Notifications ADD
  CONSTRAINT FK_REQUESTERID FOREIGN KEY (requester_id) REFERENCES Users (user_id);
  
--FK for InfoRequest Table
ALTER TABLE InfoRequest ADD
  CONSTRAINT FK_ADDITAPPID FOREIGN KEY (app_id) REFERENCES Applications (app_id);
ALTER TABLE InfoRequest ADD
  CONSTRAINT FK_ADDITRESOLUTIONID FOREIGN KEY (resolution_id) REFERENCES ResolutionStatus (resolution_id);
ALTER TABLE InfoRequest ADD
  CONSTRAINT getGradeAttachURL FOREIGN KEY (requester_id) REFERENCES Users (user_id);

  
COMMIT;