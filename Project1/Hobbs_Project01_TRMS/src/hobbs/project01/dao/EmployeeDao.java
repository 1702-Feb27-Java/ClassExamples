package hobbs.project01.dao;

import java.util.List;

import hobbs.project01.pojo.Employee;

public interface EmployeeDao {

	public List<Employee> getEmployees();
	
	public Employee getEmployee(Integer id);
	
	void updateAccount(Employee user);
	
}
