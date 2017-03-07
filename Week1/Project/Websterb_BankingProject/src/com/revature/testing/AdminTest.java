package com.revature.testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankingproject.Admin;
import com.revature.bankingproject.Customer;
import com.revature.bankingproject.Employee;

public class AdminTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test(){
	try (BufferedReader br = new BufferedReader(new FileReader(
			"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\testing\\AdminTestCLI.txt"));)
		{
			//test admin login
			Admin admin = new Admin();
			String username = br.readLine();
			String password = br.readLine();
			System.out.println(username + " " + password);
			boolean loginResult = admin.login(username, password);
			assertEquals(true, loginResult);
			
			//test retrieving customerid based on username
			username = br.readLine();
			String customerId = admin.getCustomerId(username);
			assertEquals(Integer.toString(username.hashCode()), customerId);
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

}
