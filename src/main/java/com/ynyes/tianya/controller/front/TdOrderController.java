package com.ynyes.tianya.controller.front;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csii.payment.client.core.CebMerchantSignVerify;
import com.tianya.payment.ceb.CEBPayConfig;
import com.tianya.payment.ceb.PaymentChannelCEB;
import com.ynyes.tianya.entity.TdCartGoods;
import com.ynyes.tianya.entity.TdCartGoodsPs;
import com.ynyes.tianya.entity.TdCoupon;
import com.ynyes.tianya.entity.TdCouponType;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdGoodsPs;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdOrderGoods;
import com.ynyes.tianya.entity.TdOrderVisitor;
import com.ynyes.tianya.entity.TdPayRecord;
import com.ynyes.tianya.entity.TdPayType;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.entity.TdShippingAddress;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.entity.TdUserVisitor;
import com.ynyes.tianya.service.TdCartGoodsPsService;
import com.ynyes.tianya.service.TdCartGoodsService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdCouponService;
import com.ynyes.tianya.service.TdCouponTypeService;
import com.ynyes.tianya.service.TdGoodsPsService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdOrderGoodsService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdOrderVisitorService;
import com.ynyes.tianya.service.TdPayRecordService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdShippingAddressService;
import com.ynyes.tianya.service.TdTimePriceService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.service.TdUserVisitorService;

import net.sf.json.JSONObject;

/**
 * 订单
 *
 */
@Controller
@RequestMapping(value = "/order")
public class TdOrderController extends AbstractPaytypeController {

//	private static final String PAYMENT_ALI = "ALI";

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdUserLevelService tdUserLevelService;

	@Autowired
	private TdShippingAddressService tdShippingAddressService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdCouponService tdCouponService;
	@Autowired
	private TdCouponTypeService tdCouponTypeService;
	
	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdGoodsPsService tdGoodsPsService;

	@Autowired
	private TdCartGoodsPsService tdCartGoodsPsService;

	@Autowired
	private TdCartGoodsService tdCartGoodsService;

	@Autowired
	private TdUserVisitorService tdUserVisitorService;

	@Autowired
	private TdOrderService tdOrderService;

	@Autowired
	private TdOrderGoodsService tdOrderGoodsService;

	@Autowired
	private TdOrderVisitorService tdOrderVisitorService;

//	@Autowired
//	private PaymentChannelCEB payChannelCEB;

	@Autowired
	private TdPayRecordService payRecordService;
	
	@Autowired
	private TdTimePriceService tdTimePriceService;

	@RequestMapping("/{goodsId}")
	public String login(@PathVariable Long goodsId, String date,String price,Long ertongshu, Long chengrenshu,
			HttpServletRequest req, ModelMap map) throws Exception {
		String username = (String) req.getSession().getAttribute("username");
		Boolean isQQLogin = Boolean.parseBoolean((String)req.getSession().getAttribute("isQQLogin"));
        if (null == username || (isQQLogin != null && isQQLogin) ) {
        	req.getSession().invalidate();
            return "redirect:/login";
        }

		// if(null == username)
		// {
		// return "redirect:/login";
		// }

		boolean isLogin = true;

//		if (null == username) {
//			isLogin = false;
//			username = req.getSession().getId();
//		}

		tdCommonService.setCommon(map, req);

		if (null == goodsId) {
			return "/client/error_404";
		}

		req.getSession().setAttribute("orderGoodsId", goodsId);

		TdGoods goods = tdGoodsService.findOne(goodsId);
		TdProductCategory tdpcy = tdProductCategoryService.findOne(goods.getCategoryId());
		boolean isChuanpiao = false;
		if(tdpcy.getTitle().contains("船票")){
			isChuanpiao = true;
		}
		req.getSession().putValue("isChuanpiao", isChuanpiao);

		if (null == goods) {
			return "/client/error_404";
		}

		map.addAttribute("goods", goods);

		if (null != date) {
			req.getSession().setAttribute("date", date);
			map.addAttribute("leaveDate", date);
		}
		
		if(null!=price && !price.equals("")){
			Double price1 = Double.valueOf(price);
			map.addAttribute("price1", price1);
			req.getSession().setAttribute("price1", price1);
			req.getSession().setAttribute("totalPrice", price1);
			
		}
		if(null!=ertongshu && !ertongshu.equals("")){
			req.getSession().setAttribute("ertongshu", ertongshu);
		}
		if(null!=chengrenshu && !chengrenshu.equals("")){
			req.getSession().setAttribute("chengrenshu", chengrenshu);
		}

		TdProductCategory shipCat = tdProductCategoryService
				.findByTitle("邮轮俱乐部");
		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		TdProductCategory specialCat = tdProductCategoryService
				.findByTitle("特产商城");

		if (null != goods.getCategoryIdTree() && null != shipCat) {
			if (goods.getCategoryIdTree().contains("[" + shipCat.getId() + "]")) {
				
				List<TdCartGoods> oldList = null;
				oldList = tdCartGoodsService.findByGoodsIdAndUsername(goodsId,
						username);
				if (null == oldList || oldList.size() == 0) {
					TdCartGoods cartGoods = new TdCartGoods();

					cartGoods.setGoodsId(goodsId);
					cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
					cartGoods.setReachPort(goods.getReachPort());
					cartGoods.setPassPort(goods.getPassPort());
					cartGoods.setLeavePort(goods.getLeavePort());
					cartGoods.setGoodsTitle(goods.getTitle());
					if (null != date && !date.equals("")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date leaveDate = sdf.parse(date);
						cartGoods.setLeaveDate(leaveDate);
					}
					cartGoods.setQuantity(1L);
					cartGoods.setIsLoggedIn(isLogin);
					cartGoods.setUsername(username);
					cartGoods = tdCartGoodsService.save(cartGoods);
				}
				TdCartGoods cartGoods = tdCartGoodsService
						.findTopByGoodsIdAndUsername(goodsId, username);

				req.getSession().setAttribute("cartGoodsId", cartGoods.getId());

				map.addAttribute("cartGoods", cartGoods);
				map.addAttribute("totalPrice", cartGoods);
				if(isChuanpiao){
					return "redirect:/order/ship4";
				}
				return "/client/order_ship_1";
			}
		}
		
		// 跳转到目的地立即预定页面
		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
				List<TdCartGoods> oldList = null;
				oldList = tdCartGoodsService.findByGoodsIdAndUsername(goodsId,
						username);
				if (null == oldList || oldList.size() == 0) {
					TdCartGoods cartGoods = new TdCartGoods();

					cartGoods.setGoodsId(goodsId);
					cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
					cartGoods.setReachPort(goods.getReachPort());
					cartGoods.setPassPort(goods.getPassPort());
					cartGoods.setLeavePort(goods.getLeavePort());
					cartGoods.setGoodsTitle(goods.getTitle());
					if (null != date && !date.equals("")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date leaveDate = sdf.parse(date);
						cartGoods.setLeaveDate(leaveDate);
					}
					cartGoods.setQuantity(1L);
					cartGoods.setIsLoggedIn(isLogin);
					cartGoods.setUsername(username);
					cartGoods = tdCartGoodsService.save(cartGoods);
				}
				TdCartGoods cartGoods = tdCartGoodsService
						.findTopByGoodsIdAndUsername(goodsId, username);

				req.getSession().setAttribute("cartGoodsId", cartGoods.getId());

				map.addAttribute("cartGoods", cartGoods);
				// 如果是国内游，就跳转页面。(国内游和国外游分开)
				if (goods.getCategoryTitle().equals("国内旅游")) {
					return "/client/order_destination_2_2";
				}
				if(goods.getCategoryTitle().equals("门票代售")){
					
					return "redirect: /ship3";
				}
				
				
				return "/client/order_destination_1";
			}
		}
		// 跳转到主题活动立即预定页面
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
				List<TdCartGoods> oldList = null;
				oldList = tdCartGoodsService.findByGoodsIdAndUsername(goodsId,
						username);
				if (null == oldList || oldList.size() == 0) {
					TdCartGoods cartGoods = new TdCartGoods();

					cartGoods.setGoodsId(goodsId);
					cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
					cartGoods.setReachPort(goods.getReachPort());
					cartGoods.setPassPort(goods.getPassPort());
					cartGoods.setLeavePort(goods.getLeavePort());
					cartGoods.setGoodsTitle(goods.getTitle());
					if (null != date && !date.equals("")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date leaveDate = sdf.parse(date);
						cartGoods.setLeaveDate(leaveDate);
					}
					cartGoods.setQuantity(1L);
					cartGoods.setIsLoggedIn(isLogin);
					cartGoods.setUsername(username);
					cartGoods = tdCartGoodsService.save(cartGoods);
				}
				TdCartGoods cartGoods = tdCartGoodsService
						.findTopByGoodsIdAndUsername(goodsId, username);

				req.getSession().setAttribute("cartGoodsId", cartGoods.getId());

				map.addAttribute("cartGoods", cartGoods);
				map.addAttribute("totalPrice", cartGoods);
				return "/client/order_activity_1";
			}
		}
		/*
		 * //跳转到签证预定页面 if (null != goods.getCategoryIdTree() && null != visaCat)
		 * { if (goods.getCategoryIdTree().contains("[" + visaCat.getId() +
		 * "]")) { List<TdCartGoods> oldList = null; oldList =
		 * tdCartGoodsService.findByGoodsIdAndUsername(goodsId, username);
		 * if(null == oldList || oldList.size() == 0 ) { TdCartGoods cartGoods =
		 * new TdCartGoods();
		 * 
		 * cartGoods.setGoodsId(goodsId);
		 * cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
		 * cartGoods.setReachPort(goods.getReachPort());
		 * cartGoods.setPassPort(goods.getPassPort());
		 * cartGoods.setLeavePort(goods.getLeavePort());
		 * cartGoods.setGoodsTitle(goods.getTitle()); if(null !=
		 * date&&!date.equals("")) { SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd"); Date leaveDate = sdf.parse(date);
		 * cartGoods.setLeaveDate(leaveDate); } cartGoods.setQuantity(1L);
		 * cartGoods.setIsLoggedIn(isLogin); cartGoods.setUsername(username);
		 * cartGoods = tdCartGoodsService.save(cartGoods); } TdCartGoods
		 * cartGoods = tdCartGoodsService.findTopByGoodsIdAndUsername(goodsId,
		 * username);
		 * 
		 * req.getSession().setAttribute("cartGoodsId", cartGoods.getId());
		 * 
		 * map.addAttribute("cartGoods", cartGoods);
		 * map.addAttribute("totalPrice", cartGoods); return
		 * "redirect:/order/ship2?cartId="+cartGoods.getId(); } }
		 */
		// 跳转到特产预定页面
		if (null != goods.getCategoryIdTree() && null != specialCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + specialCat.getId() + "]")) {
				List<TdCartGoods> oldList = null;
				oldList = tdCartGoodsService.findByGoodsIdAndUsername(goodsId,
						username);
				if (null == oldList || oldList.size() == 0) {
					TdCartGoods cartGoods = new TdCartGoods();

					cartGoods.setGoodsId(goodsId);
					cartGoods.setGoodsCoverImageUri(goods.getCoverImageUri());
					cartGoods.setReachPort(goods.getReachPort());
					cartGoods.setPassPort(goods.getPassPort());
					cartGoods.setLeavePort(goods.getLeavePort());
					cartGoods.setGoodsTitle(goods.getTitle());
					if (null != date && !date.equals("")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date leaveDate = sdf.parse(date);
						cartGoods.setLeaveDate(leaveDate);
					}
					cartGoods.setQuantity(1L);
					cartGoods.setIsLoggedIn(isLogin);
					cartGoods.setUsername(username);
					cartGoods = tdCartGoodsService.save(cartGoods);
				}
				TdCartGoods cartGoods = tdCartGoodsService
						.findTopByGoodsIdAndUsername(goodsId, username);

				req.getSession().setAttribute("cartGoodsId", cartGoods.getId());

				map.addAttribute("cartGoods", cartGoods);
				map.addAttribute("totalPrice", cartGoods);
				
				//确定折后价
				Long levelId = tdUserService.findByUsername(username).getUserLevelId();
		 		Double discountRatio = null;
		 		if (levelId != null) {
		 			TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
		 			if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
		 				discountRatio = tdUserLevelService.findOne(levelId)
		 						.getDiscountRatio();
		 				if (discountRatio != null) {
		 					map.addAttribute("discountRatio", discountRatio);
		 				}
		 			}
		 		}
		 		
				
				// gl
				TdUser user = tdUserService.findByUsername(username);
				if (user != null) {
					List<TdShippingAddress> addressList = tdShippingAddressService
							.findByUserId(user.getId());
					map.addAttribute("addressList", addressList);
				}
				
				//优惠劵使用
		        List<TdCoupon> couponList = tdCouponService.findByUsername(username);
		        Date date1 = new Date();
		        //对过期优惠劵直接删除
		        for(int x=0;x<couponList.size();x++){
		        	TdCoupon coupon = couponList.get(x);
		        	if(date1.after(coupon.getExpireTime())||coupon.getGetTime().equals(date1)){
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
		        		if(couponType.getProductTypeId()!=30L){
		        			couponList.remove(coupon);
		        		}
		        	}
		        	
		        }
		        
		        map.addAttribute("coupon_list", couponList);
		        req.getSession().setAttribute("special_price", goods.getSalePrice());
				
				return "/client/order_specialty_submit";
			}
		}

		return "/client/error_404";
	}

	@RequestMapping(value = "/goodsPs/select", method = RequestMethod.POST)
	public String goodsPsSelect(Long cartId, Long gpId, Long num1, Long num2,
			String type, String in, HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			username = req.getSession().getId();
		}
		if (null == cartId) {
			return "/client/error_404";
		}
		if (null == gpId) {
			return "/client/error_404";
		}

		if (null == num1) {
			num1 = 0L;
		}

		if (null == num2) {
			num2 = 0L;
		}

		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));

		// 查看当前购物车项
		TdCartGoods cartGoods = tdCartGoodsService.findOne(cartId);
		if (null != cartGoods.getPsList() && cartGoods.getPsList().size() > 0) {
			TdCartGoodsPs cartGoodsPs = tdCartGoodsPsService
					.findTopByCartIdAndGoodsPsId(cartId, gpId);
			if (null != cartGoodsPs) {
				// 已选当前服务
				if (null == in) {
					if (cartGoodsPs.getIsSelect()) {
						cartGoodsPs.setIsSelect(false);
					} else {
						cartGoodsPs.setIsSelect(true);
					}
				}
				cartGoodsPs.setQuantity1(num1);
				cartGoodsPs.setQuantity2(num2);
				tdCartGoodsPsService.save(cartGoodsPs);
			} else {
				TdGoodsPs goodsPs = tdGoodsPsService.findOne(gpId);
				if (null != goodsPs) {
					// 未选当前服务
					cartGoodsPs = new TdCartGoodsPs();
					cartGoodsPs.setGoodsPsId(goodsPs.getId());
					if (null == in) {
						cartGoodsPs.setIsSelect(true);
					} else {
						cartGoodsPs.setIsSelect(false);
					}
					cartGoodsPs.setPrice1(goodsPs.getPrice1());
					cartGoodsPs.setPrice2(goodsPs.getPrice2());
					cartGoodsPs.setQuantity1(num1);
					cartGoodsPs.setQuantity2(num2);
					cartGoodsPs.setTitle(goodsPs.getTitle());
					if (null == type) {
						cartGoodsPs.setType(1L); // 房型
					} else {
						cartGoodsPs.setType(2L); // 服务
					}

					cartGoodsPs = tdCartGoodsPsService.save(cartGoodsPs);
				}
				cartGoods.getPsList().add(cartGoodsPs);
			}
		} else {
			TdGoodsPs goodsPs = tdGoodsPsService.findOne(gpId);
			List<TdCartGoodsPs> psList = new ArrayList<>();
			if (null != goodsPs) {
				// 未选当前服务
				TdCartGoodsPs cartGoodsPs = new TdCartGoodsPs();
				cartGoodsPs.setGoodsPsId(goodsPs.getId());
				if (null == in) {
					cartGoodsPs.setIsSelect(true);
				} else {
					cartGoodsPs.setIsSelect(false);
				}
				cartGoodsPs.setPrice1(goodsPs.getPrice1());
				cartGoodsPs.setPrice2(goodsPs.getPrice2());
				cartGoodsPs.setQuantity1(num1);
				cartGoodsPs.setQuantity2(num2);
				cartGoodsPs.setTitle(goodsPs.getTitle());
				if (null == type) {
					cartGoodsPs.setType(1L); // 房型
				} else {
					cartGoodsPs.setType(2L); // 服务
				}

				cartGoodsPs = tdCartGoodsPsService.save(cartGoodsPs);
				psList.add(cartGoodsPs);
			}
			cartGoods.setPsList(psList);
		}
		cartGoods.setPrice(sumPrice(cartGoods));// 添加价格
		map.addAttribute("cartGoods", tdCartGoodsService.save(cartGoods));

//		TdProductCategory shipCat = tdProductCategoryService
//				.findByTitle("邮轮俱乐部");
		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		TdGoods goods = tdGoodsService.findOne(cartGoods.getGoodsId());
		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
				return "/client/order_destination_1_cart";
			}
		}
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
				return "/client/order_activity_1_cart";
			}
		}

		return "/client/order_ship_1_cart";
	}

	@RequestMapping("/chooseService")
	public String chooseService(String date, Long cartId,
			HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if(null == username)
		{
			return "redirect:/login";
		}
		
		tdCommonService.setCommon(map, req);

		Long goodsId = (Long) req.getSession().getAttribute("orderGoodsId");
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		if (null == goodsId) {
			return "/client/error_404";
		}
		if (null == cartId) {
			return "/client/error_404";
		}

		// 查询该商品
		TdGoods goods = tdGoodsService.findOne(goodsId);

		if (null == goods) {
			return "/client/error_404";
		}

		map.addAttribute("goods", goods);

		if (null != date) {
			map.addAttribute("leaveDate", date);
		}
		
		
		map.addAttribute("price1", req.getSession().getAttribute("price1"));
		
		TdProductCategory shipCat = tdProductCategoryService
				.findByTitle("邮轮俱乐部");
		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");

		if (null != goods.getCategoryIdTree() && null != shipCat) {
			if (goods.getCategoryIdTree().contains("[" + shipCat.getId() + "]")) {
				map.addAttribute("cartGoods",
						tdCartGoodsService.findOne(cartId));
				return "/client/order_ship_2";
			}
		}
		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
				map.addAttribute("cartGoods",
						tdCartGoodsService.findOne(cartId));
				return "/client/order_destination_2";
			}
		}
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
				map.addAttribute("cartGoods",
						tdCartGoodsService.findOne(cartId));
				return "/client/order_activity_2";
			}
		}

		return "/client/error_404";
	}

	/**
	 * 添加选择项
	 * 
	 * @author Max
	 * 
	 */
	@RequestMapping(value = "/ship")
	@ResponseBody
	public Map<String, Object> orderShip2(Long[] listIds, Boolean[] check,
			Long[] adultNum, Long[] childNum, String type,
			HttpServletRequest req, ModelMap map) {
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		Map<String, Object> res = new HashMap<>();
		res.put("code", 0);

		// 筛选出选择的服务及人数
		List<Map<String, Long>> list = check(listIds, check, adultNum,
				childNum, type, req, map);

		res.put("code", 1);

		return res;
	}

	/**
	 * 进入第二步
	 * 
	 * @author Max
	 * 
	 */
	@RequestMapping(value = "/ship2")
	public String toship2(HttpServletRequest req, ModelMap map) {
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		Long orderGoodsId = (Long) req.getSession()
				.getAttribute("orderGoodsId");
		if (null == orderGoodsId) {
			return "/client/error_404";
		}

		tdCommonService.setCommon(map, req);
		TdGoods goods = tdGoodsService.findOne(orderGoodsId);
		map.addAttribute("goods", goods);
		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
				return "/client/order_destination_2";
			}
		}
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
				return "/client/order_activity_2";
			}
		}
		return "/client/order_ship_2";
	} 

	/**
	 * 进入第三步
	 * 
	 * @author Max
	 * 
	 */
	@RequestMapping(value = "/ship3")
	public String toship3(Long vid, HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			username = req.getSession().getId();
		}
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		Long orderGoodsId = (Long) req.getSession()
				.getAttribute("orderGoodsId");
		if (null == orderGoodsId) {
			return "/client/error_404";
		}

		if (null != vid) {
			map.addAttribute("visitor", tdUserVisitorService.findOne(vid));
		}
		tdCommonService.setCommon(map, req);
		map.addAttribute("goods", tdGoodsService.findOne(orderGoodsId));

		map.addAttribute("visitorList",
				tdUserVisitorService.findByUsername(username));
		
		map.addAttribute("price1", req.getSession().getAttribute("price1"));
		if(tdGoodsService.findOne(orderGoodsId).getCategoryTitle().equals("门票代售")){
			return "/client/order_ship_menpiao_3";
		}
		return "/client/order_ship_3";

		// String username = (String)req.getSession().getAttribute("username");
		// if(null == username)
		// {
		// username = req.getSession().getId();
		// }
		// map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		// Long orderGoodsId =
		// (Long)req.getSession().getAttribute("orderGoodsId");
		// if(null == orderGoodsId)
		// {
		// return "/client/error_404";
		// }
		//
		// if(null != vid)
		// {
		// map.addAttribute("visitor", tdUserVisitorService.findOne(vid));
		// }
		// tdCommonService.setCommon(map, req);
		// TdGoods goods = tdGoodsService.findOne(orderGoodsId);
		// map.addAttribute("goods", goods);
		//
		// map.addAttribute("visitorList",
		// tdUserVisitorService.findByUsername(username));
		// TdProductCategory activityCat =
		// tdProductCategoryService.findByTitle("主题活动");
		// TdProductCategory destinationCat =
		// tdProductCategoryService.findByTitle("目的地");
		// if (null != goods.getCategoryIdTree() && null != activityCat)
		// {
		// if (goods.getCategoryIdTree().contains("[" + activityCat.getId() +
		// "]"))
		// {
		// return "/client/order_activity_3";
		// }
		// }
		// if (null != goods.getCategoryIdTree() && null != destinationCat)
		// {
		// if (goods.getCategoryIdTree().contains("[" + destinationCat.getId() +
		// "]"))
		// {
		// return "/client/order_destination_3";
		// }
		// }
		//
		// return "/client/order_ship_3";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ship4")
	public String toSubmit(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		Long goodsId = (Long) req.getSession().getAttribute("orderGoodsId");
		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setCommon(map, req);
		// 依据等级确定打折额度
		Long levelId = tdUserService.findByUsername(username).getUserLevelId();
		if (levelId != null) {
			TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
			Double discountRatio = null;
			if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
				discountRatio = tdUserLevelService.findOne(levelId)
						.getDiscountRatio();
				if (discountRatio != null) {
					map.addAttribute("discountRatio", discountRatio);
				}
			}
		}

		if (null == goodsId) {
			return "/client/error_404";
		}
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		List<Map<String, Long>> psList = (List<Map<java.lang.String, Long>>) req
				.getSession().getAttribute("PSLIST");
//		if (null == psList || psList.size() < 1) {
//			return "/client/error_404";
//		}

		List<TdGoodsPs> goodsPsList = new ArrayList<>();
		if(null != psList)
		{
			for (Map<String, Long> map2 : psList) {
				Long id = map2.get("id");
				
				if (null != id) {
					TdGoodsPs goodsPs = tdGoodsPsService.findOne(id);
					if (null != goodsPs) {
						goodsPsList.add(goodsPs);
					}
				}
			}
		}

		map.addAttribute("goodsPsList", goodsPsList);
		map.addAttribute("visitorList",
				tdUserVisitorService.findByUsernameAndIsSelect(username));
		map.addAttribute("goods", tdGoodsService.findOne(goodsId));

		// map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		// map.addAttribute("goodsPsList", goodsPsList);
		// map.addAttribute("visitorList",
		// tdUserVisitorService.findByUsernameAndIsSelect(username));
		// TdGoods goods = tdGoodsService.findOne(goodsId);
		// map.addAttribute("goods", goods);
		//
		// TdProductCategory activityCat =
		// tdProductCategoryService.findByTitle("主题活动");
		// TdProductCategory destinationCat =
		// tdProductCategoryService.findByTitle("目的地");
		// if (null != goods.getCategoryIdTree() && null != activityCat)
		// {
		// if (goods.getCategoryIdTree().contains("[" + activityCat.getId() +
		// "]"))
		// {
		// return "/client/order_activity_4";
		// }
		// }
		// if (null != goods.getCategoryIdTree() && null != destinationCat)
		// {
		// if (goods.getCategoryIdTree().contains("[" + destinationCat.getId() +
		// "]"))
		// {
		// return "/client/order_destination_4";
		// }
		// }
		
		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		TdProductCategory specialCat = tdProductCategoryService
				.findByTitle("特产商城");
		TdProductCategory shipCat = tdProductCategoryService
				.findByTitle("邮轮俱乐部");
		
		TdGoods goods = tdGoodsService.findByGoodsId(goodsId);
		
		//优惠劵使用
        List<TdCoupon> couponList = tdCouponService.findByUsername(username);
        Date date = new Date();
		
		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
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
		        	if(coupon.getTypeCategoryId()==2L){
		        		TdCouponType couponType = tdCouponTypeService.findOne(coupon.getTypeId());
		        		//1L表示游轮订单
		        		if(couponType.getProductTypeId()!=2L){
		        			couponList.remove(coupon);
		        		}
		        	}
		        	
		        }
			}
		}
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
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
		        	if(coupon.getTypeCategoryId()==5L){
		        		TdCouponType couponType = tdCouponTypeService.findOne(coupon.getTypeId());
		        		//1L表示游轮订单
		        		if(couponType.getProductTypeId()!=5L){
		        			couponList.remove(coupon);
		        		}
		        	}
		        	
		        }
			}
		}
		if (null != goods.getCategoryIdTree() && null != specialCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + specialCat.getId() + "]")) {
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
		        	if(coupon.getTypeCategoryId()==30L){
		        		TdCouponType couponType = tdCouponTypeService.findOne(coupon.getTypeId());
		        		//1L表示游轮订单
		        		if(couponType.getProductTypeId()!=30L){
		        			couponList.remove(coupon);
		        		}
		        	}
		        	
		        }
			}
		}
		if (null != goods.getCategoryIdTree() && null != shipCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + shipCat.getId() + "]")) {
				
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
		        		//1L表示游轮订单
		        		if(couponType.getProductTypeId()!=1L){
		        			couponList.remove(coupon);
		        		}
		        	}
		        	
		        }
			}
		}
		
		//map.addAttribute("price1", req.getSession().getAttribute("price1"));
		map.addAttribute("price1", req.getSession().getAttribute("totalPrice"));
        
        map.addAttribute("coupon_list", couponList);

		return "/client/order_ship_4";
	}

	/**
	 * 提交订单
	 * 
	 * @author Max
	 * @throws ParseException
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submit")
	public String submit(HttpServletRequest req, ModelMap map,Long couponId)
			throws ParseException {
		String username = (String) req.getSession().getAttribute("username");
		Long goodsId = (Long) req.getSession().getAttribute("orderGoodsId");

		if (null == username) {
			return "redirect:/login";
		}
		tdCommonService.setCommon(map, req);
		Long ertongshu =  (Long) req.getSession().getAttribute("ertongshu");
		Double price11 = (Double) req.getSession().getAttribute("price1");
		
		
		
		if (null == goodsId) {
			return "/client/error_404";
		}

		TdGoods goods = tdGoodsService.findOne(goodsId);
		// 商品不存在或者已下架
		if (null == goods || !goods.getIsOnSale()) {
			return "/client/error_404";
		}

		String sessionId = (String) req.getSession().getId();

		List<TdUserVisitor> visitorList = tdUserVisitorService
				.findByUsernameAndIsSelect(sessionId);
		if (null != visitorList) {
			for (TdUserVisitor tdUserVisitor : visitorList) {
				tdUserVisitor.setUsername(username);
				tdUserVisitorService.save(tdUserVisitor);
			}
		}
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));

		if (null != username) {
			visitorList = tdUserVisitorService
					.findByUsernameAndIsSelect(username);
		}

		// 取出session里存储的选择项目Id及人数

		List<Map<String, Long>> psList = (List<Map<java.lang.String, Long>>) req
				.getSession().getAttribute("PSLIST");
		if(goods != null && !goods.getCategoryTitle().equals("门票代售")){
			if (null == psList || psList.size() < 1) {
				//return "/client/error_404";
			}
		}

		List<TdOrderGoods> orderGoodsList = new ArrayList<>();
		Double totalPrice = 0.0; // 总价
		Long quantity = 0L; // 总人数
		if(psList != null && psList.size() > 0){
			for (Map<String, Long> map2 : psList) {
				Long id = map2.get("id");
				Long adiltNum = map2.get("adiltNum");
				Long childNum = map2.get("childNum");
				
				if (null != id) {
					TdGoodsPs goodsPs = tdGoodsPsService.findOne(id);
					
					TdOrderGoods orderGoods = new TdOrderGoods();
					
					// 添加商品信息
					orderGoods.setGoodsId(goodsPs.getId());
					orderGoods.setGoodsTitle(goodsPs.getTitle());
					orderGoods.setPrice1(goodsPs.getPrice1());
					orderGoods.setQuantity1(adiltNum);
					orderGoods.setPrice2(goodsPs.getPrice2());
					orderGoods.setQuantity2(childNum);
					
					if (null != goodsPs.getPrice1() && null != adiltNum) {
						totalPrice += goodsPs.getPrice1() * adiltNum;
					}
					if (null != goodsPs.getPrice2() && null != childNum) {
						totalPrice += goodsPs.getPrice2() * childNum;
					}
					// 计算总人数 只计算房间数
					if (goodsPs.getTitle().contains("房")) {
						quantity += childNum + childNum;
					}
					orderGoodsList.add(orderGoods);
				}
			}
			
		}

		List<TdOrderVisitor> orderVisitorList = new ArrayList<>();

		// 添加证件信息
		for (TdUserVisitor tduserVisitor : visitorList) {
			TdOrderVisitor visitor = new TdOrderVisitor();

			visitor.setCertificateCardCode(tduserVisitor
					.getCertificateCardCode());
			visitor.setCertificateType(tduserVisitor.getCertificateType());
			visitor.setSex(tduserVisitor.getSex());
			visitor.setVisitorName(tduserVisitor.getVisitorName());
			visitor.setTelephone(tduserVisitor.getTelephone());

			orderVisitorList.add(visitor);
		}
//		if(goods != null && !goods.getCategoryTitle().equals("门票代售")){
//			if (null == orderGoodsList || orderGoodsList.size() <= 0) {
//				return "/client/error_404";
//			}			
//		}
		

		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		TdProductCategory specialCat = tdProductCategoryService
				.findByTitle("特产商城");
		TdProductCategory shipCat = tdProductCategoryService
				.findByTitle("邮轮俱乐部");
		TdProductCategory menpiao = tdProductCategoryService
				.findByTitle("门票代售");

		TdOrder order = new TdOrder();

		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
				order.setTypeId(1L);
				order.setOrderType("目的地");
				
			}
		}
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
				order.setTypeId(2L);
				order.setOrderType("主题活动");
			}
		}
		if (null != goods.getCategoryIdTree() && null != specialCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + specialCat.getId() + "]")) {
				order.setTypeId(6L);
				order.setOrderType("特产商城");
			}
		}
		if (null != goods.getCategoryIdTree() && null != shipCat) {
			if (goods.getCategoryIdTree().contains("[" + shipCat.getId() + "]")) {
				order.setTypeId(0L);
				order.setOrderType("邮轮俱乐部");
			}
		}
		if (null != goods.getCategoryIdTree() && null != menpiao) {
			if (goods.getCategoryIdTree().contains("[" + menpiao.getId() + "]")) {
				order.setTypeId(7L);
				order.setOrderType("门票代售");
			}
		}

		String leaveDate = (String) req.getSession().getAttribute("date");

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sim.parse(leaveDate);
		order.setLeaveDate(d);

		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String curStr = sdf.format(current);
		Random random = new Random();

		// 基本信息
		order.setUsername(username);
		order.setOrderTime(current);
		order.setErtongshu(ertongshu);
		// 订单号
		order.setOrderNumber("P" + curStr
				+ leftPad(Integer.toString(random.nextInt(999)), 3, "0"));

		// 待付款
		order.setStatusId(2L);

		// 订单商品信息
		order.setGoodsId(goods.getId()); // 商品编号
		order.setGoodsTitle(goods.getTitle()); // 名称
		// order.setLeaveDate(goods.getLeaveDate()); 出发时间
		order.setReachPort(goods.getReachPort()); // 到达港口
		order.setPassPort(goods.getPassPort()); // 途径港口
		order.setShipCompany(goods.getShipCompany()); // 邮轮公司
		order.setLeavePort(goods.getLeavePort()); // 出发港口
		// order.setTurnDate(goods.getTurnDate()); //返航时间

		// 商品服务
		order.setOrderGoodsList(orderGoodsList);
		// 证件
		order.setVisitorList(orderVisitorList);

		// 计算折后价
		// Long levelId =
		// tdUserService.findByUsername(username).getUserLevelId();
		// TdUserLevel tdUserLevel = null;
		// if(levelId != null){
		// tdUserLevel = tdUserLevelService.findByLevelId(levelId);
		// }
		// Double disCountRatio = 1D;
		// if(tdUserLevel != null){
		// if(tdUserLevel.getDiscountRatio() != null){
		// disCountRatio = tdUserLevel.getDiscountRatio();
		// }
		// }
		// 订单总价
		// if(disCountRatio != null){
		// order.setTotalPrice((totalPrice+goods.getSalePrice())*disCountRatio);
		// }else{
		// order.setTotalPrice(totalPrice+goods.getSalePrice());
		// }
		// 订单总价
		
		TdCoupon coupon = tdCouponService.findOne(couponId);
		if(null!=coupon){
			order.setTotalPrice(totalPrice + price11 - coupon.getPrice());
			
			Long levelId = tdUserService.findByUsername(username).getUserLevelId();
			Double discountRatio = null;
			if (levelId != null) {
				TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
				if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
					discountRatio = tdUserLevelService.findOne(levelId)
							.getDiscountRatio();
					if (discountRatio != null) {
						order.setTotalPrice((totalPrice +price11)*discountRatio - coupon.getPrice());
					}
				}
			}
	
			
			if(null!=couponId&&!couponId.equals("")){
	        	tdCouponService.delete(couponId);
	        }
		}else{
			order.setTotalPrice(totalPrice + price11);
			
			Long levelId = tdUserService.findByUsername(username).getUserLevelId();
			Double discountRatio = null;
			if (levelId != null) {
				TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
				if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
					discountRatio = tdUserLevelService.findOne(levelId)
							.getDiscountRatio();
					if (discountRatio != null) {
						order.setTotalPrice((totalPrice + price11)*discountRatio);
					}
				}
			}
		}
		
		// 保存订单服务及订单、证件
		tdOrderGoodsService.save(orderGoodsList);
		tdOrderVisitorService.save(orderVisitorList);
		
		// 清楚此次订单session
		req.getSession().removeAttribute("PSLIST");
		req.getSession().removeAttribute("totalPrice");
		req.getSession().removeAttribute("adiltNum");
		req.getSession().removeAttribute("childNum");

		map.addAttribute("order", tdOrderService.save(order));
		setPayTypes(map, true, false, req);
		
		return "/client/order_ship_5";
	}

	// @RequestMapping(value="/submit")
	public String orderSubmit(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}
		tdCommonService.setCommon(map, req);

		String sessionId = (String) req.getSession().getId();

		TdCartGoods cartGoods = tdCartGoodsService.findTopByUsername(sessionId);
		TdGoods goods = tdGoodsService.findByGoodsId(cartGoods.getGoodsId());
		if (null != cartGoods) {
			cartGoods.setUsername(username);
			tdCartGoodsService.save(cartGoods);
		}

		List<TdUserVisitor> visitorList = tdUserVisitorService
				.findByUsernameAndIsSelect(sessionId);
		if (null != visitorList) {
			for (TdUserVisitor tdUserVisitor : visitorList) {
				tdUserVisitor.setUsername(username);
				tdUserVisitorService.save(tdUserVisitor);
			}
		}

		if (null != username) {
			visitorList = tdUserVisitorService
					.findByUsernameAndIsSelect(username);
		}

		cartGoods = tdCartGoodsService.findTopByUsername(username);

		if (null == cartGoods) {
			return "/client/error_404";
		}

		TdGoods tdGoods = tdGoodsService.findOne(cartGoods.getGoodsId());
		// 商品不存在或已下架
		if (null == tdGoods || !tdGoods.getIsOnSale()) {
			return "/client/error_404";
		}

		List<TdOrderGoods> orderGoodsList = new ArrayList<>();
		List<TdOrderVisitor> orderVisitorList = new ArrayList<>();

		// 添加证件信息
		for (TdUserVisitor tduserVisitor : visitorList) {
			TdOrderVisitor visitor = new TdOrderVisitor();

			visitor.setCertificateCardCode(tduserVisitor
					.getCertificateCardCode());
			visitor.setCertificateType(tduserVisitor.getCertificateType());
			visitor.setSex(tduserVisitor.getSex());
			visitor.setVisitorName(tduserVisitor.getVisitorName());
			visitor.setTelephone(tduserVisitor.getTelephone());

			orderVisitorList.add(visitor);
		}

		// 订单总价
		Double totalPrice = 0.0;

		// 总量
		Long quantity = 0L;

		if (null != cartGoods.getPsList() && cartGoods.getPsList().size() > 0) {
			for (TdCartGoodsPs cartGoodsPs : cartGoods.getPsList()) {
				if (cartGoodsPs.getIsSelect()) {
					TdOrderGoods orderGoods = new TdOrderGoods();

					// 添加商品信息
					orderGoods.setGoodsId(cartGoodsPs.getGoodsPsId());
					orderGoods.setGoodsTitle(cartGoodsPs.getTitle());
					orderGoods.setPrice1(cartGoodsPs.getPrice1());
					orderGoods.setQuantity1(cartGoodsPs.getQuantity1());
					orderGoods.setPrice2(cartGoodsPs.getPrice2());
					orderGoods.setQuantity2(cartGoodsPs.getQuantity2());

					if (null != cartGoodsPs.getPrice1()
							&& null != cartGoodsPs.getQuantity1()) {
						totalPrice += cartGoodsPs.getPrice1()
								* cartGoodsPs.getQuantity1();
					}
					if (null != cartGoodsPs.getPrice2()
							&& null != cartGoodsPs.getQuantity2()) {
						totalPrice += cartGoodsPs.getPrice2()
								* cartGoodsPs.getQuantity2();
					}

					// 计算总人数 只计算房间数
					if (cartGoodsPs.getType() == 1) {
						quantity += cartGoodsPs.getQuantity1()
								+ cartGoodsPs.getQuantity2();
					}
					orderGoodsList.add(orderGoods);
				}
			}
		}
		tdCartGoodsService.delete(cartGoods);

		if (null == orderGoodsList || orderGoodsList.size() <= 0) {
			return "/client/error_404";
		}

		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		TdProductCategory specialCat = tdProductCategoryService
				.findByTitle("特产商城");
		TdProductCategory shipCat = tdProductCategoryService
				.findByTitle("邮轮俱乐部");

		TdOrder order = new TdOrder();

		if (null != goods.getCategoryIdTree() && null != destinationCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + destinationCat.getId() + "]")) {
				order.setTypeId(1L);
				order.setOrderType("目的地");
			}
		}
		if (null != goods.getCategoryIdTree() && null != activityCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + activityCat.getId() + "]")) {
				order.setTypeId(2L);
				order.setOrderType("主题活动");
			}
		}
		if (null != goods.getCategoryIdTree() && null != specialCat) {
			if (goods.getCategoryIdTree().contains(
					"[" + specialCat.getId() + "]")) {
				order.setTypeId(6L);
				order.setOrderType("特产商城");
			}
		}
		if (null != goods.getCategoryIdTree() && null != shipCat) {
			if (goods.getCategoryIdTree().contains("[" + shipCat.getId() + "]")) {
				order.setTypeId(0L);
				order.setOrderType("邮轮俱乐部");
			}
		}

		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String curStr = sdf.format(current);
		Random random = new Random();

		// 基本信息
		order.setUsername(username);
		order.setOrderTime(current);

		// 订单号
		order.setOrderNumber("P" + curStr
				+ leftPad(Integer.toString(random.nextInt(999)), 3, "0"));

		// 待付款
		order.setStatusId(2L);

		// 订单商品信息
		order.setGoodsId(cartGoods.getGoodsId()); // 商品编号
		order.setGoodsTitle(cartGoods.getGoodsTitle()); // 名称
		order.setLeaveDate(cartGoods.getLeaveDate()); // 出发时间
		order.setReachPort(cartGoods.getReachPort()); // 到达港口
		order.setPassPort(cartGoods.getPassPort()); // 途径港口
		order.setShipCompany(cartGoods.getShipCompany()); // 邮轮公司
		order.setLeavePort(cartGoods.getLeavePort()); // 出发港口
		order.setTurnDate(cartGoods.getTurnDate()); // 返航时间

		// 商品服务
		order.setOrderGoodsList(orderGoodsList);
		// 证件
		order.setVisitorList(orderVisitorList);
		/*
		 * //计算折后价 Long levelId =
		 * tdUserService.findByUsername(username).getUserLevelId(); TdUserLevel
		 * tdUserLevel = null; if(levelId != null){ tdUserLevel =
		 * tdUserLevelService.findByLevelId(levelId); } Double disCountRatio =
		 * 1D; if(tdUserLevel != null){ if(tdUserLevel.getDiscountRatio() !=
		 * null){ disCountRatio = tdUserLevel.getDiscountRatio(); } }
		 */
		// 依据等级确定打折额度
		Long levelId = tdUserService.findByUsername(username).getUserLevelId();
		Double discountRatio = null;
		if (levelId != null) {
			TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
			if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
				discountRatio = tdUserLevelService.findOne(levelId)
						.getDiscountRatio();
//				if (discountRatio != null) {
//					map.addAttribute("discountRatio", discountRatio);
//				}
			}
		}
		// 订单总价
		if (discountRatio != null) {
			order.setTotalPrice(totalPrice * discountRatio);
		} else {
			order.setTotalPrice(totalPrice);
		}

		order.setTypeId(0L);

		// 保存订单服务及订单、证件
		tdOrderGoodsService.save(orderGoodsList);
		tdOrderVisitorService.save(orderVisitorList);

		map.addAttribute("order", tdOrderService.save(order));
		setPayTypes(map, true, false, req);

		return "/client/order_ship_5";
	}

	@RequestMapping(value = "/gopay", method = RequestMethod.POST)
	public String doPay(Long id, Long payId, HttpServletRequest req,
			ModelMap map) {
		map.addAttribute("leaveDate", req.getSession().getAttribute("date"));
		if (null == payId) {
			return "/client/error_404";
		}
		if (null == id) {
			return "/client/error_404";
		}

		TdOrder order = tdOrderService.findOne(id);

		TdPayType payType = tdPayTypeService.findOne(payId);

		// 支付类型
		order.setPayTypeId(payType.getId());
		order.setPayTypeTitle(payType.getTitle());
		order.setIsOnlinePay(payType.getIsOnlinePay());

		order = tdOrderService.save(order);

		return "redirect:/order/dopay/" + order.getId();
	}

	/**
	 * 新加地址
	 * 
	 * @author Max
	 * 
	 */
	@RequestMapping(value = "/visitor/edit")
	public String String(TdUserVisitor visitor, Long cartId,
			HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			username = req.getSession().getId();
		}

		if (null == visitor) {
			return "/client/error_404";
		}

		visitor.setUsername(username);
		tdUserVisitorService.save(visitor);

		return "redirect:/order/ship3";
	}

	/**
	 * 地址选择状态
	 * 
	 * @author Max
	 * 
	 */
	@RequestMapping(value = "/visitor/select", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> visitorSelect(Long visitId,
			HttpServletRequest req) {
		Map<String, Object> res = new HashMap<>();
		res.put("code", 0);

		if (null == visitId) {
			res.put("msg", "参数错误！");
			return res;
		}

		TdUserVisitor visitor = tdUserVisitorService.findOne(visitId);
		if (null != visitor) {
			if (null == visitor.getIsSelect() || !visitor.getIsSelect()) {
				visitor.setIsSelect(true);
			} else {
				visitor.setIsSelect(false);
			}
		}

		tdUserVisitorService.save(visitor);

		res.put("code", 1);
		return res;

	}

	// 跳转支付
	@RequestMapping(value = "/dopay/{orderId}")
	public String payOrder(@PathVariable Long orderId, ModelMap map,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setCommon(map, req);

		if (null == orderId) {
			return "/client/error_404";
		}

		TdOrder order = tdOrderService.findOne(orderId);

		if (null == order) {
			return "/client/error_404";
		}

		// 判断订单是否过时 订单提交后24小时内
		// Date cur = new Date();
		// long temp = cur.getTime() - order.getOrderTime().getTime();
		// if (temp > 1000 * 3600 * 24) {
		// order.setSortId(6L);
		// tdOrderService.save(order);
		// return "/client/overtime";
		// }

		// 待付款
		if (!order.getStatusId().equals(2L)) {
			return "/client/error_404";
		}

		String amount = order.getTotalPrice().toString();
		req.setAttribute("totalPrice", amount);

		String payForm = "";

		Long payId = order.getPayTypeId();
		if (null == payId) {
			map.addAttribute("order", order);
			setPayTypes(map, true, false, req);

			return "/client/order_ship_5";
		}

		TdPayType payType = tdPayTypeService.findOne(payId);
		if (payType != null) {
			TdPayRecord record = new TdPayRecord();
			record.setCreateTime(new Date());
			record.setOrderId(order.getId());
			record.setPayTypeId(payType.getId());
			record.setStatusCode(1);
			record.setCreateTime(new Date());
			record = payRecordService.save(record);

			String payRecordId = record.getId().toString();
			int recordLength = payRecordId.length();
			if (recordLength > 6) {
				payRecordId = payRecordId.substring(recordLength - 6);
			} else {
				payRecordId = leftPad(payRecordId, 6, "0");
			}

			req.setAttribute("payRecordId", payRecordId);

			req.setAttribute("orderNumber", order.getOrderNumber());

			String payCode = payType.getCode();
			PaymentChannelCEB payChannelCEB = new PaymentChannelCEB();
			if (CEBPayConfig.INTER_B2C_BANK_CONFIG.keySet().contains(
					payCode)) {
				req.setAttribute("payMethod", payCode);
				payForm = payChannelCEB.getPayFormData(req);
				map.addAttribute("charset", "GBK");
			} else {
				// 其他目前未实现的支付方式
				return "/client/error_404";
			}
		} else {
			return "/client/error_404";
		}

		order.setPayTime(new Date());

		tdOrderService.save(order);

		map.addAttribute("payForm", payForm);

		return "/client/order_pay_form";
	}

	@RequestMapping(value = "/pay/notify_cebpay")
	public void payNotifyCEBPay(ModelMap map, HttpServletRequest req,
			HttpServletResponse resp) {
		
		PaymentChannelCEB payChannelCEB = new PaymentChannelCEB();
		payChannelCEB.doResponse(req, resp);
	}

	@RequestMapping(value = "/pay/result_cebpay")
	public String payResultCEBPay(Device device, ModelMap map,
			HttpServletRequest req, HttpServletResponse resp) {

		tdCommonService.setCommon(map, req);

		String plainData = req.getParameter("Plain");
		String signature = req.getParameter("Signature");
		System.err.println("Max:"+plainData);
		System.err.println("Max:"+plainData);
		// 计算得出通知验证结果
		boolean verify_result = CebMerchantSignVerify
				.merchantVerifyPayGate_ABA(signature, plainData);
		String plainObjectStr = "";

		if (null != plainData && plainData.endsWith("~|~")) {
			plainObjectStr = plainData.substring(0, plainData.length() - 3);
		}

		plainObjectStr = plainObjectStr.replaceAll("=", "\":\"").replaceAll(
				"~\\|~", "\",\"");
		plainObjectStr = "{\"" + plainObjectStr + "\"}";

		JSONObject paymentResult = JSONObject.fromObject(plainObjectStr);

		String orderNo = paymentResult.getString("orderId");
		orderNo = (orderNo == null) ? "" : (orderNo.length() < 6) ? orderNo
				: orderNo.substring(0, orderNo.length() - 6);
		TdOrder order = tdOrderService.findByOrderNumber(orderNo);
		if (order == null) {
			// 订单不存在
			// 触屏
			if (device.isMobile() || device.isTablet()) {
				return "/touch/order_pay_failed";
			}
			return "/client/order_pay_failed";
		}

		map.put("order", order);

		if (verify_result) {// 验证成功
			String trade_status = paymentResult.getString("respCode");
			if ("".equals(trade_status) || "AAAAAAA".equals(trade_status)) {
				// 订单支付成功

				afterPaySuccess(order);
				// 触屏
				if (device.isMobile() || device.isTablet()) {
					return "/touch/order_pay_success";
				}
				return "/client/order_pay_success";
			}

		}
		// 验证失败或者支付失败
		// 触屏
		if (device.isMobile() || device.isTablet()) {
			return "/touch/order_pay_failed";
		}
		return "/client/order_pay_failed";
	}

	// 支付完成后续
	private void afterPaySuccess(TdOrder order) {
		if (null == order) {
			return;
		}

		if (order.getStatusId().equals(2L)) {
			order.setStatusId(3L);
			order.setPayTime(new Date());
			tdOrderService.save(order);
		}
	}

	/**
	 * 计算总价
	 * 
	 * @author Max
	 * 
	 */
	public Double sumPrice(TdCartGoods cartGoods) {
		Double totalPrice = 0.0;
		Double price1 = 0.0;
		Double price2 = 0.0;
		if (null != cartGoods && null != cartGoods.getPsList()) {
			if (cartGoods.getPsList().size() > 0) {
				for (TdCartGoodsPs goodsPs : cartGoods.getPsList()) {
					if (goodsPs.getIsSelect())// 选择的服务计算价格
					{
						if (null != goodsPs.getPrice1()
								&& null != goodsPs.getQuantity1()) {
							price1 += goodsPs.getPrice1()
									* goodsPs.getQuantity1();
						}
						if (null != goodsPs.getPrice2()
								&& null != goodsPs.getQuantity2()) {
							price1 += goodsPs.getPrice2()
									* goodsPs.getQuantity2();
						}
					}
				}
			}
		}
		totalPrice += price1 + price2;
		return totalPrice;
	}

	@RequestMapping(value = "/test")
	public String test(Long[] listIds, Boolean[] check, Long[] adultNum,
			Long[] childNum, HttpServletRequest req) {

		// check(listIds, check, adultNum, childNum, req);

		return "redirect:/order/1";
	}

	/**
	 * 便利选择的数据 存到session
	 * 
	 * @author Max
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Long>> check(Long[] ids, Boolean checks[],
			Long[] adiltNums, Long[] childNums, String type,
			HttpServletRequest req, ModelMap map) {
		if (null == ids || null == checks || null == adiltNums
				|| null == childNums || ids.length < 1 || checks.length < 1
				|| adiltNums.length < 1 || childNums.length < 1) {
			return null;
		}

		Long adilt = 0L;
		Long child = 0L;
		Double price = 0.0;

		Double totalPeice = (Double) req.getSession()
				.getAttribute("totalPrice"); // 二次进入 累加之前的价格
		if (null != totalPeice) {
			price += totalPeice;
		}
		Long adl = (Long) req.getSession().getAttribute("adiltNum"); // 二次进入
																		// 接收成人数
		if (null != adl) {
			adilt += adl;
		}

		Long chil = (Long) req.getSession().getAttribute("childNum"); // 二次进入
																		// 接收儿童数
		if (null != chil) {
			child += chil;
		}

		List<Map<String, Long>> psList = (List<Map<java.lang.String, Long>>) req
				.getSession().getAttribute("PSLIST");
		if (null == psList) {
			psList = new ArrayList<>();
		}

		for (int i = 0; i < checks.length; i++) {
			if (checks[i]) {
				Map<String, Long> res = new HashMap<>();
				Long id = ids[i];
				Long adiltNum = adiltNums[i];
				Long childNum = childNums[i];

				res.put("id", id);
				res.put("adiltNum", adiltNum);
				res.put("childNum", childNum);

				TdGoodsPs goodsPs = tdGoodsPsService.findOne(id);
				if (null == goodsPs) {
					break;
				}

				if (null != goodsPs.getPrice1()) {
					price += adiltNum * goodsPs.getPrice1(); // 计算成人价
				}
				if (null != goodsPs.getPrice2()) {
					price += childNum * goodsPs.getPrice2(); // 计算儿童价
				}

				if ("hou".equals(type))// 房型 人数累加
				{
					child += childNum;
					adilt += adiltNum;
				}

				psList.add(res);

			}
		}

		// for (Map<String, Long> map2 : psList) {
		// System.err.println(map2.toString());
		// System.err.println(map2.get("id")+"---"+"---childNum="+map2.get("adiltNum"));
		// }

		// map.addAttribute("totalPrice", price);
		// map.addAttribute("adiltNum", adilt);
		// map.addAttribute("childNum", child);
		req.getSession().setAttribute("totalPrice", price);
		req.getSession().setAttribute("adiltNum", adilt);
		req.getSession().setAttribute("childNum", child);

		req.getSession().setAttribute("PSLIST", psList);

		return psList;
	}

	// 跳转到特产订单页面
	@RequestMapping(value = "/specialty", method = RequestMethod.GET)
	public String orderSpecial(ModelMap map, HttpServletRequest req, Long id) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}
		tdCommonService.setCommon(map, req);
		TdUser user = tdUserService.findByUsername(username);

		TdGoods goods = tdGoodsService.findByIdAndIsOnSaleTrue(id);
		map.addAttribute("goods", goods);

		List<TdShippingAddress> addressList = tdShippingAddressService
				.findByUserId(user.getId());
		map.addAttribute("addressList", addressList);

		map.addAttribute("id", id);
		
		
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
        		if(couponType.getProductTypeId()!=30L){
        			couponList.remove(coupon);
        		}
        	}
        	
        }
        
        map.addAttribute("coupon_list", couponList);
		
		return "/client/order_specialty_submit";
	}

	// 订单提交
	@RequestMapping(value = "/ordersubmit", method = RequestMethod.POST)
	public String ordersubmit1(ModelMap map, HttpServletRequest req,
			Long goodsId, Long shopId, Double totalPrice,Long couponId,
			String shippingAddress, String remarkInfo) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}
		tdCommonService.setCommon(map, req);
		
		
		
		TdCoupon coupon = tdCouponService.findOne(couponId);
		
		TdGoods goods = tdGoodsService.findOne(goodsId);

		TdOrder order = new TdOrder();

		order.setTypeId(6L);
		order.setOrderType("特产商城");

		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String curStr = sdf.format(current);
		Random random = new Random();
		// 订单号
		order.setOrderNumber("P" + curStr
				+ leftPad(Integer.toString(random.nextInt(999)), 3, "0"));
		// 基本信息
		order.setUsername(username);
		order.setOrderTime(current);
		// 待付款
		order.setStatusId(2L);

		// 订单商品信息
		order.setGoodsId(goodsId); // 商品编号
		order.setGoodsTitle(goods.getTitle()); // 名称
		// 订单总价
		if(coupon == null){
			order.setTotalPrice(totalPrice);	
		}else{
			order.setTotalPrice(totalPrice-coupon.getPrice());
		}
		
		// 依据等级确定打折额度
		 		Long levelId = tdUserService.findByUsername(username).getUserLevelId();
		 		Double discountRatio = null;
		 		if (levelId != null) {
		 			TdUserLevel tdUserLevel = tdUserLevelService.findOne(levelId);
		 			if (tdUserLevel != null && tdUserLevel.getLevelId() > -1) {
		 				discountRatio = tdUserLevelService.findOne(levelId)
		 						.getDiscountRatio();
//		 				if (discountRatio != null) {
//		 					map.addAttribute("discountRatio", discountRatio);
//		 				}
		 			}
		 		}
		 		// 修改订单总价
		 		if(coupon != null){
		 			if (discountRatio != null) {
		 				order.setTotalPrice(totalPrice * discountRatio-coupon.getPrice());
		 			}		 			
		 		}else{
		 			if (discountRatio != null) {
		 				order.setTotalPrice(totalPrice * discountRatio);
		 			}
		 		}
		
		order.setRemarkInfo(remarkInfo);
		// 数量 多少斤
		order.setShopId(shopId);
		// 收货地址
		order.setShippingAddress(shippingAddress);
		tdOrderService.save(order);
		
		if(null!=couponId&&!couponId.equals("")){
        	tdCouponService.delete(couponId);
        }
		
		map.addAttribute("order", order);
		setPayTypes(map, true, false, req); // gl

		return "/client/order_ship_5";
	}

}
