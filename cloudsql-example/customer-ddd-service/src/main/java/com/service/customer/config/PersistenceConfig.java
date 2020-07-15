package com.service.customer.config;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import com.service.customer.model.GeneratedId;

@Configuration
public class PersistenceConfig {
	
	@SuppressWarnings("rawtypes")
	@Bean
    public ApplicationListener<BeforeSaveEvent> idGenerator() {
        return event -> {
            var entity = event.getEntity();
            if (entity instanceof GeneratedId) {
                ((GeneratedId) entity).setId(UUID.randomUUID().toString());
            }
        };
    }
}
