package com.ynyes.tianya.controller.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdReserveCar;
import com.ynyes.tianya.service.TdReserverCarService;
import com.ynyes.tianya.util.SmsUtils;

/**
 * gl
 * 游客定制Controller
 *
 */
@Controller
@RequestMapping("/reserveCar")
public class TdReserveCarController{
	
	@Autowired
	private TdReserverCarService tdReserverCarService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> content(String name, String phone, String takeCarTime, String backCarTime, String getCarPlace, String backCarPlace, ModelMap map, HttpServletRequest req){
		Map<String, Object> backInfo = new HashMap<>(); 
		backInfo.put("success", false);
		TdReserveCar trc = new TdReserveCar();
		trc.setName(name);
		trc.setPhone(phone);
		trc.setTakeCarTime(takeCarTime);
		trc.setBackCarTime(backCarTime);
		trc.setGetCarPlace(getCarPlace);
		trc.setBackCarPlace(backCarPlace);
		trc.setType(0);
		trc.setIsCheck(false);
		tdReserverCarService.save(trc);
		
		//发送短信通知后台工作人员
        String message = "您有新的约租业务，预定人为 :"+trc.getName();
        SmsUtils.smsSend(SmsUtils.RENT_PHONE, message);
		backInfo.put("success", true);
		return backInfo;
	}
        
	    
}
