package com.softtech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.com.YearInfo;
import com.softtech.entity.SalaryInfo;
import com.softtech.entity.SalaryInfoComment;
import com.softtech.mapper.SalaryInfoMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：給料サービス暗さ于
 *
 * 作成者：開発@ソフトテク
 * 作成日：2021/4/23
 */
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
	public SalaryInfoBean tranferData(SalaryInfo salaryInfo) {
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		//名前
		salaryInfoBean.setEmployeeName(salaryInfo.getEmployeeName());
		//住所
		salaryInfoBean.setAddress(salaryInfo.getAddress());
		//対象月
		salaryInfoBean.setMonth(salaryInfo.getMonth());
		//支払日
		salaryInfoBean.setPaymentDate(salaryInfo.getPaymentDate());
		//基本給
		salaryInfoBean.setBase(salaryInfo.getBase());
		//残業時間
		salaryInfoBean.setOverTime(salaryInfo.getOverTime());
		//不足時間
		salaryInfoBean.setShortage(salaryInfo.getShortage());
		//残業加算
		salaryInfoBean.setOverTimePlus(salaryInfo.getOverTimePlus());
		//稼働不足減
		salaryInfoBean.setShortageReduce(salaryInfo.getShortageReduce());
		//交通費
		salaryInfoBean.setTransportExpense(salaryInfo.getTransportExpense());
		//手当加算
		salaryInfoBean.setAllowancePlus(salaryInfo.getAllowancePlus());
		//手当減算
		salaryInfoBean.setAllowanceReduce(salaryInfo.getAllowanceReduce());
		//手当理由
		salaryInfoBean.setAllowanceReason(salaryInfo.getAllowanceReason());
		//特別加算
		salaryInfoBean.setSpecialAddition(salaryInfo.getSpecialAddition() );
		//厚生年金控除個人
		salaryInfoBean.setWelfarePensionSelf(salaryInfo.getWelfarePensionSelf());
		//厚生年金控除会社
		salaryInfoBean.setWelfarePensionComp(salaryInfo.getWelfarePensionComp());
		//厚生健康控除会社
		salaryInfoBean.setWelfareHealthComp(salaryInfo.getWelfareHealthComp());
		//厚生健康控除個人
		salaryInfoBean.setWelfareHealthSelf(salaryInfo.getWelfareHealthSelf());
		//厚生控除子育(会社)
		salaryInfoBean.setWelfareBaby(salaryInfo.getWelfareBaby());
		//雇用保険個人負担
		salaryInfoBean.setEplyInsSelf(salaryInfo.getEplyInsSelf());
		//雇用保険会社負担
		salaryInfoBean.setEplyInsComp(salaryInfo.getEplyInsComp());
		//雇用保拠出金（会社)
		salaryInfoBean.setEplyInsWithdraw(salaryInfo.getEplyInsWithdraw());
		//労災保険（会社負担のみ)
		salaryInfoBean.setWkAcccpsIns(salaryInfo.getWkAcccpsIns());
		//源泉控除
		salaryInfoBean.setWithholdingTax(salaryInfo.getWithholdingTax());
		//住民税控除
		salaryInfoBean.setMunicipalTax(salaryInfo.getMunicipalTax());
		//社宅家賃控除
		salaryInfoBean.setRental(salaryInfo.getRental());
		//社宅共益費控除
		salaryInfoBean.setRentalMgmtFee(salaryInfo.getRentalMgmtFee());
		//特別控除
		salaryInfoBean.setSpecialReduce(salaryInfo.getSpecialReduce());
		//総額
		salaryInfoBean.setSum(salaryInfo.getSum());
		//総費用
		salaryInfoBean.setTotalFee(salaryInfo.getTotalFee());
		//備考
		salaryInfoBean.setRemark(salaryInfo.getRemark());
		//削除フラグ
		salaryInfoBean.setDeleteFlg(salaryInfo.getDeleteFlg());
		//作成日
		salaryInfoBean.setInsertDate(salaryInfo.getInsertDate());
		//更新日
		salaryInfoBean.setUpdateDate(salaryInfo.getUpdateDate());

		return salaryInfoBean;
	}

	/**
	 * 機能：選択する年度IDの設定
	 *
	 * @param salaryInfoBean 給料情報
	 * @param yearMonth 選択された年月度
	 *
	 * @return 給料情報
	 * @author 開発@ソフトテク
	 */
	public SalaryInfoBean setSelectedYear(SalaryInfoBean salaryInfoBean,String yearMonth) {

		// 年度リストを取得する
		ArrayList<YearInfo> years = salaryInfoBean.getYearInfoList();

		//給料詳細画面にて選択された年を設定する
		String selectedYear = DateUtil.chgMonthToYM(yearMonth).substring(0,4);

		//年度リストごとループ
		for(YearInfo yearInfo:years) {
			int yearID = yearInfo.getId();
			String year = yearInfo.getName();
			if(selectedYear.equals(year)) {
				salaryInfoBean.setSelectedYearId(yearID);
				break;
			}
		}
		return salaryInfoBean;
	}

	/**
	 * 機能：選択する月度IDの設定
	 *
	 * @param salaryInfoBean 給料情報
	 * @param yearMonth 選択された年月度
	 *
	 * @return 給料情報
	 * @author 開発@ソフトテク
	 */
	public SalaryInfoBean setSelectedMonth(SalaryInfoBean salaryInfoBean,String yearMonth) {

		//選択された月を設定する
		String monthID = DateUtil.chgMonthToYM(yearMonth).substring(4);
		switch (Integer.parseInt( monthID)){
		case 1:
			salaryInfoBean.setSelectedMonthId(1);
			break;
		case 2 :
			salaryInfoBean.setSelectedMonthId(2);
			break;
		case 3 :
			salaryInfoBean.setSelectedMonthId(3);
			break;
		case 4 :
			salaryInfoBean.setSelectedMonthId(4);
			break;
		case 5 :
			salaryInfoBean.setSelectedMonthId(5);
			break;
		case 6 :
			salaryInfoBean.setSelectedMonthId(6);
			break;
		case 7 :
			salaryInfoBean.setSelectedMonthId(7);
			break;
		case 8 :
			salaryInfoBean.setSelectedMonthId(8);
			break;
		case 9 :
			salaryInfoBean.setSelectedMonthId(9);
			break;
		case 10 :
			salaryInfoBean.setSelectedMonthId(10);
			break;
		case 11 :
			salaryInfoBean.setSelectedMonthId(11);
			break;
		case 12 :
			salaryInfoBean.setSelectedMonthId(12);
			break;
		default:
			break;
		}

		return salaryInfoBean;
	}

}
