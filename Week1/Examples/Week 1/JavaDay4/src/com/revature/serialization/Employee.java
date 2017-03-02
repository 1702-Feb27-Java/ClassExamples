package com.revature.serialization;

import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee>{
	private transient String name = "Bobbert";
	private transient int age = 93;
	private transient String ssn = "123-45-6789";
	
	public Employee(){
		
	}
	public Employee(String name, int age, String ssn){
		this.name = name;
		this.age = age;
		this.ssn = ssn;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", ssn=" + ssn + "]";
	}
	@Override
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
