package com.daimao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author daimao
 * @date 2022/8/2 0:34
 */
@SpringBootApplication(scanBasePackages= {"com.daimao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
