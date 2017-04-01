package com.revature.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="BEAR")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="myAwesomeCache")
public class Bear {
	
	@Id
	@Column(name="BEAR_ID")
	@SequenceGenerator(name="BEARID_SEQ", sequenceName="BEARID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BEARID_SEQ")
	private int bearId;
	
	@Column(name="BEAR_COLOR")
	private String bearColor;
	
	@Column(name="BREED")
	private String breed;
	
	@Column(name="HEIGHT")
	private double height;
	
	@Column(name="WEIGHT")
	private double weight;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="CAVE_ID")
	private Cave bearHome;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PARENT_CUB",
				joinColumns=@JoinColumn(name="PARENT_ID"),
				inverseJoinColumns=@JoinColumn(name="CUB_ID"))
	private Set<Bear> bearCubs;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="HONEYPOT_ID")
	private HoneyPot HoneyPot;

	public Bear(){
		
		
	}
	
	public Bear(String bearColor, String breed, int height, int weight, HoneyPot honeyPot) {
		// TODO Auto-generated constructor stub
		this.bearColor = bearColor;
		
	}

	@Override
	public String toString() {
		return "Bear [bearId=" + bearId + ", bearColor=" + bearColor + ", breed=" + breed + ", height=" + height
				+ ", weight=" + weight + ", bearCubs=" + bearCubs + ", HoneyPot=" + HoneyPot + "]";
	}

	public void setBearHome(Cave newHome) {
		// TODO Auto-generated method stub
		bearHome=newHome;
	}

	public HoneyPot getHoneyPot() {
		// TODO Auto-generated method stub
		return HoneyPot;
	}

	public Set<Bear> getChildren() {
		// TODO Auto-generated method stub
		return bearCubs;
	}
	
	
}
