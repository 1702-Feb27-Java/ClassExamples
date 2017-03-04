package Q7;

public class Employee {
	
	public int age;
	public String department;
	public String name;
	public Employee(int age, String department, String name){
		this.age = age;
		this.department = department;
		this.name = name;
		
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static void main(String [] args){
		
	}	
@Override
public String toString(){
	return (name + " " + department);
	
}
}
