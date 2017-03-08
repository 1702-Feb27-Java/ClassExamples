package com.mory.question06;

public class Q6 {

	public static void main(String[] args) {
		System.out.println(isEven(4));

	}
    /***
     * This method takes a number and check whether it is
     * even or odd
     * 
     * @param number The number which parity to check
     * @return true if the number is even and false otherwise
     */
	public static boolean isEven(int number) {
		if((number/2)*2==number) return true;
		return false;
		
		

	}

}
