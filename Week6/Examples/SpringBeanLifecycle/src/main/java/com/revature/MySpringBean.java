package com.revature;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MySpringBean implements BeanNameAware,
										BeanFactoryAware,
										InitializingBean,
										DisposableBean,
										Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7160921136599547629L;
	
	private String secret;
	private String beanName;
	
	@Override
	public void destroy() throws Exception{
		
		System.out.println("Destroy: " + beanName);
		
	}
	
	@Override
	public void setBeanName(String beanName){
		
		System.out.println("Bean Name Aware's Set Bean Name: " + beanName);
		this.beanName = beanName;
		
	}
	
	@Override
	public void setBeanFactory(BeanFactory field) throws BeansException{
		
		System.out.println("Bean Factory Aware's Set Bean Factory " + field);
		
	}
	
	public void customDestroyMethod(){
		
		System.out.println("Custom Destroy: " + beanName);
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception{
		
		System.out.println("After Properties Set: "+ beanName);
		
	}
	
	@Override
	public String toString() {
		return "MySpringBean [secret=" + secret + ", beanName=" + beanName + "]";
	}

	public void customInitializationMethod(){
		
		System.out.println("Custom Initializtion: " + beanName);
		
	}
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getBeanName() {
		return beanName;
	}

}
