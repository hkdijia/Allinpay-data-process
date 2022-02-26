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
public class AreaMapping {

    /** 主键 */
    private Long id;
    /**第三方编码类型, GD:高德地区编码 SP: 威富通地区编码*/
    private String thirdType;
    /** 第三方编码 */
    private String thirdCode;
    /** 汇商编码 */
    private String hstypayCode;
    /** 备用字段1 */
    private String standby1;
    /** 备用字段2 */
    private String standby2;
    /** 备用字段3 */
    private String standby3;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
