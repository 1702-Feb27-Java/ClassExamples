package testing;

import java.util.ArrayList;

import dao.DAOObject;
import objects.Employee;
import objects.Reimburse;
import service.Service;

public class TestingClass {

	public static void main(String[] args) {
		DAOObject obj = new DAOObject();
//		Employee emp = obj.getEmployee("Al");
//		
//		System.out.println(emp.toString());
//		ArrayList<Employee> em = obj.getAllEmployees();
//		System.out.print("\n\n");
//		
//		for(Employee e : em){
//			System.out.println(e.toString());
//		}
//		Reimburse test = obj.getReimbursement(43);
//		System.out.println(test.toString());
		
		Employee test = obj.getEmployee(41);
		
		Service serv = new Service();
		Employee boss = serv.getDepartmentHead(test.getReportsto());
		System.out.println(boss.toString());
				

	}

}
