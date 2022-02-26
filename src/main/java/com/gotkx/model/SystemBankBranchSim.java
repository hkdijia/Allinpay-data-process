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
public class SystemBankBranchSim {

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


    /**
     * 备注
     */
    private String remark;

    /**
     * 物理标识, true(1) 正常 false(0) 删除
     */
    private int physicsFlag;


    private boolean enabled;

}
