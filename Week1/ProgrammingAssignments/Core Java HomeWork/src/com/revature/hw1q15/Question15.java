package com.revature.hw1q15;

public class Question15 implements Question15Interface{
	
	public static void main(String[] args) {
		int n1 = 30;
		int n2 = 15;
		
		Question15 q15 = new Question15();
		
		System.out.println(q15.addition(n1, n2));
		System.out.println(q15.subtraction(n1, n2));
		System.out.println(q15.multiply(n1, n2));
		System.out.println(q15.division(n1, n2));
	}

	@Override
	public int addition(int n1, int n2) {
		// TODO Auto-generated method stub
		return n1+n2;
	}

	@Override
	public int subtraction(int n1, int n2) {
		// TODO Auto-generated method stub
		return n1-n2;
	}

	@Override
	public int multiply(int n1, int n2) {
		// TODO Auto-generated method stub
		return n1*n2;
	}

	@Override
	public int division(int n1, int n2) {
		// TODO Auto-generated method stub
		return n1/n2;
	}
	
	

}
