package com.revature.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOExample {

	public static void main(String[] args) {
		// Creating a .txt file
		File file = new File("outfile2.txt");
		FileWriter fw = null;
		BufferedReader br = null;
		
		try {

			//FileOutputStream fout = new FileOutputStream(file);
			
			//Write a file
			fw = new FileWriter(file);
			fw.write("This is my example file!\n");
			fw.write("A second line?\n");
			fw.write("Aaaaand a third line!\n\n");
			fw.close();
			
			
			
			
			br = new BufferedReader(new FileReader("outfile2.txt"));
			
			int i = 1;
			String s;
			while((s = br.readLine())!=null){
				System.out.println("line " + i + ": " + s);
				i++;
			}
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{

				fw.close();
				br.close();

			} catch(IOException e){
				
			} 
		}

	}

}
