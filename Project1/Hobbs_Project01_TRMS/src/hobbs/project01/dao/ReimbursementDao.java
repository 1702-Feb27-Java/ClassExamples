package hobbs.project01.dao;

import java.util.List;

import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.GradeFormat;
import hobbs.project01.pojo.Reimbursement;

public interface ReimbursementDao {
	
	/**
	 * Creates a new reimbursement request.
	 * 
	 * @param reimbursement the reimbursement to persist to the database
	 */
	void addReimbursement(Reimbursement reimbursement);

	void addGradeFormat(GradeFormat gradeFormat);
	
	List<Reimbursement> getReimbursementsOf(Employee employee);
	
	List<GradeFormat> getGradeFormats();
	
	Reimbursement getReimbursement(Integer id);
}
