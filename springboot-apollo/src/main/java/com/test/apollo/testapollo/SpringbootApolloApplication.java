package com.test.apollo.testapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class SpringbootApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApolloApplication.class, args);
    }

}
