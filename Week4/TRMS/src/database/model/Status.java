package database.model;

public class Status {
	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusName=" + statusName + "]";
	}
	public Status(Integer statusId, String statusName) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
	}
	Integer statusId;
	String statusName;
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		return true;
	}

}
