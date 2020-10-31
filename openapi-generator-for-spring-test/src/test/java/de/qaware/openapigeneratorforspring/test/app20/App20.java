package de.qaware.openapigeneratorforspring.test.app20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class App20 {
    public static void main(String[] args) {
        SpringApplication.run(App20.class, args);
    }
}