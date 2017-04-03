package hobbs.project01.pojo;

/**
 * Attachments of a reimbursement.
 * 
 * @author Michael Hobbs
 *
 */
public class ReimbursementAttachment {
	
	/**
	 * The types of reimbursement attachments.
	 * 
	 * @author Michael Hobbs
	 *
	 */
	public enum AttachmentType {
		event(1, "Event-related"), approvalSupervisor(2, "Supervisor pre-approval"), approvalHead(3, "Department Head pre-approval"), grade(21, "Grade-related");
		
		private int id;
		/**
		 * Pretty name for the attachment type, for front-end printing and displaying purposes.
		 */
		private String name;
		
		private AttachmentType(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return this.id;
		}
		
		public static AttachmentType getAttachmentType(int id)
        {
            AttachmentType[] attachmentTypes = AttachmentType.values();
            for(int i = 0; i < attachmentTypes.length; i++)
            {
                if(attachmentTypes[i].id  == id) {
                	return attachmentTypes[i];
                }
            }
            return null;
        }
		
		public static AttachmentType getAttachmentType(String attachmentType) {
			AttachmentType[] attachmentTypes = AttachmentType.values();
			for(int i = 0; i < attachmentTypes.length; i++) {
				if (attachmentTypes[i].toString().equals(attachmentType)) {
					return attachmentTypes[i];
				}
			}
			return null;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
	}

	private Integer id, reimbursementId, attachmentTypeId;
	private String url;
	
	public ReimbursementAttachment() {
		super();
	}
	
	public ReimbursementAttachment(Integer id, Integer reimbursementId, Integer attachmentTypeId, String url) {
		super();
		this.id = id;
		this.reimbursementId = reimbursementId;
		this.attachmentTypeId = attachmentTypeId;
		this.url = url;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(Integer reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public Integer getAttachmentTypeId() {
		return attachmentTypeId;
	}
	public void setAttachmentTypeId(Integer attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ReimbursementAttachment [id=" + id + ", reimbursementId=" + reimbursementId + ", attachmentTypeId="
				+ attachmentTypeId + ", url=" + url + "]";
	}
	
}
