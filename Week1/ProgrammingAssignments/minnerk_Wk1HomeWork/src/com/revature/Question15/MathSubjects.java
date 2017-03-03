package com.revature.Question15;

public class MathSubjects implements MathSubjectInterface {
	
	// Addition, Subtraction, Multiplication, and Division Methods	
	public int additon(int num1, int num2){
		return num1 + num2;
	}
	
	public int subtraction(int num1, int num2){
		return num1 - num2;
	}
	
	public int multiplication(int num1, int num2){
		return num1 * num2;
	}
	
	public double division(int num1, int num2){
		
		double total = 0;
		
		//Try, Catch block for divide by zero case
		try{
			total = (double)num1 / (double)num2; //Casted to double for division and decimal answer
		}catch(Exception e){
			System.out.println("Probably tried to divide by zero");
		}
		return total;
	}

}
