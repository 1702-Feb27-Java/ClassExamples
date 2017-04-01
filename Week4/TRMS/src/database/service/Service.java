package database.service;

import java.sql.Connection;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import database.ConnectionUtil;
import database.dao.Dao;
import database.model.Attachment;
import database.model.Dept;
import database.model.EventType;
import database.model.Feedback;
import database.model.GradeFormat;
import database.model.Reimbursement;
import database.model.Role;
import database.model.Status;
import database.model.User;

public class Service {
	
	//Log Levels
	public static final int FATAL = 1;
	public static final int ERROR = 2;
	public static final int WARN = 3;
	public static final int INFO = 4;
	public static final int DEBUG = 5;
	
	//Event Types
	public static final int UCourse = 1;
	public static final int Seminar = 2;
	public static final int CPC = 3;
	public static final int Certif = 4;
	public static final int TechnicalTraining = 5;
	public static final int OtherEventType = 6;
	
	//Roles
	public static final int Employee = 1;
	public static final int DeptHead = 2;
	public static final int BenCo = 3;
	public static final int Supervisor = 4;
	public static final int Manager = 5;
	
	//Statuses
	public static final int AwaitSuperApp = 1;
	public static final int AwaitDepHeadApp = 2;
	public static final int AwaitBenCoApp = 3;
	public static final int AwaitComplete = 4;
	public static final int Approved = 5;
	public static final int Rejected = 6;
	public static final int Canceled = 7;
	
	//Grading_Format
	public static final int LetterGrade = 1;
	public static final int GradePoint = 2;
	public static final int PassFail = 3;
	public static final int OtherGradingFormat = 4;
	public static final int None = 5;
	
	//BenCo
	public static final int BenCoDept = 5;
	
	
	private static Service instance;
	private Dao dao;
	private Map<Integer, Dept> deptMap;
	private Map<Integer, EventType> eventTypes;
	private Map<Integer, GradeFormat> gradeFormats;
	private Map<Integer, Role> roles;
	private Map<Integer, Status> statuses;
	
	private Service(){
		dao = Dao.getInstance();
	}
	
	public static Service getInstance(){
		if (instance == null){
			instance = new Service();
		}
		return instance;
	}
	
	public User addNewUser(String fname, String lname, String username, String password, int roleId, int deptId, Integer supervisorId){
		User user = new User (null, fname, lname, username, password, roleId, deptId, supervisorId);
		Dao.getInstance().insertOrUpdateUser(user);
		return Dao.getInstance().getUserByUsername(username);
		
		
	}
	
	public User loginUser(String name, String password){
		User loggedInUser = null;
		User user = Dao.getInstance().getUserByUsername(name);
		if (user != null && password.equals(user.getPassword())){
			loggedInUser = user;
			
			
		}
		return user;
	}

	
	public List<Reimbursement> getReimbursementsFromUser(User loggedInUser){
		
		return Dao.getInstance().getReimbursementsByUser(loggedInUser);
		
	}
	
	
	
	public void log(int logLevel, String message){
	Logger logging = Logger.getRootLogger();
		switch (logLevel) {
		case FATAL:
			logging.fatal(message);
			break;
		case ERROR:
			logging.error(message);
			break;
		case WARN:
			logging.warn(message);
			break;
		case INFO:
			logging.info(message);
			break;
		case DEBUG:
			logging.debug(message);
			break;
		default:
			logging.debug(message);
		}
	}
	
	
	
	public  void log(int logLevel, String message, Throwable t){
	Logger logging = Logger.getRootLogger();
		switch (logLevel) {
		case FATAL:
			logging.fatal(message, t);
			break;
		case ERROR:
			logging.error(message, t);
			break;
		case WARN:
			logging.warn(message, t);
			break;
		case INFO:
			logging.info(message, t);
			break;
		case DEBUG:
			logging.debug(message, t);
			break;
		default:
			logging.debug(message, t);
		}
	}
	
	public void logNoDatabaseConnection(int logLevel, String message, Throwable t){
	Logger logging = Logger.getRootLogger();
		switch (logLevel) {
		case FATAL:
			logging.fatal(message, t);
			break;
		case ERROR:
			logging.error(message, t);
			break;
		case WARN:
			logging.warn(message, t);
			break;
		case INFO:
			logging.info(message, t);
			break;
		case DEBUG:
			logging.debug(message, t);
			break;
		default:
			logging.debug(message, t);
		}
	}
	
	public Map<Integer, Dept> getDepts(){
		if(deptMap == null){
			List<Dept> depts = dao.getDepts();
			this.deptMap = new HashMap<Integer, Dept>();
			for(Dept dept: depts){
				this.deptMap.put(dept.getDeptId(), dept);
			}
		}
		
		return deptMap;
	}
	
	public Map<Integer, EventType> getEventType(){
		if(eventTypes == null){
			eventTypes = new HashMap<Integer, EventType>();
			List<EventType> list = dao.getEventTypes();
			for(EventType eventType: list){
				eventTypes.put(eventType.getEventId(), eventType);
			}
		}
		
		return eventTypes;
	}
	
	public Map<Integer, GradeFormat> getGradeFormats(){
		if(gradeFormats == null){
			gradeFormats = new HashMap<Integer, GradeFormat>();
			List<GradeFormat> list = dao.getGradeFormats();
			for(GradeFormat gradeFormat: list){
				gradeFormats.put(gradeFormat.getFormatId(), gradeFormat);
			}
		}
		
		return gradeFormats;
	}
	
	public Map<Integer, Role> getRoles(){
		if(this.roles == null){
			this.roles = new HashMap<Integer, Role>();
			List<Role> list = dao.getRoles();
			for(Role role: list){
				this.roles.put(role.getRoleId(), role);
			}
		}
		return roles;
	}
	
	public Map<Integer, Status> getStatuses(){
		if(this.statuses == null){
			this.statuses = new HashMap<Integer, Status>();
			List<Status> list = dao.getStatuses();
			for(Status status: list){
				this.statuses.put(status.getStatusId(), status);
			}
		}
		return statuses;
	}

	public void InsertOrUpdateReimbursement(Reimbursement reimbursement) {
		Dao.getInstance().insertOrUpdateReimbursement(reimbursement);
		
	}

	public Reimbursement getReimbursementFromUserWithId(User user, Integer id) {
		List<Reimbursement> list = Dao.getInstance().getReimbursementsByUser(user);
		Reimbursement reimbursement = Dao.getInstance().getReimbursementById(id);
		if(list.contains(reimbursement)){
			return reimbursement;
		} else {
			return null;
		}
	}
	
	public List<Feedback> getFeedbackFromReimbursementForUser(Reimbursement reimbursement, User user){
		List<Feedback> list = Dao.getInstance().getFeedbackByReimbursement(reimbursement);
		List<Feedback> result = new LinkedList<Feedback>();
		
		// returns only the feedback related to user for reimbursement 
		list.forEach(p-> {
			if(p.getFromUserId().equals(user.getUserId())|| p.getToUserId().equals(user.getUserId())){
				result.add(p);
			}
		});
		
		//Sort it, in case there is a rare case of it being read unsorted.
		list.sort(new Comparator<Feedback>(){
			@Override
			public int compare(Feedback arg0, Feedback arg1) {
				
				return arg0.getDateOfMessage().compareTo(arg1.getDateOfMessage());
			}
			
		});
		
		return result;
	}

	public User getUserFromId(Integer userId) {
		return Dao.getInstance().getUserById(userId);
	}

	public void addFeedBackToDatabase(Feedback feedback) {
		Dao.getInstance().insertOrUpdateFeedback(feedback);
	}

	public void InsertOrUpdateAttachment(Attachment attachment) {
		Dao.getInstance().insertOrUpdateAttachment(attachment);
	}

	public Attachment getAttachmentById(Integer id) {
		return Dao.getInstance().getAttachmentById(id);
	}

	public Reimbursement getReimbursementById(Integer id) {
		return Dao.getInstance().getReimbursementById(id);
	}

	public User getDeptHead(Dept dept) {
		
		List<User> users = Dao.getInstance().getUsersByDept(dept);
		
		//puting it to a list because lambda exp can't do assignments to variables
		
		List<User> deptHead = new LinkedList<>();
		
		users.forEach(p -> {
			if (p.getRoleId() == Service.DeptHead){
				deptHead.add(p);
			}
		});
		
		return deptHead.get(0);
		
		
	}

	public int getStartingStatusId(User user) {
		User deptHead = this.getDeptHead(user.getDept());
		if (user.getSupervisorId().equals(deptHead.getUserId())){
			return Service.AwaitDepHeadApp;
		} else {
			return Service.AwaitSuperApp;
		}
	}
	
	public double getMaxReimbursementLeft(User user){
		List<Reimbursement> list = Dao.getInstance().getReimbursementsByUser(user);
		
		LocalDate dateNow = LocalDate.now();
		Double reimbursementsAmount = 0.0;
		
		for(Reimbursement r : list){
			//checks add the reimbursements totals together from the past year if pending or accepted.
			
			Period d = Period.between(r.getDateOfRequest().toLocalDate(), dateNow);
			if(d.getYears() < 1 && 
					(r.getStatusId() != Service.Rejected && r.getStatusId() != Service.Canceled)  ){
				reimbursementsAmount += r.getReimbursement();
			}
		}
		
		return reimbursementsAmount <= 1000 ? 1000 - reimbursementsAmount : 0;
		
	}

	public List<Reimbursement> getAllPendingReimbursements() {
		return dao.getAllPendingReimbursements();
	}

	public List<User> getUsersBySupervisor(User user) {
		return dao.getUsersBySupervisor(user);
	}

	public List<User> getUsersByDept(Dept dept) {
		
		return dao.getUsersByDept(dept);
	}

	public List<User> getUsersToMessage(User loggedInUser, Integer userIdRequestingReimbursement) {
		List<User> usersToMessage = new LinkedList<User>();
		List<Integer> usersIdsToMessage = new LinkedList<Integer>();
		User userRequestingReimbursement = Service.getInstance().getUserFromId(userIdRequestingReimbursement);
		
		Integer supervisorId = userRequestingReimbursement.getSupervisorId();
		//add in supervisor if applicable
		if(supervisorId != null && supervisorId != 0){
			User supervisor = Service.getInstance().getUserFromId(supervisorId);
			usersToMessage.add(supervisor);
		}
		//add in Dept head
		if (userRequestingReimbursement.getDeptId() != null && userRequestingReimbursement.getDeptId() != 0){
			User deptHead = Service.getInstance().getDeptHead(userRequestingReimbursement.getDept());
			usersToMessage.add(deptHead);
		}
		//add in BenCo dept
		usersToMessage.addAll(Service.getInstance().getUsersByDept(Service.getInstance().getDepts().get(Service.BenCoDept)));
		
		//add User request Reimbursement
		usersToMessage.add(userRequestingReimbursement);
		
		//remove self from list
		while(usersToMessage.contains(loggedInUser)){
			usersToMessage.remove(loggedInUser);
		}
		
		Collections.sort(usersToMessage);
		
		return usersToMessage;
		
		
	}

	public List<Attachment> getAttachmentByReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAttachmentsByReimbursement(reimbursement);
	}

	public Feedback getFeedbackById(Integer id) {
		// TODO Auto-generated method stub
		return Dao.getInstance().getFeedbackById(id);
	}
	

	
	
	
	
	

}
