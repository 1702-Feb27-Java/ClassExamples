package com.revature.weekone.question7;

import java.util.ArrayList;
import java.util.List;

/**
 * Sorts employees.
 * 
 * It uses the Comparator interface in doing so.
 * 
 * @author Michael Hobbs
 *
 */
public class Question7 {

	/**
	 * Creates employees and sorts them by various criteria.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// create employees
		Employee one = new Employee("Michael Hobbs", "SDET", 25); //create employee one
		Employee two = new Employee("Lisa Applegate", "Support", 30); //create employee two
		Employee three = new Employee("Mason Grave", "IT", 25); //create employee three
		Employee four = new Employee("Dean Gray", "Support", 40); //create employee four
		
		// add employees to the list to be sorted
		List<Employee> employees = new ArrayList<>(); //store employees to be sorted
		employees.add(one); //employee one added to the list 
		employees.add(two); //employee two added to the list
		employees.add(three); //employee three added to the list
		employees.add(four); //employee four added to the list
		
		// sort employees by name
		System.out.println("\nSORT BY NAME:");
		System.out.println("LIST BEFORE SORT:");
		System.out.println(employees); //print out the employees in the list before sorting
		employees.sort(new NameComparator()); //sort the employees by name
		System.out.println("LIST AFTER SORT:");
		System.out.println(employees); //print out the employees in the list after sorting
		
		// sort employees by department
		System.out.println("\nSORT BY DEPARTMENT:");
		System.out.println("LIST BEFORE SORT:");
		System.out.println(employees); //print out the employees in the list before sorting
		employees.sort(new DepartmentComparator()); //sort the employees by department
		System.out.println("LIST AFTER SORT:");
		System.out.println(employees); //print out the employees in the list after sorting
		
		// sort employees by age
		System.out.println("\nSORT BY AGE:");
		System.out.println("LIST BEFORE SORT:");
		System.out.println(employees); //print out the employees in the list before sorting
		employees.sort(new AgeComparator()); //sort the employees by age
		System.out.println("LIST AFTER SORT:");
		System.out.println(employees); //print out the employees in the list after sorting

	}

}
