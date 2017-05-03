package Q20;

import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		//Read in the file, and set up the ArrayLists for holding data
		File file = new File("Data.txt");
		Scanner scan = new Scanner(file);
		String line = null;
		String[] holder = new String[4];

		ArrayList first = new ArrayList();
		ArrayList last = new ArrayList();
		ArrayList age = new ArrayList();
		ArrayList state = new ArrayList();
		
		//This while loop is going through the saved file
		while(scan.hasNext())
		{
			for(int i = 0; i < holder.length; i++)
			{
				line = scan.nextLine();
				holder = line.split(":");
				first.add(holder[0]);
				last.add(holder[1]);
				age.add(holder[2]);
				state.add(holder[3]);
			}
		}
		
		//This for loop is just printing out the information in a formatted way
		for(int j = 0; j < first.size(); j++)
		{
			System.out.println("Name: " + first.get(j) + " " + last.get(j));
			System.out.println("Age: " + age.get(j) + " years");
			System.out.println("State: " + state.get(j) + " State\n");
		}
	}
}
