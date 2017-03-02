package q2;

public class Fibonacci {

	public static final int NUM_FIB = 25;
	public static void main(String[] args) {
		//begining 2 numbers in the fibonacci sequence this is required 
		int fib1 = 0;
		int fib2 = 1;
		
		//temp varable for storage when updating the numbers
		int temp = 0;
		//print the first two numbers
		System.out.print(fib1 + ", ");
		System.out.print(fib2 + ", ");
		
		//iterate to find the next 23 numbers
		for (int i = 0; i < NUM_FIB - 2; i++){
			//add the two preceding numbers to find the next number in the sequence then update the varables
			temp = fib1 + fib2;
			fib1 = fib2;
			fib2 = temp;
			//print the number we just calcuated
			System.out.print(fib2);
			
			// adds a comma if it isn't the last number to be found.
			if(i < NUM_FIB - 3){
				System.out.print(", ");
			}
			
		}
		
	

	}
	


}
