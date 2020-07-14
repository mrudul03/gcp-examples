package com.image.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageServiceApplication {

	private static final Log LOGGER = LogFactory.getLog(ImageServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ImageServiceApplication.class, args);
    }
}
