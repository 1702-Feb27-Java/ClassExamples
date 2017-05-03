package com.revature.driver;

import com.revature.domain.FlashCard;
import com.revature.service.FlashCardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        FlashCardService flashCardService = applicationContext.getBean("flashCardService", flashCardServiceImpl.class);

        FlashCard fc = new FlashCard();
        fc.setId(100);
        fc.setQuestion("New Question");
        fc.setAnswer("New Answer");

        List<FlashCard> fcList = flashCardService.findAllFlashCards();
        for(FlashCard temp: fcList)
        {
            System.out.println(temp);
        }

        System.out.println(flashCardService.findFlashCardByQuestion("Why is Spring awesome?"));
    }
}
