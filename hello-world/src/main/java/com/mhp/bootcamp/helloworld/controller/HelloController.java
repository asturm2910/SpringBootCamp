package com.mhp.bootcamp.helloworld.controller;

import com.mhp.bootcamp.helloworld.aop.PerformanceLogging;
import com.mhp.bootcamp.helloworld.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PerformanceLogging
public class HelloController {

    private HelloService service;

    @Autowired
    public HelloController(HelloService service) {
        this.service = service;
    }


    @GetMapping("/hello")
    public String sayHello() {
        return service.getGreeting();
    }
}


