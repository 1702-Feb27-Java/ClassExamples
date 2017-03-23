package com.revature.pojo;

public class Message {
	String message, messager;
	int messagerId;
	public Message(String message, String messager, int messagerId) {
		super();
		this.message = message;
		this.messager = messager;
		this.messagerId = messagerId;
	}
	public Message(){
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessager() {
		return messager;
	}
	public void setMessager(String messager) {
		this.messager = messager;
	}
	public int getMessagerId() {
		return messagerId;
	}
	public void setMessagerId(int messagerId) {
		this.messagerId = messagerId;
	}
	@Override
	public String toString() {
		return "Message [message=" + message + ", messager=" + messager + ", messagerId=" + messagerId + "]";
	}


}
