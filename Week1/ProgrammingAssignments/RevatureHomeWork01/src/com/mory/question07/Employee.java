package com.mory.question07;

public class Employee {
	String lastName;
	String firstName;
	String department;
	int age;

	public Employee(String lN, String fN, String dp, int age) {
		this.lastName = lN;
		this.firstName = fN;
		this.department = dp;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [lastName=" + lastName + ", firstName=" + firstName + ", department=" + department + ", age="
				+ age + "]";
	}

}
