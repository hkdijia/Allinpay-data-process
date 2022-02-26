package com.gotkx.easyExcel.areaCode;

import com.gotkx.mapper.AreaMappingMapper;
import com.gotkx.mapper.SystemAreaMapper;
import com.gotkx.model.AreaMapping;
import com.gotkx.model.SystemArea;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
public class AreaCodeDao {

    //计数器
    private AtomicInteger successCount = new AtomicInteger();

    //计数器
    private AtomicInteger failCount = new AtomicInteger();
    HashMap<String,String> failMap = new HashMap<>();

    @Autowired
    private AreaMappingMapper areaMappingMapper;

    @Autowired
    private SystemAreaMapper systemAreaMapper;

    public void save(List<AreaCodeExcelData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
        // 操作相关数据
        // 构建省 -> 市  区分粒度是 【regionCode】 使用名字去查询并不可靠，如【朝阳区】在【北京】和【长春】都有  应该先根据省分组
        // 省级行政分组
        Map<String, List<AreaCodeExcelData>> map = list.stream().collect(Collectors.groupingBy(AreaCodeExcelData::getProvince));
        //  市级行政分组
        for (Map.Entry<String, List<AreaCodeExcelData>> entry : map.entrySet()) {
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            //  记录省数据
            String provinceName = entry.getKey();
            // 查询省名称，对应的code
            String provinceCode = systemAreaMapper.queryByProvinceName(provinceName);
            // 开始市区分组
            Map<String, List<AreaCodeExcelData>> cityMap = entry.getValue().stream().collect(Collectors.groupingBy(AreaCodeExcelData::getCity));
            for (Map.Entry<String, List<AreaCodeExcelData>> cityEntry : cityMap.entrySet()) {
                //  记录市数据
                String cityName = cityEntry.getKey();
                //   ========================   容错处理开始   ========================  //
                if(cityName.equals("北京") || cityName.equals("天津") || cityName.equals("重庆") || cityName.equals("上海")){
                    cityName = "市辖区";
                }
                //   ========================   容错处理结束   ========================  //

                //log.info("记录【市名称】：{},记录【市代码】：{}", cityName,cityCode);
                //  查询市区名称，对应的code
                String cityCode = systemAreaMapper.queryByCityName(cityName,provinceCode);
                //log.info("记录市代码名称数据：{}", cityCode);
                log.info("记录【市名称】：{},记录【市代码】：{}", cityName,cityCode);
                // 组装市区维度数据
                List<AreaCodeExcelData> dataList = cityEntry.getValue();
                loadInDb(dataList,cityCode,cityName);
            }
        }
        // 打印失败的记录
        if(failCount.get() > 0){
            log.info("失败总数为：{},打印失败的记录：{}", failCount.get(),failMap);
        }
        log.info("打印成功的记录数：{}", successCount);
    }

    @SneakyThrows
    void loadInDb(List<AreaCodeExcelData> list, String cityCode, String cityName){
        for (AreaCodeExcelData data : list) {
            String regionName = data.getRegionName();
            //   ========================   容错处理开始   ========================  //
            if(regionName.equals("宁乡市")){
                regionName = "宁乡县";
            }
            if(regionName.equals("淮阳区")){
                regionName = "淮阳县";
            }
            if(regionName.equals("兴仁市")){
                regionName = "兴仁县";
            }
            if(regionName.equals("罗江区")){
                regionName = "罗江县";
            }
            if(regionName.equals("邹平市")){
                regionName = "邹平县";
            }
            if(regionName.equals("怀仁市")){
                regionName = "怀仁县";
            }
//            if(regionName.equals("库车市")){
//                regionName = "库车县";
//            }
            if(regionName.equals("南郑区")){
                regionName = "南郑县";
            }
            if(regionName.equals("余江区")){
                regionName = "余江县";
            }
            if(regionName.equals("长乐区")){
                regionName = "长乐市";
            }
            if(regionName.equals("广德市")){
                regionName = "广德县";
            }
            if(regionName.equals("荔浦市")){
                regionName = "荔浦县";
            }
            if(regionName.equals("湟中区")){
                regionName = "湟中县";
            }
            if(regionName.equals("射洪市")){
                regionName = "射洪县";
            }
            if(regionName.equals("即墨区")){
                regionName = "即墨市";
            }
//            if(regionName.equals("平果市")){
//                regionName = "平果县";
//            }
            if(regionName.equals("龙港市")){
                regionName = "龙港区";
            }
            if(regionName.equals("邵东市")){
                regionName = "邵东县";
            }
            if(regionName.equals("潞城区")){
                regionName = "潞城市";
            }
//            if(regionName.equals("无为市")){
//                regionName = "无为县";
//            }
            if(regionName.equals("京山市")){
                regionName = "京山县";
            }
            if(regionName.equals("华亭市")){
                regionName = "华亭县";
            }
            if(regionName.equals("嫩江市")){
                regionName = "嫩江县";
            }
            if(regionName.equals("子长市")){
                regionName = "子长县";
            }
            if(regionName.equals("马龙区")){
                regionName = "马龙县";
            }
            if(regionName.equals("太谷区")){
                regionName = "太谷县";
            }
            if(regionName.equals("茌平区")){
                regionName = "茌平县";
            }
            if(regionName.equals("长垣市")){
                regionName = "长垣县";
            }
            if(regionName.equals("海安市")){
                regionName = "海安县";
            }
            if(regionName.equals("水富市")){
                regionName = "水富县";
            }
            if(regionName.equals("潜山市")){
                regionName = "潜山县";
            }
            if(regionName.equals("临安区")){
                regionName = "临安市";
            }
            if(regionName.equals("达孜区")){
                regionName = "达孜县";
            }
//            if(regionName.equals("澄江市")){
//                regionName = "澄江县";
//            }
            //   ========================   容错处理结束   ========================  //
            log.info("执行插入前数据记录【区县名称】：{}, 【城市-名称】：{}", regionName,cityName+cityCode);
            SystemArea systemArea = systemAreaMapper.getOne(regionName,cityCode,cityName);
            if (StringUtils.isEmpty(systemArea)) {
                log.info("入库失败, 该记录不存在【区县名称】：{}, 【城市-代码】：{}", regionName,cityName+cityCode);
                failCount.incrementAndGet();
                failMap.put(cityName+cityCode,regionName);
                continue;
            }
            // 得到需要的 地区编码 【区县级别】    系统码
            String areaCode = systemArea.getAreaCode();
            // 组装对象
            AreaMapping areaMapping = AreaMapping.builder()
                    .hstypayCode(areaCode)
                    .thirdCode(data.getRegionCode())
                    .thirdType("ALLINPAY")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            // 入库
            int id = areaMappingMapper.insert(areaMapping);
            if(id > 0){
                successCount.incrementAndGet();
            }
            // 只测试一条
//            System.out.println(id);
//            // 行记录id
//            System.out.println(areaMapping.getId());
        }
    }
}
