package com.gazorpazorp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	private Long id;
//	private String id;
	private Long userId;
//	private String userId;
	private String firstName;
	private String lastName;
	private String address;

	public Account() {}
	
	
//	@Id
//	@Column(columnDefinition = "BINARY(16)")
//	@GeneratedValue(generator = "uuid2")
//	@GenericGenerator(name = "uuid2", strategy = "uuid2")
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	@Id
	@Column(name = "id", length = 20)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

//	@Column(columnDefinition = "BINARY(16)")
//	public String getUserId() {
//		return id;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	
	@Column(name = "user_id", length = 20)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "first_name", length = 30)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 30)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "address", length = 255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
