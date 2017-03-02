package q7;

public class EmployeeSort {

	public static void main(String[] args) {
		//createss new employees
		Employee dave = new Employee("Dave", "Science", 27);
		Employee robert = new Employee("Robert", "Magic", 43);
		
		//compares the age
		EmployeeCompare comp = new EmployeeCompare(0);		
		int compare = comp.compare(dave, robert);
		
		//sorts based on age oldest first
		if(compare >= 0 ){
			System.out.println(dave.getName() + " " + dave.getDepartment() + " " + dave.getAge());
			System.out.println(robert.getName() + " " + robert.getDepartment() + " " + robert.getAge());
		}
		else
		{
			System.out.println(robert.getName() + " " + robert.getDepartment() + " " + robert.getAge());
			System.out.println(dave.getName() + " " + dave.getDepartment() + " " + dave.getAge());
		}
		System.out.println();
		//compares based on department
		EmployeeCompare comp2 = new EmployeeCompare(1);		
		compare = comp2.compare(dave, robert);
		
		//sorts based on department largest value first
		if(compare >= 0 ){
			System.out.println(dave.getName() + " " + dave.getDepartment() + " " + dave.getAge());
			System.out.println(robert.getName() + " " + robert.getDepartment() + " " + robert.getAge());
		}
		else
		{
			System.out.println(robert.getName() + " " + robert.getDepartment() + " " + robert.getAge());
			System.out.println(dave.getName() + " " + dave.getDepartment() + " " + dave.getAge());
		}
		System.out.println();
		
		//compares the names 
		EmployeeCompare comp3 = new EmployeeCompare(2);		
		compare = comp3.compare(dave, robert);
		
		//sorts based on name longest name value displayed first
		if(compare >= 0 ){
			System.out.println(dave.getName() + " " + dave.getDepartment() + " " + dave.getAge());
			System.out.println(robert.getName() + " " + robert.getDepartment() + " " + robert.getAge());
		}
		else
		{
			System.out.println(robert.getName() + " " + robert.getDepartment() + " " + robert.getAge());
			System.out.println(dave.getName() + " " + dave.getDepartment() + " " + dave.getAge());
		}

	}

}
