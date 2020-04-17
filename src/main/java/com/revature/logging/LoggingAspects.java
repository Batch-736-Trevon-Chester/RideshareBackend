package com.revature.logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
 * The purpose of this class is to responsively log information before or after method calls.
 */

@Aspect
@Component
public class LoggingAspects {
	
	private static final Logger logger = LogManager.getLogger(LoggingAspects.class);
	
	
	
}
