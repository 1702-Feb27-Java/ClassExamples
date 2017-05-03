package com.sqlbank.bankservices;

import com.sqlbank.peoplepack.Customer;

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
