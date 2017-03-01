package com.revature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedRead {
	public static void main(String[] args) {

		// BufferedReader br=null;
		//BufferedWriter bw=null;
		
		try (BufferedReader br = new BufferedReader(new FileReader("test1.txt")); BufferedWriter bw = new BufferedWriter(new FileWriter("test1.txt"))){
			
			  //br= new BufferedReader(new FileReader("test1.txt"));
			  
			  String s=null;
			  
			  while(( s=br.readLine())!=null) System.out.println(s);
			 
			
		/*	bw = new BufferedWriter(new FileWriter("test1.txt"));
			bw.write(" This is a sample text. ");
			*/
			System.out.println("done!");

		} catch (Exception e) {
			e.printStackTrace();

		} catch (IOException e){
			
			
		}
		
/*		finally{

			try {
				br.close();
				//bw.flush();
				//bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}*/
		

	}
}
