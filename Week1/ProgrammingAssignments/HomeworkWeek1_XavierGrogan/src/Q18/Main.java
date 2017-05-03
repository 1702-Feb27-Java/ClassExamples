package Q18;

public class Main
{
	public static void main(String[] args)
	{
		WorkingClass work = new WorkingClass();
		String tester = "What's up, my name is Xavier!";
		System.out.println("The original string: " + tester);
		
		System.out.println("\nIs there an uppercase letter in the test string: " + work.isThereUpper(tester));
		System.out.println("\nThe test string returned as all upper case: " + work.allUpper(tester));
		System.out.println("\nConverting test string to an integer and adding 10: " + work.stringToInt(tester));
	}
}
