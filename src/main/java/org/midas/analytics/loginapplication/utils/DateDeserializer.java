package org.midas.analytics.loginapplication.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.midas.analytics.loginapplication.constants.LoginConstants;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {
	
	private static final Logger LOGGER = Logger.getLogger(DateDeserializer.class.getName());
	
	@Override
	public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String date = element.getAsString();

	    SimpleDateFormat format = new SimpleDateFormat(LoginConstants.DATE_FORMAT);
	    format.setTimeZone(TimeZone.getTimeZone(LoginConstants.GMT));
	    
	    try {
	    	return format.parse(date);
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "DateDeserializer:deserialize :: Failed to Parse Date", e);
			return null;
		}	    
	}
}