package com.bankingapp.bankaccount;

public abstract class Account
{
    public int amount;
    int accountNumber;

    abstract int getAmount();

    abstract void setAmount(int amnt);
    
    abstract void addAmount(int amnt);

    abstract int getAccountNumber();
    
    abstract void setAccountNumber(int newNumber);


}

