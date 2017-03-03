package com.mory.question07;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

	public int compare(Employee o1, Employee o2) {
		// we compare two employees first by their last name,then first name,
		// department, and then age.
		if (o1.lastName.equals(o2.lastName)) {
			if (o1.firstName.equals(o2.firstName)) {
				if (o1.department.equals(o2.department)) {
					if (o1.age < o2.age) {
						return -1;
					} else if (o1.age > o2.age) {
						return 1;
					} else {
						return 0;
					}
				} else {
					return o1.department.compareTo(o2.department);
				}
			} else {
				return o1.firstName.compareTo(o2.firstName);
			}

		} else {
			return o1.lastName.compareTo(o2.lastName);
		}
	}
}
