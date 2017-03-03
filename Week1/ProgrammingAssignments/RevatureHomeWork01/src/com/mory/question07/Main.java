package com.mory.question07;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		ArrayList<Employee> listEmp= new ArrayList<>();
		listEmp.add(new Employee("Mory","Keita", "Mathematics",23));
		listEmp.add(new Employee("Lol","la", "actuaril",33));
		listEmp.add(new Employee("Mohn","Doe", "Software Developper",43));
		
		
		Collections.sort(listEmp,new EmployeeComparator());
		for(Employee emp:listEmp){
			System.out.println(emp);
		}

	}

}
