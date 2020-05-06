package com.revature.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Name: Trevor Miller, Anthony Ledgister		Timestamp: 5/6/20 6:39 am
*  Description: This class is used to custom handle exceptions by implementing the logger created for them.
*  Returns void: No return type, handles input exception.
 */

@Component
public class CustomExceptionHandler {
	
	private final Logger logger = LogManager.getLogger("errorLogger");
	
	@ExceptionHandler
	public void handleExcpetion(Exception e) {
		logger.error(e.getCause() + "\n" + e.getLocalizedMessage());
	}

}
