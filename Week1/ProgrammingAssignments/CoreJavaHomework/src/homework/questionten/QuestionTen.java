//David Lund
package homework.questionten;

public class QuestionTen {

	public static void main(String[] args) {
		
		int minVal=2,num1Val=3,num2Val=4;
		minVal = (num1Val < num2Val) ? num1Val : num2Val;
		System.out.println(String.format("The Minumum value of %d and %d",num1Val,num2Val));
		System.out.println(minVal);
	}

}
