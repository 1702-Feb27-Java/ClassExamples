package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="HONEY_POT")
public class HoneyPot {

	@Id
	@Column(name="HONEYPOT_ID")
	@SequenceGenerator(name="HONEYPOTID_SEQ", sequenceName="HONEYPOTID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HONEYPOTID_SEQ")
	private int honeyPotId;

	@Column(name="HONEY_AMOUNT")
	private double honeyAmount;
	
	@Column(name="VOLUME")
	private double volume;

	public HoneyPot() {
		super();
	}

	public HoneyPot(int honeyPotId, double honeyAmount, double volume) {
		super();
		this.honeyPotId = honeyPotId;
		this.honeyAmount = honeyAmount;
		this.volume = volume;
	}

	public HoneyPot(double honeyAmount, double volume){
		
		this.honeyAmount = honeyAmount;
		this.volume = volume;
	}
	
	public int getHoneyPotId() {

		return honeyPotId;
	}

	public void setHoneyPotId(int honeyPotId) {
		this.honeyPotId = honeyPotId;
	}

	public double getHoneyAmount() {
		return honeyAmount;
	}

	public void setHoneyAmount(double honeyAmount) {
		this.honeyAmount = honeyAmount;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
}
