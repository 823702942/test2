package com.ls.demo.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: 罗帅
 * @date: 2021-08-17
 */
@Data
@TableName("DHCC_ORGSTRUCT_EMP")
public class EmpModel implements Serializable {
    @TableId(value = "PHY_ID")
    private String phyId;
    @TableField(value = "EMP_ID")
    private String empId;
    @TableField(value = "EMP_NAME")
    private String empName;
    @TableField(value = "MAIN_DEPT_ID")
    private String mainDeptId;
    @TableField(value = "MAIN_POST_ID")
    private String mainPostId;
    @TableField(value = "MAIN_ORG_ID")
    private String mainOrgId;
}
