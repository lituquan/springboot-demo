package com.test.apollo.testapollo.config;

import com.test.apollo.testapollo.model.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "user")
public class AppConfig {
    private String name;
    private Integer id;
    private List<User> list;
}
