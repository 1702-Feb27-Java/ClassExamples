package com.revature.q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author Aaron Camm
 *
 */
public class SortEmployees {
	
	/**
	 * Creates a list of Employees, Sorts them, and prints out the list
	 * 
	 * @param args
	 */
	public static void main (String[] args){
		Employee john = new Employee("John", "Smith", "Testing", 23);
		Employee jane = new Employee("Jane", "Smith", "Testing", 21);
		Employee smith1 = new Employee("Agent", "Smith", "Matrix", 18);
		Employee smith2 = new Employee("Agent", "Smith", "Matrix", 14);
		Employee smith3 = new Employee("Agent", "Smith", "Matrix", 13);
		Employee mrAnderson = new Employee("Thomas", "Anderson", "Matrix", 18);
		Employee aaronCamm = new Employee("Aaron", "Camm", "Software Developer", 28);
		EmployeeComparator comparator = new EmployeeComparator();
		List<Employee> list = new ArrayList<Employee>();
		list.add(john);
		list.add(jane);
		list.add(smith1);
		list.add(smith2);
		list.add(smith3);
		list.add(mrAnderson);
		list.add(aaronCamm);
		System.out.println(list);
		Collections.sort(list, comparator );
		System.out.println(list);
	}
}
