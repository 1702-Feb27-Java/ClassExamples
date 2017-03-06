package com.bankingapp.bankaccount;

public abstract class Account
{
    public int amount;
    long accountNumber;

    abstract int getAmount();

    abstract void setAmount(int amnt);
    
    abstract void addAmount(int amnt);

    abstract long getAccountNumber();


}

