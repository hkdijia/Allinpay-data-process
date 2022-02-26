package com.gotkx.mapper;


import com.gotkx.model.AreaMapping;

import java.util.List;

public interface AreaMappingMapper {

    List<AreaMapping> getAll();

    AreaMapping getOne(String areaName);

    int insert(AreaMapping areaMapping);

}