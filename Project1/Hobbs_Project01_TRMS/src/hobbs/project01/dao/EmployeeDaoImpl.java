package hobbs.project01.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hobbs.project01.pojo.Employee;
import hobbs.project01.service.EmployeeServiceImpl;
import hobbs.project01.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static EmployeeDao employeeDao;
	
	private EmployeeDaoImpl() {
		
	}
	
	public static EmployeeDao getEmployeeDao() {
		if (employeeDao == null) {
			employeeDao = new EmployeeDaoImpl();
		}
		return employeeDao;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM EMPLOYEES";
			Statement selectStatement = connection.createStatement();
			ResultSet employeeResults = selectStatement.executeQuery(selectSql);
			employees = new ArrayList<Employee>(); //TODO: cache results and return this instead in order to minimize db access. 
			while (employeeResults.next()) {
				Employee employee = new Employee(employeeResults.getInt("id"), employeeResults.getInt("role_id"), employeeResults.getInt("supervisor_id"), employeeResults.getInt("department_id"), employeeResults.getString("username"), employeeResults.getString("password"), employeeResults.getString("email"), employeeResults.getString("first_name"), employeeResults.getString("last_name"));
				employees.add(employee);
			}
		}
		catch (Exception e) {
			System.err.println("exception getting employees");
			e.printStackTrace();
		}
		return employees;
	}
	
}
