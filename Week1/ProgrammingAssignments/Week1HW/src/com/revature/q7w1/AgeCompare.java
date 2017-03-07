// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 7 - SORT TWO EMPLOYEES BASED ON THEIR NAME, DEPARTMENT, AND AGE USING THE COMPARATOR INTERFACE.

package com.revature.q7w1;

import java.util.Comparator;

//we need to create classes for the comparator interface

public class AgeCompare implements Comparator<Employee>{
	@Override
	 public int compare(Employee e1, Employee e2) { // comparing 2 employee objects
		
		// comparing when we get their ages
		if (e1.getAge() < e2.getAge()){
			return -1;
		}
		if (e1.getAge() > e2.getAge()){
			return 1;
		}
		return 0;
	}
}
