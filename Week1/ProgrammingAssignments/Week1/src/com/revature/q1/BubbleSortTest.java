package com.revature.q1;

import org.junit.Assert;
import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class BubbleSortTest {

	@Test
	public void testSample() {
		int[] array = new int[]{1,0,5,6,3,2,3,7,9,8,4};
		int[] arrayAnswer = new int[]{0,1,2,3,3,4,5,6,7,8,9};
		BubbleSort.sort(array);
		
		Assert.assertArrayEquals(array, arrayAnswer);
	}
	
	@Test
	public void testZeros(){
		int[] array = new int[]{0,0,0};
		int[] arrayAnswer = new int[]{0,0,0};
		BubbleSort.sort(array);
		
		Assert.assertArrayEquals(array, arrayAnswer);
	}
	
	@Test
	public void testEmpty(){
		int[] array = new int[]{};
		int[] arrayAnswer = new int[]{};
		BubbleSort.sort(array);
		
		Assert.assertArrayEquals(array, arrayAnswer);
	}
	
	@Test
	public void testAlreadySorted(){
		int[] array = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		int[] arrayAnswer = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		
		BubbleSort.sort(array);
		
		Assert.assertArrayEquals(array, arrayAnswer);
	}
	
	@Test
	public void testWorstCase(){
		int[] array = new int[]{12,11,10,9,8,7,6,5,4,3,2,1};
		int[] arrayAnswer = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		
		BubbleSort.sort(array);
		
		Assert.assertArrayEquals(array, arrayAnswer);
	}

}
