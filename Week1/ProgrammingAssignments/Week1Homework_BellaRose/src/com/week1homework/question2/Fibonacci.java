package com.week1homework.question2;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//We want to determine how many numbers we want to find
		int count = 25;
		
		//Create new array that will hold our sequence
        int[] fib = new int[count];
        
        //First 2 numbers in the sequence have always been and always will be 0 then 1
        fib[0] = 0;
        fib[1] = 1;
        
        //Perform the addition to find next value in sequence eg. fib[0] + fib[1] = 0 + 1 = 1 which is now fib[2]
        for(int i=2; i < count; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }

        //Show everyone all the hard work you made this poor computer do.
        for(int i=0; i< count; i++){
                System.out.print(fib[i] + " ");
        }
	}

}
