package Q6;
import java.util.*;

public class Main 
{
	public static void main(String[] args) 
	{
		int num;
		int newNum;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a number that you would like us to check if it is even: ");
		num = scan.nextInt();
		newNum = num / 2;
		newNum = newNum * 2;
		if(newNum != num)
		{
			System.out.println("This number is not even!");
		}
		else
		{
			System.out.println("This is an even number!!!!!!!");
		}
	}
}
