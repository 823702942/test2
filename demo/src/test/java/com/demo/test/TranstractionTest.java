package com.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.dao.ContractDao;
import com.demo.dao.StudentDao;
import com.demo.model.ContractModel;
import com.demo.model.StudentModel;
import com.demo.service.IStdentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 罗帅
 * @Date: 2020/9/22
 * <p>
 * 事务测试
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TranstractionTest {

    @Autowired
    private IStdentService iStdentService;
    @Autowired
    private StudentDao studentDao;

    @Test
    public void studentTest(){
        StudentModel studentModel=new StudentModel();
        studentModel.setId("78797c701b85c5c470a5bd39f8f3605c");
        studentModel.setName("测试修改");
        QueryWrapper<StudentModel> wrapper=new QueryWrapper<>();
        wrapper.eq("id",studentModel.getId())
                .and(qw->qw.eq("sex","男").or().isNull("sex"));
        studentDao.update(studentModel,wrapper);
    }
    /**
     * 通过类名、参数类型、参数值反射调用类方法
     */
    public <T> T reflectionCall(String className, String methodName, Class paramClass, T param) {
        try {
            Class clazz = Class.forName(className);
            Method payMethod = clazz.getMethod(methodName, paramClass);
            return (T) payMethod.invoke(clazz.newInstance(), param);
        } catch (Exception ee) {
            log.error("反射调用出现异常:", ee);
        }
        return null;
    }

    /**
     * 通过对象 属性名 获取属性值
     */
    public Object getAttributeValueByReflect(Object obj, String attributeName) {
        try {
            Class clazz = obj.getClass();
            //获得类的声明的字段
            Field name = clazz.getDeclaredField(attributeName);
            //可访问私有变量
            name.setAccessible(true);
            return name.get(obj);
        } catch (Exception ee) {
            log.error("反射获取属性值出现异常:", ee);
        }
        return null;
    }

    /**
     * 通过对象 属性名 给属性赋值
     */
    public Object setAttributeValueByReflect(Object obj, String attributeName, Object value) {
        try {
            Class clazz = obj.getClass();
            //获得类的声明的字段
            Field name = clazz.getDeclaredField(attributeName);
            //可访问私有变量
            name.setAccessible(true);
            name.set(obj, value);
            return obj;
        } catch (Exception ee) {
            log.error("反射给属性赋值出现异常:", ee);
        }
        return null;
    }

    @Test
    public void testReflect() {

        try {
            StudentModel studentModel = new StudentModel();
            studentModel.setName("123--------");
            String name = (String) getAttributeValueByReflect(studentModel, "name");
            log.info(name);
            studentModel = (StudentModel) setAttributeValueByReflect(studentModel, "name", new BigDecimal("123"));
            log.info(studentModel.toString());
        } catch (Exception ee) {
            log.error("出错:", ee);
        }
    }

    @Test
    public void test() {
        QueryWrapper<StudentModel> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "插入1");
        List<StudentModel> list = studentDao.selectList(wrapper);
        log.info(list.toString());

    }

    /**
     * 测试事务回滚后是否还会回传返回值
     */
    @Test
    public void test1() {
        StudentModel studentModel = new StudentModel();
        studentModel.setSex("男");
        studentModel.setName("test1");
        int result = iStdentService.addStudent(studentModel);
        System.out.println("执行结果：" + result);
    }

    @Autowired
    private ContractDao contractDao;

    /**
     * 合同表模型不写映射插入数据测试
     */
    @Test
    public void test2() {
Integer a=2;
Integer b=a-1;

log.info((a-1)+"");
log.info(a+"");
    }
}
