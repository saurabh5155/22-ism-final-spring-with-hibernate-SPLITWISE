package com.controller.private_access;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.GroupBean;
import com.repository.GroupRepository;

@RestController
public class GroupController {

	@Autowired
	GroupRepository groupRepository;

	@PostMapping("/groups")
	public ResponseEntity<?> addGroup(@RequestBody GroupBean groupBean) {
		groupRepository.save(groupBean);
		return ResponseEntity.ok().body(groupBean);
	}
	
	@GetMapping("/groups")
	public ResponseEntity<?> getAllGroup(){
	 	List<GroupBean> groups= groupRepository.findAll();
	 	return ResponseEntity.ok().body(groups);
	}
	
	@GetMapping("/groups/{groupId}")
	public ResponseEntity<?> getGroupById(@PathVariable("groupId") int groupId){
		GroupBean group = groupRepository.findByGroupId(groupId);
		return ResponseEntity.ok().body(group);
	}
	
	@DeleteMapping("/groups/{groupId}")
	public ResponseEntity<?> deleteGroupById(@PathVariable("groupId") int groupId){
		GroupBean group = groupRepository.findByGroupId(groupId);
		if(group!=null) {
			groupRepository.delete(group);
		}
		return ResponseEntity.ok().body(group);
	}
	
	@PutMapping("/groups")
	public ResponseEntity<?> updateGroup(@RequestBody GroupBean groupBean) {
		groupRepository.save(groupBean);
		return ResponseEntity.ok().body(groupBean);
	}
}
