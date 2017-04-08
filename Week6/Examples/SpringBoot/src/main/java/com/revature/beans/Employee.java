package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This should be a standard Hibernate annotated pojo
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee
{
	@Id
	@GeneratedValue(generator="EMPLOYEE_AUTO_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="EMPLOYEE_AUTO_SEQ", sequenceName="EMPLOYEE_AUTO_SEQ", allocationSize=1)
	@Column(name="E_ID")
	private int id;
	@Column(name="E_FIRSTNAME")
	private String first;
	@Column(name="E_LASTNAME")
	private String last;
	@Column(name="E_EMAIL")
	private String email;

	public Employee()
	{
		
	}

	public Employee(int id, String first, String last, String email) {
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", first=" + first + ", last=" + last + ", email=" + email + "]";
	}
}
