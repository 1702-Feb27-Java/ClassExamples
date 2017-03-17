package com.revature.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.revature.service.EmployeeService;

public class Main {
	static int timer = 0;
	
	static EmployeeService serveEmp = new EmployeeService();
	public static void main(String[] args) {
		
		startTimer();
		System.out.println("benwebsta id: " + serveEmp.loginEmployee("benwebsta", "password"));	
		System.out.println("jLee id: " + serveEmp.loginEmployee("jLee", "password"));
	}
	
	public static void startTimer(){
	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            myTask();
	            System.out.println(timer);
	        }
	    }, 0, 5, TimeUnit.SECONDS);
	}
	
	private static void myTask() {
	    timer++;
	}

}
