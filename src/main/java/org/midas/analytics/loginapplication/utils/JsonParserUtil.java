package org.midas.analytics.loginapplication.utils;

import java.util.Date;

import org.midas.analytics.loginapplication.model.CassandraLoginResponse;
import org.midas.analytics.loginapplication.model.LoginDetails;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParserUtil {	
	public LoginDetails getLoginDetails(String loginResponse) {		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		Gson gson = gsonBuilder.create();
		
		CassandraLoginResponse cassanrdaResponse = gson.fromJson(loginResponse, CassandraLoginResponse.class);
		
		return cassanrdaResponse.getLoginDetails().get(0);
	}
}
