package com.rev.test;

import com.rev.dao.DAOImp;
import com.rev.pojo.FlashCard;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DAOImp dao = new DAOImp();
		
		FlashCard fc = new FlashCard();
//		fc.setId(3);
//		
//		dao.updateQuestion(fc, "question?");

//		dao.addCard("Does this work?", "I guess");
		
//		FlashCard fc = dao.getCardByID(3);
//		System.out.println(fc.toString());
		
		fc.setId(4);
		dao.updateAnswer(fc, "updating answer");
		
		
	}

}
