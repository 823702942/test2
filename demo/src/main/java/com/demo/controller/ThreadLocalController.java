package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.downgoon.snowflake.Snowflake;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 罗帅
 * @Date: 2021/1/14
 */
@Slf4j
@RestController
public class ThreadLocalController {
    static final ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<Map<String,String>>();
    Snowflake snowflake=new Snowflake(0,0);
    Map<String,String> map=new HashMap<>();
    @GetMapping("/local/{id}")
    public String getId(@PathVariable String id){
        log.info("入参id:{}",id);
        String value=snowflake.nextId()+"";
        map.put("id",value);
        threadLocal.set(map);
        return  threadLocal.get().get("id");
    }
}
