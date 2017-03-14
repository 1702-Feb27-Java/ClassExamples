package dao;

public interface Dao {
	//add customer
	void addCustomer(String firstName,String lastName, String username,String password);
	// Open an account;
	
	
// open account and deposit
	void openAccount(int accountTypeId, double balance);
	
	
	// get balance given accountid;
	
	public double getBalance( int accountid );
	
	public void deposit(int accntid,double amount);
}


