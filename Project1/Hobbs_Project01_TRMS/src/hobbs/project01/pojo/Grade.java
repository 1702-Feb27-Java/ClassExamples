package hobbs.project01.pojo;

/**
 * 
 * @author Michael Hobbs
 *
 */
public class Grade {
	
	private Integer id, reimbursementId;
	private String grade;
	
	public Grade() {
		super();
	}
	
	public Grade(Integer id, Integer reimbursementId, String grade) {
		super();
		this.id = id;
		this.reimbursementId = reimbursementId;
		this.grade = grade;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", reimbursementId=" + reimbursementId + ", grade=" + grade + "]";
	}

}
