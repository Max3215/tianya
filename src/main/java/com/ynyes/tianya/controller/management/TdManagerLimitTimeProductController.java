package com.ynyes.tianya.controller.management;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ynyes.tianya.entity.TdKeywords;
import com.ynyes.tianya.entity.TdLimitTimeProduct;
import com.ynyes.tianya.service.TdLimitTimeProductService;

/**
 * 后台关键词管理控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value = "/Verwalter/limitTimeProduct")
public class TdManagerLimitTimeProductController {

	@Autowired
	TdLimitTimeProductService tdLimitTimeProductService;

	@RequestMapping(value = "/list")
	public String limitTimeProductList(ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}

		List<TdLimitTimeProduct> tdLimitTimeProductList = tdLimitTimeProductService.findAll();
		map.addAttribute("tdLimitTimeProductList", tdLimitTimeProductList);
		return "/site_mag/limit_time_product_list";
	}
}
