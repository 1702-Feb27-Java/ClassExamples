package com.revature.q20;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author tobon
 *
 *Reads a file and prints out its contents in
 *readable format
 */
public class Question20
{
	public static void main(String[] args)
	{
		Scanner sc = null; 
		File fl = null;
		try
		{
			fl = new File("Data.txt");
			sc = new Scanner (fl);
			String line; 
			while (sc.hasNextLine())
			{
				String arr[] = null;
				line = sc.nextLine();
				
				arr = line.split(":");
				System.out.println("Name: " + arr[0] + " " + arr[1] );
				System.out.println("Age: " + arr[2] + " years");
				System.out.println("State: " + arr[3] + " state");
				System.out.println();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
				sc.close();
		}
	}

}
