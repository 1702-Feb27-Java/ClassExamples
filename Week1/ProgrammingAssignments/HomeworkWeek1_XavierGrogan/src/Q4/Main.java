package Q4;
import java.util.*;

public class Main 
{
	public static void main(String[] args)
	{
		int total = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number whose factorial you would like to compute: ");
		int N = scan.nextInt();
		
		for(int i = N; i > 0; i--)
		{
			total *= i;
		}
		
		System.out.println(total + " is the final answer!");
	}
}