package com.mory.question13;

public class Q13 {

	public static void main(String[] args) {
		printTriagle();
	}
/***
 * The can think of the desired output as
 * 0101010101.
 * 
 * we see that we 0 shows up only on odd iterations.thus
 * whenever we are at an odd iteration we show zero, otherwise we show 0.
 * 
 */
		public static void printTriagle(){
		boolean showZero=true;
		//number of lines to print
		for(int i=0; i<4;i++){
			//for each iteration display based on boolean showzero
			for(int j=0; j<i+1;j++){
				System.out.print(showZero?"0":"1");
				showZero=!showZero;
				
			}
			System.out.println();
			
		}
	}

}
