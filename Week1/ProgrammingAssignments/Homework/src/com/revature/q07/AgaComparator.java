package com.revature.q07;

import java.util.Comparator;

public class AgaComparator implements Comparator<Employee> {

	
	public int compare(Employee o1, Employee o2) {
		// accesending order
		return o1.getAge() - o2.getAge();
	}

}
