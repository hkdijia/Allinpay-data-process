package com.gotkx.easyExcel.bank;

import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 *
 **/
@Data
public class BankCodeExcelData {

    /**
     * 银行代码  总行代码
     */
    private String bankCode;

    /**
     * 银行【支行、分行】名称
     */
    private String bankName;

}
