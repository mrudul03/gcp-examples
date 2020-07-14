package com.helloworld.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api/helloservice")
@Api(value = "Hello World System", description = "Operations pertaining to hello world")
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Value("${message}")
    private String message;
	
	@Autowired
    private Environment env;
	
	@ApiOperation(value = "Say hello")
	@GetMapping("/{name}")
	public ResponseEntity<String> hello(@PathVariable String name){
		
		LOGGER.info("called hello");
		return ResponseEntity.ok(message+"---"+name);
	}
	
}
