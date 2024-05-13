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

import com.softtech.actionForm.YukyuInfoFormBean;
import com.softtech.service.YukyuInfoService;
import com.softtech.util.DateUtil;
/**
 * 概要：有給管理機能
 *
 * 作成者：孫@ソフトテク
 * 作成日：2024/4/9
 */
@Controller
public class YukyuInfoController {

	@Autowired
	private final YukyuInfoService yukyuInfoService;

	public YukyuInfoController(YukyuInfoService yukyuInfoService) {
        this.yukyuInfoService = yukyuInfoService;
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
        //登録の社員IDを取得
    	String employeeID=(String) session.getAttribute("userEmoplyeeID");
    	//現在の年度を取得
    	String nendo = DateUtil.getNowYear();
    	//取得したIDと年を受け取るコンテナを新規作成します
    	YukyuInfoFormBean DetailForm = new YukyuInfoFormBean();
    	DetailForm.setEmployeeID(employeeID);
    	DetailForm.setNendo(nendo);
		//コンテナをマップに変換する
    	Map<String, String> sqlParam = yukyuInfoService.transferUIToMap(DetailForm);
    	//マップ型データでDBを検索し、最終的にFromBean形式に変換します
    	YukyuInfoFormBean yukyuDetailFormBean = yukyuInfoService.findIDnendo(sqlParam);


//    	YukyuInfoFormBean yukyuDetailFormBean = yukyuInfoService.findIDnendo1(DetailForm);

        model.addAttribute("yukyuDetail", yukyuDetailFormBean);

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
			@Validated @ModelAttribute("yukyuDetail") YukyuInfoFormBean yukyuInfoFormBean,
			BindingResult errors,
			Model model) throws ParseException {

		// Validationチェックエラー時、エラー情報表示
		if (errors.hasErrors()) {
			return "/ems/yukyuManage";
		}
		// DB更新
//		Map<String, String> map = yukyuInfoService.transferUIToMap(yukyuInfoFormBean);
//		int num = yukyuInfoService.update(map);
//
//		if (num == 1) {
//			model.addAttribute("updateMsg", "有給情報を更新しました。");
//		}

		boolean updated = yukyuInfoService.update1(yukyuInfoFormBean);
		if (updated == true) {
			model.addAttribute("updateMsg", "有給情報を更新しました。");
		}


		return "/ems/yukyuManage";

	}

}





