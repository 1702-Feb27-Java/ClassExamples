package com.revature.q18;

public class ImplementationClass extends AbstractClass {

	public ImplementationClass() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkUpper(String s) {
		// TODO Auto-generated method stub
		
		if(s.toLowerCase().equals(s))
			return false;
		return true;
	}

	@Override
	public String changeUpper(String s) {
		// TODO Auto-generated method stub
		char charArr[] = s.toCharArray();
		for(int c : charArr){
			if(Character.isLowerCase(c)){
				c = Character.toUpperCase(c);
			}
		}
		String returnStr = charArr.toString();
		return returnStr;
	}

	@Override
	public int parseInt(String s) {
		// TODO Auto-generated method stub
		int i = Integer.parseInt(s);
		return i;
	}

}
