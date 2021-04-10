package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WorkDetail;
import com.softtech.mapper.WorkDetailListMapper;
@Service
public class WorkDetailListServiceImpl implements WorkDetailListService{
	@Autowired
	WorkDetailListMapper workDetailListMapper;
	@Override
	public List<WorkDetail> queryWorkDetail(String month) {

        // YYYY/MM→yyyymmに変換

		// DBからデータを取得する
		WorkDetail workDetail = new WorkDetail();
		workDetail.setWorkInfoID("");
		workDetail.setContractID("");
		workDetail.setWorkMonth("");
		workDetail.setWorkTime(0);
//		workDetail.setA("あり");
		workDetail.setTransportExpense(0);
		workDetail.setTransport(0);
//		workDetail.setC("SBT開発支援");
     	List<WorkDetail> a  =new ArrayList<WorkDetail>();
     	 a.add(workDetail);
		return  a;
	}

}
