package com.project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
}
