package com.aj.dropwizardkafkaproducer.resource;

import com.aj.dropwizardkafkaproducer.domain.KafkaProducerRequest;
import com.aj.dropwizardkafkaproducer.service.KafkaProducerService;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/kafka")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "kafka", description = "Kafka Resource for sending messages to Kafka Producer")
public class KafkaProducerResource {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerResource.class);

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessageToTopic(KafkaProducerRequest kafkaProducerRequest) throws Exception {
        logger.info("Request received is: " + kafkaProducerRequest );
        Map<String, String> response = new HashMap<>();
        KafkaProducerService kafkaProducerService = new KafkaProducerService();
        String status = kafkaProducerService.sendMessageToTopic(kafkaProducerRequest.getTopic(),kafkaProducerRequest.getMessage());
        if("success".equalsIgnoreCase(status)) {
            response.put("Status is: " + status, "Message has been sent to Topic: " + kafkaProducerRequest.getTopic());
        }
        else{
            response.put("Status is: " + status, "Error in sending Message to Topic: " + kafkaProducerRequest.getTopic());
        }
        return Response.ok(response).build();
    }
}
