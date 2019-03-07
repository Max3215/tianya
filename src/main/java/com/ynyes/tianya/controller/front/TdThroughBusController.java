package com.ynyes.tianya.controller.front;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdCoupon;
import com.ynyes.tianya.entity.TdCouponType;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.entity.TdUserVisitor;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdCouponService;
import com.ynyes.tianya.service.TdCouponTypeService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.service.TdUserVisitorService;

import freemarker.core.ParseException;

/**
 * lulu
 * 旅游直通车
 *
 */
@Controller
@RequestMapping("/through")
public class TdThroughBusController extends AbstractPaytypeController{
	
	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	private TdCouponService tdCouponService;
	@Autowired
	private TdCouponTypeService tdCouponTypeService;
	
	@Autowired
	TdUserVisitorService tdUserVisitorService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;
	
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdUserLevelService tdUserLevelService;

	@RequestMapping("/bus/{type}")
	public String ship(@PathVariable Long type,
			HttpServletRequest req,Device device, ModelMap map,Integer page) {
		tdCommonService.setCommon(map, req);
		
		if(null==page){
			page=0;
		}

		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("旅游直通车轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		if(null == type)
		{
			//旅游直通车
			Page<TdGoods> tdGoods = tdGoodsService.findByCategoryTitleAndIsOnSaleTrue("旅游直通车",page,12);
			map.addAttribute("through_bus_page", tdGoods);
		}else{
			Page<TdGoods> tdGoods = tdGoodsService.findByCategoryIdAndIsOnSaleTrue(type, page, 12);
			map.addAttribute("through_bus_page", tdGoods);
			map.addAttribute("type", type);
		}
		return "/client/through_bus";
	}
	//搜索查询
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest req,ModelMap map,String onSaleTime,String leavePort,String reachPort,Integer page) throws ParseException {
		tdCommonService.setCommon(map, req);
		
		// 旅游直通车轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("旅游直通车轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		if(onSaleTime.equals("出发日期")){
			onSaleTime="";
		}
		if(leavePort.equals("出发地点")){
			leavePort="";
		}
		if(reachPort.equals("目标地点")){
			reachPort="";
		}
		//分页
		if(null==page){
			page=0;
		}
		if(onSaleTime.equals("")){
			Page<TdGoods> tdGoods = tdGoodsService.findByReachPortContainingAndLeavePortContainingAndCategoryTitleAndIsOnSaleTrue(reachPort,leavePort,"旅游直通车",page,12);
			map.addAttribute("through_bus_page", tdGoods);
			//回显
			map.addAttribute("leavePort", leavePort);
			map.addAttribute("onSaleTime", onSaleTime);
			map.addAttribute("reachPort", reachPort);
			return "/client/through_bus";
		}
		if(!onSaleTime.equals("")){
			//时间转换
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date date;
			try {
				date = sim.parse(onSaleTime);
			} catch (java.text.ParseException e) {
				return "/client/error_404";
			}
	    	if(leavePort.equals("")&&reachPort.equals("")){
		    	Page<TdGoods> tdGood = tdGoodsService.findByOnSaleTime(date,page,12);
				map.addAttribute("through_bus_page", tdGood);
				map.addAttribute("onSaleTime", onSaleTime);
				return "/client/through_bus";
	    	}
	    	if(leavePort.equals("")&&!reachPort.equals("")){
		    	Page<TdGoods> tdGood = tdGoodsService.findByOnSaleTimeAndReachPort(date,reachPort,page,12);
				map.addAttribute("through_bus_page", tdGood);
				map.addAttribute("reachPort", reachPort);
				map.addAttribute("onSaleTime", onSaleTime);
				return "/client/through_bus";
	    	}
	    	if(!leavePort.equals("")&&reachPort.equals("")){
		    	Page<TdGoods> tdGood = tdGoodsService.findByOnSaleTimeAndLeavePort(date,leavePort,page,12);
				map.addAttribute("through_bus_page", tdGood);
				map.addAttribute("leavePort", leavePort);
				map.addAttribute("onSaleTime", onSaleTime);
				return "/client/through_bus";
	    	}
	    	if(!leavePort.equals("")&&!reachPort.equals("")){
	    		Page<TdGoods> tdGood = tdGoodsService.findByOnSaleTimeAndLeavePortAndReachPort(date,leavePort,reachPort,page,12);
	    		map.addAttribute("through_bus_page", tdGood);
	    		map.addAttribute("leavePort", leavePort);
	    		map.addAttribute("reachPort", reachPort);
	    		map.addAttribute("onSaleTime", onSaleTime);
	    		return "/client/through_bus";
	    	}
		}
		return "/client/error_404";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order1(HttpServletRequest req, ModelMap map,Long goodsId,Integer page) {
		String username = (String) req.getSession().getAttribute("username");
		Boolean isQQLogin = Boolean.parseBoolean((String)req.getSession().getAttribute("isQQLogin"));
        if (null == username || (isQQLogin != null && isQQLogin) ) {
        	req.getSession().invalidate();
            return "redirect:/login";
        }
        if(null==page){
        	page=0;
        }
        
        tdCommonService.setCommon(map, req);
        Page<TdUserVisitor> visitorPage = tdUserVisitorService.findByUsername(username,page,10);
        map.addAttribute("visitor_page", visitorPage);
        
        TdGoods goods = tdGoodsService.findOne(goodsId);
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
        		if(couponType.getProductTypeId()!=28L){
        			couponList.remove(coupon);
        		}
        	}
        	
        }
        
        map.addAttribute("coupon_list", couponList);
        
		return "/client/through_order";
	}
	
	@RequestMapping(value = "/order/submit", method = RequestMethod.POST)
	public String order2(HttpServletRequest req,
						ModelMap map,
						String orderType,
						String shippingName,
						String shippingPhone,
						String leavePort,
						String goodsTitle,
						Double totalPrice,
						Long goodsId,
						Long couponId,
						Long[] listId,
                        Integer[] listChkId,
                        Long ertongshu
						) {
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
        
        if(null!=listChkId){
        	Long x = order.getShopId();
        	for(Long i=0L;i<listChkId.length+1;i++){
        		x=i;
        	}
        	order.setShopId(x);
        }
        order.setErtongshu(ertongshu);
        order.setLeaveDate(goods.getLeaveDate());
        order.setUsername(username);
        order.setShippingName(shippingName);
        order.setShippingPhone(shippingPhone);
        order.setLeavePort(leavePort);
        order.setGoodsTitle(goodsTitle);
        order.setTypeId(5L);
        order.setOrderType("旅游直通车");
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
        
        order.setStatusId(2L);
        order.setOrderType(orderType);
        tdOrderService.save(order);
        map.addAttribute("order", order);
        setPayTypes(map, true, false, req);
        
		return "/client/order_ship_5";
	}
	
	/*@RequestMapping(value = "/user/order/submit", method = RequestMethod.GET)
	public String order3(HttpServletRequest req,ModelMap map,Long id){
		String username = (String) req.getSession().getAttribute("username");
        if(null==username){
        	return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
		TdOrder order = tdOrderService.findOne(id);
        
		map.addAttribute("order", order);
		setPayTypes(map, true, false, req);
    	return "/client/order_ship_5";
    }*/
}
