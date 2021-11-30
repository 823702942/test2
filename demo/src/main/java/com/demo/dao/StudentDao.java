package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.StudentModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 罗帅
 * @Date: 2020/9/21
 */
@Mapper
public interface StudentDao extends BaseMapper<StudentModel> {

}
