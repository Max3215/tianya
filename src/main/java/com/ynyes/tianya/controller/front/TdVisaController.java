package com.ynyes.tianya.controller.front;

import static org.apache.commons.lang3.StringUtils.leftPad;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserLevel;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdUserLevelService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * lulu
 * 签证
 *
 */
@Controller
@RequestMapping("/visa")
public class TdVisaController{
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdGoodsService tdGoodsService;

	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;

	@Autowired
	private TdUserLevelService tdUserLevelService;
	
	String filepath = SiteMagConstant.imagePath;

	@RequestMapping("/{goodsId}")
	public String visa(@PathVariable Long goodsId, String date, HttpServletRequest req, ModelMap map) throws Exception {
		String username = (String)req.getSession().getAttribute("username");
		if(null==username){
			return "redirect:/login";
		}
		
		tdCommonService.setCommon(map, req);
		TdUser user = tdUserService.findByUsername(username); 
		map.addAttribute("user", user);
		TdGoods goods = tdGoodsService.findByGoodsId(goodsId);
		map.addAttribute("goods", goods);
		
		return "/client/visa_order";
	}
	
	@RequestMapping("/order2")
	public String visa1(HttpServletRequest req, ModelMap map,String txtcountry) throws Exception {
		List<TdGoods> tdGoodsList = tdGoodsService.findByTitleContainigAndCategoryIdTreeContaining(txtcountry,"[23]");
		Long categoryId = 23L;
		if(tdGoodsList != null && tdGoodsList.size() > 0){
			categoryId = tdGoodsList.get(0).getCategoryId();
		}
		
//		String redirectStr = ;
		
		return "redirect:/list/" + categoryId + "?keyword=" + txtcountry;
		//return "redirect:/login";
		/*
		String username = (String)req.getSession().getAttribute("username"); 
		if(null==username){
			return "redirect:/login";
		}
		
		
//		List<TdProductCategory> visas = tdProductCategoryService.findByParentIdOrderBySortIdAsc(23L);
//		
//		if(txtcountry == null || txtcountry.equals("")){
//			return "redirect:/list/23";
//		}else{
//			for(TdProductCategory pc : visas){
//				if(pc.getTitle().equals(txtcountry)){
//					return "redirect:/list/" + pc.getId();
//				}
//			}
//			return "/client/error_404";
//		}
		try{
			tdCommonService.setCommon(map, req);
			TdUser user = tdUserService.findByUsername(username); 
			map.addAttribute("user", user);
			TdGoods goods = tdGoodsService.findByTitleAndCategoryIdTreeContaining(txtcountry,"[23]");
			map.addAttribute("goods", goods);
			if(null==goods){
				return "/client/error_404";
			}
			
			return "/client/visa_detail";
		}catch(Exception e){
			List<TdGoods> goods = tdGoodsService.findByTitleAndCategoryIdTreeContaining2(txtcountry,"[23]");
			for(TdGoods good : goods){
				return "redirect:/list/"+good.getCategoryId();
			}
			return "/client/error_404";
		}
		*/
	}
	
	@RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    public String visaOrder(HttpServletRequest req,
    						ModelMap map,
    						Double totalPrice,
    						Long goodsId,
    						String goodsTitle,
    						String orderType,
    						String dataUpload,
    						String shippingName,//姓名
    						String shippingPhone,//电话
    						String leavePort//邮箱
    						) throws ParseException {
        String username = (String) req.getSession().getAttribute("username");
        if(null==username){
        	return "redirect:/login";
        }
        tdCommonService.setCommon(map, req);
        TdOrder order = new TdOrder();
        order.setUsername(username);
        order.setDataUpload(dataUpload);
        order.setShippingName(shippingName);
        order.setShippingPhone(shippingPhone);
        order.setLeavePort(leavePort);
        order.setGoodsTitle(goodsTitle);
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
//     				if (discountRatio != null) {
//     					map.addAttribute("discountRatio", discountRatio);
//     				}
     			}
     		}
     		// 修改订单总价
     		if (discountRatio != null) {
     			order.setTotalPrice(totalPrice * discountRatio);
     		}
        
        
        
        
        order.setStatusId(2L);
        order.setTypeId(3L);
        order.setOrderType("签证");
        order.setOrderTime(current);
        order.setOrderType(orderType);
        tdOrderService.save(order);
        
        map.addAttribute("order", order);
        
        return "/client/bus_submit_success";
	}
	
	@RequestMapping(value="/download/data", method = RequestMethod.GET)
    @ResponseBody
    public void download(String fileName,HttpServletResponse resp,HttpServletRequest req) throws IOException {
        if (null == fileName || fileName.isEmpty())
        {
            return;
        }
        
        String name = fileName.substring(8);
        
        OutputStream os = resp.getOutputStream();  
        
        File file = new File(filepath +"/" + name);
        
        if (file.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                		+URLEncoder.encode(name, "UTF-8"));
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
        else 
        {
        	return;
        }
    }

	
}
