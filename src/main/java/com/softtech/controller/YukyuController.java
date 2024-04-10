package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.softtech.actionForm.YukyuDetail;
import com.softtech.service.YukyuService;

@Controller
public class YukyuController {

	@Autowired
	YukyuService yukyuService;

    public YukyuController(YukyuService yukyuService) {
        this.yukyuService = yukyuService;
    }

    @PostMapping("/update/{employeeID}/{nendo}")
    public String showYukyuTable(Model model) {
        // 从数据库中检索所有的yukyu记录
        //List<YukyuDetail> yukyuList = yukyuService.findAll();
    	List<YukyuDetail> yukyuList = new ArrayList<YukyuDetail>();

    	YukyuDetail yukyuDetail1 = new YukyuDetail();
    	yukyuDetail1.setEmployeeID("11");
    	yukyuDetail1.setNendo("2222");
    	yukyuDetail1.setTotalDay("33");
    	yukyuDetail1.setUsedDay("34");

    	yukyuList.add(yukyuDetail1);
        // 将检索到的数据传递到HTML模板
        model.addAttribute("yukyuList", yukyuList);

        // 返回HTML模板的名称（假设HTML文件位于templates文件夹下）
        return "/ems/yukyuManage";
    }
}
