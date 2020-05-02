package com.certimeter.safestadium.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.certimeter.safestadium.filter.LogEndpointFilter;

@Configuration
@EnableWebMvc
public class FilterConfiguration implements WebMvcConfigurer {

	/*********************************************************************************************************/
	/**									LogEndpointFilter FILTER CONFIGURATION								**/
	/*********************************************************************************************************/
	
	@Autowired
	private LogEndpointFilter logEndpointFilter;
	
	@Bean
	public FilterRegistrationBean<LogEndpointFilter> configurationLogEndpointFilter(){
	    FilterRegistrationBean<LogEndpointFilter> registrationBean = new FilterRegistrationBean<LogEndpointFilter>();

	    registrationBean.setFilter(logEndpointFilter);
	    registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
	    
	    registrationBean.addServletNames( new String[] {"apiBACKOFFICE", "apiMOBILE", "apiWEB"} );
	    
	    return registrationBean;    
	}

}
