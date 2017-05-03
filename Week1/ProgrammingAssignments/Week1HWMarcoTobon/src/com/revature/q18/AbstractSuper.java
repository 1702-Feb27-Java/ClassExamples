package com.revature.q18;

public abstract class AbstractSuper
{
	/**
	 * @param s 
	 * @return boolean
	 * Returns true if there is an uppercase char in our string
	 */
	public abstract boolean checkUppercase (String s);
	/**
	 *  @param s
	 *  @return String
	 *  Returns a string that is the uppercase of what was passed
	 */
	public abstract String toUpper (String s);
	/**
	 * @param s 
	 * @return int
	 * Returns a string converted to an int + 10
	 * if string contains any char it will instead add the Dec of each one
	 */
	public abstract int StringToInt (String s);
}
