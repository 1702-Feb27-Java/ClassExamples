package com.revature.banking.test;

import java.util.Scanner;

public class TestRegex {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		 
		String s = "";
		while(true) {
			s = scan.nextLine();
			System.out.println(s);
			if (!s.matches("[A-Za-z0-9_]*")) { //alphanumeric _
			//if (!s.matches("[A-Za-z0-9_!@#$%^&*()-]*")) { //alphanumeric !@#$%^&*()-
				System.out.println(true); //bad string
			}
			else {
				System.out.println(false); //good string
			}
		}
	}

}
