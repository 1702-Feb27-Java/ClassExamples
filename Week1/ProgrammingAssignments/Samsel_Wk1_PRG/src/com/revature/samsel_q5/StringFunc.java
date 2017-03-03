package com.revature.samsel_q5;

/*
 * Question : Write SubString method to accept a string 'str' and an integer 'idx' and return 
 * the substring contained between 0 and idx-1 inclusive.
 */
public class StringFunc{

	public static String substring(String iStr, int idx){
		int uplimitToSearch=0;
		if(iStr.length() >= idx)
			uplimitToSearch = idx;
		else
			uplimitToSearch = iStr.length();
		
		char[] c = new char[uplimitToSearch];
		for(int i=0; i<= uplimitToSearch-1;i++){
			c[i] = iStr.charAt(i);
		}	
		return new String(c);
	}

	public static void main(String[] args) {

		String name = "samsel";
		int iIndex = 4;
		String sSub = StringFunc.substring(name, iIndex);
		System.out.println("Given String : "+name);
		System.out.println("SubString of "+iIndex+" characters :"+sSub);
	}
}
