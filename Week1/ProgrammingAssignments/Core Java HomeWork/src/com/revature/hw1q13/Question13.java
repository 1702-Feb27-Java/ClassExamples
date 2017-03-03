package com.revature.hw1q13;

public class Question13 {
	
	public static void main(String[] args) {
		Question13.makeTriangleBits();
	}
	
	public static void makeTriangleBits(){
		// makes it print zero or one
		boolean zeroOrOne = true;
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j <= i; j++){
				if(zeroOrOne){
					System.out.print("0 ");
					zeroOrOne = false;
				}
				else{
					System.out.print("1 ");
					zeroOrOne = true;
				}
					
			}
			System.out.println();
			
		}
	}

}
