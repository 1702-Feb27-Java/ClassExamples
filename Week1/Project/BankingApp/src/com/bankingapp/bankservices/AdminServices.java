package com.bankingapp.bankservices;

import com.bankingapp.peoplepack.Admin;
import com.bankingapp.peoplepack.Customer;
import com.bankingapp.peoplepack.Employee;
import com.bankingapp.peoplepack.People;

//Services only an admin can do
public interface AdminServices
{
    //View any account
    //of Person p
    public void viewAccounts (String person);

    public void viewAccounts ();

    //Edit any account
    public boolean editUsername (Customer name, String option);
    public void editPassword (Customer name, String option);
    public boolean editUsername (Employee name, String option);
    public void editPassword (Employee name, String option);
    public boolean editUsername (Admin name, String option);
    public void editPassword (Admin name, String option);
}
