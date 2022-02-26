package com.gotkx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SystemArea {
//    CREATE TABLE `system_area` (
//            `area_code` varchar(16) NOT NULL COMMENT '地区编码',
//            `area_name` varchar(64) NOT NULL COMMENT '地区名称',
//            `area_type` tinyint(4) NOT NULL COMMENT '地区类型, 1国家，2省份，3城市，4县/区',
//            `parent_area` varchar(16) DEFAULT NULL COMMENT '所属地区',
//            `zip_code` varchar(16) DEFAULT NULL COMMENT '邮政编码',
//            `tel_code` varchar(4) DEFAULT NULL COMMENT '电话区号, 生成渠道编号时要用到',
//            `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用, true(1) 正常 false(0) 冻结',
//            `name_py` varchar(64) DEFAULT NULL COMMENT '地区名称全拼',
//            `name_spy` varchar(64) DEFAULT NULL COMMENT '地区名称拼音缩写',
//            `remark` varchar(256) DEFAULT NULL COMMENT '备注',
//            `physics_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '物理标识, true(1) 正常 false(0) 删除',
//            `create_user` bigint(11) NOT NULL COMMENT '创建用户',
//            `create_user_name` varchar(32) DEFAULT NULL COMMENT '创建用户名称',
//            `create_time` datetime NOT NULL COMMENT '创建时间',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
//    PRIMARY KEY (`area_code`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区表'

    private String areaCode;

    private String areaName;

    private String areaType;

    private String parentArea;

    private String zipCode;

    private String telCode;

    private String enabled;

    private String namePy;

    private String nameSpy;

    private String remark;

    private String physicsFlag;

    private String createUser;

    private String createUserName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
