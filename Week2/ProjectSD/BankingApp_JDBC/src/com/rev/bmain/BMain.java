package com.rev.bmain;

import com.rev.services.BService;

public class BMain {

	private static BService bServe = null; 
	public static void main(String[] args) {
		
		boolean bRet = bServe.runApp();
	}
}
