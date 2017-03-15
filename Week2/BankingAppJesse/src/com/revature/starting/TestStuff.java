package com.revature.starting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TestStuff {

	public static void main(String[] args) throws IOException {
		login();

	}

	public static void login() {
		System.out.println("welcome to Generic Bank. Are you an admin, employee, or user?");

		ArrayList<bank> fullfile = new ArrayList<bank>();
		String str;
		try {
			File file = new File("customerinfo.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// while (br.readLine()!=null) {
			// String[] firstarr = str.split("\n");
			// String
			//
			while (true) {

				str = br.readLine();
				try {

					String[] sarr = str.split(":");
					String type = sarr[0];
					String username = sarr[1];
					String password = sarr[2];
					String sacc = sarr[3];
					String cacc = sarr[4];
					String sbal = sarr[5];
					String cbal = sarr[6];

					System.out.println(sarr[0] + sarr[1]);
				} catch (Exception e) {
					System.out.println("Somethinh");
					break;
				}

				/*
				 * 
				 * //what type Scanner uinput = new Scanner(System.in); String
				 * savedin = uinput.nextLine(); //Array sarr = new Array();
				 * 
				 * ///////bunch of if statements depending on type////////////
				 * if (savedin.equals("user")) { //enter customer info file
				 * System.out.println("what is your user name?"); Scanner u =
				 * new Scanner(System.in); /////save input for user
				 * name/////////////////////////// String step2 = u.nextLine();
				 * try{ File file = new File("customerinfo.txt"); FileReader fr
				 * = new FileReader(file); BufferedReader br = new
				 * BufferedReader(fr); str = br.readLine();
				 * 
				 * //System.out.println("lrjfn"); //cycle through file to
				 * identify username while (br.readLine()!=null) { //String
				 * String[] sarr = str.split(":"); String type = sarr[0]; String
				 * username = sarr[1]; String password = sarr[2]; String savings
				 * = sarr[3]; String checkings = sarr[4]; String sbalance =
				 * sarr[5]; String cbalance = sarr[6]; if
				 * (sarr[1].equals("jbar")){ //save remaining accs and balances
				 * break; }
				 * 
				 * String[] sbal= [5];
				 * 
				 * 
				 * System.out.println(type + " "+ username + " " + password +
				 * " " + savings + " "+ checkings + " " + sbalance + " "+
				 * cbalance);
				 * 
				 * } //user System.out.println(type + " "+ username + " " +
				 * password + " " + savings + " "+ checkings + " " + sbalance +
				 * " "+ cbalance); } catch(IOException exc){
				 * 
				 * } System.out.println("youdidit!!");
				 * 
				 * }
				 * 
				 * System.out.println("youdidit!!"); if
				 * (savedin.equals("employee")) { //enter employee info file
				 * 
				 * 
				 * 
				 * System.out.println("what is your user name?"); Scanner u =
				 * new Scanner(System.in); String step2 = u.nextLine(); }
				 * 
				 * 
				 * 
				 * if (savedin.equals("admin")) { //enter admin info file
				 * 
				 * 
				 * 
				 * System.out.println("what is your user name?"); Scanner u =
				 * new Scanner(System.in); String step2 = u.nextLine(); }
				 * 
				 * else { //follow up if invalid
				 * System.out.println("invalid input"); }
				 */

			}
		} catch (IOException plp) {

		}

	}
}
