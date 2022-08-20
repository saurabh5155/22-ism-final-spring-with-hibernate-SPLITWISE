package com.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "roles")
public class RoleBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;	
	
	@NotBlank(message = "Please Enter roleName")
	private String roleName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<UserBean> user;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<UserBean> getUser() {
		return user;
	}
	public void setUser(List<UserBean> user) {
		this.user = user;
	}
	
}
