package com.revature.logging;

import org.apache.logging.log4j.Logger;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * The purpose of this class is to responsively log information before or after method calls.
 */

@Aspect
@Component
public class LoggingAspects extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LogManager.getLogger(LoggingAspects.class);
	
	@AfterThrowing("execution( * *() )")
	public void logErrors( Throwable e ) { 
		Timestamp t = new Timestamp(System.currentTimeMillis());
		logger.error("Potentially fatal error/exception: ", e, t);
	}
	
	public void logRequests(HttpServletRequest req) {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		logger.info("Request head: ", req.getRemoteAddr(), req.getMethod(), req.getRequestURI(), t);
	}
}
