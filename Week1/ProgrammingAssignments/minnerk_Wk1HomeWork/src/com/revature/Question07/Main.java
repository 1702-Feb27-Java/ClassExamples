package com.revature.Question07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		//ArrayList created to hold the employees
		ArrayList<Employee> al = new ArrayList<>();
		
		//Creation of Employee object and insertion into the array
		al.add(new Employee ("Keith", "HR", 41));
		al.add(new Employee ("Abbey", "Training", 31));
		
		System.out.println("Sorted Employees by Name: ");
		
		//Sorting the items in the array
		Collections.sort(al, new NameComparator());
		
		//Iterator creation
		Iterator<Employee> itr = al.iterator();
		
		//Loops to cycle through employees and pring them out
		while(itr.hasNext()){
			Employee e = (Employee)itr.next();
			System.out.println(e);
		}
		
		

	}

}
