package com.example.timekeepv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TimeKeepV1Application {

    public static void main(String[] args) {
        SpringApplication.run(TimeKeepV1Application.class, args);
    }

}
