package com.revature.q3;



import org.junit.Assert;
import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class ReverseStringTest {

	ReverseString r = new ReverseString();
	
	@Test
	public void testSample() {
		String s = "Hello World";
		String sAnswer = "dlroW olleH";
		
		Assert.assertEquals(r.reverse(s), sAnswer);
		
	}
	
	@Test
	public void testEmpty(){
		String s = "";
		String sAnswer = "";
		
		Assert.assertEquals(r.reverse(s), sAnswer);
		
	}
	
	@Test
	public void testSingleLetter(){
		String s = "A";
		String sAnswer = "A";
		
		Assert.assertEquals(r.reverse(s), sAnswer);
		
	}
	
	@Test
	public void testTwoLetters(){
		String s = "AB";
		String sAnswer = "BA";
		
		Assert.assertEquals(r.reverse(s), sAnswer);
	}
	
	@Test
	public void testOdd(){
		String s = "12345";
		String sAnswer = "54321";
		
		Assert.assertEquals(r.reverse(s), sAnswer);
		
	}
	
	@Test
	public void testEven(){
		String s = "123456";
		String sAnswer = "654321";
		
		Assert.assertEquals(r.reverse(s), sAnswer);
		
	}

}
