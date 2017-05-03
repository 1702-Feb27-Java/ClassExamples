package com.revature.bankapp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class LogInMethods {

	private static File userPass = new File("BankAccount.txt");
	
	public static boolean checkUsername(String username, ArrayList<UserAccount> userArray){
		
		boolean userTaken = false;
		for(int i = 0; i < userArray.size(); i++){
			if(userArray.get(i).getUsername().equals(username)){
				userTaken = true;
			}
		}
		return userTaken;

		
	}
	
	public static boolean checkPassword(String username, String password, ArrayList<UserAccount> userArray){
		boolean hasPassword = false;
		
		for(int i = 0; i < userArray.size(); i++){
			if(userArray.get(i).getUsername().equalsIgnoreCase(username)){
				if(userArray.get(i).getPassword().equals(password)){
					hasPassword =  true;
				}
			}
		}
		return hasPassword;
	}
	
}
