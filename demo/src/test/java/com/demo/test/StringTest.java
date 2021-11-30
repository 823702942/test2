package com.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: 罗帅
 * @Date: 2021/1/20
 */
@Slf4j
public class StringTest {

    @Test
    public void subStringTest(){
        String processDefId="11111:22222:33333";
        String processId=processDefId.substring(0,processDefId.indexOf(":"));
        log.info(processId);
    }
}
