package com.revature.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.beans.House;

@Component
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		House h = (House) ac.getBean("houseImpl");
		
		h.setName("Our House");
		
		System.out.println("h is " + h);
		
		House h2 = (House) ac.getBean("houseImpl");
		
		h2.setName("THe Rising Sun");
		
		h2.getWallid().setMaterial("Rock");
		
		System.out.println(h2);
		
		System.out.println(h);

	}

}
