package com.zjp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ��ҳ���йصľ�̬Ŀ¼��������Ŀ��staticĿ¼��
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // �ϴ���ͼƬ��D���µ�OTAĿ¼�£�����·���磺http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        // ����OTA��ʾ���ʵ�ǰ׺��"file:D:/OTA/"���ļ���ʵ�Ĵ洢·��
        registry.addResourceHandler("/suda/static/**").addResourceLocations("classpath:/static/");
    }
}