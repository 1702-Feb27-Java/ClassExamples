package banking.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banking.BankDatabase;
import banking.BankMember;
import banking.Type;

public class BankDatabaseTest {
	private BankDatabase db;

	@Before
	public void setUp() throws Exception {
		BankMember bm = new BankMember("Test", Type.CUSTOMER);
		bm.setUserName("us");
		bm.setPassword("123");
		db = new BankDatabase();
		db.addMember(bm);
	}

	//tests that add worked and the data base holds 1 element
	@Test
	public void testAdd() {
		assertEquals(1, db.getArray().size());
	}
	
	//tests getting the userName 
	@Test
	public void testGetUserName(){
		
		BankMember test = db.getAccount("us");
		assertEquals("123", db.getAccount("us").getPassword());
	}
	
	@Test 
	public void checkUserName(){
		assertTrue(db.checkUserNameAvailability("this is a free userName")); // this a free userName 
		assertFalse(db.checkUserNameAvailability("us")); //this is a taken userName
	}

}
