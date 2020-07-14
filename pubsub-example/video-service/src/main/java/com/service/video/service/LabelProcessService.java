package com.service.video.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.videointelligence.v1.AnnotateVideoProgress;
import com.google.cloud.videointelligence.v1.AnnotateVideoRequest;
import com.google.cloud.videointelligence.v1.AnnotateVideoResponse;
import com.google.cloud.videointelligence.v1.Entity;
import com.google.cloud.videointelligence.v1.Feature;
import com.google.cloud.videointelligence.v1.LabelAnnotation;
import com.google.cloud.videointelligence.v1.LabelSegment;
import com.google.cloud.videointelligence.v1.VideoAnnotationResults;
import com.google.cloud.videointelligence.v1.VideoIntelligenceServiceClient;

@Service
public class LabelProcessService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LabelProcessService.class);
	
	public void processLabel(String gcsUri) {

		try (VideoIntelligenceServiceClient client = VideoIntelligenceServiceClient.create()) {
			
			// Create an operation that will contain the response when the operation
			// completes.
			AnnotateVideoRequest request = AnnotateVideoRequest.newBuilder().setInputUri(gcsUri)
					.addFeatures(Feature.LABEL_DETECTION).build();

			OperationFuture<AnnotateVideoResponse, AnnotateVideoProgress> response = client.annotateVideoAsync(request);

			LOGGER.info("Waiting for operation to complete...");

			List<VideoAnnotationResults> results = response.get().getAnnotationResultsList();
			if (results.isEmpty()) {
				LOGGER.info("No labels detected in " + gcsUri);
				return;
			}
			LOGGER.info("Labels detected in " + results);
			for (VideoAnnotationResults result : results) {
				LOGGER.info("Labels:");
				LOGGER.info("Labels::SegmentLabelAnnotationsList().size():"
						+ result.getSegmentLabelAnnotationsList().size());
				// get video segment label annotations
				for (LabelAnnotation annotation : result.getSegmentLabelAnnotationsList()) {
					LOGGER.info("Video label description : " + annotation.getEntity().getDescription());
					// categories
					for (Entity categoryEntity : annotation.getCategoryEntitiesList()) {
						LOGGER.info("Label Category description : " + categoryEntity.getDescription());
					}
					// segments
					for (LabelSegment segment : annotation.getSegmentsList()) {
						double startTime = segment.getSegment().getStartTimeOffset().getSeconds()
								+ segment.getSegment().getStartTimeOffset().getNanos() / 1e9;
						double endTime = segment.getSegment().getEndTimeOffset().getSeconds()
								+ segment.getSegment().getEndTimeOffset().getNanos() / 1e9;
						LOGGER.info("Segment location : %.3f:%.3f\n", startTime, endTime);
						LOGGER.info("Confidence : " + segment.getConfidence());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception video processing for labels", e);

		}
	}

}
