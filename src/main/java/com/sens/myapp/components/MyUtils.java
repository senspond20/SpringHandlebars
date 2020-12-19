package com.sens.myapp.components;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component("MyUtils")
public class MyUtils {

	@Autowired
	private ResourceLoader resourceLoader;
	private final String JS_ROOT = "classpath:static/js";
	
	public InputStreamReader getStream(String fileName) throws UnsupportedEncodingException, IOException {
		
		String path = fileName.substring(0, 1).equals("/") 
					? JS_ROOT.concat(fileName)
					: JS_ROOT.concat("/").concat(fileName); 
		return new InputStreamReader(resourceLoader.getResource(path).getInputStream(), "UTF-8");
	}

}
