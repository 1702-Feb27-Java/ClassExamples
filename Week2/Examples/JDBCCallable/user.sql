--DROP TABLE
DROP TABLE DEMO;

--CREATE TABLE
CREATE TABLE DEMO
(
id INT,
name VARCHAR2(255),
PRIMARY KEY(id)
);

--CREATE STORED PROCEDURES

CREATE or REPLACE PROCEDURE createUser(e_id IN NUMBER, e_name VARCHAR2)

IS
BEGIN

INSERT INTO demo(id, name) VALUES(e_id, e_name);
COMMIT;
END;



CREATE or REPLACE PROCEDURE updateUser(e_id IN NUMBER, e_name VARCHAR2)

IS
BEGIN

UPDATE demo SET name = e_name WHERE id = e_id;
COMMIT;
END updateUser;



CREATE or REPLACE PROCEDURE retrieveUser(e_id IN NUMBER, c_row OUT SYS_REFCURSOR)

IS
BEGIN
OPEN c_row FOR
SELECT * FROM demo WHERE ID = e_id;
END retrieveUser;




CREATE or REPLACE PROCEDURE retrieveUser2(e_id IN NUMBER, o_id OUT NUMBER, o_name OUT VARCHAR2)

IS
BEGIN

SELECT *
INTO o_id, o_name
FROM demo
WHERE id = e_id;
END retrieveUser2;

--INSERT TEST DATABASE

INSERT INTO demo(id, name) VALUES(1, 'Nick');
INSERT INTO demo(id, name) VALUES(2, 'Ricky');
INSERT INTO demo(id, name) VALUES(3, 'Steven');
INSERT INTO demo(id, name) VALUES(4, 'Patrick');
INSERT INTO demo(id, name) VALUES(5, 'Brian');
INSERT INTO demo(id, name) VALUES(6, 'Yuvi');
INSERT INTO demo(id, name) VALUES(7, 'August');
INSERT INTO demo(id, name) VALUES(8, 'Joe');
INSERT INTO demo(id, name) VALUES(9, 'Ryan');
INSERT INTO demo(id, name) VALUES(10, 'Fred');
INSERT INTO demo(id, name) VALUES(11, 'Arun');
INSERT INTO demo(id, name) VALUES(12, 'Kuran');
INSERT INTO demo(id, name) VALUES(13, 'Bart');
INSERT INTO demo(id, name) VALUES(14, 'Homer');
INSERT INTO demo(id, name) VALUES(15, 'Lisa');