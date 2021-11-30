package com.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: 罗帅
 * @Date: 2020/9/26
 */
@Component("SpringBeansUtilDemo")
public class SpringBeansUtilDemo implements ApplicationContextAware {

    private static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeansUtilDemo.applicationContext=applicationContext;
    }

    public static<T> T getBean(Class<T> tClass){
        return (T)applicationContext.getBean(tClass);
    }
}
