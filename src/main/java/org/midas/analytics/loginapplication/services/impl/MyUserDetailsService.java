package org.midas.analytics.loginapplication.services.impl;

import java.util.ArrayList;

import org.midas.analytics.loginapplication.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new User(userName, loginService.getUserByID(userName).getLoginPassword(), new ArrayList<>());
	}

}