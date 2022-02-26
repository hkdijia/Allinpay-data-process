package com.gotkx.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.gotkx.easyExcel.areaCode.AreaCodeDao;
import com.gotkx.easyExcel.areaCode.AreaCodeExcelDataListener;
import com.gotkx.mapper.AreaMappingMapper;
import com.gotkx.mapper.SystemAreaMapper;
import com.gotkx.easyExcel.areaCode.AreaCodeExcelData;
import com.gotkx.model.AreaMapping;
import com.gotkx.model.SystemArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/areaMapping")
public class AreaMappingController {

    @Autowired
    private AreaMappingMapper areaMappingMapper;

    @Autowired
    private SystemAreaMapper systemAreaMapper;

    @Autowired
    private AreaCodeDao allinpayAreaCodeDao;

    @RequestMapping("/getAll")
    public List<AreaMapping> getUsers() {
        List<AreaMapping> areaMappings = areaMappingMapper.getAll();
        return areaMappings;
    }


    @RequestMapping("/loadExcelToDB")
    public void loadAreaCodeExcelToDB() {
        String fileName = "C:\\Users\\Administrator.WFT--OSZRVYLJBY\\OneDrive\\参加工作\\威富通\\通联支付\\地区码\\地区码-测试.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, AreaCodeExcelData.class, new AreaCodeExcelDataListener(allinpayAreaCodeDao)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }



    @RequestMapping("/queryByName")
    public SystemArea queryByName(String areaName, String cityCode, String provinceCode) {
        SystemArea systemArea = systemAreaMapper.getOne(areaName, cityCode, provinceCode);
        return systemArea;
    }


}