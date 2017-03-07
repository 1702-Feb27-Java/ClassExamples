package com.revature.examples;

import java.util.StringTokenizer;

public class SBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = new String("Dog");
		StringBuilder sb = new StringBuilder(s);  // insert string you want to use into the string builder
		
		System.out.println(sb + " " + new Object().toString() + "\n");
		System.out.println("sb is: " + sb);
		System.out.println("sb code: " + System.identityHashCode(sb));
		System.out.println("string is: " + s);
		System.out.println("string code: " + System.identityHashCode(s));
		
		sb.append("New Letters");
		s+="s";
		
		System.out.println("sb is: " + sb);
		System.out.println("sb code: " + System.identityHashCode(sb));  
		// this has the same hashcode as the sb above
		// this is because StringBuilder is mutable, can change the original string without creating a new one
		// not using the String Pool
		System.out.println("string is: " + s);
		System.out.println("string code: " + System.identityHashCode(s));
		
		
//		// Digression
//		String s2 = null;
//		//s2.concat("asd");  // NULL pointer exception - do not call a method on a null object
//		s2 += "s";  // this prints out "nulls"
//		System.out.println(s2);
		
		
		// Performance test
		String s2 = "";
		StringBuilder sb2 = new StringBuilder();
		StringBuffer sbuff = new StringBuffer();
		
		int loop = 100000;
		
		long curTime = System.currentTimeMillis();
		
		for (int i = 0; i < loop; i++){
			sb2.append("a");
		}
		System.out.println("String Builder: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		
		for (int i = 0; i < loop; i++){
			sbuff.append("a");
		}
		System.out.println("String Buffer: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		
		for (int i = 0; i < loop; i++){
			s2+="a";
		}
		System.out.println("String: " + (System.currentTimeMillis() - curTime));
		
		
		// Tokenizer
		String s3 = "a b, c d, e f,g";
		String[] strArr = s3.split(",");
		
//		for (String ss : strArr){
//			System.out.println(ss);
//		}
		
		StringTokenizer st = new StringTokenizer(s3);  // put s3 in the string tokenizer
		
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken(","));
		}
		
	}

}
