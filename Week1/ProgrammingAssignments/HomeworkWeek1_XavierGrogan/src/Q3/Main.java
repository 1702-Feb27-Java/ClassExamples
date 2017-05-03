package Q3;

public class Main 
{
	public static void main(String[] args)
	{
		String str = "SuperCaliFragilisticExpialidosious";
		char a = 0;
		
		//loops through in reverse, concatinating the letters to the end of the string
		for(int i = str.length() - 1; i >= 0; i--)
		{
			str += str.charAt(i);
		}
		
		//sets the string equal to the last half of the string
		str = str.substring(str.length()/2, str.length());
		System.out.println(str);
	}
}
