package com.revature.q12;

public class EvenArray{
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int hundredArr[] = new int[100];
		// populate hundredArr with numbers from 1 to 100
		// and execute checkEven method using the same enhanced for loop
		int a = 1;
		for(int i : hundredArr){
			// postfix increment a after assigning each i in hundredArr
			i = a++;
			// run checkEven procedure on each newly assigned i
			if(checkEven(i)){
				// display i if checkEven passes
				System.out.print(i + " ");
			}
		}
	}
	
	/**
	 * 
	 * @param n number to be checked
	 * @return true if even, false otherwise
	 */
	static boolean checkEven(int n){
		if(Math.pow(-1, n) == 1){
			return true;
		}
		return false;
	}

}
