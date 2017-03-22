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
 application_id NUMBER, --FK to applications table, can be null
 notification_id NUMBER, --FK to notificationS table

 CONSTRAINT PK_USERS PRIMARY KEY (user_id)
);

CREATE TABLE Applications(
  app_id NUMBER,
  user_id NUMBER, --FK to user_id in Users table
  priority_id NUMBER NOT NULL, --FK to Priority Level
  event_id NUMBER NOT NULL, --FK to Event Type
  cdt_id NUMBER NOT NULL, --FK to ClassDateTime
  loc VARCHAR2(60), -- could be null, address of class?
  total_cost NUMBER(6,2) NOT NULL,
  grading_id NUMBER, -- FK to Grading
  justification VARCHAR2(60), --explains how this class relates to work
  attachment_id NUMBER, --FK to attachments table, can be null
  grade_attach_id NUMBER, --FK to grade attachments table, can be null
  reimbursement_id NUMBER, --FK to reimbusements table
  additional_info_id NUMBER, --FK to additional info table
  approval_app_id NUMBER, --FK to Approvals table

  CONSTRAINT PK_APPS PRIMARY KEY (app_id)
);

CREATE TABLE ClassDateTime(
  cdt_id NUMBER,

  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  --start_time TIME NOT NULL,
  --end_time TIME NOT NULL,
  days_per_week NUMBER NOT NULL,

  CONSTRAINT PK_CDT PRIMARY KEY (cdt_id)
);

CREATE TABLE Grading(
  grading_id NUMBER,
  grading_format_id NUMBER NOT NULL, --FK to grading format 
  grade_cutoff VARCHAR2(25), -- required for A-F and percentage based grading formats
  default_grade VARCHAR2(25),
  grade_awarded VARCHAR2(25), -- for grading formats 1-3
  presentation_review VARCHAR2(25), -- for grading format 4

  CONSTRAINT PK_GRADING PRIMARY KEY (grading_id)
);

CREATE TABLE Attachments( --optional table
  attachment_id NUMBER,
  app_id NUMBER, --FK to app_id in Applications table
  attachment_type_id NUMBER, --FK to attachment type
  attachment_url VARCHAR2(250),  --where the file is stored
  
  CONSTRAINT PK_ATTACHMENTS PRIMARY KEY (attachment_id)
);

CREATE TABLE GradeAttachments( --optional table
  grade_attach_id NUMBER,
  grade_attach_type_id NUMBER, --FK to grade attachment type
  grade_attach_url VARCHAR2(250),  --where the file is stored
  
  CONSTRAINT PK_GRADEATTACHMENTS PRIMARY KEY (grade_attach_id)
);

ALTER TABLE GradeAttachments
  ADD app_id NUMBER;

CREATE TABLE Reimbursements(
  reimbursement_id NUMBER,
  projected_reimbursement NUMBER(6,2) NOT NULL, --calculated from equation, write as procedure
  change_reason VARCHAR2(250), --message from benco saying why amount was change, can be null
  
  CONSTRAINT PK_REIMBURSEMENT PRIMARY KEY (reimbursement_id)
);

CREATE TABLE Approvals(
  approval_app_id NUMBER,
  approval_level NUMBER, --FK to approval level table
  approval_status NUMBER, --FK to approval status table
  approver_id NUMBER, --FK to users table
  approval_message VARCHAR2(250), --message from D.S. saying why it was denied, can be null if approved
  
  CONSTRAINT PK_APPROVALAPP PRIMARY KEY (approval_app_id)
);

CREATE TABLE Notifications(
  notification_id NUMBER,
  user_id NUMBER, --FK to user_id in Users table
  resolution_id NUMBER, --FK to lookup resolution status table (has this request been resolved)
  requester_id NUMBER, --FK to user_id in Users table
  notification_message VARCHAR2(250),
  
  CONSTRAINT PK_NOTIFICATIONS PRIMARY KEY (notification_id)
);

CREATE TABLE AdditionalInfo(
  additional_info_id NUMBER,
  app_id NUMBER, --FK to app_id in Applications table
  resolution_id NUMBER, --FK to lookup resolution status table (has this request been resolved)
  requester_id NUMBER, --FK to user_id in Users table
  request_message VARCHAR2(250), --message with the request
  info_returned VARCHAR2(250), --url of where this file is uploaded, or message answering the request
  
  CONSTRAINT PK_ADDITIONALINFO PRIMARY KEY (additional_info_id)
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

CREATE TABLE AttachmentType(
  attachment_type_id NUMBER,
  attachment_type VARCHAR2(40), --event-related, approvals already provided, etc
  
  CONSTRAINT PK_ATTACHMENTSTYPE PRIMARY KEY (attachment_type_id)
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

CREATE TABLE GradeAttachmentType(
  grade_attach_type_id NUMBER,
  attachment_type VARCHAR2(40), --grade or presentation
  
  CONSTRAINT PK_GRADEATTACHMENTSTYPE PRIMARY KEY (grade_attach_type_id)
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
ALTER TABLE Users ADD
 CONSTRAINT FK_APPID FOREIGN KEY (application_id) REFERENCES Applications (app_id);
ALTER TABLE Users ADD
 CONSTRAINT FK_NOTIFID FOREIGN KEY (notification_id) REFERENCES Notifications (notification_id);
 
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
  CONSTRAINT FK_ATTACHMENTSID FOREIGN KEY (attachment_id) REFERENCES Attachments (attachment_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_GRADEATTACHID FOREIGN KEY (grade_attach_id) REFERENCES GradeAttachments (grade_attach_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_REIMBURSEMENTID FOREIGN KEY (reimbursement_id) REFERENCES Reimbursements (reimbursement_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_ADDITIONALINFOID FOREIGN KEY (additional_info_id) REFERENCES AdditionalInfo (additional_info_id);
ALTER TABLE Applications ADD 
  CONSTRAINT FK_APPROVALID FOREIGN KEY (approval_app_id) REFERENCES Approvals (approval_app_id);
  
--FK for the Grading table
ALTER TABLE Grading ADD
  CONSTRAINT FK_GRADINGFORMATID FOREIGN KEY (grading_format_id) REFERENCES GradingFormat (grading_format_id);
  
--FK for the Attachments table
ALTER TABLE Attachments ADD
  CONSTRAINT FK_ATTACHAPPID FOREIGN KEY (app_id) REFERENCES Applications (app_id);
  
--FK for the GradeAttachments table
ALTER TABLE GradeAttachments ADD
  CONSTRAINT FK_GRADEATTACHTYPEID FOREIGN KEY (grade_attach_type_id) REFERENCES GradeAttachmentType (grade_attach_type_id);
ALTER TABLE GradeAttachments ADD
  CONSTRAINT FK_GRADEATTACHAPPID FOREIGN KEY (app_id) REFERENCES Applications (app_id);
  
--FK for the Approvals table
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
  
--FK for AdditionalInfo Table
ALTER TABLE AdditionalInfo ADD
  CONSTRAINT FK_ADDITUSERID FOREIGN KEY (app_id) REFERENCES Users (user_id);
ALTER TABLE AdditionalInfo ADD
  CONSTRAINT FK_ADDITRESOLUTIONID FOREIGN KEY (resolution_id) REFERENCES ResolutionStatus (resolution_id);
ALTER TABLE AdditionalInfo ADD
  CONSTRAINT FK_ADDITREQUESTERID FOREIGN KEY (requester_id) REFERENCES Users (user_id);
  
COMMIT;