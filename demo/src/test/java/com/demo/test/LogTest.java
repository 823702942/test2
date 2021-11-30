package com.demo.test;

import com.demo.model.StudentModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: 罗帅
 * @Date: 2021/1/13
 */
@Slf4j
public class LogTest {
    @Test
    public void testLogPrint(){
        StudentModel studentModel=new StudentModel();
        studentModel.setName("测试日志输出");
        //纯文字输出
        log.info("测试日志纯文字输出1:{}","逗号后部分");
        log.info("测试日志纯文字输出2："+"逗号后部分");
        //实体类输出
        log.info("测试日志实体类输出1 {}",studentModel);
        log.error("测试日志实体类输出error：",studentModel.toString());
        log.info("测试日志实体类输出2："+studentModel);
        log.info("测试日志实体类输出2："+studentModel.toString());
    }
}
