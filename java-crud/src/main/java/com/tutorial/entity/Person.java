package com.tutorial.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.CascadeType.REFRESH;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tutorial.strategies.EmailStrategy;

import uk.co.jemos.podam.common.PodamStrategyValue;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private String lastName;

	@PodamStrategyValue(EmailStrategy.class)
	private String email;
	
	private LocalDate dateOfBirth;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;
	
	//@ManyToOne(cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH})
	@JoinColumn(name = "idCompany")
	private Company company;
	
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
