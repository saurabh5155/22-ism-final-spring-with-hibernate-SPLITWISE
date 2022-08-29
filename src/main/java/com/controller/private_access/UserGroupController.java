package com.controller.private_access;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.GroupBean;
import com.bean.ResponceBean;
import com.bean.UserBean;
import com.bean.UserGroupBean;
import com.repository.GroupRepository;
import com.repository.UserGroupRepository;
import com.repository.UserRepository;

@RestController
public class UserGroupController {

	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	UserGroupRepository userGroupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/userGroup")
	public ResponseEntity<?> addUserGroup(@RequestBody UserGroupBean userGroupBean) {
		ResponceBean<GroupBean> resUserGroup = new ResponceBean<>();
		
		GroupBean groupBean = groupRepository.findByGroupId(userGroupBean.getGroupId());
		
		if (groupBean != null) {
			for (int i = 0; i < userGroupBean.getUserId().size(); i++) {
				UserBean userBean = userRepository.findByUserId(userGroupBean.getUserId().get(i));
				if(userBean!=null) {					
					groupBean.getUsers().add(userBean);
					groupRepository.save(groupBean);
				}else {
					List<Integer> nullUser = new ArrayList<Integer>() ;
					nullUser.add(userGroupBean.getUserId().get(i));
					
				}
			}
			
			return ResponseEntity.ok().body(groupBean);
		}else {
			resUserGroup.setData(groupBean);
			resUserGroup.setResMsg("Group is Null");
			resUserGroup.setStatusCode(400);
			return ResponseEntity.ok().body(resUserGroup);
			
		}

	}
	
	@GetMapping("/userGroupByUserId/{userId}")
	public ResponseEntity<?> getAllGroupByUserId(@PathVariable("userId") Integer userId){
		List<GroupBean> groups = userGroupRepository.findByUsers(userId);
		return ResponseEntity.ok().body(groups);
	}
	
	@GetMapping("/userGroup/{groupId}")
	public ResponseEntity<?> getAllGroupByGroupId(@PathVariable("groupId") Integer groupId){
		GroupBean group = userGroupRepository.findByGroupId(groupId);
		return ResponseEntity.ok().body(group);
	}
	
}
