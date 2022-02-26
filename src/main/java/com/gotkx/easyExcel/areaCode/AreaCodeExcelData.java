package com.gotkx.easyExcel.areaCode;

import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 *
 **/
@Data
public class AreaCodeExcelData {
    //  地区码
    private String regionCode;
    //  地区名称
    private String regionName;
    //  所在市
    private String city;
    //  所在省
    private String province;
}
