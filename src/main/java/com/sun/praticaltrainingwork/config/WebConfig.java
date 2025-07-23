package com.sun.praticaltrainingwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//在那些路径添加
                .allowedOriginPatterns("*")
                //.allowedOrigins("*")//允许哪些域访问
                .allowCredentials(true)//是否发送Cookie
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")//允许哪些方法
                .allowedHeaders("*")//允许哪些头信息
                .exposedHeaders("*")
                .maxAge(3600);//设置多少秒内浏览器无需再次询问
    }
}
