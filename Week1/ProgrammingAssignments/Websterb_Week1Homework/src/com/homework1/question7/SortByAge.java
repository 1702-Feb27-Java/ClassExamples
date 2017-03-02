package com.homework1.question7;

import java.util.Comparator;

public class SortByAge implements Comparator<Employee>{

	/**
	 * sorts by lowest age first
	 */
	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.age - e2.age;
	}

}
