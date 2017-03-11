package com.revature.threads;

public class SimpleRunnable implements Runnable {
	MyObject o;
	
	public SimpleRunnable(MyObject o){
		this.o = o;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			int j = 0;
			try {
				j = o.getInt();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			j++;
			o.setInt(j);
			
		}
	}

}
