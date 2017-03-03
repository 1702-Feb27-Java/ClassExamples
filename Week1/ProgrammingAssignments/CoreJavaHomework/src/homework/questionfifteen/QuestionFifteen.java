package homework.questionfifteen;

public class QuestionFifteen extends Math {

	
	public static void main(String[] args) 
	{
		
		// creating methods that allow for 4 function simple calculator
		//this is implementing the interface methods
		QuestionFifteen qf= new QuestionFifteen();
		System.out.println("Additon  5 + 4 = " + qf.addition(5, 4));
		System.out.println("Subtract  5 - 4 = " + qf.subtract(5, 4));
		System.out.println("Multiply 5 * 4 = " + qf.multiplication(5, 4));
		System.out.println("Divide  5 / 4 = " + qf.division(5, 4));

	}

	
}
