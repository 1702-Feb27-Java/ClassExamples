package com.revature.designpats;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SingletonExample se = SingletonExample.getSingleton();
		System.out.println("se hash: " + System.identityHashCode(se));
		
		SingletonExample se2 = SingletonExample.getSingleton();
		System.out.println("se2 hash: " + System.identityHashCode(se2));
		
		ShapeFactory sf = new ShapeFactory();
		Shape c = sf.retrieveShape("ciRcle");
		Shape wtf = sf.retrieveShape("Stuff");
				
	}

}
