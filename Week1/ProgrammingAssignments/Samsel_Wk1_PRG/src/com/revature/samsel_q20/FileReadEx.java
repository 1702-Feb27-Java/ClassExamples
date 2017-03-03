package com.revature.samsel_q20;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Question: Read from a test file and print it out on the screen in the given format
 * Name : sam
 * Age  : 27 years
 * State: Arizona State
 */
public class FileReadEx {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		try(BufferedReader fR = new BufferedReader(new FileReader("FilePreq.txt")))
		{	
			String s=null;
			System.out.println("=============FILE CONTENTS===============");
			while((s= fR.readLine()) != null){
				String [] sArr = s.split(":");
				for(int i=0; i<sArr.length;i++){
					switch(i) {
					case 1: System.out.println("Name  : "+ sArr[i]);
							break;
					case 2: System.out.println("Age   : "+ sArr[i]+ " years");
						    break;
					case 3: System.out.println("State : "+ sArr[i]);
						    break;
				    default : break;
					}
				}
				System.out.println();
			}
			System.out.print("=============================");
		}catch(Exception e){
			e.getStackTrace();
		}
	}
}
