package hobbs.project01.service;

import java.util.List;

import hobbs.project01.dao.ReimbursementDaoImpl;
import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.GradeFormat;
import hobbs.project01.pojo.Reimbursement;

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
	public List<GradeFormat> getGradeFormats() {
		return ReimbursementDaoImpl.getReimbursementDao().getGradeFormats();
	}
	
	@Override
	public List<Reimbursement> getReimbursementsOf(Employee employee) {
		return ReimbursementDaoImpl.getReimbursementDao().getReimbursementsOf(employee);
	}
	
	@Override
	public Reimbursement getReimbursement(Integer id) {
		return ReimbursementDaoImpl.getReimbursementDao().getReimbursement(id);
	}

}
