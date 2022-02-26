package com.gotkx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemIndustryHs {

//  `industry_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '行业id',
//            `industry_name` varchar(128) NOT NULL COMMENT '行业名称',
//            `parent_industry` bigint(11) NOT NULL DEFAULT '0' COMMENT '所属行业',
//            `merchant_class` tinyint(4) NOT NULL COMMENT '商户类型, 企业商户-1,个体工商户-2,个体经营者-3,事业单位-4,其他组织-99',
//            `sp_industry_id` bigint(11) DEFAULT NULL COMMENT '威富通行业id',
//            `mcc_id` varchar(8) DEFAULT NULL COMMENT '银行商户类别码',
//            `fy_industy_id` varchar(8) DEFAULT NULL COMMENT '富友行业类别码',
//            `fy_mcc` varchar(4) DEFAULT NULL COMMENT '富友收单商户类别码',
//            `remark` varchar(256) DEFAULT NULL COMMENT '备注',
//            `physics_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '物理标识, true(1) 正常 false(0) 删除',
//            `third_industry_code` varchar(32) DEFAULT NULL COMMENT '第三方编码（名称）',
//            `hs_industry_code` varchar(32) DEFAULT NULL COMMENT '汇商编码',
//            `industry_type` varchar(32) DEFAULT NULL COMMENT '第三方类型：HS(汇商)，direct(微信直联)，BCM(交通银行)，ALI(支付宝直连)',

    /** 行业id */
    private long industryId;

    private String industryName;

    //  所属行业
    private long parentIndustry;

    //  商户类型, 企业商户-1,个体工商户-2,个体经营者-3,事业单位-4,其他组织-99
    private int merchantClass;

    //  威富通行业id
    private long spIndustryId;

    // 银行商户类别码
    private String mccId;

    //  富友行业类别码
    private String fyIndustyId;

    //  富友收单商户类别码
    private String fyMcc;

    //  备注
    private String remark;

    //  物理标识, true(1) 正常 false(0) 删除
    private boolean physicsFlag;

    private String thirdIndustryCode;

    private String hsIndustryCode;

    private String industryType;


}
