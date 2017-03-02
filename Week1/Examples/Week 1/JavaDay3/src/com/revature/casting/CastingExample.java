package com.revature.casting;

public class CastingExample {

	public static void main(String[] args) {
		int i = 5;			//32 bit
		long l = 15L;		//64 bit
		short s = 6;		//16 bit
		double d = 8;		//64 bit  //implicitly casts to 8.0
		float f = 4f;		//32 bit
		byte b = 14;		//8  bit
		char c = '4';		//16 bit
		boolean bb = true;  //"1 bit" but actually 8 bit
		
		l = Long.MAX_VALUE;
		i = (int)l;
		System.out.println("i is now: " + i);
		l = i;
		
		i = s;
		s = (short)i;
		
		d = f;
		f = (float)d;
		
		l = (long)d;
		d = l;
		
		
		
		Character wC = new Character('a');
		Byte wB = new Byte(b);
		Boolean wBB = new Boolean(true);
		Double wD = new Double(2.2);
		i = c;
		f = c;
		
		c = (char)b;
		b = (byte)c;
		System.out.println(Byte.MIN_VALUE);
		System.out.println((int)Character.MIN_VALUE + " " + (int)Character.MAX_VALUE);
		
		
		
		
	}

}
