package com.service.video.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.videointelligence.v1.AnnotateVideoProgress;
import com.google.cloud.videointelligence.v1.AnnotateVideoRequest;
import com.google.cloud.videointelligence.v1.AnnotateVideoResponse;
import com.google.cloud.videointelligence.v1.Feature;
import com.google.cloud.videointelligence.v1.TextAnnotation;
import com.google.cloud.videointelligence.v1.TextSegment;
import com.google.cloud.videointelligence.v1.VideoAnnotationResults;
import com.google.cloud.videointelligence.v1.VideoIntelligenceServiceClient;

@Service
public class TextProcessService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TextProcessService.class);
	
	public void processText(String gcsUri) {
		
		try (VideoIntelligenceServiceClient client = VideoIntelligenceServiceClient.create()) {
			// Create the request
			AnnotateVideoRequest request = AnnotateVideoRequest.newBuilder().setInputUri(gcsUri)
					.addFeatures(Feature.TEXT_DETECTION).build();

			// asynchronously perform object tracking on videos
			OperationFuture<AnnotateVideoResponse, AnnotateVideoProgress> future = client.annotateVideoAsync(request);

			LOGGER.info("Waiting for operation to complete...");
			// The first result is retrieved because a single video was processed.
			//AnnotateVideoResponse response = future.get(300, TimeUnit.SECONDS);
			List<VideoAnnotationResults> results = future.get().getAnnotationResultsList();
			this.logAnnotationResults(results);
			
		}
		catch (Exception e) {
			LOGGER.error("Exception video processing for text", e);
		}
	}
	
	
	private void logAnnotationResults(/**AnnotateVideoResponse response*/ List<VideoAnnotationResults> results) {
		//List<VideoAnnotationResults> results = response.getAnnotationResultsList();
		for (VideoAnnotationResults result : results) {
			LOGGER.info("Labels:");
			//TextAnnotation annotation = result.getTextAnnotations(0);
			List<TextAnnotation> annotations = result.getTextAnnotationsList();
			for(TextAnnotation annotation: annotations) {
				LOGGER.info("Text: " + annotation.getText());
				
				List<TextSegment> segments = annotation.getSegmentsList();
				//LOGGER.info("Text::segments.size() " + segments.size());
				
				for(TextSegment textSegment: segments) {
					LOGGER.info("TextSegment Confidence: " + textSegment.getConfidence());
					// For the text segment display it's time offset
//					VideoSegment videoSegment = textSegment.getSegment();
//					Duration startTimeOffset = videoSegment.getStartTimeOffset();
//					Duration endTimeOffset = videoSegment.getEndTimeOffset();
					
					// Display the offset times in seconds, 1e9 is part of the formula to convert
					// nanos to seconds
//					LOGGER.info(
//							String.format("Start time: %.2f", startTimeOffset.getSeconds() + startTimeOffset.getNanos() / 1e9));
//					LOGGER.info(
//							String.format("End time: %.2f", endTimeOffset.getSeconds() + endTimeOffset.getNanos() / 1e9));

					// Show the first result for the first frame in the segment.
//					TextFrame textFrame = textSegment.getFrames(0);
//					Duration timeOffset = textFrame.getTimeOffset();
//					LOGGER.info(String.format("Time offset for the first frame: %.2f",
//							timeOffset.getSeconds() + timeOffset.getNanos() / 1e9));

					// Display the rotated bounding box for where the text is on the frame.
//					LOGGER.info("Rotated Bounding Box Vertices:");
//					
//					List<NormalizedVertex> vertices = textFrame.getRotatedBoundingBox().getVerticesList();
//					for (NormalizedVertex normalizedVertex : vertices) {
//						LOGGER.info(String.format("\tVertex.x: %.2f, Vertex.y: %.2f", normalizedVertex.getX(),
//								normalizedVertex.getY()));
//					}
				}
			}
		}
	}

}
