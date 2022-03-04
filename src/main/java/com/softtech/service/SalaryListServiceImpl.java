package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.com.SalaryCommon;
import com.softtech.entity.SalaryInfo;
import com.softtech.mapper.SalaryListMapper;
/**
 * 概要：給料リストのservice
 *
 * 作成者：王＠ソフトテク
 * 作成日：2021/04/13
 */
@Service
public class SalaryListServiceImpl implements SalaryListService{
	@Autowired
	SalaryListMapper salarylistMapper;

	/**
	 * 機能：DBから取得したデータを取得する。
	 *
	 * @param year 対象年度
	 * @param employeeID 対象社員ID
	 * @return 給料情報リスト
	 *
	 * @author 王@ソフトテク
	 */
	@Override
	public List<SalaryInfoBean> getSalaryList(String year,String employeeID) {

		// DBからデータを取得する
		SalaryCommon salaryCommon= new SalaryCommon();
		// 開始年月設定
		salaryCommon.setFromMonth(year+"01");
		// 終了年月設定
		salaryCommon.setToMonth(year+"12");
		// 対象社員ID
		salaryCommon.setEmployeeID(employeeID);

		// 給料情報を取得する
		List<SalaryInfo> salaryInfoList =  salarylistMapper.getSalaryInfoList(salaryCommon);

		// EntityからFormBeanへ変換。
		return changeData(salaryInfoList);

	}
	/**
	 * 機能：給料Entityから給料FormBeanに変換する
	 *
	 * @param salaryInfos 給料Entity
	 * @return FormBeanのリスト
	 *
	 * @author テー@ソフトテク
	 */
	private List<SalaryInfoBean> changeData(List<SalaryInfo> salaryInfos)
	{
		List<SalaryInfoBean> salaryInfoBeans = new ArrayList<SalaryInfoBean>();
		if(salaryInfos == null ) return salaryInfoBeans;
		for(SalaryInfo si:salaryInfos) {
			SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
			//給料年月
			salaryInfoBean.setMonth(si.getMonth());
			//基本給
			salaryInfoBean.setBase(si.getBase());
			//残業代
			salaryInfoBean.setOverTimePlus(si.getOverTimePlus());
			//交通費
			salaryInfoBean.setTransportExpense(si.getTransportExpense());
			//控除（社会）
			salaryInfoBean.setWelfarePensionComp(si.getWelfarePensionComp());
			//控除(源泉)
			salaryInfoBean.setWithholdingTax(si.getWithholdingTax());
			//控除(住民税)
			salaryInfoBean.setMunicipalTax(si.getMunicipalTax());
			//総額
			salaryInfoBean.setSum(si.getSum());
			//他の画面項目を設定する
			salaryInfoBeans.add(salaryInfoBean);
		}
		return salaryInfoBeans;
	}

}
