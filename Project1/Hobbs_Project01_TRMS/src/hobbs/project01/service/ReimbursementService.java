package hobbs.project01.service;

import java.util.List;

import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.GradeFormat;
import hobbs.project01.pojo.Reimbursement;

public interface ReimbursementService {

	void createReimbursement(Reimbursement reimbursement, boolean createGradeFormat);
	
	List<GradeFormat> getGradeFormats();
	
	List<Reimbursement> getReimbursementsOf(Employee employee);

	Reimbursement getReimbursement(Integer id);
	
}
