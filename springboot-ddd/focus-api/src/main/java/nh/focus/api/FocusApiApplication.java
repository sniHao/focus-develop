package nh.focus.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nh.focus.api", "nh.focus.common", "nh.focus.application"})
public class FocusApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocusApiApplication.class, args);
    }

}
