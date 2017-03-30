package com.revature.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date(); // get current day
		String today = sdf.format(date);
		
		System.out.println(today);
		Date todaysDate = new SimpleDateFormat("MM-dd-yyyy").parse(today);
		
		Date startdate = new SimpleDateFormat("MM-dd-yyyy").parse("04-05-2017");
		
		long diff = Math.abs(todaysDate.getTime() - startdate.getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		System.out.println(diffDays);
		
	}

}
