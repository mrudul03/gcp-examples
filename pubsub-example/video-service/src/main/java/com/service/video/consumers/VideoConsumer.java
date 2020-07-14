package com.service.video.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.stereotype.Component;

import com.google.pubsub.v1.PubsubMessage;
import com.service.video.service.VideoService;

@Component
public class VideoConsumer extends PubSubConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VideoConsumer.class);
	
	@Autowired
	private VideoService videoService;
	
	@Override
	public String subscription() {
		return "video-workers";
	}

	@Override
	protected void consume(BasicAcknowledgeablePubsubMessage acknowledgeablePubsubMessage) {
		// TODO Auto-generated method stub
		
		// extract wrapped message
        PubsubMessage message = acknowledgeablePubsubMessage.getPubsubMessage();
        String messageData = message.getData().toStringUtf8();
        // process message
        LOGGER.info("message received and sending to VideoService: " + messageData);
        //videoService.processVideo(messageData);
        videoService.processVideo(messageData);

        // acknowledge that message was received
        acknowledgeablePubsubMessage.ack();
	}

}
