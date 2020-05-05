package com.revature.logging;

import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

/*
 * The purpose of this class is to responsively log information before or after method calls.
 */

//@Aspect
//@Component
//@ConditionalOnExpression("${endpoint.aspect.enabled:true}")
public class LoggingAspects {

	private static final Logger logger = LogManager.getLogger(LoggingAspects.class);

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() {
		System.out.println("controller");
	}

	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
		System.out.println("allmethod");
	}

	@Pointcut("execution(public * *(..))")
	protected void loggingPublicOperation() {
		System.out.println("publicOp");
	}

	@Pointcut("execution(* *.*(..))")
	protected void loggingAllOperation() {
		System.out.println("allOp");
	}

	@Pointcut("within(org.learn.log..*)")
	private void logAnyFunctionWithinResource() {
	}

	@After("controller() && allMethod()")
	public void afterCompletion(HttpServletResponse res, Object handler, Exception ex) {
		HttpServletResponse response = new ContentCachingResponseWrapper(res);
		String returnValue = this.getValue(handler);
		logger.debug("Method Return value : " + returnValue);
		logger.debug("Http response status: " + response.getStatus());
	}

	// After -> Any method within resource annotated with @Controller annotation
	// throws an exception ...Log it
	@AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		logger.error("Cause : " + exception.getCause());
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

//	@AfterThrowing("execution( * *() )")
//	public void logErrors( Throwable e ) { 
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		logger.error("Potentially fatal error/exception: ", e, t);
//	}

}
