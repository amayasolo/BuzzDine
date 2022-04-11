package com.gatech.buzzdine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gatech.buzzdine.storage.mapper")
public class BuzzDineApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuzzDineApplication.class, args);
    }
}