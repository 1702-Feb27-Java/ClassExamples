package com.revature.banking.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TestObjectStream {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		////////////////////////////////////////////////////////////////////////
		// SERIALIZE
		
		///*
		HashMap<String, ArrayList<Object>> outmap = new HashMap<>();

		ArrayList<Object> a = new ArrayList<>();
		a.add("Aoeu");
		a.add(1234);
		
		ArrayList<Object> b = new ArrayList<>();
		b.add("Htns");
		b.add(78901234);
		
		outmap.put("Alpha", a);
		outmap.put("Beta", b);
		
		System.out.println(outmap);
		System.out.println(outmap.get("Alpha"));
		System.out.println(outmap.get("Alpha").get(1));
		
		//ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("testobjectstream.ser"));
		//o.writeObject(outmap);
		
		//o.close();
		//*/
		
		System.out.println("=================================================");
		
		////////////////////////////////////////////////////////////////////////
		// DESERIALIZE
		
		HashMap<String, ArrayList<Object>> inmap = new HashMap<>();
		
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("testobjectstream.ser"));
		inmap = (HashMap<String, ArrayList<Object>>) i.readObject();
		
		i.close();
		
		System.out.println(inmap);
		System.out.println(inmap.get("Alpha"));
		System.out.println(inmap.get("Alpha").get(1));
		
	}

}
