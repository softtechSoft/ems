package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WorkDetail;
import com.softtech.entity.Transport;
import com.softtech.entity.WorkInfo;
import com.softtech.mapper.WorkDetailListMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：勤怠リストのservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/04/10
 */
@Service
public class WorkDetailListServiceImpl implements WorkDetailListService{
	@Autowired
	WorkDetailListMapper workDetailListMapper;
	@Override
	public List<WorkDetail> queryWorkDetail(String month) {

        // YYYY/MM→yyyymmに変換
		String monthP = DateUtil.chgMonthToYM(month);
		// 勤怠情報を取得する
		List<WorkInfo> workInfoLst =  workDetailListMapper.getWorkInfoDetail(monthP);
		// 交通費情報を取得する
		List<Transport> transportLst = workDetailListMapper.geTransportDetail(monthP);

		// 勤怠リストを作成する
		List<WorkDetail> rtn  =new ArrayList<WorkDetail>();
		for(WorkInfo wk : workInfoLst) {
			WorkDetail workDetail = new WorkDetail();
			workDetail.setWorkInfoID(wk.getWorkInfoID());
			workDetail.setWorkMonth(month);
			workDetail.setContractID(wk.getContractID());
			if(wk.getWorkTime() == null ) {
				workDetail.setWorkTime(0);
			} else {
				workDetail.setWorkTime(Float.parseFloat(wk.getWorkTime()));
			}

			for(Transport tt : transportLst) {
				if(tt.getEmployeeID()==null) continue;
				if(wk.getEmployeeID()==null) continue;

				if(wk.getEmployeeID().equals(tt.getEmployeeID())) {
					workDetail.setTransport(tt.getTransport());
					if(tt.getTransportExpense() == null ) {
						workDetail.setTransportExpense(0);
					} else {
						workDetail.setTransportExpense(Integer.parseInt(tt.getTransportExpense()));
					}

					continue;
				}
			}
			rtn.add(workDetail);
		}
		return  rtn;
	}

}
