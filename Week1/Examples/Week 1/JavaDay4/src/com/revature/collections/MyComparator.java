package com.revature.collections;

import java.util.Comparator;

import com.revature.serialization.Employee;

public class MyComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee e1, Employee e2) {
		// TODO Auto-generated method stub
		
//		if (e1.getAge() < e2.getAge()){
//			return -1;
//		}
//		if (e1.getAge() > e2.getAge()){
//			return 1;
//		}
//		return 0;
		
		return e1.getName().toUpperCase().compareTo(e2.getName().toUpperCase());
	}
	
}
