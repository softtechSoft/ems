package com.softtech.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Contract;

@Mapper
public interface ContractMapper {

	Contract getContract(String employeeID);
}
