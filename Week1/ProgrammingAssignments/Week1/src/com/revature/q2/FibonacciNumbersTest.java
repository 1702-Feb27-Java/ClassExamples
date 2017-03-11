package com.revature.q2;

import org.junit.Assert;
import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class FibonacciNumbersTest {

	@Test
	public void testSample() {
		int[] seqAnswer = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368};
		int[] seq = FibonacciNumbers.fib(25);
		Assert.assertArrayEquals(seqAnswer, seq);
		Assert.assertEquals(seq.length, 25);
	}
	
	@Test
	public void testSingle(){
		int[] seqAnswer = {0};
		int[] seq = FibonacciNumbers.fib(1);
		
		Assert.assertArrayEquals(seq, seqAnswer);
		Assert.assertEquals(seq.length, 1);
	}
	
	@Test
	public void testTwo(){
		int[] seqAnswer = {0,1};
		int[] seq = FibonacciNumbers.fib(2);

		Assert.assertArrayEquals(seq, seqAnswer);
		Assert.assertEquals(seq.length, 2);
	}

}
