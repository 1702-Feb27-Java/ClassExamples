package Q14;

//import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
//import java.util.StringTokenizer;

public class switchCase {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter 1 through 3");
		int start = scan.nextInt();

		switch (start) {

		case 1:
			sqrt(100);

			break;

		case 2:
			date();

			break;

		case 3:
			str();

			break;

		default:
			System.out.println("Sorry, try again.");
		}
		System.out.println(start);
		//System.out.println();
		//System.out.println();

	}

	public static double sqrt(double i) {

		return Math.sqrt(i);
	}

	public static void date() {

		Date d = new Date();
		d.toString();
		// SimpleDateFormat sdf = new SimpleDateFormat;
	}

	public static void str() {

		//String[] sar = new String[];
		String s1 = new String();
		s1 = "I amlearning Core Java";
		String strArray[] = s1.split(" ");
		for( int i = 0; i < strArray.length; i++ ){
		System.out.println(strArray[i]);
		}
		//StringTokenizer st = new StringTokenizer(s1, :);
		//while(st.hasMoreTokens()){
			//sar.add
		}
		
		//String[] str = { s1 };

		/*
		 * switch date;{ case : } switch str;{ case string
		 * "I amlearning Core Java":
		 * 
		 * }
		 */
	
}
