CREATE OR REPLACE PROCEDURE insertEmployee(dept IN number, fn IN varchar2, ln IN varchar2, un IN varchar2,
                                            pw IN varchar2, phone IN varchar2, email IN varchar2)
IS
BEGIN
  INSERT INTO Employee(EMPLOYEE_ID, ROLE_ID, DEPT_ID, SUPERVISOR_ID, FIRST_NAME, LAST_NAME, USERNAME, PASS, PHONE, EMAIL, MESSAGE) 
  VALUES(1, 3, dept, 1, fn, ln, un, pw, phone, email, 0); 
  COMMIT;
END;
/
DECLARE

BEGIN
  INSERTEMPLOYEE(2, 'ben', 'webster', 'benwebsta', 'password', '7146421889', 'tacosnak@gmail.com');
END;
/