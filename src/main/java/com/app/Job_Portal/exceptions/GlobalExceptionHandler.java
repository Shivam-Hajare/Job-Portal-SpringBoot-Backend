package com.app.Job_Portal.exceptions;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.Job_Portal.dto.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice // =@ControllerAdvice => global exc handler class
//--common interceptor to intercept ALL excs in all contoller + @ResponseBody added impl. 
//on ret types of all req handling methods 
public class GlobalExceptionHandler {
	// method level anno to tell SC , following is an exc handling method : to
	// handle MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in method arg invalid " + e);
		List<FieldError> fieldErrors = e.getFieldErrors();// list of fiels having validation errs
		Map<String, String> map = fieldErrors.stream()
				.collect(Collectors.toMap(f -> f.getField(), f -> f.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}

	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
	
//	 @ExceptionHandler(BindException.class)
//	    public ResponseEntity<String> handleBindException(BindException ex) {
//	        String errorMessage = "Invalid input data. Please check your input and try again.";
//	        return ResponseEntity.badRequest().body(errorMessage);
//	    }
	
	
	// method level anno to tell SC , following is an exc handling method : to
	// handle : ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in res not found " + e);
		return new ApiResponse(e.getMessage());
	}

	// method level anno to tell SC , following is an exc handling method : to
	// handle any other remaining exc => catch all
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handleAnyException(RuntimeException e) {
		System.out.println("in catch-all " + e);
		return new ApiResponse(e.getMessage());
	}
	
	@ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidInputException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // You can customize the error response as needed
        String errorMessage = "Email already in use. Please check your input.";
        //Data integrity violation occurred
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String originalErrorMessage = ex.getMostSpecificCause().getMessage();
        String customErrorMessage = "Invalid date format. Please provide the date in yyyy-MM-dd format.";

        if (ex.getMostSpecificCause() instanceof DateTimeParseException) {
            // Handle DateTimeParseException specifically
            customErrorMessage = "Invalid date format. Please provide a valid date.";
        }

        if(ex.getMostSpecificCause()instanceof InvalidFormatException)
        {
        	customErrorMessage="Invalid Job type, Job Type Must be in:[FULL_TIME,PART_TIME] ";
        }
        return ResponseEntity.badRequest().body(customErrorMessage);
    }
	
	
	
	
}
