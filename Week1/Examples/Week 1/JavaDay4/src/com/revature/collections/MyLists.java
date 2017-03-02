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
		
		Employee e1 = new Employee();
		ArrayList<Integer> al = new ArrayList<>();
		List<Employee> el = new ArrayList<>();
		List<Employee> el2 = Arrays.asList(new Employee("Ted", 78, "123"), new Employee("Bob", 66,"345"), new Employee("bob", 100, "888"));
		
		el.add(new Employee("bob", 80, "0"));
		el.add(new Employee("bobby", 60, "456"));
		el.add(new Employee("bobbert", 100, "123"));
		el.add(new Employee("rob", 40, "234"));
		
		
		al.add(10);
		al.add(5);
		al.add(20);
		al.add(15);
		
		//prints out a tuple
		System.out.println(al);
		
		Collections.sort(al);
		
		System.out.println(al);
		
		
		System.out.println(el);
		Collections.sort(el);
		System.out.println(el);
		
		System.out.println(el2);
		MyComparator mc = new MyComparator();
		
		Collections.sort(el2, mc);
		System.out.println(el2);
		
		
		//lists are indexed, random access possible
		//And iterable
		
		System.out.println("=============================\niterator example\n");
		Iterator it = el.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		it = el.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		it = el.listIterator();
		
		ListIterator li = el.listIterator();
		System.out.println("=======================");
		
		System.out.println(li.next());
		System.out.println(li.previous());
		System.out.println(li.previousIndex());
		
		
	}

}
