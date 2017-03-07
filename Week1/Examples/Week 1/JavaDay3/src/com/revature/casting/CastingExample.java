package com.revature.casting;

public class CastingExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i = 5;			// 32 bit
		long l = 15L;		// 64
		short s = 6;		// 16
		double d = 8;		// 64
		float f = 4f;		// 32
		byte b = 14;		// 8
		char c = '4';		// 16
		boolean bb = true;	// "1 bit" but actually 8
		
		l = Long.MAX_VALUE;
		i = (int)l;
		System.out.println("i is now: " + i);
		
		l = i; // doesn't need casting
		
		i = s;
		s = (short)i;
		
		d = f;
		f = (float)d;
		
		l = (long)d;
		d = l;
		
		i = c;
		f = c;
		
		c = (char)b; 
		b = (byte)c;  // char is an unsigned
		System.out.println(Byte.MIN_VALUE);
		System.out.println((int)Character.MIN_VALUE + " " + (int)Character.MAX_VALUE);
		
		// all primitive values have their associated wrapper class
		
	}

}
