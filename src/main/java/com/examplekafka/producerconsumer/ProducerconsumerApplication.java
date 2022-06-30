package com.examplekafka.producerconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.examplekafka.*")
public class ProducerconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerconsumerApplication.class, args);
	}

}
