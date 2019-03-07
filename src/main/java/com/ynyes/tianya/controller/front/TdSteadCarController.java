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
@RequestMapping("/steadCar")
public class TdSteadCarController{
	
	@Autowired
	private TdReserverCarService tdReserverCarService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> content(String name, String driverName, String phone, String email, String qq, String note, ModelMap map, HttpServletRequest req){
		Map<String, Object> backInfo = new HashMap<>(); 
		backInfo.put("success", false);
		TdReserveCar trc = new TdReserveCar();
		trc.setName(name);
		trc.setDriverName(driverName);
		trc.setPhone(phone);
		trc.setEmail(email);
		trc.setQq(qq);
		trc.setNote(note);
		trc.setType(1);
		tdReserverCarService.save(trc);
		
		//发送短信通知后台工作人员
        String message = "您有新的代驾业务，预定人为 :"+trc.getName();
        SmsUtils.smsSend(SmsUtils.RENT_PHONE, message);
		
		backInfo.put("success", true);
		return backInfo;
	}
        
	    
}
