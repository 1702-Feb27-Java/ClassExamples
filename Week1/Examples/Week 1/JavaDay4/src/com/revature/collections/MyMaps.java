package com.revature.collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class MyMaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<Integer, String> m = new HashMap<>();  // not synchronized
		Map<Integer, String> ht = new Hashtable<>();  // synchronized
		
		m.put(1, "Bob");
		m.put(2,  "Bobby");
		m.put(3, "Bobbert");
		m.put(3, "Rob");
		m.put(4,  null);
		m.put(null, null);
		
		ht.put(1, "Bob");
		ht.put(2,  "Bobby");
		ht.put(3, "Bobbert");
		ht.put(3, "Rob");
		ht.put(4,  "Rob");  // cannot put null keys and values into a hashtable
		ht.put(5, "Bobbert");
		
		System.out.println(m.get(null));
		// System.out.println(ht.get(null)); // cannot check for nulls
		
		// hashmaps can have ONE null key and countless null values
		
		for (Integer i : m.keySet()){
			System.out.println(m.get(i));
		}
		
		for (Entry<Integer, String> e : m.entrySet()){
			System.out.println(e.getKey() + "\t" + e.getValue());
		}
		
	}

}
