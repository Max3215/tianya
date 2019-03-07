package com.ynyes.tianya.controller.management;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.service.TdOutMoneyItemService;

/**
 * 后台客户管理控制器
 * @author gl
 *
 */
@Controller
@RequestMapping(value="/Verwalter/outMoneyItem")
public class TdOutMoneyItemController {
	
	@Autowired
	TdOutMoneyItemService tdOutMoneyItemService;
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map clientList(String itemId){
		System.out.println();
		tdOutMoneyItemService.deleteOutMoneyItem(Long.parseLong(itemId));
		return null;
	}
	
}
