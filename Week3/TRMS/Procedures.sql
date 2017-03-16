CREATE OR REPLACE PROCEDURE insertEmployee(dept IN number, fn IN varchar2, ln IN varchar2, un IN varchar2,
                                            pw IN varchar2, phone IN varchar2, email IN varchar2)
IS
  sup_id number;
BEGIN
  SELECT EMPLOYEE_ID INTO sup_id FROM EMPLOYEE WHERE ROLE_ID = 2 AND DEPT_ID = dept;
  INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, PHONE, EMAIL, MESSAGE) 
  VALUES(1, 1, dept, sup_id, fn, ln, un, pw, phone, email, 0); 
  COMMIT;
END;
/
