package com.softtech.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.entity.Transport;
import com.softtech.service.TransportService;
import com.softtech.service.WorkInfoService;


@Controller
public class TransportController<WorkInfoComment>
{
	@Autowired
	private TransportService transportService;
	@Autowired
	private WorkInfoService workinfoService;


	@RequestMapping("/transport-workinfo")
	public String insertTransport(HttpServletRequest request,HttpSession session,@RequestParam("file") MultipartFile file,Model model) throws JsonMappingException, JsonProcessingException{
		 model.addAttribute("state", "007");
		if (!file.isEmpty()){
	        try {
	                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:/"+file.getOriginalFilename())));
	                out.write(file.getBytes());
	                out.flush();
	                out.close();
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	                model.addAttribute("uploadFile", "001");
	            } catch (IOException e) {
	                e.printStackTrace();
	                model.addAttribute("uploadFile", "001");
	            }
            	model.addAttribute("uploadFile", "111");
		}
		Map<String,String> mapper = new HashMap();
		mapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));
		Map<String,String[]> map = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			if(entry.getKey().equals("workStartDay")||entry.getKey().equals("workEndDay")){
				mapper.put(entry.getKey(),entry.getValue()[0].replace("-",""));
				continue;
			}
			if(entry.getKey().equals("workMonth")){
				mapper.put(entry.getKey(),entry.getValue()[0].replace("/",""));
				continue;
			}
			mapper.put(entry.getKey(), entry.getValue()[0]);
		}
		try{
			int upWork = workinfoService.uploadWorkInfo(mapper);
			if(upWork>=0){
				model.addAttribute("uploadInfo", "111");
			}
		}catch(Exception e){
			model.addAttribute("uploadInfo", "001");
		}
		try
			{
				int uptransport = transportService.uploadTransport(mapper);
				if(uptransport>=0)
				{
					model.addAttribute("upTransportInfo", "111");

				}
			}catch(Exception e){
				model.addAttribute("upTransportInfo", "001");
			}

		Map<String,String> sportMapper = new HashMap();
		sportMapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));
		Transport transport = transportService.queryTransport(sportMapper);
		model.addAttribute("transport", transport);
		return "/ems/transpirt";
	}

	@RequestMapping("/workdetail")
	public String Workdetail(HttpServletRequest request,HttpSession session,Model model){
		Map<String,String> sportMapper = new HashMap();
		sportMapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));
		Transport transport = transportService.queryTransport(sportMapper);
		if(transport == null) {
			transport = new Transport();
		}
		model.addAttribute("transport", transport);
		return "/ems/transpirt";
	}
}