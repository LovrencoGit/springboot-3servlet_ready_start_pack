package com.certimeter.safestadium.backoffice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@ComponentScan(basePackages = {
	"com.certimeter.jobservations.backoffice.configuration",
	"com.certimeter.jobservations.backoffice.controller"
})
@EnableWebMvc
public class ApiBackofficeServletConfig {

}
