package com.softtech.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.YukyuDetailFormBean;
import com.softtech.service.YukyuDetailService;
import com.softtech.util.DateUtil;
/**
 * 概要：有給管理機能
 *
 * 作成者：孫@ソフトテク
 * 作成日：2024/4/9
 */
@Controller
public class YukyuDetailController {

	@Autowired
	private final YukyuDetailService yukyuDetailService;

	public YukyuDetailController(YukyuDetailService yukyuDetailService) {
        this.yukyuDetailService = yukyuDetailService;
    }

    /**
	 * 機能：画面初期化と遷移
	 *
	 * @param model
	 * @return 有給管理画面
	 * @exception
	 *
	 * @author 孫@ソフトテク
	 */


	//画面初期化
    @RequestMapping("/yukyu")
    public String toYukyuList(Model model,HttpSession session) {
        //自分社員IDを取得
    	String employeeID=(String) session.getAttribute("userEmoplyeeID");
    	//現在の年度を取得
    	String nendo = DateUtil.getNowYear();
    	//DB検索（自分社員IDを取得と現在の年度を取得),給管理テーブルからデータを取得
    	Map<String, String> sqlParam = yukyuDetailService.getIdAndNendoForPara(employeeID, nendo);
    	YukyuDetailFormBean yukyuDetailep = yukyuDetailService.findIDnendo(sqlParam);
    	//DB検索（自分社員IDを取得),給管理テーブルからデータを取得
//    	YukyuDetailFormBean yukyuDetailep = yukyuDetailService.findEmployeeID(employeeID);
    	//年月日をフォーマット
    	YukyuDetailFormBean yukyuDetail = yukyuDetailService.transferDbToUI(yukyuDetailep);
        model.addAttribute("yukyuDetail", yukyuDetail);

        // 有給管理画面

        return "ems/yukyuManage";
    }

    /**
	 * 機能：更新
	 *
	 * @param model
	 * @return result
	 * @exception ParseException
	 *
	 * @author 孫@ソフトテク
	 */
    //更新ボタン
    @PostMapping("/btn-yukyuUpdate")
	public String yukyuSubmit(
			@Validated @ModelAttribute("yukyuDetail") YukyuDetailFormBean yukyuDetail,
			BindingResult errors,
			Model model) throws ParseException {

		// Validationチェックエラー時、エラー情報表示
		if (errors.hasErrors()) {
			model.addAttribute("yukyuDetail", yukyuDetail);
			return "/ems/yukyuManage";
		}
		// DB更新
		Map<String, String> map = yukyuDetailService.transferUIToPara(yukyuDetail);
		int num = yukyuDetailService.updateYukyuDetail(map);

		if (num == 1) {
			model.addAttribute("updateMsg", "有給情報を更新しました。");
		} else {
			model.addAttribute("updateMsg", "有給情報の更新に失敗しました。");
		}
		// 画面再表示設定
    	YukyuDetailFormBean yukyuDetaileps= yukyuDetailService.findIDnendo(map);
    	//年月日をフォーマット
    	YukyuDetailFormBean yukyuDetails= yukyuDetailService.transferDbToUI(yukyuDetaileps);

		model.addAttribute("yukyuDetail", yukyuDetails);

		return "/ems/yukyuManage";

	}

}





