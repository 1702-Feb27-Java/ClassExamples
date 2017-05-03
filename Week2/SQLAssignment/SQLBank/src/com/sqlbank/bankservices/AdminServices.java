package com.sqlbank.bankservices;

import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;

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
