package com.homework1.question7;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		//create empty employee list
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		//add employees to the list
		employeeList.add(new Employee("Ben", "CompSci", 23));
		employeeList.add(new Employee("John", "Bio", 24));
		//3 extra employees to test the sorting
/*		employeeList.add(new Employee("Mory", "Math", 25));
		employeeList.add(new Employee("Aaron", "Econ", 28));
		employeeList.add(new Employee("Bella", "GameDev", 23));*/
		
		//print unsorted list
		System.out.println("Unsorted: ");
		for(int i = 0; i < employeeList.size(); i++){
			System.out.println(employeeList.get(i).name + " " + employeeList.get(i).department 
							+ " " + employeeList.get(i).age);
		}
		System.out.println();
		
		//sort list by employee name and then print
		Collections.sort(employeeList, new SortByName());
		System.out.println("Sorted by name: ");
		for(int i = 0; i < employeeList.size(); i++){
			System.out.println(employeeList.get(i).name + " " + employeeList.get(i).department 
					+ " " + employeeList.get(i).age);
		}
		System.out.println();
		
		//sort employee list by department then print
		Collections.sort(employeeList, new SortByDepartment());
		System.out.println("Sorted by department: ");
		for(int i = 0; i < employeeList.size(); i++){
			System.out.println(employeeList.get(i).name + " " + employeeList.get(i).department 
					+ " " + employeeList.get(i).age);
		}
		System.out.println();
		
		//sort employee list based on age then print
		Collections.sort(employeeList, new SortByAge());
		System.out.println("Sorted by age: ");
		for(int i = 0; i < employeeList.size(); i++){
			System.out.println(employeeList.get(i).name + " " + employeeList.get(i).department 
					+ " " + employeeList.get(i).age);
		}
		System.out.println();
	}

}
