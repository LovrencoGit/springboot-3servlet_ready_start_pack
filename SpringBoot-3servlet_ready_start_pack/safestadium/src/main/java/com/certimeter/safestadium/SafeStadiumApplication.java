package com.certimeter.safestadium;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.certimeter.safestadium.backoffice.configuration.ApiBackofficeServletConfig;
import com.certimeter.safestadium.mobile.configuration.ApiMobileServletConfig;
import com.certimeter.safestadium.web.configuration.ApiWebServletConfig;

@ComponentScan({					
	/*"com.certimeter.duffandphelps.controller",*/ 
	"com.certimeter.safestadium.configuration", 	
	"com.certimeter.safestadium.filter",
	"com.certimeter.safestadium.service",
	"com.certimeter.safestadium.repository"
})
@MapperScan("com.certimeter.safestadium.mapper")

@SpringBootApplication
public class SafeStadiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeStadiumApplication.class, args);
	}

	/**************************************************************************/
	/************					SERVLETs DEFINITION				***********/
	/**************************************************************************/

	@Bean
	public ServletRegistrationBean<DispatcherServlet> apiMOBILE() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApiMobileServletConfig.class);
		dispatcherServlet.setApplicationContext(applicationContext);
		ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet, "/mobile/*");
		servletRegistrationBean.setName("apiMOBILE");
		servletRegistrationBean.setLoadOnStartup(1);
		
//		/***   Multipart configuration for Upload   ***/
//		servletRegistrationBean.setMultipartConfig( new MultipartConfigElement("", 1024*1024*10, 1024*1024*30, 0) );
		
		return servletRegistrationBean;
	}
	@Bean
	public ServletRegistrationBean<DispatcherServlet> apiWEB() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApiWebServletConfig.class);
		dispatcherServlet.setApplicationContext(applicationContext);
		ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet, "/web/*");
		servletRegistrationBean.setName("apiWEB");
		servletRegistrationBean.setLoadOnStartup(1);
		
//		/***   Multipart configuration for Upload   ***/
//		servletRegistrationBean.setMultipartConfig( new MultipartConfigElement("", 1024*1024*10, 1024*1024*30, 0) );
		
		return servletRegistrationBean;
	}
	@Bean
    public ServletRegistrationBean<DispatcherServlet> apiBACKOFFICE() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApiBackofficeServletConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet, "/backoffice/*");
        servletRegistrationBean.setName("apiBACKOFFICE");
        //servletRegistrationBean.setLoadOnStartup(1);
        
        return servletRegistrationBean;
    }
	
}
