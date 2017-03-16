CREATE TABLE Users
(
    userId NUMBER NOT NULL,
    firstName VARCHAR2(35) NOT NULL,
    middleName VARCHAR2(25),
    lastName VARCHAR2(35) NOT NULL,
    userName VARCHAR2(30)  NOT NULL,
    pw VARCHAR2 (15)NOT NULL,
    email VARCHAR2(320) NOT NULL,
    CONSTRAINT PK_users PRIMARY KEY  (userid)
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
