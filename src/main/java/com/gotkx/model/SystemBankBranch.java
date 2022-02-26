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
public class SystemBankBranch {

//      `bank_branch_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '支行id',
//            `bank_branch_name` varchar(128) NOT NULL COMMENT '支行名称',
//            `bank_id` bigint(11) NOT NULL COMMENT '银行ID, 对应银行表主键ID',
//            `contact_line` varchar(32) NOT NULL COMMENT '联行号',
//            `province` varchar(32) NOT NULL COMMENT '省份, 对应地区表AREA_CODE',
//            `city` varchar(32) NOT NULL COMMENT '城市, 对应地区表AREA_CODE',
//            `standby_1` varchar(256) DEFAULT NULL COMMENT '备用字段1',
//            `standby_2` varchar(256) DEFAULT NULL COMMENT '备用字段2',
//            `standby_3` varchar(256) DEFAULT NULL COMMENT '备用字段3',
//            `standby_4` varchar(256) DEFAULT NULL COMMENT '备用字段4',
//            `remark` varchar(256) DEFAULT NULL COMMENT '备注',
//            `physics_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '物理标识, true(1) 正常 false(0) 删除',
//            `create_user` bigint(11) NOT NULL COMMENT '创建用户',
//            `create_user_name` varchar(32) DEFAULT NULL COMMENT '创建用户名称',
//            `create_time` datetime NOT NULL COMMENT '创建时间',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
//            `enabled` tinyint(1) DEFAULT '1',
//            `update_user` bigint(11) DEFAULT NULL COMMENT '更新用户',
//            `update_user_name` varchar(32) DEFAULT NULL COMMENT '更新用户名称',

    /**
     * 银行id
     */
    private long bankBranchId;

    /**
     * 支行名称
     */
    private String bankBranchName;

    /**
     * 银行id 对应银行表主键ID
     */
    private long bankId;

    /**
     * 联行号
     */
    private String contactLine;

    /**
     * 省份, 对应地区表AREA_CODE
     */
    private String province;

    /**
     * 城市, 对应地区表AREA_CODE
     */
    private String city;

//    /** 备用字段1 */
//    private String standby1;
//    /** 备用字段2 */
//    private String standby2;
//    /** 备用字段3 */
//    private String standby3;
//    /** 备用字段4 */
//    private String standby4;

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
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次更新时间
     */
    private Date updateTime;

    private boolean enabled;

    /**
     * 更新用户id
     */
    private long updateUser;
    /**
     * 更新用户名称
     */
    private String updateUserName;
}
