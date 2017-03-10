package com.rev.dao;

import java.util.ArrayList;

import com.rev.pojo.FlashCard;

public interface DAOInter {
	
	int addCard(String q, String a);
	FlashCard getCardByID(int id);
	
	int updateQuestion(FlashCard fc, String q);
	int updateAnswer(FlashCard fc, String a);
	ArrayList<FlashCard> getAllCards();
	
}
