package com.iotresearcher.bridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Main Class of the Spirago MQTT Kafka Bridge Spring Boot App
 *
 */
@EnableAutoConfiguration
@Component
@ComponentScan
public class Main {

    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
    }

}
