package com.css.springboot.backend.apirest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringBootBackendApirestLastApplication implements CommandLineRunner {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBackendApirestLastApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = "123456";
        System.out.println(passwordEncoder.encode(password));
    }
}
