
public abstract class Account
{
	
	private String username;
	private double balance;
	private boolean appliedFor = false, approved = false;
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	
	public boolean isAppliedFor()
	{
		return appliedFor;
	}

	public void setAppliedFor(boolean appliedFor)
	{
		this.appliedFor = appliedFor;
	}

	public boolean isApproved()
	{
		return approved;
	}

	public void setApproved(boolean approved)
	{
		this.approved = approved;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	void deposit(double amount)
	{
		this.setBalance(this.getBalance() + amount);
	}
	
	void withdraw(double amount)
	{
		this.setBalance(this.getBalance() - amount);
	}
	

}
