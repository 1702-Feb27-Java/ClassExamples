package com.revature.pojo;

import java.util.Date;

/**
  * Reimbursement Value Object.
  * This class is value object representing database table REIMBS
  * This class is intended to be used together with associated Dao object.
  */
public class Reimbursement {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int reimbId;
    private int employeeId;
    private String location;
    private double cost;
    private int eventTitleId;
    private Date eventDate;
    private String eventDesc;
    private String workJust;
	private int gradeId;
    private int urgencyId;
    private int appLevel;



    /** 
     * Two Constructors.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Reimbursement () {

    }

    public Reimbursement (int reimbIdIn) {

          this.reimbId = reimbIdIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getReimbId() {
          return this.reimbId;
    }
    public void setReimbId(int reimbIdIn) {
          this.reimbId = reimbIdIn;
    }
    
    public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
    
    public String getLocation() {
          return this.location;
    }
    public void setLocation(String locationIn) {
          this.location = locationIn;
    }

    public double getCost() {
          return this.cost;
    }
    public void setCost(double costIn) {
          this.cost = costIn;
    }

    public int getEventTitleId() {
          return this.eventTitleId;
    }
    public void setEventTitleId(int eventTitleIdIn) {
          this.eventTitleId = eventTitleIdIn;
    }

    public Date getEventDate() {
          return this.eventDate;
    }
    public void setEventDate(Date eventDateIn) {
          this.eventDate = eventDateIn;
    }

    public String getEventDesc() {
          return this.eventDesc;
    }
    public void setEventDesc(String eventDescIn) {
          this.eventDesc = eventDescIn;
    }

    public String getWorkJust() {
          return this.workJust;
    }
    public void setWorkJust(String workJustIn) {
          this.workJust = workJustIn;
    }

    public int getGradeId() {
          return this.gradeId;
    }
    public void setGradeId(int gradeIdIn) {
          this.gradeId = gradeIdIn;
    }

    public int getUrgencyId() {
          return this.urgencyId;
    }
    public void setUrgencyId(int statusIdIn) {
          this.urgencyId = statusIdIn;
    }

	public int getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(int appLevel) {
		this.appLevel = appLevel;
	}



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int reimbIdIn,
          String locationIn,
          double costIn,
          int eventTitleIdIn,
          Date eventDateIn,
          String eventDescIn,
          String workJustIn,
          int gradeIdIn,
          int urgencyIdIn,
          int employeeIdIn,
          int appLevelIn) {
          this.reimbId = reimbIdIn;
          this.location = locationIn;
          this.cost = costIn;
          this.eventTitleId = eventTitleIdIn;
          this.eventDate = eventDateIn;
          this.eventDesc = eventDescIn;
          this.workJust = workJustIn;
          this.gradeId = gradeIdIn;
          this.urgencyId = urgencyIdIn;
          this.employeeId = employeeIdIn;
          this.appLevel = appLevelIn;
    }


    /** 
     * hasEqual-method will compare two Reimbursement instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqual(Reimbursement valueObject) {

          if (valueObject.getReimbId() != this.reimbId)
        	  return(false);
          return true;
    }


    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in textlog.
     */
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass Reimbursement, mapping to table REIMBS\n");
        out.append("Persistent attributes: \n"); 
        out.append("reimbId = " + this.reimbId + "\n");
        out.append("employeeId = " + this.employeeId + "\n");
        out.append("location = " + this.location + "\n"); 
        out.append("cost = " + this.cost + "\n"); 
        out.append("eventTitleId = " + this.eventTitleId + "\n"); 
        out.append("eventDate = " + this.eventDate + "\n"); 
        out.append("eventDesc = " + this.eventDesc + "\n"); 
        out.append("workJust = " + this.workJust + "\n"); 
        out.append("gradeId = " + this.gradeId + "\n"); 
        out.append("urgencyId = " + this.urgencyId + "\n");
        out.append("appLevel = " + this.appLevel + "\n");
        return out.toString();
    }
}