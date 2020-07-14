package com.service.video;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoServiceApplication {
	
private static final Log LOGGER = LogFactory.getLog(VideoServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
    }

}
