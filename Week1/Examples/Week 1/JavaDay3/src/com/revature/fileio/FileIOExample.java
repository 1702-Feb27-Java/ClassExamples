package com.revature.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOExample {

	public static void main(String[] args) {

		// Create .txt file
		
		File file = new File("outfile.txt");
		FileWriter fw = null;
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			FileOutputStream fout = new FileOutputStream(file);
			
			fw = new FileWriter(file);
			fw.write("This is my example file.\n");
			fw.write("The second line.");
			fw.close();
			
			br = new BufferedReader(new FileReader("outfile.txt"));
			
			String s;
			while ((s = br.readLine()) != null){ // runs a while loop to read the entire file
				System.out.println(s);
			}
			
			//System.out.println(br.readLine());
			//System.out.println(br.readLine());
			

			// throw new RuntimeException();
			
		}catch(IOException e){
			e.printStackTrace();
			// System.exit(0);  // this will stop the finally block from executing
		}finally {  // the finally block will always execute unless an error occurs
			
			try{
				// System.out.println("Test for finally block.");
				fw.close();
				// fr.close();
			}catch(IOException e){
				
			}
			
		}
		
	}

}
