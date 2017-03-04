package com.revature.weekone.question20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Reads a file containing Entries and prints them out.
 * 
 * @author Michael Hobbs
 *
 */
public class Question20 {	

	/**
	 * Initializes an Entry from a given line read from input.
	 * 
	 * Entries should be stored in data in the following form per line:
	 * First:Last:Age:State
	 * 
	 * @param input a raw line read from data
	 * @return an Entry initialized to the data read from the input
	 */
	public static Entry parseEntry(String input) {
		String[] values = input.split(":");
		
		String name = values[0] + " " + values[1];
		int age = Integer.parseInt(values[2]);
		String state = values[3];
		
		return new Entry(name, age, state);
	}
	
	/**
	 * Prints an Entry.
	 * 
	 * The Entry is printed in the following form:
	 * Name: getName()
	 * Age: getAge() years
	 * State: getState() State
	 * 
	 * @param name the name
	 * @param age the age
	 * @param state the state
	 */
	public static void printEntry(Entry entry) {
		System.out.println("Name: " + entry.getName());
		System.out.println("Age: " + entry.getAge() + (entry.getAge() != 1 ? " years" : " year"));
		System.out.println("State: " + entry.getState() + " State");
	}

	/**
	 * Reads Entries from a file and prints each one out.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("data.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedFileReader = new BufferedReader(fileReader);
		
		String input = null;
		while ((input = bufferedFileReader.readLine()) != null) {
			
			Entry entry = Question20.parseEntry(input);
			
			Question20.printEntry(entry);
			
		}
		
	}

}

/**
 * A person.
 * 
 * @author Michael Hobbs
 *
 */
class Entry {
	
	/**
	 * A person's first and last name.
	 */
	private String name;
	
	/**
	 * A person's age.
	 */
	private int age;
	
	/**
	 * A person's state.
	 */
	private String state;
	
	public Entry(String name, int age, String state) {
		this.name = name;
		this.age = age;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
