package com.gotkx.mapper;


import com.gotkx.model.SystemBankBranch;

import java.util.List;

public interface SystemBankBranchMapper {
	
//	List<SystemBank> getAll();
//
//	List<SystemBank> getByCondition(@Param("bankName")String bankName, @Param("thirdType")String thirdType);

	int insert(SystemBankBranch systemBankBranch);

	List<SystemBankBranch> getAll();
}