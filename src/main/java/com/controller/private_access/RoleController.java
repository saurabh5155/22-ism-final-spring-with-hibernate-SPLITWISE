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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponceBean;
import com.bean.RoleBean;
import com.repository.RoleRepository;

@RestController
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@PostMapping("/roles")
	public ResponseEntity<?> addRoles(@RequestBody @Valid RoleBean roleBean, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errorMessage = new ArrayList<>();
			for (int i = 0; i < result.getErrorCount(); i++) {
				String e = result.getFieldErrors().get(i).getDefaultMessage();
				errorMessage.add(e);
			}
			ResponceBean<RoleBean> resRole=  new ResponceBean<>();
			resRole.setMessage(errorMessage);
			resRole.setData(roleBean);
			resRole.setStatusCode(400);
			return ResponseEntity.badRequest().body(resRole);
		} else {
			roleRepository.save(roleBean);
			return ResponseEntity.ok().body(roleBean);
		}
	}

	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles() {
		List<RoleBean> roles = roleRepository.findAll();
		if (roles == null) {
			return ResponseEntity.badRequest().body("Role Not Found");
		} else {
			return ResponseEntity.ok().body(roles);
		}
	}

	@GetMapping("/roles/{roleId}")
	public ResponseEntity<?> getRoleById(@PathVariable("roleId") int roleId) {
		RoleBean role = roleRepository.findByRoleId(roleId);
		if (role == null) {
			return ResponseEntity.badRequest().body("Role Not Found");
		} else {
			return ResponseEntity.ok().body(role);
		}
	}

	@DeleteMapping("/roles/{roleId}")
	public ResponseEntity<?> deleteRoleById(@PathVariable("roleId") int roleId) {
		RoleBean roleBean = roleRepository.findByRoleId(roleId);
		if (roleBean == null) {
			return ResponseEntity.ok().body("This RoleId is not avalible");
		} else {
			roleRepository.delete(roleBean);
			return ResponseEntity.ok().body(roleBean);
		}
	}

	@PutMapping("/roles")
	public ResponseEntity<?> updateRole(@RequestBody @Valid RoleBean roleBean, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errorMessage = new ArrayList<>();
			for (int i = 0; i < result.getErrorCount(); i++) {
				String e = result.getFieldErrors().get(i).getDefaultMessage();
				errorMessage.add(e);
			}
			ResponceBean<RoleBean> resRole=  new ResponceBean<>();
			resRole.setMessage(errorMessage);
			resRole.setData(roleBean);
			resRole.setStatusCode(400);

			return ResponseEntity.badRequest().body(errorMessage);
		} else {
			roleRepository.save(roleBean);
			return ResponseEntity.ok().body(roleBean);
		}
	}
}
