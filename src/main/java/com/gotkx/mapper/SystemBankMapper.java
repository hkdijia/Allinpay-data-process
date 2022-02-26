package com.gotkx.mapper;


import com.gotkx.model.SystemBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemBankMapper {
	
	List<SystemBank> getAll();

	List<SystemBank> getByCondition(@Param("bankName")String bankName, @Param("thirdType")String thirdType);

	int insert(SystemBank systemBank);
}