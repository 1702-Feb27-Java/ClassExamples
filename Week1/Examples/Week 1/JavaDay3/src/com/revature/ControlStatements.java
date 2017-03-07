package com.revature;

public class ControlStatements {

	public static void main(String[] args) {
		
		// Switch statement example
		
		int i = (int)Math.ceil((Math.random() * 3));  // cast this as int
		
		switch(i){
		case 1:
			System.out.println("i is equal to: 1");
			break;
		case 2:
			System.out.println("i is equal to: 2");
			break;
		case 3:
			System.out.println("i is equal to: 3");
			break;
			
		default:  // the default case can be placed anywhere
			System.out.println("aint 1-3");
			break;
		}
		
		//-------------------------------------
		
		for (int j=0; j<10; j++){
			System.out.println(j);
			if (j==4) break;
		}
	}

}
