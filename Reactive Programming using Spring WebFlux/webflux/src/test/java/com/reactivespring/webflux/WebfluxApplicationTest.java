package com.reactivespring.webflux;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebfluxApplicationTest {

    WebfluxApplication sampleObj = new WebfluxApplication();
    @Test
    void namesFlux() {
//        var namesFlux = sampleObj.namesFlux();

        StepVerifier.create(sampleObj.namesFlux())
                .expectNext("Ram","Krishna")
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap(){

        StepVerifier.create(sampleObj.namesFlux_flatmap(3))
                .expectNext("K")
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void nameMono_flatmap() {

        StepVerifier.create(sampleObj.nameMono_flatmap())
                .expectNext(List.of("S","I","N","G","L","E"))
                .verifyComplete();
    }
}