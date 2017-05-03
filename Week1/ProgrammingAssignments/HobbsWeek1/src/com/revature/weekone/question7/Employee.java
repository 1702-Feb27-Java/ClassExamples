package com.revature.weekone.question7;

/**
 * 
 * @author Michael Hobbs
 *
 */
public class Employee {

	private String name;
	private String department;
	private int age;
	
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

	public Employee(String name, String department, int age) {
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
//	public int compareName(Employee e) {
//		return this.name.compareTo(e.name);
//	}
//	
//	public int compareDepartment(Employee e) {
//		return this.department.compareTo(e.department);
//	}
//	
//	public int compareAge(Employee e) {
//		return this.age - e.age;
//	}
	
//	/**
//	 * Two employees are ordered first by name, then by department, then by age.
//	 * 
//	 * @param arg0
//	 * @param arg1
//	 * @return zero if equal, less than zero if arg0 is ordered before arg1, greater than zero if arg0 is ordered after arg1
//	 */
//	@Override
//	public int compareTo(Employee e) {
//		
//		int nameComparison = this.compareName(e);
//		
//		if (nameComparison < 0) {
//			int departmentComparison = this.compareDepartment(e);
//			if (departmentComparison < 0) {
//				int ageComparison = this.compareAge(e);
//				if (ageComparison < 0) {
//					return -1;
//				}
//				else if (ageComparison > 0) {
//					return 1;
//				}
//				else {
//					return 0;
//				}
//			}
//			else if (departmentComparison > 0) {
//				return 1;
//			}
//			else {
//				return 0;
//			}
//		}
//		else if (nameComparison > 0) {
//			return 1;
//		}
//		else {
//			return 0;
//		}
//	}
	
	@Override
	public String toString() {
		return "EMPLOYEE [name=this.name=" + this.name + ", department=" + this.department + ", age=" + this.age + "]"; 
	}
	
}