package com.revature.q20;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author Aaron Camm
 *
 */
public class MainApp {

	/**
	 * formats data from the "Data.txt" file to display aech Name, Age, and State information from the file
	 * on the console.
	 * 
	 * 
	 * @requires 
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Data.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (br != null){
			try {
				//reads each line from file, formats its, and then displays it. 
				for (String s = br.readLine(); s != null; s = br.readLine()){
					String[] data = s.split(":");
					System.out.println(String.format("Name: %s %s", data[0], data[1]));
					System.out.println(String.format("Age: %s", data[2]));
					System.out.println(String.format("State: %s State", data[3]));		
					System.out.println();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

				

	}

}
