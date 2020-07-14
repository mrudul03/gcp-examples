package com.service.video.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class VideoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoService.class);
	
	@Autowired
	private TextProcessService textProcessService;
	
	@Autowired
	private LabelProcessService labelProcessService;
	
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public void processVideo(String message) {
		LOGGER.info("In method::processVideoText");
		JsonObject jsonObject = this.getMessageAsJson(message);
		if (null == jsonObject) {
			LOGGER.info("Error: Invalid Pub/Sub message:");
			return;
		}
		
		String gcsUri = this.getFileName(jsonObject);
		LOGGER.info("Processing file:" + gcsUri);
		
		executorService.execute(new Runnable() {
		    public void run() {
		    	LOGGER.info("calling processText");
		        textProcessService.processText(gcsUri);
		    }
		});
		
		executorService.execute(new Runnable() {
		    public void run() {
		        LOGGER.info("calling processlabel");
		        labelProcessService.processLabel(gcsUri);
		    }
		});
		
		
	}

	private JsonObject getMessageAsJson(String message) {
		JsonObject jsonObject = null;
		try {
			// String decodedMessage = new
			// String(Base64.getDecoder().decode(pubSubMessage));
			jsonObject = JsonParser.parseString(message).getAsJsonObject();
			
		} catch (Exception e) {
			String msg = "Error: Invalid Pub/Sub message: data property is not valid base64 encoded JSON";
			LOGGER.info(msg);
		}
		return jsonObject;
	}

	private String getFileName(JsonObject jsonObject) {

		String fileName = jsonObject.get("name").getAsString();
		String bucketName = jsonObject.get("bucket").getAsString();
		// BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();
		// Construct URI to GCS bucket and file.
		String gcsPath = String.format("gs://%s/%s", bucketName, fileName);
		return gcsPath;
	}

}
