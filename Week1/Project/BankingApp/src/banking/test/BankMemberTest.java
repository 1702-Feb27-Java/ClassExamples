package banking.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banking.BankMember;
import banking.Status;
import banking.Type;

public class BankMemberTest {
	private BankMember test;

	@Before
	public void setUp() throws Exception {
		test = new BankMember("Test", Type.CUSTOMER);
		test.setChecking(8);
		test.setCheckingStatus(Status.APPLIED);
		test.setName("this is setting Name");
		test.setPassword("password");
		test.setSavings(9);
		test.setSavingStatus(Status.APPLIED);
		test.setUserName("userName");
		test.setType(Type.ADMIN);
		
	}

	@Test
	public void testGetterAndSetter() {
		assertEquals(8, test.getChecking());
		assertEquals(9, test.getSavings());
		assertEquals(Status.APPLIED, test.getCheckingStatus());
		assertEquals(Status.APPLIED, test.getSavingStatus());
		assertEquals("this is setting Name", test.getName());
		assertEquals("password", test.getPassword());
		assertEquals("userName", test.getUserName());
		assertEquals(Type.ADMIN, test.getType());
	}

}
