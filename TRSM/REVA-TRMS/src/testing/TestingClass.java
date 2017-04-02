package testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import dao.DAOObject;
import objects.Employee;
import objects.Reimburse;
import service.Service;

public class TestingClass {

	public static void main(String[] args) {
		DAOObject obj = new DAOObject();
//		Reimburse re = obj.getReimbursement(181);
//		System.out.println(re.toString());
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
		
//		Employee test = obj.getEmployee(41);
//		
//		Service serv = new Service();
//		Employee boss = serv.getDepartmentHead(test.getReportsto());
//		System.out.println(serv.getAllReim().get(1).getNumDay());
		String input = "10/10/2017";
		
		try {
		    DateTimeFormatter formatter =
		                      DateTimeFormatter.ofPattern("MM/dd/yyyy");
		    LocalDate date = LocalDate.parse(input, formatter);
		    System.out.printf("%s%n", date);
		    LocalDate today = LocalDate.now();
//			LocalDate birthday = LocalDate.of(2017, Month.DECEMBER, 2);
			long p2 = ChronoUnit.DAYS.between(today, date);
			System.out.println(p2);
		}
		catch (DateTimeParseException exc) {
		    System.out.printf("%s is not parsable!%n", input);
		    throw exc;      // Rethrow the exception.
		}
		// 'date' has been successfully parsed
		
//		try {
////		    DateTimeFormatter formatter =
////		                      DateTimeFormatter.ofPattern("mm/dd/yyyy");
////		    LocalDate date = LocalDate.parse(input, formatter);
////		    System.out.printf("%s%n", date);
//		}
//		catch (DateTimeParseException exc) {
//		    System.out.printf("%s is not parsable!%n", input);
//		    throw exc;      // Rethrow the exception.
//		}
		
		
				

	}

}
