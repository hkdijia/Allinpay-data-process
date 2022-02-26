package com.gotkx.mapper;


import com.gotkx.model.SystemArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemAreaMapper {
	
	List<SystemArea> getAll();

	SystemArea getOne(@Param("areaName")String areaName, @Param("cityCode")String cityCode, @Param("provinceCode")String provinceCode);

	String queryByProvinceName(String provinceName);

	String queryByCityName(@Param("cityName")String cityName, @Param("provinceCode")String provinceCode);
}