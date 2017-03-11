package com.revature.q6;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class EvenOrOddTest {

	EvenOrOdd evenOrOdd = new EvenOrOdd();

	@Test
	public void testTwo() {
		assertEquals(evenOrOdd.isEven(2), true);
	}
	
	@Test
	public void testOne() {
		assertEquals(evenOrOdd.isEven(1), false);
	}

	@Test
	public void testZero() {
		assertEquals(evenOrOdd.isEven(0), true);
	}
	
	@Test
	public void testNegativeOne() {
		assertEquals(evenOrOdd.isEven(-1), false);
	}
	
	@Test
	public void testNegativeTwo() {
		assertEquals(evenOrOdd.isEven(-2), true);
	}
}
