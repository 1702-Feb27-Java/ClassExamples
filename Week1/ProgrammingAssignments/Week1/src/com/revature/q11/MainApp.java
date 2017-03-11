package com.revature.q11;

import com.revature.q11.access.Access;
/**
 * 
 * @author Aaron Camm
 *
 */
public class MainApp {

	public static void main(String[] args) {
		Access access = new Access();
		
		System.out.println(access.getA());
		System.out.println(access.getB());
	}

}
