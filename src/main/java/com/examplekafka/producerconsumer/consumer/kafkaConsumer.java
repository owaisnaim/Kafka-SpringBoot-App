package com.examplekafka.producerconsumer.consumer;
import com.examplekafka.producerconsumer.constant.ApplicationConstant;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class kafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(groupId = ApplicationConstant.GROUP_ID_STRING, topics = ApplicationConstant.TOPIC_NAME,
            containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(String message) {
        logger.info("Message Received using Kafka listener : " + message);
    }
}
