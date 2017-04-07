//David Lund
package homework.questionsix;

public class QuestionSix {

	public static void main(String[] args) {
		
	// check using method if 	
    QuestionSix qs = new QuestionSix();
		
	System.out.println(qs.checkIfEven(2));
	System.out.println(qs.checkIfEven(3));

	}
public String checkIfEven(int num)
{
	// if num & 1 is equal to zero then it is even
	
	if((num & 1) == 0){
	    return ("Even number");
	}
	else
	{
	    return("Odd number");
	}
	
}
}
