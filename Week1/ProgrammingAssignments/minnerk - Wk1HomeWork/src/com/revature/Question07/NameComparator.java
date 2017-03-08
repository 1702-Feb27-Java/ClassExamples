package com.revature.Question07;

import java.util.Comparator;

import com.revature.Question07.Employee;


public class NameComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getName().compareTo(e2.getName()); //Returns the comparison
													 //of two employees by name
	}

}