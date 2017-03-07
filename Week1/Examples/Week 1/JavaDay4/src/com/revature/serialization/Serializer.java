package com.revature.serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Employee e1 = new Employee();
		Employee e2 = new Employee("Bobby", 92, "321-54-9876");
		
		FileOutputStream fos = new FileOutputStream("Employee.ser");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(e1);
		
		System.out.println("Employee serialized.");
		
		oos.close();
		fos.close();
	}

}
