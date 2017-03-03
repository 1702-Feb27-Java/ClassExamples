package com.revature.q13;
/**
 * This program will print out a triangle of 
 * ones and zeros in a particular pattern
 * i.e
 * 0
 * 1 0
 * 1 0 1
 * @author Nick
 *
 */
public class TrianglePrint {

	public static void main(String[] args) {
		
		boolean check = false;
		int count = 0;
		int zero = 0;
		int one = 1;
		
		//for loop for the row
		for ( int i = 0; i < 4; i++) {
			//increment count to check against j(column)
			count++;
			//for loop for the column
			for(int j = 1; j <= count; j++) {
				if ( check == false ){
					//print 0 as the top of the triangle
					System.out.print(zero + " ");
					check = true;
				}
				
				else {
					check = false;
					System.out.print(one + " ");
				}
				if( j == count)
					//if the count is the same as the column number go to the next line
					System.out.println();
			}
		}	
		
	}

}
