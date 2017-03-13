package banking.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.chainsaw.Main;
import org.junit.Before;
import org.junit.Test;

import banking.BankDatabase;
import banking.BankMember;
import banking.CommandParser;
import banking.Type;

public class CommandParserTest {
	private BankDatabase db;
	CommandParser parse;
	File file;
	BankMember bm;
	@Before
	public void setUp() throws Exception {
		db = new BankDatabase();
		file = new File("banking.txt");
		if(file.exists()){
			file.delete();
			file.createNewFile();
		}
		else{
			file.createNewFile();
		}
		parse = new CommandParser(file, db);
		bm = new BankMember("Boss", Type.ADMIN);
		bm.setUserName("Bo$$");
		bm.setPassword("123");
		db.addMember(bm);
	}

	/*
	 * tests the output of the file after a supplied text file of command is run on it
	 * makes sure methods are some times call incorrectly
	 * 
	 * 
	 */
	@Test
	public void test() {
		//String ma[] = {"true"};
		//assertEquals(1, db.getArray().size());
		parse.getCommands(true);
		File fileTest = new File("banking.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(fileTest);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//asserts that the commands included in the test.txt file were executed so the 
		// output to the bank.txt file is exspected
		String test = scan.nextLine();
		assertEquals("Bo$$:Boss:123:ADMIN:NONE:NONE:0:0", test);
		test = scan.nextLine();
		assertEquals("Zack:Zack Stefan:900:CUSTOMER:ACTIVE:ACTIVE:100:7", test);
		test = scan.nextLine();
		assertEquals("Employee:Employee 1:pass:EMPLOYEE:NONE:NONE:0:0", test);
		
		//fail("Not yet implemented");
	}

}
