//David Lund
package homework.questionthree;


public class ReverseString
{
	
	public String stringReversal(String rev)
	{
		
		for (int i = 0; i < rev.length(); i++) {
		    rev = rev.substring(1, rev.length() - i) + rev.substring(0, 1)  + rev.substring(rev.length() - i, rev.length());
		 }
		return rev;
	}
}