package q13;

public class Triangle {

	private static final int NUM_RUNS = 4;
	public static void main(String[] args) {
		//tells if a zero should be printed
		boolean isZero = true;
		// the number of numbers to be printed in a line
		int count = 1;
		// the number of numbers printed 
		int temp = 0;
		
		// run for a certain number of lines
		for ( int i = 0; i < NUM_RUNS; i++){			
		
			//while there are still numbers to print run
			while(temp < count){
				if ( isZero){ //if zero is next print it and change it so a 1 will be printed next
					System.out.print("0 ");
					isZero = false;
				}
				else // if a 1 should be printed then print it and change it so a 0 will be printed next
				{
					System.out.print("1 ");
					isZero = true;
				}	
				//increase counter for numbers printed on this line
				temp++;
			}
			// go to a new line and increase the line number we are on and reset the current line counter 
			System.out.println();
			count++;
			temp = 0;
			
		

	}
		
	}
}
