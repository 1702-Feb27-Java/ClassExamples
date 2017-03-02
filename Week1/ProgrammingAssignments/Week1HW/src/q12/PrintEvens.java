package q12;

import java.util.ArrayList;

public class PrintEvens {

	public static void main(String[] args) {
		ArrayList<Integer> even = new ArrayList<Integer>();
		
		//adds numbers to the array 
		for(int i = 1; i < 101; i++){
			even.add(i);
		}
		
		//for each element check if divisible by two 
		for(int x : even){
			if(x % 2 == 0){
				System.out.println(x);
			}
		}

	}

}
