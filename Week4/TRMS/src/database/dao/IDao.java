package database.dao;

import java.util.List;

import database.model.Attachment;
import database.model.Dept;
import database.model.EventType;
import database.model.Feedback;
import database.model.GradeFormat;
import database.model.Reimbursement;
import database.model.Role;
import database.model.Status;
import database.model.User;

public interface IDao {
	
	
	public void insertOrUpdateUser(User user);
	public void insertOrUpdateAttachment(Attachment attachment);
	public void insertOrUpdateFeedback(Feedback feedback);
	public void insertOrUpdateReimbursement(Reimbursement reumbursement);
	
	public void deleteUser(User user);
	public void deleteAttachment(Attachment attachment);
	public void deleteFeedback(Feedback feedback);
	public void deleteReimbursement(Reimbursement reimbursement);
	
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public Reimbursement getReimbursementById(int id);
	public Feedback getFeedbackById(int id);
	public Attachment getAttachmentById(int id);
	
	public List<User> getUsersBySupervisor(User user);
	public List<User> getUsersByDept(Dept dept);
	public List<Reimbursement> getReimbursementsByUser(User user);
	public List<Feedback> getFeedbackByReimbursement(Reimbursement reumbursement);
	public List<Attachment> getAttachmentsByReimbursement(Reimbursement reumbursement);
	
	
	public List<Status> getStatuses();
	public List<Role> getRoles();
	public List<GradeFormat> getGradeFormats();
	public List<EventType> getEventTypes();
	public List<Dept> getDepts();
}
