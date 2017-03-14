package com.projectone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.project1.util.ConnectionUtil;
public class BankDriver implements SignUp, Approve {
	private Bank bankUpdate;
	private String tmp;
	private String signedInUser;
	private String signedInPass;
	private boolean signedIn;
	private int indexSignedIn;
	private String userRight;
	private ArrayList<Bank> b;
	private Scanner userInput;
	private File file;
	private BufferedWriter bw;
	private BufferedReader rw;
	private static int user_id;
	private static int account_id;
	private static int role_id;
	private String updateType;
	private static int usermax;
	
	public void UpdateApproveSQL(int i)
	{
		
		try
		{
			
		Connection cont = ConnectionUtil.getConnection();
		String sql= "Update accounts set status_id = " + 2 + " where account_id = "+b.get(i).getAcctid() ;
		//System.out.println("TRYING TO APPROVE ACCOUNT NUMBER :" + b.get(i).getAcctid());
		Statement  statement = cont.createStatement();
		statement.executeUpdate(sql);
		cont.close();
		statement.close();
		}
		catch(Exception e)
		{
			
		System.out.println("failed :" +  e.getMessage());
		}
	}
	public void ApproveAccounts() {
		boolean flagExit = false;

		do {
			try {
				System.out.println("||Displaying Unapproved Accounts||");

				userInput = sScannerRefresher();

				for (int i = 0; i < this.b.size(); i++) {
					if (this.b.get(i).getAccountType() == "Customer") {
						if (this.b.get(i).getCustomer().issApproved() == false) {
							System.out.println("------------------");
							System.out.println("Account Num : #" + this.b.get(i).getCustomer().getAccountNum()
									+ "\nFirst Name " + this.b.get(i).getCustomer().getsFName() + "\nLast Name "
									+ this.b.get(i).getCustomer().getsLName() + "\nBalance: " + this.b.get(i).getMoney()
									+ "\nAccount Type: " + this.b.get(i).getCustomer().getAccountCheckOrSavings()
									+ "\nApproved Status :" + this.b.get(i).getCustomer().issApproved());
							System.out.println("------------------");
							
						}
					}
				}

				System.out.println("Enter Acount Number You Wish to approve or 0 to Exit Prompt");
				System.out.print("-> ");
				int input = userInput.nextInt();
				if (input == 0) {
					break;
				}

				for (int i = 0; i < this.b.size(); i++) {
					if (this.b.get(i).getAccountType() == "Customer") {
						if (this.b.get(i).getCustomer().getAccountNum() == input) {
							System.out.println("Customer Approved");
							this.b.get(i).getCustomer().setsApproved(true);
							UpdateApproveSQL(i);
							
						}
					}
				}
			}

			catch (Exception e) {

				System.out.println("Somthing Wrong");

			}
		} while (flagExit = false);

	}
	
	public void welcomeMenu() {
		boolean flagExit = false;

		do {
			if (signedIn == true) {

				break;
			}
			try {
				userInput = sScannerRefresher();

				System.out.println("Enter 0 to Exit Prompt");

				System.out.println("Enter 1 to Sign In");

				System.out.println("Enter 2 to Sign Up As New Customer and Apply For Checking or Savings");
				System.out.print("-> ");
				int choice = userInput.nextInt();
				switch (choice) {
				case 0:
					flagExit = true;
					break;

				case 1:
					signIn();

					break;
				case 2:
					signUpCustomer();
					break;

				}

			} catch (Exception e) {
				System.out.println("Invalid Choice Please Try Again");

			}
		} while (flagExit == false);
	}

	public void runner() {

		this.b = new ArrayList<>();

	///	ReadContents();
		SQLReadContents();
		
		tmp = "0";
		while (true) {
			
			System.out.println("--------------------------");
			System.out.println("*WELCOME TO REVATURE BANK! *");
			System.out.println("---------------------------");
			if (tmp.equals("1")) {

				break;
			}
			welcomeMenu();
		
			if (signedIn == true) {

				permissionSelctionDisplay();
			}

			try {
				userInput = sScannerRefresher();

				System.out.print("Do you want to exit program?\nEnter 1 for Yes\nEnter 2 for No\n->");
				tmp = userInput.nextLine();

				userInput = sScannerRefresher();
				signedIn = false;
			} catch (Exception e) {
			}
		}

		//WriteContents();
		System.out.println("||Exiting Program||\n-GoodBye");
	}
	
	public void WriteContents() {
		try {
			file = new File("Data.txt");

			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			//public Customer(String sFName, String sLName, int accountNum,String user , String pass,boolean sApproved,String AccountCheckOrSavings)
			
			// public Customer(String sFName, String sLName, int
			// accountNum,String user , String pass,boolean sApproved)
			/*
			 * bw.write("Admin:David:Lund:DL001:Testing\n");
			 * bw.write("Employee:David:Lund:DL002:Testing\n"); bw.write(
			 * bw.write("Customer:David:Lund:1:DL003:Testing:false:checking:3000\n");
			 */
			  for (Bank bb : this.b) {
					if (bb.getAccountType().equals("Admin")) {
                        
						bw.write(bb.getAccountType() + ":" + bb.getAdmin().getFirst() + ":" + bb.getAdmin().getLast() + ":"
								+ bb.getAdmin().getsUserName() + ":" + bb.getAdmin().getsPassword() + "\n");
					}
					if (bb.getAccountType().equals("Employee")) {
						bw.write(bb.getAccountType() + ":" + bb.getEmployee().getFirst() + ":" + bb.getEmployee().getLast()
								+ ":" + bb.getEmployee().getsUserName() + ":" + bb.getEmployee().getsPassword() + "\n");

					}
					if (bb.getAccountType().equals("Customer")) {
						bw.write(bb.getAccountType() + ":" + bb.getCustomer().getsFName() + ":"
								+ bb.getCustomer().getsLName() + ":" + bb.getCustomer().getAccountNum() + ":"
								+ bb.getCustomer().getsUserName() + ":" + bb.getCustomer().getsPassword() + ":"
								+ bb.getCustomer().issApproved() + ":" + bb.getCustomer().getAccountCheckOrSavings() + ":"
								+ bb.getMoney() + "\n");

					}
				}
		

		} catch (Exception e) {

		} finally {

			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}

	}
	
	public void ReadContents() {

		b = new ArrayList<Bank>();
		
		BufferedReader br = null;
		boolean flagquit = false;
		try {
			// catch error if no data.txt exists
			br = new BufferedReader(new FileReader("Data.txt"));
		} catch (Exception eee) {
			System.out.println("quiting \n" + eee.getMessage());
			// flagquit=true;
		}
		if (flagquit != true) {

			// ArrayList<Person> alPerson= new ArrayList();
			try {
				while (true) {
					// System.out.println("In");
					String str = "";
					str = br.readLine();
					if (str.equals(null))
						break;
					/// System.out.println(str);
					String[] strarr = str.split(":");

					if (strarr[0].equals("Employee")) {
						this.b.add(new Bank(new Employee(strarr[1], strarr[2], strarr[3], strarr[4])));

					} else if (strarr[0].equals("Customer")) {
						// public Customer(String sFName, String sLName, int
						// accountNum,String user , String pass,boolean
						// sApproved)
						this.b.add(new Bank(new Customer(strarr[1], strarr[2], Integer.parseInt(strarr[3]), strarr[4],
								strarr[5], Boolean.parseBoolean(strarr[6]), strarr[7]), Double.parseDouble(strarr[8])));
					} else if (strarr[0].equals("Admin")) {
						this.b.add(new Bank(new Admin(strarr[1], strarr[2], strarr[3], strarr[4])));
					}
					// alPerson.add(new
					// Person(strarr[0],strarr[1],Integer.parseInt(strarr[2]),strarr[3]));

				}

			} catch (Exception e) {

			} finally {
				try {

					br.close();
				} catch (Exception ee) {

				}

			}
		}

	}
	
	
	
	public void UpdateMoneySQL()
	{
		
		
		try{
			
		double mon = b.get(indexSignedIn).getMoney();
		Connection cont = ConnectionUtil.getConnection();
		String sql= "Update Accounts set balance = '" + mon + "' where ACCOUNT_ID = " + b.get(indexSignedIn).getAcctid();
		Statement  statement = cont.createStatement();
		statement.executeUpdate(sql);
		}
		catch(Exception e)
		{
			
			
		}
		
		
		
	}
	public void UpdateFirstSQL(int i, String temp)
	{
		
		
		try{
			
			
			
			
		if(b.get(i).getAccountType().equals("Customer"))
		{
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set First_Name = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if(b.get(i).getAccountType().equals("Employee"))
		{
	
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set First_Name = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if (b.get(i).getAccountType().equals("Admin"))
		{
			
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set First_Name = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
		}
			

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		
		
	}
	public void UpdateLastSQL(int i, String temp )
	{
		
		
		try{
			
			
			
			
		if(b.get(i).getAccountType().equals("Customer"))
		{
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set Last_Name = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if(b.get(i).getAccountType().equals("Employee"))
		{
	
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set Last_Name = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if (b.get(i).getAccountType().equals("Admin"))
		{
			
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set Last_Name = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
		}
			

		}
		catch(Exception e)
		{
			
			
		}
		
		
		
	}
	public void UpdateUserSQL(int i, String temp)
	{
		
		
		
		try{
			
			
			
			
		if(b.get(i).getAccountType().equals("Customer"))
		{
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set username = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if(b.get(i).getAccountType().equals("Employee"))
		{
	
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set username = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if (b.get(i).getAccountType().equals("Admin"))
		{
			
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set username = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
		}
			

		}
		catch(Exception e)
		{
			
			
		}
		
		
	}
	
	
	public void UpdatePasswordSQL(int i, String temp)
	{
		
		
		
		try{
			
			
			
			
		if(b.get(i).getAccountType().equals("Customer"))
		{
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set password = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if(b.get(i).getAccountType().equals("Employee"))
		{
	
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set password = '" + temp + "' where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			
		}
		else if (b.get(i).getAccountType().equals("Admin"))
		{
			
			
			Connection cont = ConnectionUtil.getConnection();
			String sql= "Update users set password = '" + temp + "'where user_id = "  + "'"+ b.get(i).getAcctid()+"'";
			Statement  statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
		}
			

		}
		catch(Exception e)
		{
			
			
		}
		
	}
	public void SQLReadContents() {

		b = new ArrayList<Bank>();
		
	
		
		
	
			try {
				
			
				String sql = "SELECT users.USER_ID, users.FIRST_NAME,users.last_name,users.PASSword,Role.Role,Role.role_id,users.role_id from  role inner join users on users.ROLE_ID = Role.ROLE_ID ";
				Connection cont = ConnectionUtil.getConnection();
				PreparedStatement ps = cont.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
				//	System.out.println("word");
					 int temp = rs.getInt(1);
					// System.out.println(temp);
					int num = this.usermax;
				
					
				
				}
				
				cont.close();
				
				rs.close();
				 sql = "SELECT users.USER_ID, users.FIRST_NAME,users.last_name,users.PASSword,users.username,Role.Role,Role.role_id,users.role_id from  role inner join users on users.ROLE_ID = Role.ROLE_ID ";
				 cont =  ConnectionUtil.getConnection();
				 ps = cont.prepareStatement(sql);
				 rs = ps.executeQuery();
			
				while (rs.next()) {
					int count =0;
					int userid = rs.getInt(1);
					String first= rs.getString(2);
					String last= rs.getString(3);
					String password = rs.getString(4);
					String username = rs.getString(5);
					String role = rs.getString(6);
					int accountNum = userid;
					
					BankDriver.usermax+=1;
					
				//System.out.println( role + " ");

					if (role.equals("Employee")) {
						
				
					//	System.out.println("working Employee");
						this.b.add(new Bank(new Employee(first, last, username, password)));
						
					

					} else if (role.equals("Customer")) {
						count++;
						//System.out.println("working Customer Account id :" + userid);
						// public Customer(String sFName, String sLName, int
						// accountNum,String user , String pass,boolean
						// sApproved)
						
						this.b.add(new Bank(new Customer(first, last, accountNum, username,
								password),  0,userid,accountNum));
					} else if (role.equals("Admin")) {
						count++;
						//System.out.println("working Admin");
						this.b.add(new Bank(new Admin(first, last, username, password)));
				
					}

				}// alPerson.add(new
			
					// Person(strarr[0],strarr[1],Integer.parseInt(strarr[2]),strarr[3]));
cont.close();
rs.close();
					 sql = "SELECT Accounts.account_id,status.STATUS,accounts.BALANCE from accounts inner join STATUS on accounts.STATUS_ID = STATUS.STATUS_ID ";
					 cont = ConnectionUtil.getConnection();
					 ps = cont.prepareStatement(sql);
					 rs = ps.executeQuery();
					 while(rs.next())
					 {
						 
						int id = rs.getInt(1);						
						String  status = rs.getString(2);
						double balance  = rs.getDouble(3);
				for(int i = 0 ; i <this.b.size();i++)
				{
					//System.out.println("testing : account id = "+ id + " Balance:" + balance);
					if(b.get(i).getAccountType().equals("Customer")&& b.get(i).getAcctid()== id)
					{
						
					b.get(i).setMoney(balance);
					if(status.equals("Approved"))
					{
						
						b.get(i).getCustomer().setsApproved(true);
					}
					else 
					{
						
						b.get(i).getCustomer().setsApproved(false);
					}
					}
					else if(b.get(i).getAccountType().equals("Employee")&&b.get(i).getAcctid() == id)
					{
						
						
					}
					else if(b.get(i).getAccountType().equals("Admin")&&b.get(i).getAcctid() == id)
					{
						
						
					}
					
				}
					 }
				
				
				//////////////////////////////////////////////////
	
					 
				rs.close();
				cont.close();
				
				 sql = "SELECT accounts.account_id ,ACCOUNTTYPE.ACCOUNT_TYPE_ID, ACCOUNTTYPE.ACCOUNT_TYPE from ACCOUNTTYPE inner join Accounts on Accounts.ACCOUNT_TYPE_ID = ACCOUNTTYPE.ACCOUNT_TYPE_ID";
				 cont = ConnectionUtil.getConnection();
				 ps = cont.prepareStatement(sql);
				 rs = ps.executeQuery();
				 while(rs.next())
				 {
					 
					int inid = rs.getInt(1);						
					String  idtype = rs.getString(2);
					String type  = rs.getString(3);
					//System.out.println(type);
			for(Bank bb :this.b)
			{
				if(bb.getAccountType().equals("Customer")&& bb.getAcctid() == inid)
				{
					
				//System.out.println("setting account type");
			    bb.getCustomer().setAccountCheckOrSavings(type);
				}
				
				else if(bb.getAccountType().equals("Employee")&&bb.getAcctid() == inid)
				{
					
					
				}
				else if(bb.getAccountType().equals("Admin")&&bb.getAcctid() == inid)
				{
					
					
				}
				
			}
			
			
				 }
				
				/////////////////////////////////////////////////////
				
				
				
				
			
			}
				catch (Exception e) {
						System.out.println(e.getMessage());
			}
	}
	
	public BankDriver() {
		userRight = null;
		signedInUser = null;
		signedInPass = null;
		signedIn = false;
		indexSignedIn = -1;
		usermax= 2;
		
	}
	
	public Scanner sScannerRefresher() {

		return new Scanner(System.in);
	}
	
	public void signUpCustomer() {

		String first = "", last = "", user = "", password = "", cs = "";
		int cOrs;

		boolean exitFlag = false;
		do {
			try {
				System.out.print("Enter First Name : ");
				userInput = sScannerRefresher();
				first = userInput.nextLine();
				System.out.print("Enter Last Name : ");
				userInput = sScannerRefresher();
				last = userInput.nextLine();
				System.out.print("Enter User Name For Account : ");
				userInput = sScannerRefresher();
				user = userInput.nextLine();
				System.out.print("Enter Password For Account : ");
				userInput = sScannerRefresher();
				password = userInput.nextLine();
				System.out.print("Enter 1 for Checking 2 for Savings Account : ");
				userInput = sScannerRefresher();
				cOrs = Integer.parseInt(userInput.nextLine());

				if (password.equals("") || user.equals("") || first.equals("") || last.equals("")
						|| (((cOrs) < 0) && (cOrs > 2))) {
					System.out.println("Invalid Entry Please Renter Information");
					continue;
				} else {
					switch (cOrs) {

					case 1:
						cs = "Checking";
						break;
					case 2:
						cs = "Savings";
						break;
					}
					System.out.println("Success Customer Added");
					break;
				}

			} catch (Exception e) {
				System.out.println("Invalid Entry Please Renter Information");
				exitFlag = false;
			}
		} while (true);
      
		BankDriver.usermax+=1;
		
		this.b.add(new Bank(new Customer(first, last, BankDriver.usermax, user, password, false, cs), 0,BankDriver.usermax,BankDriver.usermax));
		//ww
		
	
	
	System.out.println("THE USER MAX IS " + usermax);
		try
		{
			
			
	
			try{
			
				Connection cont = ConnectionUtil.getConnection();
				
				String sql = "INSERT INTO USERS  (USER_ID,FIRST_NAME,LAST_NAME,password,username,role_id)Values(" +String.format("%d,'%s','%s','%s','%s','%s')", usermax,first,last,password,user,1);
				Statement statement = cont.createStatement();
				statement.executeUpdate(sql);
				cont.close();
				statement.close();
				System.out.println("USERS PASS");
				
				
			}catch(Exception ee)
			{
				System.out.print("USERS"+ee.getMessage());
				
			}
	
	
			try{
				
				
				Connection cont = ConnectionUtil.getConnection();
				cont = ConnectionUtil.getConnection();
			
				String sql =  "INSERT INTO BANKINGAPPLICATION.ACCOUNTS (ACCOUNT_ID, ACCOUNT_TYPE_ID, BALANCE, STATUS_ID, RESOLVER_ID) VALUES("+String.format("%d,'%s','%s','%s','%s')", usermax,1,0,1,usermax);

				 Statement statement = cont.createStatement();
				statement.executeUpdate(sql);
				cont.close();
				statement.close();
			
			}
			catch(Exception e)
			{
				System.out.println("ACCOUNTS"+e.getMessage());
				
			}
			
			
			
			try
			{
		
		
			Connection cont = ConnectionUtil.getConnection();
			String sql = "INSERT INTO CustomerAccounts(acct_id,user_id) Values("+usermax+","+usermax+")";
			 Statement statement = cont.createStatement();
			statement.executeUpdate(sql);
			cont.close();
			statement.close();
			}
			catch(Exception ee)
			{
				
			System.out.println("Customer Accounts"+ ee.getMessage());
			}
	
		
			
			
	
		
	
		}
		catch(Exception e)
		{
			
			System.out.println("FAIL");
			
		}
		
		//WriteContents();
	}
	
	public void displaySignedInfo() {

		// System.out.println(this.b.get(indexSignedIn).getAccountType());
		if (this.b.get(indexSignedIn).getAccountType().equals("Customer")) {

			// System.out.println("0)Account Num : #" +
			// c.getCustomer().getAccountNum() +"\nFirst Name "
			// +c.getCustomer().getsFName() + "\nLast Name
			// "+c.getCustomer().getsLName() +"\nBalance: "+c.getMoney());
			System.out.println(String.format(
					"Account Type: %s\n Name: %s %s\n User Name: %s\n Sign in Status: %b\n Balance: $%.2f",
					this.b.get(indexSignedIn).getAccountType(), this.b.get(indexSignedIn).getCustomer().getsFName(),
					this.b.get(indexSignedIn).getCustomer().getsLName(),
					this.b.get(indexSignedIn).getCustomer().getsUserName(),
					this.b.get(indexSignedIn).getCustomer().isSignedIn(), this.b.get(indexSignedIn).getMoney()));

		}

		else if (this.b.get(indexSignedIn).getAccountType().equals("Employee")) {

			System.out.println(String.format("Account Type: %s\n Name: %s %s\n User Name: %s\n Sign in Status: %b",
					this.b.get(indexSignedIn).getAccountType(), this.b.get(indexSignedIn).getEmployee().getFirst(),
					this.b.get(indexSignedIn).getEmployee().getLast(),
					this.b.get(indexSignedIn).getEmployee().getsUserName(),
					this.b.get(indexSignedIn).getEmployee().isSignedIn()));

		} else if (this.b.get(indexSignedIn).getAccountType().equals("Admin")) {
			System.out.println(String.format("Account Type: %s\n Name: %s %s\n User Name: %s\n Sign in Status: %b",
					this.b.get(indexSignedIn).getAccountType(), this.b.get(indexSignedIn).getAdmin().getFirst(),
					this.b.get(indexSignedIn).getAdmin().getLast(), this.b.get(indexSignedIn).getAdmin().getsUserName(),
					this.b.get(indexSignedIn).getAdmin().isSignedIn()));

		}

	}

	
	public void displayAll() {

		for (int i = 0; i < this.b.size(); i++) {
			if (this.b.get(i).getAccountType().equals("Customer")) {
				System.out.println("------------------");
				System.out.println("Index Level " + i + "\nAccount Level : " + this.b.get(i).getAccountType() + "\n"
						+ "Account Num : #" + this.b.get(i).getCustomer().getAccountNum() + "\nFirst Name "
						+ this.b.get(i).getCustomer().getsFName() + "\nLast Name "
						+ this.b.get(i).getCustomer().getsLName() + "\nBalance: " + this.b.get(i).getMoney()
						+ "\nAccount Type: " + this.b.get(i).getCustomer().getAccountCheckOrSavings());
				System.out.println("------------------");
			} else if (b.get(i).getAccountType().equals("Employee")) {
				System.out.println("------------------");
				System.out.println(String.format(
						" Index Level %d \nAccount Level: %s\n Name: %s %s\n User Name: %s\n Sign in Status: %b", i,
						this.b.get(i).getAccountType(), this.b.get(i).getEmployee().getFirst(),
						this.b.get(i).getEmployee().getLast(), this.b.get(i).getEmployee().getsUserName(),
						this.b.get(i).getEmployee().isSignedIn()));
				System.out.println("------------------");
			} else if (b.get(i).getAccountType().equals("Admin")) {
				System.out.println("------------------");
				System.out.println(String.format(
						"Index Level %d \n Account Level: %s\n Name: %s %s\n User Name: %s\n Sign in Status: %b", i,
						this.b.get(i).getAccountType(), this.b.get(i).getAdmin().getFirst(),
						this.b.get(i).getAdmin().getLast(), this.b.get(i).getAdmin().getsUserName(),
						this.b.get(i).getAdmin().isSignedIn()));
				System.out.println("------------------");
			}

		}
	}

	public void displayAllCustomer() {
		for (int i = 0; i < this.b.size(); i++) {
			if (this.b.get(i).getAccountType().equals("Customer")) {
				System.out.println("------------------");
				System.out.println("Account Num : #" + this.b.get(i).getCustomer().getAccountNum() + "\nFirst Name "
						+ this.b.get(i).getCustomer().getsFName() + "\nLast Name "
						+ this.b.get(i).getCustomer().getsLName() + "\nBalance: " + this.b.get(i).getMoney()
						+ "\nAccount Type: " + this.b.get(i).getCustomer().getAccountCheckOrSavings()
						+ "\nAccount Status: " + this.b.get(i).getCustomer().issApproved());
				System.out.println("------------------");
			}
		}

	}
	
	public void signIn() {

		String pw = "";
		String user = "";
		boolean flagExit = false;

		do {

			Scanner sc = new Scanner(System.in);
			try {

				System.out.println("--------------------------");
				System.out.println("******PLEASE SIGN IN******");
				System.out.println("---------------------------");
				System.out.print("Enter Your Username\n->");
				user = sc.nextLine();

				System.out.print("Enter Your Password\n->");
				sc = new Scanner(System.in);
				pw = sc.nextLine();

				if (user.equals("") || pw.equals("")) {
					throw new Exception();
				}
				flagExit = true;

			} catch (Exception e) {
				System.out.println("Invalid Entry Plese Sign In Again");

			}
		} while (flagExit == false);
		for (int i = 0; i < b.size(); i++)

		{

			if (this.b.get(i).getAccountType().equals("Customer")) {
				// if(b.get(i).getsUserName())
				if (this.b.get(i).getCustomer().getsUserName().equals(user)
						&& this.b.get(i).getCustomer().getsPassword().equals(pw)) {
					this.signedInUser = b.get(i).getCustomer().getsUserName();
					this.signedInPass = b.get(i).getCustomer().getsPassword();
					this.userRight = "Customer";
					this.signedIn = true;
					this.indexSignedIn = i;

					this.b.get(i).getCustomer().setSignedIn(true);

				}
			}

			if (this.b.get(i).getAccountType().equals("Employee")) {

				if (b.get(i).getEmployee().getsUserName().equals(user)
						&& b.get(i).getEmployee().getsPassword().equals(pw))

				{
					this.signedInUser = b.get(i).getEmployee().getsUserName();
					this.signedInPass = b.get(i).getEmployee().getsPassword();
					this.userRight = "Employee";
					this.signedIn = true;
					this.indexSignedIn = i;

					this.b.get(i).getEmployee().setSignedIn(true);
				}

			}
			if (this.b.get(i).getAccountType().equals("Admin")) {
				if (this.b.get(i).getAdmin().getsUserName().equals(user)
						&& this.b.get(i).getAdmin().getsPassword().equals(pw)) {

					this.signedInUser = b.get(i).getAdmin().getsUserName();
					this.signedInPass = b.get(i).getAdmin().getsPassword();
					this.userRight = "Admin";
					this.signedIn = true;
					this.indexSignedIn = i;

					this.b.get(i).getAdmin().setSignedIn(true);
				}
			}

		}
		if (this.signedIn == false) {

			System.out.println("Invalid Username or Password!");
		} else {
			System.out.println("Success Logged In");

		}

	}
	
	public void signOut() {

		if (this.userRight.equals("Customer")) {

			b.get(indexSignedIn).getAdmin().setSignedIn(false);
		}
		if (this.userRight.equals("Employee")) {

			b.get(indexSignedIn).getAdmin().setSignedIn(false);
		}
		if (this.userRight.equals("Admin")) {
			b.get(indexSignedIn).getAdmin().setSignedIn(false);

		}
		this.signedInUser = null;
		this.signedInPass = null;
		this.userRight = null;
		this.signedIn = false;
		this.indexSignedIn = -1;

	}

	public void permissionSelctionDisplay() {

		

		boolean flagExit = false;
		if (this.userRight.equals("Customer")) {

			do {
				if (this.b.get(indexSignedIn).getCustomer().issApproved() == false) {
					System.out.println(
							"You do not have access to the bank because your account has not yet been approved.");
					try {
						signOut();
					} catch (Exception e) {
						break;
					}
				}
			
				try {
					System.out.println("--------------------------");
					System.out.println("*Welcome to the "+ this.userRight+" menu");
					System.out.println("---------------------------");
					System.out.println("Enter 0 to Sign Out ");
					System.out.println("Enter 1 to withdraw from Account");
					System.out.println("Enter 2 to deposit to Account");
					System.out.println("Enter 3 display my info");
					System.out.print("->");
					int sSwith = 0;
					userInput = sScannerRefresher();
					sSwith = userInput.nextInt();
					switch (sSwith) {
					case 0:
						flagExit = true;
						signedIn = false;
						signOut();
						break;
					case 1:

						do {
							try {
								userInput = new Scanner(System.in);
								double amount = 0;
								System.out.print("How much money do you want to withdraw?\nEnter Amount->");
								amount = userInput.nextDouble();
								this.b.get(indexSignedIn).withdraw(amount);
								displaySignedInfo();
								UpdateMoneySQL();
								
								userInput = new Scanner(System.in);
								break;
							} catch (Exception e) {
								System.out.println("Invalid Entry" + e.getMessage());

							}

						} while (true);
						break;

					case 2:
						do {
							try {
								userInput = new Scanner(System.in);
								double amount = 0;

								System.out.print("How much money do you want to deposit?\nEnter Amount->");
								amount = userInput.nextDouble();
								this.b.get(indexSignedIn).deposit(amount);
								
								UpdateMoneySQL();
								displaySignedInfo();
								userInput = new Scanner(System.in);
								break;
							} catch (Exception e) {

								System.out.println("Invalid Entry");
							}

						} while (true);
						break;
					case 3:
						displaySignedInfo();

						break;
					default:
						// System.out.println("Invalid Entry");
						break;
					}
				} catch (Exception e) {

					// System.out.println("Invalid Entry");

				}
			} while (flagExit == false);

		}
		if (this.userRight.equals("Employee")) {
			do {
				try {
					System.out.println("--------------------------");
					System.out.println("*Welcome to the "+ this.userRight+" menu");
					System.out.println("---------------------------");
					System.out.println("Enter 0 to Sign Out");
					System.out.println("Enter 1 to approve customer accounts");
					System.out.println("Enter 2 to View All Customer Accounts");
					System.out.print("->");
					int sSwith = 0;
					userInput = sScannerRefresher();
					sSwith = userInput.nextInt();
					switch (sSwith) {
					case 0:
						signedIn = false;
						flagExit = true;
						signOut();
						break;
					case 1:
						ApproveAccounts();
						break;
					case 2:
						displayAllCustomer();
						break;
					default:
						System.out.println("Invalid Entry");

					}
				} catch (Exception e) {

				}

			} while (flagExit == false);

		}
		if (this.userRight.equals("Admin")) {

			do {
				try {
					System.out.println("--------------------------");
					System.out.println("*Welcome to the "+ this.userRight+" menu");
					System.out.println("---------------------------");
					System.out.println("Enter 0 to Sign Out");
					System.out.println("Enter 1 View All Accounts");
					System.out.println("Enter 2 to Edit A Account");
					System.out.print("->");
					int sSwith = 0;
					userInput = sScannerRefresher();
					sSwith = userInput.nextInt();

					switch (sSwith) {
					case 0:
						signedIn = false;
						flagExit = true;
						signOut();
						break;
					case 1:
						displayAll();
						break;
					case 2:
						adminEditAll();
						break;
					default:

						System.out.println("Invalid Entry");
						break;
					}
				} catch (Exception e) {

				}
			} while (flagExit == false);

		}
	}

	@Override
	
	public boolean Approve(Customer c) {
		return signedIn;

	}

	@Override
	
	public Customer customerSignUp() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void adminEditAll() {
		int choice = 0;
		int index = 0;
		displayAll();

		do {
			userInput = sScannerRefresher();
			System.out.print("Enter Index Level You Wish To Edit\n");
			try {
				index = userInput.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Entry");

			}
		} while (index < 0 || index > b.size() - 1);

		do {
			try {
				System.out.println("--------------------------");
				System.out.println("*WELCOME TO REVATURE BANK! *");
				System.out.println("---------------------------");
				System.out.println("Enter 0 to Exit Prompt");
				System.out.println("Enter 1 to Edit First Name");
				System.out.println("Enter 2 to Edit Last Name");
				System.out.println("Enter 3 to Edit User Name");
				System.out.println("Enter 4 to Edit Password Name");
				System.out.print("->");
				choice = userInput.nextInt();

			} catch (Exception e) {

			}
	

		System.out.println("");
		do {
			switch (choice) {

			case 0:

				break;
			case 1:
				adminEditFirst(index);

				break;
			case 2:
				adminEditLast(index);
				break;
			case 3:
				adminEditUser(index);
				break;
			case 4:
				adminEditPass(index);
			}
			break;
		} while (choice != 0);
		} while (choice < 0 || choice > 4);
	}
	
	public void adminEditFirst(int index) {
		String str = "";

		do {
			System.out.print("Enter The First Name You Wish To Have\n->");
			userInput = sScannerRefresher();
			str = userInput.nextLine();

		} while (str.equals(""));

		if (this.b.get(index).getAccountType().equals("Customer")) {
			Main.customLogger.info("Customer First Name Changed ");
			logMessages("Customer First Name Changed ");
			this.b.get(index).getCustomer().setsFName(str);
			
			UpdateFirstSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Employee")) {
			Main.customLogger.info("Employee First Name Changed ");
			logMessages("Employee First Name Changed ");
			this.b.get(index).getEmployee().setFirst(str);
			UpdateFirstSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Admin")) {
			Main.customLogger.info("Admin  Name Changed ");
			logMessages("Admin First Name Changed ");
			this.b.get(index).getAdmin().setFirst(str);
			UpdateFirstSQL(index,str);
			;

		}

	}
	
	public void adminEditLast(int index) {
		String str = "";

		do {
			System.out.print("Enter The Last Name You Wish To Have\n->");
			userInput = sScannerRefresher();
			str = userInput.nextLine();

		} while (str.equals(""));

		if (this.b.get(index).getAccountType().equals("Customer")) {
			Main.customLogger.info("Customer Last Name Changed ");
			logMessages("Customer Last Name Changed ");
			this.b.get(index).getCustomer().setsLName(str);
			UpdateLastSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Employee")) {
			Main.customLogger.info("Employee Last Name Changed ");
			logMessages("Employee Last Name Changed ");
			this.b.get(index).getEmployee().setLast(str);
			UpdateLastSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Admin")) {
			Main.customLogger.info("Admin Last Name Changed ");
			logMessages("Admin Last Name Changed ");
			this.b.get(index).getAdmin().setLast(str);
			UpdateLastSQL(index,str);
			;

		}

	}
	
	public void adminEditUser(int index) {
		String str = "";

		do {
			System.out.print("Enter The User Name You Wish To Have\n->");
			userInput = sScannerRefresher();
			str = userInput.nextLine();

		} while (str.equals(""));

		if (this.b.get(index).getAccountType().equals("Customer")) {
			Main.customLogger.info("Customer User Name Changed ");
			this.b.get(index).getCustomer().setsUserName(str);
			logMessages("Customer User Name Changed ");
			UpdateUserSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Employee")) {
			Main.customLogger.info("Employee User Name Changed ");
			logMessages("Employee User Name Changed ");
			this.b.get(index).getEmployee().setsUserName(str);
			UpdateUserSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Admin")) {
			Main.customLogger.info("Admin User Name Changed ");
			logMessages("Admin User Name Changed ");
			this.b.get(index).getAdmin().setsUserName(str);
			UpdateUserSQL(index,str);

		}

	}
	
	public void adminEditPass(int index) {

		String str = "";

		do {
			System.out.print("Enter The Password You Wish To Have\n->");
			userInput = sScannerRefresher();
			str = userInput.nextLine();

		} while (str.equals(""));



		
		if (this.b.get(index).getAccountType().equals("Customer")) {
			Main.customLogger.info("Customer Password Changed ");
			this.b.get(index).getCustomer().setsPassword(str);
			
			logMessages("Customer Password Changed ");
			UpdatePasswordSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Employee")) {
			Main.customLogger.info("Employee Password Changed ");
			logMessages("Employee Password Changed ");
			UpdatePasswordSQL(index,str);
		} else if (this.b.get(index).getAccountType().equals("Admin")) {
			Main.customLogger.info("Admin Password Changed ");
			logMessages("Admin Password Changed ");
			UpdatePasswordSQL(index,str);

		}

	}



public void logMessages(String logm)
{
	try
	{DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
		Connection cont = ConnectionUtil.getConnection();
		String sql = "INSERT INTO LOG_TABLE (DESCRIPTION_LOG) Values('"+logm + " - " +dateFormat.format(date).toString()+"')";
		 Statement statement = cont.createStatement();
		statement.executeUpdate(sql);
		cont.close();
		statement.close();
		
	}
	catch(Exception e)
	{
		
		System.out.println("Connection Failed");
	}
	
}
}



