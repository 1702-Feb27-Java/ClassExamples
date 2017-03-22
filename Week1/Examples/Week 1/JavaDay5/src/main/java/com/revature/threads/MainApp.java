package com.revature.threads;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
/*		
		Thread mainT = Thread.currentThread();
		
		System.out.println(mainT.getName());
		
		System.out.println(mainT.isDaemon());
		//Not daemon: Thread must terminate before program does
		
		MyRunnable r1 = new MyRunnable("Thread1");
		MyThread t2 = new MyThread("Thread2");
		r1.start();
		t2.start();
		*/
		MyObject o = new MyObject();
		
		SimpleRunnable sr1 = new SimpleRunnable(o);
		Thread stThread1 = new Thread(sr1,"Thread1");
		
		SimpleRunnable sr2 = new SimpleRunnable(o);
		Thread stThread2 = new Thread(sr2, "Thread2");
		
	
		
		stThread1.start();
		stThread2.start();
		try{
			Thread.currentThread().sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}

		System.out.println(o.getInt());
		
	}

}
