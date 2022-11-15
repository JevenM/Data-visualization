package com.zjp.echartsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjp.echartsdemo.dao")
public class EchartsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchartsdemoApplication.class, args);
    }

}
