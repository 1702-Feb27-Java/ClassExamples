package Q15;

public class Main
{
	public static void main(String[] args)
	{
		int a = 20;
		int b = 5;
		Mathematics mather = new Mathematics();
		System.out.println("Your numbers are: " + a + " and " + b);
		
		//These are printing out the returned values of the methods
		System.out.println("Addition: " + mather.addition(a, b));
		System.out.println("Subtraction: " + mather.subtraction(a, b));
		System.out.println("Multiplication: " + mather.multiplication(a, b));
		System.out.println("Division: " + mather.division(a, b));
	}
}
