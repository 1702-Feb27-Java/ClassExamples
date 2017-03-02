package com.revature.wrappers;

public class WrapperExample {

	public static void main(String[] args) {
		int i = 5;
		
		Integer wi = new Integer(10);
		
		
		wi = i;
		System.out.println(wi);
		
		//autoboxing example
		autoboxPlease(wi);
		autoboxPlease(new Integer(8));
		autoboxPlease(10);
	
		
		
		
		
		
		int j = wi.MAX_VALUE + 1;
		System.out.println(j);
		int p = -2_147____4____83_648;
		float f = 31_23_12.12312f;
		long m = 2147483648L;
		
	}
	
	public static void autoboxPlease(Integer i){
		System.out.println("Autobox success: " + i);
	}

}
