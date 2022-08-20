package com.controller.public_access;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.UserBean;
import com.repository.UserRepository;

@RestController
public class SessionController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserBean userBean,BindingResult result){
		if(result.hasErrors()) {
			List<String> error = new ArrayList<>();
			System.out.println(result.getErrorCount());
			for (int i = 0; i < result.getErrorCount(); i++) {				
				String	addError	= result.getFieldErrors().get(i).getDefaultMessage();
				error.add(addError);
			}
			return ResponseEntity.badRequest().body(error);
		}else {
			if(userRepository.findByEmail(userBean.getEmail())==null) {					
				userRepository.save(userBean);			
				return ResponseEntity.ok().body(userBean);
			}else {					
				return ResponseEntity.badRequest().body("Email Already exist");
			}
		}
	}
}
