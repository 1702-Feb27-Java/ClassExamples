package com.revature.pojo;

public class Message {
	String message, messager;
	int messagerId, messageId, reimbId;
	public Message(String message, String messager, int messagerId, int messageId, int reimbId) {
		super();
		this.message = message;
		this.messager = messager;
		this.messagerId = messagerId;
		this.messageId = messageId;
		this.reimbId = reimbId;
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
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	@Override
	public String toString() {
		return "Message [message=" + message + ", messager=" + messager + ", messagerId=" + messagerId + ", messageId=" + messageId + ", reimbId=" + reimbId + "]";
	}


}
