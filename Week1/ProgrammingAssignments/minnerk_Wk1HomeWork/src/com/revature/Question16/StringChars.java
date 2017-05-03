package com.revature.Question16;

public class StringChars {

	public static void main(String[] args) {
		
		//Loop that prints out the length of each argument string entered in
		for(int i = 0; i < args.length;i++){
			System.out.println("String character count: " + args[i].length());
		}
	}
}
