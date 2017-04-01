package beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="HONEY_POT")

public class HoneyPot implements Serializable{

	@Id
	@Column(name="HONEYPOT_ID")
	@SequenceGenerator(name="HONEYPOTID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HONEYPOTID_SEQ")
	private int id;
	
	@Column(name="HONEY_AMOUNT")
	private double amount;
	
	@Column(name="VOLUME")
	private double volume;
	
	public HoneyPot(int id, double amount, double volume) {
		super();
		this.id = id;
		this.amount = amount;
		this.volume = volume;
	}
	
	public HoneyPot(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "HoneyPot [id=" + id + ", amount=" + amount + ", volume=" + volume + "]";
	}
	
}
