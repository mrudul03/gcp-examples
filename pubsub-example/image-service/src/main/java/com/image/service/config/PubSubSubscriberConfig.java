package com.image.service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.image.service.consumers.ImageConsumer;

@Configuration
public class PubSubSubscriberConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(PubSubSubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;

    private final ImageConsumer imageConsumer;

    @Autowired
    public PubSubSubscriberConfig(PubSubTemplate pubSubTemplate, ImageConsumer imageConsumer) {
        this.pubSubTemplate = pubSubTemplate;
        this.imageConsumer = imageConsumer;
    }

    /**
     * It's called only when the application is ready to receive requests.
     * Passes a consumer implementation when subscribing to a Pub/Sub topic.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        LOGGER.info("Subscribing {} to {}", imageConsumer.getClass().getSimpleName(),
        		imageConsumer.subscription());
        pubSubTemplate.subscribe(imageConsumer.subscription(), imageConsumer.consumer());
    }
}
