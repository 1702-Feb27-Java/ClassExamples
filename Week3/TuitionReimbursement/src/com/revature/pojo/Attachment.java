package com.revature.pojo;

import java.net.URL;

public class Attachment {
	private URL link;
	private String name;
	
	public Attachment(URL link, String name) {
		super();
		this.link = link;
		this.name = name;
	}
	
	public Attachment(){
		
	}
	
	public URL getLink() {
		return link;
	}
	public void setLink(URL link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Attachment [link=" + link + ", name=" + name + "]";
	}
	
}
