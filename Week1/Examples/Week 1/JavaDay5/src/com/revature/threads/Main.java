package com.revature.threads;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread mainT = Thread.currentThread();

		System.out.println(mainT.getName());  // gets name of the current thread
		System.out.println(mainT.isDaemon());  // not daemon, thread must terminate before program does
		
		MyRunnable r1 = new MyRunnable("Thread1");
		MyThread r2 = new MyThread("Thread2");
		r1.start();
		r2.start();
	}

}
