package com.week1homework.question20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadAndFormat
{

	public static void main(String[] args) throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		
		//Read in the file, and set up the ArrayLists for holding data
				File txtFile = new File("Data.txt");
				Scanner scan = new Scanner(txtFile);
				String line = null;
				String[] holder = new String[4];

				ArrayList firstName = new ArrayList();
				ArrayList lastName = new ArrayList();
				ArrayList age = new ArrayList();
				ArrayList state = new ArrayList();
				
				//This while loop is going through the saved file
				while(scan.hasNext())
				{
					for(int i = 0; i < holder.length; i++)
					{
						line = scan.nextLine();
						holder = line.split(":");
						firstName.add(holder[0]);
						lastName.add(holder[1]);
						age.add(holder[2]);
						state.add(holder[3]);
					}
				}
				
				//This for loop is just printing out the information in a formatted way
				for(int j = 0; j < firstName.size(); j++)
				{
					System.out.println("Name: " + firstName.get(j) + " " + lastName.get(j));
					System.out.println("Age: " + age.get(j) + " years");
					System.out.println("State: " + state.get(j) + " State\n");
				}

	}

}
