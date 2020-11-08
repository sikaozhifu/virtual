package com.school.virtual;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.school.virtual.mapper")
public class VirtualApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualApplication.class, args);
    }

}
