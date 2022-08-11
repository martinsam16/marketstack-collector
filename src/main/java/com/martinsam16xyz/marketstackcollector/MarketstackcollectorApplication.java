package com.martinsam16xyz.marketstackcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
@EnableReactiveMongoRepositories
@EnableScheduling
public class MarketstackcollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketstackcollectorApplication.class, args);
    }

}
