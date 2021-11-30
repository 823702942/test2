package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Student模型
 *
 * @Author: 罗帅
 * @Date: 2020/9/21
 */
@TableName("student")
@Data
public class StudentModel {

    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @TableField(value = "name", exist = true)
    private String name;

    @TableField(value = "sex",exist = true)
    private String sex;
}
