//David Lund
package homework.questionnine;

import java.util.ArrayList;

public class QuestionNine {

	public static void main(String[] args) {
		ArrayList<Integer>  numList= new ArrayList<>();
		for(int i = 1 ; i <=100;i++)
		{
			numList.add(i);
		}
	     for (int i = 0; i<numList.size()-1; i++){
             if (!isPrime(numList.get(i))){
 
                 
             }
             else 
             {
            	 
            	 System.out.println("Prime Wire:"+numList.get(i) );
             }
         }
		
	}
	  public static boolean isPrime(int primeValCheck){
	        boolean flagIsPrime = true;
	        for (int i = 2; i < primeValCheck; i++){
	            if(primeValCheck%i == 0){
	                flagIsPrime = false;  
	            }
	        }
	        return flagIsPrime;
	    }
}
