package com.revature.q20;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * This program will check a file and print it in a certain format
 * @author Nick
 *
 */
public class StringReader {

	public static void main(String[] args) throws IOException {
		ReadingFile();

	}
	
	/**
	 * This function will check the file that it is trying to read
	 * Will throw an error if the file is not found or named incorrectly
	 * @throws IOException 
	 */
	public static void ReadingFile() throws IOException{
		
		
		//reading file
		File file = new File("Data.txt");
		FileReader same_file = new FileReader(file);
		BufferedReader BF = new BufferedReader(same_file);
		
		String s;
		String sArr1[] = null;
		
		
		while ( (s = BF.readLine()) != null) {
			//System.out.println(s);
			sArr1 = s.split(":");
			
			System.out.println("Name: "+ sArr1[0] + " " + sArr1[1]);
			System.out.println("Age: " + sArr1[2]);
			System.out.println("State: " + sArr1[3] + " State");
			
			System.out.println();
		}
		 
		BF.close();
		same_file.close();
	
		
	}
		

}
