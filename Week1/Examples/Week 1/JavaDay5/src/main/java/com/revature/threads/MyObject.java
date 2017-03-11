package com.revature.threads;

public class MyObject {
	private boolean inUse = false;
	private String s = "Start of string\n";
	private int i = 0;

	public synchronized void appendString(String s) {
		this.s += s;
	}

	public String getString() {
		return s;
	}

	public synchronized int getInt() throws InterruptedException {
		while(inUse) {
			wait();
		}
		inUse = true;
		return i;
	}

	public synchronized void setInt(int i) {
		this.i = i;
		inUse = false;
		notifyAll();
	}
}
