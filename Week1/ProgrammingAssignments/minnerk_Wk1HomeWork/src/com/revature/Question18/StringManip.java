package com.revature.Question18;

public class StringManip extends StringMain {
	
	//isUpperCase Method
	public boolean isUpperCase(String str){

		//Loop that checks each character in a string for 
		//upper case, once one is found it returns true, if not false
		for (int i = 0;i<str.length();i++){
			if (Character.isUpperCase(str.charAt(i))){
				return true;
			}
		}
		return false;
	}
	
	//ToUpperCase Method
	public String toUpperCase(String str){
		String s = str;
		return s.toUpperCase();
	}

	//toInt Method
	public int toInt(String str){
		int a = 0;
		
		//Loop that gets character by characters numerical value and sums it
		for (int i = 0;i<str.length();i++){
			a += Integer.valueOf(str.charAt(i));
		}return a;
	}
}
