package com.ynyes.tianya.controller.front;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdCoupon;
import com.ynyes.tianya.entity.TdCouponType;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdCouponService;
import com.ynyes.tianya.service.TdCouponTypeService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.util.SmsUtils;

import freemarker.core.ParseException;

/**
 * lulu
 * 汽车租赁
 *
 */
@Controller
@RequestMapping("/rent")
public class TdRentCarController extends AbstractPaytypeController{
	
	@Autowired
	TdOrderService tdOrderService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdCouponService tdCouponService;
	@Autowired
	private TdCouponTypeService tdCouponTypeService;
	
	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;
	
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdUserLevelService tdUserLevelService;
	
	
	
	//临租
	@RequestMapping("/car_short")
	public String rentCarShort(HttpServletRequest req,Device device, ModelMap map,Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}

		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//选择汽车
		//Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("临租服务",page,8);
		//map.addAttribute("rent_car_page11", tdGoods11);
		List<TdGoods> tdGoodsAll =  tdGoodsService.searchCarLZAll();
		map.addAttribute("tdGoodsAll", tdGoodsAll);
		//分开
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue("临租服务",page,8);
		Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("临租服务", page, 8);
		
		map.addAttribute("rent_car_page", tdGoods);
		req.getSession().removeAttribute("price1");
		
		map.addAttribute("page", page);
		return "/client/rent_car_short";
	}
	
	// 长租
	@RequestMapping("/car_long")
	public String rentCarLong(HttpServletRequest req,Device device, ModelMap map,Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}

		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//选择汽车
		Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("长租服务",page,8);
		map.addAttribute("rent_car_page11", tdGoods11);
		//分开
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue("长租服务",page,8);
		Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("长租服务", page, 8);
		map.addAttribute("rent_car_page", tdGoods);
		req.getSession().removeAttribute("price1");
		map.addAttribute("type", "long");
		
		map.addAttribute("page", page);
		//return "/client/rent_car_long";
		return "/client/rent_car_22";
	}
	
	
	//包租
	@RequestMapping("/car2")
	public String car2(HttpServletRequest req,Device device, ModelMap map,Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}
		
		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//汽车租赁
		Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("汽车租赁",page,8);
		map.addAttribute("rent_car_page11", tdGoods11);
		//分开
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("包车服务",page,8);
		Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("包车服务", page, 8);
		List<TdGoods> goodsList = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("包车服务");
		List<String> subtitleList = new ArrayList<String>();
		if(goodsList != null){
			for(TdGoods tg : goodsList){
				subtitleList.add(tg.getSubTitle());
			}
		}
		map.addAttribute("subtitleList", subtitleList);
		
		map.addAttribute("rent_car_page", tdGoods);
		map.addAttribute("type", "car");
		map.addAttribute("page", page);
		return "/client/rent_car_2";
	}
	
	//约租
	@RequestMapping("/car3")
	public String car3(HttpServletRequest req,Device device, ModelMap map,Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}
		
		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//汽车租赁
		//Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("汽车租赁",page,8);
		//map.addAttribute("rent_car_page11", tdGoods11);
		//分开
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("包车服务",page,8);
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("包车服务", page, 8);
		//map.addAttribute("rent_car_page", tdGoods);
		return "/client/rent_car3";
	}
	
	
	//代驾
	@RequestMapping("/driveStead")
	public String steadDrive(HttpServletRequest req,Device device, ModelMap map,Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}

		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//选择汽车
//		Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("临租服务",page,8);
//		map.addAttribute("rent_car_page11", tdGoods11);
		//分开
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue("临租服务",page,8);
		Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("代驾服务", page, 8);
		
		map.addAttribute("rent_car_page", tdGoods);
		req.getSession().removeAttribute("price1");
		return "/client/stead_drive";
	}
	
	//代驾产品搜索 subTitle为驾龄，leavePort为驾照，title为司机名字
	@RequestMapping("/driveStead/search")
	public String steadDriveSearch(HttpServletRequest req, Device device, String subTitle, String leavePort, String title, ModelMap map, Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}

		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("代驾服务", page, 8);
		Page<TdGoods> tdGoods = null;
		if(!"".equals(subTitle) && "".equals(leavePort) && "".equals(title)){
			//驾龄
			tdGoods = tdGoodsService.findBySubTitle(subTitle, page, 8);
		}else if("".equals(subTitle) && !"".equals(leavePort) && "".equals(title)){
			//驾照
			tdGoods = tdGoodsService.findByLeavePort(leavePort, page, 8);
		}else if("".equals(subTitle) && "".equals(leavePort) && !"".equals(title)){
			//司机
			tdGoods = tdGoodsService.findByTitle(title, page, 8);
		}else if(!"".equals(subTitle) && !"".equals(leavePort) && "".equals(title)){
			// 驾龄+驾照
			tdGoods = tdGoodsService.findBySubTitleAndLeavePort(subTitle, leavePort, page, 8);
		}else if("".equals(subTitle) && !"".equals(leavePort) && !"".equals(title)){
			// 驾照+司机
			tdGoods = tdGoodsService.findLeavePortAndTitle(leavePort, title, page, 8);
		}else if(!"".equals(subTitle) && "".equals(leavePort) && !"".equals(title)){
			// 驾龄+司机
			tdGoods = tdGoodsService.findSubTitleAndTitle(subTitle, title, page, 8);
		}else if(!"".equals(subTitle) && !"".equals(leavePort) && !"".equals(title)){
			// 驾龄+驾照+司机
			tdGoods = tdGoodsService.findSubTitleAndLeavePortAndTitle(subTitle, leavePort, title, page, 8);
		}else{
			//无条件
			tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("代驾服务", page, 8);
		}
		
		//Page<TdGoods> tdGoods = tdGoodsService.findByLeavePortAndCategoryTitleAndIsOnSaleTrueOrReachPortAndCategoryTitleAndIsOnSaleTrue(leavePort, "代驾服务", reachPort, "代驾服务", page, 8);
		
		map.addAttribute("rent_car_page", tdGoods);
		req.getSession().removeAttribute("price1");
		return "/client/stead_drive";
	}
	
	
	
	
	//搜索查询
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest req,ModelMap map,String subTitle,Double price1,Double price2,Long id11,Integer page) throws ParseException {
		tdCommonService.setCommon(map, req);
		
		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//回显
		map.addAttribute("price1", price1);
		map.addAttribute("price2", price2);
		map.addAttribute("subTitle", subTitle);
		map.addAttribute("id11", id11);
		
		/*if(price1.equals("输入您期望的最低金额")){
			price1="0";
			Double price11 = Double.parseDouble(price1);
		}
		if(price2.equals("输入您期望的最高金额")){
			price2="99999999";
			Double price22 = Double.parseDouble(price2);
		}*/
		if(subTitle.equals("输入您想要的车型")){
			subTitle="";
		}
		
		//分页
		if(null==page){
			page=0;
		}
		Page<TdGoods> tdGoods = tdGoodsService.searchCarLZ(subTitle, price1, price2, id11, page, 8);
		map.addAttribute("rent_car_page", tdGoods);
		
		List<TdGoods> tdGoodsAll =  tdGoodsService.searchCarLZAll();
		map.addAttribute("tdGoodsAll", tdGoodsAll);
		/*
		Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue("汽车租赁",page,8);
		map.addAttribute("rent_car_page11", tdGoods11);
		
		if(id11==0L && subTitle.equals("") && price1.equals("输入您期望的最低金额") && price2.equals("输入您期望的最高金额")){
			Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeTrue("汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_short";
		}else if(null != id11 && id11 != 0L){
			Page<TdGoods> tdGoods = tdGoodsService.findById(id11,page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_short";
		}else if(!subTitle.equals("") && id11 == 0L && price1.equals("输入您期望的最低金额") && price2.equals("输入您期望的最高金额")){
			Page<TdGoods> tdGoods = tdGoodsService.findBySubTitleContainingAndCategoryTitle(subTitle,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_short";
		}else if(!price1.equals("输入您期望的最低金额") && price2.equals("输入您期望的最高金额")){
			Double price11 = Double.parseDouble(price1);
			Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceGreaterThanAndCategoryTitle(price11,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_short";
		}else if(price1.equals("输入您期望的最低金额") && !price2.equals("输入您期望的最高金额")){
			Double price22 = Double.parseDouble(price2);
			Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndCategoryTitle(price22,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_short";
		}else if(!price1.equals("输入您期望的最低金额") && !price2.equals("输入您期望的最高金额")){
			Double price11 = Double.parseDouble(price1);
			Double price22 = Double.parseDouble(price2);
			Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndSalePriceGreaterThanAndCategoryTitle(price22,price11,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_short";
		}*/
		
		
		return "/client/rent_car_short";
		
	}
	
	//搜索查询
	@RequestMapping(value = "/search2", method = RequestMethod.POST)
    public String search2(HttpServletRequest req,ModelMap map,String subTitle,String price1,String price2, String carType,Long id11,Integer page) throws ParseException {
		tdCommonService.setCommon(map, req);
		
		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		//回显
		map.addAttribute("price1", price1);
		map.addAttribute("price2", price2);
		map.addAttribute("subTitle", subTitle);
		map.addAttribute("carType", carType);
		map.addAttribute("id11", id11);
		
		/*if(price1.equals("输入您期望的最低金额")){
			price1="0";
			Double price11 = Double.parseDouble(price1);
		}
		if(price2.equals("输入您期望的最高金额")){
			price2="99999999";
			Double price22 = Double.parseDouble(price2);
		}*/
		if(subTitle.equals("输入您想要的车型")){
			subTitle="";
		}
		
		//分页
		if(null==page){
			page=0;
		}
		
		Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("汽车租赁",page,8);
		map.addAttribute("rent_car_page11", tdGoods11);
		
		if(id11==0L && subTitle!=null && subTitle.equals("") && price1!=null && price1.equals("输入您期望的最低金额") && price2!=null && price2.equals("输入您期望的最高金额")){
			Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_2";
		}else if(null != id11 && id11 != 0L){
			Page<TdGoods> tdGoods = tdGoodsService.findById(id11,page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_2";
		}else if(!subTitle.equals("") && id11 == 0L && price1.equals("输入您期望的最低金额") && price2.equals("输入您期望的最高金额")){
			Page<TdGoods> tdGoods = tdGoodsService.findBySubTitleContainingAndCategoryTitle1(subTitle,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_2";
		}else if(!price1.equals("输入您期望的最低金额") && price2.equals("输入您期望的最高金额")){
			Double price11 = Double.parseDouble(price1);
			Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceGreaterThanAndCategoryTitle1(price11,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_2";
		}else if(price1.equals("输入您期望的最低金额") && !price2.equals("输入您期望的最高金额")){
			Double price22 = Double.parseDouble(price2);
			Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndCategoryTitle1(price22,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_2";
		}else if(!price1.equals("输入您期望的最低金额") && !price2.equals("输入您期望的最高金额")){
			Double price11 = Double.parseDouble(price1);
			Double price22 = Double.parseDouble(price2);
			Page<TdGoods> tdGoods = tdGoodsService.findBySalePriceLessThanAndSalePriceGreaterThanAndCategoryTitle1(price22,price11,"汽车租赁",page,8);
			map.addAttribute("rent_car_page", tdGoods);
			return "/client/rent_car_2";
		}
		return "/client/rent_car_2";
	}
	
	
	
	//搜索查询
		@RequestMapping(value = "/search222", method = RequestMethod.POST)
	    public String search222(HttpServletRequest req,ModelMap map,String st,Integer page) throws ParseException {
			tdCommonService.setCommon(map, req);
			
			List<TdGoods> goodsList = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("包车服务");
			List<String> subtitleList = new ArrayList<String>();
			if(goodsList != null){
				for(TdGoods tg : goodsList){
					subtitleList.add(tg.getSubTitle());
				}
			}
			map.addAttribute("subtitleList", subtitleList);
			
			// 首页轮播广告
			TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
			//查询最顶端的那个广告的显示
			if (null != adType) {
				map.addAttribute("big_scroll_ad_list", tdAdService
						.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
			}
			
			//回显
			map.addAttribute("subTitle", st);
			//分页
			if(null==page){
				page=0;
			}
			
			Page<TdGoods> tdGoods = tdGoodsService.searchCar2(st, page, 8);
			map.addAttribute("rent_car_page", tdGoods);
			
			return "/client/rent_car_2";
		}
	
	//搜索查询
		@RequestMapping(value = "/search22", method = RequestMethod.POST)
	    public String search22(HttpServletRequest req,ModelMap map, String price1,String price2, String carType, Integer page) throws ParseException {
			tdCommonService.setCommon(map, req);
			
			// 首页轮播广告
			TdAdType adType = tdAdTypeService.findByTitle("汽车租赁广告");
			//查询最顶端的那个广告的显示
			if (null != adType) {
				map.addAttribute("big_scroll_ad_list", tdAdService
						.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
			}
			
			//回显
			map.addAttribute("price1", price1);
			map.addAttribute("price2", price2);
			map.addAttribute("carType", carType);
			
			/*if(price1.equals("输入您期望的最低金额")){
				price1="0";
				Double price11 = Double.parseDouble(price1);
			}
			if(price2.equals("输入您期望的最高金额")){
				price2="99999999";
				Double price22 = Double.parseDouble(price2);
			}*/
			
			//分页
			if(null==page){
				page=0;
			}
			
			Page<TdGoods> tdGoods11 = tdGoodsService.findByCategoryTitleAndIsOnSaleTrueAndCarTypeFalse("汽车租赁",page,8);
			map.addAttribute("rent_car_page11", tdGoods11);
			Double p1 = null;
			Double p2 = null;
			
			if(price1 != null && !price1.equals("") && !price1.equals("输入您期望的最低金额")){
				p1 = Double.parseDouble(price1);
			}
			if(price2 != null && !price2.equals("") && !price2.equals("输入您期望的最高金额")){
				p2 = Double.parseDouble(price2);
			}
			if(carType.equals("输入车型")){
				carType = null;
			}
			
			
			Page<TdGoods> tdGoods = tdGoodsService.searchCar(p1, p2, carType, page, 8);
			map.addAttribute("rent_car_page", tdGoods);
			if(tdGoods.getContent().size() == 0){
				map.addAttribute("noCar", true);
			}
			return "/client/rent_car_22";
		}
	
	//租汽车填写信息页面
	@RequestMapping(value = "/middle", method = RequestMethod.GET)
	public String order00(HttpServletRequest req, ModelMap map,Long goodsId) {
        
        tdCommonService.setCommon(map, req);
        
        TdGoods goods = tdGoodsService.findOne(goodsId);
        map.addAttribute("goods", goods);
		
		return "/client/rent_middle";
	}
	
	//包客车详情页面
	@RequestMapping(value = "/middle2", method = RequestMethod.GET)
	public String order002(HttpServletRequest req, ModelMap map,Long goodsId) {
		
		
		tdCommonService.setCommon(map, req);
		
		TdGoods goods = tdGoodsService.findOne(goodsId);
		map.addAttribute("goods", goods);
		
		return "/client/rent_middle_2";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order1(HttpServletRequest req, ModelMap map,Long goodsId,Long days, String groupSaleStartTime, String qcdd, String hcdd, String qcdz, String hcdz) {
		String username = (String) req.getSession().getAttribute("username");
		Boolean isQQLogin = Boolean.parseBoolean((String)req.getSession().getAttribute("isQQLogin"));
        if (null == username || (isQQLogin != null && isQQLogin) ) {
        	req.getSession().invalidate();
            return "redirect:/login";
        }
        map.addAttribute("groupSaleStartTime", groupSaleStartTime);
        tdCommonService.setCommon(map, req);
        
        if(null!=days){
        	map.addAttribute("days", days);
        }
        
        TdGoods goods = tdGoodsService.findOne(goodsId);
        goods.setQcdd(qcdd);
        goods.setHcdd(hcdd);
        goods.setQcdz(qcdz);
        goods.setHcdz(hcdz);
        goods = tdGoodsService.save(goods);
        map.addAttribute("goods", goods);
        
        //优惠劵使用
        List<TdCoupon> couponList = tdCouponService.findByUsername(username);
        Date date = new Date();
        //对过期优惠劵直接删除
        for(int x=0;x<couponList.size();x++){
        	TdCoupon coupon = couponList.get(x);
        	if(date.after(coupon.getExpireTime())||coupon.getGetTime().equals(date)){
        		couponList.remove(coupon);
        		tdCouponService.delete(coupon.getId());
        	}
        }
        //分类满减卷处理
        for(int i=0;i<couponList.size();i++){
        	TdCoupon coupon = couponList.get(i);
        	if(coupon.getTypeCategoryId()==1L){
        		TdCouponType couponType = tdCouponTypeService.findOne(coupon.getTypeId());
        		//29L表示汽车租赁的订单
        		if(couponType.getProductTypeId()!=29L){
        			couponList.remove(coupon);
        		}
        	}
        	
        }
        
        map.addAttribute("coupon_list", couponList);
		
		return "/client/rent_order";
	}
	//客车包租
	@RequestMapping(value = "/order1", method = RequestMethod.GET)
	public String order2(HttpServletRequest req,
						 ModelMap map,
						 Long goodsId,
						 Long days,
						 String clientName,
						 String mobile,
						 String email,
						 String qcdd,
						 String hcdd,
						 String qcdz,
						 String hcdz
						 ) {
		String username = (String) req.getSession().getAttribute("username");
        if (null == username) {
            return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        TdGoods goods = tdGoodsService.findOne(goodsId);
        goods.setQcdd(qcdd);
        goods.setHcdd(hcdd);
        goods.setQcdz(qcdz);
        goods.setHcdz(hcdz);
        goods = tdGoodsService.save(goods);
        TdOrder order = new TdOrder();
        order.setUsername(username);
        order.setShippingName(clientName);
        order.setShippingPhone(mobile);
        order.setLeavePort(email);
        order.setTypeId(4L);
        //订单下单时间
        Date date = new Date();
        order.setOrderTime(date);
        
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String curStr = sdf.format(current);
        Random random = new Random();
        // 订单号
        order.setOrderNumber("P" + curStr
                + leftPad(Integer.toString(random.nextInt(999)), 3, "0"));
        order.setGoodsId(goodsId);
        order.setShopId(days);
        order.setStatusId(2L);
        order.setOrderType("客车包租");
        order.setGoodsTitle(goods.getTitle());
        order.setTotalPrice(goods.getSalePrice());
        order.setLeavePort(qcdd+qcdz);
        order.setReachPort(hcdd+hcdz);
        tdOrderService.save(order);
        
        //发送短信通知后台工作人员
        String message = "您有新的汽车租赁业务，订单编号为 :"+order.getOrderNumber()+",姓名:" + clientName + ",手机:" + mobile;
         SmsUtils.smsSend(SmsUtils.RENT_PHONE, message);
        
		return "/client/bus_submit_success";
	}
	//汽车租赁
	@RequestMapping(value = "/order/submit", method = RequestMethod.POST)
	public String order2(HttpServletRequest req,
						ModelMap map,
						String rent1,
						String shippingName,
						String shippingPhone,
						String leavePort,
						String appointmentTime,
						String returnTime,
						Double totalPrice,
						Double includePrice,
						Long days,
						Long couponId,
						Long goodsId
						) throws java.text.ParseException {
		String username = (String) req.getSession().getAttribute("username");
        if(null==username){
        	return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        if(null!=couponId&&!couponId.equals("")){
        	tdCouponService.delete(couponId);
        }
        
        TdGoods goods = tdGoodsService.findOne(goodsId);
        
        TdOrder order = new TdOrder();
        
        order.setUsername(username);
        order.setShippingName(shippingName);
        order.setShippingPhone(shippingPhone);
        order.setLeavePort(leavePort);
        order.setOrderType("汽车租赁");
        order.setTypeId(4L);//4表示汽车租赁订单
        order.setGoodsTitle(goods.getTitle());
        order.setGoodsId(goodsId);
        if(includePrice != null){
        	order.setIncludePrice(includePrice);
        }
        //取车时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date appointmentTime1 = sdf1.parse(appointmentTime);
        order.setAppointmentTime(appointmentTime1);
        //还车时间
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date returnTime1 = sdf2.parse(returnTime);
        order.setReturnTime(returnTime1);
        //订单下单时间
        Date date = new Date();
        order.setOrderTime(date);
        
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String curStr = sdf.format(current);
        Random random = new Random();
        // 订单号
        order.setOrderNumber("P" + curStr
                + leftPad(Integer.toString(random.nextInt(999)), 3, "0"));
        order.setGoodsId(goodsId);
        order.setTotalPrice(totalPrice);
        
        
     // 依据等级确定打折额度
 		Long levelId = tdUserService.findByUsername(username).getUserLevelId();
 		Double discountRatio = null;
 		if (levelId != null) {
 			TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
 			if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
 				discountRatio = tdUserLevelService.findOne(levelId)
 						.getDiscountRatio();
// 				if (discountRatio != null) {
// 					map.addAttribute("discountRatio", discountRatio);
// 				}
 			}
 		}
 		// 修改订单总价
 		if (discountRatio != null) {
 			order.setTotalPrice(totalPrice * discountRatio);
 		}
        
        order.setStatusId(2L);//待付款订单
        order.setDeliveryPerson(rent1);
        order.setShopId(days);
        tdOrderService.save(order);
        map.addAttribute("order", order);
        setPayTypes(map, true, false, req);
        
        //发送短信通知后台工作人员
        String message = "您有新的汽车租赁业务，订单编号为 :"+order.getOrderNumber()+",姓名:" + shippingName + ",手机:" + shippingPhone;
       
        SmsUtils.smsSend(SmsUtils.RENT_PHONE, message);
        
		return "/client/order_ship_5";
	}
	
	
}
