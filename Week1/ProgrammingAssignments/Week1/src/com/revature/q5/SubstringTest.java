package com.revature.q5;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class SubstringTest {
	
	Substring s = new Substring();

	@Test
	public void testSample() {
		String str = "I am going to get ya, some nice ice scream";
		String strAnswer = "I am going to get ya";
		
		assertEquals(s.substring(str, 20), strAnswer);
	}

	@Test
	public void testSubLengthOne() {
		String str = "I am going to get ya, some nice ice scream";
		String strAnswer = "I";
		
		assertEquals(s.substring(str, 1), strAnswer);
	}
	
	@Test
	public void testSubLengthZero() {
		String str = "I am going to get ya, some nice ice scream";
		String strAnswer = "";
		
		assertEquals(s.substring(str, 0), strAnswer);
	}
	
	@Test
	public void testSubLengthTwo() {
		String str = "I am going to get ya, some nice ice scream";
		String strAnswer = "I ";
		
		assertEquals(s.substring(str, 2), strAnswer);
	}
	
	@Test
	public void testLengthOneSubLengthOne() {
		String str = "I";
		String strAnswer = "I";
		
		assertEquals(s.substring(str, 1), strAnswer);
	}
	
	@Test
	public void testLengthOneSubLengthZero() {
		String str = "I";
		String strAnswer = "";
		
		assertEquals(s.substring(str, 0), strAnswer);
	}
	
}
