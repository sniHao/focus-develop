package com.focus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot Multi-Model 启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.focus"})
@MapperScan("com.focus.model.mapper")
public class FocusApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocusApplication.class, args);
    }
}
