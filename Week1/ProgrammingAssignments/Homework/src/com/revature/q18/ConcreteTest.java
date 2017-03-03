package com.revature.q18;
/**
 * This program will override three abstract methods from an abstract class 
 * Check Concrete.java in this package for blueprint
 * @author Nick
 *
 */
public class ConcreteTest extends Concrete {

	public static void main(String[] args) {
		ConcreteTest n = new ConcreteTest();
		System.out.println(n.CheckUpperCase("hello"));
		System.out.println(n.ConvertToUpper("hello"));
		System.out.println(n.Convert("10"));
		
		System.out.println();

	}

	@Override
	public boolean CheckUpperCase(String a) {
		char ch;
		
		for ( int i = 0; i < a.length(); i++ ){
			ch = a.charAt(i);
			if ( Character.isUpperCase(ch) )
				return true;
		}
		
		return false;
	}

	@Override
	public String ConvertToUpper(String b) {
		return b.toUpperCase();
	}

	@Override
	public int Convert(String c) {
		int result = Integer.parseInt(c);
		result += 10;
		return result;
	}

	
}
