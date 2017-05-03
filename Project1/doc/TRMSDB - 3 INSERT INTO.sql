/*******************************************************************************
 * ADDS DATA TO THE TABLES
 *
 * There are 18 tables altogether:
 * 10 'normal' tables
 * 8 lookup tables
 *
 * AUTHOR: Michael Hobbs
 * DATE: 2017 March 24
 */
 
 CONN trmsdb/password2
 
 /******************************************************************************
  * LOOKUP TABLES
  *
  *//* EXECUTED
 -------------------------------------------------------------------------------
 -- ROLE
 --
 -- Employees have a role.
 --
 INSERT INTO role (role) VALUES ('sysadmin');      --1
 INSERT INTO role (role) VALUES ('head');          --2
 INSERT INTO role (role) VALUES ('supervisor');    --3
 INSERT INTO role (role) VALUES ('employee');      --4
 
 -------------------------------------------------------------------------------
 -- DEPARTMENT
 --
 -- Employees are in a department.
 --
 INSERT INTO department (department) VALUES ('RD');     --1
 INSERT INTO department (department) VALUES ('IT');     --2
 INSERT INTO department (department) VALUES ('PR');     --3
 INSERT INTO department (department) VALUES ('HR');     --4
 INSERT INTO department (department) VALUES ('Benco');  --5
 
 -------------------------------------------------------------------------------
 -- REIMBURSEMENT_STATUS
 --
 -- Reimbursements have a status.
 --
 INSERT INTO reimbursement_status (reimbursement_status) VALUES ('open');       --1
 INSERT INTO reimbursement_status (reimbursement_status) VALUES ('pending');    --2
 INSERT INTO reimbursement_status (reimbursement_status) VALUES ('cancelled');  --3
 INSERT INTO reimbursement_status (reimbursement_status) VALUES ('awarded');    --4
 INSERT INTO reimbursement_status (reimbursement_status) VALUES ('denied');     --5
 
 -------------------------------------------------------------------------------
 -- EVENT_TYPE
 --
 -- Reimbursements have an event type the cost of which they will cover.
 --
 -- NOTE : Percentages are stored as integers (e.g., 80% is not stored as .8 but as 80).
 --
 INSERT INTO event_type (event_type, coverage_percentage) VALUES ('university', 80);      --1
 INSERT INTO event_type (event_type, coverage_percentage) VALUES ('seminar', 60);         --2
 INSERT INTO event_type (event_type, coverage_percentage) VALUES ('cert_prep', 75);       --3
 INSERT INTO event_type (event_type, coverage_percentage) VALUES ('cert', 100);           --4
 INSERT INTO event_type (event_type, coverage_percentage) VALUES ('tech_training', 90);   --5
 INSERT INTO event_type (event_type, coverage_percentage) VALUES ('other', 30);           --6
 
 -------------------------------------------------------------------------------
 -- GRADE_FORMAT
 --
 -- Reimbursements have a grade format.
 --
 INSERT INTO grade_format (grades, passing_grade) VALUES ('Pass/Fail', 'Pass');                               --1
 INSERT INTO grade_format (grades, passing_grade) VALUES ('A/B/C/D/F', 'C');                                  --2
 INSERT INTO grade_format (grades, passing_grade) VALUES ('Percentage', '65%');                               --3
 INSERT INTO grade_format (grades, passing_grade) VALUES ('Presentation', 'Satisfactory');                    --4
 
 -------------------------------------------------------------------------------
 -- REIMBURSEMENT_ATTACHMENT_TYPE
 --
 -- Reimbursement attachments have a type.
 --
 INSERT INTO reimbursement_attachment_type (attachment_type) VALUES ('event-related');            --1
 INSERT INTO reimbursement_attachment_type (attachment_type) VALUES ('supervisor approval');      --2
 INSERT INTO reimbursement_attachment_type (attachment_type) VALUES ('department-head approval'); --3  
 INSERT INTO reimbursement_attachment_type (attachment_type) VALUES ('grade');                    --4
 
 -------------------------------------------------------------------------------
 -- APPROVAL_STATUS
 --
 -- Approvals have a status.
 --
 INSERT INTO approval_status (approval_status) VALUES ('pending');    --1
 INSERT INTO approval_status (approval_status) VALUES ('awarded');    --2
 INSERT INTO approval_status (approval_status) VALUES ('denied');     --3
 
 -------------------------------------------------------------------------------
 -- LOG_LEVEL
 --
 -- Logs have a log level.
 --
 INSERT INTO log_level (log_level) VALUES ('all');      --1
 INSERT INTO log_level (log_level) VALUES ('trace');    --2
 INSERT INTO log_level (log_level) VALUES ('debug');    --3
 INSERT INTO log_level (log_level) VALUES ('info');     --4
 INSERT INTO log_level (log_level) VALUES ('warn');     --5
 INSERT INTO log_level (log_level) VALUES ('error');    --6
 INSERT INTO log_level (log_level) VALUES ('fatal');    --7
 INSERT INTO log_level (log_level) VALUES ('off');      --8
 
 */
 
 /******************************************************************************
  * 'NORMAL' TABLES
  *
  *//* EXECUTED
 -------------------------------------------------------------------------------
 -- EMPLOYEES
 --
 
 ---------------------------------------
 -- DEPARTMENT HEADS
 --
 INSERT INTO employees --head of rd
 (username, "password", email, first_name, last_name, role_id, department_id) VALUES
 ('michaelhobbs', 'password', 'michael.a.hobbs@gmail.com', 'Michael', 'Hobbs', 2, 1);
 
 INSERT INTO employees --head of it
 (username, "password", email, first_name, last_name, role_id, department_id) VALUES
 ('meli_b', 'honey', 'meli_b@example.com', 'Melissa', 'Blackwell', 2, 2);
 
 INSERT INTO employees --head of pr
 (username, "password", email, first_name, last_name, role_id, department_id) VALUES
 ('hollym', 'doseo', 'hollymiller@example.com', 'Holly', 'Miller', 2, 3);
 
 INSERT INTO employees --head of hr (also supervisor)
 (username, "password", email, first_name, last_name, role_id, department_id) VALUES
 ('averycannon', 'averypassword', 'avery_cannon@example.com', 'Avery', 'Cannon', 2, 4);
 
 INSERT INTO employees --head of benco
 (username, "password", email, first_name, last_name, role_id, department_id) VALUES
 ('benjiro', 'bennie', 'benjirosan@example.com', 'Benjiro', 'Ito', 2, 5);

 ---------------------------------------
 -- SUPERVISORS
 --
 INSERT INTO employees --rd supervisor 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('eseymour', 'selwyn', 'elvinseymour@example.com', 'Elvin', 'Seymour', 3, 1, 1);
  INSERT INTO employees --rd supervisor 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('yim', 'chus', 'yim@example.com', 'Yi', 'Mitchell', 3, 1, 1);
  INSERT INTO employees --rd supervisor 3
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('jherbert', 'jeangrey', 'jean@example.com', 'Jeanette', 'Herbert', 3, 1, 1);
  INSERT INTO employees --rd supervisor 4
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('eddiej', 'misterjeong', 'eddiej@example.com', 'Edgar', 'Jeong', 3, 1, 1);
 
 INSERT INTO employees --it supervisor 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('pradilla', 'deeepend', 'pradeep@example.com', 'Pradeep', 'Villa', 3, 2, 2);
 INSERT INTO employees --it supervisor 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('lysette', 'valerian', 'lysette@example.com', 'Lysette', 'Woodhams', 3, 2, 2);
 INSERT INTO employees --it supervisor 3
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('sanj', 'kathleenbaby', 'bigsanjiv@example.com', 'Sanjiv', 'Singh', 3, 2, 2);
 
 INSERT INTO employees --pr supervisor 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('lilabby', 'routh123', 'abbybaby@example.com', 'Lily', 'Abbott', 3, 3, 3);
 INSERT INTO employees --pr supervisor 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('miguel', 'agua', 'miguel@example.com', 'Miguel', 'Sano', 3, 3, 3);
 
 --INSERT INTO employees --hr supervisor 1 (is actually also the head)
 --(username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 --();
 
 INSERT INTO employees --benco supervisor 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('seong', 'song', 'seongssi@example.com', 'Seong', 'Cho', 3, 5, 5);
 
 ---------------------------------------
 -- EMPLOYEES
 --
 
 INSERT INTO employees --rd employee 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('seanb', 'mean', 'sean@example.com', 'Sean', 'Bean', 4, 6, 1);
 INSERT INTO employees --rd employee 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('weaver', 'sigourney', 'ariella@example.com', 'Ariella', 'Weaver', 4, 6, 1);
 INSERT INTO employees --rd employee 3
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('dparsons', 'dpw', 'dparsons@example.com', 'Darnell', 'Parsons', 4, 6, 1);
 INSERT INTO employees --rd employee 4
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('epam', 'electric bread', 'epam@example.com', 'Emilia', 'Pam', 4, 7, 1);
 INSERT INTO employees --rd employee 5
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('rachel', 'opensesame', 'rachel@example.com', 'Rachel', 'Masters', 4, 7, 1);
 INSERT INTO employees --rd employee 6
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('ftw', 'mow', 'ftw@example.com', 'Merideth', 'Winship', 4, 8, 1);
 INSERT INTO employees --rd employee 7
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('wheelie', 'pop', 'wheelie@example.com', 'Kyleigh', 'Wheeler', 4, 8, 1);
 INSERT INTO employees --rd employee 8
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('tabitha', 'love', 'tabitha@example.com', 'Tabitha', 'Love', 4, 8, 1);
 INSERT INTO employees --rd employee 9
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('msray', 'sunspots', 'irisray@example.com', 'Iris', 'Ray', 4, 8, 1);
 INSERT INTO employees --rd employee 10
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('patrick', 'slayer', 'patricktheslayer@example.com', 'Patrick', 'Thayer', 4, 8, 1);
 INSERT INTO employees --rd employee 11
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('xi', 'abcxyz', 'xi@example.com', 'Xiomara', 'Jacob', 4, 9, 1);
 INSERT INTO employees --rd employee 12
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('gil', 'goodoldgil', 'gil@example.com', 'Gil', 'Abraham', 4, 9, 1);
 INSERT INTO employees --rd employee 13
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('hatoful', 'boyfriend', 'hatty@example.com', 'Hatty', 'Simmons', 4, 9, 1);
 INSERT INTO employees --rd employee 14
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('melina', 'mmmhmm', 'melinamolina@example.com', 'Melina', 'Molina', 4, 9, 1);
 
 INSERT INTO employees --it employee 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('cwoodrow', 'cdog', 'cwoodrow@example.com', 'Curtis', 'Woodrow', 4, 10, 2);
 INSERT INTO employees --it employee 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('felix', 'the cat', 'felixj@example.com', 'Felix', 'Jimenez', 4, 10, 2);
 INSERT INTO employees --it employee 3
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('zelda', 'princess', 'zeldabass@example.com', 'Zelda', 'Bass', 4, 10, 2);
 INSERT INTO employees --it employee 4
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('foster', 'yesyesyes', 'foster@example.com', 'Yeshua', 'Foster', 4, 10, 2);
 INSERT INTO employees --it employee 5
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('kelly', 'kellyanne', 'kelsilucas@example.com', 'Kelsi', 'Lucas', 4, 11, 2);
 INSERT INTO employees --it employee 6
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('federico', 'isaura', 'federicotasker@example.com', 'Federico', 'Tasker', 4, 11, 2);
 INSERT INTO employees --it employee 7
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('dory', 'justkeepswimming', 'doryfisher@example.com', 'Dory', 'Fisher', 4, 11, 2);
 INSERT INTO employees --it employee 8
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('lianna', 'leah', 'lianna@example.com', 'Lianna', 'Farrow', 4, 12, 2);
 
 INSERT INTO employees --pr employee 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('don', 'donniedarko', 'donovan@example.com', 'Donovan', 'Holstead', 4, 13, 3);
 INSERT INTO employees --pr employee 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('homer', 'simpsons', 'homer@example.com', 'Homer', 'Simmonds', 4, 13, 3);
 INSERT INTO employees --pr employee 3
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('peta', 'flowers', 'petachandler@example.com', 'Peta', 'Chandler', 4, 13, 3);
 INSERT INTO employees --pr employee 4
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('maribel', 'christina', 'maribelfirmin@example.com', 'Maribel', 'Firmin', 4, 14, 3);
 INSERT INTO employees --pr employee 5
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('elon', 'tesla', 'elonhargrave@example.com', 'Elon', 'Hargrave', 4, 14, 3);
 INSERT INTO employees --pr employee 6
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('stephen', 'zhao', 'stephenzheng@example.com', 'Stephen', 'Zheng', 4, 14, 3);
 INSERT INTO employees --pr employee 7
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('hailee', 'duff', 'haileelambert@example.com', 'Hailee', 'Lambert', 4, 14, 3);
 
 INSERT INTO employees --hr employee 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('shivali', 'sksksk', 'skumar@example.com', 'Shivali', 'Kumar', 4, 4, 4);
 INSERT INTO employees --hr employee 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('lynna', 'lkh123', 'lynnahowland@example.com', 'Lynna', 'Howland', 4, 4, 4);
 INSERT INTO employees --hr employee 3
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('lorne', 'lonerangerwarwick', 'lornewarwick@example.com', 'Lorne', 'Warwick', 4, 4, 4);
 INSERT INTO employees --hr employee 4
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('emma', 'edison', 'emmawatson@example.com', 'Emma', 'Watson', 4, 4, 4);
 
 INSERT INTO employees --benco employee 1
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('paola', 'password123', 'paoladeluca@example.com', 'Paola', 'DeLuca', 4, 15, 5);
 INSERT INTO employees --benco employee 2
 (username, "password", email, first_name, last_name, role_id, supervisor_id, department_id) VALUES
 ('hannah', 'one', 'hannahlee@example.com', 'Hannah', 'Lee', 4, 15, 5);
 */
 