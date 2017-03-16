package com.revature.q20;

import java.io.BufferedReader;
import java.io.FileReader;

public class DataOutApp {

	public static void main(String[] args) {
		String s = null;
		try(BufferedReader fin = new BufferedReader(new FileReader("Data.txt"))){
			while((s = fin.readLine()) != null){
				String dataArr[] = fin.readLine().split(":");
				System.out.println(formatData(dataArr));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static char[] formatData(String[] dataArr) {
		// TODO Auto-generated method stub
		return("Name: " + dataArr[0] )
	}
}