package Q7;

import java.util.ArrayList;
import java.util.Collections;

public class finish {
public static void main(String[]args){

	Employee e1 = new Employee(23, "HR", "Frank"); 
	Employee e2 = new Employee(49, "sales", "Bill");
	
	CompareAge ca = new CompareAge();
	CompareDepartment cd = new CompareDepartment();
	CompareName cn = new CompareName();
	
	ArrayList<Employee> arr = new ArrayList<>();
	arr.add(e1);
	arr.add(e2);
	
	Collections.sort(arr, ca);
	System.out.println(arr);
	
	Collections.sort(arr, cd);
	System.out.println(arr);
	
	Collections.sort(arr, cn);
	System.out.println(arr);
	
	
	
	
	
	}	
}