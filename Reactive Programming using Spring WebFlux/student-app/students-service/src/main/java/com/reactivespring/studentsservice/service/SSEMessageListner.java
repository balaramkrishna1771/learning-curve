package com.reactivespring.studentsservice.service;

import com.reactivespring.studentsservice.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class SSEMessageListner {

    private final Sinks.Many<StudentDTO> sink = Sinks.many().multicast().onBackpressureBuffer();

    public Flux<StudentDTO> subscribeMessages(){
        return sink.asFlux();
    }

    public void emitMessage(StudentDTO message){
        sink.tryEmitNext(message);
    }
    @RabbitListener(queues = "${rabbitmq.queue.studentsServiceQueue}")
    public void receiveStudentData(StudentDTO studentDTO) {
        emitMessage(studentDTO);
        log.info("Received and emitted student data: {}", studentDTO);
    }

}
