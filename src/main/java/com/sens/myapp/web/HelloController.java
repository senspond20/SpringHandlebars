package com.sens.myapp.web;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sens.myapp.BootHandleApplication;
import com.sens.myapp.components.MyUtils;

import lombok.RequiredArgsConstructor;

@Controller
public class HelloController {

	@Autowired
	@Qualifier("MyUtils")
	private MyUtils myutils;
	
	@GetMapping("/")
	public ModelAndView hello() throws ScriptException, NoSuchMethodException, UnsupportedEncodingException, IOException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "안녕하세요");
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/api/test")
	@ResponseBody
	public Object test() throws NoSuchMethodException, ScriptException, UnsupportedEncodingException, IOException {
		
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");	
		engine.eval("print('Hello World!');");
		engine.eval(myutils.getStream("script.js"));
		Invocable invocable = (Invocable) engine;
		
		Object result = invocable.invokeFunction("fun1", "안녕하세요");
		System.out.println(result);
		return result;
	}
	
	@GetMapping("/api/test2")
	@ResponseBody
	public Object test2() throws NoSuchMethodException, ScriptException, UnsupportedEncodingException, IOException, URISyntaxException {
		
//		Path jsPath = Paths.get(BootHandleApplication.class.getResource("/fibonacci.js").toURI());
		// GraalVM
	    System.setProperty("polyglot.js.nashorn-compat", "true");
		ScriptEngine graalEngine = new ScriptEngineManager().getEngineByName("graal.js");
		 
//		 graalEngine.eval("print('Hello World!');");
		 graalEngine.eval(myutils.getStream("script.js"));
		Invocable invocable = (Invocable) graalEngine;
		
		Object result = invocable.invokeFunction("fun1", "안녕하세요");
		System.out.println(result);
		return result;
	}
	
  
	
	
}
