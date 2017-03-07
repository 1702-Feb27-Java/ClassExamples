package com.revature.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MathClassTest {
	
	MathClass mc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("===Before Class===");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("===After Class===");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("===Before Method===");
		
		mc = new MathClass();
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("===After Method===");
		
		mc = null;
	}

	@Test
	public void testAddition() {
		System.out.println("===Running Addition===");
		assertEquals("Expect 15 from addition", 15, mc.addition(10, 5));
	}
	
	@Test
	public void testSubtraction() {
		System.out.println("===Running Subtraction===");
		assertEquals("Expect 5 from subtraction", 5, mc.subtraction(10, 5));
	}

	@Test
	public void testMultiplication() {
		System.out.println("===Running Multiplication===");
		assertEquals("Expect 50 from multiplication", 50, mc.multiplication(10, 5));
	}
	
	@Test
	public void testDividion() {
		System.out.println("===Running Division===");
		assertEquals("Expect 2 from division", 2, mc.division(10, 5));
	}
	
	@Test(expected=ArithmeticException.class)
	public void division2(){
		mc.division(2, 0);
	}
	


}
