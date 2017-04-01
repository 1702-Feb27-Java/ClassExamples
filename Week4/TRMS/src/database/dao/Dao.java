package database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import database.ConnectionUtil;
import database.model.Attachment;
import database.model.Dept;
import database.model.EventType;
import database.model.Feedback;
import database.model.GradeFormat;
import database.model.Reimbursement;
import database.model.Role;
import database.model.Status;
import database.model.User;
import database.service.Service;

public class Dao implements IDao {

	private static Dao instance;
	public static Dao getInstance(){
		if (instance == null){
			instance = new Dao();
			
		}
		return instance;
	}
	
	Connection connection;
	
	private Dao(){
		
		try {
			connection = ConnectionUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void log(User username, int logLevel, String message){
		System.out.println(message);
	}
	

	
	@Override
	public void insertOrUpdateUser(User user) {
		String insertSql = "INSERT INTO users(firstname, lastname, username,password, role_id, dept_id) values (?, ?, ?, ?, ?, ?)";
		String updateSql = "update user set firstname = ?, lastname = ?, username = ?, password = ?, role_id = ?, dept_id = ? where user_id = ?";
		String insertLogMessage = String.format("Inserting new User with username %s into database", user.getUsername());
		String updateLogMessage = String.format("Updaing User ID %d, with username %s", user.getUserId(), user.getUsername());
		String sql;
		boolean isInsert;
		if (user.getUserId() == null){
			//insert command
			sql = insertSql;
			isInsert = true;
		} else {
			sql = updateSql;
			isInsert = false;
		}
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getDeptId());
			if (user.getUserId() != null){
				statement.setInt(7, user.getUserId());
			}
			
			boolean successed = statement.executeUpdate() > 0 ? true : false;
			
			
			this.log(null, successed ? Service.INFO : Service.ERROR, isInsert ? insertLogMessage : updateLogMessage);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertOrUpdateAttachment(Attachment attachment) {
	
		
		String insertSql = "insert into attachments(attachment_type, reimbursement_id, attachment_loc) values (?, ?, ?)";
		String updateSql = "update attachments set attachment_type = ?, reimbursement_id = ?, attachment_loc = ? WHERE attachment_id = ?";
		String insertLogMessage = String.format("Inserting new Attachment for reimbursement  %d into database", attachment.getReimbursement());
		String updateLogMessage = String.format("Updaing Attachment id %d for reimbursement %d", attachment.getAttachmentId(), attachment.getReimbursement());
		String sql;
		boolean isInsert;
		if (attachment.getAttachmentId() == null){
			//insert command
			sql = insertSql;
			isInsert = true;
		} else {
			sql = updateSql;
			isInsert = false;
		}
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, attachment.getAttachmentType());
			statement.setInt(2, attachment.getReimbursement());
			statement.setString(3, attachment.getAttachmentLoc());
			if (attachment.getAttachmentId() != null){
				statement.setInt(4, attachment.getAttachmentId());
			}
			
			boolean successed = statement.executeUpdate() > 0 ? true : false;
			
			
			this.log(null, successed ? Service.INFO : Service.ERROR, isInsert ? insertLogMessage : updateLogMessage);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertOrUpdateFeedback(Feedback feedback) {
		String insertSql = "insert into feedback(from_user_id, to_user_id, reimbursement_id, feedback) values (?, ?, ?, ?)";
		String updateSql = "update feedback set from_user_id = ?, to_user_id = ?, reimbursement_id = ?, feedback = ? where feedback_id = ?";
		String insertLogMessage = String.format("Inserting new Feedback from %d to %d", feedback.getFromUserId(), feedback.getToUserId());
		String updateLogMessage = String.format("Updaing Feedback with id %d", feedback.getFeedbackId());
		String sql;
		boolean isInsert;
		if (feedback.getFeedbackId() == null){
			//insert command
			sql = insertSql;
			isInsert = true;
		} else {
			sql = updateSql;
			isInsert = false;
		}
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, feedback.getFromUserId());
			statement.setInt(2, feedback.getToUserId());;
			statement.setInt(3, feedback.getReimbursementId());
			statement.setString(4, feedback.getFeedback());
			if (feedback.getFeedbackId() != null){
				statement.setInt(5, feedback.getFeedbackId());
			}
			
			boolean successed = statement.executeUpdate() > 0 ? true : false;
			
			
			this.log(null, successed ? Service.INFO : Service.ERROR, isInsert ? insertLogMessage : updateLogMessage);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertOrUpdateReimbursement(Reimbursement reimbursement) {
		String insertSql = "insert into reimbursement("
				+ "user_id, " // 1
				+ "description, " //2
				+ "location, " // 3
				+ "cost_of_event, " //4
				+ "grading_format_id, " // 5
				+ "Event_Type_id, " //6
				+ "event_date_start, " //7
				+ "event_date_stop, " // 8
				+ "date_of_last_status_change, " //9
				+ "status_ID, " //10
				+ "reimbursement ,"
				+ "custom_grading_format " //12
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSql = "update reimbursement set user_id = ?,  description = ?, "
				+ "location = ?, cost_of_event = ?, grading_format_id = ?, "
				+ "event_type_id = ?, event_date_start = ?, event_date_stop = ?, date_of_last_status_change = ?, "
				+ "status_id = ?, reimbursement = ?, custom_grading_format = ?,  grade = ?, passing_grade = ?, "
				+ "rejection_feedback_id = ? where reimbursement_id = ?";
		String insertLogMessage = String.format("Inserting new Reimbursement for user %d", reimbursement.getUserId());
		String updateLogMessage = String.format("Updaing reimbursement %d", reimbursement.getReimbursementId());
		String sql;
		boolean isInsert;
		if (reimbursement.getReimbursementId() == null){
			//insert command
			sql = insertSql;
			isInsert = true;
		} else {
			sql = updateSql;
			isInsert = false;
		}
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, reimbursement.getUserId());
			statement.setString(2, reimbursement.getDescription());
			statement.setString(3, reimbursement.getLocation());
			statement.setDouble(4, reimbursement.getCostOfEvent());
			statement.setInt(5, reimbursement.getGradeFormatId());
			statement.setInt(6, reimbursement.getEventTypeId());
			//statement.setInt(8, reimbursement.getWorkTimeMissed());
			statement.setDate(7, reimbursement.getStartDate());
			statement.setDate(8, reimbursement.getEndDate());
			statement.setDate(9, reimbursement.getDateOfLastStatusChange());
			statement.setInt(10, reimbursement.getStatusId());
			statement.setDouble(11, reimbursement.getReimbursement());
			statement.setString(12, reimbursement.getCustomGradingFormat());
			if (reimbursement.getReimbursementId() != null){
				
				statement.setString(13, reimbursement.getGrade());
				statement.setString(14, reimbursement.getPassingGrade());
				if(reimbursement.getRejectionFeedbackId() == null || reimbursement.getRejectionFeedbackId().equals(0)){
					statement.setNull(15, java.sql.Types.NUMERIC);
				} else {
					statement.setInt(15, reimbursement.getRejectionFeedbackId());
				}
			
				statement.setInt(16, reimbursement.getReimbursementId() );
			}
			
			boolean successed = statement.executeUpdate() > 0 ? true : false;
			
			
			this.log(null, successed ? Service.INFO : Service.ERROR, isInsert ? insertLogMessage : updateLogMessage);
			
		} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} 

	@Override
	public User getUserById(int id) {
		User user = null;
		String sql = "Select * from users where user_id = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if(set.next()){
				user = loadUserFromResultSet(set);
				this.log(null, Service.INFO, String.format("retrieving User %d", id));
			} else {
				this.log(null,  Service.WARN, String.format("User %d does not exist", id));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
		
	}

	private User loadUserFromResultSet(ResultSet set) throws SQLException {
		User user;
		user = new User(
				set.getInt("user_id"),
				set.getString("firstname"),
				set.getString("lastname"),
				set.getString("username"),
				set.getString("password"),
				set.getInt("role_id"),
				set.getInt("dept_id"),
				set.getInt("supervisor_id")
				);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		String sql = "Select * from users where username = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, username);
			ResultSet set = statement.executeQuery();
			if(set.next()){
				user = loadUserFromResultSet(set);
				this.log(null, Service.INFO, String.format("retrieving User with username %s", username));
			} else {
				this.log(null,  Service.WARN, String.format("User with username %s does not exist", username));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		String sql = "SELECT * from reimbursement where reimbursement_id = ?";
		Reimbursement reimbursement = null;
		try(Connection connection = ConnectionUtil.getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				reimbursement = loadReimbursementFormResultSet(rs);
				
				this.log(null, Service.INFO, String.format("Retrieving Reimbursement %d", id));
			} else {
				this.log(null, Service.WARN, String.format("Unable to find Reimbursement %d", id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbursement;
	}

	private Reimbursement loadReimbursementFormResultSet(ResultSet rs) throws SQLException {
		Reimbursement reimbursement;
		reimbursement = new Reimbursement(  
				rs.getInt("reimbursement_id"),
				rs.getInt("user_id"),
				rs.getDate("date_of_request"),
				rs.getString("description"),
				rs.getString("location"),
				rs.getDouble("cost_of_event"),
				rs.getInt("grading_format_id"),
				rs.getInt("event_type_id"),
				rs.getDate("event_date_start"),
				rs.getDate("event_date_stop"),
				rs.getDate("date_of_last_status_change"),
				rs.getInt("status_id"),
				rs.getString("grade"),
				rs.getString("passing_grade"),
				rs.getDouble("reimbursement"), 
				rs.getString("custom_grading_format"),
				rs.getInt("rejection_feedback_id")
				);
		return reimbursement;
	}

	@Override
	public Feedback getFeedbackById(int id) {
		
		String sql = "SELECT * from feedback where feedback_id = ?";
		Feedback feedback = null;
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				feedback = loadFeedbackFromResultSet(rs);
				this.log(null, Service.INFO, String.format("Getting Feedback message %d", id));
			} else {
				this.log(null, Service.ERROR, String.format("Unable to feedback message %d", id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return feedback;
	}

	private Feedback loadFeedbackFromResultSet(ResultSet rs) throws SQLException {
		Feedback feedback;
		feedback = new Feedback(
				rs.getInt("feedback_id"),
				rs.getInt("from_user_id"),
				rs.getInt("to_user_id"),
				rs.getInt("reimbursement_id"),
				rs.getDate("date_of_message"),
				rs.getString("feedback")
				);
		return feedback;
	}

	@Override
	public Attachment getAttachmentById(int id) {
		Attachment attachment = null;
		String sql = "Select * from attachments where attachment_id = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if(set.next()){
				attachment = loadAttachmentFromResultSet(set);
				this.log(null, Service.INFO, String.format("retrieved attachment %d", id));
			} else {
				this.log(null, Service.WARN, String.format("Could not find attachment", id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attachment;
	}

	private Attachment loadAttachmentFromResultSet(ResultSet set) throws SQLException {
		Attachment attachment;
		attachment = new Attachment(
				
				set.getInt("attachment_id"),
				set.getString("attachment_type"),
				set.getInt("reimbursement_id"),
				set.getDate("time_uploaded"),
				set.getString("attachment_loc")
				);
		return attachment;
	}

	@Override
	public List<User> getUsersBySupervisor(User user) {
		String sql = "SELECT * FROM users WHERE supervisor_id = ?";
		List<User> list = new LinkedList<>();
		try( 
				PreparedStatement statement =  connection.prepareStatement(sql)){
			statement.setInt(1, user.getUserId());
			ResultSet set = statement.executeQuery();
			while(set.next()){
				User loadedUser = this.loadUserFromResultSet(set);
				list.add(loadedUser);
			}
			this.log(null, Service.INFO, String.format("Returned %d users that was supervised from user %d", list.size(), user.getUserId()));
			
		} catch (SQLException e) {
			this.log(null, Service.WARN, String.format("Unable to load users with supervisor user %d", user.getUserId()));
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<User> getUsersByDept(Dept dept) {
		String sql = "SELECT * FROM users WHERE dept_id = ?";
		List<User> list = new LinkedList<>();
		try(
				PreparedStatement statement =  connection.prepareStatement(sql)){
			statement.setInt(1, dept.getDeptId());
			ResultSet set = statement.executeQuery();
			while(set.next()){
				User loadedUser = this.loadUserFromResultSet(set);
				list.add(loadedUser);
			}
			this.log(null, Service.INFO, String.format("Returned %d users that was supervised from dept %d", list.size(), dept.getDeptId()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Reimbursement> getReimbursementsByUser(User user) {
		String sql = "SELECT * FROM reimbursement WHERE user_id = ?";
		List<Reimbursement> list = new LinkedList<>();
		try(
				PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setInt(1, user.getUserId());
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					Reimbursement r = this.loadReimbursementFormResultSet(rs);
					list.add(r);
				}
				this.log(null, Service.INFO, String.format("Retrieved %d reimbursements from user %d", list.size(), user.getUserId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Feedback> getFeedbackByReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM feedback WHERE reimbursement_id = ?";
		List<Feedback> list = new LinkedList<>();
		try(
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, reimbursement.getReimbursementId());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Feedback fb = this.loadFeedbackFromResultSet(rs);
				list.add(fb);
			}
				this.log(null, Service.INFO, String.format("Retrieved %d Feedbacks by reimbursement %d", list.size(), reimbursement.getReimbursementId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Attachment> getAttachmentsByReimbursement(Reimbursement reimbursement) {
		List<Attachment> list = new LinkedList<>();
		String sql = "SELECT * from attachments where reimbursement_id = ?";
		try(
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, reimbursement.getReimbursementId());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Attachment attachment = this.loadAttachmentFromResultSet(rs);
				list.add(attachment);
			}
			this.log(null, Service.INFO, String.format("loaded %d attachments from reimbursement %d", list.size(), reimbursement.getReimbursementId()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<Status> getStatuses() {
		List<Status> list = new LinkedList<>();
		String sql = "Select * from status";
		try(
				Statement statement = connection.createStatement()){
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				Status s = new Status(
						set.getInt("status_id"),
						set.getString("status_name")
						);
				list.add(s);
			}
			this.log(null, Service.INFO, String.format("Loaded %d Statuses", list.size()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Role> getRoles() {
		List<Role> list = new LinkedList<>();
		String sql = "Select * from role";
		try(
				Statement statement = connection.createStatement()){
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				Role r = new Role(
						set.getInt("role_id"),
						set.getString("status_name")
						);
				list.add(r);
			}
			this.log(null, Service.INFO, String.format("Loaded %d Roles", list.size()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<GradeFormat> getGradeFormats() {
		List<GradeFormat> list = new LinkedList<>();
		String sql = "SELECT * FROM grading_format";
		try(
				Statement statement = connection.createStatement()){
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				GradeFormat g = new GradeFormat(
						set.getInt("format_id"),
						set.getString("format_name"),
						set.getInt("requires_presentation") > 0 ? true : false
						);
				
				list.add(g);
						
			}
			this.log(null, Service.INFO, String.format("Loaded %d Grade Formats", list.size()));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<EventType> getEventTypes() {
		List<EventType> list = new LinkedList<>();
		String sql = "SELECT * FROM event_type";
		try(
				Statement statement = connection.createStatement()){
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				EventType e = new EventType(
						set.getInt("event_id"),
						set.getString("type_name"),
						set.getDouble("coverage")
						);
				list.add(e);
			}
			this.log(null, Service.INFO, String.format("Loaded %d EventTypes", list.size()));
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Dept> getDepts() {
		List<Dept> list = new LinkedList<>();
		String sql = "SELECT * FROM dept";
		try(
				Statement statement = connection.createStatement()){
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Dept d = new Dept(
						rs.getInt("dept_id"),
						rs.getString("dept_name")
						);
				list.add(d);
			}
			this.log(null, Service.INFO, String.format("Loaded %d depts", list.size()));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	private void deleteRow(int id, String tableName, String idColumnName){
		String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, idColumnName);
		try(
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			if (statement.executeUpdate() > 0){
				this.log(null, Service.INFO, String.format("Deleted %s id %d", tableName, id));
			} else {
				this.log(null, Service.WARN, String.format("Unable to delete %s with id %d", tableName, id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void deleteUser(User user) {
		this.deleteRow(user.getUserId(), "users", "user_id");
		
	}

	@Override
	public void deleteAttachment(Attachment attachment) {
		this.deleteRow(attachment.getAttachmentId(), "attachments", "attachment_id");
		
	}

	@Override
	public void deleteFeedback(Feedback feedback) {
		this.deleteRow(feedback.getFeedbackId(), "feedback", "feedback_id");
	}

	@Override
	public void deleteReimbursement(Reimbursement reimbursement) {
		this.deleteRow(reimbursement.getReimbursementId(), "reimbursement", "reimbursement_id");		
	}

	public List<Reimbursement>  getAllPendingReimbursements() {
		String sql = "select * from reimbursement where status_id <= 4";

		List<Reimbursement> reimbursements = new LinkedList<Reimbursement>();
		try(
				
				Statement statement = connection.createStatement();){
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				reimbursements.add(this.loadReimbursementFormResultSet(rs));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reimbursements;
		
	}
	

	

}
