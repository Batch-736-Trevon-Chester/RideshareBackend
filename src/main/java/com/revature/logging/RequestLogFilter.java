package com.revature.logging;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

public class RequestLogFilter extends AbstractRequestLoggingFilter {

	private static final String DEVICE_ID = "DEVICE_ID";
	private static final String DEVICE_TYPE = "DEVICE_TYPE";
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {

		logger.info("Before Request Begins: ");
		logger.debug("Request method: " + request.getMethod().toString());
		logger.debug("Request URL: " + request.getRequestURL().toString());
		logger.debug("IP address: " + request.getRemoteAddr());
		logger.debug("Query string: " + request.getQueryString());
		logger.debug("Request URI: " + request.getRequestURI());
		logger.debug("Request Header for DEVICE_ID: " + request.getHeader(DEVICE_ID));
		logger.debug("Request Header for DEVICE_TYPE: " + request.getHeader(DEVICE_TYPE));

	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {

		logger.info("Request Begins Routed: ");

	}
}