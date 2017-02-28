package com.revature.driver;

import com.revature.child.DifferentPackageChild;
import com.revature.parent.*;

public class Driver {
	
	public static void main(String ... pirateSounds) {
		
		System.out.println("Hi");
		
		AccessParent diffPackage;
		
		diffPackage = new com.revature.child.DifferentPackageChild();
		
		((DifferentPackageChild)diffPackage).childDoStuff();
		
		
		System.out.println("calling childs char constructor");
		
		new DifferentPackageChild('a');
		
		System.out.println("calling childs int constructor");
		
		new DifferentPackageChild(5);
		
		System.out.println("calling childs bool constructor");
		
		new DifferentPackageChild(true);
		
	}

}

class newClass{
	
	static{
		
		Driver.main(null);
		
		"".charAt(0);
		
	}
	
}
