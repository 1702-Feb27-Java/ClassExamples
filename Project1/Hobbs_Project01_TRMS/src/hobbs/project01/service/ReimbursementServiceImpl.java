package hobbs.project01.service;

import java.util.ArrayList;
import java.util.List;

import hobbs.project01.dao.ReimbursementDaoImpl;
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
public class ReimbursementServiceImpl implements ReimbursementService {
	
	private static ReimbursementService reimbursementService;

	private ReimbursementServiceImpl() {
		super();
	}
	
	public static ReimbursementService getReimbursementService() {
		if (reimbursementService == null) {
			reimbursementService = new ReimbursementServiceImpl();
		}
		return reimbursementService;
	}
	
	@Override
	public void createReimbursement(Reimbursement reimbursement, boolean createGradeFormat) {
		ReimbursementDaoImpl.getReimbursementDao().addReimbursement(reimbursement);
		if (createGradeFormat) {
			ReimbursementDaoImpl.getReimbursementDao().addGradeFormat(reimbursement.getGradeFormat());
		}
	}
	
	@Override
	public List<Reimbursement> getReimbursementsOf(Employee employee) {
		return ReimbursementDaoImpl.getReimbursementDao().getReimbursementsOf(employee);
	}
	
	@Override
	public Reimbursement getReimbursement(Integer id) {
		return ReimbursementDaoImpl.getReimbursementDao().getReimbursement(id);
	}

	@Override
	public List<Reimbursement> getReimbursementsFor(Employee employee) {
		return ReimbursementDaoImpl.getReimbursementDao().getReimbursementsFor(employee);
	}
	
	@Override
	public List<GradeFormat> getGradeFormats() {
		return ReimbursementDaoImpl.getReimbursementDao().getGradeFormats();
	}

	@Override
	public void approveReimbursement(Employee approver, Reimbursement reimbursement, String reason) {
		ReimbursementDaoImpl.getReimbursementDao().addApproval(approver, reimbursement, Approval.Status.awarded, reason);
	}

	@Override
	public void denyReimbursement(Employee denier, Reimbursement reimbursement, String reason) {
		ReimbursementDaoImpl.getReimbursementDao().addApproval(denier, reimbursement, Approval.Status.denied, reason);
	}

	@Override
	public Approval getSupervisorApproval(Reimbursement reimbursement) {
		return ReimbursementDaoImpl.getReimbursementDao().getSupervisorApproval(reimbursement);
	}

	@Override
	public Approval getHeadApproval(Reimbursement reimbursement) {
		return ReimbursementDaoImpl.getReimbursementDao().getHeadApproval(reimbursement);
	}

	@Override
	public Approval getBencoApproval(Reimbursement reimbursement) {
		return ReimbursementDaoImpl.getReimbursementDao().getBencoApproval(reimbursement);
	}

	@Override
	public void addGrade(Reimbursement reimbursement, Grade grade, ArrayList<ReimbursementAttachment> attachments) {
		ReimbursementDaoImpl.getReimbursementDao().addGrade(reimbursement, grade, attachments);
	}
	
	@Override
	public String generateLink(ReimbursementAttachment attachment) {
		return ReimbursementDaoImpl.getReimbursementDao().generateLink(attachment);
	}


}
