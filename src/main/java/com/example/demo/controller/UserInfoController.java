package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user")
	public UserInfo insertUser(@RequestBody UserInfo user) {
		String clearPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(clearPassword);
		user.setPassword(encodedPassword);
		return userInfoRepository.save(user);
	}
}
