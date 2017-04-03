package com.tres.objs;

public class Message
{
	private int msgid, 
				sender,
				receiver,
				reimid;
	private String msg,
				sndname,
				rcvname;
	private int rinfo;
	//============ GETTERS AND SETTERS =========================================================
	public final int getMsgid()
	{
		return msgid;
	}
	public final void setMsgid(int msgid)
	{
		this.msgid = msgid;
	}
	public final int getSender()
	{
		return sender;
	}
	public final void setSender(int sender)
	{
		this.sender = sender;
	}
	public final int getReceiver()
	{
		return receiver;
	}
	public final void setReceiver(int receiver)
	{
		this.receiver = receiver;
	}
	public final int getReimid()
	{
		return reimid;
	}
	public final void setReimid(int reimid)
	{
		this.reimid = reimid;
	}
	public final String getMsg()
	{
		return msg;
	}
	public final void setMsg(String msg)
	{
		this.msg = msg;
	}
	public final String getSndname()
	{
		return sndname;
	}
	public final void setSndname(String sndname)
	{
		this.sndname = sndname;
	}
	public final String getRcvname()
	{
		return rcvname;
	}
	public final void setRcvname(String rcvname)
	{
		this.rcvname = rcvname;
	}
	public final int getRinfo()
	{
		return rinfo;
	}
	public final void setRinfo(int rinfo)
	{
		this.rinfo = rinfo;
	}
	//=============================== CONSTRUCTORS ======================================================
	public Message(int msgid, int sender, int receiver, int reimid, String msg, String sndname, String rcvname, int rinfo)
	{
		super();
		this.msgid = msgid;
		this.sender = sender;
		this.receiver = receiver;
		this.reimid = reimid;
		this.msg = msg;
		this.sndname = sndname;
		this.rcvname = rcvname;
		this.rinfo = rinfo;
	}
	public Message()
	{
		super();
	}
	
}
