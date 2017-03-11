package com.revature.bankingapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountMenuTest.class, CreateAccountMenuTest.class, CustomerMenuTest.class, DatabaseTest.class,
		EmployeeMenuTest.class, LoginMenuTest.class })
public class AllTests {

}
