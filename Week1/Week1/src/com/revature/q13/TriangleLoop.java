package com.revature.q13;
import java.util.*;
public class TriangleLoop {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// zeroFlag to decide whether or not to show a zero
		boolean zeroFlag = true;
		// each iteration displays a line
		for(int i = 0; i < 4; i++){
			// each iteration displays 0 or 1
			for(int j = 0; j < i + 1; j++){
				// display 0 if zeroFlag is true
				System.out.print((zeroFlag ? "0 " : "1 "));
				zeroFlag = !zeroFlag;
			}
			System.out.println();
		}
	}

}
