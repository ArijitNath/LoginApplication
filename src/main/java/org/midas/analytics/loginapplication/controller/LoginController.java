package org.midas.analytics.loginapplication.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.midas.analytics.loginapplication.model.AuthenticationRequest;
import org.midas.analytics.loginapplication.model.AuthenticationResponse;
import org.midas.analytics.loginapplication.services.LoginService;
import org.midas.analytics.loginapplication.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	private Logger LOGGER = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private LoginService loginService;

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
	
	@GetMapping("/me") 
	public String n() {
		return "Arijit";
	}
	
	/*@GetMapping(value = "/user", produces = "application/json")
	public List<LoginDetails> getAlluser() {
		return loginService.getAllUser();
	}
	
	@GetMapping(value = "/user/{id}", produces = "application/json")
	public LoginDetails getUserByID( @PathVariable("id") String id ) {
		return loginService.getUserByID(id);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public LoginDetails addUser(@RequestBody LoginDetails loginDetails) {
		return loginService.saveOrUpdate(loginDetails);
	}
	
	@DeleteMapping(value = "/delete")
	public boolean deleteUser(@RequestBody LoginDetails loginDetails) {
		return loginService.delete(loginDetails);
	}
	
	*/
}
