package com.homework1.question7;

import java.util.Comparator;

public class SortByName implements Comparator<Employee>{

	/**
	 * returns 0 if strings equal, less than 0 if the argument is further in the alphabet, and 
	 * greater than 0 if the argument is closer to 'a' in the alphabet
	 */
	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.name.compareTo(e2.name);
	}

}
