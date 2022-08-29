package com.controller.public_access;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponceBean;
import com.bean.UserBean;
import com.repository.UserRepository;

@CrossOrigin
@RestController
public class SessionController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserBean userBean,BindingResult result){
		ResponceBean<UserBean> resUser=  new ResponceBean<>();
		if(result.hasErrors()) {
			List<String> error = new ArrayList<>();
			System.out.println(result.getErrorCount());
			for (int i = 0; i < result.getErrorCount(); i++) {				
				String	addError	= result.getFieldErrors().get(i).getDefaultMessage();
				error.add(addError);
			}
			resUser.setMessage(error);
			resUser.setData(userBean);
			resUser.setStatusCode(400);
			return ResponseEntity.badRequest().body(resUser);
		}else {
			if(userRepository.findByEmail(userBean.getEmail())==null) {					
				userRepository.save(userBean);			
				return ResponseEntity.ok().body(userBean);
			}else {					
				List<String> msg = new ArrayList<>();
				msg.add("Email Already Exist");
				resUser.setMessage(msg);
				resUser.setData(userBean);
				resUser.setStatusCode(400);
				return ResponseEntity.badRequest().body(resUser);
			}
		}
	}
}
