package com.revature.service;

import com.revature.domain.FlashCard;
import java.util.List;

public interface FlashCardService
{
    public void addFlashCard(FlashCard c);
    public List<FlashCard> findAllFlashCards();
    public FlashCard findFlashCardByQuestion(String question);
}
