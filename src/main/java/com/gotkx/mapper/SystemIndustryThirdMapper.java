package com.gotkx.mapper;


import com.gotkx.model.AreaMapping;
import com.gotkx.model.SystemIndustryThird;

import java.util.List;

public interface SystemIndustryThirdMapper {

    List<SystemIndustryThird> getAll();

    List<SystemIndustryThird> getByCondition();


//    AreaMapping getOne(String areaName);
//
    void insert(SystemIndustryThird systemIndustryThird);

}