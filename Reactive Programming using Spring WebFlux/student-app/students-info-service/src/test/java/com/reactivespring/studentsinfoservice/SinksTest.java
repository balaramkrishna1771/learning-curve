package com.reactivespring.studentsinfoservice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.many;

public class SinksTest {

    @Test
    void sinks_replay(){
        Sinks.Many<Integer> replayFlux = Sinks.many().replay().all();

        replayFlux.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        replayFlux.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = replayFlux.asFlux();

        integerFlux.subscribe(i -> {
            System.out.println("Subscriber 1 - "+i);
        });
        replayFlux.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux1 = replayFlux.asFlux();

        integerFlux1.subscribe(i -> {
            System.out.println("Subscriber 2 - "+i);
        });
    }

    @Test
    void sinks_multicast(){
        Sinks.Many<Integer> multicast = Sinks.many().multicast().onBackpressureBuffer();

        multicast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = multicast.asFlux();

        integerFlux.subscribe(i -> {
            System.out.println("Subscriber 1 - "+i);
        });


        Flux<Integer> integerFlux1 = multicast.asFlux();

        integerFlux1.subscribe(i -> {
            System.out.println("Subscriber 2 - "+i);
        });
        multicast.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
