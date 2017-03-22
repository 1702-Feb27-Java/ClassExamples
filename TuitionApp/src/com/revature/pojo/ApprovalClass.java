package com.revature.pojo;

public class ApprovalClass {
	
	private int approvalID, approvalLevel, approvalStatus, approverID;
	private String approvalMessage;
	
	public ApprovalClass(){
		
	}
	
	public ApprovalClass(int approvalID, int approvalLevel, int approvalStatus, int approverID,
			String approvalMessage) {
		super();
		this.approvalID = approvalID;
		this.approvalLevel = approvalLevel;
		this.approvalStatus = approvalStatus;
		this.approverID = approverID;
		this.approvalMessage = approvalMessage;
	}

	public int getApprovalID() {
		return approvalID;
	}

	public void setApprovalID(int approvalID) {
		this.approvalID = approvalID;
	}

	public int getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getApproverID() {
		return approverID;
	}

	public void setApproverID(int approverID) {
		this.approverID = approverID;
	}

	public String getApprovalMessage() {
		return approvalMessage;
	}

	public void setApprovalMessage(String approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	@Override
	public String toString() {
		return "ApprovalClass [approvalID=" + approvalID + ", approvalLevel=" + approvalLevel + ", approvalStatus="
				+ approvalStatus + ", approverID=" + approverID + ", approvalMessage=" + approvalMessage + "]";
	}
	
}
