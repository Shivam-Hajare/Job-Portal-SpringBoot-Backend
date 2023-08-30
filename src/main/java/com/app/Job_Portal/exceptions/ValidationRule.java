package com.app.Job_Portal.exceptions;





public class ValidationRule {

	
	
	public static void validationOfPhoneNumber(String phoneNumber) throws InvalidInputException
	{
		if (!phoneNumber.matches("\\d{10}")) {
            throw new InvalidInputException("Invalid phone number format. Please provide a 10-digit number.");
        }
		
	}
	
	
		
	
	
}
