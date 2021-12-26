package com.suresh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.models.AuthenticationRequest;
import com.suresh.models.AuthenticationResponse;
import com.suresh.services.MyUserDetailsService;
import com.suresh.util.JwtUtil;

@RestController
public class HelloWorld {

	@Autowired
	private JwtUtil util;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@GetMapping("/hello")
	public String getMessage() {
		return "Hello Welcome  to JWT Demo";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
//AuthenticationRequest - maintains username and password
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {

			throw new Exception("username or password is incorrect");
		}

		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String generateToken = util.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(generateToken));
	}
	
	@GetMapping("/display")
	public String display() {
		return "I Understand littilebit about JWT and internal working flow";
	}
}
