package com.dao;

import java.util.List;

import com.domain.FlashCard;

public interface DAO {
	
	public void createFlashCard(FlashCard flashCard);
	public FlashCard retrieveFlashCardById(int id);
	public List<FlashCard> retrieveAllFlashCards();
	public void updateFlashCard(FlashCard flashCard);
	public void deleteFlashCard(int id);
	
	
	public void createFlashCardPreparedStmt(FlashCard flashCard);
	public void createFcPreparedStmtGetPK(FlashCard flashCard);
	public void callHelloWorldProc();
	
}
