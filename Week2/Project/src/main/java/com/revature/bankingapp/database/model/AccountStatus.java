package com.revature.bankingapp.database.model;

public class AccountStatus {
	public int getStatusId() {
		return statusId;
	}
	public String getStatus() {
		return status;
	}
	public AccountStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	@Override
	public String toString() {
		return "AccountStatus [statusId=" + statusId + ", status=" + status + "]";
	}
	final private int statusId;
	final private String status;

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountStatus other = (AccountStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	
	
}
