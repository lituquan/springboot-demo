package com.test.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAgentApplication.class, args);
	}

}
