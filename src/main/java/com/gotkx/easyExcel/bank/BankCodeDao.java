package com.gotkx.easyExcel.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotkx.mapper.SystemBankMapper;
import com.gotkx.model.SystemBank;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 *
 * @author Jiaju Zhuang
 **/
@Log4j2
@Component
public class BankCodeDao {

    //计数器
    // 理论数量
    private AtomicInteger successTheoryCount = new AtomicInteger();
    // 实际成功数量
    private AtomicInteger successActualCount = new AtomicInteger();

    private AtomicInteger bankNameExistCount = new AtomicInteger();
    private ArrayList<String> bankNamExistList = new ArrayList();

    //计数器
    private AtomicInteger bankNameInexistCount = new AtomicInteger();
    private ArrayList<String> bankNameInexistList = new ArrayList();


    @Autowired
    private SystemBankMapper systemBankMapper;

    @SneakyThrows
    public void save(List<BankCodeExcelData> list) {
        log.info("进入对照逻辑, 共 {} 家银行", list.size());
        for (BankCodeExcelData data : list) {
            String bankName = data.getBankName();
            String bankCode = data.getBankCode();
            // ====================  兼容处理开始 =============================  //
            if(bankName.equals("进出口银行")){
                bankName = "中国进出口银行";
            }
            if(bankName.equals("农业发展银行")){
                bankName = "中国农业发展银行";
            }
            if(bankName.equals("中国银行(香港)")){
                bankName = "中國銀行(香港)有限公司";
            }
            if(bankName.equals("集友银行有限公司")){
                bankName = "集友银行";
            }
            if(bankName.equals("创兴银行有限公司")){
                bankName = "廖创兴银行";
            }
            if(bankName.equals("美国银行有限公司")){
                bankName = "美国银行";
            }
            if(bankName.equals("三菱东京日联银行")){
                bankName = "三菱东京日联银行(中国)有限公司";
            }
            if(bankName.equals("日本三井住友银行股份有限公司")){
                bankName = "三井住友银行";
            }
            if(bankName.equals("日本山口银行股份有限公司")){
                bankName = "山口银行";
            }
            if(bankName.equals("韩国外换银行股份有限公司")){
                bankName = "韩国外换银行";
            }
            if(bankName.equals("泰国盘谷银行(大众有限公司)")){
                bankName = "盘谷银行";
            }
            if(bankName.equals("奥地利中央合作银行股份有限公司")){
                bankName = "奥地利中央合作银行";
            }
            if(bankName.equals("比利时联合银行股份有限公司")){
                bankName = "比利时联合银行";
            }
            if(bankName.equals("比利时富通银行有限公司")){
                bankName = "比利时富通银行";
            }
            if(bankName.equals("英国苏格兰皇家银行公众有限公司")){
                bankName = "苏格兰皇家银行";
            }
            if(bankName.equals("法国东方汇理银行股份有限公司")){
                bankName = "东方汇理银行";
            }
            if(bankName.equals("法国外贸银行股份有限公司")){
                bankName = "法国外贸银行";
            }
            if(bankName.equals("德国德累斯登银行股份公司")){
                bankName = "德累斯顿银行";
            }
            if(bankName.equals("德国商业银行股份有限公司")){
                bankName = "德国商业银行";
            }
            if(bankName.equals("德国巴伐利亚州银行")){
                bankName = "巴伐利亚州银行";
            }
            if(bankName.equals("意大利联合圣保罗银行股份有限公司")){
                bankName = "意大利联合圣保罗银行";
            }
            if(bankName.equals("加拿大丰业银行有限公司")){
                bankName = "丰业银行";
            }
            if(bankName.equals("加拿大蒙特利尔银行有限公司")){
                bankName = "蒙特利尔银行";
            }
            if(bankName.equals("荷兰合作银行有限公司")){
                bankName = "荷兰合作银行";
            }
            if(bankName.equals("华一银行")){
                bankName = "富邦华一银行";
            }
            if(bankName.equals("中国邮储")){
                bankName = "中国邮政储蓄银行";
            }
            if(bankName.equals("工商银行")){
                bankName = "中国工商银行";
            }
            if(bankName.equals("农业银行")){
                bankName = "中国农业银行";
            }
            if(bankName.equals("建设银行")){
                bankName = "中国建设银行";
            }
            if(bankName.equals("光大银行")){
                bankName = "中国光大银行";
            }
            if(bankName.equals("民生银行")){
                bankName = "中国民生银行";
            }
            if(bankName.equals("农村信用社")){
                bankName = "农村信用合作社";
            }
            if(bankName.equals("美国建东银行")){
                bankName = "建东银行";
            }
            if(bankName.equals("日本日联银行")){
                bankName = "日联银行";
            }
            if(bankName.equals("韩国新韩银行")){
                bankName = "新韩银行";
            }
            if(bankName.equals("法国东方汇理银行")){
                bankName = "东方汇理银行";
            }

            // ====================  兼容处理结束 =============================   //

            // 根据 bankName 查询在威富通内对应的信息， 然后两部信息创建新的记录出来
            List<SystemBank> bankList = systemBankMapper.getByCondition(bankName,"HS");
            if(!CollectionUtils.isEmpty(bankList)){
                if(bankList.size() > 1){
                    log.info("有两个结果集的银行：{}", bankList);
                }

                ObjectMapper objectMapper = new ObjectMapper();
                bankNameExistCount.incrementAndGet();
                bankNamExistList.add(bankName);
                log.info("银行名：{} 在系统中有对应记录, 进入对照逻辑",bankName);
                log.info(objectMapper.writeValueAsString(bankList));

                // 数据处理和修正
                for (SystemBank systemBank : bankList) {
                    if(systemBank.getBankLetterNo().equals("110")){
                        continue;
                    }
                    successTheoryCount.incrementAndGet();
                    systemBank.setThirdType("ALLINPAY");
                    systemBank.setHsBankId(String.valueOf(systemBank.getBankId()));
                    systemBank.setThirdBankCode(bankCode);
                    systemBank.setCreateTime(new Date());
                    systemBank.setUpdateTime(new Date());
                    systemBank.setRemark("脚本导入");
                    systemBank.setCreateUser(0);
                    systemBank.setCreateUserName("system");
                    // 执行插入新增逻辑
                    int insert = systemBankMapper.insert(systemBank);
                    if(insert > 0){
                        successActualCount.incrementAndGet();
                    }
                }

            }else {
                bankNameInexistCount.incrementAndGet();
                bankNameInexistList.add(bankName);
                //log.info("银行名：{} 在系统中无对应记录, ",bankName);
            }
        }

        if(bankNameInexistList.size() > 0){
            log.info("以下银行名：{} 在系统中无对应记录, 共 {} 家银行",bankNameInexistList, bankNameInexistCount);
        }

        log.info("以下银行名：{} 在系统中存在对应记录, 共 {} 家银行",bankNamExistList, bankNameExistCount);


        log.info("完成数据导入, 理论数据数量:{}, 实际数据数量:{}",successTheoryCount, successActualCount);

        bankNameExistCount.set(0);
        bankNamExistList.clear();
        bankNameInexistCount.set(0);
        bankNameInexistList.clear();

        successActualCount.set(0);
        successTheoryCount.set(0);
    }


}
