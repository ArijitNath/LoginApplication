package org.midas.analytics.loginapplication.services.impl;

import java.util.ArrayList;

import org.midas.analytics.loginapplication.exception.UserLoginException;
import org.midas.analytics.loginapplication.model.LoginDetails;
import org.midas.analytics.loginapplication.services.CassandraConnectivityLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CassandraConnectivityLoginService cassandraConnectivityLoginService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LoginDetails loginDetails;
		String password = "";
		
		try {
			loginDetails = cassandraConnectivityLoginService.getUserByUsername(userName);
			password = loginDetails.getLoginPassword();			
		} catch (UserLoginException e) {
			throw new UsernameNotFoundException("MyUserDetailsService:loadUserByUsername :: Error while fetching username from Casandra", e);
		}
		
		return new User(userName, password, new ArrayList<>());
	}

}