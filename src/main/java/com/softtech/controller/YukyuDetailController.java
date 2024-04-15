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

import com.softtech.actionForm.YukyuDetail;
import com.softtech.service.YukyuDetailService;
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

//    @RequestMapping("/yukyu")
//    public String getYukyuDetail(Model model) {
//        List<YukyuDetail> yukyuDetail = yukyuDetailService.findAll();
//        model.addAttribute("detail", yukyuDetail);
//        return "ems/yukyuManage";
//    }

	//画面初期化
    @RequestMapping("/yukyu")
    public String toYukyuList(Model model,HttpSession session) {
        //自分社員IDを取る
    	String employeeID=(String) session.getAttribute("userEmoplyeeID");

//    	//有給管理テーブルからデータ取得
//        List<YukyuDetail> yukyuDetailList = yukyuDetailService.queryEmployeeID(employeeID);
//        model.addAttribute("yukyuDetailListl", yukyuDetailList);

    	// employeeIDがnullでない場合のみ有給管理テーブルからデータを取得
        if (employeeID != null) {
            YukyuDetail yukyuDetailList = yukyuDetailService.queryYukyuDetail(employeeID);

            // yukyuDetailListがnullでない場合のみモデルに追加
            if (yukyuDetailList != null) {
                model.addAttribute("detail", yukyuDetailList);
            }
        }
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
			@Validated @ModelAttribute("detail") YukyuDetail yukyuDetail,
			BindingResult errors,
			Model model, HttpSession session) throws ParseException {
		// Validationチェックエラー時、エラー情報表示
		if (errors.hasErrors()) {
			model.addAttribute("detail", yukyuDetail);
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
		String employeeID=(String) session.getAttribute("userEmoplyeeID");
		YukyuDetail yukyuDetailp = yukyuDetailService.queryYukyuDetail(employeeID);

		// 画面再表示設定
		yukyuDetail = yukyuDetailService.resetToUI(yukyuDetailp);
		model.addAttribute("detail", yukyuDetail);

		return "/ems/yukyuManage";

	}

}





