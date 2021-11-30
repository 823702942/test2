package com.ls.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: 罗帅
 * @date: 2021-05-25
 */
@Slf4j
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        log.info("DemoApplication启动成功----------------");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

    // 添加跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "POST")
                .maxAge(3600);
    }
}
