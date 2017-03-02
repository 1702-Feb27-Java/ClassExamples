package com.homework1.question18;

public class Main {

	public static void main(String[] args) {
		//create a subclass object to run the abstract methods
		SubClass subClass = new SubClass();
		
		System.out.println(subClass.upperCaseChars("test"));
		System.out.println(subClass.lowerCaseConvert("mean"));
		subClass.integerConvert("101");
	}

}
