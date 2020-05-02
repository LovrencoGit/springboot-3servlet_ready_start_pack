package com.certimeter.safestadium.mobile.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@ComponentScan(basePackages = {
	"com.certimeter.safestadium.mobile.configuration",
	"com.certimeter.safestadium.mobile.controller",
	"com.certimeter.safestadium.mobile.service"
})
@EnableWebMvc
public class ApiMobileServletConfig {

}
