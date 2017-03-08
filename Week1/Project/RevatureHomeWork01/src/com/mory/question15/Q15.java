package com.mory.question15;

public class Q15 implements OperationInterface{
	

		@Override
		public int addition(int a, int b) {
			return a+b;
		}

		@Override
		public int subtraction(int a, int b) {
			return a-b;
		}

		@Override
		public int multiplication(int a, int b) {
			return a*b;
		}

		@Override
		public int division(int a, int b) {
			if(b==0) throw new IllegalArgumentException("Cannot diviide by zeo");
			return a/b;
		}
		
	

}
