package com.revature.driver;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.domain.FlashCard;
import com.revature.service.FlashCardService;
import com.revature.service.FlashCardServiceImple;

public class Driver {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

		FlashCardService flashCardService = applicationContext.getBean("flashCardService", FlashCardServiceImple.class);

		/* FlashCard fc = new FlashCard(); */

		/*
		 * fc.setId(101); fc.setQuestion("This is a new question1");
		 * fc.setAnswer("THis is the new answer");
		 */

		// flashCardService.addFlashCard(fc);

		List<FlashCard> fcList = flashCardService.findAllFlashCards();

		for (FlashCard temp : fcList) {

			System.out.println(temp);

		}

		System.out.println(flashCardService.findFlashCardByQuestion("Why is Spring awesome?"));

		fcList = flashCardService.alphabeticalOrder();

		for (FlashCard temp : fcList) {

			System.out.println(temp);

		}

		fcList = flashCardService.topTen();

		for (FlashCard temp : fcList) {

			System.out.println(temp);

		}

	}

}
