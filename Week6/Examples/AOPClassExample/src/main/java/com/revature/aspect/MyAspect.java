package com.revature.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	
	@After("everything()")
	//@Before("execution(public * *(..))")
	public void followUp(JoinPoint jp){
		
		System.out.println(jp.getSignature());
		System.out.println("Wasn't that a thrilling coversation");
		
	}
	
	@Pointcut("execution(* *(..))")
	public void everything(){}
	


}
