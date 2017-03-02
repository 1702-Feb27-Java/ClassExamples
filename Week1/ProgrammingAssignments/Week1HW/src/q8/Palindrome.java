package q8;

import java.util.ArrayList;

public class Palindrome {

	public static void main(String[] args) {
		//makes the arrays 
		ArrayList<String> firstList = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		
		//sets up the first array
		firstList.add("karan");
		firstList.add("madam");
		firstList.add("tom");
		firstList.add("civic");
		firstList.add("radar");
		firstList.add("sexes");
		firstList.add("jimmy");
		firstList.add("kayak");
		firstList.add("john");
		firstList.add("refer");
		firstList.add("billy");
		firstList.add("did");
		
		
		findPalindromes(firstList, result);
		
		//prints out the palindromes
		for(String temp : result){
			System.out.println(temp);
		}
		

	}
	
	
	/*
	 * finds the palindromes in the original list and adds them to the resulting list
	 */
	public static void findPalindromes(ArrayList<String> src, ArrayList<String> res){
		for(String temp : src){	// for each element in the source list check 
			if(temp.equals(new StringBuffer(temp).reverse().toString())){ // if it is the same as the reversed then add it
				res.add(temp);
			}
		}
	}

}
