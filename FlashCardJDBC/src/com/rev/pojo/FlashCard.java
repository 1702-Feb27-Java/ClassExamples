package com.rev.pojo;

public class FlashCard {
	
	int id;
	String quesiton;
	String answer;
	
	public FlashCard(int id, String quesiton, String answer) {
		super();
		this.id = id;
		this.quesiton = quesiton;
		this.answer = answer;
	}

	public FlashCard() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuesiton() {
		return quesiton;
	}

	public void setQuesiton(String quesiton) {
		this.quesiton = quesiton;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "FlashCard [id=" + id + ", quesiton=" + quesiton + ", answer=" + answer + "]";
	}

}
