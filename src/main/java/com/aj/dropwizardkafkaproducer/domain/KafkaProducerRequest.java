package com.aj.dropwizardkafkaproducer.domain;

public class KafkaProducerRequest {

    private String topic;
    private String message;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "KafkaProducerRequest{" +
                "topic='" + topic + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
