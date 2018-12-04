package com.mhj.math.controllers;

import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class LoginController {

	@GetMapping(value="/login")
	public String loginForm() {
		return "loginForm";
	}
	
}
