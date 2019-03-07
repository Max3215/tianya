package com.ynyes.tianya.controller.front;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emay.channel.httpclient.SDKHttpClient;
import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdDemand;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdDemandService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.util.SmsUtils;

/**
 * 私家定制信息保存
 * 
 * @author lulu
 *
 */
@Controller
public class TdPrivateController {
	@Autowired
	TdCommonService tdCommonService;

	@Autowired
	TdDemandService tdDemandService;

	@Autowired
	TdGoodsService tdGoodsService;

	@Autowired
	TdAdTypeService tdAdTypeService;

	@Autowired
	TdAdService tdAdService;

	@Autowired
	TdUserService tdUserService;

	// 邮轮定制跳转
	@RequestMapping(value = "/ship/make", method = RequestMethod.GET)
	public String ship(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			//return "redirect:/login";
		}
		// 设置网站基本信息
		tdCommonService.setCommon(map, req);
		// 私家定制轮播广告
		TdAdType adType5 = tdAdTypeService.findByTitle("私家定制轮播广告");
		// 查询最顶端的那个广告的显示
		if (null != adType5) {
			map.addAttribute("big_scroll_ad_list5",
					tdAdService.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType5.getId()));
		}

		List<TdGoods> goodsList = tdGoodsService
				.findByCategoryIdTreeContainingAndIsOnSaleTrueAndIsHotTrueOrderBySortIdAsc("[1]");
		map.addAttribute("goods_list", goodsList);

		return "/client/custom_ship_index";
	}

	// 普通定制
	@RequestMapping(value = "/private/make", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchList(HttpServletRequest req, ModelMap map, String reachPort, String totalPeople,
			String groupSaleStartTime, String groupSaleStopTime, String way, String hotel, String mobile, String email,
			String money, String remark, String privateType) {
		String username = (String) req.getSession().getAttribute("username");
		Map<String, Object> res = new HashMap<String, Object>();
		if (null == username) {
			//res.put("code", 1);
			//return res;
		}

		if (reachPort.equals("您的目的地")) {
			reachPort = "";
		}
		if (totalPeople.equals("出行人数")) {
			totalPeople = "";
		}
		if (groupSaleStartTime.equals("出发日期")) {
			groupSaleStartTime = "";
		}
		if (groupSaleStopTime.equals("返程日期")) {
			groupSaleStopTime = "";
		}
		if (way.equals("出发方式")) {
			way = "";
		}
		if (mobile.equals("您的联系电话")) {
			mobile = "";
		}
		if (email.equals("您的邮箱")) {
			email = "";
		}
		if (money.equals("请输入你的最大支付限额")) {
			money = "99999999.00";
			res.put("money", money);
		}
		if (remark.equals("特殊要求：如：单人房、双人房")) {
			remark = "";
		}
		res.put("money", money);
		TdDemand tdDemand = new TdDemand();
		tdDemand.setEmail(email);
		tdDemand.setGroupSaleStartTime(groupSaleStartTime);
		tdDemand.setGroupSaleStopTime(groupSaleStopTime);
		tdDemand.setHotel(hotel);
		tdDemand.setMobile(mobile);
		tdDemand.setMoney(money);
		tdDemand.setReachPort(reachPort);
		tdDemand.setRemark(remark);
		tdDemand.setStatusId(0L);
		tdDemand.setIsReplied(false);
		tdDemand.setName(username);
		tdDemand.setWay(way);
		tdDemand.setTime(new Date());
		tdDemand.setTotalPeople(totalPeople);
		tdDemand.setPrivateType(privateType);
		tdDemand.setStatus(1);
		tdDemandService.save(tdDemand);

		// 发送短信通知后台工作人员
		String message = "您有新的私人定制业务，预定人为 :" + tdDemand.getName()+",预定出现时间："+groupSaleStartTime+",目的地为："+reachPort;
		SmsUtils.smsSend(SmsUtils.PRIVATE_PHONE, message);

		res.put("code", 0);
		return res;
	}

	// 邮轮定制
	@RequestMapping(value = "/private/make1", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchList11(HttpServletRequest req, ModelMap map, String reachPort, String totalPeople,
			String groupSaleStartTime, String groupSaleStopTime, String way, String hotel, String mobile, String email,
			String money, String remark) {
		String username = (String) req.getSession().getAttribute("username");
		Map<String, Object> res = new HashMap<String, Object>();
		if (null == username) {
			res.put("code", 1);
			return res;
		}

		if (totalPeople.equals("出行人数")) {
			totalPeople = "";
		}
		if (groupSaleStartTime.equals("出发日期")) {
			groupSaleStartTime = "";
		}
		if (groupSaleStopTime.equals("返程日期")) {
			groupSaleStopTime = "";
		}
		if (way.equals("出发方式")) {
			way = "";
		}
		if (mobile.equals("您的联系电话")) {
			mobile = "";
		}
		if (email.equals("您的邮箱")) {
			email = "";
		}
		if (money.equals("请输入你的最大支付限额")) {
			money = "99999999.00";
			res.put("money", money);
		}
		if (remark.equals("特殊要求：如：单人房、双人房")) {
			remark = "";
		}
		res.put("money", money);
		TdDemand tdDemand = new TdDemand();
		tdDemand.setEmail(email);
		tdDemand.setGroupSaleStartTime(groupSaleStartTime);
		tdDemand.setGroupSaleStopTime(groupSaleStopTime);
		tdDemand.setHotel(hotel);
		tdDemand.setMobile(mobile);
		tdDemand.setMoney(money);
		tdDemand.setReachPort(reachPort);
		tdDemand.setRemark(remark);
		tdDemand.setIsReplied(false);
		tdDemand.setStatusId(1L);
		tdDemand.setName(username);
		tdDemand.setWay(way);
		tdDemand.setStatus(1);

		tdDemandService.save(tdDemand);
		// 发送短信通知后台工作人员
		String message = "您有新的私人定制业务，预定人为 :" + tdDemand.getName()+",预定出现时间："+groupSaleStartTime+",目的地为："+reachPort;
		SmsUtils.smsSend(SmsUtils.PRIVATE_PHONE, message);
		res.put("code", 0);
		return res;
	}

	@RequestMapping(value = "/private/getValidCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getValidCode2(HttpServletRequest request, String m) {
		Map<String, String> info = new HashMap<>();
		String username = (String) request.getSession().getAttribute("username");
		TdUser tdUser = tdUserService.findByUsername(username);
//		String mobile = tdUser.getMobile();
		info.put("status", "ok");
		// 产生四位随机数
		Random random = new Random();
		String psmscode = String.format("%04d", random.nextInt(9999));
		request.getSession().setAttribute("PSMSCODE", psmscode);
		// 发送短信验证码
		String message = "您的验证码为：" + psmscode;
		if(m != null && !m.equals("")){
			SmsUtils.smsSend(m, message);
		}
		return info;
	}
	
	@RequestMapping(value = "/private/validateCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getValidCode(HttpServletRequest request, String mcode) {
		String psmscode = (String)request.getSession().getAttribute("PSMSCODE");
		Map<String, String> info = new HashMap<>();
		if(!(psmscode != null && psmscode.equals(mcode))){
			info.put("pass", "no");
		}else{
			info.put("pass", "yes");
		}
		return info;
	}
	
	

}
