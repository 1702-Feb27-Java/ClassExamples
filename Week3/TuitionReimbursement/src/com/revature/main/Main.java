package com.revature.main;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.revature.dao.DAOEmployeeImpl;
import com.revature.service.EmployeeService;

public class Main {
	static int timer = 0;
	
	static EmployeeService serveEmp = new EmployeeService();
	static DAOEmployeeImpl empDao = new DAOEmployeeImpl();
	public static void main(String[] args) {
		
/*		int emp_id = 9;
		String event = "Acting";
		String eventDate = "06/29/2017";
		String time = "10am";
		int location = 1;
		String formDate = "03/19/2017";
		String description = "Some description";
		int cost = 100;
		int grading_id = 2;
		int typeOfEventId = 1;
		int urgentId = 1;
		int approval_step_id = 1;
		int approval_cutoff = 8;*/
		
		startTimer();
		System.out.println("benwebsta id: " + serveEmp.loginEmployee("benwebsta", "password"));	
		System.out.println("jLee id: " + serveEmp.loginEmployee("jLee", "password"));
		//System.out.println(serveEmp.applyForReimbursement(emp_id, event, eventDate, time, location, formDate, description, cost, 
		//		grading_id, typeOfEventId, urgentId, approval_step_id, approval_cutoff));
		//System.out.println(serveEmp.loginEmployee("mLin", "password"));
		//serveEmp.getReimbursements(3);
		//System.out.println(serveEmp.getAllGradingTypes());
		ArrayList<String> attachments = new ArrayList<String>();
		attachments.add("attachment1.com/yes");
		attachments.add("attachment2.net/no");
		attachments.add("attachment3");
		System.out.println(serveEmp.submitEdit(3, attachments));
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
