package com.revature.samsel_q7;

import java.util.ArrayList;
import java.util.Collections;
/*
 * Quesetion : Program to sort by name , age and department
 */
public class MainComp {

	public static void main(String[] args) {
		
		ArrayList <SortCompInt> iA = new ArrayList();
		iA.add(new SortCompInt("Samsel","Development",28));
		iA.add(new SortCompInt("Rainer","Service Manager",30));
		iA.add(new SortCompInt("Nymphia","Finance",36));
		
		System.out.println("============Sort By Name===============");
		Collections.sort(iA, new NameComp());
		for(SortCompInt istr : iA){
			System.out.println(istr.name+" "+istr.department+" "+istr.age);
		}
		
		System.out.println("============Sort By Department===============");
		Collections.sort(iA, new DeptComp());
		for(SortCompInt istr : iA){
			System.out.println(istr.name+" "+istr.department+" "+istr.age);
		}
		
		System.out.println("============Sort By Age===============");
		Collections.sort(iA, new AgeComp());
		for(SortCompInt istr : iA){
			System.out.println(istr.name+" "+istr.department+" "+istr.age);
		}
	}
}