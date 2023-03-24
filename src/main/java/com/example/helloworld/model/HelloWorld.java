package com.example.helloworld.model;

public class HelloWorld {

    private String value;

    public HelloWorld() {
        value = "World";
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Hello " + value + " !";
    }
}
