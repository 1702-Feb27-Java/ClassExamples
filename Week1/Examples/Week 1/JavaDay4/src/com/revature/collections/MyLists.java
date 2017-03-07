package com.revature.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.revature.serialization.Employee;

public class MyLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Employee e1 = new Employee();
		
		ArrayList<Integer> al = new ArrayList<>();
		List<Employee> el = new ArrayList<>();
		//List<Employee> el2= Arrays.aslist(new Employee("bob", 45, "6758"), new Employee("andy", 89, "3870"), new Employee("phil", 100, "2389"));
		
		el.add(new Employee("bob", 80, "0"));
		el.add(new Employee("andy", 60, "456"));
		el.add(new Employee("Bob", 10, "123-45-6789"));
		

		al.add(5);
		al.add(10);
		al.add(20);
		al.add(15);
		//al.add("fifteen");
		//al.add(e1);
		
//		for (Object o: al){
//			System.out.println(o);
//		}
		
		System.out.println(al);
		
		Collections.sort(al);
		
		System.out.println(al);
		
		System.out.println(al.size());
		
		
//		System.out.println(el);
//		Collections.sort(el);
//		System.out.println(el);
		
		//System.out.println(el);
		
		MyComparator mc = new MyComparator();
		
		Collections.sort(el, mc);
		System.out.println(el);
		
		System.out.println("Program end");
		
		
		// LISTS
		
		System.out.println("===========================");
		Iterator it = el.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		// no way to reset the iterator, have to instantiate again
		it = el.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		it = el.listIterator();
		
		ListIterator li = el.listIterator();
		
		System.out.println("===========================");
		
		System.out.println(li.next());
		System.out.println(li.previous());
		System.out.println(li.previousIndex());
		
	}

}
