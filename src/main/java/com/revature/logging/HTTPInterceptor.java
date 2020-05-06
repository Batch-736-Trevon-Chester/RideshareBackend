package com.revature.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/*
 * Name: Trevor Miller, Anthony Ledgister		Timestamp: 5/6/20 6:39 am
*  Description: This class intercepts all methods involving a request or a response. It will log the request before the 
*  method and the response after the method without consuming either.
*  Returns boolean or void: Boolean on the preHandler() to allow it to continue, and void on the afterCompletion()
 */

@RestControllerAdvice
public class HTTPInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LogManager.getLogger("HTTPLogger");

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler)
			throws Exception {
		HttpServletRequest request = new ContentCachingRequestWrapper(req);
		logger.warn("Before Request Routed: ");
		logger.warn("Request method: " + request.getMethod().toString());
		logger.warn("Request URL: " + request.getRequestURL().toString());
		logger.warn("IP address: " + request.getRemoteAddr());
		logger.warn("Query string: " + request.getQueryString());
		logger.warn("Request URI: " + request.getRequestURI());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse res, Object handler,
			@Nullable Exception ex) throws Exception {
		HttpServletResponse response = new ContentCachingResponseWrapper(res);
		String returnValue = this.getValue(handler);
		logger.warn("Method Return value : " + returnValue);
		logger.warn("Http response status: " + response.getStatus());
		logger.warn("\n");
	}

	private String getValue(Object result) {
		String returnValue = null;
		if (null != result) {
			if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
				returnValue = ReflectionToStringBuilder.toString(result);
			} else {
				returnValue = result.toString();
			}
		}
		return returnValue;
	}

}
