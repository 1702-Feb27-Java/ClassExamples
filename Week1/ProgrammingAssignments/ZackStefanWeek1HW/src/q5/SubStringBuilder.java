package q5;

public class SubStringBuilder {

	private static final int IDX = 4;
	private static final String STRING_INPUT = "string";
	public static void main(String[] args) {
		System.out.print(buildString(IDX, STRING_INPUT));

	}
	
	/*
	 * builds returns the first x charactors in a string as a substring
	 * @param int x the number of charactors to return
	 * @param String str the string to make a substring out of.
	 */
	public static String buildString(int x, String str){
		//set a string for the result
		String temp = "";
		
		//builds the substring 
		for(int j = 0; j < x; j++){
			temp += str.charAt(j);
		}
		return temp;
	}

}
