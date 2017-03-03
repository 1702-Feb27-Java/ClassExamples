package com.revature.Q7;

import java.util.Arrays;

public class Q7{

	public static void main(String[] args) {
		Employee person[] = new Employee[3];
		
		//create a person object
		person[0] = new Employee();
		person[0].setName("Bob Walkman");
	    person[0].setDepartment("Tech");
		person[0].setAge(43);
		
		person[1] = new Employee();
		person[1].setName("Sam Cobbs");
		person[1].setDepartment("Tech");
		person[1].setAge(32);
		
		person[2] = new Employee();
		person[2].setName("Mark Daniels");		
	    person[2].setDepartment("Marketing");
		person[2].setAge(22);
		
		//before sorted
		for(int i=0; i<person.length;i++){
			System.out.println("Name: "+person[i].getName()+" Department: "+person[i].getDepartment()+" Age: "+person[i].getAge());
			
		}
		System.out.println();
		System.out.println("Sorted by name");
		//sorted by object name
		Arrays.sort(person, new Q7ComparatorName());
		for(int i=0; i<person.length;i++){
			System.out.println("Name: "+person[i].getName()+" Department: "+person[i].getDepartment()+" Age: "+person[i].getAge());
			
		}
		System.out.println();
		System.out.println("Sorted by department");
		//sorted by object department
		Arrays.sort(person, new Q7ComparatorDepartment());
		for(int i=0; i<person.length;i++){
			System.out.println("Name: "+person[i].getName()+" Department: "+person[i].getDepartment()+" Age: "+person[i].getAge());
			
		}
		System.out.println();
		System.out.println("Sorted by age");
		//sorted by object age
	    
		Arrays.sort(person, new Q7ComparatorAge());
		for(int i=0; i<person.length;i++){
			System.out.println("Name: "+person[i].getName()+" Department: "+person[i].getDepartment()+" Age: "+person[i].getAge());
		}
	}
}
