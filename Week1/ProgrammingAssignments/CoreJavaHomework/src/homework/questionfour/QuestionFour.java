
//David Lund
package homework.questionfour;

import java.util.Scanner;

public class QuestionFour {

	public static void main(String[] args) 
	{
		
    double num=5;
	//Scanner input = new Scanner(System.in);
	//num = input.nextDouble();
	QuestionFour qf= new QuestionFour();
	
    System.out.println("The factorial is -> "+ qf.computeFactorial(num));
	}
    public double computeFactorial(double factNum)
    {
    	//Compound multiply down to 1 return result
    	double result=1;
    	for (; factNum!=0;factNum--)
    	{
    	    result *=factNum;
    		
    	}
    	factNum = result;
    	return result;
    }

    public double display(double num)
    {
    	return num;
    	
    }
}
