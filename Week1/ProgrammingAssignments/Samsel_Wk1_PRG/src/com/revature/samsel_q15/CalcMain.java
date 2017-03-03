package com.revature.samsel_q15;

/*
 * Question: Program to demonstrate interfaces having methods ADDITION, SUBTRACTION, MULTIPLICATION
 * & DIVISION. Create a class that implements this interface and provides appropriate functionality.
 * HArdCode two operands in a test class having main method that calls the implementing class
 */
public class CalcMain {

	public static void main(String[] args) {
		
		OperatCalc opAdd = new OperatCalc();
		OperatCalc opSub = new OperatCalc();
		OperatCalc opMul = new OperatCalc();
		OperatCalc opDiv = new OperatCalc();
		
		//Display : Addition, Subtraction, Multiplication & Division of 2 hard-coded values
		double iOperA = 34;
		double iOperB = 10;
		System.out.println("=======Operations on ["+iOperA +" , "+ iOperB+"]============");
		System.out.println("Addition       : "+opAdd.Add(iOperA, iOperB));
		System.out.println("Subtraction    : "+opSub.Sub(iOperA, iOperB));
		System.out.println("Multiplication : "+opMul.Mul(iOperA, iOperB));
		System.out.println("Division       : "+opDiv.Div(iOperA, iOperB));
	}

}
