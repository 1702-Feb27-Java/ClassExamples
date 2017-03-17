CREATE TABLE Users
(
    userId NUMBER NOT NULL,
    firstName VARCHAR2(35) NOT NULL,
    middleName VARCHAR2(25),
    lastName VARCHAR2(35) NOT NULL,
    userName VARCHAR2(30)  NOT NULL,
    pw VARCHAR2 (15)NOT NULL,
    email VARCHAR2(320) NOT NULL,
    roleId NUMBER,
    PRIMARY KEY(userId),
    FOREIGN KEY(roleId) REFERENCES Role(roleId),
    FOREIGN KEY(deptId) ReFERENCES DEPARTMENT(deptId)
);

CREATE TABLE Role(
  roleId NUMBER,
  role VARCHAR2(25),
  
  CONSTRAINT PK_Role PRIMARY KEY(roleId)
);

CREATE TABLE Department(
  deptId NUMBER,
  deptName VARCHAR2(65),
  CONSTRAINT PK_Department PRIMARY KEY (deptId)
);

CREATE TABLE Events(
  eventId NUMBER,
  eventName VARCHAR2(65),
  CONSTRAINT PK_Events PRIMARY KEY (eventId)
);


--------------------- populating Roles tables---------------------
INSERT INTO ROLE (ROLEID,role) VALUES (1,'employee');
INSERT INTO ROLE (ROLEID,role) VALUES (2,'Direct Supervisor');
INSERT INTO ROLE (ROLEID,role) VALUES (3,'Department Head');
INSERT INTO ROLE (ROLEID,role) VALUES (4,'Benefits Coordinator');

-------------------- Populating Events---------------------
INSERT INTO Events (eventId, eventName) VALUES (1,'University Course');
INSERT INTO Events (eventId, eventName) VALUES (2,'Seminar');
INSERT INTO Events (eventId, eventName) VALUES (3,'Certification');
INSERT INTO Events (eventId, eventName) VALUES (4,'Technical training');

---- Populating Departments------------------------------------
INSERT INTO Department (deptId, deptName) VALUES (1,'Java');
INSERT INTO Department (deptId, deptName) VALUES (2,'C#');







