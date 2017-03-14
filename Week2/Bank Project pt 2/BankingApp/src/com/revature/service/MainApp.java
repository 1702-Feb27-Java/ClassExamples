package com.revature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainApp {

	// this is what our program will start with
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// show the menu and give functionality to the menu
		MenuClass.showMainMenu();
		MainMenu.functionality();
	}
}
