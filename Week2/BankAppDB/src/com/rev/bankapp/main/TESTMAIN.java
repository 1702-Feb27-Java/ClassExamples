package com.rev.bankapp.main;

import com.rev.bankapp.dao.BankDAO;

public class TESTMAIN {

	public static void main(String[] args) {

	/*	BankDAO.deleteUser(22);*/
		
		
		BankDAO.requestAccount(23, 1);
		System.out.println("test success");
		

	}
}
