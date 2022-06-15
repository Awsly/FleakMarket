package com.ct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.ct.mapper")
@EnableConfigurationProperties
@EnableAsync
@EnableScheduling
public class FleamarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleamarketApplication.class, args);
    }

}