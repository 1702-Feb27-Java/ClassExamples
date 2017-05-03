package com.revature.weekone.question11a;

import com.revature.weekone.question11b.Question11b;

/**
 * Accesses data stored in a service.
 * 
 * @author Michael Hobbs
 *
 */
public class Question11a {

	/**
	 * Accesses data stored in a service.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Question11b dataStore = new Question11b(1.0f, 100.0f); //the service which provides values.

		System.out.println(dataStore.getValue1() + ", " + dataStore.getValue2()); //retrieve the values.
	}

}
