package com.revature.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.services.ContactTheDao;

public class Driver {
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ContactTheDao ctd = ac.getBean("contactTheDao", ContactTheDao.class);
		System.out.println(ctd.telephone(8675309l, "hey, it the the ContactTheDao class?"));
		
		com.revature.services2.ContactTheDriver fakectd = ac.getBean("imposterContacter", com.revature.services2.ContactTheDriver.class);
		System.out.println(fakectd.telephone(1681527641, "this is fake"));
		
		System.out.println(ctd.hangup());
		
	}

}
