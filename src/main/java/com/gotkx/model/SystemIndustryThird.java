package com.gotkx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemIndustryThird {

//      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
//            `industry_id` bigint(11) NOT NULL COMMENT '行业id，根据行业类型来确定是谁的行业id',
//            `industry_type` varchar(32) NOT NULL COMMENT '行业类型：HS(汇商)，direct（微信直联），WFT（威富通），yf（友富），BCM（交通银行），RYX（瑞银信）',
//            `industry_name` varchar(128) NOT NULL COMMENT '行业名称',
//            `parent_industry` bigint(11) NOT NULL DEFAULT '0' COMMENT '所属行业',
//            `merchant_class` tinyint(4) NOT NULL COMMENT '商户类型, 企业商户-1,个体工商户-2,个体经营者-3,事业单位-4',
//            `third_mcc_id` varchar(10) DEFAULT NULL COMMENT '第三方银联MCC_ID',
//            `hs_industry_code` varchar(32) DEFAULT NULL COMMENT '汇商编码',
//            `remark` varchar(256) DEFAULT NULL COMMENT '备注',
//            `physics_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '物理标识, true(1) 正常 false(0) 删除',
//            `create_user` bigint(11) NOT NULL COMMENT '创建用户',
//            `create_user_name` varchar(32) DEFAULT NULL COMMENT '创建用户名称',
//            `update_user` bigint(11) NOT NULL COMMENT '更新用户',
//            `update_user_name` varchar(32) DEFAULT NULL COMMENT '更新用户名称',
//            `create_time` datetime NOT NULL COMMENT '创建时间',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',

    /** 主键 */
    private long id;

    /** 行业id，根据行业类型来确定是谁的行业id */
    private Long industryId;

    /**
     * 行业类型：HS(汇商)，direct（微信直联），WFT（威富通），yf（友富），BCM（交通银行），RYX（瑞银信）
     */
    private String industryType;

    /**
     * 行业名称
     */
    private String industryName;

    /**
     * 0' COMMENT '所属行业
     */
    private long parentIndustry;

    /**
     * 商户类型, 企业商户-1,个体工商户-2,个体经营者-3,事业单位-4
     */
    private int merchantClass;

    /**
     * 第三方银联MCC_ID
     */
    private String thirdMccId;

    /**
     * 汇商编码
     */
    private String hsIndustryCode;

    private String remark;

    private boolean physicsFlag;

    private long createUser;

    /**
     * 创建用户名称
     */
    private String createUserName;

    /**
     * 更新用户id
     */
    private long updateUser;
    /**
     * 更新用户名称
     */
    private String updateUserName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
