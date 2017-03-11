package com.revature.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MainTest.class, CustomerTest.class, EmployeeTest.class, AdminTest.class})
public class AllTests {

}
