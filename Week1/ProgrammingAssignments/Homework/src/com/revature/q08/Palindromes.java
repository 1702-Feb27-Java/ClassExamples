package com.revature.q08;
import java.util.ArrayList;
/**
 * This program will check if certain strings in an array
 * are palindromes and, if so, puts them in a separate array 
 * @author Nick
 *
 */
public class Palindromes {

	public static void main(String[] args) {
		
		//creating ArrayList to put strings into
		ArrayList <String> my_list = new ArrayList<String>();
		
		my_list.add("karan");
		my_list.add("madam");
		my_list.add("tom");
		my_list.add("civic");
		my_list.add("radar");
		my_list.add("sexes");
		my_list.add("jimmy");
		my_list.add("kayak");
		my_list.add("john");
		my_list.add("refer");
		my_list.add("billy");
		my_list.add("did");
		
		//empty ArrayList
		ArrayList <String> other_list = new ArrayList<String>();
		
		//looping through list and placing palidromes in another arraylist
		for ( int i = 0; i < my_list.size(); i++) {
			StringBuilder str = new StringBuilder(my_list.get(i));
			
			if ( my_list.get(i).equals(str.reverse().toString()) ) {
				other_list.add(my_list.get(i));	
			}
		}
		
		for ( int i = 0; i < other_list.size();i++) 
			System.out.println(other_list.get(i));
	}
	
	
}
