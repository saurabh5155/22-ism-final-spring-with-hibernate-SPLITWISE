package com.controller.private_access;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.UserBean;
import com.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		List<UserBean> users =  userRepository.findAll();
		if(users==null) {
			return ResponseEntity.badRequest().body("User Not Found");
		}else {
			return ResponseEntity.ok().body(users);
		}
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
		UserBean user = userRepository.findByUserId(userId);
		if(user==null) {
			return ResponseEntity.badRequest().body("User Not Found");
		}else {
			return ResponseEntity.ok().body(user);
		}
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") int userId){
		UserBean user = userRepository.findByUserId(userId);
		if(user==null) {
			return ResponseEntity.badRequest().body("User Not Found");
		}else {
			userRepository.delete(user);
			return ResponseEntity.ok().body(user);
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@RequestBody @Valid UserBean userBean,BindingResult result){
		if(result.hasErrors()) {
			List<String> error = new ArrayList<>();
			System.out.println(result.getErrorCount());
			for (int i = 0; i < result.getErrorCount(); i++) {				
				String	addError	= result.getFieldErrors().get(i).getDefaultMessage();
				error.add(addError);
			}
			return ResponseEntity.badRequest().body(error);
		}else {
			if(userRepository.findByUserId(userBean.getUserId())!=null) {		
				if(userRepository.findByEmail(userBean.getEmail())==null) {					
					userRepository.save(userBean);			
					return ResponseEntity.ok().body(userBean);
				}else {					
					return ResponseEntity.badRequest().body("Email Already exist");
				}
			}else {				
				return ResponseEntity.badRequest().body("User Not Found");
			}
		}
	}
}
