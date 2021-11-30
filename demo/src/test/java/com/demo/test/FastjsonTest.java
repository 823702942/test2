package com.demo.test;

import com.alibaba.fastjson.JSON;
import com.demo.model.SmallStudentModel;
import com.demo.model.StudentModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 罗帅
 * @Date: 2021/1/16
 */
@Slf4j
public class FastjsonTest {

    /**
     * javabean转map
     */
    @Test
    public void test1(){
        StudentModel studentModel=new  StudentModel();
        studentModel.setId("1");
        studentModel.setName("test");
        List<StudentModel> list=new ArrayList<>();
        list.add(studentModel);
        String jsonStr=JSON.toJSONString(list);

        List<SmallStudentModel> smallStudentModelList=JSON.parseArray(jsonStr,SmallStudentModel.class);
        log.info("123");
    }
}
