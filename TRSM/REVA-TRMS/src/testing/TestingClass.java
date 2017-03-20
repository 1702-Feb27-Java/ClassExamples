package testing;

import java.util.ArrayList;

import dao.DAOObject;
import objects.Employee;

public class TestingClass {

	public static void main(String[] args) {
		DAOObject obj = new DAOObject();
		Employee emp = obj.getEmployee("Al");
		
		System.out.println(emp.toString());
		ArrayList<Employee> em = obj.getAllEmployees();
		System.out.print("\n\n");
		
		for(Employee e : em){
			System.out.println(e.toString());
		}
				

	}

}
