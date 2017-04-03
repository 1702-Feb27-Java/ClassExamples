package com.revature.main;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.revature.service.EmployeeService;

public class FakeMain {
	static int timer = 0;
	static Date todaysDate = new Date();
	
	public static Date getTodaysDate() {
		return todaysDate;
	}

	public static void setTodaysDate(Date todaysDate) {
		FakeMain.todaysDate = todaysDate;
	}
	
	public static int getTimer() {
		return timer;
	}

	public static void setTimer(int timer) {
		FakeMain.timer = timer;
	}
	
	public void startTimer(){
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            myTask();
	            System.out.println(todaysDate);
	        }
	    }, 0, 60, TimeUnit.SECONDS);
	}


	private static void myTask() {
		EmployeeService serveEmp = new EmployeeService();
		//add 1 day to current day to get cutoff day
		Calendar c = Calendar.getInstance();
		c.setTime(todaysDate);
		c.add(Calendar.DATE, 1);  // number of days to add
		todaysDate = c.getTime();
		todaysDate = truncateDate(todaysDate);
		
		serveEmp.checkAutoApprove(todaysDate);
		
	    timer++;
	}
	
	public static Date truncateDate(Date date) {
	    return new java.sql.Date(date.getTime());
	}
}
