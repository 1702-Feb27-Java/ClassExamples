package q4;

public class Factorial {

	public static void main(String[] args) {
		System.out.println(calcFactorial(9));
	}
	
	/*
	 * function will calculate the factoral of a given number. If the number is 0 then 0 will be returned.
	 *  Likewise if the number is 1 then 1 will be returned. This is to prevent an array out of bounds error.
	 */
	public static int calcFactorial(int n){
		
		if (n == 0){	//prevents error if factoral of 0 is attemped to be calculated
			return 0;
		}
		else if (n == 1){ // prevents error if factoral of 1 is calculated
			return 1;
		}
		else{
			int total = n * (n - 1); //sets the total as the first two numbers to be multiplied 
			for (int i = n - 2; i > 1 ; i--){ // makes sure every number is multiplied 
				total *= i; // stores the factarial 
			}
			return total;
		}
		
		
	}

}
