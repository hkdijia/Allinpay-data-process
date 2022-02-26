package com.gotkx.mapper;


import com.gotkx.model.SystemIndustryHs;
import com.gotkx.model.SystemIndustryThird;

import java.util.List;

public interface SystemIndustryHsMapper {

    List<SystemIndustryHs> getAll();


//    AreaMapping getOne(String areaName);
//
    int insert(SystemIndustryThird systemIndustryThird);

}