package com.otakuy.otakuymusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class OtakuymusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtakuymusicApplication.class, args);
    }

}

