package com.reactivespring.studentspaymentservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Value("${rabbitmq.queue.paymentsQueue}")
    private String PAYMENTS_QUEUE;

    @Value("${rabbitmq.queue.payemtsResponseQueue}")
    private String RESPONSE_QUEUE;

    @Value("${rabbitmq.exchange.studentServiceExchange}")
    private String STUDENTS_SERVICE_EXCHANGE;


    @Value("${rabbitmq.routingKeys.paymentsRoutingKey}")
    private String PAYMENTS_REQUEST_ROUTING_KEY;

    @Value("${rabbitmq.routingKeys.paymentResponseRoutingKey}")
    private String RESPONSE_REQUEST_ROUTING_KEY;


    @Bean
    public Queue paymentsQueue(){
        return new Queue(PAYMENTS_QUEUE);
    }

    @Bean
    public Queue responseQueue(){
        return new Queue(RESPONSE_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(STUDENTS_SERVICE_EXCHANGE);
    }


    @Bean
    public Binding bindPaymentsServiceQueue(Queue paymentsQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(paymentsQueue)
                .to(exchange)
                .with(PAYMENTS_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Binding bindResponseQueue(Queue responseQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(responseQueue)
                .to(exchange)
                .with(RESPONSE_REQUEST_ROUTING_KEY);
    }



}
