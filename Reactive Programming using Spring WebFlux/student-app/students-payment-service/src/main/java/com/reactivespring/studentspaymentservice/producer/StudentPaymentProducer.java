package com.reactivespring.studentspaymentservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactivespring.studentspaymentservice.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class StudentPaymentProducer {

    @Value("${spring.kafka.topic}")
    public String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public StudentPaymentProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public Mono<SendResult<String, String>> sendStudentPaymentEvent(PaymentDTO paymentDTO) {
        try {
            String key = String.valueOf(paymentDTO.getPaymentId());
            String value = objectMapper.writeValueAsString(paymentDTO);

            return Mono.fromFuture(kafkaTemplate.sendDefault(key, value).toCompletableFuture())
                    .doOnSuccess(result -> handleSuccess(key, value, result))
                    .doOnError(error -> handleFailure(key, value, error));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }

    private void handleFailure(String key, String value, Throwable ex) {
        log.error("Error Sending the Message. Key: {}, Value: {}, Exception: {}", key, value, ex.getMessage());
    }

    private void handleSuccess(String key, String value, SendResult<String, String> result) {
        log.info("Message Sent Successfully. Key: {}, Value: {}, Partition: {}", key, value, result.getRecordMetadata().partition());
    }
}
