/**
 * Fills tables of bankdb with initial values
 *
 */

---- populate roles of bankdb
INSERT INTO Role(role_id, role) VALUES (1, 'Admin');
INSERT INTO Role(role_id, role) VALUES (2, 'Employee');
INSERT INTO Role(role_id, role) VALUES (3, 'Customer');

---- populate account statuses of bankdb
INSERT INTO Status (status_id, status) VALUES (1, 'Pending');
INSERT INTO Status (status_id, status) VALUES (2, 'Approved');
INSERT INTO Status (status_id, status) VALUES (3, 'Denied');

---- populate account types of bankdb
INSERT INTO Account_Type (type_id, type) VALUES (1, 'Checking');
INSERT INTO Account_Type (type_id, type) VALUES (2, 'Savings');

