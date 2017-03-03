package com.mory.question18;

public class Q18 extends AbstractClass {

	@Override
	boolean upperCaseExist(String str) {
		char[] charArr= str.toCharArray();
		for(char character: charArr){
			if (Character.isUpperCase(character)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	String toUpperCase(String str) {
		return str.toUpperCase();
	}

	@Override
	void toInteger(String str) {
		System.out.println(Integer.parseInt(str)+10);	
	}

}