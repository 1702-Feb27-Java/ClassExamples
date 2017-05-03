package hobbs.project01.pojo;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import oracle.sql.DATE;


/**
 * 
 * @author Michael Hobbs
 *
 */
public class Reimbursement {
	
	/**
	 * The statuses a reimbursement will go through.
	 * 
	 * @author Michael Hobbs
	 *
	 */
	public enum Status {
		
		open(1), pending(2), cancelled(3), awarded(4), denied(5);
		private int id;
		
		private Status(int id) {
			this.id = id;
		}
		
		public static int getDefaultStatus() {
			return open.getId();
		}
		
		public int getId() {
			return this.id;
		}
		
		public static Status getStatus(int id)
        {
            Status[] statuses = Status.values();
            for(int i = 0; i < statuses.length; i++)
            {
                if(statuses[i].id  == id) {
                	return statuses[i];
                }
            }
            return null;
        }
		
		public static Status getStatus(String status) {
			Status[] statuses = Status.values();
			for(int i = 0; i < statuses.length; i++) {
				if (statuses[i].toString().equals(status)) {
					return statuses[i];
				}
			}
			return null;
		}
	}
	
	/**
	 * The types of events covered by a reimbursement. 
	 * 
	 * Depending on the type of event, the percentage that is covered of the cost of the event to the employee varies.
	 * 
	 * @author Michael Hobbs
	 *
	 */
	public enum EventType {
		university(1, 80, "University Course"), 
		seminar(2, 60, "Seminar"), 
		certPrep(3, 75, "Certification Preparation Course"), 
		cert(4, 100, "Certification"), 
		techTraining(5, 90, "Technical Training"), 
		other(6, 30, "Other");
		
		private int id;
		/**
		 * The percentage of the cost to the employee that is covered.
		 */
		private int coveragePercentage;
		/**
		 * Pretty name for the event type, for front-end printing and displaying purposes.
		 */
		private String name;
		
		private EventType(int id, int coveragePercentage, String name) {
			this.id = id;
			this.coveragePercentage = coveragePercentage;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public int getCoveragePercentage() {
			return coveragePercentage;
		}
		
		public static EventType getEventType(int id)
        {
            EventType[] eventTypes = EventType.values();
            for(int i = 0; i < eventTypes.length; i++)
            {
                if(eventTypes[i].id  == id) {
                	return eventTypes[i];
                }
            }
            return null;
        }
		
		public static EventType getEventTypes(String eventType) {
			EventType[] eventTypes = EventType.values();
			for(int i = 0; i < eventTypes.length; i++) {
				if (eventTypes[i].toString().equals(eventType)) {
					return eventTypes[i];
				}
			}
			return null;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
	}
	
	private Integer id, employeeId, statusId, eventTypeId, gradeFormatId, gradeId;
	private Timestamp startDatetime, endDatetime, datetimeCreated;
	private String description, justification, location, worktimeToBeMissed;
	private double cost;
	
	private List<ReimbursementAttachment> attachments;
	
	private GradeFormat gradeFormat;
	
	private Grade grade;
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(Integer id, Integer employeeId, Integer statusId, Integer eventTypeId, Integer gradeFormatId,
			Integer gradeId, Timestamp startDatetime, Timestamp endDatetime, Timestamp datetimeCreated, String description, String justification,
			String location, String worktimeToBeMissed, double cost) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.statusId = statusId;
		this.eventTypeId = eventTypeId;
		this.gradeFormatId = gradeFormatId;
		this.gradeId = gradeId;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.datetimeCreated = datetimeCreated;
		this.description = description;
		this.justification = justification;
		this.location = location;
		this.worktimeToBeMissed = worktimeToBeMissed;
		this.cost = cost;
	}
	
	public boolean isUrgent() {
		
		if (new Timestamp(new Date().getTime()).toInstant().until(this.startDatetime.toInstant(), ChronoUnit.DAYS) < 14) {
			return true;
		}
		return false;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	public Integer getGradeFormatId() {
		return gradeFormatId;
	}
	public void setGradeFormatId(Integer gradeFormatId) {
		this.gradeFormatId = gradeFormatId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Timestamp getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Timestamp startDatetime) {
		this.startDatetime = startDatetime;
	}
	public Timestamp getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}
	public Timestamp getDatetimeCreated() {
		return datetimeCreated;
	}
	public void setDatetimeCreated(Timestamp datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWorktimeToBeMissed() {
		return worktimeToBeMissed;
	}
	public void setWorktimeToBeMissed(String worktimeToBeMissed) {
		this.worktimeToBeMissed = worktimeToBeMissed;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<ReimbursementAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ReimbursementAttachment> attachments) {
		this.attachments = attachments;
	}

	public GradeFormat getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(GradeFormat gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employeeId=" + employeeId + ", statusId=" + statusId + ", eventTypeId="
				+ eventTypeId + ", gradeFormatId=" + gradeFormatId + ", gradeId=" + gradeId + ", startDatetime="
				+ startDatetime + ", endDatetime=" + endDatetime + ", description=" + description + ", justification="
				+ justification + ", location=" + location + ", worktimeToBeMissed=" + worktimeToBeMissed + ", cost="
				+ cost + "]";
	}
	
}
