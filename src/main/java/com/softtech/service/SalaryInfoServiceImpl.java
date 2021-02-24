package com.softtech.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.SalaryInfo;
import com.softtech.entity.SalaryInfoComment;
import com.softtech.mapper.SalaryInfoMapper;

@Service
public class SalaryInfoServiceImpl implements SalaryInfoService {
	@Autowired
	SalaryInfoMapper salaryInfoMapper;

	@Override
	public List<SalaryInfo> queryAllSalaryInfo() {
		return salaryInfoMapper.queryAllSalaryInfo();
	}

	@Override
	public SalaryInfo uploadSalaryInfo(String slry) {
		String slryinfo = "insert into ems.salaryinfo values (employeeID, month,employeeName,paymentDate,base,overTimePlus,shortageReduce,transportExpense,allowancePlus,allowanceReduce,allowanceReason,welfareSelf,welfareComp,welfareBaby,eplyInsSelf,eplyInsComp,eplyInsWithdraw,withholdingTax,municipalTax,sum,deleteFlg,insertDate,updateDate)";
		return salaryInfoMapper.uploadSalaryInfo(slryinfo);
	}

	@Override
	public SalaryInfo querySalaryInfo(Map<String, String> map) {
		return salaryInfoMapper.querySalaryInfo(map);
	}

	@Override
	public int updateSalaryInfo(Map<String, String> map) {
		return salaryInfoMapper.updateSalaryInfo(map);
	}

	@Override
	public List<SalaryInfoComment> querySalaryInfoComment() {

		return salaryInfoMapper.querySalaryInfoComment();
	}
}
