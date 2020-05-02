package com.certimeter.safestadium.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ 
	@PropertySource("classpath:application.properties"),
	
	@PropertySource(value = "classpath:/${env}/database.properties", ignoreResourceNotFound = false),
	@PropertySource(value = "classpath:/${env}/endpoint.properties", ignoreResourceNotFound = false),
	@PropertySource(value = "classpath:/${env}/filesystem.properties", ignoreResourceNotFound = false)
})
public class EnvironmentConfiguration {

	final Logger LOG = LoggerFactory.getLogger(EnvironmentConfiguration.class);
	
	
	@Value("${env}")
	private String env;
	
	@Value("${spring.datasource.url}")
	private String springDatasourceUrl;

	
	@PostConstruct
	public void checkEnvironmentUsed(){
		LOG.info("***************************************************************");
		LOG.info("***************************************************************");
		LOG.info("********************                       ********************");
		LOG.info("***************          CONFIGURATION          ***************");
		LOG.info("********************                       ********************");
		LOG.info("***************************************************************");
		LOG.info("***************************************************************");
		
		
		LOG.info("env => " + env);
		
		LOG.info("springDatasourceUrl => " + springDatasourceUrl);

		
		LOG.info("***************************************************************");
		LOG.info("***************************************************************");
		LOG.info("***************************************************************");
	}
	
}
