package com.revature.starting;

import java.io.IOException;

import com.rev.dao.DAOimpl;

public class MainApp extends FunctionClass {

	public static void main(String[] args) throws IOException {
		DAOimpl dao = new DAOimpl();
		FunctionClass func = new FunctionClass();
		shotcaller();
		
	}
}
