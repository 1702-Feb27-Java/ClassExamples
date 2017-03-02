package com.revature.exceptions;

import java.io.FileNotFoundException;

public class ExceptionExample {

	public static void main(String[] args) throws Exception{
		// Trigger an exception
		try {
			throw new IndexOutOfBoundsException();
		} catch (NullPointerException e) { // Block is only entered upon
											// catching exception
			System.out.println("Exception Caught!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Caught the: " + e.getClass());
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}
		method1();
		
		System.out.println("Success!");
		
		
		
		

	}

	public static void method1() throws Exception{
		method2();
	}

	public static void method2(){
		try{
			//method3();
		}catch(RuntimeException e){
			e.printStackTrace();
		}
	}

	public static void method3() throws Exception{
		throw new RyansDisasterException("This ain't good...");
	}

}