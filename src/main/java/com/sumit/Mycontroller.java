package com.sumit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class Mycontroller {

    @GetMapping("/demo")
    public Mono<String> greetingMessage(){
      return computeMsg()
                .zipWith(getNamefromDB())
                .map(value->{
                    return value.getT1()+value.getT2();
                });
    }

    private Mono<String> computeMsg(){
        return Mono.just("Hello").delayElement(Duration.ofSeconds(5));
    }

    private Mono<String> getNamefromDB(){
        return Mono.just(" sumit").delayElement(Duration.ofSeconds(5));
    }
}
