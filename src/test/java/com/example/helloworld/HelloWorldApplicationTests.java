package com.example.helloworld;

import com.example.helloworld.model.HelloWorld;
import com.example.helloworld.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HelloWorldApplicationTests {
	
	@Autowired
	private final BusinessService bs = new BusinessService();

	@Test
	void contextLoads() {
	}


	@Test
	public void testGetHelloWorld() {
		HelloWorld hw = bs.getHelloWorld();
		String result = "Hello World !";
		assertEquals(hw.toString(), result);
		hw.setValue("Lorenzo");
		result = "Hello Lorenzo !";
		assertEquals(hw.toString(), result);
	}
}
