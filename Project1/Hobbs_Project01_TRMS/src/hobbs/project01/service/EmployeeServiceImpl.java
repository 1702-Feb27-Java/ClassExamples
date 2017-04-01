package hobbs.project01.service;

import java.util.ArrayList;

import hobbs.project01.dao.EmployeeDaoImpl;
import hobbs.project01.pojo.Employee;
import hobbs.project01.service.EmployeeServiceImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	private static EmployeeService employeeService;
	
	private EmployeeServiceImpl() {
		
	}
	
	public static EmployeeService getEmployeeService() {
		if (employeeService == null) {
			employeeService = new EmployeeServiceImpl();
		}
		return employeeService;
	}
	
	/**
	 * 
	 * 
	 * @param username the employee's username
	 * @param password the employee's password
	 * @return the employee if login was successful, or null if not
	 */
	public Employee login(String username, String password) {
		ArrayList<Employee> employees = (ArrayList<Employee>) EmployeeDaoImpl.getEmployeeDao().getEmployees();
		
		boolean matched = false;
		if (employees != null) {
			for (Employee employee : employees) {
				if (employee.getUsername().compareTo(username) == 0) {
					if (employee.getPassword().compareTo(password) == 0) {
						return employee;
					}
				}
			}
		}
		
		return null;
	}

}
