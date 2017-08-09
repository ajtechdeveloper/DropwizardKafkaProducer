package com.aj.dropwizardkafkaproducer.resource;

import com.aj.dropwizardkafkaproducer.DropwizardKafkaProducerConfiguration;
import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropwizardKafkaProducerHealthCheckResource extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardKafkaProducerHealthCheckResource.class);

    private static String appName;

    public DropwizardKafkaProducerHealthCheckResource(DropwizardKafkaProducerConfiguration dropwizardKafkaProducerConfiguration){
       this.appName = dropwizardKafkaProducerConfiguration.getAppName();
    }

    @Override
    protected Result check() throws Exception {
        logger.info("App Name is: {}", appName);
        if("DropwizardKafkaProducer".equalsIgnoreCase(appName)) {
            return Result.healthy();
        }
        return Result.unhealthy("Dropwizard Kafka Producer Service is down");
    }
}