package com.gotkx.easyExcel.mccCode;

import com.gotkx.mapper.SystemIndustryThirdMapper;
import com.gotkx.model.SystemArea;
import com.gotkx.model.SystemIndustryThird;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 *
 * @author Jiaju Zhuang
 **/
@Log4j2
@Component
public class MccCodeDao {

    //计数器
    private AtomicInteger trueCount = new AtomicInteger();

    private AtomicInteger successCount = new AtomicInteger();
    //计数器
    private AtomicInteger failCount = new AtomicInteger();
    private AtomicInteger cityNameFailCount = new AtomicInteger();


    @Autowired
    private SystemIndustryThirdMapper systemIndustryThirdMapper;

    public void save(List<MccCodeExcelData> list) {
        //List<SystemIndustryThird> all = systemIndustryThirdMapper.getAll();
        //计数器
        for (MccCodeExcelData data : list) {
//            SystemIndustryThird build = SystemIndustryThird.builder()
//                    .industryId(Long.parseLong(data.getMccId()))
//                    .industryName(data.getMccName())
//                    .industryType("ALLINPAY")
//                    .parentIndustry(0)
//                    .merchantClass(2)
//                    .physicsFlag(true)
//                    .createUser(1)
//                    .createUserName("超级管理员")
//                    .updateUser(1)
//                    .updateUserName("超级管理员")
//                    .createTime(new Date())
//                    .updateTime(new Date())
//                    .build();

            SystemIndustryThird build = SystemIndustryThird.builder()
                    .industryId(1l)
                    .industryType("ALLINPAY")
                    .industryName(data.getMccName())
                    .parentIndustry(0)
                    .merchantClass(1)
                    .thirdMccId(data.getMccId())
                    .hsIndustryCode("1")
                    .remark("脚本导入")
                    .physicsFlag(true)
                    .createUser(1)
                    .createUserName("超级管理员")
                    .updateUser(1)
                    .updateUserName("超级管理员")
                    .updateTime(new Date())
                    .createTime(new Date())
                    .build();

            int insert;

            systemIndustryThirdMapper.insert(build);
            successCount.incrementAndGet();

            build.setMerchantClass(2);
            systemIndustryThirdMapper.insert(build);
            successCount.incrementAndGet();

            build.setMerchantClass(3);
            systemIndustryThirdMapper.insert(build);
            successCount.incrementAndGet();

            build.setMerchantClass(4);
            systemIndustryThirdMapper.insert(build);
            successCount.incrementAndGet();
        }

        log.info("执行导入完成, 共成功: {}", successCount);
    }


}
