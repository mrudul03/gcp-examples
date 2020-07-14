package com.image.service.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.stereotype.Component;

import com.google.pubsub.v1.PubsubMessage;

@Component
public class ImageConsumer extends PubSubConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageConsumer.class);

	@Override
	public String subscription() {
		return "image-workers";
	}

	@Override
	protected void consume(BasicAcknowledgeablePubsubMessage acknowledgeablePubsubMessage) {
		// TODO Auto-generated method stub
		// extract wrapped message
        PubsubMessage message = acknowledgeablePubsubMessage.getPubsubMessage();

        // process message
        LOGGER.info("message received: " + message.getData().toStringUtf8());

        // acknowledge that message was received
        acknowledgeablePubsubMessage.ack();
	}

}
