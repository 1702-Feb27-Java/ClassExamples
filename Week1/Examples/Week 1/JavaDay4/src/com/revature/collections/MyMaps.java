package com.revature.collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import com.revature.serialization.Employee;

public class MyMaps {

	public static void main(String[] args) {
		Map<Integer,String> m = new HashMap<>();
		Map<Integer,String> ht = new Hashtable<>();
		
		m.put(1, "Bob");
		m.put(2, "Bobby");
		m.put(3, "Bobbert");
		m.put(3, "Rob");
		m.put(4, null);
		//m.put(null, null);
		
		//HashMap<Employee,Integer> m2 = new HashMap();
		
		/**
		 * HashTables can NOT have null values or keys.
		 * You can NOT check for null keys in a hashTable
		 * HashTables are synchronized
		 * HashTable is considered a legacy class
		 * 
		 * HashMaps are NOT synchronized
		 * HashMaps can have ONE null key and countless null values
		 * You can check for nulls in a hashmap
		 */
		ht.put(4, "Bob");      //1
		ht.put(2, "Bobby");
		ht.put(1, "Bobbert");
		ht.put(2, "Rob");
		ht.put(4, "Rob");        //2
		ht.put(5, "Bobbert");    //3
		
		
		System.out.println(m);
		
		for(Integer i : m.keySet()){
			System.out.println(i + "\t" + m.get(i));
		}
		
		for(Entry<Integer, String> e : m.entrySet() ){
			System.out.println(e.getKey() + "\t" + e.getValue());
		}
		
	}

}
