package com.revature.Q20;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q20 {

	public static void main(String[] args) throws IOException {
		ArrayList<String> wordsInArray = new ArrayList<String>();
		String filename = "Data.txt";
		//write words to a file
		writeToFile(filename);
		//read words from a file
		wordsInArray=readFromFile(filename);
		//printNewList(wordsInArray);
		getInformation(wordsInArray);
		
	}// main

	public static void writeToFile(String s) {
		File file = new File(s); //create file
		FileOutputStream f = null; //open output file stream
		FileWriter fw = null; //create a file writer
		try {
			f = new FileOutputStream(file);
			fw = new FileWriter(file);
			fw.write("Mickey:Mouse:35:Arizona\n");
			fw.write("Hulk:Hogan:50:Virginia\n");
			fw.write("Roger:Rabbit:22:California\n");
			fw.write("Wonder:Woman:18:Montana\n");
			fw.write("Bugs:Bunny:15:Chicago");
			fw.close(); //close the file writer stream
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static ArrayList<String> readFromFile(String s) {
		File file = new File(s); //create a new file
		BufferedReader f = null; //create a buffered reader
		ArrayList<String> words = new ArrayList<String>(); //an array that stores words
		int count = 0; //the count
		try {
			f = new BufferedReader(new FileReader(file));
			String line = f.readLine(); //get the next line
			while(line!=null){
				for(String w : line.split(":")){
					words.add(w); //add to the words array
				}
				line = f.readLine(); //go to the next line
		}
		} catch (IOException e) {
			e.getStackTrace();
		}
		return words;
	}
	public static void printNewList(ArrayList<String> s){
		for(String w: s){ //loop through array
			System.out.println(w);
		}
	}

	public static void getInformation(ArrayList<String> s){
		for(int i=0; i<s.size(); i+=4){ 
				System.out.println("Name: "+s.get(i) + " " + s.get(i+1));
				System.out.println("Age: "+s.get(i+2));
				System.out.println("State: "+s.get(i+3));
				System.out.println("=========================");
		}
	}
	
}// Q20
