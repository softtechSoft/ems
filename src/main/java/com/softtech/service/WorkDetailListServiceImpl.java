package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WorkDetail;
import com.softtech.entity.Transport;
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
//		// 勤怠情報を取得する
//		List<WorkInfo> workInfoLst =  workDetailListMapper.getWorkInfoDetail(monthP);
//		// 交通費情報を取得する
//		List<Transport> transportLst = workDetailListMapper.geTransportDetail(monthP);
//
//		// 勤怠リストを作成する
//		List<WorkDetail> rtn  =new ArrayList<WorkDetail>();
//		for(WorkInfo wk : workInfoLst) {
//			WorkDetail workDetail = new WorkDetail();
//			workDetail.setWorkInfoID(wk.getWorkInfoID());
//			workDetail.setWorkMonth(month);
//			workDetail.setContractID(wk.getContractID());
//			if(wk.getWorkTime() == null ) {
//				workDetail.setWorkTime(0);
//			} else {
//				workDetail.setWorkTime(Float.parseFloat(wk.getWorkTime()));
//			}
//
//			for(Transport tt : transportLst) {
//				if(tt.getEmployeeID()==null) continue;
//				if(wk.getEmployeeID()==null) continue;
//
//				if(wk.getEmployeeID().equals(tt.getEmployeeID())) {
//					workDetail.setTransport(tt.getTransport());
//					if(tt.getTransportExpense() == null ) {
//						workDetail.setTransportExpense(0);
//					} else {
//						workDetail.setTransportExpense(Integer.parseInt(tt.getTransportExpense()));
//					}
//
//					continue;
//				}
//			}
//			rtn.add(workDetail);
//		}
		// 勤怠リストを取得する
		List<Transport> transportLst = workDetailListMapper.getWorkTransport(monthP);

		// 勤怠情報へ変更する。
		List<WorkDetail> rtn  = transfter(transportLst);
		return  rtn;
	}

	/**
	 * 機能：DBから取得したデータを勤怠情報へ変換する。
	 *
	 * @param lst DBから取得したデータ
	 * @return 勤怠情報リスト
	 *
	 * @author 馬@ソフトテク
	 */
	private List<WorkDetail> transfter(List<Transport> lst){
		if(lst == null ) return new ArrayList<WorkDetail>();

		List<WorkDetail> rtn  =new ArrayList<WorkDetail>();
		for(Transport tt : lst) {
			WorkDetail workDetail = new WorkDetail();
			//社員ID
			workDetail.setEmployeeID(tt.getEmployeeID());
			//社員氏名
			workDetail.setEmployeeName(tt.getEmployeeName());
			//対象月
			workDetail.setWorkMonth(tt.getWorkMonth());
			//勤怠時間（H)
			workDetail.setWorkTime(tt.getWorkTime());
			//定期券額（円）
			workDetail.setTransportExpense(tt.getTransportExpense1());
			//交通費（定期券以外）(円）
			workDetail.setTransport(tt.getTransport());

			rtn.add(workDetail);
		}

		return rtn;
	}

}
