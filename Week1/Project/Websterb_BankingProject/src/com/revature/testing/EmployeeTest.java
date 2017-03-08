package com.revature.testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.revature.bankingproject.Customer;
import com.revature.bankingproject.Employee;

public class EmployeeTest {

	@Test
	public void test() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\testing\\EmployeeTestCLI.txt"));)
			{
			Employee employee = new Employee();
			String username = br.readLine();
			String password = br.readLine();
			
			//Test Creating an employee account
			String employeeAccountCreationResult = employee.createEmployeeAccount(username, password);
			assertEquals("Account created successfully!", employeeAccountCreationResult);
			
			//test logging in as an employee
			String employeeLoginResult = employee.login(username, password);
			assertEquals(Integer.toString(username.hashCode()), employeeLoginResult);
			
			//test getting all accounts tied to this employee
			ArrayList<String[]> accountList = employee.viewCustomerAccounts("username".hashCode());
			for(int i = 0; i < accountList.size(); i++){
				String[] customerStringArray = accountList.get(i);
				switch(i){
				case 1:
					assertEquals("customer",customerStringArray[0]);
					assertEquals("-1291972862",customerStringArray[1]);
					assertEquals("boblikesunicorns",customerStringArray[2]);
					assertEquals("supersecretpassword",customerStringArray[3]);
					assertEquals("-287807177",customerStringArray[4]);
					break;
				case 2:
					assertEquals("savings",customerStringArray[0]);
					assertEquals("-1291972862",customerStringArray[1]);
					assertEquals("true",customerStringArray[2]);
					assertEquals("55",customerStringArray[3]);
					assertEquals("-287807177",customerStringArray[4]);
					break;
				case 3:
					assertEquals("checking",customerStringArray[0]);
					assertEquals("-1291972862",customerStringArray[1]);
					assertEquals("true",customerStringArray[2]);
					assertEquals("1300",customerStringArray[3]);
					assertEquals("-287807177",customerStringArray[4]);
					break;
				case 4:
					assertEquals("customer",customerStringArray[0]);
					assertEquals("972571480",customerStringArray[1]);
					assertEquals("captaininsano",customerStringArray[2]);
					assertEquals("reallygoodpassword",customerStringArray[3]);
					assertEquals("-287807177",customerStringArray[4]);
					break;
				case 5:
					assertEquals("savings",customerStringArray[0]);
					assertEquals("972571480",customerStringArray[1]);
					assertEquals("true",customerStringArray[2]);
					assertEquals("250",customerStringArray[3]);
					assertEquals("-287807177",customerStringArray[4]);
					break;
				case 6:
					assertEquals("checking",customerStringArray[0]);
					assertEquals("972571480",customerStringArray[1]);
					assertEquals("true",customerStringArray[2]);
					assertEquals("800",customerStringArray[3]);
					assertEquals("-287807177",customerStringArray[4]);
					break;
				}
			}
			
			//test getting unapproved account applications
			Customer newCustomer = new Customer();
			String newCustomerUsername = br.readLine();
			String newCustomerPassword = br.readLine();
			newCustomer.signUpForServices(newCustomerUsername, newCustomerPassword, newCustomerUsername.hashCode());
			newCustomer.signUpForSavingsAccount(newCustomerUsername.hashCode());
			
			String[] getAccountApplications = employee.getAccountApplications(br);
			assertEquals("savings", getAccountApplications[0]);
			assertEquals(Integer.toString(newCustomerUsername.hashCode()), getAccountApplications[1]);
			assertEquals("true", getAccountApplications[2]);
			
			//approve account
			boolean result = employee.approveAccountApplications(getAccountApplications, br);
			assertEquals(true, result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
