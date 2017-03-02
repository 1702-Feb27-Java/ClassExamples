package com.revature.serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {

	public static void main(String[] args) throws Exception{
		
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Employee.ser"));
		
		Employee importEmp = (Employee)ois.readObject();
		
		System.out.println(importEmp);
		
		ois.close();
		

	}

}
