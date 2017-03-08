package com.revature.testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankingproject.Admin;
import com.revature.bankingproject.Customer;
import com.revature.bankingproject.Main;

import static com.revature.bankingproject.Main.*;

public class MainTest {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File("src\\com\\revature\\bankingproject\\Data.txt");
		file.delete();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"src\\com\\revature\\bankingproject\\Data.txt", true));
		
		bw.write("admin:" + "benwebsteradmin".hashCode() + ":" + "benwebsteradmin" + ":" + "password" + ":" + "benwebsteradmin".hashCode());
		bw.newLine();
		bw.close();
	}

	@Test
	public void test() {
		try(BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\testing\\MainTestCLI.txt"));)
			{
			
			System.out.println("test");
			
			mainMenuOption(br);
			
			Customer viewAccounts = new Customer();
			ArrayList<String> viewAccountsResult = viewAccounts.getAccountsForCustomer("boblikesunicorns".hashCode());	
			assertEquals("savings", viewAccountsResult.get(0));
			assertEquals("$65", viewAccountsResult.get(1));
			assertEquals("checking", viewAccountsResult.get(2));
			System.out.println(viewAccountsResult.get(3));
			assertEquals("$1150", viewAccountsResult.get(3));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
