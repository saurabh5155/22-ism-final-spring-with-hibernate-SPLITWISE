package com.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UserBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotBlank(message = "Please Enter FirstName")
	private String firstName;
	
	@NotBlank(message = "Please Enter LastName")
	private String lastName;
	
	@NotBlank(message = "Please Enter Gender")
	private String gender;
	
	@NotBlank(message = "Please Enter Email")
	private String email;
	
	@NotBlank(message = "Please Enter Password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "roleId",nullable = false)
	private RoleBean role;

	@JsonIgnore
	@ManyToMany(mappedBy = "users")
	private Set<GroupBean> groups = new HashSet<>();
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public Set<GroupBean> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupBean> groups) {
		this.groups = groups;
	}
	
	
}
