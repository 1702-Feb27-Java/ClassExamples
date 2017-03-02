package com.revature.child;

import com.revature.parent.AccessParent;

public class DifferentPackageChild extends AccessParent {

	@Override
	public void childDoStuff() {
		
		System.out.println("In diffrent package child");
		
		doStuff();

	}
	
	public DifferentPackageChild(){
		super();
		System.out.println("no args constructor child");
	}
	
	public DifferentPackageChild(boolean b){
		this();
		System.out.println("boolean constructor child");
	}
	public DifferentPackageChild(char a){
		super();
		System.out.println("char constructor child");
		
	}
	public DifferentPackageChild(int i){
		this(true);
		System.out.println("int constructor child");
		
	}


}
