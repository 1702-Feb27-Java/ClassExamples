CALL addApp(2, 2, 3, 'Grand Central', 500, 'for work');
CALL addClassDateTime(1, '01-FEB-17', '05-MAY-17', 3);
CALL addGrading(1, 2, 'C-', 'C');

COMMIT;
ROLLBACK;

SELECT * FROM Applications;
select * from ClassDateTime;