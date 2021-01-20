package com.baruch.coupons.exceptionHandler;


import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baruch.coupons.dataObjectsForPresentation.ErrorDataObject;
import com.baruch.coupons.enums.ErrorTypes;
import com.baruch.coupons.exceptions.ApplicationException;


@RestControllerAdvice
public class ExceptionsHandler {

	
	@ExceptionHandler
	@ResponseBody
	public ErrorDataObject toResponse(Throwable throwable, HttpServletResponse response) {
		
		if(throwable instanceof ApplicationException) {
		
			ApplicationException appException = (ApplicationException) throwable;

			ErrorTypes errorType = appException.getType(); 
			int errorNumber = errorType.getSerialNumber();
			String errorMessage = errorType.getMessage();
			String errorName = errorType.toString();
			if (appException.getType().isPresented()) {
				response.setStatus(errorNumber);
				ErrorDataObject ErrorDataObject = new ErrorDataObject(errorNumber, errorMessage, errorName);

				return ErrorDataObject;
			}
			if(errorNumber > 500){
				appException.printStackTrace();
			}
			return null;
		}
		
		response.setStatus(501);

		ErrorDataObject ErrorDataObject = new ErrorDataObject(301, "General error", 
		"Sorry, our services are currently unavailable");
		
		throwable.printStackTrace();

		return ErrorDataObject;
	}

}

