package com.revature.threads;

public class MyThread extends Thread{
	private Thread t;
	private String tName;
	
	public MyThread(String tName){
		this.tName = tName;
	}
	
	@Override
	public void run(){
		for (int i = 0; i < 20; i++) {
			try {
				System.out.println("\t\t" + tName + ": " + i);
				t.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(){
		if(t==null){
			t = new Thread(this, tName);
		}
		t.start();
	}
}
