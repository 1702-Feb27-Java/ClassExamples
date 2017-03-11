package com.revature.designpats;

public class SingletonExample {
	//Ensure no other class can grab this object directly
	private static SingletonExample se;
	
	
	private SingletonExample(){
		System.out.println("Instance created!");
	}
	
	public static SingletonExample getSingleton(){
		if(se==null){
			se = new SingletonExample();
		}
		return se;
	}
	

}
