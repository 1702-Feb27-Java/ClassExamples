package q3;

public class ReverseString {

	public static void main(String[] args) {
		//gets the string inputed in the command line while running this program
		//the current string set to run is "string"
		String str = args[0];
		
		//gets the length of the string
		int stringLen = str.length();		
		
		//for the length of the string to reverse
		for(int i = 0; i < stringLen; i++){
			
			//add the string toegther
			str = str.substring(1, stringLen - i)+ str.substring(0, 1) + str.substring(stringLen - i, stringLen);
			
			
		}
		
		//print reversed string
		System.out.println(str);
		
		

	}

}
