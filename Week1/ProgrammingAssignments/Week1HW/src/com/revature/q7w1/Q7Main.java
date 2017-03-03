// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 7 - SORT TWO EMPLOYEES BASED ON THEIR NAME, DEPARTMENT, AND AGE USING THE COMPARATOR INTERFACE.

package com.revature.q7w1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q7Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Employee> em = new ArrayList<>();  // make a list for employee objects
		
		// two example employees
		em.add(new Employee("Nancy", "Human Resources", 44));
		em.add(new Employee("David", "Internal Affairs", 35));
		System.out.println(em);
		
		// creates namecompare object so we can sort by name
		NameCompare nc = new NameCompare();
		Collections.sort(em, nc);
		
		System.out.println(em);
		
		// creates departmentcompare object so we can sort by department
		DeptCompare dc = new DeptCompare();
		Collections.sort(em, dc);
		
		System.out.println(em);
		
		// creates agecompare object so we can sort by age
		AgeCompare ac = new AgeCompare();
		Collections.sort(em,ac);
		
		System.out.println(em);

		
	}

}
