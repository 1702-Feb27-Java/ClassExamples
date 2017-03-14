package com.sqlbank.bankaccount;

public class Account
{
	private int id;
	private double amount;
    private String type;
    private String status;
    private String resolver;

    public final int getId()
	{
		return id;
	}
	public final void setId(int id)
	{
		this.id = id;
	}
	public final double getAmount()
	{
		return amount;
	}
	public final void setAmount(double amount)
	{
		this.amount = amount;
	}
	public final String getType()
	{
		return type;
	}
	public final void setType(String type)
	{
		this.type = type;
	}
	public final String getStatus()
	{
		return status;
	}
	public final void setStatus(String status)
	{
		this.status = status;
	}
	public final String getResolver()
	{
		return resolver;
	}
	public final void setResolver(String resolver)
	{
		this.resolver = resolver;
	}
	
	public boolean withdraw(double amount)
	{
		if (amount > this.getAmount() || (this.getAmount() - amount) == 0)
		{
			return false;
		}
		this.setAmount(this.getAmount() + (-1* amount));
		return true;
	}
	public void deposit(double amount)
	{
		this.setAmount(this.getAmount() + amount);
	}
	public void printAccount()
	{
		System.out.println("ACCOUNTS =====================");
		System.out.println("\tAccount Id: " + this.getId());
		System.out.println("\t\tType: " + this.getType());
		System.out.println("\t\t\tBalance: " + this.getAmount());
		System.out.println("\t\t\tStatus: " + this.getStatus());
		System.out.println("\t\t\tResolver: " + this.getResolver());
	}
	public Account(int id, double amount, String type, String status, String resolver)
	{
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.status = status;
		this.resolver = resolver;
	}
	
}

