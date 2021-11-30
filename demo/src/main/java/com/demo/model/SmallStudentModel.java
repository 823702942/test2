package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: 罗帅
 * @Date: 2021/1/16
 */
@Data
public class SmallStudentModel {
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

}
