package com.revature.weekone.question7;

import java.util.Comparator;

/**
 * Compares two employees by department.
 * 
 * @author Michael Hobbs
 *
 */
public class DepartmentComparator implements Comparator<Employee> {

	/**
	 * Compares two employees by department.
	 * 
	 * @param e1 the first employee
	 * @param e2 the second employee
	 * @return less than 0 if e1's department's name is sorted after e2's, greater than 0 if e1's is sorted after, 0 if they are equal 
	 */
	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getDepartment().compareTo(e2.getDepartment());
	}

}
