package com.revature.q15;

public class MainApp {

	public static void main(String[] args){
		MathClass a = new MathClass(10);
		MathClass b = new MathClass(2);
		System.out.println(a.addition(b));
		System.out.println(a.subtraction(b));
		System.out.println(a.multiplication(b));
		System.out.println(a.division(b));
	}

}
