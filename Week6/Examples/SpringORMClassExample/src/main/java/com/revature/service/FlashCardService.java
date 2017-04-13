package com.revature.service;

import java.util.List;

import com.revature.domain.FlashCard;

public interface FlashCardService {
	
	public void addFlashCard(FlashCard fc);
	public List<FlashCard> findAllFlashCards();
	public FlashCard findFlashCardByQuestion(String question);
	public List<FlashCard> topTen();
	public List<FlashCard> alphabeticalOrder();

}
