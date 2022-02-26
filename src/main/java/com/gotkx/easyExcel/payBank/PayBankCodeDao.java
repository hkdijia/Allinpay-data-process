package com.gotkx.easyExcel.payBank;

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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 *
 * @author Jiaju Zhuang
 **/
@Log4j2
@Component
public class PayBankCodeDao {

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

    @PostConstruct
    private void init(){
        all = systemAreaMapper.getAll();
        provinceList = all.stream().filter(systemArea -> systemArea.getAreaType().equals("2"))
                .collect(Collectors.toList());
        provinceMap = provinceList.stream()
                .collect(Collectors.toMap(systemArea -> systemArea.getAreaName(), systemArea -> systemArea.getAreaCode()));

        cityList = all.stream().filter(systemArea -> systemArea.getAreaType().equals("3"))
                .collect(Collectors.toList());
        collect = cityList.stream()
                .collect(Collectors.groupingBy(SystemArea::getParentArea, LinkedHashMap::new, Collectors.toList()));
    }


    public void save(List<PayBankCodeExcelData> list) {
        //计数器
         AtomicInteger localTrueCount = new AtomicInteger();

         AtomicInteger localSuccessCount = new AtomicInteger();
        //计数器
         AtomicInteger localFailCount = new AtomicInteger();

         AtomicInteger localCityNameFailCount = new AtomicInteger();

        String provinceCode = null;
        String cityCode = null;

        //  查询出汇商库中所有的银行code 数据   然后再进行比较
        List<SystemBank> systemBankList = systemBankMapper.getByCondition(null, "ALLINPAY");
        // key:第三方银行代码，即通联支付系银行  value:bankid 在汇商表中的id记录
        Map<String, Long> collect = systemBankList.stream()
                .collect(Collectors.toMap(systemBank -> systemBank.getThirdBankCode(), systemBank -> systemBank.getBankId()));

        List<String> thirdBankCode = systemBankList.stream()
                                                    .map(systemBank -> systemBank.getThirdBankCode())
                                                    .distinct()
                                                    .collect(Collectors.toList());
        for (PayBankCodeExcelData data : list) {
            // 这里是4位，但在 system_bank 中是三位，需要进行截取
            String bankCode = data.getBankCode();
            String substring = bankCode.substring(bankCode.length() - 3);
            if(!StringUtils.isEmpty(substring) && thirdBankCode.contains(substring)){
                Long bankId = collect.get(substring);
                if(!StringUtils.isEmpty(bankId)){
                    localTrueCount.incrementAndGet();
                    String bankName = data.getBankName();
                    // 这是中文  省
                    String provinceName = data.getProvinceCode();
                    for (Map.Entry<String, String> entry : provinceMap.entrySet()) {
                        boolean contains = entry.getKey().contains(provinceName);
                        if(contains){
                            provinceCode = entry.getValue();
                        }
                    }

                    // 市
                    String area = data.getArea();
                    if(area.equals("北京") || area.equals("天津") || area.equals("重庆") || area.equals("上海")){
                        area = "市辖区";
                    }
                    log.info("进入市区对照逻辑, 当前市名称 {}", area);
                    for (SystemArea city : cityList) {
                        String areaName = city.getAreaName();
                        if(areaName.contains(area)){
                            cityCode = city.getAreaCode();
                        }
                    }
                    if(StringUtils.isEmpty(cityCode)){
                        // 说明市取值有问题  起码城市里面 没有
                        log.info("城市名对照失败，无法进入银行网点对照逻辑, 当前传入城市名称 {}", area);
                        cityNameFailCount.incrementAndGet();
                        continue;
                    }
                    // 计数  可以入库的数据
                    trueCount.incrementAndGet();
                    String netBankCode = data.getNetBankCode();
                    SystemBankBranch systemBankBranch = SystemBankBranch.builder()
                                                .bankBranchName(bankName)
                                                .contactLine(netBankCode)
                                                .createTime(new Date())
                                                .updateTime(new Date())
                                                .createUserName("admin")
                                                .createUser(1)
                                                .updateUser(1)
                                                .updateUserName("admin")
                                                .physicsFlag(1)
                                                .enabled(true)
                                                .bankId(bankId)
                                                .province(provinceCode)
                                                .city(cityCode)
                                                .remark("脚本插入")
                                                .build();
                    int insert = systemBankBranchMapper.insert(systemBankBranch);
                    if(insert > 0){
                        successCount.incrementAndGet();
                        localSuccessCount.incrementAndGet();
                    }
                }

            }else {
                log.info("无法进入银行网点对照逻辑, 当前传入名称 {}", substring);
                failCount.incrementAndGet();
                localFailCount.incrementAndGet();
            }
        }

        log.info("该批次输入录入完成, 理论可入库数据 {} 条, 共完成 {} 条, 不符合条件记录 {} 条, 城市对照失败 {} 条",
                localTrueCount, localSuccessCount,localFailCount,localCityNameFailCount);
        log.info("总录入情况: 理论可入库数据 {} 条, 共完成 {} 条, 不符合条件记录 {} 条, 城市对照失败 {} 条",
                trueCount, successCount,failCount,cityNameFailCount);
    }


}
