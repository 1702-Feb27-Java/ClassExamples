package Q5;
import java.util.*;

public class Main 
{
	//Takes in a string and an int, then makes the string a substring of the original without using the substring methods
	public static void main(String[] args) 
	{
		String str;
		int idx;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a string: ");
		str = scan.nextLine();
		System.out.println("Please enter a number: ");
		idx = scan.nextInt();
		
		try
		{
			str = str.copyValueOf(str.toCharArray(), 0, idx-1);
		}catch(StringIndexOutOfBoundsException e)
		{
			System.out.println("String Index Out Of Bounds Exception Caught: " + e.getMessage());
		}
		System.out.println(str);
	}
}