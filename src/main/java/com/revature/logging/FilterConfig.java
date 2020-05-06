package com.revature.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/*
 * Name: Trevor Miller, Anthony Ledgister		Timestamp: 5/6/20 6:39 am
*  Description: This class is used to configure the logging filter
*  Returns CommonsRequestLoggingFilter: Used to configure custom filters 
 */

@Configuration
public class FilterConfig {
	
	@Bean
    public CommonsRequestLoggingFilter requestLogFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST: ");
        return filter;
    }

}
