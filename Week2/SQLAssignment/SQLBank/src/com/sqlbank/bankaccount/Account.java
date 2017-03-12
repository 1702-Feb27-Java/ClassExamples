package com.sqlbank.bankaccount;

public class Account
{
	private double amount;
    private String type;
    private String status;
    private String resolver;

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
		if (this.getStatus().equals("Approved"))
		{
			if (amount > this.getAmount())
			{
				return false;
			}
			this.setAmount(this.getAmount() + (-1* amount));
			return true;
		}
		return false;
	}
	public boolean deposit(double amount)
	{
		if (this.getStatus().equals("Approved"))
		{
			this.setAmount(this.getAmount() + amount);
			return true;
		}
		return false;	
	}
}

