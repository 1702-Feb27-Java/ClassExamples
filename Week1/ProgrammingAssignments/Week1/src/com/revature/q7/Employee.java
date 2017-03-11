package com.revature.q7;

/**
 * 
 * @author Aaron Camm
 *
 */
public class Employee{
	final String firstName;
	final String lastName;
	final String department;
	final int age;
	
	public Employee(String firstName, String lastName, String department, int age){
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", department=" + department + ", age="
				+ age + "]";
	}
	
	
}
