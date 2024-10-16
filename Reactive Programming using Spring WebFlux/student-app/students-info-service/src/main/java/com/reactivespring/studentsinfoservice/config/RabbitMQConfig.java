package com.reactivespring.studentsinfoservice.config;

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


    @Value("${rabbitmq.queue.infoResponseQueue}")
    private String RESPONSE_QUEUE;

    @Value("${rabbitmq.exchange.studentServiceExchange}")
    private String STUDENTS_SERVICE_EXCHANGE;

    @Value("${rabbitmq.routingKeys.studentsInfoRoutingKey}")
    private String STUDENTS_INFO_REQUEST_ROUTING_KEY;


    @Value("${rabbitmq.routingKeys.infoResponseRoutingKey}")
    private String INFO_RESPONSE_REQUEST_ROUTING_KEY;

    @Bean
    public Queue studentsInfoQueue(){
        return new Queue(STUDENTS_INFO_QUEUE);
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
    public Binding bindStudentsInfoServiceQueue(Queue studentsInfoQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(studentsInfoQueue)
                .to(exchange)
                .with(STUDENTS_INFO_REQUEST_ROUTING_KEY);
    }


    @Bean
    public Binding bindResponseQueue(Queue responseQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(responseQueue)
                .to(exchange)
                .with(INFO_RESPONSE_REQUEST_ROUTING_KEY);
    }



}
