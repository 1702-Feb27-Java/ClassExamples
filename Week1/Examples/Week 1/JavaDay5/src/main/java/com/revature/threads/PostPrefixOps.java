package com.revature.threads;

public class PostPrefixOps {

	public static void main(String[] args) {
		int i = 0;
		System.out.println(i);		//0
		System.out.println(i++);	//0
		System.out.println(--i);	//-1 or 0
		
		for(int j = 0; j < 10; j++){
			System.out.println(j);
		}
		
		for(int j = 0; 
				j < 10; 
				++j){
			System.out.println(j);
		}
		
	}

}
