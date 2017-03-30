
COMMIT;
ROLLBACK;

SELECT * FROM Users;
SELECT * FROM Applications;
select * from ClassDateTime;
SELECT * FROM Grading;

SELECT * FROM Approvals;

CALL approveAsManager(1, 2, 1, 'I approve');
CALL setAppToNextAppr(1, 3);

SELECT approval_level, approval_status, approver_id, approval_message FROM Approvals WHERE app_id IN
(SELECT app_id FROM Approvals WHERE app_id = ?);


SELECT app_id, priority_id, event_id, loc, total_cost, grading_id, justification, reimbursement_id 
  FROM Applications WHERE user_id IN (SELECT user_id FROM Applications WHERE user_id = 7);

SELECT APPROVAL_APP_ID, APPROVAL_LEVEL, APPROVAL_STATUS, APPROVER_ID, APPROVAL_MESSAGE 
  FROM Approvals WHERE app_id IN (SELECT app_id FROM Approvals WHERE app_id = 9);

  
SELECT app_id, grading_format_id, grade_cutoff, grade_awarded, presentation_review FROM (
SELECT apps.app_id, gr.grading_format_id, gr.grade_cutoff, gr.grade_awarded, gr.presentation_review
FROM Applications apps
INNER JOIN grading gr ON apps.grading_id = gr.grading_id)
  WHERE app_id IN (SELECT app_id FROM Applications WHERE app_id = 10);
  
  
SELECT app_id, reimbursement_id, projected_reimbursement, awarded_reimbursement, change_reason 
  FROM (SELECT apps.app_id, re.reimbursement_id, re.projected_reimbursement, re.awarded_reimbursement, re.change_reason 
  FROM Applications apps INNER JOIN Reimbursements re ON apps.reimbursement_id = re.reimbursement_id) 
  WHERE app_id IN (SELECT app_id FROM Applications WHERE app_id = 10);

SELECT * FROM (
SELECT us.user_id, us.dept_id, apps.app_id, apps.priority_id, apps.event_id, apps.total_cost, apps.justification, appr.approval_level, appr.approval_status
FROM Applications apps
INNER JOIN Users us ON us.user_id = apps.user_id
INNER JOIN Approvals appr ON apps.app_id = appr.app_id)
WHERE approval_level = 1 AND approval_status = 1 AND dept_id = 1;



SELECT * FROM (SELECT us.user_id, us.dept_id, apps.app_id, apps.priority_id, apps.event_id, apps.total_cost, 
apps.justification, appr.approval_level, appr.approval_status 
FROM Applications apps INNER JOIN Users us ON us.user_id = apps.user_id 
INNER JOIN Approvals appr ON apps.app_id = appr.app_id) 
WHERE approval_level = 3 AND approval_status = 1;