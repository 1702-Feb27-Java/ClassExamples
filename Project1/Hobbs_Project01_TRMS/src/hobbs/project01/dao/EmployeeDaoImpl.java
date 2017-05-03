package hobbs.project01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				Employee employee = new Employee(employeeResults.getInt("id"), employeeResults.getInt("role_id"), employeeResults.getInt("supervisor_id"), employeeResults.getInt("department_id"), employeeResults.getString("username"), employeeResults.getString("password2"), employeeResults.getString("email"), employeeResults.getString("first_name"), employeeResults.getString("last_name"));
				employees.add(employee);
			}
		}
		catch (Exception e) {
			System.err.println("exception getting employees");
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getEmployee(Integer id) {
		Employee employee = null;
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String selectSql = "SELECT * FROM EMPLOYEES WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setInt(1, id.intValue());
			ResultSet employeeResult = selectStatement.executeQuery();
			if (employeeResult.next()) {
				employee = new Employee(employeeResult.getInt("id"), employeeResult.getInt("role_id"), employeeResult.getInt("supervisor_id"), employeeResult.getInt("department_id"), employeeResult.getString("username"), employeeResult.getString("password2"), employeeResult.getString("email"), employeeResult.getString("first_name"), employeeResult.getString("last_name"));
			}
		}
		catch (Exception e) {
			System.err.println("exception getting employee that should have id " + id);
			e.printStackTrace();
		}
		
		return employee;
	}
	
	@Override
	public void updateAccount(Employee user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertSql = "UPDATE employees SET first_name = ?, last_name = ?, password2 = ? WHERE id = ?";
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			insertStatement.setString(1, user.getFirstName());
			insertStatement.setString(2, user.getLastName());
			insertStatement.setString(3, user.getPassword());
			insertStatement.setInt(4, user.getId());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("exception updating employee " + user.getId());
			e.printStackTrace();
		}
		
		
	}
	
}
