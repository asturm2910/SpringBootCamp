package com.mhp.bootcamp.helloworld.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class HelloService {

    @Value("${greeting.message:NOT AVAILABLE}")
    private String greeting;


    @PostConstruct
    public void afterFinished() {
        System.out.println("Finished with greeting: " + greeting);
    }

    @PreDestroy
    public void rightBeforeDestruction() {
        System.out.println("HelloService will be destroyed shortly");
    }


    public String getGreeting() {
        return greeting + "\n";
    }
}
