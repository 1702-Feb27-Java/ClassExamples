package com.revature.serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {

	public static void main(String[] args) throws Exception{
		Employee e1 = new Employee();
		Employee e2 = new Employee("Bobby", 92, "321-54-0987");
		
		FileOutputStream fos = new FileOutputStream("Employee.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(e1);
		
		
		System.out.println("Employee serialized!");
		
		oos.close();
		fos.close();
		
	}

}
