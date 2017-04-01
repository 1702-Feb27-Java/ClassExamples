package beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CAVE")
public class Cave implements Serializable{
	private Set<Bear> bears;
	
	@Id
	@Column(name="CAVE_ID")
	@SequenceGenerator(name="CAVEID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAVEID_SEQ")
	private int id;
	
	@Column(name="CAVE_TYPE")
	private String type;
	
	@Column(name="SQ_FOOTAGE")
	private double sqFootage;
	
	@OneToMany(mappedBy="bearHome")
	public Set<Bear> getBears(){
		return bears;
	}
	public void setBears(Set<Bear> bears){
		this.bears = bears;
	}
	
	public Cave(int id, String type, double sqFootage) {
		super();
		this.id = id;
		this.type = type;
		this.sqFootage = sqFootage;
	}
	
	public Cave(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSqFootage() {
		return sqFootage;
	}

	public void setSqFootage(double sqFootage) {
		this.sqFootage = sqFootage;
	}

	@Override
	public String toString() {
		return "Cave [id=" + id + ", type=" + type + ", sqFootage=" + sqFootage + "]";
	}

}
