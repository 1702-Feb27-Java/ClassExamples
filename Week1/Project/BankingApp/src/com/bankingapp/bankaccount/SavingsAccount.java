package com.bankingapp.bankaccount;

public class SavingsAccount extends Account
{

    public int getAmount()
    {
        return this.amount;
    }

    public void setAmount(int amnt)
    {
        this.amount = amnt;
    }
    
    public void addAmount(int amnt)
    {
        this.amount+=amnt;
    }
    
    public long getAccountNumber()
    {
        return this.accountNumber;
    }

    //CONSTRUCTORS FOR AN ACCOUNT TYPE
    public SavingsAccount ()
    {
        this.setAmount(0);
        //RADOMNUMBER FOR ACCOUNT NUMBER
    }

    public SavingsAccount (int amt)
    {
        this.setAmount(amt);

        //RADOMNUMBER FOR ACCOUNT NUMBER

    }
}

