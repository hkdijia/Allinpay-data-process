package com.gotkx.easyExcel.payBank;

import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 *
 **/
@Data
public class PayBankCodeExcelData {
    /**
     * 银行网点代码
     */
    private String netBankCode;

    /**
     * 银行【支行、分行】名称
     */
    private String bankName;

    /**
     * 省代码
     */
    private String provinceCode;

    /**
     * 地区
      */
    private String area;

    /**
     * 银行代码  总行代码
      */
    private String bankCode;

    /**
     *
     */
    private String bafName;
}
