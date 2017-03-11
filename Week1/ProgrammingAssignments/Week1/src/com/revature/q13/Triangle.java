package com.revature.q13;
/**
 * 
 * @author Aaron Camm
 *
 */
public class Triangle {
	
	public static void main(String[] args){
		Triangle triangle = new Triangle();
		triangle.printTriangles(4);
		
	}
	/**
	 * displays a triangle figure on the console
	 * @param nOfLines - number of lines the triangle uses.
	 */
	public void printTriangles(int nOfLines){
		// boolean to show either zero or one when making display triangle
		boolean showZero = true;
		
		for (int i = 0; i < nOfLines; ++i ){
			for(int j = 0; j <= i; j++){
				// creates alternating zeros and ones that will be print
				System.out.print(showZero ? "0" : "1");
				showZero = !showZero;
			}
			
			// finished with current line, start on next line
			System.out.println();
		}
		
	}
}
