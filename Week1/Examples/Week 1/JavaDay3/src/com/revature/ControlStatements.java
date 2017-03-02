package com.revature;

public class ControlStatements {

	public static void main(String[] args) {
		// Switch statement example

		/*
		 * int i = (int)Math.ceil((Math.random() * 5));
		 * 
		 * 
		 * switch(i){ case 1: System.out.println("i is equal to: 1"); break;
		 * default: System.out.println("aint 1-3"); break; case 2:
		 * System.out.println("i is equal to: 2"); break; case 3:
		 * System.out.println("i is equal to: 3"); break; }
		 */
		// for (int l = 0; l < 5; l++) {
		// int i;
		for (int j = 0; j < 10; j++) {
			if (j == 4)
				continue;
			System.out.println(j);
		}
		// }

	}

}
