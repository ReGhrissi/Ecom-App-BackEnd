package com.site3.ecommerce;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    
	public void addCorsMappings(CorsRegistry registry) {
		
		registry
		  .addMapping("/**")
		  .allowedMethods("*")
		  .allowedOrigins("*");
		
	}
}