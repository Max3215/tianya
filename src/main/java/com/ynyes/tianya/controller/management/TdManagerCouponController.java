package com.ynyes.tianya.controller.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.tianya.entity.TdCoupon;
import com.ynyes.tianya.entity.TdCouponType;
import com.ynyes.tianya.entity.TdDiySite;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdManager;
import com.ynyes.tianya.entity.TdManagerRole;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.entity.TdOrderGoods;
import com.ynyes.tianya.entity.TdUser;
import com.ynyes.tianya.service.TdCouponService;
import com.ynyes.tianya.service.TdCouponTypeService;
import com.ynyes.tianya.service.TdDiySiteService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdManagerLogService;
import com.ynyes.tianya.service.TdManagerRoleService;
import com.ynyes.tianya.service.TdManagerService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.service.TdUserService;
import com.ynyes.tianya.util.SiteMagConstant;

/**
 * 优惠券管理
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter/coupon")
public class TdManagerCouponController {
    
    @Autowired
    private TdCouponTypeService tdCouponTypeService;
    
    @Autowired
    private TdCouponService tdCouponService;
    
    @Autowired
    private TdManagerLogService tdManagerLogService;
    
    @Autowired 
    private TdDiySiteService tdDiySiteService;
    
    @Autowired
    private TdProductCategoryService tdProductCategoryService;
    
    @Autowired
    private TdUserService tdUserService;
    
    @Autowired
    private TdGoodsService tdGoodsService;
    
    @Autowired
    TdManagerRoleService tdManagerRoleService;
    
    @Autowired
    TdManagerService tdManagerService;
//    @RequestMapping(value="/check", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> validateForm(String param, Long id) {
//        Map<String, String> res = new HashMap<String, String>();
//        
//        res.put("status", "n");
//        
//        if (null == param || param.isEmpty())
//        {
//            res.put("info", "该字段不能为空");
//            return res;
//        }
//        
//        if (null == id)
//        {
//            if (null != tdSiteService.findByTitle(param))
//            {
//                res.put("info", "已存在同名站点");
//                return res;
//            }
//        }
//        else
//        {
//            if (null != tdSiteService.findByTitleAndIdNot(param, id))
//            {
//                res.put("info", "已存在同名站点");
//                return res;
//            }
//        }
//        
//        res.put("status", "y");
//        
//        return res;
//    }
    
    @RequestMapping(value="/type/list")
    public String couponType(String __EVENTTARGET,
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
                btnTypeDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除优惠券类型", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnTypeSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改优惠券类型", req);
            }
        }
        
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        List<TdCouponType> couponTypeList = null;
        
        couponTypeList = tdCouponTypeService.findAllOrderBySortIdAsc();
        
        map.addAttribute("coupon_type_list", couponTypeList);
        
        return "/site_mag/coupon_type_list";
    }
    
    @RequestMapping(value = "/type/edit")
    public String typeEdit(Long id, String __VIEWSTATE, ModelMap map,
            HttpServletRequest req) {
        
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        map.addAttribute("category_list", tdProductCategoryService.findAll());

        if (null != id) {
            map.addAttribute("coupon_type", tdCouponTypeService.findOne(id));
        }
        return "/site_mag/coupon_type_edit";
    }
    
    @RequestMapping(value = "/type/save")
    public String typeSave(TdCouponType tdCouponType, String __VIEWSTATE, ModelMap map,
            HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        if (null == tdCouponType.getId()) {
            tdManagerLogService.addLog("add", "用户修改优惠券类型", req);
        } else {
            tdManagerLogService.addLog("edit", "用户修改优惠券类型", req);
        }

        tdCouponTypeService.save(tdCouponType);       
        return "redirect:/Verwalter/coupon/type/list";
    }
    
    
    @RequestMapping(value="/list")
    public String setting(Integer page,
                          Integer size,
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
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除优惠券", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改优惠券", req);
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
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);

        Page<TdCoupon> couponPage = null;
        
        couponPage = tdCouponService.findByIsDistributtedFalseOrderBySortIdAsc(page, size);
        
        map.addAttribute("coupon_page", couponPage);
        
        return "/site_mag/coupon_list";
    }
    /**
     * @author lulu
     * @注释 优惠劵编辑
     * @param id
     * @param __VIEWSTATE
     * @param map
     * @param req
     * @return
     */
    
    @RequestMapping(value="/edit")
    public String orderEdit(Long id,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        map.addAttribute("diy_site_list", tdDiySiteService.findByIsEnableTrue());
        
        List<TdCouponType> couponTypeList = null;
        
        couponTypeList = tdCouponTypeService.findAllOrderBySortIdAsc();
        
        map.addAttribute("coupon_type_list", couponTypeList);

        if (null != id)
        {
            map.addAttribute("coupon", tdCouponService.findOne(id));
            return "/site_mag/coupon_edit_hasId";
        }
        return "/site_mag/coupon_edit";
    }
    
    /**
     * @author lulu
     * @注释 优惠劵列表展示
     * @param page
     * @param size
     * @param __EVENTTARGET
     * @param __EVENTARGUMENT
     * @param __VIEWSTATE
     * @param listId
     * @param listChkId
     * @param listSortId
     * @param map
     * @param diysiteId
     * @param keywords
     * @param isUsed
     * @param typeId
     * @param exportUrl
     * @param exportAllUrl
     * @param resp
     * @param req
     * @return
     */
    
    @SuppressWarnings("deprecation")
	@RequestMapping(value="/distributed/list")
    public String distributedList(Integer page,
                          Integer size,
                          String __EVENTTARGET,
                          String __EVENTARGUMENT,
                          String __VIEWSTATE,
                          Long[] listId,
                          Integer[] listChkId,
                          Long[] listSortId,
                          ModelMap map,
                          Long diysiteId,
                          String keywords,
                          Long isUsed,
                          Long typeId,
                          String exportUrl,
                          String exportAllUrl,
                          HttpServletResponse resp,
                          HttpServletRequest req){
        
        String username = (String) req.getSession().getAttribute("manager");
        
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnDelete"))
            {
                btnDelete(listId, listChkId);
                tdManagerLogService.addLog("delete", "删除优惠券", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("btnSave"))
            {
                btnSave(listId, listSortId);
                tdManagerLogService.addLog("edit", "修改优惠券", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("export"))
            {
            	exportUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("export", "导出优惠券领取记录", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("exportAll"))
            {
            	exportAllUrl = SiteMagConstant.backupPath;
                tdManagerLogService.addLog("exportAll", "导出全部优惠券领取记录", req);
            }
            else if (__EVENTTARGET.equalsIgnoreCase("changeDiysite")) {
		   
			}else if(__EVENTTARGET.equalsIgnoreCase("changeType")){
				
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
        
        if (null == diysiteId) 
        {
			diysiteId = 0L;
		}
        
        if (null == isUsed) 
        {
        	isUsed = 0L;
		}
        
        if(null == typeId)
        {
        	typeId =0L;
        }
        
        
        /**
  		 * @author lulu
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
          cell.setCellValue("优惠券类型");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 1);  
          cell.setCellValue("姓名");  
          cell.setCellStyle(style);  
          cell = row.createCell((short) 2);  
          cell.setCellValue("电话");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 3);  
          cell.setCellValue("抵用价格");
          cell = row.createCell((short) 4);  
          cell.setCellValue("有效时长");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 5);  
          cell.setCellValue("领用时间");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 6);  
          cell.setCellValue("有效截止时间");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 7);  
          cell.setCellValue("消费密码");  
          cell.setCellStyle(style);
          cell = row.createCell((short) 8);  
          cell.setCellValue("是否核销");  
          cell.setCellStyle(style);
          
        if (null != exportAllUrl) {//导出全部
			List<TdCoupon> tdCouponslist = tdCouponService.findByIsDistributtedTrueOrderByIdDesc();
			if (ImportAllData(tdCouponslist, row, cell, sheet)) {
				download(wb, username, resp);
			}
		}
        
        Page<TdCoupon> couponPage = null;
        
        if (null == keywords) {//无搜索
			if (diysiteId.equals(0L)) {//全部同盟店
				if (isUsed.equals(0L)) {//两种核销状态
					if(typeId.equals(0L)){//类型
						couponPage = tdCouponService.findByIsDistributtedTrueOrderByIdDesc(page, size);			        
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}
					else{
						couponPage = tdCouponService.findByTypeIdAndIsDistributtedTrueOrderByIdDesc(typeId,page,size);
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}
				}
				else{
					if (isUsed.equals(1L)) {//已核销
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
						else{
							couponPage = tdCouponService.findByTypeIdAndIsDistributtedTrueAndIsUsedTrueOrderByIdDesc(typeId,page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
					if (isUsed.equals(2L)) {
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
						else{
							couponPage = tdCouponService.findByTypeIdAndIsDistributtedTrueAndIsUsedFalseOrderByIdDesc(typeId,page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
				}
			}
			else{
				if (isUsed.equals(0L)) {//两种核销状态	
					if(typeId.equals(0L)){//类型
						couponPage = tdCouponService.findByIsDistributtedTrueAndDiySiteIdOrderByIdDesc(diysiteId, page, size);			        
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}else{
						couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndDiySiteIdOrderByIdDesc(typeId,diysiteId, page, size);			        
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}
				}
				else{
					if (isUsed.equals(1L)) {//已核销
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(diysiteId, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}else{
							couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedTrueOrderByIdDesc(typeId,diysiteId, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
					if (isUsed.equals(2L)) {
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(diysiteId, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}else{
							couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndIsUsedFalseOrderByIdDesc(typeId,diysiteId, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
				}
			}
		}
        else{//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        	if (diysiteId.equals(0L)) {//全部同盟店
				if (isUsed.equals(0L)) {
					if(typeId.equals(0L)){//类型
						couponPage = tdCouponService.findByIsDistributtedTrueAndContainingKeywords(keywords, page, size);			        
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}else{
						couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndContainingKeywords(typeId,keywords, page, size);			        
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}
				}
				else{
					if (isUsed.equals(1L)) {//已核销
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndIsUsedTrueAndContainingKeywords(keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}else{
							couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndIsUsedTrueAndContainingKeywords(typeId,keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
					if (isUsed.equals(2L)) {
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndIsUsedFalseAndContainingKeywords(keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}else{
							couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndIsUsedFalseAndContainingKeywords(typeId,keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
				}
			}
			else{
				if (isUsed.equals(0L)) {//两种核销状态	
					if(typeId.equals(0L)){//类型
						couponPage = tdCouponService.findByIsDistributtedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(diysiteId,keywords, page, size);			        
						map.addAttribute("coupon_page", couponPage);
						if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}else{
						couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(typeId,diysiteId,keywords, page, size);			        
				        map.addAttribute("coupon_page", couponPage);
				        if (null != exportUrl) {                          	
                          	if (ImportData(couponPage, row, cell, sheet)) {
                          		download(wb, username, resp);
    						}                         	                           
    					}
					}
				}
				else{
					if (isUsed.equals(1L)) {//已核销
						if(typeId.equals(0L)){//类型
							couponPage = tdCouponService.findByIsDistributtedTrueAndAndIsUsedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(diysiteId,keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}else{
							couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndAndIsUsedTrueAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(typeId,diysiteId,keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
					if (isUsed.equals(2L)) {
						if(typeId.equals(0L)){
							couponPage = tdCouponService.findByIsDistributtedTrueAndAndIsUsedFalseAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(diysiteId,keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}else{
							couponPage = tdCouponService.findBytypeIdAndIsDistributtedTrueAndAndIsUsedFalseAndDiySiteIdAndContainingKeywordsOrderBySortIdAsc(typeId,diysiteId,keywords, page, size);
							map.addAttribute("coupon_page", couponPage);
							if (null != exportUrl) {                          	
	                          	if (ImportData(couponPage, row, cell, sheet)) {
	                          		download(wb, username, resp);
	    						}                         	                           
	    					}
						}
					}
				}
			}
        }
        map.addAttribute("page", page);
        map.addAttribute("size", size);
        map.addAttribute("diysiteId", diysiteId);
        map.addAttribute("isUsed", isUsed);
        map.addAttribute("keywords", keywords);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        //查询同盟店
        List<TdDiySite> tdDiySitelist = tdDiySiteService.findByIsEnableTrue();
        map.addAttribute("tdDiySite_list", tdDiySitelist);
        //查询优惠券类型
        map.addAttribute("couponType_list", tdCouponTypeService.findAllOrderBySortIdAsc());
        map.addAttribute("typeId", typeId);
        
        return "/site_mag/coupon_distributed_list";
    }
    
    /**
	 * @author lulu
	 * @注释：将page中的订单数据存入excel表格中
	 */
    @SuppressWarnings("deprecation")
	public boolean ImportData(Page<TdCoupon> tdCouponPage, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	
    	for (int i = 0; i < tdCouponPage.getContent().size(); i++)  
        {  
    	 				
            row = sheet.createRow((int) i + 1);  
            TdCoupon tdCoupon = tdCouponPage.getContent().get(i);  
            
            // 第四步，创建单元格，并设置值  
            if (null != tdCoupon.getTypeTitle()) {
            	row.createCell((short) 0).setCellValue(tdCoupon.getTypeTitle());
			}           
            
            if (null != tdCoupon.getUsername()) {
            	row.createCell((short) 1).setCellValue(tdCoupon.getUsername());
			}
            
            if (null != tdCoupon.getMobile()) {
            	row.createCell((short) 2).setCellValue(tdCoupon.getMobile());
			}
            
            if (null != tdCoupon.getPrice()) {
            	row.createCell((short) 3).setCellValue(tdCoupon.getPrice());
			}
            
            if (null != tdCoupon.getTotalDays()) {
            	row.createCell((short) 4).setCellValue(tdCoupon.getTotalDays());
			}
            
            if (null != tdCoupon.getGetTime()) {
            	cell = row.createCell((short) 5);
            	cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tdCoupon.getGetTime()));
			}
            
            if (null != tdCoupon.getExpireTime()) {
            	cell = row.createCell((short) 6);
            	cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tdCoupon.getExpireTime()));
			}
            
            if (null != tdCoupon.getConsumerPassword()) {
            	row.createCell((short) 7).setCellValue(tdCoupon.getConsumerPassword());
			}
            
            if (null != tdCoupon.getIsUsed()) {
            	if (tdCoupon.getIsUsed()) {
            		row.createCell((short) 8).setCellValue("已核销");
				}else{
					row.createCell((short) 8).setCellValue("未核销");
				}
            	
			}          
         
        } 
    	return true;
    }
    
    /**
	 * @author lulu
	 * @注释：将list中的数据存入excel表格中
	 */
    @SuppressWarnings("deprecation")
	public boolean ImportAllData(List<TdCoupon> tdCouponPage, HSSFRow row, HSSFCell cell, HSSFSheet sheet){
    	
    	for (int i = 0; i < tdCouponPage.size(); i++)  
        {  
    	 				
            row = sheet.createRow((int) i + 1);  
            TdCoupon tdCoupon = tdCouponPage.get(i);  
            
            // 第四步，创建单元格，并设置值  
            if (null != tdCoupon.getTypeTitle()) {
            	row.createCell((short) 0).setCellValue(tdCoupon.getTypeTitle());
			}           
            
            if (null != tdCoupon.getUsername()) {
            	row.createCell((short) 1).setCellValue(tdCoupon.getUsername());
			}
            
            if (null != tdCoupon.getMobile()) {
            	row.createCell((short) 2).setCellValue(tdCoupon.getMobile());
			}
            
            if (null != tdCoupon.getPrice()) {
            	row.createCell((short) 3).setCellValue(tdCoupon.getPrice());
			}
            
            if (null != tdCoupon.getTotalDays()) {
            	row.createCell((short) 4).setCellValue(tdCoupon.getTotalDays());
			}
            
            if (null != tdCoupon.getGetTime()) {
            	cell = row.createCell((short) 5);
            	cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tdCoupon.getGetTime()));
			}
            
            if (null != tdCoupon.getExpireTime()) {
            	cell = row.createCell((short) 6);
            	cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tdCoupon.getExpireTime()));
			}
            
            if (null != tdCoupon.getConsumerPassword()) {
            	row.createCell((short) 7).setCellValue(tdCoupon.getConsumerPassword());
			}
            
            if (null != tdCoupon.getIsUsed()) {
            	if (tdCoupon.getIsUsed()) {
            		row.createCell((short) 8).setCellValue("已核销");
				}else{
					row.createCell((short) 8).setCellValue("未核销");
				}
            	
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
	          FileOutputStream fout = new FileOutputStream(exportUrl+"distributedCoupon.xls");  
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
				File file = new File(exportUrl + "distributedCoupon.xls");
                 
             if (file.exists())
                 {
                   try {
                         resp.reset();
                         resp.setHeader("Content-Disposition", "attachment; filename="
                                 + "distributedCoupon.xls");
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
     * @author lulu
     * @注释  后台添加优惠劵保存
     * @param __VIEWSTATE
     * @param leftNumbers
     * @param typeId
     * @param map
     * @param req
     * @return
     */
    
    @RequestMapping(value="/save")
    public String orderEdit(
                        String __VIEWSTATE,
                        Long leftNumber,
                        Long typeId,
                        ModelMap map,
                        Long sortId,
                        Long couponId,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        TdCouponType couponType = tdCouponTypeService.findOne(typeId);
        TdCoupon coupon = null;
        if(couponId != null || "".equals(couponId)){
        	coupon = tdCouponService.findOne(couponId);
        	
        }else{
        	coupon = new TdCoupon();        	
        }
        coupon.setTypeId(typeId);
        coupon.setTypeTitle(couponType.getTitle());
        coupon.setTypeCategoryId(couponType.getProductTypeId());
        coupon.setTypeDescription(couponType.getDescription());
        coupon.setTypePicUri(couponType.getPicUri());
        coupon.setPrice(couponType.getPrice());
        coupon.setCanUsePrice(couponType.getCanUsePrice());
        coupon.setLeftNumber(leftNumber);
        coupon.setSortId(sortId);
        coupon.setTotalDays(couponType.getTotalDays());
        if(couponId == null || couponId.equals("")){
        	coupon.setGetNumber(0L);        	
        }
        tdCouponService.save(coupon);
        tdManagerLogService.addLog("add", "用户添加优惠券", req);
        
        return "redirect:/Verwalter/coupon/list";
    }
    /**
	 * @author lc
	 * @注释：通过id返回title
	 */
    @RequestMapping(value = "/getTitle", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(Long typeId, 
			 HttpServletRequest request) {
    	Map<String, Object> res = new HashMap<String, Object>();

		res.put("code", 1);
		if (null == typeId) {
			res.put("msg", "error");
			return res;
		}
		
		TdCouponType tdCouponType = tdCouponTypeService.findOne(typeId);
		res.put("typetitle", tdCouponType.getTitle());
		res.put("code", 0);
		return res;
    }
	
	
    @ModelAttribute
    public void getModel(@RequestParam(value = "couponTypeId", required = false) Long couponTypeId,
                        @RequestParam(value = "couponId", required = false) Long couponId,
                        Model model) {
        if (null != couponTypeId) {
            model.addAttribute("tdCouponType", tdCouponTypeService.findOne(couponTypeId));
        }
        
        if (null != couponId) {
            model.addAttribute("tdCoupon", tdCouponService.findOne(couponId));
        }
    }
    
    private void btnTypeSave(Long[] ids, Long[] sortIds)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            TdCouponType e = tdCouponTypeService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdCouponTypeService.save(e);
                }
            }
        }
    }
    
    private void btnTypeDelete(Long[] ids, Integer[] chkIds)
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
                
                tdCouponTypeService.delete(id);
            }
        }
    }
    
    private void btnSave(Long[] ids, Long[] sortIds)
    {
        if (null == ids || null == sortIds
                || ids.length < 1 || sortIds.length < 1)
        {
            return;
        }
        
        for (int i = 0; i < ids.length; i++)
        {
            Long id = ids[i];
            
            TdCoupon e = tdCouponService.findOne(id);
            
            if (null != e)
            {
                if (sortIds.length > i)
                {
                    e.setSortId(sortIds[i]);
                    tdCouponService.save(e);
                }
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
                
                tdCouponService.delete(id);
            }
        }
    }
}
