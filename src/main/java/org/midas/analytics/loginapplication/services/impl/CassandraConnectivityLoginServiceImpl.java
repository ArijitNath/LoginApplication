package org.midas.analytics.loginapplication.services.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.midas.analytics.loginapplication.exception.UserLoginException;
import org.midas.analytics.loginapplication.model.LoginDetails;
import org.midas.analytics.loginapplication.services.CassandraConnectivityLoginService;
import org.midas.analytics.loginapplication.utils.JsonParserUtil;
import org.springframework.stereotype.Service;

@Service
public class CassandraConnectivityLoginServiceImpl implements CassandraConnectivityLoginService {

	private static final String USER_AGENT = "Googlebot";
	private static final String ACCEPT = "*/*";
	private static final String CONTENT_TYPE = "application/json";
	private static final String LOGIN_GET_URL = "http://cassandraconnectivity:3070/midas/cassandra/login/";
	private static final Logger LOGGER = Logger.getLogger(CassandraConnectivityLoginServiceImpl.class.getName());
	
	private JsonParserUtil jsonParserUtil;
	
	@PostConstruct
	public void init() {
		jsonParserUtil = new JsonParserUtil();
	}
	
	@Override
	public LoginDetails getUserByUsername(String username) throws UserLoginException {
		try ( CloseableHttpClient httpClient = HttpClients.createDefault() ){
			LOGGER.log(Level.INFO, "CassandraConnectivityLoginServiceImpl:getUserByUsername :: Making GET request to URL " + LOGIN_GET_URL + username);
			HttpGet getRequest = new HttpGet(LOGIN_GET_URL + username);
	         
	        getRequest.addHeader(HttpHeaders.ACCEPT, ACCEPT);
	        getRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
	        getRequest.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);
	          
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        int statusCode = response.getStatusLine().getStatusCode();
	        
			LOGGER.log(Level.INFO, "CassandraConnectivityLoginServiceImpl:getUserByUsername :: Request to URL " + LOGIN_GET_URL + username + " Ended with Status code + " + statusCode);
	        
	        if (statusCode != 200) {
				LOGGER.log(Level.INFO, "CassandraConnectivityLoginServiceImpl:getUserByUsername :: Failed to get login details from Casandra. HTTP error code : " + statusCode);
	            throw new RuntimeException("Failed to get login details from Casandra. HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = response.getEntity();
	        String loginResponse = EntityUtils.toString(httpEntity);
	        LoginDetails loginDetails = this.jsonParserUtil.getLoginDetails(loginResponse);
	        
	        return loginDetails;
		}
		catch( Exception e ) {
			LOGGER.log(Level.INFO, "CassandraConnectivityLoginServiceImpl:getUserByUsername :: Error while fetching Userdetails from Cassandra. ", e);
			throw new UserLoginException(e.getMessage());
		}		
	}
}
