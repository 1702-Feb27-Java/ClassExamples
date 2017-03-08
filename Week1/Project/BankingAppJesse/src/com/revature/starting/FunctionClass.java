package com.revature.starting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class FunctionClass {
	private static BufferedReader br;
	private  static ArrayList<Customer> customer;
	private static ArrayList<Employee>employee ;
	private static ArrayList<Admin>admin ;
	private static boolean signedin;
	private static String signedintype;
	private static int signedinindex;
	private static double checkingbalance;
	private static double savingsbalance;
	private static File file;
	private static BufferedWriter bw;
public FunctionClass(){
	customer = new ArrayList<>();
	employee = new ArrayList<>();
	admin = new ArrayList<>();
	signedin = false;
	signedintype = "";
	signedinindex = -1;
	checkingbalance = 0.01;
	savingsbalance = 0.01;
}

public void shotcaller(){
	
	readfile();
	//displayall();
	opendisplay();
	overwritefile();
	
	//displayallcus();
	//displayall();
}
	public  void readfile() {
		// System.out.println("Please enter your file and extension:");
		try {
			
			file = new File("customerinfo.txt");
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			// br.readLine();
			String str;
			while ((str = br.readLine()) != null) {
			//	System.out.println(str);
				String[] sarr = str.split(":");
				String type = sarr[0];
				String username = ""; 
				String password = ""; 
				String savings = "";
				String checkings = "";
				String sbalance ="";
				String cbalance ="";
				String approved = "";
//				System.out.println(type + username + password + savings );
		
				if(sarr[0].equals("customer")){
					 type = sarr[0];
					 username = sarr[1]; 
					 password = sarr[2]; 
					 savings = sarr[3];
					 checkings = sarr[4];
					 sbalance = sarr[5];
					 cbalance = sarr[6];
					 approved = sarr[7];
				//	System.out.println(type + " "+ username +" "+ password + " "+savings + " "+checkings + " "+sbalance + " "+cbalance);
					customer.add(new Customer(type, username, password, savings, checkings,Double.parseDouble(sbalance) , Double.parseDouble(cbalance), Boolean.parseBoolean(approved)));
				}
				else if(sarr[0].equals("employee")){
					 type = sarr[0];
					 username = sarr[1]; 
					 password = sarr[2]; 
					// System.out.println(type + " "+ username +" "+ password + " "+savings + " "+checkings + " "+sbalance + " "+cbalance);

					 employee.add(new Employee(type, username, password));
				}
				else if(sarr[0].equals("admin")){
					 type = sarr[0];
					 username = sarr[1]; 
					 password = sarr[2]; 
					// System.out.println(type + " "+ username +" "+ password + " "+savings + " "+checkings + " "+sbalance + " "+cbalance);

					 admin.add(new Admin(type, username, password));

			
			} 
			}
		}
		catch (IOException exc) {
			System.out.println(exc);
		}
		finally {
			try{
			br.close();
			}
			catch(IOException io){
				
			}
			}
	}

public static void displayallcus(){
	for(Customer c: customer){
		System.out.println(c.getName());
	}
}
public static void displayall(){
	for(Customer c: customer){
		System.out.println("type: " + c.getType() + " username: " + c.getName() +" password: " + c.getPassword() + " do they have a saving account: " +
							c.getSavings() + " do they have a checkings account: " + c.getCheckings() + " savings balance: " + c.getSbalance() + " checkings balance: "
							+ c.getCbalance() + " is the account approved: " + c.isApproved());
		//public Customer(String type, String username, String password, String savings, String checkings, double sbalance, double cbalance, boolean approved){

	}
	for(Employee e: employee){
		System.out.println("type: " + e.getType() + " username: " + e.getName() +" password: " + e.getPassword());

	}	for(Admin a: admin){
		System.out.println("type: " + a.getType() + " username: " + a.getName() +" password: " + a.getPassword());

	}
}

public static void opendisplay(){
	System.out.println("Welcome to Generic Bank," + "\n" + "enter 1 to sign in." + "\n" +"Enter 2 to sign up." + "\n" +"press 0 to exit.");
		Scanner scan = new Scanner(System.in);
		String savedin = scan.nextLine();
		if(savedin.equals("1")){
			signin();
			
			
		}
		if(savedin.equals("2")){
			signup();
		}
		if(savedin.equals("0")){
			signout();
		}
}
public static void signin(){
	System.out.println("enter your Username: ");
	try{
	Scanner input = new Scanner(System.in);
	String temp = input.nextLine();
	System.out.println("enter your password: ");
	input = new Scanner(System.in);
	String temp2 = input.nextLine();

	for(int i = 0; i<customer.size(); i++){
		if(temp.equals(customer.get(i).getName()) && temp2.equals(customer.get(i).getPassword())){
			signedin = true;
			signedinindex = i;
			signedintype = "customer";
//			checkingbalance = Double.parseDouble(customer.get(6));
//			savingsbalance = Double.parseDouble(customer.get(7));

		System.out.println("thank you for logging in " + customer.get(i).getName());
		}
			
	}
	for(int i = 0; i<employee.size(); i++){
		if(temp.equals(employee.get(i).getName()) && temp2.equals(employee.get(i).getPassword())){
			signedin = true;
			signedinindex = i;
			signedintype = "employee";
			System.out.println("thank you for logging in " + employee.get(i).getName());

		
	}
	}
	for(int i = 0; i<admin.size(); i++){
		if(temp.equals(admin.get(i).getName()) && temp2.equals(admin.get(i).getPassword())){
			signedin = true;
			signedinindex = i;
			signedintype = "admin";
			System.out.println("thank you for logging in " + admin.get(i).getName());

	}
		if(signedin == true){
		displaypermissions();
	}
	
	}}
	catch(Exception e){
		System.out.println("Wrong username or password.");
	}
}

public static void displaypermissions(){
	
	boolean flagexit = false;
	
	if(signedintype.equals("customer")){
		do{
			try{
			
			if(customer.get(signedinindex).isApproved()== false){
				System.out.println("This account has not been approved");
			}
			else{
				System.out.println("if you would like to view your savings account press1:" + "\n"
									+ "if you would like to view your checking balance press 2:");
			Scanner scn = new Scanner(System.in);
			String choice = scn.nextLine();
			
			if(choice.equals("0")){
				signout();
				break;
		}
			
			if (choice.equals("1")&& signedin == true ){
				System.out.println("your current savings balance is: " + savingsbalance + "\n" 
									+ "If you would like to withdraw enter 1: " + "\n" 
									+ "If you would like to make a deposit enter 2: ");
				scn = new Scanner(System.in);
				String choice2 = scn.nextLine();
				double currentbalance = savingsbalance;
				
//				if(choice2.equals("0")){
//						signout();
//						break;
//				}
//				
				
				if (choice2.equals("1")&& signedin == true ){
					System.out.println("How much would you like to withdraw");
					scn = new Scanner(System.in);
					double withdraw = scn.nextDouble();
					customer.get(signedinindex).getSaving().withdraw(Double.parseDouble(choice2));
					System.out.println("Your new balance is " + currentbalance);
				}
				
				if (choice2.equals("2")&& signedin == true ){
					System.out.println("How much would you like to deposite");
					scn = new Scanner(System.in);
					double deposite = scn.nextDouble();
					customer.get(signedinindex).getSaving().withdraw(Double.parseDouble(choice2));
					System.out.println("Your new balance is " + currentbalance);

			}
			
			if (choice.equals("2")&& signedin == true ){
				System.out.println("your current checkings balance is: " + checkingbalance + "\n" + "If you would like to withdraw enter 1: " + "\n" + "If you would like to make a deposit enter 2: ");
				scn = new Scanner(System.in);
				String choice3 = scn.nextLine();
				//customer.get(signedinindex).getChecking().withdraw(Double.parseDouble(choice3));
				//double currentbalance = checkingbalance;
				System.out.println("Your new balance is " + currentbalance);
				
				if(choice3.equals("0")){
					signout();
					break;
				}

				
				if (choice3.equals("1")&& signedin == true ){
					System.out.println("How much would you like to withdraw");
					scn = new Scanner(System.in);
					double withdraw = scn.nextDouble();
					customer.get(signedinindex).getChecking().withdraw(Double.parseDouble(choice3));
					System.out.println("Your new balance is " + currentbalance);


				}
				if (choice3.equals("2" )&& signedin == true ){
					System.out.println("How much would you like to deposite");
					scn = new Scanner(System.in);
					double deposit = scn.nextDouble();
					customer.get(signedinindex).getChecking().deposit(Double.parseDouble(choice3));
					System.out.println("Your new balance is " + currentbalance);
					
			}
			}
			}
			//if (choice.equals(1) )
			System.out.println();
		//	Scanner scn = new Scanner(System.in);
		}}catch(Exception e){
			
			signout();
			System.out.println("i feel a pulse");
			break;
		}
			
		}
		while((flagexit == false)||(signedin == false))
		;
			
		
	}

	
	else if(signedintype.equals("employee")){
		do{try{
			System.out.println("If you would like to view your customers enter 1: " + "\n" + 
								"If you would like to approve new customers enter 2: ");
			Scanner scn = new Scanner(System.in);
			String choice = scn.nextLine();
			if(choice.equals("1")){
				viewCus();
				break;
			}
			if(choice.equals("2")){
				approveCustomer();
				break;
			}
			
			

		}catch(Exception e){
			
		}
			
		}
		while(flagexit == false);
	}
	else if(signedintype.equals("admin")){
		do{try{
			System.out.println("If you would like to view all users enter 1: " + "\n" + 
								"If you would like to edit a user enter 2: " +"\n"
								+ "or enter 0 to exit:");
			Scanner scn = new Scanner(System.in);
			String choice = scn.nextLine();
			
			switch(Integer.parseInt(choice))
			{
			case 0:
				signout();
				flagexit= true;
				break;
			case 1:
				displayall();
				break;
			case 2:
				edit();
				
				break;
			default:
				break;
			}
		}catch(Exception e){
			
		}
			
		}
		while(flagexit == false);
	}
}
public static void signout(){
	signedin = false;
	signedinindex = -1;
	signedintype = "";
}
public static void overwritefile(){
	try{
		file = new File("customerinfo.txt");
		file.createNewFile();
		bw = new BufferedWriter(new FileWriter(file));
		for (Customer c: customer){
			bw.write(c.getType() + ":" + c.getUsername() + ":" + c.getPassword() + ":" +c.getSavings() + ":" + c.getCheckings() + ":" + c.getSbalance() + ":" + c.getCbalance() + ":" + c.isApproved() + "\n");

			
		}
		for (Employee e: employee){
			bw.write(e.getType() + ":" + e.getName() + ":" + e.getPassword() + "\n");
			
		}
		for (Admin a: admin){
			bw.write(a.getType() + ":" + a.getName() + ":" + a.getPassword() + "\n");
		}
		
	}
	catch(Exception e){
		
	}
	finally{try{
		bw.close();

	}
	catch(IOException io){
		
	}
	}
}
public static void approveCustomer(){
	for(int i=0; i<customer.size(); i++){
		if(customer.get(i).isApproved() == false){
			System.out.println("Account for " + customer.get(i).getName() + " is not approved. would you like to approve them? if yes enter 1." + "\n" + "if not enter 2");
			Scanner scn = new Scanner(System.in);
			String choice = scn.nextLine();
			if (choice == "1"){
				customer.get(i).setApproved(true);
				System.out.println("Customer approved, thank you.");
				
			}
		}
	}
}
public static void viewCus(){
for (Customer c : customer){
	System.out.println("User name: " + c.getUsername() + " " +"savings balance: " + c.getSaving().getBalance() + " Checkings balance: " + c.getChecking().getBalance() + "\n");
}
}
public static void viewemp(){
for (Employee e : employee){
	System.out.println("User name: " + e.getName());
}
}
public static void signup(){
	System.out.println("please enter a username");
	Scanner scn1 = new Scanner(System.in);
	String newuser = scn1.nextLine();
	System.out.println("please enter a password ");
	Scanner scn2 = new Scanner(System.in);
	String newpassword = scn2.nextLine();
	customer.add(new Customer("customer", newuser, newpassword, "N", "N", 0, 0, false));

	
	
	
}
public static void edit(){
	System.out.println("which user would you like to edit?");
	displayall();
	Scanner scn = new Scanner(System.in);
	String usered = scn.nextLine();
	{
		for(int i =0; i<customer.size();i++){
			if(customer.get(i).getName().equals(usered)){
				
				System.out.println("please enter a type (customer, employee, or admin)");
				Scanner scn1 = new Scanner(System.in);
				String newtype = scn1.nextLine();
				
				System.out.println("please enter a username ");
				Scanner scn2 = new Scanner(System.in);
				String newuser = scn2.nextLine();
				
				System.out.println("please enter a password ");
				Scanner scn3 = new Scanner(System.in);
				String newpassword = scn3.nextLine();
				
				System.out.println("does this user have a savings account (Y or N) ");
				Scanner scn4 = new Scanner(System.in);
				String newacc = scn4.nextLine();
				
				System.out.println("does this user have a checkings account (Y or N) ");
				Scanner scn5 = new Scanner(System.in);
				String newchk = scn5.nextLine();
				
				System.out.println("set their new savings balance");
				Scanner scn6 = new Scanner(System.in);
				double sbal = Double.parseDouble(scn6.nextLine());
				
				System.out.println("set their new checkings balance");
				Scanner scn7 = new Scanner(System.in);
				double cbal = Double.parseDouble(scn7.nextLine());
				
				System.out.println("is this user approved? (true or false)");
				Scanner scn8 = new Scanner(System.in);
				boolean app = Boolean.parseBoolean(scn8.nextLine());
				
				customer.set(i,new Customer(newtype, newuser, newpassword, newacc, newchk, sbal, cbal, app));
			}
		}
		
		for(int i =0; i<employee.size();i++){
			if(employee.get(i).getName().equals(usered)){
				System.out.println("please enter a type (customer, employee, or admin)");
				Scanner scn1 = new Scanner(System.in);
				String newtype = scn1.nextLine();
				
				System.out.println("please enter a username ");
				Scanner scn2 = new Scanner(System.in);
				String newuser = scn2.nextLine();
				
				System.out.println("please enter a password ");
				Scanner scn3 = new Scanner(System.in);
				String newpassword = scn3.nextLine();
				
				employee.set(i,new Employee(newtype, newuser, newpassword));
			}
			}

		}	
		for(int i =0; i<admin.size();i++){
			if(admin.get(i).getName().equals(usered)){
				System.out.println("please enter a type (customer, employee, or admin)");
				Scanner scn1 = new Scanner(System.in);
				String newtype = scn1.nextLine();
				
				System.out.println("please enter a username ");
				Scanner scn2 = new Scanner(System.in);
				String newuser = scn2.nextLine();
				
				System.out.println("please enter a password ");
				Scanner scn3 = new Scanner(System.in);
				String newpassword = scn3.nextLine();
				
				admin.set(i,new Admin(newtype, newuser, newpassword));
				//System.out.println(admin.get(i).getType() + admin.get(i).getName() + admin.get(i).getPassword());
				//System.out.println(admin.get(1).getType());

			}
		}
	}
}


