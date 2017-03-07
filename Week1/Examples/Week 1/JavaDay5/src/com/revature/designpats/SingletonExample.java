package com.revature.designpats;

public class SingletonExample {
	
	private static SingletonExample se;
	private SingletonExample(){  // private constructor
		System.out.println("Instance created.");
	}
	
	public static SingletonExample getSingleton(){
		if (se == null){  // if the instance isn't created
			se = new SingletonExample();  // we create it
		}
		return se;  // returns the singleton regardless
	}

}
