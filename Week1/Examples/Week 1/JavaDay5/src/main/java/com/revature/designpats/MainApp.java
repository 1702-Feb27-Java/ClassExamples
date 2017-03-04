package com.revature.designpats;

public class MainApp {
	public static void main(String[] args){
		System.out.println("Begin program:");
		
		SingletonExample se = SingletonExample.getSingleton();
		System.out.println("se hash: " + System.identityHashCode(se));
		SingletonExample se2 = SingletonExample.getSingleton();
		System.out.println("se2 hash: " + System.identityHashCode(se2));

		
		ShapeFactory sf = new ShapeFactory();
		Shape c = sf.retrieveShape("cIrCle");
		Shape s = sf.retrieveShape("sQUARe");
		Shape wtf = sf.retrieveShape("Octa-angle");
		
		c.draw();
		s.draw();
		try{
			wtf.draw();
		}catch(NullPointerException e){
			e.printStackTrace();
			
		}
	}
}
