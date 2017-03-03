package com.revature.Q7;

public class Employee {
	String name;
	String department;
	int age;

	// default Employee constructor
	public Employee() {

	}

	public Employee(String name, String department, int age) {
		// Employee fields
		name = this.name;
		department = this.department;
		age = this.age;
	}

	// get the name
	public String getName() {
		return name;
	}

	// set the name
	public void setName(String name) {
		this.name = name;
	}

	// get the department
	public String getDepartment() {
		return department;
	}

	// set the department
	public void setDepartment(String department) {
		this.department = department;
	}

	// get the age
	public int getAge() {
		return age;
	}

	// set the age
	public void setAge(int age) {
		this.age = age;
	}

}
