package org.midas.analytics.loginapplication.services;

import org.midas.analytics.loginapplication.exception.UserLoginException;
import org.midas.analytics.loginapplication.model.LoginDetails;

public interface CassandraConnectivityLoginService {
	LoginDetails getUserByUsername(String username) throws UserLoginException;
}
