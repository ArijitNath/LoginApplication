package org.midas.analytics.loginapplication.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.midas.analytics.loginapplication.model.AuthenticationRequest;
import org.midas.analytics.loginapplication.model.AuthenticationResponse;
import org.midas.analytics.loginapplication.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> authenticate( @RequestBody AuthenticationRequest authRequest ) throws Exception {
		LOGGER.log(Level.INFO, "LoginController:authenticate :: Initiating Authentication");
		try {
			authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
					);
		}
		catch (BadCredentialsException e) {
			LOGGER.log(Level.INFO, "LoginController:authenticate :: Incorrect Username or Password.", e);
			throw new Exception("LoginController:authenticate :: Incorrect Username or Password.", e);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));		
	}
}
