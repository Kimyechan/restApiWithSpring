package me.yechan.restapiwithspring;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class RestApiWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiWithSpringApplication.class, args);
    }
}
