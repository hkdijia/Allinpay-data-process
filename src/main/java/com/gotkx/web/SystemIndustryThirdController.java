package com.gotkx.web;


import com.gotkx.mapper.SystemIndustryHsMapper;
import com.gotkx.mapper.SystemIndustryThirdMapper;
import com.gotkx.model.SystemIndustryHs;
import com.gotkx.model.SystemIndustryThird;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.awt.print.Book;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


@RestController
@RequestMapping("/systemIndustryThird")
@Log4j2
public class SystemIndustryThirdController {


    @Autowired
    private SystemIndustryThirdMapper systemIndustryThirdMapper;

    @Autowired
    private SystemIndustryHsMapper systemIndustryHsMapper;

    // 全量数据
    private static List<SystemIndustryThird> industryThirdList;
    // 汇商
    private static List<SystemIndustryThird> hsList;
    // 通联
    private static List<SystemIndustryThird> allinpayList;

    private  List<SystemIndustryThird> inList = new ArrayList<>();

    private  HashMap<Long,String> inMap = new HashMap<>();

    private  List<SystemIndustryThird> outList = new ArrayList<>();

    private  HashMap<Long,String> outMap = new HashMap<>();

    @PostConstruct
    private void init(){
        industryThirdList = systemIndustryThirdMapper.getAll();
        hsList = industryThirdList.stream()
                .filter(systemIndustryThird -> systemIndustryThird.getIndustryType().equals("HS"))
                .collect(Collectors.toList());

        allinpayList = industryThirdList.stream()
                .filter(systemIndustryThird -> systemIndustryThird.getIndustryType().equals("ALLINPAY"))
                .collect(Collectors.toList());
    }

    @RequestMapping("/transForData")
    public void loadExcelToDB() {
        List<SystemIndustryHs> hsList = systemIndustryHsMapper.getAll();
        for (SystemIndustryHs industryHs : hsList) {
            long industryId;
            // 判断是汇商，还是其他
            String hsIndustryCode = industryHs.getHsIndustryCode();
            //  说明是其他机构
            if(StringUtils.isEmpty(hsIndustryCode)){
                industryId = industryHs.getIndustryId();
            }else {
                industryId = Long.parseLong(hsIndustryCode);
            }

            SystemIndustryThird build = SystemIndustryThird.builder()
                    .industryId(industryId)
                    .industryType(industryHs.getIndustryType())
                    .industryName(industryHs.getIndustryName())
                    .parentIndustry(industryHs.getParentIndustry())
                    .merchantClass(industryHs.getMerchantClass())
                    .thirdMccId(industryHs.getMccId())
                    .hsIndustryCode(industryHs.getHsIndustryCode())
                    .remark("脚本导入")
                    .physicsFlag(industryHs.isPhysicsFlag())
                    .createUser(1)
                    .createUserName("超级管理员")
                    .updateUser(1)
                    .updateUserName("超级管理员")
                    .updateTime(new Date())
                    .createTime(new Date())
                    .build();

            systemIndustryThirdMapper.insert(build);
        }

    }

    @RequestMapping("/analyseToDB")
    public void analyseToDB(){
        HashMap<Object, String> map = new HashMap<>();

        ArrayList<SystemIndustryThird> collect = hsList.stream().collect(
                        collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getIndustryName()))),
                        ArrayList::new));

        List<Long> xxxx = hsList.stream()
                .map(systemIndustryThird -> systemIndustryThird.getIndustryId())
                .distinct()
                .collect(Collectors.toList());

        for (SystemIndustryThird third : hsList) {
            Long industryId = third.getIndustryId();
            String industryName = third.getIndustryName();
            map.put(industryId,industryName);
        }

//        List<Long> bigIds = collect.stream()
//                .map(systemIndustryThird -> systemIndustryThird.getIndustryId())
//                .distinct()
//                .collect(Collectors.toList());

//        ArrayList<SystemIndustryThird> arrayList = allinpayList.stream().collect(
//                collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getIndustryName()))),
//                        ArrayList::new));

        List<Long> longList = allinpayList.stream().map(systemIndustryThird -> systemIndustryThird.getIndustryId()).collect(Collectors.toList());

        for (Long aLong : longList) {
            System.out.println(aLong);
        }


        for (Long id : xxxx) {
            if(id.equals(4100L)){
                System.out.println("1111111111");
            }

            if(longList.contains(id)){
                String name = map.get(id);
                inMap.put(id,name);
            }else {
                // 找出这个id 对应的 hs 数据
                String name = map.get(id);
                outMap.put(id,name);
            }
        }

        log.info("行业匹配成功数据, 共 {} 条, 行业名称：{}",
                inMap.size(),
                inMap);

        log.info("行业匹配失败数据, 共 {} 条, 行业名称：{}",
                outMap.size(),
                outMap);




        //  数据分析
//        log.info("行业匹配成功数据, 共 {} 条, 行业名称和代码：{}",
//                inList.size(),
//                inList.stream()
//                        .map(systemIndustryThird -> systemIndustryThird.getIndustryName() + systemIndustryThird.getIndustryId())
//                        .collect(Collectors.toList()));
//
//        log.info("行业匹配失败数据, 共 {} 条, 行业名称和代码：{}",
//                outList.size(),
//                outList.stream()
//                        .map(systemIndustryThird -> systemIndustryThird.getIndustryName() + systemIndustryThird.getIndustryId())
//                        .collect(Collectors.toList()));


    }

}