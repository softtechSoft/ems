package com.softtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.Contract;
import com.softtech.mapper.ContractMapper;

@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	ContractMapper contractMapper;

	@Override
	public Contract getContract(String employeeID) {

		return contractMapper.getContract(employeeID);
	}



}
