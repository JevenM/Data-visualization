package com.zjp.echartsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.zjp.echartsdemo.dao")
public class EchartsdemoApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(EchartsdemoApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EchartsdemoApplication.class);
    }
}
