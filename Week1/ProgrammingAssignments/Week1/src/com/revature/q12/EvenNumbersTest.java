package com.revature.q12;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
/**
 * 
 * @author Aaron Camm
 *
 */
public class EvenNumbersTest {

	@Test
	public void testOne() {
		ArrayList<Integer> result = EvenNumbers.getEvenNumbers(1);
		Assert.assertArrayEquals(new Integer[]{}, result.toArray());
	}
	
	@Test
	public void testTwo() {
		ArrayList<Integer> result = EvenNumbers.getEvenNumbers(2);
		Assert.assertArrayEquals(new Integer[]{2}, result.toArray());
	}

	@Test
	public void testThree() {
		ArrayList<Integer> result = EvenNumbers.getEvenNumbers(3);
		Assert.assertArrayEquals(new Integer[]{2}, result.toArray());
	}
	
	@Test
	public void testFour() {
		ArrayList<Integer> result = EvenNumbers.getEvenNumbers(4);
		Assert.assertArrayEquals(new Integer[]{2,4}, result.toArray());
	}
	
}
