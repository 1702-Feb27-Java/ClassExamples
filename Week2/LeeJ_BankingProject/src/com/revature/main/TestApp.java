package com.revature.main;

import java.util.ArrayList;

import com.revature.banking.Account;
import com.revature.banking.User;
import com.revature.dao.DAOImplement;

public class TestApp {

	public static void main(String[] args) {
		DAOImplement dao = new DAOImplement();
		try {
//			dao.addAccount(82, 1, 1);
			
			/*
			 * TODO if username and pw unique, call dao.getUserId method and store userid
			 * use this id in the next layer when you add account
			 */
//			int id = dao.getUserId(c.getUsername(), c.getPassword());
//			System.out.println(id);
//			
//			
//			if (dao.addAccount(id, 1, 1) == 0)
//				System.out.println("program didn't run");
//			if (dao.addAccount(id, 1, 1) == 0)
//				System.out.println("program didn't run");
//			ArrayList<Integer> al = dao.getAccountIds(id);
//			for(int i : al)
//				System.out.println(i);
//			System.out.println("add account executed from main, now for delete account");
//			
//			double bal = dao.getBalance(81);
//			System.out.println(bal);
//			
//			User user = dao.getUser(47);
//			Account account = dao.getAccount(81);
//			System.out.println(user);
//			System.out.println(account);
//			System.out.println("getbalance returned " + bal);
//			System.out.println("setBalance");
//			dao.setBalance(5, bal + 10);
	//		dao.deleteAccount(5);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception in main");
		}
		
		System.out.println("main finished");

	}

}
