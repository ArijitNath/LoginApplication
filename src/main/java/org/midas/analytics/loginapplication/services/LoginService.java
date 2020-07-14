package org.midas.analytics.loginapplication.services;

import java.util.List;

import org.midas.analytics.loginapplication.model.LoginDetails;

public interface LoginService {
	List<LoginDetails> getAllUser();
	LoginDetails getUserByID(String id);
	LoginDetails saveOrUpdate(LoginDetails loginDetails);
	boolean delete(LoginDetails loginDetails);
}
