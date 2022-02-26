package com.gotkx.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.gotkx.easyExcel.analyse.AnalyseDao;
import com.gotkx.easyExcel.analyse.AnalyseExcelDataListener;
import com.gotkx.easyExcel.payBank.PayBankCodeDao;
import com.gotkx.easyExcel.payBank.PayBankCodeExcelData;
import com.gotkx.easyExcel.payBank.PayBankCodeExcelDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payBankCode")
public class PayBankCodeController {

    @Autowired
    private PayBankCodeDao bankCodeDao;

    @Autowired
    private AnalyseDao analyseDao;

    @RequestMapping("/loadExcelToDB")
    public void loadExcelToDB() {
        String fileName = "C:\\Users\\Administrator.WFT--OSZRVYLJBY\\OneDrive\\参加工作\\威富通\\通联支付\\支付行号\\支付行号-测试.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, PayBankCodeExcelData.class, new PayBankCodeExcelDataListener(bankCodeDao)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }


    /**
     * 分析生产差异
     */
    @RequestMapping("/analyseToDB")
    public void analyseToDB() {
        String fileName = "C:\\Users\\Administrator.WFT--OSZRVYLJBY\\OneDrive\\参加工作\\威富通\\通联支付\\支付行号\\支付行号-测试.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, PayBankCodeExcelData.class, new AnalyseExcelDataListener(analyseDao)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

}