package com.sens.myapp.config;

import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

public class ScriptTemplateConfiguration {
	
    @Autowired
	private ResourceLoader resourceLoader;
	 
	@Bean
	public ScriptTemplateConfigurer configurer() {
	    ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
	    configurer.setEngine(scriptEngine());
	    configurer.setRenderFunction("render");
	    configurer.setSharedEngine(true);
	    return configurer;
	}
	 
	@Bean
	public ScriptEngine scriptEngine() {
	    ScriptEngine nashornScriptEngine = (ScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
	    try {

//	        nashornScriptEngine.eval(new InputStreamReader(resourceLoader.getResource("classpath:static/polyfill.js").getInputStream(), "UTF-8"));
//	        nashornScriptEngine.eval(new InputStreamReader(resourceLoader.getResource("classpath:META-INF/resources/webjars/handlebars/4.0.5/handlebars.min.js").getInputStream(), "UTF-8"));
//	        nashornScriptEngine.eval(new InputStreamReader(resourceLoader.getResource("classpath:static/js/templates.js").getInputStream(), "UTF-8"));
	        nashornScriptEngine.eval(new InputStreamReader(resourceLoader.getResource("classpath:static/render.js").getInputStream(), "UTF-8"));
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return nashornScriptEngine;
	}
}
