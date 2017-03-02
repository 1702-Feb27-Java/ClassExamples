package com.revature.examples;

import java.util.StringTokenizer;

public class SBuilder {

	public static void main(String[] args) {
		String s = new String("Dog");
		StringBuilder sb = new StringBuilder("Test");
		
		System.out.println(sb + " " + new Object().toString() + "\n");
		
		System.out.println("sb is: " + sb);
		System.out.println("sb code: " + System.identityHashCode(sb));
		System.out.println("string is: " + s);
		System.out.println("string code: " + System.identityHashCode(s));
		
		sb.append("New Letters");
		s+="s";
		
		System.out.println("sb is: " + sb);
		System.out.println("sb code: " + System.identityHashCode(sb));
		System.out.println("string is: " + s);
		System.out.println("string code: " + System.identityHashCode(s));

			
		//Performance test
		String s2 = "";
		StringBuilder sb2 = new StringBuilder();
		StringBuffer sbuff = new StringBuffer();
		
		System.out.println("\nPerformance Times\n");
		
		int loop = 1000;
		long curTime = System.currentTimeMillis();
		
		for(int i = 0; i < loop; i++){
			sb2.append("a");
		}
		System.out.println("StringBuilder: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		for(int i = 0; i < loop; i++){
			sbuff.append("a");
		}
		System.out.println("StringBuffer: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		for(int i = 0; i < loop; i++){
			s2+="a";
		}
		System.out.println("String(Only 100000 appends): " + (System.currentTimeMillis() - curTime));
		
		System.out.println("\n\n");
		//tokenizer
		String s3 = "a b, c d, e f,g";
		String[] sArr = s3.split(",");
		
		for(String ss : sArr){
			System.out.println(ss);
		}
		
		StringTokenizer st = new StringTokenizer(s3);
		
		//"a b, c d, e f,g"
		while(st.hasMoreTokens()){
			st.nextToken(",");
		}
		

		
		
	}
}
