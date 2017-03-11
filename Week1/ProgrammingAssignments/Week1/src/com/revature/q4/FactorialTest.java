package com.revature.q4;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class FactorialTest {

	Factorial f = new Factorial();

	@Test
	public void testZero() {
		assertEquals(f.fact(0),1);
	}
	
	@Test
	public void testOne() {
		assertEquals(f.fact(1),1);
	}
	
	@Test
	public void testTwo() {
		assertEquals(f.fact(2),2);
	}

	@Test
	public void testThree() {
		assertEquals(f.fact(3),6);
	}
	
	@Test
	public void test20() {
		assertEquals(f.fact(20), 2432902008176640000l);
	}
}
