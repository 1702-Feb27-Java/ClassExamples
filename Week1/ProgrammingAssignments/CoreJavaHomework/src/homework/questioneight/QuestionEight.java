//David Lund`
package homework.questioneight;
import homework.questionthree.*;
import java.util.ArrayList;

public class QuestionEight {
int x;
	public static void main(String[] args) {
	// create array list for strings only
		ArrayList<String> liststr = new ArrayList<>();
		//add them
		liststr.add("karan");
		liststr.add("madam");
		liststr.add("tom");
		liststr.add("civic");
		liststr.add("radar");
		liststr.add("sexes");
		liststr.add("jimmy");
		liststr.add("kayak");
		liststr.add("john");
		liststr.add("refer");
		liststr.add("billy");
		liststr.add("did");
		System.out.println("Printing Palindrones");
		ArrayList<String> palliststr = new ArrayList<>();
		ReverseString revString = new ReverseString();
		for(String str: liststr)
		{
		 //add palindromes if the are equal reverse
			if (str.equals(revString.stringReversal(str)))
			{
				palliststr.add(str);
				System.out.println(str);
				
			}
		}
			

		

	}

}
