package beans;

import java.io.Serializable;
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

@Entity
@Table(name="BEAR")
public class Bear implements Serializable{
	
	@Id
	@Column(name="BEAR_ID")
	@SequenceGenerator(name="BEARID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BEARID_SEQ")
	private int id;
	
	@Column(name="BEAR_COLOR")
	private String color;
	
	@Column(name="BREED")
	private String breed;
	
	@Column(name="HEIGHT")
	private double height;
	
	@Column(name="WEIGHT")
	private double weight;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="CAVE_ID")
	private Cave bearHome;
	
	@ManyToMany
	@JoinTable(name="PARENT_CUB", 
		joinColumns=@JoinColumn(name="PARENT_ID"),
		inverseJoinColumns=@JoinColumn(name="CUB_ID"))
	private Set<Bear> bearCubs;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="HONEYPOD_ID")
	private HoneyPot HoneyPot;
	
	public Bear(int id, String color, String breed, double height, double weight) {
		super();
		this.id = id;
		this.color = color;
		this.breed = breed;
		this.height = height;
		this.weight = weight;
	}
	
	public Bear(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", color=" + color + ", breed=" + breed + ", height=" + height + ", weight=" + weight
				+ "]";
	}

}
