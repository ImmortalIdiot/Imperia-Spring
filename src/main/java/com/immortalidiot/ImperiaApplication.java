package com.immortalidiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.immortalidiot.cfg")
@ComponentScan("com.immortalidiot.services")
@ComponentScan("com.immortalidiot.repositories")
public class ImperiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImperiaApplication.class, args);
    }
}
