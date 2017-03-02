package com.revature.parent;

import com.revature.inter.MyInterface;

public class AccessParent{
	
	int y;
	public static int a;
	private static int b;
	static int c;
	static protected int d;
	
	protected void doStuff(){
		
		System.out.println("Inside do Stuff in parent");
		
	}
	
	protected void childDoStuff(){}

	public AccessParent(){
		
		System.out.println("no args constructor parent");
		
	}
	
}

class SamePackageChild extends AccessParent{
	
	@Override
	public void childDoStuff(){
		
		System.out.println("Inside SamePackage Child");
		
		doStuff();
		
		new AccessParent().doStuff();
		
	}
	
	
	
}
