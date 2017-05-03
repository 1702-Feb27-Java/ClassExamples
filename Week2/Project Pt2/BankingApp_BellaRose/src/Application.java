import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import Accounts.Account;
import Accounts.Checking;
import Accounts.Savings;
import Users.Admin;
import Users.Customer;
import Users.Employee;
import Users.Person;
import Users.*;
import Accounts.*;

public final class Application
{

	private Person currentUser;
	private ArrayList<Account> bankAccounts = new ArrayList<>();
	private LinkedList<Customer> allCustomers = new LinkedList<>();

	String mainMenu = "===================================================\n====== Welcome to Bella Federal Credit Union ======\n===================================================\n\n\t\t1: Sign In\n\n\t\t2: Sign Up\n";
	///// use this to make header for account, put name ^^^^
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub

		Application app = new Application();

		app.start();

		app.writeCustomersToFile();
		app.writeAccountsToFile();
		app.exit();
	}

	private void exit()
	{
		// TODO Auto-generated method stub
		System.out.println("Goodbye");
	}

	private void start() throws Exception
	{

		readAllAccounts();
		allCustomers = fillAllEmployeeCustomersList();

		System.out.println(mainMenu);

		boolean validInput = false;

		int choice;

		do
		{
			System.out.println("What do you want to do?\n");

			choice = scan.nextInt();

			if (choice == 1 || choice == 2)
				validInput = true;
			else
			{
				System.out.println("Not a valid input! Try again.\n");
			}

		} while (validInput == false);

		switch (choice)
		{
		case 1:
		{
			clearScreen();
			signIn();
		}
			break;
		case 2:
		{
			clearScreen();
			signUp();
			System.out.println("Signed up.");
		}
			break;
		default:
		{
			clearScreen();
			System.out.println("*******************THIS SHOULDNT HAPPEN*******************");
		}
			break;
		}

	}

	private void signIn() throws InterruptedException, IOException
	{

		boolean loginSuccessful = false;

		do
		{
			System.out.println("===================== Sign In =====================\n");

			String username = "", passwordAttempt = "";

			System.out.println("Username: ");

			username = scan.next();

			System.out.println("Password: ");

			passwordAttempt = scan.next();

			if (isValidUser(username, passwordAttempt))
			{

				if (currentUser.getType() == 1)
				{
					System.out.println("Welcome Administrator " + currentUser.getFirstName());
				} else if (currentUser.getType() == 2)
				{
					System.out.println("Welcome Employee " + currentUser.getFirstName());
					showEmployeeMenus();
				} else
				{
					System.out.println("Welcome Customer " + currentUser.getFirstName());
					showCustomerMenus();
				}

				loginSuccessful = true;
			} else
			{
				System.out.println("Password not valid! Try Again.");
			}

		} while (loginSuccessful == false);
	}

	private void signUp() throws IOException, InterruptedException
	{
		System.out.println("===================== Sign Up =====================\n");

		String firstName, lastName, username, password;

		System.out.println("First Name: ");

		firstName = scan.next();
		scan.nextLine();

		System.out.println("Last Name: ");

		lastName = scan.next();
		scan.nextLine();

		do
		{
			System.out.println("Username: ");
			username = scan.next();
			scan.nextLine();

			if (isValidUsername(username) == false)
			{
				System.out.println("Username is already being used. Try Again.");
			} else
			{
				break;
			}

		} while (true);

		System.out.println("Password: ");
		password = scan.next();
		scan.nextLine();

		Customer temp = new Customer();
		temp.setFirstName(firstName);
		temp.setLastName(lastName);
		temp.setUsername(username);
		temp.setPassword(password);
		allCustomers.add(temp);

	}

	private static void clearScreen()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void showCustomerMenus() throws InterruptedException
	{
		String accountMenu = "===================================================\n====== "
				+ currentUser.getFirstName().toUpperCase()
				+ "'s Account ======\n===================================================\n\n\t\t1: View account balances\n\n\t\t2: Apply for account\n\n\t\t3: Deposit\n\n\t\t4: Withdraw\n\n\t\t5: Exit\n";

		boolean validInput = false;

		int choice;

		do
		{
			System.out.println(accountMenu);
			System.out.println("What do you want to do?\n");

			choice = scan.nextInt();

			if (choice > 0 && choice <= 5)
				validInput = true;
			else
			{
				System.out.println("Not a valid input! Try again.\n");
			}

		} while (validInput == false);

		switch (choice)
		{
		case 1:
		{
			clearScreen();
			showBalances((Customer) currentUser);
			showCustomerMenus();
		}
			break;
		case 2:
		{
			clearScreen();
			String whichTypeMenu = "Which type of account do you want to apply for?\n\n\t\t1: Checking\n\n\t\t2: Savings\n\n\t\t3: Both\n\n\t\t4:Go Back\n";

			validInput = false;

			do
			{
				System.out.println(whichTypeMenu);
				System.out.println("Your decision: \n");

				choice = scan.nextInt();

				if (choice > 0 && choice <= 4)
					validInput = true;
				else
				{
					System.out.println("Not a valid input! Try again.\n");
				}

				if (choice == 4)
				{
					showCustomerMenus();
					break;
				}

			} while (validInput == false);
			applyForAccount(choice);
		}
			break;
		case 3:
		{
			clearScreen();
			if (((Customer) currentUser).getCheckingAccount() == null
					&& ((Customer) currentUser).getSavingsAccount() == null)
			{
				System.out.println("You do not have any open bank accounts. Please Apply!");
				showCustomerMenus();
			} else
			{
				System.out.println("How much are you depositing?\n");
				double amount = scan.nextDouble();
				distinguishWhichAccount(1, amount);
				showCustomerMenus();
			}
		}
			break;
		case 4:
		{
			clearScreen();
			if (((Customer) currentUser).getCheckingAccount() == null
					&& ((Customer) currentUser).getSavingsAccount() == null)
			{
				System.out.println("You do not have any open bank accounts. Please Apply!");
				showCustomerMenus();
			} else
			{
				System.out.println("How much do you want to withdraw?\n");
				double amount = scan.nextDouble();
				distinguishWhichAccount(2, amount);
				showCustomerMenus();
			}
		}
		case 5:
			return;
		default:
		{
			clearScreen();
			System.out.println("*******************THIS SHOULDNT HAPPEN*******************");
		}
			break;
		}

	}

	private void showEmployeeMenus()
	{
		String accountMenu = "===================================================\n====== "
				+ currentUser.getFirstName().toUpperCase()
				+ "'s Account ======\n===================================================\n\n\t\t1: Approve all account applications\n\n\t\t2: View my customers\n\n\t\t3: Exit\n";

		boolean validInput = false;

		int choice;

		do
		{
			System.out.println(accountMenu);
			System.out.println("What do you want to do?\n");

			choice = scan.nextInt();

			if (choice == 1 || choice == 2)
				validInput = true;
			else if (choice == 3)
			{
				return;
			} else
			{
				System.out.println("Not a valid input! Try again.\n");
			}

		} while (validInput == false);

		switch (choice)
		{
		case 1:
		{
			clearScreen();
			approveAllApplications();
		}
			break;
		case 2:
		{
			clearScreen();
			showMyCustomers();
		}
			break;
		default:
		{
			clearScreen();
			System.out.println("*******************THIS SHOULDNT HAPPEN*******************");
		}
			break;
		}

	}

	private boolean isValidUser(String username, String password) throws IOException
	{

		BufferedReader in = new BufferedReader(new FileReader("AppData\\customers.txt"));
		String line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[2].equals(username) && parts[3].equals(password))
			{
				currentUser = new Customer();
				currentUser.setFirstName(parts[0]);
				currentUser.setLastName(parts[1]);
				currentUser.setUsername(username);
				currentUser.setPassword(password);
				currentUser.setType(3);

				((Customer) currentUser).setCheckingAccount(GetChecking(username));
				((Customer) currentUser).setSavingsAccount(GetSavings(username));

				if (parts[4].equals("null") == false)
				{
					((Customer) currentUser).setBanker(getEmployeeByUsername(parts[4]));
				}
				return true;
			}
		}
		in.close();

		in = new BufferedReader(new FileReader("AppData\\employees.txt"));
		line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[2].equals(username) && parts[3].equals(password))
			{
				if (Integer.parseInt(parts[4]) == 1)
				{
					currentUser = new Admin();

					currentUser.setFirstName(parts[0]);
					currentUser.setLastName(parts[1]);
					currentUser.setUsername(username);
					currentUser.setPassword(password);
					currentUser.setType(1);
					((Admin) currentUser).setCustomers(fillAllEmployeeCustomersList());
				} else
				{
					currentUser = new Employee();

					currentUser.setFirstName(parts[0]);
					currentUser.setLastName(parts[1]);
					currentUser.setUsername(username);
					currentUser.setPassword(password);
					currentUser.setType(2);
					((Employee) currentUser).setCustomers(fillEmployeeCustomersList(username));
				}

				return true;
			}
		}
		in.close();

		return false;
	}

	private LinkedList<Customer> fillAllEmployeeCustomersList() throws IOException
	{
		LinkedList<Customer> custList = new LinkedList<>();

		BufferedReader in = new BufferedReader(new FileReader("AppData\\customers.txt"));
		String line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			Customer temp = new Customer();
			temp.setFirstName(parts[0]);
			temp.setLastName(parts[1]);
			temp.setUsername(parts[2]);
			temp.setPassword(parts[3]);
			temp.setBanker(this.getEmployeeByUsername(parts[4]));
			temp.setType(3);
			custList.add(temp);

		}
		in.close();

		return custList;
	}

	private LinkedList<Customer> fillEmployeeCustomersList(String username) throws IOException
	{
		// TODO Auto-generated method stub
		LinkedList<Customer> custList = new LinkedList<>();

		BufferedReader in = new BufferedReader(new FileReader("AppData\\customers.txt"));
		String line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[4].equals(username))
			{
				Customer temp = new Customer();
				temp.setFirstName(parts[0]);
				temp.setLastName(parts[1]);
				temp.setUsername(parts[2]);
				temp.setPassword(parts[3]);
				temp.setType(3);
				custList.add(temp);
			}
		}
		in.close();

		return custList;
	}

	private Employee getEmployeeByUsername(String username) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("AppData\\employees.txt"));
		String line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[2].equals(username))
			{
				Employee temp = new Employee();
				temp.setFirstName(parts[0]);
				temp.setLastName(parts[1]);
				temp.setUsername(username);
				temp.setPassword(parts[3]);
				temp.setType(2);
				return temp;
			}
		}
		in.close();
		return null;
	}

	private boolean isValidUsername(String username) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("AppData\\customers.txt"));
		String line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[2].equals(username))
			{
				return false;
			}
		}
		in.close();

		in = new BufferedReader(new FileReader("AppData\\employees.txt"));
		line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[2].equals(username))
			{
				return false;
			}
		}

		in.close();

		return true;
	}

	private void showBalances(Customer current) throws InterruptedException
	{
		if (current.getCheckingAccount() == null && current.getSavingsAccount() == null)
		{
			System.out.println("You have no accounts. Please apply for one.");
		}

		if (current.getCheckingAccount() != null)
		{
			System.out.println("Checking balance is: " + current.getCheckingAccount().getBalance());
		}

		if (current.getSavingsAccount() != null)
		{
			System.out.println("Savings balance is: " + current.getSavingsAccount().getBalance());
		}

	}

	private void readAllAccounts() throws IOException
	{
		// TODO Auto-generated method stub

		BufferedReader in = new BufferedReader(new FileReader("AppData\\accounts.txt"));
		String line = "";
		while ((line = in.readLine()) != null)
		{
			String parts[] = line.split("\t");

			if (parts[2].equals("checking"))
			{
				Checking temp = new Checking();
				temp.setUsername(parts[0]);
				temp.setBalance(Double.parseDouble(parts[1]));
				temp.setApproved(Boolean.parseBoolean(parts[3]));
				temp.setAppliedFor(Boolean.parseBoolean(parts[4]));
				bankAccounts.add(temp);
			} else
			{
				Savings temp = new Savings();
				temp.setUsername(parts[0]);
				temp.setBalance(Double.parseDouble(parts[1]));
				temp.setApproved(Boolean.parseBoolean(parts[3]));
				temp.setAppliedFor(Boolean.parseBoolean(parts[4]));
				bankAccounts.add(temp);
			}
		}
		in.close();

	}

	private Checking GetChecking(String username)
	{

		for (int i = 0; i < bankAccounts.size(); i++)
		{

			if (bankAccounts.get(i) instanceof Savings)
				return null;

			if (bankAccounts.get(i).getUsername().equals(username))
			{
				return (Checking) bankAccounts.get(i);
			}
		}

		return null;
	}

	private Savings GetSavings(String username)
	{
		for (int i = 0; i < bankAccounts.size(); i++)
		{
			if (bankAccounts.get(i) instanceof Checking)
				return null;

			if (bankAccounts.get(i).getUsername().equals(username))
			{
				return (Savings) bankAccounts.get(i);
			}
		}

		return null;
	}

	private void distinguishWhichAccount(int transaction, double amount) throws InterruptedException
	{

		if (((Customer) currentUser).getCheckingAccount() != null
				&& ((Customer) currentUser).getSavingsAccount() == null)
		{
			if (transaction == 1)
				((Customer) currentUser).getCheckingAccount().deposit(amount);
			else
			{
				if (((Customer) currentUser).getCheckingAccount().getBalance() - amount < 0)
				{
					System.out.println("You don't have enough money to withdraw $" + amount);
					showCustomerMenus();
				} else
					((Customer) currentUser).getCheckingAccount().withdraw(amount);
			}
		} else if (((Customer) currentUser).getCheckingAccount() == null
				&& ((Customer) currentUser).getSavingsAccount() != null)
		{
			if (transaction == 1)
				((Customer) currentUser).getSavingsAccount().deposit(amount);
			else
			{
				if (((Customer) currentUser).getSavingsAccount().getBalance() - amount < 0)
				{
					System.out.println("You don't have enough money to withdraw $" + amount);
					showCustomerMenus();
				} else
					((Customer) currentUser).getSavingsAccount().withdraw(amount);
			}
		} else
		{

			String whichTypeMenu = "Which type of account?\n\n\t\t1: Checking\n\n\t\t2: Savings\n";

			boolean validInput = false;

			int choice;

			do
			{
				System.out.println(whichTypeMenu);
				System.out.println("Your decision: \n");

				choice = scan.nextInt();

				if (choice > 0 && choice <= 4)
					validInput = true;
				else
				{
					System.out.println("Not a valid input! Try again.\n");
				}

			} while (validInput == false);

		}
	}

	private void writeAccountsToFile() throws IOException
	{

		for (int i = 0; i < bankAccounts.size(); i++)
		{

			String type;

			if (bankAccounts.get(i) instanceof Checking)
			{
				type = "checking";
			} else
			{
				type = "savings";
			}
			
			if (i == 0)
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("AppData\\accounts.txt", false));
				out.write(bankAccounts.get(i).getUsername() + "\t" + bankAccounts.get(i).getBalance() + "\t" + type + "\t"
						+ bankAccounts.get(i).isApproved() + "\t" + bankAccounts.get(i).isAppliedFor());
				out.newLine();
				out.close();
			}else
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("AppData\\accounts.txt", true));
				out.write(bankAccounts.get(i).getUsername() + "\t" + bankAccounts.get(i).getBalance() + "\t" + type + "\t"
						+ bankAccounts.get(i).isApproved() + "\t" + bankAccounts.get(i).isAppliedFor());
				out.newLine();
				out.close();
			}
		}

	}

	private void writeCustomersToFile() throws IOException
	{

		for (int i = 0; i < allCustomers.size(); i++)
		{

			if (i == 0)
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("AppData\\customers.txt", false));
				out.write(allCustomers.get(i).getFirstName() + "\t" + allCustomers.get(i).getLastName() + "\t"
						+ allCustomers.get(i).getUsername() + "\t" + allCustomers.get(i).getPassword() + "\t"
						+ allCustomers.get(i).getBanker());
				out.newLine();
				out.close();
			}else
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("AppData\\customers.txt", true));
				out.write(allCustomers.get(i).getFirstName() + "\t" + allCustomers.get(i).getLastName() + "\t"
						+ allCustomers.get(i).getUsername() + "\t" + allCustomers.get(i).getPassword() + "\t"
						+ allCustomers.get(i).getBanker());
				out.newLine();
				out.close();
			}
		}

	}

	private void applyForAccount(int choice) throws InterruptedException
	{
		// TODO Auto-generated method stub
		if (choice == 1)
		{
			((Customer) currentUser).setCheckingAccount(new Checking());
			((Customer) currentUser).getCheckingAccount().setAppliedFor(true);
			((Customer) currentUser).getCheckingAccount().setUsername(currentUser.getUsername());
			Checking temp = new Checking();
			temp.setAppliedFor(true);
			temp.setUsername(currentUser.getUsername());
			bankAccounts.add(temp);
		} else if (choice == 2)
		{
			((Customer) currentUser).setSavingsAccount(new Savings());
			((Customer) currentUser).getSavingsAccount().setAppliedFor(true);
			((Customer) currentUser).getSavingsAccount().setUsername(currentUser.getUsername());
			Savings temp = new Savings();
			temp.setAppliedFor(true);
			temp.setUsername(currentUser.getUsername());
			bankAccounts.add(temp);
		} else
		{
			((Customer) currentUser).setCheckingAccount(new Checking());
			((Customer) currentUser).getCheckingAccount().setAppliedFor(true);
			((Customer) currentUser).getCheckingAccount().setUsername(currentUser.getUsername());
			Checking temp = new Checking();
			temp.setAppliedFor(true);
			temp.setUsername(currentUser.getUsername());
			bankAccounts.add(temp);

			((Customer) currentUser).setSavingsAccount(new Savings());
			((Customer) currentUser).getSavingsAccount().setAppliedFor(true);
			((Customer) currentUser).getSavingsAccount().setUsername(currentUser.getUsername());
			Savings temp2 = new Savings();
			temp2.setAppliedFor(true);
			temp2.setUsername(currentUser.getUsername());
			bankAccounts.add(temp2);
		}

		System.out.println("Application(s) sent!");
		showCustomerMenus();
	}

	private void showMyCustomers()
	{
		// TODO Auto-generated method stub
		clearScreen();
		System.out.println("My Customers");
		
		((Employee)currentUser).setCustomers(fillOutCustomers());

		for (int i = 0; i < ((Employee) currentUser).getCustomers().size(); i++)
		{
			String firstName = ((Employee) currentUser).getCustomers().get(i).getFirstName(),
					lastName = ((Employee) currentUser).getCustomers().get(i).getLastName();
			double checking = ((Employee) currentUser).getCustomers().get(i).getCheckingAccount().getBalance(),
					savings = ((Employee) currentUser).getCustomers().get(i).getSavingsAccount().getBalance();

			System.out.println(firstName + " " + lastName);

			if (((Employee) currentUser).getCustomers().get(i).getCheckingAccount().isApproved() == false)
			{
				System.out.println("Checking Account = NULL");
			} else if (((Employee) currentUser).getCustomers().get(i).getCheckingAccount().isApproved())
			{
				System.out.println("Checking account balance: " + checking);
			}

			if (((Employee) currentUser).getCustomers().get(i).getSavingsAccount().isApproved() == false)
			{
				System.out.println("Savings Account = NULL");
			} else if (((Employee) currentUser).getCustomers().get(i).getSavingsAccount().isApproved())
			{
				System.out.println("Savings account balance: " + checking);
			}

			System.out.println("\n");

		}

		showEmployeeMenus();

	}

	private LinkedList<Customer> fillOutCustomers()
	{
		LinkedList<Customer> custs = new LinkedList<>();
		
		for (int i = 0; i < allCustomers.size(); i++)
		{ 
			if (allCustomers.get(i).getBanker() == (Employee)currentUser)
			{
				custs.add(allCustomers.get(i));
			}
		}
		
		return custs;
	}

	private void approveAllApplications()
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < bankAccounts.size(); i++)
		{

			if (bankAccounts.get(i).isAppliedFor() && bankAccounts.get(i).isApproved() == false)
			{
				bankAccounts.get(i).setApproved(true);
				setBanker(bankAccounts.get(i).getUsername());
			}

		}

		System.out.println("Application(s) approved!");

		showEmployeeMenus();
	}

	private void setBanker(String username)
	{
		// TODO Auto-generated method stub

		for (int i = 0; i < allCustomers.size(); i++)
		{

			if (allCustomers.get(i).getUsername().equals(username))
			{
				allCustomers.get(i).setBanker((Employee) currentUser);
			}
		}

	}

}
