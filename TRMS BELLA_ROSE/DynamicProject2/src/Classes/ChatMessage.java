package Classes;

public class ChatMessage
{

	private String note;
	private int Reim_id;
	private int chat_id;
	private int author_id;
	private int sent_to_id;
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	public int getReim_id()
	{
		return Reim_id;
	}
	public void setReim_id(int reim_id)
	{
		Reim_id = reim_id;
	}
	public int getChat_id()
	{
		return chat_id;
	}
	public void setChat_id(int chat_id)
	{
		this.chat_id = chat_id;
	}
	public int getAuthor_id()
	{
		return author_id;
	}
	public void setAuthor_id(int author_id)
	{
		this.author_id = author_id;
	}
	public int getSent_to_id()
	{
		return sent_to_id;
	}
	public void setSent_to_id(int sent_to_id)
	{
		this.sent_to_id = sent_to_id;
	}
	public ChatMessage(String note, int reim_id, int chat_id, int author_id, int sent_to_id)
	{
		super();
		this.note = note;
		Reim_id = reim_id;
		this.chat_id = chat_id;
		this.author_id = author_id;
		this.sent_to_id = sent_to_id;
	}
	public ChatMessage()
	{
		super();
	}
	
	
	
}
