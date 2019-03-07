package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdDeliveryType;
import com.ynyes.tianya.entity.TdDiySite;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdOrderGoods;
import com.ynyes.tianya.entity.TdPayType;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.entity.TdUserPoint;
import com.ynyes.tianya.service.TdArticleService;
import com.ynyes.tianya.service.TdDeliveryTypeService;
import com.ynyes.tianya.service.TdDiySiteService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdOrderService;
import com.ynyes.tianya.service.TdPayTypeService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdUserPointService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.util.SiteMagConstant;
import com.ynyes.tianya.util.StatisticalUtil;
/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/order")
public class TdManagerOrderController {
    
    @Autowired
    TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdGoodsService tdGoodsService;
    
    @Autowired
    TdPayTypeService tdPayTypeService;
    
    @Autowired
    TdDeliveryTypeService tdDeliveryTypeService;
    
    @Autowired
    TdDiySiteService tdDiySiteService;
    
    @Autowired
    TdUserPointService tdUserPointService;
    
    @Autowired
    TdOrderService tdOrderService;
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
    
    //销售量统计
    @RequestMapping(value="/numStatis")
    public String numStatis(HttpServletRequest request ,Model model ,HttpServletResponse response){
    	response.setHeader("Content-type", "text/html;charset=UTF-8");
    	
    	String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

     // 订单类型 0：邮轮俱乐部订单1：目的地订单 2：主题活动订单 3：签证订单  4：汽车租赁订单 5：旅游直通车订单 6：特产商城订单
//        @Column
//        private Long typeId;
        
    	Map<String, Long> data = new HashMap<>();
    	for(int typeId = 0; typeId <= 6; typeId ++){
    		switch (typeId) {
    		case 0:
    			data.put("邮轮俱乐部订单", countNum(new Long(typeId)));
    			break;
			case 1:
				data.put("目的地订单 ", countNum(new Long(typeId)));
				break;
			case 2:
				data.put("主题活动订单", countNum(new Long(typeId)));
				break;
			case 3:
				data.put("签证订单", countNum(new Long(typeId)));
				break;
			case 4:
				data.put("汽车租赁订单 ", countNum(new Long(typeId)));
				break;
			case 5:
				data.put("旅游直通车订单", countNum(new Long(typeId)));
				break;
			case 6:
				data.put("特产商城订单", countNum(new Long(typeId)));
				break;
			default:
				break;
			}
    	}
    	String title = "销量";
    	String pieChartImagePath =  new StatisticalUtil<Long>().createSimplePieChart(data, title);
    	String barChartImagePath = new StatisticalUtil<Long>().createSimpleBarChart(data, title);
    	model.addAttribute("pieChartImagePath", "/images/"+title+"统计饼图.jpg");
    	model.addAttribute("barChartImagePath", "/images/"+title+"统计条形图.jpg");
    	
    	return "/site_mag/numstatis";
    }
    
    
  //销售额统计
    @RequestMapping(value="/priceStatis")
    public String priceStatis(HttpServletRequest request ,Model model){
    	
    	String username = (String) request.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
    	
    	Map<String, Double> data1 = new HashMap<>();
    	for(int i = 1; i <= 2; i ++){
    		switch (i) {
			case 1:
				data1.put("未销售额", countUnsales());
				break;
			case 2:
				data1.put("销售额", countPrice(0L) - countUnsales());
				break;
			default:
				break;
			}
    	}
    	
    	String title = "销售额";
    	String pieChartImagePath = new  StatisticalUtil<Double>().createSimplePieChart(data1, title);
    	String barChartImagePath = new StatisticalUtil<Double>().createSimpleBarChart(data1, title);
    	model.addAttribute("pieChartImagePath1", "/images/"+title+"统计饼图.jpg");
    	model.addAttribute("barChartImagePath1", "/images/"+title+"统计条形图.jpg");
    	
    	Map<String, Double> data2 = new HashMap<>();
    	title = "销售额(状态)";
    	
    	// 订单状态 1:待确认 2:待付款 3:待服务 4:待评价 5: 已完成 6: 已取消
//        @Column
//        private Long statusId;
    	for(int statusId = 1; statusId <= 6; statusId ++){
    		switch (statusId) {
			case 1:
				data2.put("待确认", countPrice(new Long(statusId)));
				break;
			case 2:
				data2.put("待付款 ", countPrice(new Long(statusId)));
				break;
			case 3:
				data2.put("待服务", countPrice(new Long(statusId)));
				break;
			case 4:
				data2.put("待评价", countPrice(new Long(statusId)));
				break;
			case 5:
				data2.put("已完成", countPrice(new Long(statusId)));
				break;
			case 6:
				data2.put("已取消", countPrice(new Long(statusId)));
				break;
			default:
				break;
			}
    	}
    	
    	String pieChartImagePath2 = new  StatisticalUtil<Double>().createSimplePieChart(data2, title);
    	String barChartImagePath2 = new StatisticalUtil<Double>().createSimpleBarChart(data2, title);
    	model.addAttribute("pieChartImagePath2", "/images/"+title+"统计饼图.jpg");
    	model.addAttribute("barChartImagePath2", "/images/"+title+"统计条形图.jpg");
    	
    	return "/site_mag/pricestatis";
    }
    
    // 订单设置
    @RequestMapping(value="/setting/{type}/list")
    public String setting(@PathVariable String type, 
                          Integer page,
                          Integer size,
                          String keywords,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
                          Long[] listId,
                          Integer[] listChkId,
                          Long[] listSortId,
                          ModelMap map,
                          HttpServletRequest req){
    	
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
                btnDelete(type, listId, listChkId);
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdManagerLogService.addLog("delete", "删除支付方式", req);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdManagerLogService.addLog("delete", "删除配送方式", req);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdManagerLogService.addLog("delete", "删除自提点", req);
                }
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(type, listId, listSortId);
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdManagerLogService.addLog("edit", "修改支付方式", req);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdManagerLogService.addLog("edit", "修改配送方式", req);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdManagerLogService.addLog("edit", "修改自提点", req);
                }
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
                
        if (null != type)
        {
            if (type.equalsIgnoreCase("pay")) // 支付方式
            {
                if (null == keywords)
                {
                    map.addAttribute("pay_type_page", 
                            tdPayTypeService.findAllOrderBySortIdAsc(page, size));
                }
                else
                {
                    map.addAttribute("pay_type_page", 
                            tdPayTypeService.searchAllOrderBySortIdAsc(keywords, page, size));
                }
                
                return "/site_mag/pay_type_list";
            }
            else if (type.equalsIgnoreCase("delivery")) // 配送方式
            {
                if (null == keywords)
                {
                    map.addAttribute("delivery_type_page", 
                            tdDeliveryTypeService.findAllOrderBySortIdAsc(page, size));
                }
                else
                {
                    map.addAttribute("delivery_type_page", 
                            tdDeliveryTypeService.searchAllOrderBySortIdAsc(keywords, page, size));
                }
                
                return "/site_mag/delivery_type_list";
            }
            else if (type.equalsIgnoreCase("diysite")) // 配送方式
            {
                if (null == keywords)
                {
                    map.addAttribute("diy_site_page", 
                            tdDiySiteService.findAllOrderBySortIdAsc(page, size));
                }
                else
                {
                    map.addAttribute("diy_site_page", 
                            tdDiySiteService.searchAllOrderBySortIdAsc(keywords, page, size));
                }
                
                return "/site_mag/diy_site_list";
            }
        }
        
        return "/site_mag/pay_type_list";
    }
    
    // 订单设置编辑
    @RequestMapping(value="/setting/{type}/edit")
    public String edit(@PathVariable String type, 
                        Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != type)
        {
            if (type.equalsIgnoreCase("pay")) // 支付方式
            {
                if (null != id)
                {
                    map.addAttribute("pay_type", tdPayTypeService.findOne(id));
                }
                
                return "/site_mag/pay_type_edit";
            }
            else if (type.equalsIgnoreCase("delivery")) // 配送方式
            {
                if (null != id)
                {
                    map.addAttribute("delivery_type", tdDeliveryTypeService.findOne(id));
                }
                
                return "/site_mag/delivery_type_edit";
            }
            else if (type.equalsIgnoreCase("diysite")) // 自提点
            {
                if (null != id)
                {
                    map.addAttribute("diy_site", tdDiySiteService.findOne(id));
                }
                
                return "/site_mag/diy_site_edit";
            }
        }
        
        return "/site_mag/pay_type_edit";
    }
    
    // 订单设置编辑
    @RequestMapping(value = "/setting/diysite/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> validateForm(String param, Long id) {
        Map<String, String> res = new HashMap<String, String>();

        res.put("status", "n");

        if (null == param || param.isEmpty()) {
            res.put("info", "该字段不能为空");
            return res;
        }
        
        TdUser tdUser = tdUserService.findByUsername(param);
        
        if (null == id) // 新增
        {
            if (null != tdUser) {
                res.put("info", "该登录名不能使用");
                return res;
            }
        } 
        else // 修改，查找除当前ID的所有
        {
            TdDiySite dSite = tdDiySiteService.findOne(id);
            
            if (null == dSite)
            {
                if (null != tdUser && tdUser.getRoleId() == 2L) {
                    res.put("info", "该登录名不能使用");
                    return res;
                }
            }
            else
            {
                if (null != tdUser && tdUser.getUsername() != dSite.getUsername() && tdUser.getRoleId()!=2L) {
                    res.put("info", "该登录名不能使用");
                    return res;
                }
            }
        }

        res.put("status", "y");

        return res;
    }
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        TdManager tdManager = tdManagerService.findByUsernameAndIsEnableTrue(username);
        TdManagerRole tdManagerRole = null;
        
        if (null != tdManager && null != tdManager.getRoleId())
        {
        	tdManagerRole = tdManagerRoleService.findOne(tdManager.getRoleId());
        }
        map.addAttribute("tdManagerRole", tdManagerRole);
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
    	
        TdOrder order = tdOrderService.findOne(id);
        map.addAttribute("order", order);
        TdUser user = tdUserService.findByUsername(order.getUsername());
        map.addAttribute("user", user);
        //不同的订单类型，订单详情页面不一样 mr.zhu
        if(order.getTypeId()!=null&&!order.getTypeId().equals("")){
		    if(order.getTypeId()==0L||order.getTypeId()==1L||order.getTypeId()==2L || order.getTypeId()==3L){
		    	return "/site_mag/order_edit";
		    }else if(order.getTypeId()==3L){
		    	return "/site_mag/order_visa_edit";
		    }else if(order.getOrderType().equals("汽车租赁")){
		    	map.addAttribute("goods", tdGoodsService.findOne(order.getGoodsId()));
		    	return "/site_mag/order_rent_edit";
		    }else if(order.getOrderType().equals("客车包租")){
		    	map.addAttribute("goods", tdGoodsService.findOne(order.getGoodsId()));
		    	return "/site_mag/order_rent_bus_edit";
		    }else if(order.getTypeId()==5L){
		    	return "/site_mag/order_through_edit";
		    }else if(order.getTypeId()==6L){
		    	return "/site_mag/order_special_edit";
		    }
        }
        return "/site_mag/order_edit";
    }
    
    @RequestMapping(value="/save")
    public String orderEdit(TdOrder tdOrder,
                        Long statusId,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("statusId", statusId);
        
        tdOrderService.save(tdOrder);
        
        tdManagerLogService.addLog("edit", "修改订单", req);
        
        return "redirect:/Verwalter/order/list/"+statusId;
    }
    
    
    // 订单列表
    //	typeDd => 1:邮轮俱乐部2:目的地3:主题活动4:签证5:汽车租赁6:旅游直通车(td_goods.category_title)
    @SuppressWarnings("deprecation")
	@RequestMapping(value="/list/{statusId}/{type}")
    public String goodsListDialog(String keywords,
			            @PathVariable Long statusId,
			            @PathVariable Long type,
			            Integer page, 
			            Integer size,
			            String start,
			            String end,
			            Long typeId,
			            String __EVENTTARGET,
			            String __EVENTARGUMENT,
			            String __VIEWSTATE,
			            Long[] listId,
			            Integer[] listChkId,
			            ModelMap map,
			            String exportUrl,
			//            String dateId,
			            HttpServletResponse resp,
			            HttpServletRequest req){
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
            if (__EVENTTARGET.equalsIgnoreCase("btnCancel"))
            {
                btnCancel(listId, listChkId);
                tdManagerLogService.addLog("cancel", "取消订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnConfirm"))
            {
                btnConfirm(listId, listChkId);
                tdManagerLogService.addLog("confirm", "确认订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出订单", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
        }
        
        if (null == page || page < 0)
        {
            page = 0;
        }
        
        if (null == size || size <= 0)
        {
            size = SiteMagConstant.pageSize;;
        }
        
        Date startTime =null; // 起始时间
        Date endTime = null; // 截止时间
        if(null != start && !"".equals(start.trim()))
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	try {
				startTime = sdf.parse(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        
        if(null != end && !"".equals(end.trim()))
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	try {
				endTime = sdf.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
//        	endTime= new Date(end);
        }
        
        /**
  		 * @author lc
  		 * @注释：根据不同条件导出excel文件
  		 */
          // 第一步，创建一个webbook，对应一个Excel文件  
          HSSFWorkbook wb = new HSSFWorkbook();  
          // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
          HSSFSheet sheet = wb.createSheet("order");  
          // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
          HSSFRow row = sheet.createRow((int) 0);  
          // 第四步，创建单元格，并设置值表头 设置表头居中  
          HSSFCellStyle style = wb.createCellStyle();  
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
          
          HSSFCell cell = row.createCell((short) 0);  
          cell.setCellValue("用户名");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue("用户等级");  
          cell.setCellStyle(style); 
          cell = row.createCell((short) 2);  
          cell.setCellValue("联系方式");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 3);  
          cell.setCellValue("邮箱");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 4);  
          cell.setCellValue("选择时间");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 5);  
          cell.setCellValue("订单编号");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 6);  
          cell.setCellValue("下单时间");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 7);  
          cell.setCellValue("订单类型");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 8);  
          cell.setCellValue("服务项");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 9);  
          cell.setCellValue("支付金额");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 10);  
          cell.setCellValue("支付方式");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 11);  
          cell.setCellValue("订单状态");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 12);  
          cell.setCellValue("使用优惠券");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 13);  
          cell.setCellValue("备注");  
          cell.setCellStyle(style);
          
          if(null == typeId)
          {
          	if(null == startTime)
  	        {
  	        	if(null == endTime)
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",
  	        							tdOrderService.searchByOrderNumber(keywords, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumber(keywords,page,size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 关键字订单筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findAllOrderByIdDesc(page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findAllOrderByIdDesc(page,size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单END
  	        			}// 所有状态END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusOrderByIdDesc(keywords, statusId, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusOrderByIdDesc(keywords, statusId, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态关键字筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusOrderByIdDesc(statusId, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusOrderByIdDesc( statusId, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单
  	        			}
  	        		}
  	        	} // 未选结尾时间END
  	        	else
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",
  	        							tdOrderService.searchByOrderNumberAndOrderTimeBefore(keywords, endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndOrderTimeBefore(keywords, endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 关键字+时间截止日期订单筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByOrderTimeBeforeOrderByIdDesc(endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByOrderTimeBeforeOrderByIdDesc(endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单+时间截止筛选END
  	        			}// 所有状态+时间截止 END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndOrderTimeBeforeOrderByIdDesc(keywords, statusId,endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndOrderTimeBeforeOrderByIdDesc(keywords, statusId,endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态+关键字+截止时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndOrderTimeBeforeOrderByIdDesc(statusId,endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndOrderTimeBeforeOrderByIdDesc(statusId,endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单+ 截止时间筛选END
  	        			}
  	        		}
  	        	} // 截止时间END
  	        }// 未选开始时间筛选END
  	        else
  	        {
  	        	if(null == endTime)
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.searchByOrderNumberAndOrderTimeAfter(keywords,startTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndOrderTimeAfter(keywords,startTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 关键字订单+ 起始时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByOrderTimeAfterOrderByIdDesc(startTime,page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByOrderTimeAfterOrderByIdDesc(startTime,page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单+起始时间END
  	        			}// 所有状态END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndOrderTimeAfterOrderByIdDesc(keywords, statusId,startTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndOrderTimeAfterOrderByIdDesc(keywords, statusId,startTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态关键字+起始时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndOrderTimeAfterOrderByIdDesc(statusId, startTime,page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndOrderTimeAfterOrderByIdDesc(statusId, startTime,page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单+起始时间
  	        			}
  	        		}
  	        	} // 起始时间+未选结尾时间END
  	        	else
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.searchByOrderNumberAndOrderTimeDetween(keywords,startTime, endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndOrderTimeDetween(keywords,startTime, endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 起始时间+关键字+时间截止日期订单筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByOrderTimeBetweenOrderByIdDesc(startTime,endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByOrderTimeBetweenOrderByIdDesc(startTime,endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单+起始时间+时间截止筛选END
  	        			}// 所有状态+起始时间+时间截止 END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndOrderTimeBetweenOrderByIdDesc(keywords, statusId,startTime,endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndOrderTimeBetweenOrderByIdDesc(keywords, statusId,startTime,endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态+关键字+起始时间+截止时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndOrderTimeBetweenOrderByIdDesc(statusId,startTime,endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndOrderTimeBetweenOrderByIdDesc(statusId,startTime,endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单+ 截止时间筛选END
  	        			}
  	        		}
  	        	} // 截止时间END
  	        }// 起始时间+。。。END 
          }// 未选订单类型筛选END -----------------------------------------------------------------------------
          else
          {
  	        if(null == startTime)
  	        {
  	        	if(null == endTime)
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",
  	        							tdOrderService.searchByOrderNumberAndTypeId(keywords,typeId, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndTypeId(keywords,typeId, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 关键字订单筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByTypeIdOrderByIdDesc(typeId,page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByTypeIdOrderByIdDesc(typeId,page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单END
  	        			}// 所有状态END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndTypeIdOrderByIdDesc(keywords, statusId,typeId, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndTypeIdOrderByIdDesc(keywords, statusId,typeId, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态关键字筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndTypeOrderByIdDesc(statusId, typeId, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTypeOrderByIdDesc(statusId, typeId, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单
  	        			}
  	        		}
  	        	} // 未选结尾时间END
  	        	else
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",
  	        							tdOrderService.searchByOrderNumberAndTypeIdAndOrderTimeBefore(keywords,typeId, endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndTypeIdAndOrderTimeBefore(keywords,typeId, endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 关键字+时间截止日期订单筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByTypeIdAndOrderTimeBeforeOrderByIdDesc(typeId,endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByTypeIdAndOrderTimeBeforeOrderByIdDesc(typeId,endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单+时间截止筛选END
  	        			}// 所有状态+时间截止 END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndTypeIdAndOrderTimeBeforeOrderByIdDesc(keywords, statusId,typeId, endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndTypeIdAndOrderTimeBeforeOrderByIdDesc(keywords, statusId,typeId, endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态+关键字+截止时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndTypeIdAndOrderTimeBeforeOrderByIdDesc(statusId, typeId,  endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTypeIdAndOrderTimeBeforeOrderByIdDesc(statusId, typeId,  endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单+ 截止时间筛选END
  	        			}
  	        		}
  	        	} // 截止时间END
  	        }// 未选开始时间筛选END
  	        else
  	        {
  	        	if(null == endTime)
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.searchByOrderNumberAndTypeIdAndOrderTimeAfter(keywords, typeId, startTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndTypeIdAndOrderTimeAfter(keywords, typeId, startTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 关键字订单+ 起始时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByTypeIdAndOrderTimeAfterOrderByIdDesc(typeId, startTime,page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByTypeIdAndOrderTimeAfterOrderByIdDesc(typeId, startTime,page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单+起始时间END
  	        			}// 所有状态END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndTypeIdAndOrderTimeAfterOrderByIdDesc(keywords, statusId, typeId, startTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndTypeIdAndOrderTimeAfterOrderByIdDesc(keywords, statusId, typeId, startTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态关键字+起始时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndTypeIdAndOrderTimeAfterOrderByIdDesc(statusId, typeId, startTime,page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTypeIdAndOrderTimeAfterOrderByIdDesc(statusId, typeId, startTime,page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单+起始时间
  	        			}
  	        		}
  	        	} // 起始时间+未选结尾时间END
  	        	else
  	        	{
  	        		if(null != statusId)
  	        		{
  	        			if(statusId.equals(0L))
  	        			{
  	        				if(null != keywords)
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.searchByOrderNumberAndTypeIdAndOrderTimeDetween(keywords, typeId, startTime, endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndTypeIdAndOrderTimeDetween(keywords, typeId, startTime, endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 起始时间+关键字+时间截止日期订单筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByTypeIdAndOrderTimeBetweenOrderByIdDesc(typeId, startTime,endTime, page, size));
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByTypeIdAndOrderTimeBetweenOrderByIdDesc(typeId, startTime,endTime, page, size);
  	                				
  	                				if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 所有订单+起始时间+时间截止筛选END
  	        			}// 所有状态+起始时间+时间截止 END
  	        			else
  	        			{
  	        				if(null !=keywords)
  	        				{
  	        					map.addAttribute("order_page", tdOrderService.searchByOrderNumberAndStatusAndTypeIdAndOrderTimeBetweenOrderByIdDesc(keywords, statusId,typeId,startTime,endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.searchByOrderNumberAndStatusAndTypeIdAndOrderTimeBetweenOrderByIdDesc(keywords, statusId,typeId,startTime,endTime, page, size);
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态+关键字+起始时间+截止时间筛选END
  	        				else
  	        				{
  	        					map.addAttribute("order_page",tdOrderService.findByStatusAndTypeIdAndOrderTimeBetweenOrderByIdDesc(statusId, typeId, startTime,endTime, page, size));
  	        					
  	        					if(null != exportUrl)
  	        					{
  	        						Page<TdOrder> tdOrderPage = tdOrderService.findByStatusAndTypeIdAndOrderTimeBetweenOrderByIdDesc(statusId, typeId, startTime,endTime, page, size);//--
  	        						
  	        						if (ImportData(tdOrderPage, row, cell, sheet)) {
  	                					download(wb, username, resp);
  	                				}
  	        					}
  	        				} // 各状态所有订单+ 截止时间筛选END
  	        			}
  	        		}
  	        	} // 截止时间END
  	        }// 起始时间+。。。END 
          }// 订单状态END  
          
          
        // 参数注回
//        map.addAttribute("dateId",dateId);
//        map.addAttribute("price",price);
//        map.addAttribute("sales",sales);
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("keywords", keywords);
        map.addAttribute("statusId", statusId);
        /**
		 * @author lc
		 * @注释：添加时间删选参数
		 */
//        map.addAttribute("time_id", timeId);
        map.addAttribute("startime", startTime);
        map.addAttribute("endTime", endTime);
        map.addAttribute("typeId", typeId);
        map.addAttribute("type", type);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        return "/site_mag/order_list";
    }
    /**
	 * @author Max
	 * @注释：将page中的订单数据存入excel表格中
	 */
    @SuppressWarnings("deprecation")
	public boolean ImportData(Page<TdOrder> tdOrderPage, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	
    	for (int i = 0; i < tdOrderPage.getContent().size(); i++)  
        {  
    	 				
            row = sheet.createRow((int) i + 1);  
            TdOrder tdOrder = tdOrderPage.getContent().get(i);  
            //获取用户信息
            TdUser tdUser = tdUserService.findByUsername(tdOrder.getUsername());
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(tdOrder.getUsername());
            if (null != tdUser) {
            	row.createCell((short) 1).setCellValue(tdUser.getUserLevelTitle()); 
			}
            if(null != tdUser)
            {
            	row.createCell((short) 2).setCellValue(tdUser.getMobile());
            }
            if (null != tdUser) {
            	row.createCell((short) 3).setCellValue(tdUser.getEmail());
			}
            if (null != tdOrder.getLeaveDate()) {
            	cell = row.createCell((short) 4);  
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tdOrder.getLeaveDate()));
			}           
            if (null != tdOrder) {
            	 row.createCell((short) 5).setCellValue(tdOrder.getOrderNumber());
			} 
            if (null != tdOrder.getOrderTime()) {
            	cell = row.createCell((short) 6);  
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tdOrder.getOrderTime()));
			}
            if (null != tdOrder) {
            		row.createCell((short) 7).setCellValue(tdOrder.getOrderType());
			} 
            if (null != tdOrder.getOrderGoodsList()) {
            	cell = row.createCell((short) 8);
            	StringBuffer orderGoods = new StringBuffer();
            	for (TdOrderGoods og : tdOrder.getOrderGoodsList()) {
					orderGoods.append(og.getGoodsTitle()+",");
				}
            	cell.setCellValue(orderGoods.toString());
			} 
            if (null != tdOrder.getTotalPrice()) {
            	row.createCell((short) 9).setCellValue(tdOrder.getTotalPrice());
            } 
            if (null != tdOrder.getPayTypeTitle()) {
            	row.createCell((short) 10).setCellValue(tdOrder.getPayTypeTitle());
            }    
            if (tdOrder.getStatusId().equals(2L)) {
            	row.createCell((short) 11).setCellValue("待付款");
			}else if (tdOrder.getStatusId().equals(3L)) {
				row.createCell((short) 11).setCellValue("待服务");
			}else if (tdOrder.getStatusId().equals(4L)) {
				row.createCell((short) 11).setCellValue("待评价");
			}else if (tdOrder.getStatusId().equals(5L)) {
				row.createCell((short) 11).setCellValue("已完成 ");
			}else if (tdOrder.getStatusId().equals(6L)) {
				row.createCell((short) 11).setCellValue("已取消");
			} 
            if (null != tdOrder.getCouponTitle()) {
            	row.createCell((short) 12).setCellValue(tdOrder.getCouponTitle());
			}
            if (null != tdOrder.getUserRemarkInfo()) {
            	row.createCell((short) 13).setCellValue(tdOrder.getUserRemarkInfo());
			}
         
        } 
    	return true;
    }
    /**
	 * @author lc
	 * @注释：文件写入和下载
	 */
    public Boolean download(HSSFWorkbook wb, String exportUrl, HttpServletResponse resp){
    	 try  
         {  
	          FileOutputStream fout = new FileOutputStream(exportUrl+"order.xls");  
//	          OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");	                       	     
	          wb.write(fout);  
	          fout.close();
         }catch (Exception e)  
         {  
             e.printStackTrace();  
         } 
    	 OutputStream os;
		 try {
				os = resp.getOutputStream();
				File file = new File(exportUrl + "order.xls");
                 
             if (file.exists())
                 {
                   try {
                         resp.reset();
                         resp.setHeader("Content-Disposition", "attachment; filename="
                                 + "order.xls");
                         resp.setContentType("application/octet-stream; charset=utf-8");
                         os.write(FileUtils.readFileToByteArray(file));
                         os.flush();
                     } finally {
                         if (os != null) {
                             os.close();
                         }
                     }
             }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 return true;	
    }
    /**
	 * @author lc
	 * @注释：计算总额和销售额
	 */
    public Double countprice(List<TdOrder> list){
    	
    	Double price = new Double(0.00);       
    	for (int i = 0; i < list.size(); i++) {
    		if(list.get(i).getTotalPrice()!=null&&!list.get(i).getTotalPrice().equals("")){
    			price += list.get(i).getTotalPrice();
    		}
    	}
    	return price;
    }
    public Double countsales(List<TdOrder> list){
    	Double sales = new Double(0.00);
    	for(int i = 0; i < list.size(); i++){
    		if (list.get(i).getStatusId().equals(2L) || list.get(i).getStatusId().equals(7L)) {	
    			
			}
    		else{
    			sales += list.get(i).getTotalPrice();
    		}
    	}
    	return sales;
    }
    
    @RequestMapping(value="/setting/pay/save", method = RequestMethod.POST)
    public String save(TdPayType tdPayType,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdPayType.getId())
        {
            tdManagerLogService.addLog("add", "新增支付方式", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改支付方式", req);
        }
        tdPayTypeService.save(tdPayType);
        
        return "redirect:/Verwalter/order/setting/pay/list";
    }
    
    @RequestMapping(value="/setting/delivery/save", method = RequestMethod.POST)
    public String save(TdDeliveryType tdDeliveryType,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == tdDeliveryType.getId())
        {
            tdManagerLogService.addLog("add", "新增配送方式", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改配送方式", req);
        }
        
        tdDeliveryTypeService.save(tdDeliveryType);
        
        return "redirect:/Verwalter/order/setting/delivery/list";
    }
    
    @RequestMapping(value="/setting/diysite/save", method = RequestMethod.POST)
    public String save(TdDiySite tdDiySite,
                        String[] hid_photo_name_show360,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        String uris = parsePicUris(hid_photo_name_show360);
        
        tdDiySite.setShowPictures(uris);
        
        if (null == tdDiySite.getId())
        {
            tdManagerLogService.addLog("add", "新增同盟店", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "修改同盟店", req);
        }
        
        tdDiySiteService.save(tdDiySite);
        
        return "redirect:/Verwalter/order/setting/diysite/list";
    }
    
    @RequestMapping(value="/dialog/contact")
    public String addressDialog(ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        return "/site_mag/dialog_contact";
    }
    
    @RequestMapping(value="/dialog/delivery")
    public String sendDialog(String orderNumber, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != orderNumber && !orderNumber.isEmpty())
        {
            map.addAttribute("order", tdOrderService.findByOrderNumber(orderNumber));
        }
        
        map.addAttribute("delivery_type_list", tdDeliveryTypeService.findByIsEnableTrue());
        
        return "/site_mag/dialog_delivery";
    }
    
    @RequestMapping(value="/dialog/print")
    public String printDialog(String orderNumber, ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != orderNumber && !orderNumber.isEmpty())
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            map.addAttribute("order", order);
            map.addAttribute("now", new Date());
            map.addAttribute("manager", req.getSession().getAttribute("manager"));
            
            if (null != order)
            {
                map.addAttribute("user", tdUserService.findByUsernameAndIsEnabled(order.getUsername()));
                map.addAttribute("goods", tdGoodsService.findOne(order.getGoodsId()));
            }
        }
        
        return "/site_mag/dialog_order_print";
    }
    
    @RequestMapping(value="/param/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> paramEdit(String orderNumber,
                        String type,
                        String data,
                        String info,
                        String name,
                        String address,
                        String postal,
                        String mobile,
                        String expressNumber,
                        Long deliverTypeId,
                        Long changeOrderStatusId,
                        ModelMap map,
                        HttpServletRequest req){
        
        Map<String, Object> res = new HashMap<String, Object>();
        
        res.put("code", 1);
        
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            res.put("message", "请重新登录");
            return res;
        }
        
        if (null != orderNumber && !orderNumber.isEmpty() && null != type && !type.isEmpty())
        {
            TdOrder order = tdOrderService.findByOrderNumber(orderNumber);
            
            // 修改备注
            if (type.equalsIgnoreCase("editMark"))
            {
                order.setRemarkInfo(data);
            }
            
            if (null == order.getDeliverTypeFee())
            {
                order.setDeliverTypeFee(0.0);
            }
            
            if (null == order.getPayTypeFee())
            {
                order.setPayTypeFee(0.0);
            }
            
            // 修改商品总金额
            else if (type.equalsIgnoreCase("editTotalGoodsPrice"))
            {
                double goodsPrice = Double.parseDouble(data);
                order.setTotalPrice(goodsPrice);
                
                if (null != order.getPointUse() ) {
                	if (null != order.getCouponUse()) {
                		if (order.getPointUse() + order.getCouponUse() > goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee()) {
							order.setTotalPrice(0.0);
						}else{
							order.setTotalPrice(goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee() - order.getPointUse() - order.getCouponUse());
						}
					}else{
						if (order.getPointUse() > goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee()) {
							order.setTotalPrice(0.0);
						}else{
							order.setTotalPrice(goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee() - order.getPointUse());
						}
					}					
				}else if (null != order.getCouponUse()) {
					if (order.getCouponUse() > goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee()) {
						order.setTotalPrice(0.0);
					}else{
						order.setTotalPrice(goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee() - order.getCouponUse());
					}
				}
                else{
					order.setTotalPrice(goodsPrice + order.getPayTypeFee() + order.getDeliverTypeFee());
				}
                
                order.setTotalPriceChangeInfo(info);
                tdManagerLogService.addLog("edit", info, req);
            }
            
            // 修改预付金额
            else if (type.equalsIgnoreCase("Editprepay"))
            {
                double totalpreprice = Double.parseDouble(data);
                
                if (null != order.getPointUse() ) {
                	if (null != order.getCouponUse()) {
                		if (order.getPointUse() + order.getCouponUse() > totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee()) {
							order.setTotalPrice(0.0);
						}else{
							order.setTotalPrice(totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee() - order.getPointUse() - order.getCouponUse());
						}
					}else{
						if (order.getPointUse() > totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee()) {
							order.setTotalPrice(0.0);
						}else{
							order.setTotalPrice(totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee() - order.getPointUse());
						}
					}					
				}else if (null != order.getCouponUse()) {
					if (order.getCouponUse() > totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee()) {
						order.setTotalPrice(0.0);
					}else{
						order.setTotalPrice(totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee() - order.getCouponUse());
					}
				}
                else{
					order.setTotalPrice(totalpreprice + order.getPayTypeFee() + order.getDeliverTypeFee());
				}
                
                order.setTotalPriceChangeInfo(info);
                tdManagerLogService.addLog("edit", info, req);
            }
            
            // 修改尾款金额
            else if (type.equalsIgnoreCase("EditfinalPayment"))
            {
                double totalleftprice = Double.parseDouble(data);
                order.setTotalLeftPrice(totalleftprice);                              
                
                order.setTotalPriceChangeInfo(info);
                tdManagerLogService.addLog("edit", info, req);
            }
            
            // 修改配送费用
            else if (type.equalsIgnoreCase("editDeliveryPrice"))
            {
                double deliveryPrice = Double.parseDouble(data);
                order.setDeliverTypeFee(deliveryPrice);
                order.setTotalPrice(deliveryPrice + order.getPayTypeFee() + order.getTotalGoodsPrice());
                order.setDeliverTypePriceChangeInfo(info);
                tdManagerLogService.addLog("edit", info, req);
            }
            // 修改支付手续费
            else if (type.equalsIgnoreCase("editPayPrice"))
            {
                double payPrice = Double.parseDouble(data);
                order.setPayTypeFee(payPrice);
                order.setTotalPrice(payPrice + order.getTotalGoodsPrice() + order.getDeliverTypeFee());
                order.setPayTypePriceChangeInfo(info);
                tdManagerLogService.addLog("edit", info, req);
            }
            // 修改联系方式
            else if (type.equalsIgnoreCase("editContact"))
            {
                order.setShippingName(name);
                order.setShippingAddress(address);
                order.setShippingPhone(mobile);
                order.setPostalCode(postal);
            }
            // 确认订单
            else if (type.equalsIgnoreCase("orderConfirm"))
            {
                if (order.getStatusId().equals(1L))
                {
                    order.setStatusId(2L);
                    order.setCheckTime(new Date());
                }
            }
            // 确认付款
            else if (type.equalsIgnoreCase("orderPay"))
            {
                if (order.getStatusId().equals(2L))
                {
                        order.setStatusId(3L);
                        order.setPayTime(new Date());
                }
            }
            // 确认已服务  //确认已经评价 gl
            else if (type.equalsIgnoreCase("orderService"))
            {
                if (order.getStatusId().equals(4L))
                {
                    order.setStatusId(5L);
                    //order.setServiceTime(new Date());
                    order.setFinishTime(new Date());
                }
            }
            // 确认已服务 gl
            else if(type.equals("orderPayLeft")){
            	if(order.getStatusId() == 3L){
            		order.setStatusId(4L);
            		order.setServiceTime(new Date());
            	}
            	
            	TdUser tdUser = tdUserService.findByUsername(order.getUsername());
            	List<TdOrderGoods> tdOrderGoodsList = order.getOrderGoodsList();

                Long totalPoints = 0L;
                // 返利总额
                if (null != tdOrderGoodsList) {
                    for (TdOrderGoods tog : tdOrderGoodsList) {
                        TdGoods tdGoods = tdGoodsService.findOne(tog.getGoodsId());

                        if (null != tdGoods && null != tdGoods.getReturnPoints()) {
                            totalPoints += tdGoods.getReturnPoints();
                        }
                    }

                    final Long totalPointsDely = totalPoints;
                    final TdUser tdUserDely = tdUser;
                    
                    // 用户返利
                    if (null != tdUser) {
                       // System.out.println("-------设定要指定任务--------");  
                        TdUserPoint userPoint = new TdUserPoint();
                        
                        userPoint.setDetail("购买商品赠送积分");
                        userPoint.setOrderNumber(order.getOrderNumber());
                        userPoint.setPoint(totalPointsDely);
                        userPoint.setPointTime(new Date());
                        userPoint.setTotalPoint(tdUserDely.getTotalPoints() + totalPointsDely);
                        userPoint.setUsername(tdUserDely.getUsername());

                        userPoint = tdUserPointService.save(userPoint);

                        tdUserDely.setTotalPoints(userPoint.getTotalPoint());
                        
                        tdUserService.save(tdUserDely);
                    }
                }
            }
            
            // 货到付款确认付款
            else if (type.equalsIgnoreCase("orderPayOffline"))
            {
                if (order.getStatusId().equals(2L)
                        && !order.getIsOnlinePay())
                {
                    order.setStatusId(5L);
                    order.setPayTime(new Date());
                }
            }
            // 确认发货
            else if (type.equalsIgnoreCase("orderDelivery"))
            {
                if (order.getStatusId().equals(3L))
                {
                    order.setDeliverTypeId(deliverTypeId);
                    order.setExpressNumber(expressNumber);
                    order.setStatusId(4L);
                    order.setSendTime(new Date());
                    
                    TdUser tdUser = tdUserService.findByUsername(order.getUsername());
                    
                    if (null != tdUser)
                    {
//                        SMSUtil.send(tdUser.getMobile(), "28744",
//                                new String[] { order.getUsername(),
//                                        order.getOrderNumber()});
                    }
                }
            }
            // 确认已评价
            else if (type.equalsIgnoreCase("orderReceive"))
            {
                if (order.getStatusId().equals(4L))
                {
                    order.setStatusId(5L);
                    order.setReceiveTime(new Date());
                }
            }
            // 确认完成
            else if (type.equalsIgnoreCase("orderFinish"))
            {
                if (order.getStatusId().equals(5L))
                {
                    order.setStatusId(6L);
                    order.setFinishTime(new Date());
                    
                    tdUserService.addTotalSpend(order.getUsername(), order.getTotalPrice());
                }
            }
            // 确认取消
            else if (type.equalsIgnoreCase("orderCancel"))
            {
                order.setStatusId(6L);
                order.setCancelTime(new Date());
            }
            // 修改订单状态
            else if (type.equalsIgnoreCase("changeOderStatus"))
            {
                if (null != changeOrderStatusId) {
					order.setStatusId(changeOrderStatusId);
				}
            }
            tdOrderService.save(order);
            tdManagerLogService.addLog("edit", "修改订单", req);
            
            res.put("code", 0);
            res.put("message", "修改成功!");
            return res;
        }
        
        res.put("message", "参数错误!");
        return res;
    }
    
    @RequestMapping(value = "order/sumPrice" , method = RequestMethod.GET)
    public String sumPrice(String date,String date1,HttpServletRequest request){
    	
    	
    	return "/";
    }
    
  
    @ModelAttribute
    public void getModel(@RequestParam(value = "payTypeId", required = false) Long payTypeId,
                    @RequestParam(value = "deliveryTypeId", required = false) Long deliveryTypeId,
                    @RequestParam(value = "diySiteId", required = false) Long diySiteId,
                        Model model) {
        if (null != payTypeId) {
            model.addAttribute("tdPayType", tdPayTypeService.findOne(payTypeId));
        }
        
        if (null != deliveryTypeId) {
            model.addAttribute("tdDeliveryType", tdDeliveryTypeService.findOne(deliveryTypeId));
        }
        
        if (null != diySiteId) {
            model.addAttribute("tdDiySite", tdDiySiteService.findOne(diySiteId));
        }
    }
    
    private void btnSave(String type, Long[] ids, Long[] sortIds)
    {
        if (null == type || type.isEmpty())
        {
            return;
        }
        
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            if (type.equalsIgnoreCase("pay"))
            {
                TdPayType e = tdPayTypeService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdPayTypeService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("delivery"))
            {
                TdDeliveryType e = tdDeliveryTypeService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdDeliveryTypeService.save(e);
                    }
                }
            }
            else if (type.equalsIgnoreCase("diysite"))
            {
                TdDiySite e = tdDiySiteService.findOne(id);
                
                if (null != e)
                {
                    if (sortIds.length > i)
                    {
                        e.setSortId(sortIds[i]);
                        tdDiySiteService.save(e);
                    }
                }
            }
        }
    }
    
    private void btnDelete(String type, Long[] ids, Integer[] chkIds)
    {
        if (null == type || type.isEmpty())
        {
            return;
        }
        
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
                
                if (type.equalsIgnoreCase("pay"))
                {
                    tdPayTypeService.delete(id);
                }
                else if (type.equalsIgnoreCase("delivery"))
                {
                    tdDeliveryTypeService.delete(id);
                }
                else if (type.equalsIgnoreCase("diysite"))
                {
                    tdDiySiteService.delete(id);
                }
            }
        }
    }
    
    private void btnConfirm(Long[] ids, Integer[] chkIds)
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
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有待确认(1L)订单能进行确认，确认后状态为待发货(3L)
                if (tdOrder.getStatusId().equals(1L))
                {
                    tdOrder.setStatusId(3L);
                    tdOrder.setCheckTime(new Date()); // 确认时间
                    tdOrderService.save(tdOrder);
                }
            }
        }
    }
    
    private void btnCancel(Long[] ids, Integer[] chkIds)
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
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有待确认(1L)、待付款(2L)订单能进行删除，确认后状态为已取消(7L)
//                if (tdOrder.getStatusId().equals(1L) ||
//                        tdOrder.getStatusId().equals(2L))
//                {
                    tdOrder.setStatusId(7L);
                    tdOrder.setCancelTime(new Date()); // 取消时间
                    tdOrderService.save(tdOrder);
//                }
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
                
                TdOrder tdOrder= tdOrderService.findOne(id);
                
                // 只有已取消(7L)订单能进行删除
                if (tdOrder.getStatusId().equals(7L))
                {
                    tdOrderService.delete(tdOrder);
                }
            }
        }
    }
    
    /**
     * 图片地址字符串整理，多张图片用,隔开
     * 
     * @param params
     * @return
     */
    private String parsePicUris(String[] uris)
    {
        if (null == uris || 0 == uris.length)
        {
            return null;
        }
        
        String res = "";
        
        for (String item : uris)
        {
            String uri = item.substring(item.indexOf("|")+1, item.indexOf("|", 2));
            
            if (null != uri)
            {
                res += uri;
                res += ",";
            }
        }
        
        return res;
    }
    
    /**
     * 订单过滤
     * @author gl
     */
    private List<TdOrder> orderFiler(Long statusId, Long typeId){
    	if(statusId == 0){
    		if(typeId ==0){
    			return tdOrderService.findAll();
    		}else{
    			return tdOrderService.findBytypeIdOrderByIdDesc(typeId);
    		}
    	}else{
    		if(typeId == 0){
    			return tdOrderService.findByStatusId(statusId);
    		}else{
    			return tdOrderService.findByStatusAndTypeIdOrderByIdDesc(statusId, typeId);
    		}
    	}
    }
    
    /**
     * 统计未销售额(statusId等于2或7)
     * @author gl
     */
    private Double countUnsales(){
    	return countPrice(0L) - countPrice(2L) - countPrice(7L);
    }
    
    /**
     * 统计各类状态的销售额
     * @author gl
     */
    private Double countPrice(Long statusId){
    	List<TdOrder> list = orderFiler(statusId, 0L);
    	return countprice(list);
    }
    
    /**
     * 统计各类类型的销量
     * @author gl
     */
    private Long countNum(Long typeId){
    	List<TdOrder> list = orderFiler(0L, typeId);
    	return new Long(list.size());
    }
    
    
    
    
    
}
