package Q14;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice;
		System.out.println("Please enter 1, 2, or 3.");
		choice = scan.nextInt();
		
		switch(choice)
		{
		case 1:
			double number;
			System.out.println("Please enter a number for me to find the square root of: ");
			number = scan.nextDouble();
			System.out.println(Math.sqrt(number));
			break;
		case 2:
			Date date = new Date();
			System.out.println(date.toString());
			break;
		case 3:
			String str = "I am learning Core Java";
			String[] array = new String[5];
			array = str.split(" ");
			for(int i = 0; i < array.length; i++)
			{
				System.out.println(array[i]);
			}
			break;
		default:
			System.out.println("Invalid Command");
			
		}
	}
}
