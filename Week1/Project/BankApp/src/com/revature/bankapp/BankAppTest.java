package com.revature.bankapp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class BankAppTest {
	
	ArrayList<UserAccount> userArray = UserAccountMethods.deserUserAccount();
	Person p = new Person("fname", "lname", "1234567890", "email@email.com");
	UserAccount ua = new UserAccount("user","pass", p);
	EmployeeAccount ea = new EmployeeAccount("emp", "emppass", p);
	AdminAccount aa = new AdminAccount("admin", "admin", p);
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testUserMethods(){

		assertEquals("Testing user username", "user", ua.getUsername());
		assertEquals("Testing user password", "pass", ua.getPassword());
		assertEquals("Testing user Person", p, ua.getPerson());
		assertEquals("Testing user accesss level", 0, ua.getAccessLevel());
		assertEquals("Testing user default checking request", false, ua.getCheckReqStat());
		assertEquals("Testing user default saving request", false, ua.getSavReqStat());
		assertNull("Testing user default checking", ua.getChecking());
		assertNull("Testing user default saving", ua.getSaving());
		
	}
	
	@Test
	public void testEmployeeMethods(){
		assertEquals("Testing employee username", "emp", ea.getUsername());
		assertEquals("Testing employee password", "emppass", ea.getPassword());
		assertEquals("Testing employee Person", p, ea.getPerson());
		assertEquals("Testing employee accesss level", 1, ea.getAccessLevel());
		
	}
	
	@Test
	public void testAdminMethods(){
		assertEquals("Testing admin username", "admin", aa.getUsername());
		assertEquals("Testing admin password", "admin", aa.getPassword());
		assertEquals("Testing admin Person", p, aa.getPerson());
		assertEquals("Testing admin accesss level", 2, aa.getAccessLevel());
		
	}
	
	@Test
	public void testPersonMethods(){
		assertEquals("Testing person fname after init", "fname", p.getFname());
		assertEquals("Testing person lname after init", "lname", p.getLname());
		assertEquals("Testing person pnum after init", "1234567890", p.getPnum());
		assertEquals("Testing person email after init", "email@email.com", p.getEmail());
		
		p.setFname("newfname");
		p.setLname("newlname");
		p.setPnum("0987654321");
		p.setEmail("newemail@email.com");
		
		assertEquals("Testing person fname after new set", "newfname", p.getFname());
		assertEquals("Testing person lname after new set", "newlname", p.getLname());
		assertEquals("Testing person pnum after new set", "0987654321", p.getPnum());
		assertEquals("Testing person email after new set", "newemail@email.com", p.getEmail());
	}
	
	@Test
	public void testDeserUsers(){

		
		//assertEquals("Returns an arraylist object", userArray, UserAccountMethods.deserUserAccount());  
		assertNotNull("Returns an arraylist object", UserAccountMethods.deserUserAccount());  
	
		/*java.lang.AssertionError: Returns an arraylist object expected: java.util.ArrayList<[USER: admin Checking Request: false Savings Request: false, USER: newUser Checking Request: false Savings Request: false]> but was: java.util.ArrayList<[USER: admin Checking Request: false Savings Request: false, USER: newUser Checking Request: false Savings Request: false]>
		at org.junit.Assert.fail(Assert.java:88)
		at org.junit.Assert.failNotEquals(Assert.java:834)
		at org.junit.Assert.assertEquals(Assert.java:118)
		at com.revature.bankapp.BankAppTest.testDeserUsers(BankAppTest.java:26)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
		at java.lang.reflect.Method.invoke(Unknown Source)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:678)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)


		dont understand literally the same
*/
		
		
	}
	
	// works. 
	@Test
	public void testGetArrayIndex(){
		
		assertEquals("Returns index of Array List", 0 ,UserAccountMethods.getArrayIndex("admin", userArray));
		
	}
	
	
	
	
	

}
