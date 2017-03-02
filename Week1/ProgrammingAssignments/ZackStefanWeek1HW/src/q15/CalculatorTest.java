package q15;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	private Calculator calc;
	@Before
	public void setUp() throws Exception {
		calc = new Calculator();
	}

	@Test
	public void test() {
		assertEquals(5 ,calc.addition(2, 3));
		assertEquals(8, calc.multiplication(4, 2));
	}

}
