package com.ynyes.tianya.controller.management;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdOutMoneyItemService;
import com.ynyes.tianya.service.TdStationCenterStaffTravelLineService;

/**
 * 后台客户管理控制器
 * @author gl
 *
 */
@Controller
@RequestMapping(value="/Verwalter/travelLineItem")
public class TdTravelLineItemController {
	
	@Autowired
	TdStationCenterStaffTravelLineService tdStationCenterStaffTravelLineService;
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map clientList(String itemId){
		System.out.println();
		tdStationCenterStaffTravelLineService.deleteTdStationCenterStaffTravelLine(Long.parseLong(itemId));
		return null;
	}
	
}
