package com.revature;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
	
	public static void main(String[] args) throws InterruptedException {
		
		ApplicationContext spring = new ClassPathXmlApplicationContext("master.xml");
		System.out.println(spring.getBean("MySpringBean", MySpringBean.class).getSecret());
		((AbstractApplicationContext) spring).close();
		Thread.sleep(10000);
		
	}

}
