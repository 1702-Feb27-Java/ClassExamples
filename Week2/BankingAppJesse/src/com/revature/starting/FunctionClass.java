package com.revature.starting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.rev.dao.*;

import com.rev.pojo.UserInformation;

public class FunctionClass {
	private static BufferedReader br;
	private static ArrayList<Customer> customer;
	private static ArrayList<Employee> employee;
	private static ArrayList<Admin> admin;
	private static boolean signedin;
	private static String signedintype;
	private static int signedinindex;
	private static double checkingbalance;
	private static double savingsbalance;
	private static DAOimpl dao = new DAOimpl();
	private static ArrayList<UserInformation> userz;

	public FunctionClass(){
		customer  =new ArrayList<Customer>();
		employee = new ArrayList<>();
		admin = new ArrayList<>();
		signedin = false;
		signedintype = "";
		signedinindex = -1;
		checkingbalance = 0.0;
		savingsbalance = 0.0;
	}

	public static void shotcaller() {

		ArrayConvert();
		opendisplay();

	}

	public static void ArrayConvert() {
		// splitting the array i pulled in using the admin method to create 3
		// arrays to it fits the scheme of the previous
		// iteration of the project

		try {
			userz = dao.getAllInfo();
			for (UserInformation u : userz) {

				if (u.getRid().equals("3")) { 
					customer.add(new Customer(u.getRole(), u.getUn(), u.getPw(), u.getId()));
					System.out.println(customer);
				}
				if (u.getRid().equals("2")) {
					employee.add(new Employee(u.getRole(), u.getUn(), u.getPw(), u.getId()));
					System.out.println(employee);

				}
				if (u.getRid().equals("1")) {
					admin.add(new Admin(u.getRole(), u.getUn(), u.getPw(), u.getId()));
					System.out.println(admin);
				}
			}

		} catch (Exception e) {

		}
	}

	public static void displayallcus() {
		for (Customer c : customer) {
			System.out.println(c.getName());
		}
	}

	public static void displayall() {
		for (Customer c : customer) {
			System.out.println("type: " + c.getType() + " username: " + c.getName() + " password: " + c.getPassword()
					+ " do they have a saving account: " + c.getSavings() + " do they have a checkings account: "
					+ c.getCheckings() + " savings balance: " + c.getSbalance() + " checkings balance: "
					+ c.getCbalance() + " is the account approved: " + c.isApproved());
			// public Customer(String type, String username, String password,
			// String savings, String checkings, double sbalance, double
			// cbalance, boolean approved){

		}
		for (Employee e : employee) {
			System.out.println("type: " + e.getType() + " username: " + e.getName() + " password: " + e.getPassword());

		}
		for (Admin a : admin) {
			System.out.println("type: " + a.getType() + " username: " + a.getName() + " password: " + a.getPassword());

		}
	}

	public static void opendisplay() {
		System.out.println("Welcome to Generic Bank," + "\n" + "enter 1 to sign in." + "\n" + "Enter 2 to sign up."
				+ "\n" + "press 0 to exit.");
		Scanner scan = new Scanner(System.in);
		String savedin = scan.nextLine();
		if (savedin.equals("1")) {
			signin();

		}
		if (savedin.equals("2")) {
			signup();
		}
		if (savedin.equals("0")) {
			signout();
		}
	}

	public static void signin() {
		System.out.println("enter your Username: ");
		try {
			Scanner input = new Scanner(System.in);
			String temp = input.nextLine();
			System.out.println("enter your password: ");
			input = new Scanner(System.in);
			String temp2 = input.nextLine();
			for (int i = 0; i < customer.size(); i++) {
				if (temp.equals(customer.get(i).getName()) && temp2.equals(customer.get(i).getPassword())) {
					signedin = true;
					signedinindex = i;
					signedintype = "customer";
					// checkingbalance = Double.parseDouble(customer.get(6));
					// savingsbalance = Double.parseDouble(customer.get(7));

					System.out.println("thank you for logging in " + customer.get(i).getName());
				}

			}
			for (int i = 0; i < employee.size(); i++) {
				if (temp.equals(employee.get(i).getName()) && temp2.equals(employee.get(i).getPassword())) {
					signedin = true;
					signedinindex = i;
					signedintype = "employee";
					System.out.println("thank you for logging in " + employee.get(i).getName());

				}
			}
			for (int i = 0; i < admin.size(); i++) {
				if (temp.equals(admin.get(i).getName()) && temp2.equals(admin.get(i).getPassword())) {
					signedin = true;
					signedinindex = i;
					signedintype = "admin";
					System.out.println("thank you for logging in " + admin.get(i).getName());

				}
				if (signedin == true) {
					displaypermissions();
				}

			}
		} catch (Exception e) {
			System.out.println("Wrong username or password.");
		}
	}

	public static void displaypermissions() {

		boolean flagexit = false;

		if (signedintype.equals("customer")) {
			do {
				try {

						if (customer.get(signedinindex).isApproved() == true) {
					System.out.println("This account has not been approved");
				} 
						else
				 {
						System.out.println("if you would like to view your savings account press1:" + "\n"
								+ "if you would like to view your checking balance press 2:");
						Scanner scn = new Scanner(System.in);
						String choice = scn.nextLine();

						if (choice.equals("0")) {
							signout();
							break;
						}

						if (choice.equals("1") && signedin == true) {
							System.out.println("your current savings balance is: " + savingsbalance + "\n"
									+ "If you would like to withdraw enter 1: " + "\n"
									+ "If you would like to make a deposit enter 2: ");
							scn = new Scanner(System.in);
							String choice2 = scn.nextLine();
							double currentbalance = savingsbalance;

//							 if(choice2.equals("0")){
//							 signout();
//							 break;
//							 }
//							

							if (choice2.equals("1") && signedin == true) {
								System.out.println("How much would you like to withdraw");
								scn = new Scanner(System.in);
								double withdraw = scn.nextDouble();
								customer.get(signedinindex).getSaving().withdraw(Double.parseDouble(choice2));
								System.out.println("Your new balance is " + withdraw);
							}

							if (choice2.equals("2") && signedin == true) {
								System.out.println("How much would you like to deposite");
								scn = new Scanner(System.in);
								double deposite = scn.nextDouble();
								customer.get(signedinindex).getSaving().deposit(Double.parseDouble(choice2));
								System.out.println("Your new balance is " + deposite);

							}

							if (choice.equals("2") && signedin == true) {
								System.out.println("your current checkings balance is: " + checkingbalance + "\n"
										+ "If you would like to withdraw enter 1: " + "\n"
										+ "If you would like to make a deposit enter 2: ");
								scn = new Scanner(System.in);
								String choice3 = scn.nextLine();
								 customer.get(signedinindex).getChecking().withdraw(Double.parseDouble(choice3));
								// double currentbalance = checkingbalance;
								System.out.println("Your new balance is " + currentbalance);

								if (choice3.equals("0")) {
									signout();
									break;
								}

								if (choice3.equals("1") && signedin == true) {
									System.out.println("How much would you like to withdraw");
									scn = new Scanner(System.in);
									double withdraw = scn.nextDouble();
									customer.get(signedinindex).getChecking().withdraw(Double.parseDouble(choice3));
									System.out.println("Your new balance is " + currentbalance);

								}
								if (choice3.equals("2") && signedin == true) {
									System.out.println("How much would you like to deposite");
									scn = new Scanner(System.in);
									double deposit = scn.nextDouble();
									customer.get(signedinindex).getChecking().deposit(Double.parseDouble(choice3));
									System.out.println("Your new balance is " + currentbalance);

								}
							}
						}
						System.out.println();
					}
				} catch (Exception e) {

					signout();
					System.out.println("i feel a pulse");
					break;
				}

			} while ((flagexit == false) || (signedin == false));

		}

		else if (signedintype.equals("employee")) {
			do {
				try {
					System.out.println("If you would like to view your customers enter 1: " + "\n"
							+ "If you would like to approve new customers enter 2: ");
					Scanner scn = new Scanner(System.in);
					String choice = scn.nextLine();
					if (choice.equals("1")) {
						//////////////////////// viewCus();	//////////////////////// /////////////////////
						break;
					}
					if (choice.equals("2")) {
						approveCustomer();
						break;
					}

				} catch (Exception e) {

				}

			} while (flagexit == false);
		} else if (signedintype.equals("admin")) {
			do {
				try {
					System.out.println("If you would like to view all users enter 1: " + "\n"
							+ "If you would like to edit a user enter 2: " + "\n" + "or enter 0 to exit:");
					Scanner scn = new Scanner(System.in);
					String choice = scn.nextLine();

					switch (Integer.parseInt(choice)) {
					case 0:
						signout();
						flagexit = true;
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
				} catch (Exception e) {

				}

			} while (flagexit == false);
		}
	}

	public static void signout() {
		signedin = false;
		signedinindex = -1;
		signedintype = "";
	}

	public static void approveCustomer() {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).isApproved() == false) {
				System.out.println("Account for " + customer.get(i).getName()
						+ " is not approved. would you like to approve them? if yes enter 1." + "\n"
						+ "if not enter 2");
				Scanner scn = new Scanner(System.in);
				String choice = scn.nextLine();
				if (choice == "1") {
					customer.get(i).setApproved(true);
					System.out.println("Customer approved, thank you.");

				} else {
					signout();
				}
			}
		}
	}

	public static void viewCus() {
		for (Customer c : customer) {
			System.out.println("User name: " + c.getUsername() + " " + "savings balance: " + c.getSaving().getBalance()
					+ " Checkings balance: " + c.getChecking().getBalance() + "\n");
		}
	}

	public static void viewemp() {
		for (Employee e : employee) {
			System.out.println("User name: " + e.getName());
		}
	}

	public static void signup() { 
		System.out.println("please enter a username");
		Scanner scn1 = new Scanner(System.in);
		String newuser = scn1.nextLine();
		System.out.println("please enter a password ");
		Scanner scn2 = new Scanner(System.in);
		String newpassword = scn2.nextLine();
		
		dao.insertNewUser(newuser, newpassword);
	

	}

	public static void edit() { 
		System.out.println("which user would you like to edit?");
		displayall();
		Scanner scn = new Scanner(System.in);
		String usered = scn.nextLine();
		{
			for (int i = 0; i < customer.size(); i++) {
				if (customer.get(i).getName().equals(usered)) {

					System.out.println("please enter a type (customer, employee, or admin)");
					Scanner scn1 = new Scanner(System.in);
					String newtype = scn1.nextLine();

					System.out.println("please enter a username ");
					Scanner scn2 = new Scanner(System.in);
					String newuser = scn2.nextLine();

					System.out.println("please enter a password ");
					Scanner scn3 = new Scanner(System.in);
					String newpassword = scn3.nextLine();
					
//					System.out.println("please enter the user id ");
//					Scanner scn4 = new Scanner(System.in);
//					String newid = scn4.nextLine();
					
			int oldid =customer.get(i).getId(); 
			dao.updateUser( newuser, newpassword, Integer.parseInt(newtype), oldid);
			
				}
			}

			for (int i = 0; i < employee.size(); i++) {
				if (employee.get(i).getName().equals(usered)) {
					System.out.println("please enter a type (customer, employee, or admin)");
					Scanner scn1 = new Scanner(System.in);
					String newtype = scn1.nextLine();

					System.out.println("please enter a username ");
					Scanner scn2 = new Scanner(System.in);
					String newuser = scn2.nextLine();

					System.out.println("please enter a password ");
					Scanner scn3 = new Scanner(System.in);
					String newpassword = scn3.nextLine();
					
//					System.out.println("please enter the user id ");
//					Scanner scn4 = new Scanner(System.in);
//					String newid = scn4.nextLine();
					
					
					int oldid =employee.get(i).getId(); 

					dao.updateUser(newuser, newpassword, Integer.parseInt(newtype), oldid);

				}
				
			}

		}
		for (int i = 0; i < admin.size(); i++) {
			if (admin.get(i).getName().equals(usered)) {
				System.out.println("please enter a type id (3 for customer, 2 for employee, or 1 for admin)");
				Scanner scn1 = new Scanner(System.in);
				String newtype = scn1.nextLine();

				System.out.println("please enter a username ");
				Scanner scn2 = new Scanner(System.in);
				String newuser = scn2.nextLine();

				System.out.println("please enter a password ");
				Scanner scn3 = new Scanner(System.in);
				String newpassword = scn3.nextLine();
				
//				System.out.println("please enter the user id ");
//				Scanner scn4 = new Scanner(System.in);
//				String newid = scn4.nextLine();
//				Integer.parseInt(newid);

				int oldid =admin.get(i).getId(); 

				dao.updateUser(newuser, newpassword, Integer.parseInt(newtype), oldid);
				

			}
		}
	}


	}

