package com.revature.starting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Customer {

		private String type;
		private String username;
		private String password;
		private String savings;
		private String checkings;
		private double sbalance;
		private double cbalance;
		private boolean approved;
		private  Checking checking;
		private Saving saving;
		
		public Customer(String type, String username, String password, String savings, String checkings, double sbalance, double cbalance, boolean approved){
			this.checkings = checkings;
			this.savings = savings;
			checking =new Checking();
			saving = new Saving();
			this.type = type;
			this.username = username;
			this.password = password;
			this.sbalance = sbalance;
			this.cbalance = cbalance;
			this.approved = approved;
			try{
		saving.setBalance(sbalance);
		checking.setBalance(cbalance);
			}
			catch(Exception e){
				//System.out.println("bruhhhhhhh....");
			}
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getSavings() {
			return savings;
		}

		public void setSavings(String savings) {
			this.savings = savings;
		}

		public String getCheckings() {
			return checkings;
		}

		public void setCheckings(String checkings) {
			this.checkings = checkings;
		}

		public boolean isApproved() {
			return approved;
		}

		public void setApproved(boolean approved) {
			this.approved = approved;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return username;
		}

		public void setName(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public double getSbalance() {
			return sbalance;
		}

		public void setSbalance(double sbalance) {
			this.sbalance = sbalance;
		}

		public double getCbalance() {
			return cbalance;
		}

		public void setCbalance(double cbalance) {
			this.cbalance = cbalance;
		}

		public Checking getChecking() {
			return checking;
		}

		public void setChecking(Checking checking) {
			this.checking = checking;
		}

		public Saving getSaving() {
			return saving;
		}

		public void setSaving(Saving saving) {
			this.saving = saving;
		}
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*// do{
			try {
				File file = new File("Data.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				// br.readLine();
				String str;
				while ((str = br.readLine()) != null) {
					String[] sarr = str.split(":");
					String first = sarr[0]; 
					String last = sarr[1]; 
					String age = sarr[2]; 
					String state = sarr[3];
			
			
			
			
			
		}
			Scanner uinput = new Scanner(System.in);
			String s1 = uinput.nextLine();
			File f1 = new File("names.txt");
			FileReader fr = new FileReader(f1);
			BufferedReader ubr = new BufferedReader(fr);
			ubr.close();
		}

		catch (IOException exc) {
			System.out.println("\nUser name not found.\n");
		}

		System.out.println("Password: ");

		try {
			Scanner pinput = new Scanner(System.in);
			String s2 = pinput.nextLine();
			File f2 = new File("passwords.txt");
			FileReader fr = new FileReader(f2);
			BufferedReader pbr = new BufferedReader(fr);
			pbr.close();
		} catch (IOException exc)

		{
			System.out.println("Incorrect password.");
		}

		// }
	}

	public void deposit(double depo){
		System.out.println("Deposit or Withdrawal?");
		Scanner pick = new Scanner;
		String choice = pick.nextLine();
		
		if (choice.equals("deposit")){
			
		
		try
		{
			System.out.println("how much would you like to deposit?");
			
			Scanner sdep = new Scanner(System.in);
			String ds = sdep.nextLine();
			File df2 = new File("======= to be determined =============");
			FileReader dfr = new FileReader(df2);
			BufferedReader dbr = new BufferedReader(dfr);
			double balance = sdep.read();
			
			
			double newbalance = (balance + sdep);
			System.out.println("your new balance is: " + newbalance);

			
			for (int i = 0; i < .size(); i++) {
			    if (.get(i).equals(bal)) {
			       .set(i, newbalance);
			        break;
			    }
			}
			
			dbr.close();
			
			
			}
			catch (IOException exc){
			System.out.println("your new balance is: \n");
			}
		}
		if (choice.equals("withdrawl")){
			try
			{
				System.out.println("how much would you like to withdrawl?");
				
				Scanner swit = new Scanner(System.in);
				String ws = swit.nextLine();
				File wf2 = new File("======= to be determined =============");
				FileReader wfr = new FileReader(wf2);
				BufferedReader wbr = new BufferedReader(wfr);
				double balance = swit.read();
				
				
				double newbalance = (balance - swit);
				System.out.println("your new balance is: " + newbalance);
				
				dbr.close();
				
				
				}
				catch (IOException exc){
				System.out.println("your new balance is: \n");
				}
			
		}
		else{
			System.out.println("invalid input, try again.");
		}
		
		
		
	  }
}
*/