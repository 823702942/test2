package com.demo.test;

import com.demo.dao.StudentDao;
import com.demo.model.StudentModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 罗帅
 * @Date: 2021/1/21
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class insertTet {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void test1(){
        StudentModel s= new StudentModel();
        studentDao.insert(s);
     log.info(s.getId());
    }
}
