package com.javatechie.cloud_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController

public class FallbackController {

    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallBack(){
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
    }
    @RequestMapping("/paymentFallback")
    public Mono<String> paymentServiceFallBack(){
        return Mono.just("Payment Service is taking too long to respond or is down. Please try again later.");
    }
}
