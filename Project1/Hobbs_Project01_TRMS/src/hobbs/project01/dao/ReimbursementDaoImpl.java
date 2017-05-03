package hobbs.project01.dao;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import hobbs.project01.pojo.Approval;
import hobbs.project01.pojo.Approval.Status;
import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.Grade;
import hobbs.project01.pojo.GradeFormat;
import hobbs.project01.pojo.Reimbursement;
import hobbs.project01.pojo.ReimbursementAttachment;
import hobbs.project01.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
	private static ReimbursementDao reimbursementDao;
	
	private ReimbursementDaoImpl() {
		super();
	}
	
	public static ReimbursementDao getReimbursementDao() {
		if (reimbursementDao == null) {
			reimbursementDao = new ReimbursementDaoImpl();
		}
		return reimbursementDao;
	}

	@Override
	public void addReimbursement(Reimbursement reimbursement) {
		String insertSql;
		PreparedStatement insertStatement;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			// insert reimbursement
			insertSql = "INSERT INTO reimbursements (employee_id, status_id, event_type_id, grade_format_id, start_datetime, end_datetime, description, justification, location, worktime_to_be_missed, cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setInt(1, reimbursement.getEmployeeId());
			insertStatement.setInt(2,  Reimbursement.Status.getDefaultStatus());
			insertStatement.setInt(3, reimbursement.getEventTypeId());
			insertStatement.setInt(4, reimbursement.getGradeFormatId());
			insertStatement.setTimestamp(5, reimbursement.getStartDatetime());
			insertStatement.setTimestamp(6, reimbursement.getEndDatetime());
			insertStatement.setString(7, reimbursement.getDescription());
			insertStatement.setString(8, reimbursement.getJustification());
			insertStatement.setString(9, reimbursement.getLocation());
			insertStatement.setString(10, reimbursement.getWorktimeToBeMissed());
			insertStatement.setDouble(11, reimbursement.getCost());
			insertStatement.executeUpdate();
			
			// insert attachments if any
			if (reimbursement.getAttachments() != null) {
				int reimbursementId;
				String selectSql = "SELECT MAX(id) id FROM reimbursements";
				Statement statement = connection.createStatement();
				ResultSet reimbursementResult = statement.executeQuery(selectSql);
				reimbursementResult.next();
				reimbursementId = reimbursementResult.getInt("id");
				
				for (ReimbursementAttachment attachment : reimbursement.getAttachments()) {
					attachment.setReimbursementId(reimbursementId);
					insertSql = "INSERT INTO reimbursement_attachments (reimbursement_id, attachment_type_id, url) VALUES (?, ?, ?)";
					insertStatement = connection.prepareStatement(insertSql);
					insertStatement.setInt(1, attachment.getReimbursementId());
					insertStatement.setInt(2, attachment.getAttachmentTypeId());
					insertStatement.setString(3, attachment.getUrl());
					insertStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.err.println("exception adding reimbursement for employee " + reimbursement.getEmployeeId());
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Reimbursement> getReimbursementsFor(Employee employee) {
		List<Reimbursement> reimbursements = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "";
			PreparedStatement selectStatement = null;
			ResultSet reimbursementResults = null;
			boolean doAdd = false;
			
			// how to retrieve reimbursements for a 'manager' depends on the role of the 'manager' (supervisor vs head).
			if (employee.getRoleId() == Employee.Role.supervisor.getId()) {
				selectSql = "SELECT * FROM reimbursements INNER JOIN employees ON reimbursements.employee_id = employees.id AND employees.SUPERVISOR_ID = ?";
				selectStatement = connection.prepareStatement(selectSql);
				selectStatement.setInt(1, employee.getId());
				reimbursementResults = selectStatement.executeQuery();
				if (reimbursementResults.next()) {
					doAdd = true;
				}
			}
			else if (employee.getRoleId() == Employee.Role.head.getId()) {
				// possibility 1: reimbursement has approval AND one of my supes approved.
				selectSql = "SELECT * FROM reimbursements " +
						"INNER JOIN approvals ON " +
						"reimbursements.id = approvals.reimbursement_id AND " +
						"approvals.approver_id IN (SELECT id FROM employees supervisors WHERE supervisors.supervisor_id = ?)";
				selectStatement = connection.prepareStatement(selectSql);
				selectStatement.setInt(1, employee.getId());
				reimbursementResults = selectStatement.executeQuery();
				if (reimbursementResults.next()) {
					doAdd = true;
				}
				else {
					// possibility 2: if possibility 1 returns empty, then i must be the only supe and i should see if my emps have applied.
					selectSql = "SELECT * FROM reimbursements INNER JOIN employees ON reimbursements.employee_id = employees.id AND employees.SUPERVISOR_ID = ?";
					selectStatement = connection.prepareStatement(selectSql);
					selectStatement.setInt(1, employee.getId());
					reimbursementResults = selectStatement.executeQuery();
					if (reimbursementResults.next()) {
						doAdd = true;
					}
				}
			}
			else if (employee.getDepartmentId() == Employee.Department.Benco.getId()) {
				selectSql = "SELECT * FROM reimbursements " +
						//--reimbursement has an approval
						"INNER JOIN approvals " +
						"ON reimbursements.id = approvals.reimbursement_id " +
						//--applicant's supervisor has approved
						"INNER JOIN (SELECT supervisor.id supervisor_id, employee.id employee_id FROM employees supervisor INNER JOIN employees employee ON employee.supervisor_id = supervisor.id) supervisor " +
						"ON approvals.approver_id = supervisor.supervisor_id AND supervisor.employee_id = reimbursements.employee_id " +
						//--applicant's department head has approved
						"INNER JOIN (SELECT head.id head_id, employee.id employee_id FROM employees head INNER JOIN employees employee ON employee.DEPARTMENT_ID = head.department_id AND head.role_id = ?) head " +
						"ON approvals.approver_id = supervisor.supervisor_id AND head.employee_id = reimbursements.employee_id " +
						//--not my own reimbursement!
						"WHERE reimbursements.employee_id != ?"; //--benco id
				selectStatement = connection.prepareStatement(selectSql);
				selectStatement.setInt(1, Employee.Role.head.getId());
				selectStatement.setInt(2, employee.getId());
				reimbursementResults = selectStatement.executeQuery();
				if (reimbursementResults.next()) {
					doAdd = true;
				}
			}
			
			reimbursements = new ArrayList<Reimbursement>();
			if (doAdd) {
				addReimbursements(connection, reimbursementResults, reimbursements);
			}
		} catch (SQLException e) {
			System.err.println("exception getting reimbursements for employee " + employee.getId());
			e.printStackTrace();
		}
		
		return reimbursements;
	}
	
	/**
	 * Helper method to pull reimbursements from resultset.
	 * 
	 * @param connection
	 * @param reimbursementResults
	 * @param reimbursements
	 * @throws SQLException
	 */
	private void addReimbursements(Connection connection, ResultSet reimbursementResults, List<Reimbursement> reimbursements) throws SQLException {
		do {
			Reimbursement reimbursement = new Reimbursement(
					reimbursementResults.getInt("id"),
					reimbursementResults.getInt("employee_id"),
					reimbursementResults.getInt("status_id"),
					reimbursementResults.getInt("event_type_id"),
					reimbursementResults.getInt("grade_format_id"),
					reimbursementResults.getInt("grade_id"),
					reimbursementResults.getTimestamp("start_datetime"),
					reimbursementResults.getTimestamp("end_datetime"),
					reimbursementResults.getTimestamp("datetimecreated"),
					reimbursementResults.getString("description"),
					reimbursementResults.getString("justification"),
					reimbursementResults.getString("location"),
					reimbursementResults.getString("worktime_to_be_missed"),
					reimbursementResults.getDouble("cost")
					);
			reimbursements.add(reimbursement);
			
			//GET ATTACHMENTS
			List<ReimbursementAttachment> attachments = new ArrayList<>();
			String selectSqlAttachments = "SELECT * FROM reimbursement_attachments WHERE reimbursement_id = ?";
			PreparedStatement selectStatementAttachments = connection.prepareStatement(selectSqlAttachments);
			selectStatementAttachments.setInt(1, reimbursement.getId());
			ResultSet attachmentResults = selectStatementAttachments.executeQuery();
			while (attachmentResults.next()) {
				ReimbursementAttachment attachment = new ReimbursementAttachment(
						attachmentResults.getInt("id"),
						attachmentResults.getInt("reimbursement_id"),
						attachmentResults.getInt("attachment_type_id"),
						attachmentResults.getString("url")
						);
				attachments.add(attachment);
			}
			if (!attachments.isEmpty()) {
				reimbursement.setAttachments(attachments);
			}
			
			//GET GRADE FORMAT
			String selectSqlGradeFormat = "SELECT * FROM grade_format WHERE id = ?";
			PreparedStatement selectStatementGradeFormat = connection.prepareStatement(selectSqlGradeFormat);
			selectStatementGradeFormat.setInt(1, reimbursement.getGradeFormatId());
			ResultSet gradeFormatResult = selectStatementGradeFormat.executeQuery();
			gradeFormatResult.next();
			GradeFormat gradeFormat = new GradeFormat(
					gradeFormatResult.getInt("id"),
					gradeFormatResult.getString("grades"),
					gradeFormatResult.getString("passing_grade")
					);
			reimbursement.setGradeFormat(gradeFormat);
			
			//GET GRADE
			if (reimbursement.getGradeId() != 0) { //if == 0 then was null (i.e., there was no grade) 
				String selectSqlGrade = "SELECT * FROM reimbursement_grades WHERE id = ?";
				PreparedStatement selectStatementGrade = connection.prepareStatement(selectSqlGrade);
				selectStatementGrade.setInt(1, reimbursement.getGradeId());
				ResultSet gradeResult = selectStatementGrade.executeQuery();
				gradeResult.next();
				Grade grade = new Grade(
						gradeResult.getInt("id"),
						gradeResult.getInt("reimbursement_id"),
						gradeResult.getString("grade")
						);
				reimbursement.setGrade(grade);
			}
		} while (reimbursementResults.next());
	}
	
	@Override
	public List<Reimbursement> getReimbursementsOf(Employee employee) {
		List<Reimbursement> reimbursements = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM reimbursements WHERE employee_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, employee.getId());
			ResultSet reimbursementResults = selectStatement.executeQuery();
			reimbursements = new ArrayList<Reimbursement>();
			while (reimbursementResults.next()) {
				Reimbursement reimbursement = new Reimbursement(
						reimbursementResults.getInt("id"),
						reimbursementResults.getInt("employee_id"),
						reimbursementResults.getInt("status_id"),
						reimbursementResults.getInt("event_type_id"),
						reimbursementResults.getInt("grade_format_id"),
						reimbursementResults.getInt("grade_id"),
						reimbursementResults.getTimestamp("start_datetime"),
						reimbursementResults.getTimestamp("end_datetime"),
						reimbursementResults.getTimestamp("datetimecreated"),
						reimbursementResults.getString("description"),
						reimbursementResults.getString("justification"),
						reimbursementResults.getString("location"),
						reimbursementResults.getString("worktime_to_be_missed"),
						reimbursementResults.getDouble("cost")
						);
				reimbursements.add(reimbursement);
				
				//GET ATTACHMENTS
				List<ReimbursementAttachment> attachments = new ArrayList<>();
				String selectSqlAttachments = "SELECT * FROM reimbursement_attachments WHERE reimbursement_id = ?";
				PreparedStatement selectStatementAttachments = connection.prepareStatement(selectSqlAttachments);
				selectStatementAttachments.setInt(1, reimbursement.getId());
				ResultSet attachmentResults = selectStatementAttachments.executeQuery();
				while (attachmentResults.next()) {
					ReimbursementAttachment attachment = new ReimbursementAttachment(
							attachmentResults.getInt("id"),
							attachmentResults.getInt("reimbursement_id"),
							attachmentResults.getInt("attachment_type_id"),
							attachmentResults.getString("url")
							);
					attachments.add(attachment);
				}
				if (!attachments.isEmpty()) {
					reimbursement.setAttachments(attachments);
				}
				
				//GET GRADE FORMAT
				String selectSqlGradeFormat = "SELECT * FROM grade_format WHERE id = ?";
				PreparedStatement selectStatementGradeFormat = connection.prepareStatement(selectSqlGradeFormat);
				selectStatementGradeFormat.setInt(1, reimbursement.getGradeFormatId());
				ResultSet gradeFormatResult = selectStatementGradeFormat.executeQuery();
				gradeFormatResult.next();
				GradeFormat gradeFormat = new GradeFormat(
						gradeFormatResult.getInt("id"),
						gradeFormatResult.getString("grades"),
						gradeFormatResult.getString("passing_grade")
						);
				reimbursement.setGradeFormat(gradeFormat);
				
				//GET GRADE
				if (reimbursement.getGradeId() != 0) { //if == 0 then was null (i.e., there was no grade) 
					String selectSqlGrade = "SELECT * FROM reimbursement_grades WHERE id = ?";
					PreparedStatement selectStatementGrade = connection.prepareStatement(selectSqlGrade);
					selectStatementGrade.setInt(1, reimbursement.getGradeId());
					ResultSet gradeResult = selectStatementGrade.executeQuery();
					gradeResult.next();
					Grade grade = new Grade(
							gradeResult.getInt("id"),
							gradeResult.getInt("reimbursement_id"),
							gradeResult.getString("grade")
							);
					reimbursement.setGrade(grade);
				}
			}
			
		} catch (SQLException e) {
			System.err.println("exception getting reimbursements of employee " + employee.getId());
			e.printStackTrace();
		}
		
		return reimbursements;
	}

	@Override
	public Reimbursement getReimbursement(Integer id) {
		Reimbursement reimbursement = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM reimbursements WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, id);
			ResultSet reimbursementResult = selectStatement.executeQuery();
			reimbursementResult.next();
			reimbursement = new Reimbursement(
					reimbursementResult.getInt("id"),
					reimbursementResult.getInt("employee_id"),
					reimbursementResult.getInt("status_id"),
					reimbursementResult.getInt("event_type_id"),
					reimbursementResult.getInt("grade_format_id"),
					reimbursementResult.getInt("grade_id"),
					reimbursementResult.getTimestamp("start_datetime"),
					reimbursementResult.getTimestamp("end_datetime"),
					reimbursementResult.getTimestamp("datetimecreated"),
					reimbursementResult.getString("description"),
					reimbursementResult.getString("justification"),
					reimbursementResult.getString("location"),
					reimbursementResult.getString("worktime_to_be_missed"),
					reimbursementResult.getDouble("cost")
					);
			
			//GET ATTACHMENTS
			List<ReimbursementAttachment> attachments = new ArrayList<>();
			String selectSqlAttachments = "SELECT * FROM reimbursement_attachments WHERE reimbursement_id = ?";
			PreparedStatement selectStatementAttachments = connection.prepareStatement(selectSqlAttachments);
			selectStatementAttachments.setInt(1, reimbursement.getId());
			ResultSet attachmentResults = selectStatementAttachments.executeQuery();
			while (attachmentResults.next()) {
				ReimbursementAttachment attachment = new ReimbursementAttachment(
						attachmentResults.getInt("id"),
						attachmentResults.getInt("reimbursement_id"),
						attachmentResults.getInt("attachment_type_id"),
						attachmentResults.getString("url")
						);
				attachments.add(attachment);
			}
			if (!attachments.isEmpty()) {
				reimbursement.setAttachments(attachments);
			}
			
			//GET GRADE FORMAT
			String selectSqlGradeFormat = "SELECT * FROM grade_format WHERE id = ?";
			PreparedStatement selectStatementGradeFormat = connection.prepareStatement(selectSqlGradeFormat);
			selectStatementGradeFormat.setInt(1, reimbursement.getGradeFormatId());
			ResultSet gradeFormatResult = selectStatementGradeFormat.executeQuery();
			gradeFormatResult.next();
			GradeFormat gradeFormat = new GradeFormat(
					gradeFormatResult.getInt("id"),
					gradeFormatResult.getString("grades"),
					gradeFormatResult.getString("passing_grade")
					);
			reimbursement.setGradeFormat(gradeFormat);
			
			//GET GRADE
			if (reimbursement.getGradeId() != 0) { //if == 0 then was null (i.e., there was no grade) 
				String selectSqlGrade = "SELECT * FROM reimbursement_grades WHERE id = ?";
				PreparedStatement selectStatementGrade = connection.prepareStatement(selectSqlGrade);
				selectStatementGrade.setInt(1, reimbursement.getGradeId());
				ResultSet gradeResult = selectStatementGrade.executeQuery();
				gradeResult.next();
				Grade grade = new Grade(
						gradeResult.getInt("id"),
						gradeResult.getInt("reimbursement_id"),
						gradeResult.getString("grade")
						);
				reimbursement.setGrade(grade);
			}
			
		} catch (SQLException e) {
			System.err.println("exception getting reimbursement #" + id);
			e.printStackTrace();
		}
		
		return reimbursement;
	}

	@Override
	public void addGradeFormat(GradeFormat gradeFormat) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertSql = "INSERT INTO grade_format (grades, passing_grade) VALUES (?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setString(1, gradeFormat.getGrades());
			insertStatement.setString(2, gradeFormat.getPassingGrade());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("exception adding grade format");
			e.printStackTrace();
		}
	}

	@Override
	public List<GradeFormat> getGradeFormats() {
		List<GradeFormat> gradeFormats = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM grade_format";
			Statement statement = connection.createStatement();
			ResultSet gradeFormatResults = statement.executeQuery(selectSql);
			gradeFormats = new ArrayList<GradeFormat>();
			while (gradeFormatResults.next()) {
				GradeFormat gradeFormat = new GradeFormat(
						gradeFormatResults.getInt("id"),
						gradeFormatResults.getString("grades"),
						gradeFormatResults.getString("passing_grade")
						);
				gradeFormats.add(gradeFormat);
			}
		} catch (SQLException e) {
			System.err.println("exception getting grade formats");
			e.printStackTrace();
		}
		
		return gradeFormats;
	}
	
	@Override
	public void addApproval(Employee adder, Reimbursement reimbursement, Status status, String reason) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String callSql = "CALL insert_then_pend_approval_if(?, ?, ?, ?)";
			CallableStatement callStatement = connection.prepareCall(callSql);
			callStatement.setInt(1, adder.getId());
			callStatement.setInt(2, reimbursement.getId());
			callStatement.setInt(3, status.getId());
			callStatement.setString(4, reason);
			callStatement.execute();
		} catch (SQLException e) {
			System.err.println("exception adding approval of employee " + adder.getId() + " to reimbursement " + reimbursement.getId() + " with status " + status);
			e.printStackTrace();
		}
	}

	@Override
	public Approval getSupervisorApproval(Reimbursement reimbursement) {
		Approval supervisorApproval = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM approvals " +
					//--get supervisor
					"INNER JOIN (SELECT supervisor.id supervisor_id, employee.id employee_id FROM employees supervisor INNER JOIN employees employee ON employee.supervisor_id = supervisor.id AND employee.id = ?) supervisor " +
					"ON approvals.approver_id = supervisor.supervisor_id AND reimbursement_id = ? " +
					//--make sure approval is this reimbursement's and this reimbursement is this employee's.
					"INNER JOIN (SELECT employee_id applier_id FROM reimbursements WHERE reimbursements.employee_id = ?) reimbursement " +
					"ON supervisor.employee_id = reimbursement.applier_id";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, reimbursement.getEmployeeId());
			selectStatement.setInt(2, reimbursement.getId());
			selectStatement.setInt(3, reimbursement.getEmployeeId());
			ResultSet approvalResult = selectStatement.executeQuery();
			if (approvalResult.next()) {
				supervisorApproval = new Approval(
						approvalResult.getInt("id"),
						approvalResult.getInt("approval_status_id"),
						approvalResult.getInt("reimbursement_id"),
						approvalResult.getInt("approver_id"),
						approvalResult.getString("reason"),
						approvalResult.getTimestamp("datetimecreated")
						);
			}
		} catch (SQLException e) {
			System.err.println("exception getting supervisor approval of reimbursement " + reimbursement.getId());
			e.printStackTrace();
		}
		
		return supervisorApproval;
	}

	@Override
	public Approval getHeadApproval(Reimbursement reimbursement) {
		Approval headApproval = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM approvals " +
					"INNER JOIN (SELECT head.id head_id, employee.id employee_id FROM employees head INNER JOIN employees employee ON employee.DEPARTMENT_ID = head.department_id AND head.role_id = ? AND employee.id = ?) head " +
					//--get head's approval of this reimbursement..
					"ON approvals.approver_id = head.head_id AND approvals.reimbursement_id = ? " +
					"INNER JOIN (SELECT employee_id applier_id FROM reimbursements WHERE reimbursements.employee_id = ?) reimbursement " +
					//--but make sure the reimbursement is this employee's!
					"ON head.employee_id = reimbursement.applier_id";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, Employee.Role.head.getId());
			selectStatement.setInt(2, reimbursement.getEmployeeId());
			selectStatement.setInt(3, reimbursement.getId());
			selectStatement.setInt(4, reimbursement.getEmployeeId());
			ResultSet approvalResult = selectStatement.executeQuery();
			if (approvalResult.next()) {
				headApproval = new Approval(
						approvalResult.getInt("id"),
						approvalResult.getInt("approval_status_id"),
						approvalResult.getInt("reimbursement_id"),
						approvalResult.getInt("approver_id"),
						approvalResult.getString("reason"),
						approvalResult.getTimestamp("datetimecreated")
						);
			}
		} catch (SQLException e) {
			System.err.println("exception getting head approval of reimbursement " + reimbursement.getId());
			e.printStackTrace();
		}
		
		return headApproval;
	}

	@Override
	public Approval getBencoApproval(Reimbursement reimbursement) {
		Approval bencoApproval = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM approvals " +
					"INNER JOIN (SELECT bencos.id benco_id FROM employees bencos WHERE bencos.department_id = ?) bencos " +
					"ON approvals.approver_id = benco_id AND reimbursement_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, Employee.Department.Benco.getId());
			selectStatement.setInt(2, reimbursement.getId());
			ResultSet approvalResult = selectStatement.executeQuery();
			if (approvalResult.next()) {
				bencoApproval = new Approval(
						approvalResult.getInt("id"),
						approvalResult.getInt("approval_status_id"),
						approvalResult.getInt("reimbursement_id"),
						approvalResult.getInt("approver_id"),
						approvalResult.getString("reason"),
						approvalResult.getTimestamp("datetimecreated")
						);
			}
		} catch (SQLException e) {
			System.err.println("exception getting benco approval of reimbursement " + reimbursement.getId());
			e.printStackTrace();
		}
		
		return bencoApproval;
	}

	@Override
	public void addGrade(Reimbursement reimbursement, Grade grade, ArrayList<ReimbursementAttachment> attachments) {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// insert grade
			String insertSql = "INSERT INTO reimbursement_grades (reimbursement_id, grade) VALUES (?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setInt(1, reimbursement.getId());
			insertStatement.setString(2, grade.getGrade());
			insertStatement.executeUpdate();
			
			// get id of just inserted grade, in order to attach it to reimbursement
			String selectSql = "SELECT MAX(id) id FROM reimbursement_grades";
			Statement selectStatement = connection.createStatement();
			ResultSet gradeResult = selectStatement.executeQuery(selectSql);
			gradeResult.next();
			int gradeId = gradeResult.getInt("id");
			
			String updateSql = "UPDATE reimbursements SET grade_id = ? WHERE id = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateSql);
			updateStatement.setInt(1, gradeId);
			updateStatement.setInt(2, reimbursement.getId());
			updateStatement.executeUpdate();
			
			// insert attachments if any
			if (attachments != null) {
				for (ReimbursementAttachment attachment : attachments) {
					insertSql = "INSERT INTO reimbursement_attachments (reimbursement_id, attachment_type_id, url) VALUES (?, ?, ?)";
					insertStatement = connection.prepareStatement(insertSql);
					insertStatement.setInt(1, reimbursement.getId());
					insertStatement.setInt(2, attachment.getAttachmentTypeId());
					insertStatement.setString(3, attachment.getUrl());
					insertStatement.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			System.err.println("exception adding grade to reimbursement " + reimbursement.getId());
			e.printStackTrace();
		}
		
	}

	@Override
	public String generateLink(ReimbursementAttachment attachment) {
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
		
		String bucketName = "revature-project01";
		
		java.util.Date timeAvailable = new java.util.Date();
		timeAvailable.setTime(timeAvailable.getTime() + (1000 * 60 * 5)); //5 minutes is the time the generated link will work!	
		
		GeneratePresignedUrlRequest url = new GeneratePresignedUrlRequest(bucketName, attachment.getUrl());
		url.setMethod(HttpMethod.GET);
		url.setExpiration(timeAvailable);
		
		URL link = s3Client.generatePresignedUrl(url);
		
		return link.toString();
	}

}
