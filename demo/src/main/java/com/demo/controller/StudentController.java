package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.dao.StudentDao;
import com.demo.model.StudentModel;
import com.demo.service.IStdentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 罗帅
 * @Date: 2020/9/21
 */
@Slf4j
@RestController
public class StudentController {

    @Autowired
    private IStdentService iStdentService;
    @Autowired
    private StudentDao studentDao;

    /**
     * 测试form-data接口
     */
    @PostMapping("/form")
    public String testFromData(@RequestParam("param1")String param1,String param2){
        return "param1:"+param1+",param2:"+param2;
    }

    /**
     * 测试多数据源查询
     * @return
     */
    @GetMapping("/ds")
    public void dsTest(){
        QueryWrapper<StudentModel> wrapper=new QueryWrapper();
       log.info("执行结果:"+studentDao.selectList(wrapper).toString());
    }

    @PostMapping("/insert")
    public int studentInsert(@RequestBody StudentModel studentModel) {

        return iStdentService.addStudent(studentModel);
    }

    @GetMapping("/test/{appId}")
    public String testLog(@PathVariable String appId) {
        log.info("访问testLog接口,入参appId->{}", appId);
        return "访问接口成功";
    }

}
