package com.revature.q7;

import java.util.Comparator;

/**
 * 
 * @author Aaron Camm
 *
 */
public class EmployeeComparator implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		// compares two employees, by their last name, firstname, department, then age.
		// if two lastname are equal, check first name for difference, then the same for department and age.
		if (o1.lastName.equals(o2.lastName)){
			if (o1.firstName.equals(o2.firstName)){
				if(o1.department.equals(o2.department)){
					if(o1.age < o2.age){
						return -1;
					} else if (o1.age > o2.age){
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
