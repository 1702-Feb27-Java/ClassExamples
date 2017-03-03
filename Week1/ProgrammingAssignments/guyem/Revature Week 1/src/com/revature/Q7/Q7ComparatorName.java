package com.revature.Q7;

import java.util.Comparator;

public class Q7ComparatorName implements Comparator<Employee> {
	@Override // using the compare method to compare two objects
	public int compare(Employee e, Employee e2) {
		String personA = e.getName();
		String personB = e2.getName();
		// ascending order
		return personA.compareTo(personB);
	}

}
