package com.rev.service;

import java.util.ArrayList;

import com.rev.dao.DAOImp;
import com.rev.pojo.FlashCard;

public class Service {
	
	static DAOImp dao = new DAOImp();
	
	boolean doesQExist(String q){
		
		ArrayList<FlashCard> cards = new ArrayList<FlashCard>();
		
		cards = dao.getAllCards();
		
		for (FlashCard c : cards){
			String temp = c.getQuesiton();
			if (temp.equals(q)){
				return true;
			}
		}
		return false;
	}
	
	public void addCard(String q, String a){
		if (!doesQExist(q)){
			dao.addCard(q, a);
		}
		else {
			System.out.println("Card already added.");
		}
	}

}
