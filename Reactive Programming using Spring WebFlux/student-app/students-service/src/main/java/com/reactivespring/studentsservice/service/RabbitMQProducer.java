package com.reactivespring.studentsservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.studentServiceExchange}")
    private String STUDENTS_SERVICE_EXCHANGE;

    @Value("${rabbitmq.routingKeys.studentsInfoRoutingKey}")
    private String STUDENTS_INFO_REQUEST_ROUTING_KEY;

    @Value("${rabbitmq.routingKeys.paymentsRoutingKey}")
    private String PAYMENTS_REQUEST_ROUTING_KEY;


    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void requestStudentInfo(Integer studentId){
        rabbitTemplate.convertAndSend(STUDENTS_SERVICE_EXCHANGE, STUDENTS_INFO_REQUEST_ROUTING_KEY, studentId);
    }

    public void requestPayments(Integer studentId){
        rabbitTemplate.convertAndSend(STUDENTS_SERVICE_EXCHANGE, PAYMENTS_REQUEST_ROUTING_KEY, studentId);
    }
}
