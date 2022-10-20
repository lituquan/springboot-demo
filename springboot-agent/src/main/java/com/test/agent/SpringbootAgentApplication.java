package com.test.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAgentApplication.class, args);
	}

}
