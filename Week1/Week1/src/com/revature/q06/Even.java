package com.revature.q06;
public class Even {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// display function results on input 987
		System.out.println(checkInput(987));
	}
	/**
	 * 
	 * @param n number to be checked
	 * @return true if number is even, false otherwise
	 */
	protected static boolean evenCheck(int n){
		// raises -1 to the nth power
		double a = Math.pow(-1, n);
		return a == 1;
	}
	/**
	 * 
	 * @param testInput input to be tested
	 * @return String that says is even if even or not otherwise
	 */
	static String checkInput(int testInput){
		if(evenCheck(testInput)){
			return("is even");
		}
		else{
			return("is not even");
				
		}
	}
	

}
