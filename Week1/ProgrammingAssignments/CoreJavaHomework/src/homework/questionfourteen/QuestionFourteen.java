//David Lund
package homework.questionfourteen;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class QuestionFourteen {

	public static void main(String[] args) {
	int switchNum=0;
	// run scanner
	//allow for user input
			Scanner sc = new Scanner(System.in);
			try
			{
				//catch invalid entry;
				System.out.println("Enter 1 to Run Square root Method");
				System.out.println("Enter 2 to Display Todays Date.");
				System.out.print("Enter 3 to Split I am learning Core Java\n->");
				
				
				switchNum = sc.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Error Invalid Entry !");
				
			}
		switch (switchNum)
		{
		case 1:
			System.out.println("Printing square root of 222 ->"+Math.sqrt(222));
			
			break;
		case 2:
			System.out.print("Date: ");
			System.out.println(LocalDateTime.now());
			System.out.println();
			break;
		case 3:
			System.out.println("Spliting String I am learning Core Java");
			String str = "I am learning Core Java";
			String[] strarr = str.split(" ");
			for(String s : strarr)
			{
				
				System.out.println(s);
			}
			break;
		
		default:
			System.out.println("Not A Valid Case");
		
		}
		System.out.println("Exiting");

	}

}
