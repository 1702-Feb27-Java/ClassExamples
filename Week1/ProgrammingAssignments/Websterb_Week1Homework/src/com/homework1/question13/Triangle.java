package com.homework1.question13;

public class Triangle {

	public static void main(String[] args) {
		//alternating 0s and 1s have the counter start at 1
		int i = 1;
		//for 4 rows, start at row 1 to make it easy
		for(int x = 1; x < 5; x++){
			
			//value is the number of integers in the row
			int value = 0;
			//while the number of integers is less than the row number
			while(value < x){
				//if the number in the order is odd it is a 0 and if it is even it is a 1
				if(i%2 != 0){
					System.out.print(0);
				}
				else{
					System.out.print(1);
				}
				i++;
				value++;
			}
			//print the spaces between lines
			System.out.println();
			System.out.println();
		}
	}

}
