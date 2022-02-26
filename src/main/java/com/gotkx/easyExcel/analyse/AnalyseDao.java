package com.gotkx.easyExcel.analyse;

import com.gotkx.easyExcel.payBank.PayBankCodeExcelData;
import com.gotkx.mapper.SystemAreaMapper;
import com.gotkx.mapper.SystemBankBranchMapper;
import com.gotkx.mapper.SystemBankMapper;
import com.gotkx.model.SystemArea;
import com.gotkx.model.SystemBank;
import com.gotkx.model.SystemBankBranch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 *
 * @author Jiaju Zhuang
 **/
@Log4j2
@Component
public class AnalyseDao {

    //计数器
    private AtomicInteger trueCount = new AtomicInteger();

    private AtomicInteger successCount = new AtomicInteger();
    //计数器
    private AtomicInteger failCount = new AtomicInteger();
    private AtomicInteger cityNameFailCount = new AtomicInteger();

    @Autowired
    private SystemAreaMapper systemAreaMapper;

    @Autowired
    private SystemBankMapper systemBankMapper;

    @Autowired
    private SystemBankBranchMapper systemBankBranchMapper;

    private static List<SystemArea>  all = null;
    private static List<SystemArea>  provinceList = null;
    private static List<SystemArea>  cityList = null;
    private static Map<String, String>  provinceMap = null;
    private static Map<String, List<SystemArea>> collect = null;

    private static CopyOnWriteArrayList<PayBankCodeExcelData> successList = new CopyOnWriteArrayList<>();

    private static CopyOnWriteArrayList<PayBankCodeExcelData> failList = new CopyOnWriteArrayList<>();


    //private static List<SystemBankBranch>  systemBankBranchList = new ArrayList<>(160000);

    //private static List<SystemBankBranch>  systemBankBranchList = systemBankBranchMapper.getAll();

    private static List<SystemBankBranch> systemBankBranchList = null;
    private static List<String> contactLineList = null;

    @PostConstruct
    private void init(){
//        all = systemAreaMapper.getAll();
//        provinceList = all.stream().filter(systemArea -> systemArea.getAreaType().equals("2"))
//                .collect(Collectors.toList());
//        provinceMap = provinceList.stream()
//                .collect(Collectors.toMap(systemArea -> systemArea.getAreaName(), systemArea -> systemArea.getAreaCode()));
//
//        cityList = all.stream().filter(systemArea -> systemArea.getAreaType().equals("3"))
//                .collect(Collectors.toList());
//        collect = cityList.stream()
//                .collect(Collectors.groupingBy(SystemArea::getParentArea, LinkedHashMap::new, Collectors.toList()));

        //  查询出 数据
        //systemBankBranchList = systemBankBranchMapper.getAll();

        systemBankBranchList = systemBankBranchMapper.getAll();
        contactLineList = systemBankBranchList.stream().map(SystemBankBranch::getContactLine).collect(Collectors.toList());
    }


    public void save(List<PayBankCodeExcelData> list, AtomicInteger batchCount) {
        //计数器
        for (PayBankCodeExcelData data : list) {
            // 银行代码
            String bankCode = data.getBankCode();
            // 网点号
            String netBankCode = data.getNetBankCode();
            // 银行名称
            String bankName = data.getBankName();
            // 总行名称
            String bafName = data.getBafName();
            if(contactLineList.contains(netBankCode)){
                // 说明网点匹配
                trueCount.incrementAndGet();
                successList.add(data);
            }else {
                failCount.incrementAndGet();
                failList.add(data);
            }
        }
        log.info("第{} 批次数据批次检测完成, 匹配的网点数量：{}, 不匹配的网点数量{}", batchCount, trueCount, failCount);

        if(list.size() < 3000){
            log.info("第{} 批次数据批次检测完成, 匹配的网点信息：{}, 不匹配的网点信息{}", batchCount, successList, failList);
        }
    }


}
