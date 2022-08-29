package com.bean;

import java.util.List;

public class UserGroupBean {
	
	private Integer groupId;
	
	private List<Integer> userId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public List<Integer> getUserId() {
		return userId;
	}

	public void setUserId(List<Integer> userId) {
		this.userId = userId;
	}


}
