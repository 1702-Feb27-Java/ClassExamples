package com.revature.q07;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This program will demonstrate the sorting of an Employee's Name
 * age, and the department that they belong to you.
 * @author Nick
 *
 */

public class EmployeeCompare {

	public static void main(String[] args) {
		EmployCompare();
		
	}

	static void EmployCompare() {
		
		Employee e1 = new Employee("Nick", "Development", 27);
		Employee e2 = new Employee("John", "HR", 10);
		
		ArrayList<Employee> this_L = new ArrayList<Employee>();
		
		this_L.add(e1);
		this_L.add(e2);

		//first param is list of type of employee, next argument is comparator
		
		
		System.out.println("Employees sorted by Name:");
		Collections.sort(this_L, new NameComparator());
		for ( int i = 0; i < this_L.size(); i++)
			System.out.println(this_L.get(i).getName());
		
		System.out.println();
		
		System.out.println("Employees sorted by age:");
		Collections.sort(this_L, new AgaComparator());
		for ( int i = 0; i < this_L.size(); i++)
			System.out.println(this_L.get(i).getAge());
		
		System.out.println();
		
		System.out.println("Employees sorted by Department:");
		Collections.sort(this_L, new DepartComparator());
		for ( int i = 0; i < this_L.size(); i++)
			System.out.println(this_L.get(i).getDepartment());
		
		
	}

}
