package com.revature.pojo;

public class ReimbursementClass {
	
	private int reimburseID;
	private double projected;
	private double awarded;
	private String changeReason;
	
	public ReimbursementClass(){
		
	}
	
	public ReimbursementClass(int reimburseID, double projected, double awarded, String changeReason) {
		super();
		this.reimburseID = reimburseID;
		this.projected = projected;
		this.awarded = awarded;
		this.changeReason = changeReason;
	}

	public int getReimburseID() {
		return reimburseID;
	}

	public void setReimburseID(int reimburseID) {
		this.reimburseID = reimburseID;
	}

	public double getProjected() {
		return projected;
	}

	public void setProjected(double projected) {
		this.projected = projected;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@Override
	public String toString() {
		return "ReimbursementClass [reimburseID=" + reimburseID + ", projected=" + projected + ", changeReason="
				+ changeReason + "]";
	}

	public double getAwarded() {
		return awarded;
	}

	public void setAwarded(double awarded) {
		this.awarded = awarded;
	}

}
