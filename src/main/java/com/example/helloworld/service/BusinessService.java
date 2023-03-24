package com.example.helloworld.service;

import com.example.helloworld.model.HelloWorld;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {

    public BusinessService() {}

    public HelloWorld getHelloWorld() {
        return new HelloWorld();
    }
}
