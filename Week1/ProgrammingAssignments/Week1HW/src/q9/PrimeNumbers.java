package q9;

import java.util.ArrayList;

public class PrimeNumbers {

	public static void main(String[] args) {
		//make the arrayList and adds 1-100
		ArrayList<Integer> primeArr = new ArrayList<Integer>();		
		for(int i = 1; i < 101; i++){
			primeArr.add(i);
		}
		
		//start at 2 since 1 isn't a prime and do the loop for each number
		for (int idx = 2; idx < 100; idx++){
			//sets a temp variable so we know if we need to print it
			 boolean isPrime=true;
			 //check if the number is divisable
			 for (int j=2; j*j<=idx; j++)
			 {
			     if (idx % j == 0) //if it is then it isn't a prime
			      {
			          isPrime=false;
			          break;    
			      }
			 }   
			      if(isPrime){ // if it isn't then prime will still be true and we can print the number
			        	System.out.println(idx);
			    }
		}

	}

}
