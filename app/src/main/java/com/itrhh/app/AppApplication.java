package com.itrhh.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = {"com.itrhh.module","com.itrhh.app.domain.vo","com.itrhh.app.controller"})
@MapperScan("com.itrhh.module.mapper")
public class AppApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppApplication.class, args);

    }


}