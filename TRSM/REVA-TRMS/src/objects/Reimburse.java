package objects;

public class Reimburse {	
		private int reim_id;
		private int employee_id;
		private String event_date;
		private String eventLength;
		private String location;
		private String description;
		private int cost;
		private int grade;
		private String justification;
		private int numDay;
		private int courseID;
		public Reimburse(){
			this.employee_id = 0;
			this.event_date = null;
			this.eventLength = null;
			this.location = null;
			this.description = null;
			this.cost = 0;
			this.grade = 0;
			this.justification = null;
			this.numDay = 0;
			this.courseID = 0;
			
			
		}
		public Reimburse(int reim_id, int employee_id, String event_date, String eventLength, String location,
				String description, int cost, int grade, String justification, int numDay, int courseID) {
			super();
			this.reim_id = reim_id;
			this.employee_id = employee_id;
			this.event_date = event_date;
			this.eventLength = eventLength;
			this.location = location;
			this.description = description;
			this.cost = cost;
			this.grade = grade;
			this.justification = justification;
			this.numDay = numDay;
			this.courseID = courseID;
		}
		@Override
		public String toString() {
			return "Reimburse [reim_id=" + reim_id + ", employee_id=" + employee_id + ", event_date=" + event_date
					+ ", eventLength=" + eventLength + ", location=" + location + ", description=" + description
					+ ", cost=" + cost + ", grade=" + grade + ", justification=" + justification + ", numDay=" + numDay
					+ ", courseID=" + courseID + "]";
		}
		public int getReim_id() {
			return reim_id;
		}
		public void setReim_id(int reim_id) {
			this.reim_id = reim_id;
		}
		public int getEmployee_id() {
			return employee_id;
		}
		public void setEmployee_id(int employee_id) {
			this.employee_id = employee_id;
		}
		public String getEvent_date() {
			return event_date;
		}
		public void setEvent_date(String event_date) {
			this.event_date = event_date;
		}
		public String getEventLength() {
			return eventLength;
		}
		public void setEventLength(String eventLength) {
			this.eventLength = eventLength;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getCost() {
			return cost;
		}
		public void setCost(int cost) {
			this.cost = cost;
		}
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
		public String getJustification() {
			return justification;
		}
		public void setJustification(String justification) {
			this.justification = justification;
		}
		public int getNumDay() {
			return numDay;
		}
		public void setNumDay(int numDay) {
			this.numDay = numDay;
		}
		public int getCourseID() {
			return courseID;
		}
		public void setCourseID(int courseID) {
			this.courseID = courseID;
		}
		
		
		
}
