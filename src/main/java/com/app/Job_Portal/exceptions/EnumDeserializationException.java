package com.app.Job_Portal.exceptions;

@SuppressWarnings("serial")
public class EnumDeserializationException extends RuntimeException {

	
	public EnumDeserializationException(String value, Class<?> enumClass) {
        super("Error deserializing value '" + value + "' to " + enumClass.getSimpleName());
    }
}
