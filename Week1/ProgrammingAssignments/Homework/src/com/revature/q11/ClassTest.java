package com.revature.q11;

import com.revature.q11_10.OpenClass;

/**
 * This program demonstrates accessing variables form a
 * class that resides in another package 
 * @author Nick
 *
 */
public class ClassTest {

	public static void main(String[] args) {
		
		OpenClass this_class = new OpenClass();
		
		System.out.println(this_class.var);
		System.out.println(this_class.var2);

	}

}
