package Users;
import Accounts.Account;
import Accounts.Checking;
import Accounts.Savings;

public class Customer extends Person
{
	
	private Checking checkingAccount;
	private Savings savingsAccount;
	private Employee banker;
	
	
	
	public Customer(int UserID, String fName, String lName, String uName, String password)
	{
		this.setUserId(UserID);
		this.setFirstName(fName);
		this.setLastName(lName);
		this.setUsername(uName);
		this.setPassword(password);
		this.setType(3);
	}

	public Checking getCheckingAccount()
	{
		return checkingAccount;
	}

	public void setCheckingAccount(Checking checkingAccount)
	{
		this.checkingAccount = checkingAccount;
	}

	public Savings getSavingsAccount()
	{
		return savingsAccount;
	}

	public void setSavingsAccount(Savings savingsAccount)
	{
		this.savingsAccount = savingsAccount;
	}

	public Employee getBanker()
	{
		return banker;
	}

	public void setBanker(Employee banker)
	{
		this.banker = banker;
	}

	public Customer()
	{
		// TODO Auto-generated constructor stub
	}
	
	private void deposit(Account account, double value)
	{
		
		account.deposit(value);
		
	}
	
	private void withdraw(Account account, double value)
	{
		
		account.withdraw(value);
		
	}

}
