package com.revature.collections;

import java.util.Comparator;

import com.revature.serialization.Employee;


public class MyComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getName().toUpperCase().compareTo(e2.getName().toUpperCase());
	}

}
