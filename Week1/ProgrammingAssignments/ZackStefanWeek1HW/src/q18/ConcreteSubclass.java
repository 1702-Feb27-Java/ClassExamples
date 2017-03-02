package q18;

public class ConcreteSubclass extends Abstract{
	
	public ConcreteSubclass(){super();}

	/*
	 * checks for uppercase letters 
	 * @param str the string to check
	 * @return true if uppercase letters are found false if not
	 */
	@Override
	public boolean checkUppercase(String str) {
		for (int i = 0; i < str.length(); i++){ // check every char in the string
			if((int)str.charAt(i) >= 65 && (int)str.charAt(i) <= 90){ // if the char code is between 65 & 90 its uppercase
				return true;
			}
		}
		return false;
	}

	/*
	 * converts all letters to uppercase 
	 * @param str the string to check
	 * @return a string of all uppercase letters
	 */
	@Override
	public String convertLowercase(String str) {
		int ch;
		for (int i = 0; i < str.length(); i++){ // check every char in the string
			ch = (int)str.charAt(i);
			if(ch >= 97 && ch <= 122){ // if the char code is between 97 & 122 its lowercase
				
				
				str = str.replace((char)ch, (char)(ch- 32)); //replaces the lowercase letter with its upper case letter
			}
		}
		return str;
	}

	/*
	 * converts a string to an int and adds 10
	 * @param str the string to change to an int
	 * @return the string in number for + 10
	 */
	@Override
	public int stringToInt(String str) {
		//make an Integer object and get the int value to store
		Integer result = new Integer(str);
		int x = result.intValue();
		
		//add ten and return the number
		x += 10;	
		return x;
	}

}
