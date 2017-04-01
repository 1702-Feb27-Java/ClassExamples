package hobbs.project01.pojo;

import java.sql.Timestamp;

public class Approval {
	
	public enum ApprovalStatus {
		pending(1), awarded(2), denied(3);
		
		private int id;
		
		private ApprovalStatus(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
		
		public static ApprovalStatus getApprovalStatus(int id)
        {
            ApprovalStatus[] approvalStatuses = ApprovalStatus.values();
            for(int i = 0; i < approvalStatuses.length; i++)
            {
                if(approvalStatuses[i].id  == id) {
                	return approvalStatuses[i];
                }
            }
            return null;
        }
		
		public static ApprovalStatus getApprovalStatus(String approvalStatus) {
			ApprovalStatus[] approvalStatuses = ApprovalStatus.values();
			for(int i = 0; i < approvalStatuses.length; i++) {
				if (approvalStatuses[i].toString().equals(approvalStatus)) {
					return approvalStatuses[i];
				}
			}
			return null;
		}
		
	}
	
	private Integer approvalStatusId, reimbursementId, approverId;
	private String reason;
	private Timestamp datetimeCreated;
	
	public Approval() {
		
	}
	
	public Approval(Integer approvalStatusId, Integer reimbursementId, Integer approverId, String reason,
			Timestamp datetimeCreated) {
		super();
		this.approvalStatusId = approvalStatusId;
		this.reimbursementId = reimbursementId;
		this.approverId = approverId;
		this.reason = reason;
		this.datetimeCreated = datetimeCreated;
	}
	
	public Integer getApprovalStatusId() {
		return approvalStatusId;
	}
	public void setApprovalStatusId(Integer approvalStatusId) {
		this.approvalStatusId = approvalStatusId;
	}
	public Integer getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(Integer reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public Integer getApproverId() {
		return approverId;
	}
	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getDatetimeCreated() {
		return datetimeCreated;
	}
	public void setDatetimeCreated(Timestamp datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	@Override
	public String toString() {
		return "Approval [approvalStatusId=" + approvalStatusId + ", reimbursementId=" + reimbursementId
				+ ", approverId=" + approverId + ", reason=" + reason + ", datetimeCreated=" + datetimeCreated + "]";
	}

	
	
}
