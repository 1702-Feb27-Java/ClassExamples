package com.revature.hw1q8;

import java.util.*;

public class Question8 {
	
	public static void main(String[] args) {
		
		Question8 q8 = new Question8();
		q8.storePalindromes();
		
	}
		

	private List<String> al1;
	private String[] strArr = {"karen", "madam", "tom", "civic", "radar", "sexes", "jimmy", "kayak", "john", "refer", "billy", "did"};
	private List<String> al2;
	
	
	public Question8(){
		al1 = new ArrayList<String>();
		al2 = new ArrayList<String>();
		al1 = Arrays.asList(strArr);
	}
	
	public void storePalindromes(){
		for(int i = 0; i < al1.size(); i++){
			String temp = al1.get(i);
			
			//default true
			boolean palindrome = true;
			
			// if half the the array is equal to the other half then its a palindrome
			for(int j = 0; j < temp.length()/2; j++){
				
				//sets false if there is a not equal
				if(!Character.toString(temp.charAt(j)).equals(Character.toString(temp.charAt(temp.length()-j-1)))){
					palindrome = false;
				}
			}
			if(palindrome){
				al2.add(al1.get(i));
			}
		}
		
		for(int i = 0; i < al2.size(); i++){
			System.out.print(al2.get(i) + " ");
		}
		
		System.out.println();
		
	}
	
	
	
	

}
