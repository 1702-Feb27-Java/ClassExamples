package Q10;

public class Main
{
	public static void main(String args[])
	{
		//taking value as command line argument.
		//Converting String format to Integer value
		
		int i = Integer.parseInt(args[0]);
		int j = Integer.parseInt(args[1]);
		int result = (i < j) ? i : j;
		System.out.println(result + " is a minimum value out of the two");
	}
}
