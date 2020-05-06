package com.revature.logging;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Name: Trevor Miller, Anthony Ledgister		Timestamp: 5/6/20 6:39 am
*  Description: Configures the interceptor to listen to all incoming requests that match the listed patterns
*  Returns List<String>: Acceptable patterns for the interceptor to trigger on.
 */

@Configuration
public class MVCConfig implements WebMvcConfigurer {

	@Autowired
	private HTTPInterceptor logInterceptor;

	public List<String> urlPatterns() {
		List<String> patterns = new ArrayList<String>();
		patterns.add("//admins//");
		patterns.add("//batches//");
		patterns.add("//cars//");
		patterns.add("//login//");
		patterns.add("//users//");
		return patterns;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor).addPathPatterns(urlPatterns());
	}
}
