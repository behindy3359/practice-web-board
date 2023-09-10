package com.practice.webboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private String resourcePath = "/upload/**";
	private String savePath = "file:///C:/workspace/web/practice-web-board/PracticeWebBoard/src/main/resources/templates/filestorage/";
	
	@Override
	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
	}
}
