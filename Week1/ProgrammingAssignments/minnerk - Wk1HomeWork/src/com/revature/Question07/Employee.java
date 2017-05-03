package com.revature.Question07;


public class Employee implements Comparable<Employee> {
	
	//Declaration of variables
	private String name;
	private String department;
	private int age;
	
	//Constructor no arguments
	public Employee(){
		
	}
	
	//Constructor with arguments
	public Employee(String name, String dept, int age){
		this.name = name;
		this.department = dept;
		this.age = age;
	}
	
	//All Setters and Getter for variables
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
	
	@Override  //String output
	public String toString() {
		return "Employee [Name: " + name + ", Department: " + department + ", Age: " + age + "]";
	}

	@Override  //Comparison of Employees ages
	public int compareTo(Employee e) {
		if(this.age < e.age){
			return -1;
		}
		if(this.age > e.age){
			return 1;
		}
		return 0;
	}

	
}
