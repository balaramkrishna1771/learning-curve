package com.reactivespring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public class WebfluxApplication {


    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("Ram","Krishna", "Ben"))
                .log();
    }

    public Mono<String> nameMono(){
        return Mono.just("Single")
                .log();
    }

    public Mono<List<String>> nameMono_flatmap(){
        return Mono.just("Single")
                .map(String::toUpperCase)
                .flatMap(this::splitString_mono)
                .log();
    }

    private Mono<List<String>> splitString_mono(String s) {
        var charList = List.of(s.split(""));
        return Mono.just(charList);
    }

    public Flux<String> namesFlux_flatmap(int len){
        return Flux.fromIterable((List.of("Ram","Krishna", "Ben")))
                .map(String::toUpperCase)
                .filter(eachString -> eachString.length() > len)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> splitString(String name){
        return Flux.fromArray(name.split(""));
    }


    public static void main(String[] args) {
        WebfluxApplication app = new WebfluxApplication();
        app.namesFlux().subscribe(System.out::println);
        app.nameMono()
                .subscribe(System.out::println);

    }

}
