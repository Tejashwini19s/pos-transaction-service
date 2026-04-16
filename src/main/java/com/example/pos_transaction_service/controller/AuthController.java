package com.example.pos_transaction_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos_transaction_service.security.JwtUtil;

@RestController
public class AuthController {
	@GetMapping("/generate-token")
	public String login() {
		return JwtUtil.generateToken("user");
	}

}
