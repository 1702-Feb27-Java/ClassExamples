package com.revature.Question20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class IOQuestion {

	public static void main(String[] args) {
		
		//Creation of the BufferedReader object set to null
		BufferedReader br = null;
			
		//Try block to check to ensure the file exists
		try{
			br = new BufferedReader(new FileReader("Q20.txt"));	//Reading Q20.txt file

			String s = br.readLine(); //Setting up a string for a readline
						
			while(s != null) { //Loop until no more lines in text file
				
				StringTokenizer st = new StringTokenizer(s); //Creation of a StringTokenizer object to
															 //break up each line of strings
				
				while (st.hasMoreTokens()){  //Loop until no more words between :
					System.out.println("Name: " + st.nextToken(":") +
							" " + st.nextToken(":") + "\nAge: " + st.nextToken(":") + "\nState: " +
							st.nextToken(":")+"\n");
				}
				s = br.readLine(); //Read next Line
			}		
			
		//Catch exception if the file does not exist
		}catch (IOException e){
			System.out.println("File does not exist");
				
		//If all is good and done, closes the stream
		}finally {
			try{
				if (br != null)
					br.close();
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

