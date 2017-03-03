package com.revature.Q7;

import java.util.Comparator;

public class Q7ComparatorDepartment implements Comparator<Employee> {
	@Override // using the compare method to compare two objects
	public int compare(Employee e, Employee e2) {
		String personA = e.getDepartment();
		String personB = e2.getDepartment();
		// ascending order
		return personA.compareTo(personB);
	}
}
