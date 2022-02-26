package com.gotkx.model;

import lombok.Data;

import java.util.Date;

@Data
public class SystemBank {

//      `bank_type` tinyint(4) NOT NULL COMMENT '银行类型, 1 总行 2 分行',
//            `bank_name` varchar(128) NOT NULL COMMENT '银行名称',
//            `bank_no` varchar(3) NOT NULL COMMENT '银行编码',
//            `bank_letter_no` varchar(32) NOT NULL COMMENT '银行英文编码',
//            `bank_code` varchar(16) DEFAULT NULL COMMENT '银行代码',
//            `bank_tel` varchar(32) DEFAULT NULL COMMENT '银行电话',
//            `bank_letter_code` varchar(32) DEFAULT NULL COMMENT '银行英文缩写',
//            `fy_bank_code` varchar(16) DEFAULT NULL COMMENT '富友银行代码',
//            `remark` varchar(256) DEFAULT NULL COMMENT '备注',
//            `physics_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '物理标识, true(1) 正常 false(0) 删除',
//            `create_user` bigint(11) NOT NULL COMMENT '创建用户',
//            `create_user_name` varchar(32) DEFAULT NULL COMMENT '创建用户名称',
//            `create_time` datetime NOT NULL COMMENT '创建时间',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
//            `third_bank_code` varchar(64) DEFAULT NULL COMMENT '第三方编码（名称）',
//            `hs_bank_id` varchar(64) DEFAULT NULL COMMENT '汇商银行ID',
//            `third_type` varchar(64) DEFAULT NULL COMMENT '第三方类型：HS(汇商)，direct（微信直联）',

    /**
     * 银行id
     */
    private long bankId;
    /**
     * 银行类型, 1 总行 2 分行
     */
    private int bankType;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行编码
     */
    private String bankNo;
    /**
     * 银行英文编码
     */
    private String bankLetterNo;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 银行电话
     */
    private String bankTel;
    /**
     * 银行英文缩写
     */
    private String bankLetterCode;
    /**
     * 富友银行代码
     */
    private String fyBankCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 物理标识, true(1) 正常 false(0) 删除
     */
    private int physicsFlag;
    /**
     * 创建用户
     */
    private long createUser;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 最后一次更新时间
     */
    private Date createTime;
    private Date updateTime;
    /**
     * 第三方编码（名称）
     */
    private String thirdBankCode;
    /**
     * 汇商银行ID
     */
    private String hsBankId;
    /**
     * 第三方类型：HS(汇商)，direct（微信直联）
     */
    private String thirdType;

}
