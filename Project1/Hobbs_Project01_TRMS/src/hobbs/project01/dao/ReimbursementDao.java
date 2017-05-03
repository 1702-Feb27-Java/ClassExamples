package hobbs.project01.dao;

import java.util.ArrayList;
import java.util.List;

import hobbs.project01.pojo.Approval;
import hobbs.project01.pojo.Approval.Status;
import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.Grade;
import hobbs.project01.pojo.GradeFormat;
import hobbs.project01.pojo.Reimbursement;
import hobbs.project01.pojo.ReimbursementAttachment;

public interface ReimbursementDao {
	
	/**
	 * Creates a new reimbursement request.
	 * 
	 * @param reimbursement the reimbursement to persist to the database
	 */
	void addReimbursement(Reimbursement reimbursement);

	void addGradeFormat(GradeFormat gradeFormat);
	
	List<Reimbursement> getReimbursementsFor(Employee employee);
	
	List<Reimbursement> getReimbursementsOf(Employee employee);
	
	List<GradeFormat> getGradeFormats();
	
	Reimbursement getReimbursement(Integer id);

	void addApproval(Employee adder, Reimbursement reimbursement, Status denied, String reason);

	Approval getSupervisorApproval(Reimbursement reimbursement);

	Approval getHeadApproval(Reimbursement reimbursement);

	Approval getBencoApproval(Reimbursement reimbursement);

	void addGrade(Reimbursement reimbursement, Grade grade, ArrayList<ReimbursementAttachment> attachments);

	String generateLink(ReimbursementAttachment attachment);

}
