package com.revature.serialization;

import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Deserializer {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Employee.ser"));
		
		Employee importEmp = (Employee)ois.readObject();
		
//		// casting example
//		Object o = new Object();
//		importEmp = (Employee)o;  // must cast o as the Employee object
		
		System.out.println(importEmp);
		
		ois.close();
		
	}

}
