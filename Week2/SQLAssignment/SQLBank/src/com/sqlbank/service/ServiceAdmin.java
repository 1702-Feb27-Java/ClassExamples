package com.sqlbank.service;

import java.util.ArrayList;

import com.sqlbank.peoplepack.People;

public interface ServiceAdmin extends Service
{
	ArrayList<People> viewAccounts ();
	boolean viewAaccount(String username);
	
	
}
