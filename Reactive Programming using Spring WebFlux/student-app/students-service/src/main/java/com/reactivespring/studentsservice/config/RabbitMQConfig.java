package com.reactivespring.studentsservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.studentsInfoQueue}")
    private String STUDENTS_INFO_QUEUE;

    @Value("${rabbitmq.queue.paymentsQueue}")
    private String PAYMENTS_QUEUE;

    @Value("${rabbitmq.queue.infoResponseQueue}")
    private String INFO_RESPONSE_QUEUE;

    @Value("${rabbitmq.queue.payemtsResponseQueue}")
    private String PAYMENTS_RESPONSE_QUEUE;

    @Value("${rabbitmq.exchange.studentServiceExchange}")
    private String STUDENTS_SERVICE_EXCHANGE;

    @Value("${rabbitmq.routingKeys.studentsInfoRoutingKey}")
    private String STUDENTS_INFO_REQUEST_ROUTING_KEY;

    @Value("${rabbitmq.routingKeys.paymentsRoutingKey}")
    private String PAYMENTS_REQUEST_ROUTING_KEY;

    @Value("${rabbitmq.routingKeys.infoResponseRoutingKey}")
    private String INFO_RESPONSE_REQUEST_ROUTING_KEY;

    @Value("${rabbitmq.routingKeys.paymentResponseRoutingKey}")
    private String PAYMENT_RESPONSE_REQUEST_ROUTING_KEY;

    @Bean
    public Queue studentsInfoQueue(){
        return new Queue(STUDENTS_INFO_QUEUE);
    }

    @Bean
    public Queue paymentsQueue(){
        return new Queue(PAYMENTS_QUEUE);
    }

    @Bean
    public Queue infoResponseQueue(){
        return new Queue(INFO_RESPONSE_QUEUE);
    }

    @Bean
    public Queue paymentsResponseQueue(){
        return new Queue(PAYMENTS_RESPONSE_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(STUDENTS_SERVICE_EXCHANGE);
    }

    @Bean
    public Binding bindStudentsInfoServiceQueue(Queue studentsInfoQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(studentsInfoQueue)
                .to(exchange)
                .with(STUDENTS_INFO_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindPaymentsServiceQueue(Queue paymentsQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(paymentsQueue)
                .to(exchange)
                .with(PAYMENTS_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindInfoResponseQueue(Queue infoResponseQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(infoResponseQueue)
                .to(exchange)
                .with(INFO_RESPONSE_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindPaymentsResponseQueue(Queue paymentsQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(paymentsQueue)
                .to(exchange)
                .with(PAYMENTS_REQUEST_ROUTING_KEY);
    }



}
