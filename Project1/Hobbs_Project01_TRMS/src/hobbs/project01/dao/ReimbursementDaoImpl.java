package hobbs.project01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

}
