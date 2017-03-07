package com.revature.testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankingproject.Customer;
import com.revature.bankingproject.Employee;

public class CustomerTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Test
	public void test() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\testing\\CustomerTestCLI.txt"));)
			{
			//test create a customer account
			Customer customer = new Customer();
			String username = br.readLine();
			String password = br.readLine();
			String createCustomer = customer.signUpForServices(username, password, username.hashCode());
			assertEquals("Account created successfully!", createCustomer);

			//test login with a customer username and password
			String customerId = customer.login(username, password);
			assertEquals(Integer.toString(username.hashCode()), customerId);
			
			//test sign up for savings account
			String savingsAccountResult = customer.signUpForSavingsAccount(Integer.parseInt(customerId));
			assertEquals("Applied for Savings Account!", savingsAccountResult);
			
			//test sign up for checking account
			String checkingAccountResult = customer.signUpForCheckingAccount(Integer.parseInt(customerId));
			assertEquals("Applied for Checking Account!", checkingAccountResult);
			
			//approve the accounts so user can access them
			Employee employeeApprover = new Employee();
			String[] accounts = employeeApprover.getAccountApplications(br);
			String[] accounts2 = employeeApprover.getAccountApplications(br);
			
			//get accounts for customer
			ArrayList<String> accountsForCustomer = customer.getAccountsForCustomer(Integer.parseInt(customerId));
			assertEquals("savings", accountsForCustomer.get(0));
			assertEquals("$0", accountsForCustomer.get(1));
			assertEquals("checking", accountsForCustomer.get(2));
			assertEquals("$0", accountsForCustomer.get(3));
			
			//test deposit money in both accounts
			int savingsBalance = customer.depositMoney(Integer.parseInt(customerId), "savings", 300);
			int checkingBalance = customer.depositMoney(Integer.parseInt(customerId), "checking", 1200);
			
			//make sure new balances saved correctly
			ArrayList<String> accountsAfterDeposit = customer.getAccountsForCustomer(Integer.parseInt(customerId));
			assertEquals("savings", accountsAfterDeposit.get(0));
			assertEquals("$" + Integer.toString(savingsBalance), accountsAfterDeposit.get(1));
			assertEquals("checking", accountsAfterDeposit.get(2));
			assertEquals("$" + Integer.toString(checkingBalance), accountsAfterDeposit.get(3));
			
			//withdraw from both accounts
			int savingsBalance2 = customer.withdrawMoney(Integer.parseInt(customerId), "savings", 50);
			int checkingBalance2 = customer.withdrawMoney(Integer.parseInt(customerId), "checking", 400);
			
			//make sure new balances saved correctly
			ArrayList<String> accountsAfterWithdraw = customer.getAccountsForCustomer(Integer.parseInt(customerId));
			assertEquals("savings", accountsAfterWithdraw.get(0));
			assertEquals("$" + Integer.toString(savingsBalance2), accountsAfterWithdraw.get(1));
			assertEquals("checking", accountsAfterWithdraw.get(2));
			assertEquals("$" + Integer.toString(checkingBalance2), accountsAfterWithdraw.get(3));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
