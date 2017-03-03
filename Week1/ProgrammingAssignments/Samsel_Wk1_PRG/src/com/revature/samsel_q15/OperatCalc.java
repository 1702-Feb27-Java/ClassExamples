package com.revature.samsel_q15;

public class OperatCalc implements OpCalc{

	@Override
	public double Add(double a, double b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public double Sub(double a, double b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public double Mul(double a, double b) {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public double Div(double a, double b) {
		// TODO Auto-generated method stub
		if((a == 0)||(b==0))
			return 0;
		else
			return a/b;
	}

}
