package q15;

public class Calculator implements Calc {
	
	public Calculator(){
		super();
	}
	
	/*
	 * adds two numbers toegther
	 * @param x, y the two numbers to add
	 * @return the sum of x and y
	 */
	@Override
	public int addition(int x, int y) {
		return x + y;
	}

	/*
	 * substracts two numbers
	 * @param x,y the two numbers to subtract
	 * @return the difference of x and y
	 */
	@Override
	public int substraction(int x, int y) {
		// TODO Auto-generated method stub
		return x - y;
	}

	/*
	 * Multiply two numbers
	 * @param x, y the two numbers to muliply
	 * return the product of x and y
	 */
	@Override
	public int multiplication(int x, int y) {
		// TODO Auto-generated method stub
		return x * y;
	}

	/* 
	 * divide two numbers
	 * @param x, y the 2 numbers to divide
	 * @return the quotent of x and y
	 * @throws IllegalArgumentException if y is 0
	 */
	@Override
	public int division(int x, int y) {
		if( y == 0){
			throw new IllegalArgumentException("can't divide by 0");
		}
		
		return x / y;
	}

}
