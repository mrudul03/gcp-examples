package com.service.video.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.service.video.consumers.VideoConsumer;

@Configuration
public class SubscriberConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;

    private final VideoConsumer videoConsumer;

    @Autowired
    public SubscriberConfig(PubSubTemplate pubSubTemplate, VideoConsumer videoConsumer) {
        this.pubSubTemplate = pubSubTemplate;
        this.videoConsumer = videoConsumer;
    }

    /**
     * It's called only when the application is ready to receive requests.
     * Passes a consumer implementation when subscribing to a Pub/Sub topic.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        LOGGER.info("Subscribing {} to {}", videoConsumer.getClass().getSimpleName(),
        		videoConsumer.subscription());
        pubSubTemplate.subscribe(videoConsumer.subscription(), videoConsumer.consumer());
    }

}
