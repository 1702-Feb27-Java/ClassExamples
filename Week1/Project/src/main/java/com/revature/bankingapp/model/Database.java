package com.revature.bankingapp.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.revature.bankingapp.Logging;

public  class Database {

	//file names to write to.
	private static final File CUSTOMERS_FILE = new File("customers.txt");
	private static final File EMPLOYEES_FILE = new File("employees.txt");
	private static final File ACCOUNTS_FILE = new File("accounts.txt");
	//private static final String TRANSACTIONS_FILENAME = "transactions.txt";
	Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
	Map<String, Customer> customersFromUsername = new HashMap<String, Customer>();
	Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	Map<String, Employee> employeesFromUsername = new HashMap<String, Employee>();
	
	//is using a sorted map so the it is sorted when customer views it
	Map<Integer, Account> accounts = new TreeMap<Integer, Account>();
	
	
	public Map<Integer, Account> getAccounts() {
		return accounts;
	}

	static Database database;

	private Database(){
		//checks for file existence, if files does not exist create them and create a new admin account
		try{
			if(!CUSTOMERS_FILE.exists()){
				Logging.getLogger().warn("No Customers File found, creating empty one");
				CUSTOMERS_FILE.createNewFile();
			}
			if(!EMPLOYEES_FILE.exists()){
				EMPLOYEES_FILE.createNewFile();
				
				//create an admin account
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEES_FILE))){
					Logging.getLogger().warn("No employee file found, create one with default admin account");
					
					Employee employee = new Employee(0, "admin", "supersecertpassword", true);
					bw.write(String.format("%s:%s:%s:%s", employee.getId(), employee.getUsername(), employee.getPassword(), employee.isAdmin));
					bw.newLine();
				}
			}
			if(!ACCOUNTS_FILE.exists()){
				Logging.getLogger().warn("No accounts file found, creating an empty one");
				ACCOUNTS_FILE.createNewFile();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
		load();
	}
	
	/**
	 * 
	 * @return the instance of the Database
	 */
	public static Database getDatabase(){
		if (database == null){
			Logging.getLogger().debug("Creating Database Object");
			
			database = new Database();
		} 
		
		return database;
	}
	
	/**
	 * forces Database to reread the database files and repopulate the 
	 * internal data
	 */
	public void reload(){
		Logging.getLogger().warn("Reloading File from Database");
		

		database = new Database();
		
		
	}
	
	/**
	 * Returns Customer object associated with id
	 * @param id Integer of Customer object to be retrieved
	 * @return Customer object associated with id, or null if none was found
	 */
	public  Customer getCustomer(int id){
		
		return customers.get(id);
	}
	
	/**
	 * Returns Customer object associated with username
	 * @param username String of Customer object to be retrieved
	 * @return Customer object associated with username, or null if none was found
	 */
	public Customer getCustomerByUsername(String username){
		
		return customersFromUsername.get(username);
	}
	
	/**
	 * 
	 * @param username String of username
	 * @return False if there is a customer or employee object in database with a matching username, true otherwise 
	 */
	public boolean containsUsername(String username){
		return (customersFromUsername.containsKey(username) || employeesFromUsername.containsKey(username));
	}
	
	/**
	 * adds a Customer object into the database, it will set the customer's id to be a unique id in the database
	 * @param user Customer object to add
	 */
	public void addCustomer(Customer user){
		user.setId(customers.size());
		customers.put(user.getId(), user);
		customersFromUsername.put(user.getUsername(), user);
		Logging.getLogger().info("Creating new customer in database: " + user.toString());
	}
	
	/**
	 * adds an Employee object into the database, it will set the employee's id to be a unique id in the database
	 * @param user Customer object to add
	 */
	public void addEmployee(Employee employee){
		employee.setId(employees.size());
		employees.put(employee.getId(), employee);
		employeesFromUsername.put(employee.getUsername(), employee);
		Logging.getLogger().info("Creating new employee in database: " + employee.toString());
	}
	
	/**
	 * Returns Employee object associated with employee
	 * @param username String of Employee object to be retrieved
	 * @return Employee object associated with employee, or null if none was found
	 */
	public Employee getEmployee(int id){
		return employees.get(id);
	}
	
	/**
	 * Returns Employee object associated with username
	 * @param username String of Employee object to be retrieved
	 * @return Employee object associated with username, or null if none was found
	 */
	public Employee getEmployeeByUsername(String username){
		return employeesFromUsername.get(username);
	}
	
	/**
	 * returns the Bank Account associated with this id
	 * @param id Integer 
	 * @return
	 */
	public Account getAccount(int id){
		return accounts.get(id);
	}
	
	/**
	 * adds an Account object into the database, it will set the account's id to be a unique id in the database
	 * @param user Account object to add
	 */
	public void addAccount(Account account){
		account.setId(accounts.size());
		accounts.put(account.getId(), account);
		Logging.getLogger().info("Creating new account in database: " + account.toString());
	}
	
	//loads all of the maps from its files
	private void load(){
		Logging.getLogger().debug("Loading up database from file");
		
		loadCustomers();
		loadEmployees();
		loadAccounts();
	}
	
	//loads Bank Accounts from file
	private void loadAccounts() {
		Logging.getLogger().debug("loading Accounts from file");
		
		try(BufferedReader br = new BufferedReader(new FileReader(ACCOUNTS_FILE))){
			String line;
			
			while((line = br.readLine())!= null){
				String[] args = line.split(":");
				
				int id = Integer.parseInt(args[0]);
				String name = args[1];
				String type = args[2];
				double balance = Double.parseDouble(args[3]);
				boolean isApprove = Boolean.parseBoolean(args[4]);
				Account account = new Account(id, name, type, balance, isApprove);
				accounts.put(account.getId(), account);
			}
			

		
			} catch (FileNotFoundException e) {
			Logging.getLogger().error(String.format("Unable to find Accounts File at %s", ACCOUNTS_FILE.getAbsolutePath()), e);
			e.printStackTrace();
			
			} catch (IOException e) {
				Logging.getLogger().error("IOException on Accounts File", e);
				e.printStackTrace();
			}
	}

	//Saves Bank Accounts to file
	private void saveAccounts(){
		Logging.getLogger().debug("Saving Accounts to file");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCOUNTS_FILE));){
			for (Account account: accounts.values()){
				bw.write(String.format("%s:%s:%s:%s:%s", account.getId(), account.getAccountName(), account.getAccountType(), account.getBalance(), account.isApproved()));
				bw.newLine();
			}
		} catch (IOException e) {
			Logging.getLogger().error("IOException on Accounts File", e);
			e.printStackTrace();
		}
	}
	
	// Saves Bank Accounts to file
	private void saveCustomers() {
		Logging.getLogger().debug("Saving Customers to file");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))){
			
			for (Customer customer: customers.values()){
				bw.write(String.format("%s:%s:%s", customer.getId(), customer.getUsername(), customer.getPassword()));
				// 
				for (Integer ids : customer.getAccountIds()){
					bw.write(":" + String.valueOf(ids));
				}
				bw.newLine();
			}
		} catch (IOException e) {
			Logging.getLogger().error("IOException on Customers File", e);
			e.printStackTrace();
		}
	}
	
	//Loads Customers from file
	private void loadCustomers() {
		Logging.getLogger().debug("Loading up Customers from file");
		try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMERS_FILE))){
			String line;
			
			// each every line in file and loads a new customer
			while ((line = br.readLine())!= null){
				String[] args = line.split(":");
				int id = Integer.parseInt(args[0]);
				String username = args[1];
				String password = args[2];
				
				
				List<Integer> accounts = new LinkedList<Integer>();
				// anything else beyond is a 1 to many relationship with accounts
				for (int i = 3; i < args.length; ++i){
					accounts.add(Integer.parseInt(args[i]));
				}
				
				Customer customer = new Customer(id, username, password, accounts);
				customers.put(customer.getId(), customer);
				customersFromUsername.put(customer.getUsername(), customer);
			}
		} catch (FileNotFoundException e) {
			Logging.getLogger().error(String.format("Unable to find Customers File at %s", CUSTOMERS_FILE.getAbsolutePath()), e);
			e.printStackTrace();
			
		} catch (IOException e) {
			Logging.getLogger().error("IOException on Customers File", e);
			e.printStackTrace();
		}
	}

	/**
	 * Saves the database to file
	 */
	public void save(){
		saveCustomers();
		saveEmployees();
		saveAccounts();
		
	}
	

	//loads Employees from File
	private void loadEmployees(){
		Logging.getLogger().debug("Loading up Employees from file");
		try(BufferedReader br = new BufferedReader(new FileReader(EMPLOYEES_FILE))){
			String line;
			
			while((line = br.readLine())!= null){
				String[] args = line.split(":");
				
				int id = Integer.parseInt(args[0]);
				String username = args[1];
				String password = args[2];
				boolean isAdmin = Boolean.valueOf(args[3]);
				Employee e = new Employee(id, username, password, isAdmin);
				employees.put(e.getId(), e);
				employeesFromUsername.put(e.getUsername(), e);
			}
			
		} catch (FileNotFoundException e) {
			Logging.getLogger().error(String.format("Unable to find Employees File at %s", EMPLOYEES_FILE.getAbsolutePath()), e);
			e.printStackTrace();
			
		} catch (IOException e) {
			Logging.getLogger().error("IOException on Employees File", e);
			e.printStackTrace();
		}
	}
	
	//saves Employees to file
	private void saveEmployees(){
		Logging.getLogger().debug("Saving Employees to File");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEES_FILE));){
			for (Employee employee: employees.values()){
				bw.write(String.format("%s:%s:%s:%s", employee.getId(), employee.getUsername(), employee.getPassword(), employee.isAdmin));
				bw.newLine();
			}
		} catch (IOException e) {
			Logging.getLogger().error("IOException on Employees File", e);
			e.printStackTrace();
		}
	}
}