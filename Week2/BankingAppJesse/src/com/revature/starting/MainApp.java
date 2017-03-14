package com.revature.starting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) throws IOException{
		
		
		FunctionClass fc = new FunctionClass();
		fc.shotcaller();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}}
	/*	
		//intro 
		System.out.println("welcome to Generic Bank. Are you an admin, employee, or user?");
		
		try{
		Scanner uinput = new Scanner(System.in);
		
			System.out.println("what is your user name?");
			Scanner u = new Scanner (System.in);
			String step2 = u.nextLine();
			// if user is typed in....
				try {
				
				//open file
					File file = new File("customerinfo.txt");
				//read file
					FileReader fr = new FileReader(file);
					System.out.println("step1");
					BufferedReader br = new BufferedReader(fr);
				//the goal is to compare the user input to existing usernames and save that line to an array
					ArrayList<String> cus = new ArrayList<>();
					// br.readLine();
					String str;
					Object customer = new Object();
					while ((str = br.readLine()).equals(u)) {
						String
						String[] sarr = str.split(":");
						String type = sarr[0]; 
						String username = sarr[1]; 
						String password = sarr[2]; 
						String savings = sarr[3];
						String checkings = sarr[4];
						String sbalance = sarr[5];
						String cbalance = sarr[6];
						
						
						System.out.println(type  + " "+ username + " " + password  + " " + savings  + " "+ checkings + " " + sbalance  + " "+ cbalance);
						
						
						//i need to request user input for password and compare it to the item at [1]
						//check next 2 indexes and if wither equals Y display view else display create
						//computation to existing accaunts and update 
						// create new account and place in file 
						
						
						System.out.println("Hello " + username + "\n" + "please enter your password: ");
						Scanner scn = new Scanner (System.in);
						String response = scn.nextLine();
						if()
						
				//File filec = new File("customreinfo.txt");
				
					
				
			}
				}
					catch(IOException exc){
						
					}
	
		}
//		catch (IOException exc) {
//			System.out.println("input invalid");
//		}
//		// set up each option
		
		
		else if(savedin.equals("employee"){
		// if employee is typed in...
		}
		else if (savedin.equals("admin"){
		//if admin is typed in 	
		}
		else {
			System.out.println("invalid input.");
		}
		
	}
	public static void login(File f){
		try

		{
			//after request in put
			Scanner uinput = new Scanner(System.in);
			String s1 = uinput.nextLine();
			File f1 = new File("customerinfo");
			FileReader fr = new FileReader(f1);
			BufferedReader ubr = new BufferedReader(fr);
			ubr.close();
		}

		catch (IOException exc) {
			System.out.println("\nUser name not found.\n");
		}
		
	
//		} finally{
//			System.out.println("something went wrong");
//		}
//catch(IOException exc){
			
}
		

*/