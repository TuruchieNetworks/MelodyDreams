package com.turuchie.melodydreams.utils;

import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.persistence.criteria.Path;

public class MvcConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path<?> trackImageUploadDir = (Path<?>) Paths.get("./TrackArtImages");
		String trackImageUploadPath = ((java.nio.file.Path) trackImageUploadDir).toFile().getAbsolutePath();
		
		registry.addResourceHandler("/trackArtImages/**").addResourceLocations("file:/" + trackImageUploadPath);
	}

}



//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//	}
