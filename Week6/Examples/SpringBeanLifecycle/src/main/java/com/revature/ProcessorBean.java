package com.revature;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ProcessorBean implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		
		if(bean instanceof MySpringBean){
			
			System.out.println("Post Initialization Bean Post Processor "+beanName);
			
		}
		
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		
		if(bean instanceof MySpringBean){
			
			System.out.println("Pre Initialization Bean Post Processor "+beanName+" "+bean);
			
		}
		
		return bean;
	}

}
