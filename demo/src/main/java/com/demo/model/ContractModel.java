package com.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: 罗帅
 * @Date: 2020/10/25
 */
@Data
@TableName("合同表")
public class ContractModel {
    @TableId
    private String contractNo;

    private String contractName;


}
