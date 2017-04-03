package hobbs.project01.service;

import java.util.ArrayList;
import java.util.List;

import hobbs.project01.pojo.Approval;
import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.Grade;
import hobbs.project01.pojo.GradeFormat;
import hobbs.project01.pojo.Reimbursement;
import hobbs.project01.pojo.ReimbursementAttachment;

/**
 * 
 * @author Michael Hobbs
 *
 */
public interface ReimbursementService {

	/**
	 * Opens a new reimbursement request.
	 * 
	 * A reimbursement request has a grade format that determines how the event covered by the reimbursement will be graded and awarded.
	 * 
	 * @param reimbursement
	 * @param createGradeFormat true if the reimbursement has a grade format not currently in the system
	 */
	void createReimbursement(Reimbursement reimbursement, boolean createGradeFormat);
	
	/**
	 * Retrieves all reimbursements this employee is responsible for managing.
	 * 
	 * @param employee
	 * @return
	 */
	List<Reimbursement> getReimbursementsFor(Employee employee);
	
	/**
	 * Retrieves all reimbursements this employee has personally applied for.
	 * 
	 * @param employee
	 * @return
	 */
	List<Reimbursement> getReimbursementsOf(Employee employee);

	/**
	 * Retrieves a reimbursement identified by the given id.
	 * 
	 * @param id the reimbursement's id
	 * @return
	 */
	Reimbursement getReimbursement(Integer id);
	
	/**
	 * Retrieves all grade formats currently in the system.
	 * 
	 * @return
	 */
	List<GradeFormat> getGradeFormats();
	
	/**
	 * Adds an approval by an employee to the given reimbursement.
	 * 
	 * If adding this approval then also satisfies the 3-step approval process then the reimbursement's status changes to 'pending' automatically. 
	 * 
	 * @param approver
	 * @param reimbursement
	 * @param reason
	 */
	void approveReimbursement(Employee approver, Reimbursement reimbursement, String reason);
	
	/**
	 * Denies the given reimbursement.
	 * 
	 * The reimbursement's status changes to 'denied' automatically.
	 * 
	 * @param denier
	 * @param reimbursement
	 * @param reason
	 */
	void denyReimbursement(Employee denier, Reimbursement reimbursement, String reason);
	
	/**
	 * Retrieves the approval of a reimbursement given by the applicant's supervisor.
	 * 
	 * @param reimbursement
	 * @return the supervisor's approval, or null if it was not given
	 */
	Approval getSupervisorApproval(Reimbursement reimbursement);
	
	/**
	 * Retrieves the approval of a reimbursement given by the applicant's department's head.
	 * 
	 * @param reimbursement
	 * @return the department head's approval, or null if it was not given
	 */
	Approval getHeadApproval(Reimbursement reimbursement);
	
	/**
	 * Retrieves the approval of a reimbursement given by a benefits coordinator.
	 * 
	 * @param reimbursement
	 * @return the benco's approval, or null if it was not given
	 */
	Approval getBencoApproval(Reimbursement reimbursement);

	void addGrade(Reimbursement reimbursement, Grade grade, ArrayList<ReimbursementAttachment> attachments);

	String generateLink(ReimbursementAttachment attachment);
	
}
