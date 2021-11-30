package com.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 罗帅
 * @Date: 2020/12/10
 */
@Slf4j
public class DateTest {
    @Test
    public void testEnumPrint() {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        log.info(sdf.format(date));
        log.info(sdf2.format(date));
    }
}
