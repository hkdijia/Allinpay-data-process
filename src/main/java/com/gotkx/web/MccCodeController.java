package com.gotkx.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.gotkx.easyExcel.mccCode.MccCodeDao;
import com.gotkx.easyExcel.mccCode.MccCodeExcelData;
import com.gotkx.easyExcel.mccCode.MccCodeExcelDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mccCode")
public class MccCodeController {

    @Autowired
    private MccCodeDao mccCodeDao;

    @RequestMapping("/loadExcelToDB")
    public void loadExcelToDB() {
        String fileName = "C:\\Users\\Administrator.WFT--OSZRVYLJBY\\OneDrive\\参加工作\\威富通\\通联支付\\行业\\行业 - 副本.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, MccCodeExcelData.class, new MccCodeExcelDataListener(mccCodeDao)).build();
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