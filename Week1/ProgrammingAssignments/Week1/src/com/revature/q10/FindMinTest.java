package com.revature.q10;

import org.junit.Test;

import org.junit.Assert;
/**
 * 
 * @author Aaron Camm
 *
 */
public class FindMinTest {
	@Test
	public void testMinA(){
		int n = FindMin.getMin(-1, 1);
		Assert.assertEquals(-1, n);
	}
	
	@Test
	public void testMinB(){
		int n = FindMin.getMin(1, -1);
		Assert.assertEquals(-1, n);
	}
	
	@Test
	public void testEquals(){
		int n = FindMin.getMin(-1, -1);
		Assert.assertEquals(-1, n);
		
		
	}
	
}
