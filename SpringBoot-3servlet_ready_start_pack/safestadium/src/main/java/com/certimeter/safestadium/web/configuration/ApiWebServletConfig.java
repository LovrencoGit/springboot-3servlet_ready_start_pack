package com.certimeter.safestadium.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@ComponentScan(basePackages = {
	"com.certimeter.jobservations.web.configuration",
	"com.certimeter.jobservations.web.controller"
})
@EnableWebMvc
public class ApiWebServletConfig {

}
