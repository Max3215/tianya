package com.ynyes.tianya.controller.management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdPrice;
import com.ynyes.tianya.entity.TdTimePrice;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdPriceService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdTimePriceService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 价格日历表控制器
 * @author Max
 *
 */
@Controller
@RequestMapping(value = "/Verwalter")
public class TdManagerTimePriceController {
	
	@Autowired
	private TdTimePriceService tdTimePriceService;
	
	@Autowired
	private TdGoodsService tdGoodsService;
	
	@Autowired
	private TdProductCategoryService tdProductCategoryService;
	
	@Autowired
	private TdManagerService tdManagerService;
	
	@Autowired
	private TdManagerRoleService tdManagerRoleService;
	
	@Autowired
    TdManagerLogService tdManagerLogService;
	
	@Autowired
	private TdPriceService tdPriceService;
	
	@RequestMapping(value="/timePrice/list")
	public String list(Long categoryId, 
					Integer page,
            		Integer size,
			 		String __EVENTTARGET,
			 		String __EVENTARGUMENT, 
			 		String __VIEWSTATE, 
			 		String keywords,
			 		Long[] listId,
                    Integer[] listChkId,
	            ModelMap map, HttpServletRequest req){
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        //管理员角色
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager.getRoleId())
        {
            tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        
        if (null != tdManagerRole) {
			map.addAttribute("tdManagerRole", tdManagerRole);
		}
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除价目表", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0) {
            page = 0;
        }

        if (null == size || size <= 0) {
            size = SiteMagConstant.pageSize;
        }

        map.addAttribute("timePrice_page", tdPriceService.findAll(categoryId, keywords, page, size));
        map.addAttribute("category_list", tdProductCategoryService.findAll());
        
        map.addAttribute("categoryId", categoryId);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/time_price_list";
	}
	
	@RequestMapping(value="/timePrice/edit")
	public String edit(Long id,Integer page, Integer size,
			String __EVENTTARGET,
            String __EVENTARGUMENT, String __VIEWSTATE, ModelMap map,
            HttpServletRequest req){
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null == page || page < 0) {
			page = 0;
		}

		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        map.addAttribute("category_list", tdProductCategoryService.findAll());
        
        if(null !=id){
        	TdPrice price = tdPriceService.findOne(id);
        	
        	TdGoods goods = tdGoodsService.findOne(price.getGoodsId());
            if(null != goods && goods.getIsOnSale())
            {
            	map.addAttribute("sale", "true");
            }
        	map.addAttribute("timePrice", price);
        	if(null!=price&&null!=price.getCateId()){
        		map.addAttribute("goods_list", tdGoodsService.findByCategoryIdTreeContaining(price.getCateId()));
        	}
        }
		
		return "/site_mag/dialog_price_list";
	}
	
	/**
	 * @author Max
	 * 保存、修改区间价格表
	 * 
	 */
	@RequestMapping(value="/timePrice/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> timePricEeit(Long id,
							Long goodsId,String price,
							String childPrice,
							String[] start,String[] end, 
							HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("code", 1);
		
		
		TdGoods goods = tdGoodsService.findOne(goodsId);
		if(null == goods)
		{
			res.put("message", "商品参数错误");
			return res;
		}
//		if(null == cateId)
//		{
//			res.put("message", "商品类型参数错误");
//			return res;
//		}
		
//		if(null == start || null == end){
//			res.put("message", "时间录入错误，请仔细检查");
//			return res;
//		}
//		
		if(!examineTime(start,end)){
			res.put("message", "时间录入错误，请仔细检查");
			return res;
		}
		
		// 取出该商品的所有区间价格表
		List<TdPrice> goodsList = tdPriceService.findByGoodsId(goodsId);
		
		List<Date> startTimeList = getTImeList(start);
		List<Date> endTimeList = getTImeList(end);
		
		if(!goodsTimePriceCompare(goodsList, startTimeList, endTimeList, id)){
			res.put("message", "录入时间与现有价目表冲突");
			return res;
		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
//		if(null == startTime || null == endTime)
//		{
//			res.put("message", "时间录入错误，请仔细检查");
//			return res;
//		}
		
//		if(startTime.getTime() > endTime.getTime()){
//			res.put("message", "时间录入错误，请仔细检查");
//			return res;
//		}
		
		for (int l = 0; l < startTimeList.size(); l++) {
			Date startTime = startTimeList.get(l);
			Date endTime = endTimeList.get(l) ;
			
			// 计算时间区间天数
			long time = endTime.getTime() - startTime.getTime();
			long days = time / (1000 * 60 * 60 * 24); 
			
			TdPrice tdPrice = new TdPrice();
			List<TdTimePrice> priceList = new ArrayList<>();
			
			// 如果是修改，查出原记录，同时删除价格表
			if(null != id){
				tdPrice = tdPriceService.findOne(id);
				tdTimePriceService.deleteByPriceId(id);
			}
			
			tdPrice.setCategoryId(goods.getCategoryId());
			tdPrice.setCategoryIdTree(goods.getCategoryIdTree());
//			tdPrice.setCateId(cateId);
			tdPrice.setChildPrice(new Double(childPrice));
			tdPrice.setEndTime(endTime);
			tdPrice.setGoodsId(goodsId);
			tdPrice.setGoodsTitle(goods.getSubTitle());
			tdPrice.setPrice(new Double(price));
			tdPrice.setStartTime(startTime);

			// 保存区间表 先保存才有ID 
			tdPrice = tdPriceService.save(tdPrice);
			for (int i = 0; i <= days; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startTime);
				calendar.add(Calendar.DAY_OF_YEAR, +i);
				Date newTime = calendar.getTime();

				TdTimePrice timePrice = new TdTimePrice();
				timePrice.setGoodsTitle(goods.getTitle());
				timePrice.setPrice(new Double(price));
				timePrice.setChildPrice(new Double(childPrice));
				timePrice.setTime(newTime);
				timePrice.setGoodsId(goodsId);
//				timePrice.setCateId(cateId);
				timePrice.setCategoryId(goods.getCategoryId());
				timePrice.setCategoryIdTree(goods.getCategoryIdTree());
				timePrice.setPriceId(tdPrice.getId());
				
				tdTimePriceService.save(timePrice);
				
				priceList.add(timePrice);
			}
		}
		
		res.put("message", "修改成功");
		res.put("goodsId", goodsId);
		res.put("code", 0);
		return res;
	}
	
	/**
	 * 添加时间与数据库已有数据判断
	 * @param goodsList
	 * @param strartTimeList
	 * @param endTimeList
	 * @param id
	 * @return
	 */
	private boolean goodsTimePriceCompare(List<TdPrice> goodsList,List<Date> strartTimeList,List<Date> endTimeList, Long id)
	{
		if(null != goodsList && goodsList.size() > 0 ){
			for (TdPrice tdPrice : goodsList) {
				Date oldStart = tdPrice.getStartTime();
				Date oldEnd = tdPrice.getEndTime();
				for (int i = 0; i < strartTimeList.size(); i++) {
					// 如果新录入的起始时间在原记录的时间段内
					if(null == id || !tdPrice.getId().equals(id))
					{
						if(strartTimeList.get(i).getTime() >= oldStart.getTime() && strartTimeList.get(i).getTime() <= oldEnd.getTime())
						{
							return false;
						}
						if(endTimeList.get(i).getTime() >= oldStart.getTime() && endTimeList.get(i).getTime() <= oldEnd.getTime())
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 验证输入时间是否存在交叉时间
	 * @param starts
	 * @param ends
	 * @return
	 */
	private boolean examineTime(String[] starts, String[] ends) {
		if(null == starts || null == ends){
			return false;
		}
		
		if(starts.length == 0 || ends.length == 0){
			return false;
		}
		
		List<Date> startTimes = getTImeList(starts); // 开始时间集合
		List<Date> endTimes = getTImeList(ends); // 结束时间计划
		
		for (int i = 0; i < startTimes.size(); i++) 
		{
			for (int j = 0; j < endTimes.size(); j++) 
			{
				// 同条记录
				if(!firstTimeCompare(startTimes.get(i), endTimes.get(i)))
				{
					return false;	
				}
				// 对比其它时间
				if(i > 0 && j != 0 && i-j >= 0){
					// 开始时间与之前其它时间对比，如在之前时间区间内，时间输入冲突，返回false
					if(!otherTimeCompare(startTimes.get(i),startTimes.get(i-j), endTimes.get(i-j)))
					{
						return false;	
					}
					// 结束时间与之前其它时间对比，如在之前时间区间内，时间输入冲突，返回false
					if(!otherTimeCompare(endTimes.get(i),startTimes.get(i-j), endTimes.get(i-j)))
					{
						return false;	
					}
				}
				
			}
		}
		
		return true;
	}
	
	private List<Date> getTImeList(String[] times){
		List<Date> timeList = new ArrayList<>();
		
		for (String start : times) {
			timeList.add(getTimeByString(start));
		}
		return timeList;
	}
	
	private boolean firstTimeCompare(Date startTime,Date endTime){
		
		if(startTime.getTime() > endTime.getTime()){
			return false;
		}
		return true;
	}
	
	private boolean otherTimeCompare(Date startTime,Date laststartTime,Date lastendTime){
		if(startTime.getTime() >=laststartTime.getTime() && startTime.getTime() <= lastendTime.getTime()){
			return false;
		}
		
		return true;
	}
	
	
	private Date getTimeByString(String dateStr)
	{
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/timePrice/goodslist")
	public String goodslist(Long cateid,Integer page, Integer size,
			String __EVENTTARGET,
            String __EVENTARGUMENT, String __VIEWSTATE, ModelMap map,
            HttpServletRequest req){
		String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null == page || page < 0) {
			page = 0;
		}

		if (null == size || size <= 0) {
			size = SiteMagConstant.pageSize;
		}

        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        if(null!=cateid){
        	map.addAttribute("goods_list", tdGoodsService.findByCategoryIdTreeContaining(cateid));
        }
		
		return "/site_mag/dialog_goods_list";
	}
	
	@RequestMapping("/timePrice/search")
	public String goodsPrice(Long goodsId,HttpServletRequest req,ModelMap map){
		String username = (String) req.getSession().getAttribute("manager");
		if(null != goodsId){
			map.addAttribute("timePriceList", tdPriceService.findByGoodsId(goodsId));
			
			TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
	        if (null != tdManager && null != tdManager.getRoleId())
	        {
	            map.addAttribute("managerRole", tdManagerRoleService.findOne(tdManager.getRoleId()));
	        }
	        map.addAttribute("goods", tdGoodsService.findOne(goodsId));
		}
		return "/site_mag/goods_price";
	}
	
	@RequestMapping(value = "/timePrice/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(Long id,HttpServletRequest req,ModelMap map){
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != id){
			TdPrice price = tdPriceService.findOne(id);
			res.put("goodsId", price.getGoodsId());
			deletePrice(id);
			res.put("code", 1);
		}
		return res;
	}
	
	@RequestMapping(value = "/timePrice/deleteAll",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteAll(String listId,
            String listChkId,HttpServletRequest req,ModelMap map){
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null == listChkId || null == listId || listChkId.isEmpty() || listId.isEmpty()){
			return res;
		}
		
		String[] listchIds = listChkId.split(",");
		String[] listIds = listId.split(",");
		
		List<Integer> chkIds = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		
		for (int i =0; i < listchIds.length;i++) {
			chkIds.add(Integer.parseInt(listchIds[i]));
		}
		for (int i =0; i < listIds.length;i++) {
			ids.add(Long.parseLong(listIds[i]));
		}
		
		DeleteAll(ids, chkIds);
		
		res.put("code", 1);
		return res;
	}
	
	@RequestMapping("/price/edit")
	public String editPrice(Long id,String type,HttpServletRequest re,ModelMap map)
	{
		if("edit".equalsIgnoreCase(type)){
			TdPrice price = tdPriceService.findOne(id);
			map.addAttribute("timePrice", price);
			map.addAttribute("goodsId", price.getGoodsId());
		}else{
			map.addAttribute("goodsId", id);
		}
		return "/site_mag/dialog_price";
	}
	
	private void DeleteAll(List<Long> ids, List<Integer> chkIds)
    {
        if (null == ids || null == chkIds
                || ids.size() < 1 || chkIds.size() < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.size() > chkId)
            {
                Long id = ids.get(chkId);
                System.err.println(id);
                deletePrice(id);
            }
        }
    }
	
	private void btnDelete(Long[] ids, Integer[] chkIds)
    {
        if (null == ids || null == chkIds
                || ids.length < 1 || chkIds.length < 1)
        {
            return;
        }
        
        for (int chkId : chkIds)
        {
            if (chkId >=0 && ids.length > chkId)
            {
                Long id = ids[chkId];
                System.err.println(id);
                deletePrice(id);
            }
        }
    }
	
	public void deletePrice(Long id)
	{
		TdPrice price = tdPriceService.findOne(id);
        TdGoods goods = tdGoodsService.findOne(price.getGoodsId());
        if(null != goods && !goods.getIsOnSale())
        {
        	tdPriceService.delete(id);
        	tdTimePriceService.deleteByPriceId(id);
        }
	}
	
}
