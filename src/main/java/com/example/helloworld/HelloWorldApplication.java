package com.example.helloworld;

import com.example.helloworld.model.HelloWorld;
import com.example.helloworld.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication implements CommandLineRunner{

	@Autowired
	private final BusinessService bs = new BusinessService();

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		HelloWorld hw = bs.getHelloWorld();
		hw.setValue("Lorenzo");
		System.out.println(hw);
	}
}
