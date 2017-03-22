package com.bankingapp.bankservices;

import com.bankingapp.peoplepack.Customer;

//Services only and employee can do
public interface EmployeeServices
{
    //View a customers account
    public void viewCustomers ();

    //Approve a new CheckingAccount
    public void  approveChkAccount (Customer c, boolean choice);

    //Approve a new SavingsAccount
    public void  approveSavAccount (Customer c, boolean choice);
}