/**
 * 
 */
package q7;

import java.util.Comparator;

/**
 * @author Zachary
 *
 */
public class Employee  {
	private String name;
	private String department;
	private int age;
	
	/*
	 * creates a new Employee object
	 * @param name the name of the employee
	 * @param department the department of the employee
	 * @param age the age of the employee
	 */
	public Employee(String name, String department, int age){
		this.name = name;
		this.department = department;
		this.age = age;
	}

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
	
	
//	/*
//	 * returns a positive number if employee1 is older
//	 * returns 0 if the ages are the same
//	 * returns a negative number if employee1 is younger.
//	 */
//	@Override
//	public int compare(Employee o1, Employee o2) {
//		return o1.getAge() - o2.getAge();
//	}
	
	
}
