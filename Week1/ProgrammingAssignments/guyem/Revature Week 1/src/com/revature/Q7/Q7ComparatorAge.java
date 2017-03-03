package com.revature.Q7;

import java.util.Comparator;

public class Q7ComparatorAge implements Comparator<Employee> {

	@Override // using the compare method to compare two objects
	public int compare(Employee e, Employee e2) {
		//ascending order
		return e.getAge() - e2.getAge();
	}

}
