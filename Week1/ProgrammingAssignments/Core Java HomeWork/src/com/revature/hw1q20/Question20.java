package com.revature.hw1q20;

import java.io.*;

public class Question20 {
	public static void main(String[] args) {
		Question20 q20 = new Question20();
		
		q20.writeFile();
		q20.printLine();
	}
	
	File file = new File("Data.txt");
	
	//writes the stuff in the file
	public void writeFile(){
	try(FileWriter fw = new FileWriter(file)){
		
		fw.write("Mickey:Mouse:35:Arizona\n");
		fw.write("Hulk:Hogan:50:Virginia\n");
		fw.write("Roger:Rabbit:22:California\n");
		fw.write("Wonder:Woman:18:Montana\n");
		
	}catch(IOException e){
		
		System.out.println("File not found");
		
	}
	
	}
	
	//prints the stuff in the file. only mickey mouse.
	
	public void printLine(){
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			
			String[] sArr = br.readLine().split(":");
			System.out.println("Name: " + sArr[0] + " " + sArr[1]);
			System.out.println("Age: " + sArr[2] + " years");
			System.out.println("State: " + sArr[3] + " State");

			
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	


}
