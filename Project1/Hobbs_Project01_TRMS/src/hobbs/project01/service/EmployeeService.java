package hobbs.project01.service;

import hobbs.project01.pojo.Employee;

public interface EmployeeService {
	
	/**
	 * Logs a user into the system.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	Employee login(String username, String password);
	
	/**
	 * Retrieves all information about a user logged in (in cases of account changes).
	 * 
	 * @param user
	 */
	Employee refreshUser(Employee user);

	void updateAccount(Employee user);

}
