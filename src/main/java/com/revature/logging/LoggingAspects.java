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

@Aspect
@Component
//@ConditionalOnExpression("${endpoint.aspect.enabled:true}")
public class LoggingAspects {

	private static final Logger logger = LogManager.getLogger(LoggingAspects.class);
    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }
    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.revature.beans..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }
	
	/*
	 * @After("springBeanPointcut() && applicationPackagePointcut()") public void
	 * afterCompletion(HttpServletResponse res, Object handler, Exception ex) {
	 * HttpServletResponse response = new ContentCachingResponseWrapper(res); String
	 * returnValue = this.getValue(handler); logger.debug("Method Return value : " +
	 * returnValue); logger.debug("Http response status: " + response.getStatus());
	 * }
	 */

	// After -> Any method within resource annotated with @Controller annotation
	// throws an exception ...Log it
	@AfterThrowing(pointcut = "springBeanPointcut() && applicationPackagePointcut()", throwing = "exception")
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
