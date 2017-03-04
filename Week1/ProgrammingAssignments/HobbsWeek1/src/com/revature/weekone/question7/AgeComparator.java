package com.revature.weekone.question7;

import java.util.Comparator;

/**
 * Compares two employees by age.
 * 
 * Younger employees are sorted before older employees.
 * 
 * @author Michael Hobbs
 *
 */
public class AgeComparator implements Comparator<Employee> {

	/**
	 * Compares two employees by age.
	 * 
	 * @param e1 the first employee
	 * @param e2 the second employee
	 * @return less than 0 if e1 is younger than e2, greater than 0 if e1 is older than e2, 0 if e1 and e2 are the same age
	 * 
	 */
	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getAge() - e2.getAge();
	}

}
