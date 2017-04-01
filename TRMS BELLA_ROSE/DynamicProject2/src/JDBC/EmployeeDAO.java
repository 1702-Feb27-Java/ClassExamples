package JDBC;
import java.util.LinkedList;
import java.util.List;
import Classes.Employee;

public interface EmployeeDAO
{
	//////   CREATE
	public void insertEmployees(Employee per);
	
	/////    READ
	public LinkedList<Employee> getAllEmployees();
	public LinkedList<Employee> getEmployeesByRole(int role);
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByUsername(String username);
	
	//////    UPDATE
	public void saveEmployee(Employee per);
	
	//////    DELETE
	public void deleteEmployeeById(int id);
	
	
}