package com.revature.q15;

/**
 * 
 * @author Aaron Camm
 *
 */
public class IntegerOperator implements OperatorInterface<Integer>{

	public int value;

	public IntegerOperator(){
		this(0);
		
	}
	
	public IntegerOperator(int n){
		this.value = n;
	}

	@Override
	public OperatorInterface<Integer> addition(Integer n) {
		return new IntegerOperator(value + n);
	}

	@Override
	public OperatorInterface<Integer> subtraction(Integer n) {
		return new IntegerOperator(value - n);
	}

	@Override
	public OperatorInterface<Integer> multiplication(Integer n) {
		return new IntegerOperator(value * n);
	}

	@Override
	public OperatorInterface<Integer> division(Integer n) {
		return new IntegerOperator(value / n);
	}

	@Override
	public String toString() {
		return "IntegerOperator [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		// auto-generated equals test, except that if obj has a Number superclass, 
		// check the intvalue of the obj
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Number){
			return ((Integer) obj).intValue() == this.value;
		}
		if (getClass() != obj.getClass())
			return false;
		IntegerOperator other = (IntegerOperator) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	
	
}
