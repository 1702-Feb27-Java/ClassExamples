package com.revature.q15;
/**
 * 
 * @author Aaron Camm
 *
 */
public interface OperatorInterface<T extends Number> {
	/**
	 * Returns a new copy of <T> with its value added by n
	 * @param n
	 * @return
	 */
	OperatorInterface<T> addition(T n);
	/**
	 * Returns a new copy of <T> with its value subtracted by n
	 * @param n
	 * @return
	 */
	OperatorInterface<T> subtraction(T n);
	
	/**
	 * Returns a new copy of <T> with its value multiplied by n
	 * @param n
	 * @return
	 */
	OperatorInterface<T> multiplication(T n);
	
	/**
	 * Returns a new copy of <T> with its value divided by n
	 * @param n
	 * @return
	 */
	OperatorInterface<T> division(T n);
	
	
}
