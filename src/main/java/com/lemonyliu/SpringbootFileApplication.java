package com.lemonyliu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lemonyliu.mapper")
public class SpringbootFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFileApplication.class, args);
    }

}
