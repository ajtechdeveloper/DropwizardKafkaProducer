package com.aj.dropwizardkafkaproducer;

import com.aj.dropwizardkafkaproducer.resource.DropwizardKafkaProducerHealthCheckResource;
import com.aj.dropwizardkafkaproducer.resource.KafkaProducerResource;
import com.aj.dropwizardkafkaproducer.resource.PingResource;
import com.aj.dropwizardkafkaproducer.service.KafkaProducerService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DropwizardKafkaProducerApplication extends Application<DropwizardKafkaProducerConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardKafkaProducerApplication.class);

	public static void main(String[] args) throws Exception {
		new DropwizardKafkaProducerApplication().run("server", args[0]);
	}

    @Override
    public void initialize(Bootstrap<DropwizardKafkaProducerConfiguration> b) {
    }

	@Override
	public void run(DropwizardKafkaProducerConfiguration config, Environment env)
			throws Exception {
	    KafkaProducerService kafkaProducerService = new KafkaProducerService();
	    logger.info("Registering RESTful API resources");
		env.jersey().register(new PingResource());
        env.jersey().register(new KafkaProducerResource());
		env.healthChecks().register("DropwizardKafkaProducerHealthCheck",
				new DropwizardKafkaProducerHealthCheckResource(config));
	}
}
