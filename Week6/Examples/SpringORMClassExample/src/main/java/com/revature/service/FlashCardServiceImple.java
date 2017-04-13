package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.revature.dao.FlashCardRepository;
import com.revature.domain.FlashCard;

@Component(value="flashCardService")
public class FlashCardServiceImple implements FlashCardService {

	@Autowired
	@Qualifier(value="flashCardRepository")
	private FlashCardRepository dao;
	
	@Override
	public void addFlashCard(FlashCard fc) {
		// TODO Auto-generated method stub
		dao.save(fc);

	}

	@Override
	public List<FlashCard> findAllFlashCards() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public FlashCard findFlashCardByQuestion(String question) {
		// TODO Auto-generated method stub
		return dao.findByQuestion(question);
	}

	@Override
	public List<FlashCard> topTen() {
		// TODO Auto-generated method stub
		return null;// dao.findTop10ByAnswerAsc();
	}

	@Override
	public List<FlashCard> alphabeticalOrder() {
		// TODO Auto-generated method stub
		return dao.findAllByOrderQuestionDesc();
	}

}
