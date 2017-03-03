package com.revature.q15;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Aaron Camm
 *
 */
public class IntegerOperatorTest {

	int a = 0;
	int b = 50;
	
	
	@Test
	public void testEquals() {
		
		IntegerOperator n = new IntegerOperator(a);
		assertEquals(n, a);
	}
	
	@Test
	public void testAdds(){
		IntegerOperator n = new IntegerOperator(a);
		assertEquals(n.addition(b), a+b);
	}
	
	
	@Test
	public void testSubstracts(){
		IntegerOperator n = new IntegerOperator(a);
		assertEquals(n.subtraction(b), a-b);
	}
	
	@Test
	public void testMutiply(){
		IntegerOperator n = new IntegerOperator(a);
		assertEquals(n.multiplication(b), a*b);		
	}

	@Test
	public void testDivision(){
		IntegerOperator n = new IntegerOperator(a);
		assertEquals(n.division(b), a/b);		
	}
}
