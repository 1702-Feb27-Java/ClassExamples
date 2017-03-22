package com.revature.trms;

public class Priority {

	private int priorityId = 0;
	private String priority;
	
	public Priority() {
		
	}

	
	public Priority(int priorityId) {
		this.priorityId = priorityId;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}	
}
