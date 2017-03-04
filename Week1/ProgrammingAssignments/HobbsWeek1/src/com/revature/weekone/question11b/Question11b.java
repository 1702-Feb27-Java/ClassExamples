package com.revature.weekone.question11b;

/**
 * Stores and provides data for a client.
 * 
 * @author Michael Hobbs
 *
 */
public class Question11b {
	
	/**
	 * A retrievable value.
	 * 
	 */
	private float value1, value2;
	
	/**
	 * Initializes the values stored by this service.
	 * 
	 * @param value1 the first value
	 * @param value2 the second value
	 */
	public Question11b(float value1, float value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
	
	/**
	 * Returns the first value.
	 * 
	 * @return the first value
	 */
	public float getValue1() {
		return this.value1;
	}
	
	/**
	 * Stores a new value for the first value.
	 * 
	 * @param value1 the new value of the first value
	 */
	public void setValue1(float value1) {
		this.value1 = value1;
	}
	
	/**
	 * Returns the second value.
	 * 
	 * @return the second value
	 */
	public float getValue2() {
		return this.value2;
	}
	
	/**
	 * Stores a new value for the second value.
	 * 
	 * @param value2 the new value of the second value
	 */
	public void setValue2(float value2) {
		this.value2 = value2;
	}

}
