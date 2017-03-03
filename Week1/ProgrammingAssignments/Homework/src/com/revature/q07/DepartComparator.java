package com.revature.q07;

import java.util.Comparator;

public class DepartComparator implements Comparator<Employee> {

	
	public int compare(Employee o1, Employee o2) {
		return o1.getDepartment().compareTo(o2.getDepartment());
	}

}
