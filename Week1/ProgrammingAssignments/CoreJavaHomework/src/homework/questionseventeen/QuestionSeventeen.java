//David Lund
package homework.questionseventeen;

import java.util.Scanner;

public class QuestionSeventeen {

	public static void main(String[] args) {
	double principal=0,rate=0,time=0,intrest=0;

	Scanner sc = new Scanner(System.in);
	
	System.out.print("Enter Principal \n->");
	principal = sc.nextDouble();
	sc = refreshScanner();
	System.out.print("Enter Rate \n-> ");
	rate = sc.nextDouble();
	sc = refreshScanner();
	System.out.print("Enter Time \n->");
	time = sc.nextDouble();
	intrest = principal * time *rate;
	System.out.println("The Intrest is -> " + intrest);
	
	
	
	}
 public static Scanner refreshScanner()
 {
	 
	 return (new Scanner(System.in));
 }
}
