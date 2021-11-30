package com.demo.test;

import com.demo.model.StudentModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 罗帅
 * @Date: 2020/12/18
 */
@Slf4j
public class ClassTest {
    @Test
    public void classFieldTest() {
        StudentModel studentModel = new StudentModel();
        studentModel.setName("未调用方法");
        log.info(studentModel.toString());
        assembilyModelField(studentModel);
        log.info(studentModel.toString());
    }
    @Test
    public void StringTest() {
      String str="0123";
        log.info(str);
      str=str.substring(0,str.length()-1);
        log.info(str);
    }

    public void assembilyModelField(StudentModel studentModel) {
        studentModel.setName("调用装配方法咯");
    }

    @Test
    public void strTest() {
        String str = "未调用方法";
        log.info(str);
        assembilyStr(str);
        log.info(str);
    }
    public void assembilyStr(String str) {
        str="调用装配方法咯";
    }
    @Test
    public void mapTest() {
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"未调用方法");
        log.info(map.toString());
        assembilyMap(map);
        log.info(map.toString());
    }
    public void assembilyMap(Map<Integer,String> map) {
        map.put(1,"调用装配方法咯");
    }

    @Test
    public void test21(){
        StudentModel s=new StudentModel();
       System.out.println(s==null);
    }
}
