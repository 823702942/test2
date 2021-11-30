package com.demo;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author: 罗帅
 * @Date: 2020/9/21
 */
@Slf4j
@MapperScan("com.demo.dao")
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info("-----demo启动成功-----");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(DemoApplication.class);

    }

}
