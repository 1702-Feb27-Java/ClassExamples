
//David Lund
package homework.questionnineteen;


import java.util.ArrayList;
import homework.questionnine.*;

public class QuestionNineteen {
	public static void main(String args[]){
	 QuestionNine qn = new QuestionNine();
	
		ArrayList<Integer> alInt = new ArrayList();
		for(int i = 1; i<=10;i++)
			alInt.add(i);
		int count =0;
		int sumEven=0,sumOdd=0;
		for(Integer num : alInt)
		{
			
			if(num%2==0)
			{
				sumEven+=num;
			
			}
			else
			{
				sumOdd+=num;
			}
			
		}
		
			
		
				  
				//  alInt.remove(num);
			
	
		System.out.println("Sum of the Even ->"+ sumEven +"\n"
				+ "Sum of the Odd"+ sumOdd);
		System.out.println("Before Removal");
		
		
		for(Integer num: alInt)
		{
			
			System.out.print(num+" ");
		}
		System.out.println("");
		System.out.print("The remaining \n" );
		for(int i = 0; i<alInt.size();i++)
		{
		
			
			if(QuestionNine.isPrime(alInt.get(i))==true)
			  {
				  
				  alInt.remove(i);
			  }
			//
			
			
		}
		for(Integer num: alInt)
		{
			
			System.out.print(num+" ");
		}
	}

}
