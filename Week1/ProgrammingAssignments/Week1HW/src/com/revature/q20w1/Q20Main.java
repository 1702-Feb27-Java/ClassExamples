// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 20 - CREATE A NOTEPAD FILE CALLED DATA.TXT AND ENTER THE FOLLOWING:
// WRITE A PROGRAM THAT WOULD READ FROM THE FILE AND PRINT IT OUT TO THE SCREEN IN THE
// FOLLOWING FORMAT.

package com.revature.q20w1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Q20Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			// the directory of the Data.txt may by different for when you run it
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/Danni Tang/Documents/Data.txt"));
			
			String s;  // intialize a string
			
			while ((s = br.readLine()) != null){ // runs a while loop to read the entire file
				String[] s2 = s.split(":");  // we split the string by :
				System.out.println("Name: " + s2[0] + " " + s2[1]);  // print
				System.out.println("Age: " + s2[2] + " years");
				System.out.println("State: " + s2[3] + " State\n");
			}
			
		} catch (IOException e) {  // IOException includes FileNotFound
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
