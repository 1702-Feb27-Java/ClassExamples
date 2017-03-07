package com.revature.threads;

public class MyThread extends Thread {
	
	private Thread t;
	private String tName;
	
	public MyThread(String tName){
		this.tName = tName;
	}

	@Override
	public void run(){
		for (int i = 0; i < 20 ; i++){
			System.out.println("\t\t" + tName + ": " + i);
			try {
				t.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void start(){
		if(t==null){
			t = new Thread(tName);
		}
		t.start();
	}
}
