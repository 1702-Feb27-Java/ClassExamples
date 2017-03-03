// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 7 - SORT TWO EMPLOYEES BASED ON THEIR NAME, DEPARTMENT, AND AGE USING THE COMPARATOR INTERFACE.

package com.revature.q7w1;

public class Employee {  // we need to create an employee class
	
	// private members
	
	private String name;
	private String department;
	private int age;
	
	// we make our own constructor for this class
	
	public Employee (String name, String department, int age)
	{
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
	// getters and setters for our private members
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {  // need to override the toString method so we can print
		return "Employee [name = " + name + ", department = " + department + ", age = " + age + "]";
	}

}
