package com.revature.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.domain.FlashCard;

@Repository("flashCardRepository")
public interface FlashCardRepository extends JpaRepository<FlashCard, Integer>{
	
	public FlashCard findByQuestion(String question);
	
	public List<FlashCard> findAllByOrderQuestionDesc();
	
	//public List<FlashCard> findTop10ByAnswerAsc();

}
