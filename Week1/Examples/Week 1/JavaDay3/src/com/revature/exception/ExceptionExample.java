package com.revature.exception;

import java.io.FileNotFoundException;

public class ExceptionExample {

	public static void main(String[] args) throws Exception {  
		// TODO Auto-generated method stub
		
		// Trigger an exception
		try{
			throw new IndexOutOfBoundsException();
		} catch(NullPointerException e){  // Block only entered upon catching exception
			System.out.println("Exception caught.");
		} catch(IndexOutOfBoundsException e){
			System.out.println("Caught the: " + e.getClass());
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println("Success!");
		
		method1();
	}
	
	public static void method1() throws Exception {
		method2();
	}
	
	public static void method2() throws Exception{
		try{
			method3();
		} catch(RuntimeException e){
			e.printStackTrace();
		}
	}
	
	public static void method3() throws Exception { // ducking the exception
		throw new FileNotFoundException();			// instead of writing a try catch block for FileNotFound, we throw it up
	}

}
