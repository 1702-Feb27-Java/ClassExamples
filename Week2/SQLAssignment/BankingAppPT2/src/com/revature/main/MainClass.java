package com.revature.main;

import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.Users;
import java.util.Scanner;
import java.util.Random;

public class MainClass {
	
	static AccountDAOImpl AccDao = new AccountDAOImpl();
	static UserDAOImpl UsDao = new UserDAOImpl();
	static Scanner scan = new Scanner(System.in);
	static Users this_user = new Users();
	static Random rand = new Random(); 
	
	
	
	
	public static void main(String[] args) {
		
		StartProgram();
		
	}
	
	
	public static void StartProgram() {
		
			System.out.println("Welcome to Revature Bank");
			System.out.println("Please sign up or log");
			System.out.println("1: Sign up");
			System.out.println("2: Log in");
			System.out.print("Enter your choice: ");
		
			int read = scan.nextInt();
		
			switch( read ) {
		
			case 1: SignUp();
					break;
		
			case 2: LogIn();
					break;
		
			default:
				System.out.println("Incorrect choice please try again");
			}
		
	}
	
	static void SignUp() {
		
		System.out.print("Enter your first name: ");
		String name = scan.next();
		this_user.setFirstname(name);
		
		System.out.print("Enter your last name: ");
		String last = scan.next();
		this_user.setLastname(last);
		
		System.out.print("Enter a unique user name: ");
		String uname = scan.next();
		this_user.setUsername(uname);
		
		System.out.print("Enter a unique Password: ");
		String pass = scan.next();
		this_user.setPassword(pass);
		
		int id_number = rand.nextInt(1000) + 1;
		this_user.setUser_id(id_number);
		
		System.out.println("Are you an admin, employee, or customer? ");
		System.out.print("Enter 1 for admin, 2 for employee, and 3 for customer: ");
		int user_num = scan.nextInt();
		
		if ( user_num == 1)
			this_user.setRole_id(user_num);
		else if ( user_num == 2 )
			this_user.setRole_id(user_num);
		else
			this_user.setRole_id(user_num);
			AccDao.applyForAccount(this_user);
		
		UsDao.UserSignUp(this_user);
		
	}
	
	static void LogIn() {
		
		System.out.println("Please enter your credentials.");
		System.out.print("Enter your user name: ");
		String name = scan.next();
		
		System.out.print("Enter your password: ");
		String password = scan.next();
		
		int x = UsDao.ValidateUser(name, password);
		
		
		if ( x == 0 )
			System.out.println("Invlaid user!!!");
		else if ( x == 1 )
			System.out.println("Welcome Admin");
			//call admin menu
		else if ( x == 2 )
			System.out.println("Welcome Employee");
			//call employee menu
		else
			System.out.println("Welcome Customer");
			this_user.setUsername(name);
			this_user.setPassword(password);
			CustomerMenu();
			
		
	}
	
	static void CustomerMenu() {
		
		Users x = AccDao.ViewAccountInfo(this_user);
		System.out.println(x.getFirstname() + " " +  x.getLastname() + " " + x.getUsername());
		//System.out.println(x.a.getBalance() + " ");
		
	}
	
	

}
