package com.revature.wrappers;

public class WrapperExample {

	public static void main(String[] args) {
		
		int i = 5;
		
		Integer wi = new Integer(10);
		
		wi = i;
		System.out.println(wi);
		
		autoboxPlease(wi);
		autoboxPlease(new Integer(8));
		autoboxPlease(8);
		
		System.out.println(wi.MAX_VALUE + 1);
		
		int p = -2_147_483_648; //java allows _ to indicate every thousand in an int
		
		float f = 12_31.2312f;  // as well as float
		
		long m = 2147483648L;
		
	}
	
	public static void autoboxPlease(Integer i){
		System.out.println("Autobo");
	}

}
