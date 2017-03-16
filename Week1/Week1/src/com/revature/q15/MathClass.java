package com.revature.q15;

public class MathClass implements MathInterface{
	private int value;
	public MathClass(int leftOperand){
		this.value = leftOperand;
	}
	public int addition(MathClass rightOperand){
		return this.value + rightOperand.getValue();
	}
	public int subtraction(MathClass rightOperand){
		return this.value - rightOperand.getValue();
	}
	public int multiplication(MathClass rightOperand){
		return this.value * rightOperand.getValue();
	}
	public int division(MathClass rightOperand){
		return this.value / rightOperand.getValue();
	}
	public int getValue(){
		return this.value;
	}

}
