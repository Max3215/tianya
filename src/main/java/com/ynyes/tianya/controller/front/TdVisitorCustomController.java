package com.ynyes.tianya.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdVisitorCustom;
import com.ynyes.tianya.service.TdVisitorCustomService;

/**
 * gl
 * 游客定制Controller
 *
 */
@Controller
@RequestMapping("/visitorCustom")
public class TdVisitorCustomController{
	
	@Autowired
	private TdVisitorCustomService tdVisitorCustomService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> content(String month, String place, String totalDays, String totalPersons, String telephone, ModelMap map, HttpServletRequest req){
		Map<String, Object> backInfo = new HashMap<>(); 
		backInfo.put("success", false);
		TdVisitorCustom tvc = new TdVisitorCustom();
		if(month != null && !month.equals("0")){
			tvc.setMonth(month);
		}else{
			tvc.setMonth("未填写");
		}
		if(place != null){
			tvc.setPlace(place);
		}else{
			tvc.setPlace("未填写");
		}
		if(totalDays != null){
			tvc.setTotalDays(totalDays);
		}else{
			tvc.setTotalDays("未填写");
		}
		if(totalPersons != null){
			tvc.setTotalPersons(totalPersons);
		}else{
			tvc.setTotalPersons("未填写");
		}
		tvc.setTelephone(telephone);
		tvc.setCustomTime(new Date());
		String content = "我想在" + month + "月, 去" + place + "玩" +  totalDays + "天, 总共有" + totalPersons + "人, 联系电话: " + telephone;
		tvc.setContent(content);
		tvc.setAreadyCheck(false);
		tdVisitorCustomService.save(tvc);
		
		
		backInfo.put("success", true);
		return backInfo;
	}
        
	    
}
