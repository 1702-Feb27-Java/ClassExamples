package User;

import java.util.ArrayList;

public class Messages {
				private int Sender;
				private int reciever; 
				private String messages;
				private int message_id;
				static ArrayList<Messages> Messages;
				
				
				public Messages(){
					
				}
				
				public int getMessage_id() {
					return message_id;
				}
				public int getSender() {
					return Sender;
				}
				public void setSender(int sender) {
					Sender = sender;
				}
				public int getReciever() {
					return reciever;
				}
				public void setReciever(int reciever) {
					this.reciever = reciever;
				}
				public void setMessages(String messages) {
					this.messages = messages;
				}
				public void setMessage_id(int message_id) {
					this.message_id = message_id;
				}
				public static ArrayList<Messages> getMessages() {
					return Messages;
				}
				public static void setMessages(ArrayList<Messages> messages) {
					Messages = messages;
				}
				
				
	}
