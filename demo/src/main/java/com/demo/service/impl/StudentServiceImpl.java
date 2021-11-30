package com.demo.service.impl;


import com.demo.dao.StudentDao;
import com.demo.model.StudentModel;
import com.demo.service.IStdentService;
import com.demo.util.SpringBeansUtilDemo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Author: 罗帅
 * @Date: 2020/9/21
 */
@Slf4j
@Service
public class StudentServiceImpl implements IStdentService {


    @Autowired
    private StudentDao studentDao;


    @Override
    public int addStudent(StudentModel student) {
        log.info("addStudent入参："+student.toString());
        int i=0;
        try {
             i=SpringBeansUtilDemo.getBean(this.getClass()).transactionalTest();
        }catch (Exception ee){
            log.error("addStudent执行出现异常:",ee);
            return 2;
        }
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    public int transactionalTest(){
        try {
            StudentModel studentModel = new StudentModel();
            studentModel.setName("插入1");
            int count1 = studentDao.insert(studentModel);
            if (count1 == 1) {
                throw new RuntimeException();
            }
        }catch (Exception ee){
            log.error("transactionalTest执行出现异常:",ee);
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 2;
        }
        return 1;
    }
}
