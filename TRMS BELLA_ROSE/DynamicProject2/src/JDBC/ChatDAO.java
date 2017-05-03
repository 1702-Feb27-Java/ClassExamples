package JDBC;

import java.util.LinkedList;

import Classes.ChatMessage;

public interface ChatDAO
{

//////CREATE
public void insertMessage(ChatMessage per);

/////    READ
public LinkedList<ChatMessage> getAllMessages();
public LinkedList<ChatMessage> getMessagesByReimId(int id);

//////    UPDATE
public void saveMessage(ChatMessage per);

//////    DELETE
public void deleteMessageById(int id);


	
}
