package q19;

import java.util.ArrayList;

public class ArrayListOperations {

	private static final int ARRAY_SIZE = 10;
	public static void main(String[] args) {
		//makes the list
		ArrayList<Integer> list = new ArrayList<Integer>();
		// adds 1-10
		for (int i = 1; i < ARRAY_SIZE + 1; i++){
			list.add(new Integer(i));			
		}
		
		//print out the array
		System.out.print("Printing array: {");
		//print each element
		for (int i = 0; i < list.size(); i++){
			//check if its the last element to print
			if(i < list.size() - 1){
				System.out.print(list.get(i) + ", ");
			}
			else
			{
				System.out.println(list.get(i) + "}");
			}
		}
		
		int evenSum = 0;
		for(int i = 0; i < list.size(); i ++){
			if ((list.get(i) % 2) == 0){ // if it can be divided by 2 with no remainder its even 
				evenSum += list.get(i);
			}
		}
		
		System.out.println("The sum of the even numbers is: " + evenSum);
		
		
		
		int oddSum = 0;
		for(int i = 0; i < list.size(); i ++){
			if ((list.get(i) % 2) != 0){ // if has a remainder when divided by two its odd 
				oddSum += list.get(i);
			}
		}
		
		System.out.println("The sum of the odd numbers is: " + oddSum);
		
		
		for ( int i = 0; i < list.size(); i++){
			boolean isPrime = true;
			int idx = list.get(i);
			
			
			 for (int j=2; j*j<=idx; j++)
			 {
				 
			     if (idx % j == 0) //if it is then it isn't a prime
			      {
			          isPrime=false;
			          break;    
			      }
			 }   
			 if(isPrime){
				 if(idx != 1){
				 list.remove(new Integer(idx));	
				 i--;
				 }
			 }
			 
			
		}
		
		
		
		System.out.print("Printing array: {");
		//print each element
		for (int i = 0; i < list.size(); i++){
			//check if its the last element to print
			if(i < list.size() - 1){
				System.out.print(list.get(i) + ", ");
			}
			else
			{
				System.out.println(list.get(i) + "}");
			}
		}

	}

}
