package q7;

import java.util.Comparator;

public class EmployeeCompare implements Comparator<Employee>{
	private int compareType;
	
	/*
	 * takes the mode to sort 1 if by age, 2 if by Department, 3 if by Name
	 */
	public EmployeeCompare(int compareType){
		this.compareType = compareType;
	}
	
	/*
	 * compares the two employee's and returns a 
	 */
	@Override
	public int compare(Employee arg0, Employee arg1) {
		
		//compares the age
		if(compareType == 0) return arg0.getAge() - arg1.getAge();
		
		//compares the departments
		if(compareType == 1){			
			return arg0.getDepartment().compareTo(arg1.getDepartment());
		}
		else{ //compares the Name
		return arg0.getName().compareTo(arg1.getName());
		}
		 
		
		//return 0;
	}

}
