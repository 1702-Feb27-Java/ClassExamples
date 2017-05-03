package com.revature.hw1q18;

public class Question18 extends Question18Abstract {
	
	public static void main(String[] args) {
		Question18 q18 = new Question18();
		System.out.println(q18.checkUpper("HasdE"));
		System.out.println(q18.toUpper("asdsdSSaaSS"));
		System.out.println(q18.stringToIntPlus10("1212"));
	}

	@Override
	public boolean checkUpper(String s) {
		boolean b = false;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				b = true;
				break;
			}
		}
		return b;
	}

	@Override
	public String toUpper(String s) {
		s.toUpperCase();
		return s;
	}

	@Override
	public int stringToIntPlus10(String s) {
		try{
		Integer i = Integer.valueOf(s);
		i += 10;
		return i;
		}catch(Exception e){
			System.out.println("NOT AN INTEGER STRING");
		}
		return 0;

	}
}
