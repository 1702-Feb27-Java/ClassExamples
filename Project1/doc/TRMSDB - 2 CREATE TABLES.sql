/*******************************************************************************
 * CREATES THE TABLES OF THE TRMS DATABASE.
 *
 * AUTHOR: Michael Hobbs
 * DATE: 2017 March 21
 */
 
 conn trmsdb/password2
 /*
 CREATE TABLE employees (
  id              NUMBER,                         --PK
  username        VARCHAR2(40) NOT NULL UNIQUE,
  "password"      VARCHAR2(40) NOT NULL,
  email           VARCHAR2(40) NOT NULL,
  first_name      VARCHAR2(40) NOT NULL,
  last_name       VARCHAR2(40) NOT NULL,
  role_id         NUMBER,                         --FK
  supervisor_id   NUMBER,                         --FK
  department_id   NUMBER                          --FK
 );
 
 CREATE TABLE role (
  id                            NUMBER,           --PK
  role                          VARCHAR2(40)
 );
 
 CREATE TABLE department (
  id                            NUMBER,           --PK
  department                    VARCHAR2(40)
 );
 
 CREATE TABLE reimbursements (
  id                            NUMBER,           --PK
  employee_id                   NUMBER,           --FK
  status_id                     NUMBER,           --FK
  event_type_id                 NUMBER,           --FK
  grade_format_id               NUMBER,           --FK
  grade_id                      NUMBER,           --FK
  start_datetime                DATE,
  end_datetime                  DATE,
  description                   VARCHAR2(400),
  justification                 VARCHAR2(400),
  location                      VARCHAR2(400),
  cost                          NUMBER,
  worktime_to_be_missed         VARCHAR2(400)
 );
 
 CREATE TABLE urgent_reimbursements (
  reimbursement_id              NUMBER            --FK/PK      
 );
 
 CREATE TABLE altered_reimbursements (
  reimbursement_id              NUMBER,           --FK/PK
  adjusted_reimbursement        NUMBER,
  exceeds_available_funds       NUMBER,
  reason                        VARCHAR2(400)
 );
 
 CREATE TABLE reimbursement_status (
  id                            NUMBER,           --PK
  reimbursement_status          VARCHAR2(40)
 );
 
 CREATE TABLE event_type (
  id                            NUMBER,           --PK  
  event_type                    VARCHAR2(40),
  coverage_percentage           NUMBER
 );
 
 CREATE TABLE grade_format (
  id                            NUMBER,           --PK
  grades                        VARCHAR2(400),
  passing_grade                 VARCHAR2(40)
 );
 
 CREATE TABLE reimbursement_grades (
  id                            NUMBER,           --PK
  reimbursement_id              NUMBER,           --FK
  grade                         VARCHAR2(40)
 );
 
 CREATE TABLE reimbursement_attachments (
  id                                  NUMBER,           --PK
  reimbursement_id                    NUMBER,           --FK
  attachment_type_id    NUMBER,           --FK
  url                                 VARCHAR2(2000)
 );
 
 CREATE TABLE reimbursement_attachment_type (
  id                              NUMBER,               --PK
  attachment_type                 VARCHAR2(40)
 );
 
 CREATE TABLE reimbursement_messages (
  id                    NUMBER,                   --PK
  reimbursement_id      NUMBER,                   --FK  
  previous_message_id   NUMBER,                   --FK
  sender_id             NUMBER,                   --FK
  receiver_id           NUMBER,                   --FK
  message               VARCHAR2(400)
 );
 
 CREATE TABLE message_attachments (
  id                          NUMBER,             --PK
  message_id                  NUMBER,             --FK
  url                         VARCHAR2(2000)
 );
 
 CREATE TABLE approvals (
  id                    NUMBER,                   --PK    
  approval_status_id    NUMBER,                   --FK
  reimbursement_id      NUMBER,                   --FK
  approver_id           NUMBER,                   --FK  
  datetimeCreated       DATE,
  reason                VARCHAR2(400)
 );
 
 CREATE TABLE approval_status (
  id                NUMBER,                       --PK      
  approval_status   VARCHAR2(40)
 );
 
 CREATE TABLE logs (
  id                NUMBER,                       --PK
  log_level_id      NUMBER,                       --FK (LOG_LEVEL: ID) 
  datetimeCreated   DATE,
  message           VARCHAR2(400)
 );
 
 CREATE TABLE log_level (
  id                NUMBER,                       --PK
  log_level         VARCHAR2(40)
 );
 
 /******************************************************************************
  * Adds the primary keys to the table.s
  *
  *//*
  -- EMPLOYEES
   ALTER TABLE employees
 ADD CONSTRAINT pk_employees
 PRIMARY KEY (id);
  
  -- REIMBURSEMENT MESSAGE ATTACHMENTS
 ALTER TABLE message_attachments
 ADD CONSTRAINT pk_reim_msg_attach
 PRIMARY KEY (id);
 
   -- ROLE
 ALTER TABLE role
 ADD CONSTRAINT pk_role
 PRIMARY KEY (id);
 
 -- DEPARTMENT
 ALTER TABLE department
 ADD CONSTRAINT pk_department
 PRIMARY KEY (id);
 
   -- REIMBURSEMENT STATUS
 ALTER TABLE reimbursement_status
 ADD CONSTRAINT pk_reimbursement_status
 PRIMARY KEY (id);
 
 -- EVENT TYPE
 ALTER TABLE event_type
 ADD CONSTRAINT pk_event_type
 PRIMARY KEY (id);
 
 -- GRADE FORMAT
 ALTER TABLE grade_format
 ADD CONSTRAINT pk_grade_format
 PRIMARY KEY (id);
 
 -- REIMBURSEMENT GRADES
 ALTER TABLE reimbursement_grades
 ADD CONSTRAINT pk_reimbursement_grades
 PRIMARY KEY (id);
 
 -- REIMBURSEMENTS
 ALTER TABLE reimbursements
 ADD CONSTRAINT pk_reimbursements
 PRIMARY KEY (id);
 
   -- URGENT REIMBURSEMENTS
 ALTER TABLE urgent_reimbursements
 ADD CONSTRAINT pk_urgent_reimbursement
 PRIMARY KEY (reimbursement_id);
 
 -- ALTERED REIMBURSEMENTS
 ALTER TABLE altered_reimbursements
 ADD CONSTRAINT pk_altered_reimbursement
 PRIMARY KEY (reimbursement_id);
 
 -- REIMBURSEMENT ATTACHMENTS
 ALTER TABLE reimbursement_attachments
 ADD CONSTRAINT pk_reim_attachments
 PRIMARY KEY (id);
 
 -- REIMBURSEMENT ATTACHMENT TYPE
 ALTER TABLE reimbursement_attachment_type
 ADD CONSTRAINT pk_reim_attach_type
 PRIMARY KEY (id);
 
 -- REIMBURSEMENT MESSAGES
 ALTER TABLE reimbursement_messages
 ADD CONSTRAINT pk_reim_message
 PRIMARY KEY (id);
 
 -- APPROVALS
 ALTER TABLE approvals
 ADD CONSTRAINT pk_approval
 PRIMARY KEY (id);
 
 -- APPROVAL STATUS
 ALTER TABLE approval_status
 ADD CONSTRAINT pk_approval_status
 PRIMARY KEY (id);
 
 -- LOGS
 ALTER TABLE logs
 ADD CONSTRAINT pk_log
 PRIMARY KEY (id);
 
 -- LOG LEVEL
 ALTER TABLE log_level
 ADD CONSTRAINT pk_log_level
 PRIMARY KEY (id);
 
 /******************************************************************************
  * Adds the foreign keys to the tables.
  *
  *//*
  
 -- EMPLOYEES
 ALTER TABLE employees
 ADD CONSTRAINT fk_employees_supervisor
 FOREIGN KEY (supervisor_id)
 REFERENCES employees (id);
 
 ALTER TABLE employees
 ADD CONSTRAINT fk_employees_role
 FOREIGN KEY (role_id)
 REFERENCES role (id);
 
 ALTER TABLE employees
 ADD CONSTRAINT fk_employees_department
 FOREIGN KEY (department_id)
 REFERENCES department (id);

  -- REIMBURSEMENTS
 
 ALTER TABLE reimbursements
 ADD CONSTRAINT fk_reimbursements_employee
 FOREIGN KEY (employee_id)
 REFERENCES employees (id);
 
 ALTER TABLE reimbursements
 ADD CONSTRAINT fk_reimbursements_status
 FOREIGN KEY (status_id)
 REFERENCES reimbursement_status_id (id);
 
 ALTER TABLE reimbursements
 ADD CONSTRAINT fk_reimbursements_event_type
 FOREIGN KEY (event_type_id)
 REFERENCES event_type (id);
 
 ALTER TABLE reimbursements
 ADD CONSTRAINT fk_reimbursements_grade_format
 FOREIGN KEY (grade_format_id)
 REFERENCES grade_format (id);
 
 ALTER TABLE reimbursements
 ADD CONSTRAINT fk_reimbursements_grade
 FOREIGN KEY (grade_id)
 REFERENCES reimbursement_grades (id);
 
--URGENT REIMBURSEMENTS
 
 ALTER TABLE urgent_reimbursements
 ADD CONSTRAINT fk_urgent_reimbursement
 FOREIGN KEY (reimbursement_id)
 REFERENCES reimbursements (id);
 
 -- ALTERED REIMBURSEMENTS
 
 ALTER TABLE altered_reimbursements
 ADD CONSTRAINT fk_altered_reimbursement
 FOREIGN KEY (reimbursement_id)
 REFERENCES reimbursements (id);
 
 -- REIMBURSEMENT ATTACHMENTS
 
 ALTER TABLE reimbursement_attachments
 ADD CONSTRAINT fk_reim_attachments_reim
 FOREIGN KEY (reimbursement_id)
 REFERENCES reimbursements (id);
 
 ALTER TABLE reimbursement_attachments
 ADD CONSTRAINT fk_reim_attach_attach_type
 FOREIGN KEY (attachment_type_id)
 REFERENCES reimbursement_attachment_type (id);
 
 -- REIMBURSEMENT MESSAGES
 
 ALTER TABLE reimbursement_messages
 ADD CONSTRAINT fk_reim_messages_reim
 FOREIGN KEY (reimbursement_id)
 REFERENCES reimbursements (id);
 
 ALTER TABLE reimbursement_messages
 ADD CONSTRAINT fk_reim_msgs_prev_msgs
 FOREIGN KEY (previous_message_id)
 REFERENCES reimbursement_messages (id);
 
 ALTER TABLE reimbursement_messages
 ADD CONSTRAINT fk_reim_msgs_sender
 FOREIGN KEY (sender_id)
 REFERENCES employees (id);
 
 ALTER TABLE reimbursement_messages
 ADD CONSTRAINT fk_reim_msgs_receiver
 FOREIGN KEY (receiver_id)
 REFERENCES employees(id);
 
 -- REIMBURSE MESSAGE ATTACHMENTS
 
 ALTER TABLE message_attachments
 ADD CONSTRAINT fk_reim_msg_attach_msg
 FOREIGN KEY (message_id)
 REFERENCES reimbursement_messages (id);
 
 -- APROVALS
 
 ALTER TABLE approvals
 ADD CONSTRAINT fk_approvals_status
 FOREIGN KEY (approval_status_id)
 REFERENCES approval_status (id);
 
 ALTER TABLE approvals
 ADD CONSTRAINT fk_approvals_approver
 FOREIGN KEY (approver_id)
 REFERENCES employees(id);
 
 ALTER TABLE approvals
 ADD CONSTRAINT fk_approvals_reimbursement
 FOREIGN KEY (reimbursement_id)
 REFERENCES reimbursements (id);
 
 -- LOGS
 
 ALTER TABLE logs
 ADD CONSTRAINT fk_logs_log_level
 FOREIGN KEY (log_level_id)
 REFERENCES log_level (id);
 
 /******************************************************************************
  * CREATES THE SEQUENCES AND TRIGGERS FOR THE TABLES.
  *
  *//*
  -- EMPLOYEES
  CREATE SEQUENCE seq_emp_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
  /
  
  CREATE OR REPLACE TRIGGER trig_emp_id
    BEFORE INSERT ON employees
    FOR EACH ROW
    BEGIN
      SELECT seq_emp_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- ROLE
  CREATE SEQUENCE seq_role_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_role_id
    BEFORE INSERT ON role
    FOR EACH ROW
    BEGIN
      SELECT seq_role_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
  
  -- DEPARTMENT
  CREATE SEQUENCE seq_department_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_department_id
    BEFORE INSERT ON department
    FOR EACH ROW
    BEGIN
      SELECT seq_department_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENTS
  CREATE SEQUENCE seq_reim_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_reim_id
    BEFORE INSERT ON reimbursements
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENT STATUS
  CREATE SEQUENCE seq_reim_status_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
  
  CREATE OR REPLACE TRIGGER trig_reim_status_id
    BEFORE INSERT ON reimbursement_status
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_status_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- EVENT TYPE
  CREATE SEQUENCE seq_event_type_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_event_type_id
    BEFORE INSERT ON event_type
    FOR EACH ROW
    BEGIN
      SELECT seq_event_type_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- GRADE FORMAT
  CREATE SEQUENCE seq_grade_format_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
  
  CREATE OR REPLACE TRIGGER trig_grade_format_id
    BEFORE INSERT ON grade_format
    FOR EACH ROW
    BEGIN
      SELECT seq_grade_format_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENT GRADES
  CREATE SEQUENCE seq_reim_grades_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_reim_grades_id
    BEFORE INSERT ON reimbursement_grades
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_grades_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENT ATTACHMENTS
  CREATE SEQUENCE seq_reim_attach_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_reim_attach_id
    BEFORE INSERT ON reimbursement_attachments
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_attach_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENT ATTACHMENT TYPE
  CREATE SEQUENCE seq_reim_attach_type_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_reim_attach_type_id
    BEFORE INSERT ON reimbursement_attachment_type
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_attach_type_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENT MESSAGES
  CREATE SEQUENCE seq_reim_messages_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_reim_messages_id
    BEFORE INSERT ON reimbursement_messages
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_messages_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- REIMBURSEMENT MESSAGE ATTACHMENTS
  CREATE SEQUENCE seq_reim_msg_attach_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_reim_msg_attach_id
    BEFORE INSERT ON message_attachments
    FOR EACH ROW
    BEGIN
      SELECT seq_reim_msg_attach_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- APPROVALS
  CREATE SEQUENCE seq_approvals_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_approvals_id
    BEFORE INSERT ON approvals
    FOR EACH ROW
    BEGIN
      SELECT seq_approvals_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- APPROVAL STATUS
  CREATE SEQUENCE seq_approval_status_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_approval_status_id
    BEFORE INSERT ON approval_status
    FOR EACH ROW
    BEGIN
      SELECT seq_approval_status_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- LOGS
  CREATE SEQUENCE seq_logs_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_logs_id
    BEFORE INSERT ON logs
    FOR EACH ROW
    BEGIN
      SELECT seq_logs_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
    
  -- LOG LEVEL
  CREATE SEQUENCE seq_log_level_id
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;
    /
    
  CREATE OR REPLACE TRIGGER trig_log_level_id
    BEFORE INSERT ON log_level
    FOR EACH ROW
    BEGIN
      SELECT seq_log_level_id.NEXTVAL
      INTO :new.id
      FROM dual;
    END;
    /
  /**/  
  
  /* V1
  CREATE OR REPLACE PROCEDURE insert_then_pend_approval_if(approver_id IN NUMBER, reimbursement_id IN NUMBER, reason IN VARCHAR2, approval_status_id IN NUMBER)
    IS
      approval_id NUMBER;
      reim_id NUMBER;
      supervisor_approval NUMBER;
      head_approval NUMBER;
      benco_approval NUMBER;
    BEGIN
      INSERT INTO Approvals (approver_id, reimbursement_id, reason, approval_status_id) VALUES (approver_id, reimbursement_id, reason, approval_status_id);      
      SELECT MAX(id) into approval_id FROM approvals; --get just inserted approval
      SELECT reimbursement_id INTO reim_id FROM approvals where id = approval_id;
      
      --SUPERVISOR
      SELECT COUNT(*) INTO supervisor_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.role_id = 3 AND a1.reimbursement_id = reim_id);
      --HEAD
      SELECT COUNT(*) INTO head_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.role_id = 2 AND a1.reimbursement_id = reim_id);
      --BENCO
      SELECT COUNT(*) INTO benco_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.department_id = 5 AND a1.reimbursement_id = reim_id);
      
      IF (supervisor_approval > 0) THEN
        IF (head_approval > 0) THEN
          IF (benco_approval > 0) THEN
            UPDATE reimbursements SET status_id = 2 WHERE id = reim_id;
          END IF;
        END IF;
      ELSIF (head_approval > 0) THEN
        IF (benco_approval > 0) THEN
          UPDATE reimbursements SET status_id = 2 WHERE id = reim_id;
        END IF;
      END IF;
      
      commit;
    END;
    /
    */
    
create or replace PROCEDURE insert_then_pend_approval_if(approver_id IN NUMBER, reimbursement_id IN NUMBER, approval_status_id IN NUMBER, reason IN VARCHAR2)
    IS
      approval_id NUMBER;
      reim_id NUMBER;
      supervisor_approval NUMBER;
      head_approval NUMBER;
      benco_approval NUMBER;
    BEGIN
      INSERT INTO Approvals (approver_id, reimbursement_id, reason, approval_status_id) VALUES (approver_id, reimbursement_id, reason, approval_status_id);      
      SELECT MAX(id) into approval_id FROM approvals; --get just inserted approval
      SELECT reimbursement_id INTO reim_id FROM approvals where id = approval_id;
      
      --SUPERVISOR
      SELECT COUNT(*) INTO supervisor_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.role_id = 3 AND a1.reimbursement_id = reim_id);
      --HEAD
      SELECT COUNT(*) INTO head_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.role_id = 2 AND a1.reimbursement_id = reim_id);
      --BENCO
      SELECT COUNT(*) INTO benco_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.department_id = 5 AND a1.reimbursement_id = reim_id);
      
      IF (approval_status_id = 2) THEN
        --awarded approval may mean reimbursement becomes pending if 3-step approval process has occurred.
        IF (supervisor_approval > 0) THEN
          IF (head_approval > 0) THEN
            IF (benco_approval > 0) THEN
              UPDATE reimbursements SET status_id = 2 WHERE id = reim_id;
            END IF;
          END IF;
        ELSIF (head_approval > 0) THEN
          IF (benco_approval > 0) THEN
            UPDATE reimbursements SET status_id = 2 WHERE id = reim_id;
          END IF;
        END IF;
      ELSIF (approval_status_id = 3) THEN
        --reimbursement is denied if approval was denied.
        UPDATE reimbursements SET status_id = 5 WHERE id = reim_id;
      END IF;
      
      commit;
    END;
    /
    
    /* NO DON'T USE
    CREATE OR REPLACE TRIGGER trig_pend_approval
      AFTER INSERT ON approvals
      FOR EACH ROW
      DECLARE
        supervisor_approval NUMBER;
        head_approval NUMBER;
        benco_approval NUMBER;
      BEGIN
        --TODO: account for benco application.
        
        --SUPERVISOR
        SELECT COUNT(*) INTO supervisor_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.role_id = 3 AND a1.reimbursement_id = :new.reimbursement_id);
        --HEAD
        SELECT COUNT(*) INTO head_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.role_id = 2 AND a1.reimbursement_id = :new.reimbursement_id);
        --BENCO
        SELECT COUNT(*) INTO benco_approval FROM approvals WHERE EXISTS (select * from approvals a1 inner join employees e1 on a1.approver_id = e1.id AND e1.department_id = 5 AND a1.reimbursement_id = :new.reimbursement_id);
        
        IF (supervisor_approval > 0) THEN
          IF (head_approval > 0) THEN
            IF (benco_approval > 0) THEN
              UPDATE reimbursements SET status_id = 2 WHERE id = :new.reimbursement_id;
            END IF;
          END IF;
        ELSIF (head_approval > 0) THEN
          IF (benco_approval > 0) THEN
            UPDATE reimbursements SET status_id = 2 WHERE id = :new.reimbursement_id;
          END IF;
        END IF;
        
      END;
      /
  */
  --*/
  
  