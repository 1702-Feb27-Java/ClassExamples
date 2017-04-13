package com.revature.service;

import com.revature.domain.FlashCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="flashCardService")
public class FlashCardServiceImpl implements FlashCardService
{

    @Override
    public void addFlashCard(FlashCard c) {

    }

    @Override
    public List<FlashCard> findAllFlashCards() {
        return null;
    }

    @Override
    public FlashCard findFlashCardByQuestion(String question) {
        return null;
    }
}
