package com.revature.q18;
/**
 * 
 * @author Aaron Camm
 *
 */
public class MainApp {
	
	public static void main(String[] args){
		AbstractClass a = new ConcreteClass();
		
		String lower = "this was all lowercase";
		String upper = a.convertToUppercase(lower);
		String ten = "45";
		System.out.println(upper);
		
		System.out.println("The string \""+ lower + "\" has an uppercase is " + a.hasUppercaseCharacters(lower));
		System.out.println("The string \""+ upper + "\" has an uppercase is " + a.hasUppercaseCharacters(upper));
		
		a.Add10ToInputStringAndPrint(ten);
	}
	
	

}
