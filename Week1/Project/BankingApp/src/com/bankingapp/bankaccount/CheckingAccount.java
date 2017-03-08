package com.bankingapp.bankaccount;

public class CheckingAccount extends Account
{

    public int getAmount()
    {
        return this.amount;
    }

    public void setAmount(int  fields)
    {
        this.amount =fields;
    }
    
    public void addAmount(int amnt)
    {
        this.amount+=amnt;
    }
    
    public int getAccountNumber()
    {
        return this.accountNumber;
    }

    public void setAccountNumber(int newNumber)
    {
        this.accountNumber= newNumber;
    }

    //CONSTRUCTORS FOR AN ACCOUNT TYPE
    public CheckingAccount ()
    {
        this.setAmount(0);
        //RANDOMNUMBER FOR ACCOUNT NUMBER
    }

    public CheckingAccount (int amt)
    {
        this.setAmount(amt);
        
        //RADOMNUMBER FOR ACCOUNT NUMBER

    }
}

