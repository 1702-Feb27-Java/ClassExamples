package com.revature.hw1q7;
import java.util.*;

public class Question7{
	

	
	public static void main(String[] args) {
		Employee emp1;
		Employee emp2;
		
		ArrayList<Employee> empList;
		emp1 = new Employee("bob", "engineering", 41);
		emp2 = new Employee("Casdasdasd", "hr", 22);
		
		empList = new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		
		Question7 q7 = new Question7();
		q7.sortByEach(empList);

	}
	
	// constructor to create the employee objects
	public Question7(){



	}
	
	//method to sort by name, dep and age
	public void sortByEach(ArrayList<Employee> empList){
		Collections.sort(empList, new CompareName());
		System.out.println("Sorting by name");
		System.out.println(empList);
		Collections.sort(empList, new CompareDepartment());
		System.out.println("Sorting by department");
		System.out.println(empList);
		Collections.sort(empList, new CompareAge());
		System.out.println("Sorting by age");
		System.out.println(empList);
				
	}


}

//creating classes implementing comparator interface
class CompareName implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.name.compareToIgnoreCase(o2.name);
	}
	
}
class CompareDepartment implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.department.compareToIgnoreCase(o2.department);
	}
	
}
class CompareAge implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		// saw it online but its cool, does a check for age to see if case is true, if not goes to next case.
		return o1.age < o2.age ? -1: o1.age == o2.age ? 0 : 1;
	}
	
}


// creating a employee object to compare
class Employee{
	String name;
	String department;
	int age;
	
	// constructor
	public Employee(String name, String department, int age){
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
	@Override
	public String toString(){
		return name + " " + department + " " + age;
	}
	
}
