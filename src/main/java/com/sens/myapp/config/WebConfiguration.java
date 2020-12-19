package com.sens.myapp.config;

import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		HandlebarsViewResolver resolver = new HandlebarsViewResolver();
//		resolver.setCharset("UTF-8");
		resolver.setOrder(1);
		resolver.setContentType("text/html;charset=UTF-8");
//		resolver.setPrefix("classpath:/templates/");
		resolver.setPrefix("classpath:/templates");
		resolver.setCache(false); // 개발시에는 false, 배포시에는 true
		resolver.setSuffix(".html");
		registry.viewResolver(resolver);
	}



}
